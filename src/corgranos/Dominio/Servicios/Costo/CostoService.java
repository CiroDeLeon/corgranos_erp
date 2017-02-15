/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Servicios.Costo;

import corgranos.Dominio.Core.Maquila.ReporteMaquilaCerrada;
import corgranos.Dominio.Core.Articulo.Articulo;
import corgranos.Dominio.Core.Articulo.ArticuloService;
import corgranos.Dominio.Core.Maquila.Maquila;
import corgranos.Dominio.Core.Maquila.MaquilaService;
import corgranos.Dominio.Core.Maquila.EstadoDeResultadosDeMaquilasCerradas;
import corgranos.Dominio.Servicios.DataConfiguration;
import corgranos.Dominio.Servicios.DataConfigurationService;
import corgranos.Dominio.Servicios.Inventario.InventarioService;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import sic.Dominio.Core.Documento.Asiento;
import sic.Dominio.Core.Documento.Documento;
import sic.Dominio.Core.Documento.DocumentoService;
import sic.Dominio.Core.PUC.Cta_PUC;
import sic.Dominio.Core.PUC.PucService;
import sic.Dominio.Servicios.Contabilidad.ContabilidadService;

/**
 *
 * @author FANNY BURGOS
 */
public class CostoService {
    MaquilaService    ms;
    InventarioService is;
    ArticuloService as;
    ContabilidadService cs;        
    DataConfigurationService dcs; 
    private DataConfiguration dc;
    public double preciokg;
    private boolean ObtenerAsientosFacturados=true;
    double masablancaproducida=0;
    Maquila m;
    Date fecha;
    double suma;
    Vector<Articulo> lista_precio_subproductos;
    public  CostoService(Object idMaquila,Date fecha) {
        ms=new MaquilaService();
        is=new InventarioService();
        as=new ArticuloService();
        cs=new ContabilidadService();
        this.fecha=fecha;
        m=ms.getMaquilaDao().ObtenerMaquila(idMaquila);
        dcs=new DataConfigurationService();
        dc=dcs.getDao().ObtenerConfiguracion();
    }
    public  CostoService(Object idMaquila) {
        ms=new MaquilaService();
        is=new InventarioService();
        as=new ArticuloService();
        cs=new ContabilidadService();
        this.fecha=Calendar.getInstance().getTime();
        m=ms.getMaquilaDao().ObtenerMaquila(idMaquila);
        dcs=new DataConfigurationService();
        dc=dcs.getDao().ObtenerConfiguracion();
    }    
    public  double ObtenerCostoPaddy(){        
        double compra=m.getTotalcosto();
        return compra;
    }
    public  double ObtenerCostoSecamientoTrilla(){
        return m.getValortrilla()*m.getTotalpesobruto();
    }
    public  double ObtenerCantidadKgArrozBlanco(){
         Iterator <Articulo> it=as.ObtenerArrozBlanco().iterator();
        double sumakg=0;        
        while(it.hasNext()){
            Articulo a=it.next();
            double entradas=is.getDao().ObtenerEntradasPorProducciones(m.getId(),a.getId());
            if(entradas>0){
               double kgs=entradas*a.getCantidadkg();
               sumakg+=kgs;
            }
        }
        return sumakg;
    }
    public  Vector<Asiento> ObtenerAsientosDeCierreDeMaquila(){
        this.ObtenerAsientosFacturados=true;
        suma=0;
        Vector<Asiento> lista=new Vector<Asiento>();
        Asiento paddy=this.ObtenerAsientoDePaddy();        
        lista.add(paddy);
        Iterator<Asiento> it=this.ObtenerAsientosDeEmpaques().iterator();
        while(it.hasNext()){
            Asiento a=it.next();            
            lista.add(a);
        }        
        Asiento trilla=this.ObtenerAsientoDeTrilla();
        lista.add(trilla);
        //sumamos creditos para obtener 
        //costo de arroz_blanco incluyendo subproductos 
        double costo_arroz_blanco=0;
        for(int i=0;i<lista.size();i++){
            costo_arroz_blanco+=lista.get(i).getCredito();
        }
        Vector <Asiento> subproductos=this.ObtenerAsientosDeSubproductos();
        System.out.println("COSTO ARROZ BLANCO "+costo_arroz_blanco);
        double costo_subproductos=costo_arroz_blanco*0.2717;
        System.out.println("CANTIDAD SUBPRODUCTOS PRODUCIDOS"+subproductos.size());
        double porcentage;
        if(subproductos.size()==0){
           porcentage=0; 
        }else{
           porcentage=100/subproductos.size(); 
        }
        it=subproductos.iterator();
        double precio_kilo_subproductos=costo_subproductos/this.obtener_cantidad_de_kilogramos_de_subproductos_producidos();        
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("PRECIO KG SUBPRODUCTO="+precio_kilo_subproductos);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        this.lista_precio_subproductos=new Vector();
        while(it.hasNext()){
            Asiento a=it.next();        
            Object idArticulo=a.getCtaPuc().getId().toString().substring(8,a.getCtaPuc().getId().toString().length());
            Articulo articulo=new ArticuloService().getArticulo_dao().ObtenerArticulo(idArticulo);            
            a.setDebito(Math.round(a.getEntradas()*articulo.getCantidadkg()*precio_kilo_subproductos));
            suma=suma-a.getDebito();
            lista.add(a);
            // CODIGO INSEGURO
            articulo.setPreciounitario(articulo.getCantidadkg()*precio_kilo_subproductos);
            lista_precio_subproductos.add(articulo);            
            /////////////////////////////////////////////////////////////////////////////////
        }        
        it=this.ObtenerAsientosDeArroz().iterator();        
        while(it.hasNext()){
            Asiento a=it.next();
            lista.add(a);
        }
        it=this.ObtenerAsientosDeSubproductosFacturados().iterator();        
        while(it.hasNext()){
            Asiento a=it.next();
            lista.add(a);
        }
        it=this.ObtenerAsientosFacturaDeArroz().iterator();        
        while(it.hasNext()){
            Asiento a=it.next();
            lista.add(a);
        }
        return lista;
    }
    private double obtener_cantidad_de_kilogramos_de_subproductos_producidos(){
        Iterator<Articulo> it=as.ObtenerSubproductos().iterator();
        double suma_kg=0;
        while(it.hasNext()){
            Articulo a=it.next();
            double entradas=is.getDao().ObtenerEntradasPorProducciones(m.getId(),a.getId());
            if(entradas>0){
               double kgs=entradas*a.getCantidadkg();
               suma_kg+=kgs;
            }
        }
        return suma_kg;
    }
    public  Vector<Asiento> ObtenerAsientosDeCostos(){
        this.ObtenerAsientosFacturados=false;
        suma=0;
        Vector<Asiento> lista=new Vector<Asiento>();
        Asiento paddy=this.ObtenerAsientoDePaddy();        
        lista.add(paddy);
        Iterator<Asiento> it=this.ObtenerAsientosDeEmpaques().iterator();
        while(it.hasNext()){
            Asiento a=it.next();            
            lista.add(a);
        }        
        Asiento trilla=this.ObtenerAsientoDeTrilla();
        lista.add(trilla);
        this.masablancaproducida=0;
        it=this.ObtenerAsientosDeSubproductos().iterator();
        while(it.hasNext()){
            Asiento a=it.next();        
            lista.add(a);
        }        
        it=this.ObtenerAsientosDeArroz().iterator();        
        while(it.hasNext()){
            Asiento a=it.next();
            lista.add(a);
        }
        return lista;
    }
    private Asiento ObtenerAsientoDePaddy(){
        PucService ps=new PucService();
        Cta_PUC cta=ps.getDao().ObtenerCtaPuc(getDc().getAux_Paddy());
        Asiento a=new Asiento();
        a.setCtaPuc(cta);
        a.setDetalle("Cierre Maq. "+this.m.getId());
        a.setNoFactura(0);
        a.setNoFacturaCompra(0);
        a.setDebito(0);
        a.setEntradas(0);
        a.setCredito(this.ObtenerCostoPaddy());
        a.setSalidas(m.getTotalpeso14_1());
        a.setBaseIVA(0);
        a.setBaseRTF(0);        
        System.out.println(a.getCtaPuc().getId()+"    "+a.getDebito()+"   "+a.getCredito()+"   "+a.getEntradas()+"   "+a.getSalidas());
        suma=suma+a.getCredito();
        return a;
    }
    private Vector<Asiento> ObtenerAsientosDeEmpaques(){
        Vector<Asiento> lista=new Vector<Asiento>();
        Iterator <Articulo> it=as.ObtenerProductosYSubproductos().iterator();        
        while(it.hasNext()){
            Articulo ar=it.next();
            double salida=is.getDao().ObtenerEntradasPorProducciones(m.getId(),ar.getId());
            if(salida>0){
               double saldo=cs.getDao().ObtenerSaldo(ar.getAuxempaque(), fecha,true,null);
               double existencia=cs.getDao().ObtenerExistencia(ar.getAuxempaque(),fecha,true,null);
               double precio=saldo/existencia;            
               double credito=Math.round(precio*salida);
               PucService ps=new PucService();
               Cta_PUC cta=ps.getDao().ObtenerCtaPuc(ar.getAuxempaque());
               Asiento a=new Asiento();
               a.setCtaPuc(cta);
               a.setDetalle("Cierre Maq. "+this.m.getId());
               a.setNoFactura(0);
               a.setNoFacturaCompra(0);
               a.setDebito(0);
               a.setEntradas(0);
               a.setCredito(credito);
               a.setSalidas(salida);
               a.setBaseIVA(0);
               a.setBaseRTF(0);        
               suma=suma+a.getCredito();
               System.out.println(a.getCtaPuc().getId()+"    "+a.getDebito()+"   "+a.getCredito()+"   "+a.getEntradas()+"   "+a.getSalidas());
               lista.add(a);
            }
        }
        return lista;
    }
    private Asiento ObtenerAsientoDeTrilla(){
        PucService ps=new PucService();
        Cta_PUC cta=ps.getDao().ObtenerCtaPuc(getDc().getAux_Trilla());
        Asiento a=new Asiento();
        a.setCtaPuc(cta);
        a.setDetalle("Cierre Maq. "+this.m.getId()+" Se Proceso "+m.getTotalpesodestarado());
        a.setNoFactura(0);
        a.setNoFacturaCompra(0);
        a.setDebito(0);
        a.setEntradas(0);
        a.setCredito(this.ObtenerCostoSecamientoTrilla());
        a.setSalidas(m.getTotalpesobruto());
        a.setBaseIVA(0);
        a.setBaseRTF(0); 
        suma=suma+a.getCredito();
        System.out.println(a.getCtaPuc().getId()+"      "+a.getDebito()+"   "+a.getCredito()+"   "+a.getEntradas()+"   "+a.getSalidas());
        return a;
    }
    private Vector<Asiento> ObtenerAsientosDeSubproductos(){
         Vector<Asiento> lista=new Vector<Asiento>();
         Iterator <Articulo> it=as.ObtenerSubproductos().iterator();                 
         while(it.hasNext()){
            Articulo ar=it.next();
            System.out.println(ar.getDescripcion());
            double entradas=is.getDao().ObtenerEntradasPorProducciones(m.getId(),ar.getId())+is.getDao().ObtenerEntradasPorRemisiones(m.getId(),ar.getId());            
            this.masablancaproducida+=(is.getDao().ObtenerEntradasPorProducciones(m.getId(),ar.getId())*ar.getCantidadkg());
            if(entradas>0){
               double saldo=is.getDao().ObtenerSaldoPorProducciones(m.getId(),ar.getId());
               double debito=Math.round(saldo);
               PucService ps=new PucService();
               Cta_PUC cta=ps.getDao().ObtenerCtaPuc(ar.getAuxactivo()+""+ar.getId());
               Asiento a;
               // Produccion
               a=new Asiento();
               a.setCtaPuc(cta);
               a.setDetalle("Cierre Maq. "+this.m.getId());
               a.setNoFactura(0);
               a.setNoFacturaCompra(0);
               a.setDebito(debito);
               a.setEntradas(entradas);
               a.setCredito(0);
               a.setSalidas(0);
               a.setBaseIVA(0);
               a.setBaseRTF(0);        
               //suma=suma-a.getDebito();
               System.out.println(a.getCtaPuc().getId()+"    "+a.getDebito()+"   "+a.getCredito()+"   "+a.getEntradas()+"   "+a.getSalidas());
               lista.add(a);               
            }
         }
         return lista;
    }
    private Vector<Asiento> ObtenerAsientosDeSubproductosFacturados(){
         Vector<Asiento> lista=new Vector<Asiento>();
         Iterator <Articulo> it=as.ObtenerSubproductos().iterator();                 
         Iterator <Articulo> it_=this.lista_precio_subproductos.iterator();
         while(it.hasNext()){
            Articulo ar=it.next();
            Articulo ar_precio=null;
            if(it_.hasNext()){
              ar_precio=it_.next();
            }
            System.out.println(ar.getDescripcion());
            double entradas=is.getDao().ObtenerEntradasPorProducciones(m.getId(),ar.getId())+is.getDao().ObtenerEntradasPorRemisiones(m.getId(),ar.getId());            
            //this.masablancaproducida+=(is.getDao().ObtenerEntradasPorProducciones(m.getId(),ar.getId())*ar.getCantidadkg());
            if(entradas>0){
               double saldo=is.getDao().ObtenerSaldoPorProducciones(m.getId(),ar.getId());
               double debito=Math.round(saldo);
               PucService ps=new PucService();
               Cta_PUC cta=ps.getDao().ObtenerCtaPuc(ar.getAuxactivo()+""+ar.getId());
               Asiento a;               
               if(this.ObtenerAsientosFacturados){
                  //  Factura 
               double salidafact=is.getDao().ObtenerSalidasPorFacturas_(m.getId(),ar.getId());
               //double credito=((debito/entradas)*salidafact);
               double credito=0;
               if(ar_precio!=null && ar_precio.getId().equals(ar.getId())){
                  credito=salidafact*ar_precio.getPreciounitario();
               }else{
                  credito=((debito/entradas)*salidafact); 
               }
               if(salidafact>0){
                   
               a=new Asiento();
               a.setCtaPuc(cta);
               a.setDetalle("Cierre Maq. "+this.m.getId());
               a.setNoFactura(0);
               a.setNoFacturaCompra(0);
               a.setDebito(0);
               a.setEntradas(0);
               a.setCredito(Math.round(credito));
               a.setSalidas(salidafact);
               a.setBaseIVA(0);
               a.setBaseRTF(0);        
               System.out.println(a.getCtaPuc().getId()+"    "+a.getDebito()+"   "+a.getCredito()+"   "+a.getEntradas()+"   "+a.getSalidas());
               lista.add(a);
               // Costo Facturado
               cta=ps.getDao().ObtenerCtaPuc(ar.getAuxcosto()+""+ar.getId());
               a=new Asiento();
               a.setCtaPuc(cta);
               a.setDetalle("Cierre Maq. "+this.m.getId());
               a.setNoFactura(0);
               a.setNoFacturaCompra(0);
               a.setDebito(Math.round(credito));
               a.setEntradas(salidafact);
               a.setCredito(0);
               a.setSalidas(0);
               a.setBaseIVA(0);
               a.setBaseRTF(0);        
               System.out.println(a.getCtaPuc().getId()+"    "+a.getDebito()+"   "+a.getCredito()+"   "+a.getEntradas()+"   "+a.getSalidas());
               lista.add(a);
               }
               }
            }
         }
         return lista;
    }
    private Vector<Asiento> ObtenerAsientosDeArroz(){
         Vector<Asiento> lista=new Vector<Asiento>();
         Iterator <Articulo> it=as.ObtenerArrozBlanco().iterator();                 
         double totalkg=this.ObtenerCantidadKgArrozBlanco();
         preciokg=suma/totalkg;
         int pos=-1;
         while(it.hasNext()){
            Articulo ar=it.next();
            double entradas=is.getDao().ObtenerEntradasPorProducciones(m.getId(),ar.getId())+is.getDao().ObtenerEntradasPorRemisiones(m.getId(),ar.getId());
            this.masablancaproducida+=(is.getDao().ObtenerEntradasPorProducciones(m.getId(),ar.getId())*ar.getCantidadkg());
            System.out.println("Entradas : "+entradas);
            if(entradas>0){
               pos++; 
               double precio=preciokg*ar.getCantidadkg();
               double debito=Math.round(precio*entradas);
               PucService ps=new PucService();
               Cta_PUC cta=ps.getDao().ObtenerCtaPuc(ar.getAuxactivo()+""+ar.getId());
               Asiento a=new Asiento();
               a.setCtaPuc(cta);
               a.setDetalle("Cierre Maq. "+this.m.getId());
               a.setNoFactura(0);
               a.setNoFacturaCompra(0);
               a.setDebito(debito);
               a.setEntradas(entradas);
               a.setCredito(0);
               a.setSalidas(0);
               a.setBaseIVA(0);
               a.setBaseRTF(0);        
               System.out.println(a.getCtaPuc().getId()+"    "+a.getDebito()+"   "+a.getCredito()+"   "+a.getEntradas()+"   "+a.getSalidas());
               lista.add(a);
               suma=suma-a.getDebito();
            }
         }
         if(pos!=-1){
         Asiento a=lista.get(pos);
         double debito=a.getDebito()+suma;
         System.out.println(suma);
         a.setDebito(debito);
         lista.set(pos,a);         
         }
         return lista;
    }    
    public DataConfiguration getDc() {
        return dc;
    }
    private Vector<Asiento> ObtenerAsientosFacturaDeArroz(){
         Vector<Asiento> lista=new Vector<Asiento>();
         Iterator <Articulo> it=as.ObtenerArrozBlanco().iterator();                          
         int pos=-1;         
         while(it.hasNext()){
            Articulo ar=it.next();
            System.out.println(ar.getDescripcion());
            double salidas=is.getDao().ObtenerSalidasPorFacturas_(m.getId(),ar.getId());            
            System.out.println("sal : "+salidas);
            if(salidas>0){               
               double precio=preciokg*ar.getCantidadkg();
               double credito=Math.round(precio*salidas);
               PucService ps=new PucService();
               Cta_PUC cta=ps.getDao().ObtenerCtaPuc(ar.getAuxactivo()+""+ar.getId());
               Asiento a;
               a=new Asiento();
               a.setCtaPuc(cta);
               a.setDetalle("Cierre Maq. "+this.m.getId());
               a.setNoFactura(0);
               a.setNoFacturaCompra(0);
               a.setDebito(0);
               a.setEntradas(0);
               a.setCredito(credito);
               a.setSalidas(salidas);
               a.setBaseIVA(0);
               a.setBaseRTF(0);        
               System.out.println(a.getCtaPuc().getId()+"    "+a.getDebito()+"   "+a.getCredito()+"   "+a.getEntradas()+"   "+a.getSalidas());
               lista.add(a);
               
               cta=ps.getDao().ObtenerCtaPuc(ar.getAuxcosto()+""+ar.getId());
               a=new Asiento();
               a.setCtaPuc(cta);
               a.setDetalle("Cierre Maq. "+this.m.getId());
               a.setNoFactura(0);
               a.setNoFacturaCompra(0);
               a.setDebito(credito);
               a.setEntradas(salidas);
               a.setCredito(0);
               a.setSalidas(0);
               a.setBaseIVA(0);
               a.setBaseRTF(0);        
               System.out.println(a.getCtaPuc().getId()+"    "+a.getDebito()+"   "+a.getCredito()+"   "+a.getEntradas()+"   "+a.getSalidas());
               lista.add(a);
            }
         }         
         return lista;
    }    
    public Vector<ReporteMaquilaCerrada> ObtenerReporteMaquilaCerrada(Object idMaquila){
        Vector<ReporteMaquilaCerrada> lista=new Vector<ReporteMaquilaCerrada>();
        Iterator <Asiento> it=this.ObtenerAsientosDeCostos().iterator();
        MaquilaService ms=new MaquilaService();
        Maquila ma=ms.getMaquilaDao().ObtenerMaquila(idMaquila);
        while(it.hasNext()){
            Asiento a=it.next();
            ReporteMaquilaCerrada rep=new ReporteMaquilaCerrada();
            rep.setDescripcion(a.getCtaPuc().getDenominacion());
            rep.setDebito(a.getDebito());
            rep.setEntradas(a.getEntradas());
            rep.setCredito(a.getCredito());            
            rep.setSalidas(a.getSalidas());
            rep.setIdMaquila(idMaquila.toString());            
            rep.setTotal14_1(ma.getTotalpeso14_1());
            rep.setTotalcosto(ma.getTotalcosto());
            rep.setTotalmasablanca(ma.getTotalmasablanca());
            rep.setTotalhumedo(ma.getTotalpesodestarado());            
            rep.setTotalmasablancaproducida(masablancaproducida);
            lista.add(rep);
        }
        return lista;
    }
    
}
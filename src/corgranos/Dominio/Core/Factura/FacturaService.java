/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Factura;


import corgranos.Dominio.Core.Remision.Remision;
import corgranos.Dominio.Servicios.Costo.CostoService;
import corgranos.Dominio.Servicios.DataConfiguration;
import corgranos.Dominio.Servicios.DataConfigurationService;
import corgranos.Dominio.Servicios.Inventario.InventarioService;
import corgranos.Infraestructura.JDBC.Impl.FacturaDAO;
import corgranos.InterfacesDAO.IFacturaDAO;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;
import sic.Dominio.Core.Documento.Asiento;
import sic.Dominio.Core.Documento.DocumentoService;
import sic.Dominio.Core.PUC.Cta_PUC;
import sic.Dominio.Core.PUC.PucService;
import sic.Dominio.Core.Usuario.Usuario;
import sic.Dominio.Core.Usuario.UsuarioService;
import sic.Dominio.Servicios.Contabilidad.ContabilidadService;
import sic.Dominio.Servicios.Facturacion.FacturacionService;
import sic.Aplicacion.Servicios.FechaService;

public class FacturaService extends DocumentoService{
    private IFacturaDAO facturadao;
    public FacturaService() {
        super();
        this.facturadao=new FacturaDAO();
    }
    public boolean IngresarFactura(Factura f){
        int ultimanumeracion=this.getDao().ObtenerNumeracion(f.getPrefijo().getDocumento_factura());       
        if(ultimanumeracion<f.getPrefijo().getUltima_numeracion()){
            ultimanumeracion=f.getPrefijo().getUltima_numeracion();
        }
        f.setNumeracion(++ultimanumeracion);                
        f.setNofactura(ultimanumeracion);
        f.setAsientos(this.ObtenerAsientos(f));    
        f.setTdocumento(f.getPrefijo().getDocumento_factura());
        f.setAbreviatura(f.getPrefijo().getAbreviatura_factura());
        
        if(this.Guardar(f)){ // esta opcion Guarda la Contabil        
            this.getFacturadao().PersistirFactura(f);            
        Iterator <FacturacionDeArticulo> it=f.getLista().iterator();
        int sw=0;
        while(it.hasNext()){
            FacturacionDeArticulo fa=it.next();
            fa.setFactura(f);
            if( this.getFacturadao().PersistirFacturacionDeArticulo(fa)==null){
                sw=1;
            }
        }
        if(sw==0){
            this.mensaje="Ingresado Con Exito";
            
            return true;
        }else{
            this.mensaje="Hubo un Error , No se Pudo Ingresar";
            return false; 
        }
        }
        return false;
    }    
    public FacturacionDeArticulo Transformar(Remision r){
        FacturacionDeArticulo fa=new FacturacionDeArticulo();
        fa.setArticulo(r.getArticulo());
        fa.setCantidad(r.getSalidas());
        fa.setRemisionada(true);
        fa.setMaquila(r.getMaquila());
        fa.setRemision(r);
        fa.setValoriva(0);
        fa.setPreciounitario(0);
        return fa;
    }
    public IFacturaDAO getFacturadao() {
        return facturadao;
    }
    public Factura ObtenerFactura(int NumeracionFactura,long idPrefijo){
        Factura f=this.getFacturadao().ObtenerFactura(NumeracionFactura,idPrefijo);
        if(f!=null){
           Vector <FacturacionDeArticulo> lista=this.getFacturadao().ObtenerFacturacionesDeArticulo(f.getId());
           f.setLista(lista);
           f.setAsientos(this.getDao().ObtenerAsientos(f.getId()));
        }   
        return f;
    }    
    public Vector <FacturaRep> ObtenerReporte(Factura f){        
        if(f!=null){
            Iterator<FacturacionDeArticulo> it=f.getLista().iterator();
            Vector <FacturaRep> lista=new Vector <FacturaRep>(); 
            while(it.hasNext()){
                FacturacionDeArticulo fa=it.next();
                FacturaRep rep=new FacturaRep();
                rep.setIddocumento(f.getId());
                rep.setActiva(f.isActivo());
                rep.setArticulo(fa.getArticulo().getDescripcion());
                rep.setCantidad(fa.getCantidad());
                rep.setCliente(f.getCliente());
                rep.setCreador(f.getCreador());
                rep.setDian1(f.getDian1());
                rep.setDian2(f.getDian2());
                rep.setFechacontable(f.getFechaContable());
                rep.setFechacreacion(f.getFechaCreacion());
                rep.setFechamodificacion(f.getFechaCreacion());
                rep.setFechaplazo(f.getFechaplazo());
                rep.setIdctaarticulo(fa.getArticulo().getId());
                rep.setIdctausuario(f.getUsuario().getId());
                rep.setIdfacturaciondearticulo(fa.getId());
                if(fa.getMaquila()!=null)
                rep.setIdmaquila(fa.getMaquila().getId());
                if(fa.getRemision()!=null)
                rep.setIdremision(fa.getRemision().getId());
                
                rep.setModificador(f.getAnulador());
                rep.setNodocumento(f.getUsuario().getNoDocumento());
                rep.setNofactura(f.getNofactura());
                rep.setObservaciones(f.getObservaciones());
                rep.setPreciounitario(fa.getPreciounitario());
                rep.setRemisionada(fa.isRemisionada());
                rep.setSubtotal(f.getSubtotal());
                rep.setTipofactura(f.getTipofactura());
                rep.setTotal(f.getTotal());
                rep.setTotaldescuento(f.getTotaldescuento());
                rep.setTotaliva(f.getTotaliva());
                rep.setTotalrtf(f.getTotalrtf());
                rep.setUbicacion(f.getUbicacion());
                rep.setValoriva(fa.getValoriva());
                lista.add(rep);
            }
            return this.Resumido(lista);
        }
        return null;
    }
    private Vector <FacturaRep> Resumido(Vector <FacturaRep> lista){
        Vector <FacturaRep> respuesta=new Vector <FacturaRep>();
        Vector <FacturaRep> recorrido;
        int []v=new int [lista.size()];
        for(int i=0;i<lista.size();i++)
            v[i]=0;
            
        for(int i=0;i<lista.size();i++){
            String idarticulo=lista.get(i).getIdctaarticulo().toString();
            double precio=lista.get(i).getPreciounitario();
            recorrido=new Vector <FacturaRep>();            
            for(int j=i;j<lista.size();j++){
                if(v[j]==0){
                   if(idarticulo.equals(lista.get(j).getIdctaarticulo().toString())){
                      if(precio==lista.get(j).getPreciounitario()){
                        recorrido.add(lista.get(j));
                        v[j]=1;
                      }
                   }
                }
            }
            if(recorrido.size()>0){
               double suma_cantidad=0;
               double suma_iva=0;
               for(int k=0;k<recorrido.size();k++){
                   suma_cantidad+=recorrido.get(k).getCantidad();
                   suma_iva+=recorrido.get(k).getValoriva();
               }
               FacturaRep fr=recorrido.get(0);
               fr.setCantidad(suma_cantidad);
               fr.setPreciounitario(precio);
               fr.setValoriva(suma_iva);
               respuesta.add(fr);
            }
        }
        return respuesta;
    }
    public void ModificarFactura(Factura f){               
        if(this.facturadao.ModificarFactura(f)!=null){
            System.out.println(f.getTipofactura());
        Iterator <FacturacionDeArticulo> it=f.getLista().iterator();
        while(it.hasNext()){
            FacturacionDeArticulo fa=it.next();
            fa.setFactura(f);
            if(fa.getId().equals("0")){
                this.facturadao.PersistirFacturacionDeArticulo(fa);
            }else{                
                this.facturadao.ModificarFacturacionDeArticulo(fa);
                System.out.println(""+fa.getId());
            }
        }   
        System.out.println(""+f.getId());
        this.getDao().EliminarAsientos(f.getId());
        f.setAsientos(this.ObtenerAsientos(f));         
        if(this.Modificar(f))
            this.mensaje="Factura Modificada";
        else
            this.mensaje="No se Modifico Contablemente";
        }else{
            this.mensaje="Factura No se Pudo Modificar";
        }
    }
    public void AnularFactura(int NumeracionFactura,long idprefijo,String anulador,String razonanulacion){
        Factura f=this.ObtenerFactura(NumeracionFactura,idprefijo);
        Calendar cal=Calendar.getInstance();
        f.setModificador(anulador);              
        f.setAnulador(anulador);
        f.setFechaAnulacion(cal.getTime());
        f.setRazonAnulacion(razonanulacion);
        if(this.getDao().AnularDocumento(f.getId(), anulador,razonanulacion)){
            f.setActivo(false);
            this.ModificarFactura(f);
        }              
       
    }    
    public Vector<FacturasVencidasRep> ObtenerFacturasVencidas(){
        Iterator <Factura> it=this.facturadao.ObtenerFacturasCreditosVencidas().iterator();
        FacturacionService sic=new FacturacionService();
        Vector<FacturasVencidasRep> lista=new Vector<FacturasVencidasRep>();
        while(it.hasNext()){
           Factura f=it.next();
           double saldo=sic.getDao().ObtenerSaldoFactura(f.getNofactura());
           if(saldo!=0){
              FacturasVencidasRep rep=new FacturasVencidasRep();
              FechaService fs=new FechaService();
              rep.setFactura(""+f.getNofactura());
              rep.setFecha(f.getFechaContable());
              rep.setNit(f.getUsuario().getNoDocumentoCompleto());
              rep.setCliente(f.getCliente());
              rep.setPlazo(f.getFechaplazo());
              rep.setRetraso(fs.RestarFechasPorDias(fs.getFechaActual(),rep.getPlazo()));
              rep.setTotal(f.getTotal());
              rep.setSaldo(saldo);
              lista.add(rep);
           }
        }
        return lista;
    }
    ////////////////////////////////////
    //       PLATAFORMA CONTABLE      //
    ////////////////////////////////////
    Vector <Asiento> ObtenerAsientos(Factura f){
        Vector<Asiento> lista=new Vector<Asiento>();
        PucService ps=new PucService();
        DataConfigurationService dcs=new DataConfigurationService();                
        DataConfiguration dc=dcs.getDao().ObtenerConfiguracion();
        Cta_PUC cta;
        Asiento a;
        if(Math.round(f.getTotal())!=0){
           
           a=new Asiento();
           a.setId("0");
           if(f.getTipofactura().toLowerCase().equals("credito")) {
              cta=ps.ObtenerCtaPuc(dc.getAux_Clientes()+f.getUsuario().getId());
           }else{
              cta=ps.ObtenerCtaPuc(dc.getAux_Caja()); 
           }
           if(cta==null){
              Cta_PUC aux=ps.ObtenerCtaPuc(dc.getAux_Clientes()); 
              ps.IngresarEnElPUC(dc.getAux_Clientes()+""+f.getUsuario().getId(),f.getUsuario().getDescripcion(),aux.getTipoCta(), true,f.getUsuario(),true,aux.getCategoria());                                
              cta=ps.ObtenerCtaPuc(dc.getAux_Clientes()+""+f.getUsuario().getId());                 
           }
           a.setCtaPuc(cta);
           a.setDetalle(" ");
           a.setDebito(Math.round(f.getTotal()));
           a.setCredito(0);
           a.setBaseIVA(0);
           a.setBaseRTF(0);
           a.setEntradas(0);
           a.setNoFactura(f.getNumeracion());
           a.setNoFacturaCompra(0);
           lista.add(a);            
           
           Iterator <FacturacionDeArticulo> it=f.getLista().iterator();
           while(it.hasNext()){
              FacturacionDeArticulo fa=it.next();
              cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxingreso()+f.getUsuario().getId());
              if(cta==null){
                  Cta_PUC aux=ps.ObtenerCtaPuc(fa.getArticulo().getAuxingreso());
                  ps.IngresarEnElPUC(fa.getArticulo().getAuxingreso()+""+f.getUsuario().getId(),f.getUsuario().getDescripcion(),aux.getTipoCta(), true,f.getUsuario(),true,aux.getCategoria());                                
                  cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxingreso()+f.getUsuario().getId());                 
              }
              a=new Asiento();
              a.setId("0");
              a.setCtaPuc(cta);
              a.setDetalle(" "+fa.getArticulo().getDescripcion()+" Cantidad : "+NumberFormat.getInstance().format(fa.getCantidad()));
              a.setDebito(0);
              a.setCredito(Math.round(fa.getCantidad()*fa.getPreciounitario()));
              a.setBaseIVA(0);
              a.setBaseRTF(0);
              a.setEntradas(0);
              a.setNoFactura(f.getNumeracion());
              a.setNoFacturaCompra(0);
              lista.add(a); 
              if(fa.getValoriva()!=0){
                  cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxiva()+f.getUsuario().getId());
                  if(cta==null){
                     Cta_PUC aux=ps.ObtenerCtaPuc(fa.getArticulo().getAuxiva());
                     System.out.println(aux);
                     ps.IngresarEnElPUC(fa.getArticulo().getAuxiva()+""+f.getUsuario().getId(),f.getUsuario().getDescripcion(),aux.getTipoCta(), true,f.getUsuario(),true,aux.getCategoria());                                
                     cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxiva()+""+f.getUsuario().getId());                 
                  }
                  a=new Asiento();
                  a.setId("0");
                  a.setCtaPuc(cta);
                  a.setDetalle(" ");
                  a.setDebito(0);
                  a.setCredito(Math.round(fa.getValoriva()));
                  a.setBaseIVA(Math.round(fa.getCantidad()*fa.getPreciounitario()));
                  a.setBaseRTF(0);
                  a.setEntradas(0);
                  a.setNoFactura(f.getNumeracion());
                  a.setNoFacturaCompra(0);
                  lista.add(a); 
              }
              
              if(fa.getArticulo().getTipo().equals("producto")){
                  if(fa.getArticulo().getCategoria().equals("subproductos")==true){
                      if(fa.getMaquila().isCerrada()==true){
                      InventarioService is=new InventarioService();
                      double saldo=is.getDao().ObtenerSaldoPorProducciones(fa.getMaquila().getId(),fa.getArticulo().getId());
                      double entradas=is.getDao().ObtenerEntradasPorProducciones(fa.getMaquila().getId(),fa.getArticulo().getId());
                      double precio=saldo/entradas;
                             cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxactivo()+""+fa.getArticulo().getId());
                             if(cta==null){
                                 Cta_PUC aux=ps.ObtenerCtaPuc(fa.getArticulo().getAuxactivo());                     
                                 ps.IngresarEnElPUC(fa.getArticulo().getAuxactivo()+""+fa.getArticulo().getId(),fa.getArticulo().getDescripcion(),aux.getTipoCta(), true,fa.getArticulo(),true,aux.getCategoria());                                
                                 cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxactivo()+""+fa.getArticulo().getId());                 
                             }
                             a=new Asiento();
                             a.setId("0");
                             a.setCtaPuc(cta);
                             a.setDetalle("Maq"+fa.getMaquila().getId()+"_");
                             a.setDebito(0);
                             a.setCredito(Math.round(precio*fa.getCantidad()));
                             a.setBaseIVA(0);
                             a.setBaseRTF(0);
                             a.setEntradas(0);
                             a.setSalidas(fa.getCantidad());
                             a.setNoFactura(f.getNumeracion());
                             a.setNoFacturaCompra(0);
                             lista.add(a); 
                             
                             cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxcosto()+""+fa.getArticulo().getId());     
                             if(cta==null){
                                   Cta_PUC aux=ps.ObtenerCtaPuc(fa.getArticulo().getAuxcosto());                     
                                   ps.IngresarEnElPUC(fa.getArticulo().getAuxcosto()+""+fa.getArticulo().getId(),fa.getArticulo().getDescripcion(),aux.getTipoCta(), true,fa.getArticulo(),true,aux.getCategoria());                                
                                   cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxcosto()+""+fa.getArticulo().getId());                 
                             }                                  
                             a=new Asiento();
                             a.setId("0");
                             a.setCtaPuc(cta);
                             a.setDetalle("Maq"+fa.getMaquila().getId()+"_");
                             a.setDebito(Math.round(precio*fa.getCantidad()));
                             a.setCredito(0);
                             a.setBaseIVA(0);
                             a.setBaseRTF(0);
                             a.setEntradas(fa.getCantidad());
                             a.setSalidas(0);
                             a.setNoFactura(f.getNumeracion());
                             a.setNoFacturaCompra(0);
                             lista.add(a);                  
                      }
                  }
                  if(fa.getArticulo().getCategoria().equals("producto procesado")==true){
                      if(fa.getMaquila().isCerrada()==true){
                             CostoService cs=new CostoService(fa.getMaquila().getId(),f.getFechaContable());
                             cs.ObtenerAsientosDeCostos();
                             double preciokg=cs.preciokg;
                             double precio=preciokg*fa.getArticulo().getCantidadkg();                             
                             cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxactivo()+""+fa.getArticulo().getId());
                             if(cta==null){
                                 Cta_PUC aux=ps.ObtenerCtaPuc(fa.getArticulo().getAuxactivo());                     
                                 ps.IngresarEnElPUC(fa.getArticulo().getAuxactivo()+""+fa.getArticulo().getId(),fa.getArticulo().getDescripcion(),aux.getTipoCta(), true,fa.getArticulo(),true,aux.getCategoria());                                
                                 cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxactivo()+""+fa.getArticulo().getId());                 
                             }
                             a=new Asiento();
                             a.setId("0");
                             a.setCtaPuc(cta);
                             a.setDetalle("Maq"+fa.getMaquila().getId()+"_");
                             a.setDebito(0);
                             a.setCredito(Math.round(precio*fa.getCantidad()));
                             a.setBaseIVA(0);
                             a.setBaseRTF(0);
                             a.setEntradas(0);
                             a.setSalidas(fa.getCantidad());
                             a.setNoFactura(f.getNumeracion());
                             a.setNoFacturaCompra(0);
                             lista.add(a); 
                             
                             cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxcosto()+""+fa.getArticulo().getId());
                             if(cta==null){
                                   Cta_PUC aux=ps.ObtenerCtaPuc(fa.getArticulo().getAuxcosto());                     
                                   ps.IngresarEnElPUC(fa.getArticulo().getAuxcosto()+""+fa.getArticulo().getId(),fa.getArticulo().getDescripcion(),aux.getTipoCta(), true,fa.getArticulo(),true,aux.getCategoria());                                
                                   cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxcosto()+""+fa.getArticulo().getId());                 
                             }                                  
                             a=new Asiento();
                             a.setId("0");
                             a.setCtaPuc(cta);
                             a.setDetalle("Maq"+fa.getMaquila().getId()+"_");
                             a.setDebito(Math.round(precio*fa.getCantidad()));
                             a.setCredito(0);
                             a.setBaseIVA(0);
                             a.setBaseRTF(0);
                             a.setEntradas(fa.getCantidad());
                             a.setSalidas(0);
                             a.setNoFactura(f.getNumeracion());
                             a.setNoFacturaCompra(0);
                             lista.add(a); 
                             
                      }                      
                      
                  }
                 // Si es Inzumo u Otro
                 if(fa.getArticulo().getCategoria().equals("producto procesado")==false && fa.getArticulo().getCategoria().equals("subproductos")==false){                     
                     ContabilidadService cs=new ContabilidadService();
                     System.out.println(fa.getArticulo().getAuxactivo()+""+fa.getArticulo().getId());
                     double saldo=cs.getDao().ObtenerSaldo(fa.getArticulo().getAuxactivo()+""+fa.getArticulo().getId(),f.getFechaContable(),true,null);                    
                     double existencia=cs.getDao().ObtenerExistencia(fa.getArticulo().getAuxactivo()+""+fa.getArticulo().getId(),f.getFechaContable(),true,null);                      
                     double preciounitario=saldo/existencia;                     
                     System.out.println(saldo);
                     System.out.println(existencia);
                     System.out.println(preciounitario);
                     cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxactivo()+""+fa.getArticulo().getId());
                     if(cta==null){
                        Cta_PUC aux=ps.ObtenerCtaPuc(fa.getArticulo().getAuxactivo());
                        System.out.println(aux);
                        ps.IngresarEnElPUC(fa.getArticulo().getAuxactivo()+""+fa.getArticulo().getId(),fa.getArticulo().getDescripcion(),aux.getTipoCta(), true,fa.getArticulo(),true,aux.getCategoria());                                
                        cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxactivo()+""+fa.getArticulo().getId());                 
                     }
                     a=new Asiento();
                     a.setId("0");
                     a.setCtaPuc(cta);
                     a.setDetalle("Por Venta ");
                     a.setDebito(0);
                     a.setCredito(Math.round(fa.getCantidad()*preciounitario));
                     a.setBaseIVA(0);
                     a.setBaseRTF(0);
                     a.setEntradas(0);
                     a.setSalidas(fa.getCantidad());
                     a.setNoFactura(f.getNumeracion());
                     a.setNoFacturaCompra(0);
                     lista.add(a); 
                     
                     cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxcosto()+""+fa.getArticulo().getId());
                     if(cta==null){
                        Cta_PUC aux=ps.ObtenerCtaPuc(fa.getArticulo().getAuxcosto());
                        System.out.println(aux);                                                                        
                        ps.IngresarEnElPUC(fa.getArticulo().getAuxcosto()+""+fa.getArticulo().getId(),fa.getArticulo().getDescripcion(),aux.getTipoCta(), true,fa.getArticulo(),true,aux.getCategoria());                                
                        cta=ps.ObtenerCtaPuc(fa.getArticulo().getAuxcosto()+""+fa.getArticulo().getId());                 
                     }
                     a=new Asiento();
                     a.setId("0");
                     a.setCtaPuc(cta);
                     a.setDetalle("Por Venta ");
                     a.setDebito(Math.round(fa.getCantidad()*preciounitario));
                     a.setCredito(0);
                     a.setBaseIVA(0);
                     a.setBaseRTF(0);
                     a.setEntradas(fa.getCantidad());
                     a.setSalidas(0);
                     a.setNoFactura(f.getNumeracion());
                     a.setNoFacturaCompra(0);
                     lista.add(a); 
                     
                 }
              }
           }
        }        
        return lista;
    } 
}
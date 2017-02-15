/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Maquila;

import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddyPorMaquilaDTO;
import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddyService;
import corgranos.Dominio.Core.Produccion.ProduccionService;
import corgranos.Dominio.Servicios.Costo.CostoService;
import corgranos.Dominio.Servicios.Inventario.InventarioService;
import corgranos.Infraestructura.JDBC.Impl.MaquilaDAO;
import corgranos.InterfacesDAO.IMaquilaDAO;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import sic.Dominio.Core.Documento.Asiento;
import sic.Dominio.Core.Usuario.UsuarioService;
import sic.Dominio.Core.Documento.Documento;
import sic.Dominio.Core.Documento.DocumentoService;


/**
 *
 * @author FANNY BURGOS
 */
public class MaquilaService extends DocumentoService{
    private IMaquilaDAO maquiladao;    
    public MaquilaService() {
        maquiladao=new MaquilaDAO();
    }
    public IMaquilaDAO getMaquilaDao() {
        return maquiladao;
    }
    public boolean IngresarMaquila(Object id,double inicialpeso14_1, double inicialpesodestarado, double inicialcosto, double inicialmasablanca,double valortrilla){
        if(maquiladao.ObtenerMaquila(id)==null){
           double totalpeso14_1=inicialpeso14_1;
           double totalpesodestarado=inicialpesodestarado;
           double totalcosto=inicialcosto;
           double totalmasablanca=inicialmasablanca;
           Maquila m=new Maquila(totalpeso14_1, totalpesodestarado, totalcosto, totalmasablanca, inicialpeso14_1, inicialpesodestarado, inicialcosto, inicialmasablanca,false,true,valortrilla,"");
           m.setId(id);
           Maquila r=maquiladao.PersistirMaquila(m);
           if(r!=null){
               this.mensaje="Maquila Ingresada Con Exito";
              return true;
           }
           this.mensaje="Hubo un Error";
        }else{
            this.mensaje="Esa Maquila Ya Existe";
        }
        return false;
    }
    public Vector<Maquila> ObtenerMaquilas(boolean cerradas,boolean VerTodasLasMaquilas,String busqueda){
        if(VerTodasLasMaquilas==false){
           if(cerradas){
              return maquiladao.ObtenerMaquilasCerradas(busqueda);
           }else{
              return maquiladao.ObtenerMaquilasAbiertas(busqueda);
           }
        }else{
            return maquiladao.ObtenerMaquilas(busqueda);
        }
    }
    public String getMensaje() {
        return this.mensaje;
    }
    public boolean AgregarLiquidacionEnMaquila(LiquidacionDePaddyPorMaquilaDTO lp,Maquila m){
        LiquidacionDePaddyService lps=new LiquidacionDePaddyService();
        if(lps.getPaddydao().AgregarLiquidacionEnMaquila(lp.getIdDocumento(),m.getId())){
            double pesobruto=m.getTotalpesobruto()+lp.getPesobruto();
            double pesoliquidado=m.getTotalpesoliquidado()+lp.getPesoliquidado();
            double peso14_1=m.getTotalpeso14_1()+lp.getPeso14_1();
            double pesodestarado=m.getTotalpesodestarado()+lp.getPesodestarado();
            double masablanca=m.getTotalmasablanca()+lp.getMasablanca();
            double totalcosto=m.getTotalcosto()+lp.getValor();            
            m.setTotalpeso14_1(peso14_1);
            m.setTotalpesodestarado(pesodestarado);
            m.setTotalmasablanca(masablanca);
            m.setTotalcosto(totalcosto);
            m.setTotalpesobruto(pesobruto);
            m.setTotalpesoliquidado(pesoliquidado);
            if(this.getMaquilaDao().ModificarMaquila(m)!=null){
                this.mensaje="Liquidacion Agregada a Maquila ";
                return true;
            }else{
                this.mensaje="Error  ";
            }
            this.mensaje="Error No Se Pudo Agregar ";
        }
        return false;
    }
    public boolean EliminarLiquidacionEnMaquila(LiquidacionDePaddyPorMaquilaDTO lp,Maquila m){
        LiquidacionDePaddyService lps=new LiquidacionDePaddyService();
        if(lps.getPaddydao().EliminarLiquidacionEnMaquila(lp.getIdDocumento(),m.getId())){
            double pesobruto=m.getTotalpesobruto()-lp.getPesobruto();
            double pesoliquidado=m.getTotalpesoliquidado()-lp.getPesoliquidado();
            double peso14_1=m.getTotalpeso14_1()-lp.getPeso14_1();
            double pesodestarado=m.getTotalpesodestarado()-lp.getPesodestarado();
            double masablanca=m.getTotalmasablanca()-lp.getMasablanca();
            double totalcosto=m.getTotalcosto()-lp.getValor();            
            m.setTotalpeso14_1(peso14_1);
            m.setTotalpesodestarado(pesodestarado);
            m.setTotalmasablanca(masablanca);
            m.setTotalcosto(totalcosto);
            m.setTotalpesobruto(pesobruto);
            m.setTotalpesoliquidado(pesoliquidado);
            if(this.getMaquilaDao().ModificarMaquila(m)!=null){
                this.mensaje="Liquidacion Eliminada en Maquila ";
                return true;
            }else{
                this.mensaje="Error  ";
            }
            this.mensaje="Error No Se Pudo Eliminar ";
        }
        return false;
    }
    public Vector<LiquidacionDePaddyPorMaquilaDTO> ObtenerLiquidaciones(Object idMaquila){
        LiquidacionDePaddyService lps=new LiquidacionDePaddyService();
        return lps.getPaddydao().ObtenerLiquidacionesDePaddy(idMaquila);
    }
    public boolean CerrarMaquila(Object idMaquila,Date Fecha,String actor){        
        CostoService cs=new CostoService(idMaquila,Fecha);        
        CierreDeMaquila cm=new CierreDeMaquila();
        int n=this.getDao().ObtenerNumeracion(cm.getTdocumento())+1;
        cm.setNumeracion(n);        
        cm.setCreador(actor);                
        cm.setUsuario(new UsuarioService().getDao().ObtenerUsuario(cs.getDc().getIdUsuarioProduccion()));
        cm.setFechaContable(Fecha);
        cm.setAsientos(cs.ObtenerAsientosDeCierreDeMaquila());
        cm.setNorma_local(true);
        cm.setNorma_internacional(true);
        if(this.Guardar(cm)){
           long iddoc=this.getDao().ObtenerIdUltimoDocumento(); 
           Maquila m=this.maquiladao.ObtenerMaquila(idMaquila);
           m.setCerrada(true); 
           m.setIdDocumentoDeCierre(""+iddoc);
           if(this.maquiladao.ModificarMaquila(m)!=null){
              this.mensaje="Se Hizo El Cierre Satisfactoriamente";
              return true;
           }
              this.mensaje="ERROR Generó Documento Contable pero no esta Cerrada Administrativamente \n Repita esta Operacion ";
              return false;
        }
         this.mensaje="ERROR No se Pudo Cerrar la Maquila ";
           return false;
    }
    public boolean ReabrirMaquila(Object idMaquila,String Actor){
        Maquila m=this.getMaquilaDao().ObtenerMaquila(idMaquila);
        m.setCerrada(false);
        DocumentoService ds=new DocumentoService();                
        if(ds.getDao().AnularDocumento(m.getIdDocumentoDeCierre(),Actor,"Se Reabrio Esta Maquila")){
            if(this.getMaquilaDao().ModificarAsientosDeFacturas(idMaquila)){                
                if(this.getMaquilaDao().ModificarMaquila(m)!=null){
                   this.mensaje="SE REABRIO LA MAQUILA "+idMaquila+" Con Exito"; 
                   return true;
                }
            }else{                                
                this.mensaje="Error No SE Pudo Reabrir La Maquila ,Favor Intentelo Nuevamente";
                return false;                
            }        
        }
        this.mensaje="Error No SE Pudo Reabrir La Maquila ,Favor Intentelo Nuevamente";
        return false;   
    }
    public Vector<ReporteMaquilaCerrada> ObtenerReporteMaquilaCerrada(Object idMaquila){       
       Maquila m=this.maquiladao.ObtenerMaquila(idMaquila);
       DocumentoService ds=new DocumentoService();
       Documento d=ds.getDao().ObtenerDocumento(m.getIdDocumentoDeCierre());
       if(d!=null){
          CostoService cs=new CostoService(idMaquila,d.getFechaContable());        
          return cs.ObtenerReporteMaquilaCerrada(idMaquila);
       }else{
          this.mensaje="No Se Encontro El Documento De Cierre En Contabilidad"; 
          return null; 
       }
    } 
    public void ActualizarMaquila(Object idMaquila){       
        Maquila m=this.getMaquilaDao().ObtenerMaquila(idMaquila);
        double sumacosto=m.getInicialcosto();
        double sumamasablanca=m.getInicialmasablanca();
        double sumapeso14_1=m.getInicialpeso14_1();
        double sumadestarado=m.getInicialpesodestarado();
        double sumapesobruto=m.getInicialpesobruto();
        double sumapesoliquidado=m.getInicialpesoliquidado();
        Iterator<LiquidacionDePaddyPorMaquilaDTO>it=this.ObtenerLiquidaciones(idMaquila).iterator();
        while(it.hasNext()){
             LiquidacionDePaddyPorMaquilaDTO lp=it.next();             
             sumacosto+=lp.getValor();
             sumamasablanca+=lp.getMasablanca();
             sumapeso14_1+=lp.getPeso14_1();
             sumadestarado+=lp.getPesodestarado();
             sumapesobruto+=lp.getPesobruto();
             sumapesoliquidado+=lp.getPesoliquidado();
             
        }
        m.setTotalcosto(sumacosto);
        m.setTotalmasablanca(sumamasablanca);
        m.setTotalpeso14_1(sumapeso14_1);
        m.setTotalpesodestarado(sumadestarado);
        m.setTotalpesobruto(sumapesobruto);
        m.setTotalpesoliquidado(sumapesoliquidado);
        System.out.println(sumapesobruto);
        this.getMaquilaDao().ModificarMaquila(m);                
    }
    public void ActualizarMaquilas(){        
        Iterator<Maquila> it=this.maquiladao.ObtenerMaquilas("").iterator();
        while(it.hasNext()){            
            Maquila m=it.next();           
            this.ActualizarMaquila(m.getId());
        }        
    }
    public void ReabrirYCerrarMaquilasCerradas(){
        Vector<Maquila> lista =this.maquiladao.ObtenerMaquilasCerradas("");
        for(int i=0;i<lista.size();i++){
            Maquila m=lista.get(i);            
            if(m.getId().toString().trim().equals("1")){
               //System.out.println("Esta Es La Maquila : "+m.getId()+" NO REABRIRA");
            }else{
                this.ReabrirMaquila(m.getId(),"ciro");            
               System.out.println("Reabriendo La Maquila : "+m.getId()); 
            }
        }
        for(int i=0;i<lista.size();i++){
            Maquila m=lista.get(i);                        
            Documento d=new DocumentoService().getDao().ObtenerDocumento(m.getIdDocumentoDeCierre());
            if(m.getId().toString().trim().equals("1")){
               //System.out.println("Esta Es La Maquila : "+m.getId()+" NO CERRARA ");
            }else{
               this.CerrarMaquila(m.getId(),d.getFechaContable(),"ciro");
               System.out.println("Cerrando La Maquila : "+m.getId()); 
            }
            
        }
    }
    public Vector<EstadoDeResultadosDeMaquilasCerradas> ObtenerEstadoDeResultadosDeMaquilasCerradas(){
        MaquilaService maquila_service=new MaquilaService();
        DocumentoService documento_service=new DocumentoService();
        Iterator <Maquila> it=maquila_service.getMaquilaDao().ObtenerMaquilasCerradas("").iterator();
        Vector<EstadoDeResultadosDeMaquilasCerradas> lista=new Vector<EstadoDeResultadosDeMaquilasCerradas>();
        double suma_ingresos=0;
        double suma_costo=0;
        double suma_utilidad=0;
        while(it.hasNext()){
            Maquila m=it.next();
            EstadoDeResultadosDeMaquilasCerradas e=new EstadoDeResultadosDeMaquilasCerradas();
            e.setId_maquila(m.getId().toString());
            e.setProducido(new InventarioService().getDao().ObtenerEntradasPorProducciones(m.getId(),"87"));
            Documento d=documento_service.ObtenerDocumento(m.getIdDocumentoDeCierre());
            Asiento a=d.getAsientos().get(d.getAsientos().size()-1);
            double costo=(a.getDebito()+a.getCredito())/(a.getEntradas()+a.getSalidas());
            e.setCosto_bulto(costo);
            e.setVendido(new InventarioService().getDao().ObtenerSalidasPorFacturas_(m.getId(),"87"));
            e.setIngreso_ventas(maquila_service.getMaquilaDao().ObtenerIngresosPorVenta(m.getId(),"87"));            
            System.out.println(e.getId_maquila()+"   "+e.getProducido()+"   "+e.getCosto_bulto()+"   "+NumberFormat.getInstance().format(e.getCosto())+"   "+NumberFormat.getInstance().format(e.getIngreso_ventas())+"   "+e.getVendido()+"   "+NumberFormat.getInstance().format(e.getUtilidad()));
            suma_ingresos+=e.getIngreso_ventas();
            suma_costo+=e.getCosto();
            suma_utilidad+=e.getUtilidad();
            lista.add(e);
        }
        System.out.println("Ingresos:"+NumberFormat.getInstance().format(suma_ingresos));
        System.out.println("Costo:"+NumberFormat.getInstance().format(suma_costo));
        System.out.println("Utilidad:"+NumberFormat.getInstance().format(suma_utilidad));
        return lista;
    }
    
    public void VerFaltantes(){
        Iterator<Maquila> it=this.ObtenerMaquilas(true, true,"").iterator();// dame la lista de todas las maquilas
        ProduccionService ps=new ProduccionService(); //
        double faltantes=0;
        double sobrantes=0;
        double c=0;
        double suma=0;
        double suma_teorica=0;
        double suma_real=0;
        while(it.hasNext()){
            Maquila m=it.next();            
            long id=Long.parseLong(m.getId().toString());            
            if(id>=799 && id<=816 && id!=810){ //750 y 800
               double masa_blanca_real=ps.ObtenerMasaBlancaDeMaquilaPorProducciones(id); 
               double masa_blanca_teorica=this.ObtenerMasaBlanca(m.getId());
               double peso_liquidado=this.ObtenerPesoLiquidado(m.getId());               
               double por=(masa_blanca_real*100)/peso_liquidado;
               if(por>0 && por<100){
                  suma+=por;
                  c=c+1.0;
               }           
               suma_teorica+=masa_blanca_teorica;
               suma_real+=masa_blanca_real;
               if(masa_blanca_teorica>masa_blanca_real){
                 System.out.println(id+" "+por+" "+masa_blanca_real+" "+masa_blanca_teorica+" "+(masa_blanca_real-masa_blanca_teorica));
                 faltantes+=(masa_blanca_teorica-masa_blanca_real);
               }else{
                 sobrantes+=(masa_blanca_real-masa_blanca_teorica);
                 System.out.println(id+" "+por+" "+masa_blanca_real+" "+masa_blanca_teorica+" "+(masa_blanca_real-masa_blanca_teorica));
               }
               
            }
        }
        
        System.out.println(faltantes+" "+sobrantes+" rendimiento="+(suma/c));
        System.out.println(suma_real+" "+suma_teorica+" "+(suma_real-suma_teorica));
    }
    
    public double ObtenerMasaBlanca(Object idMaquila){
        Iterator<LiquidacionDePaddyPorMaquilaDTO> it=new LiquidacionDePaddyService().getPaddydao().ObtenerLiquidacionesDePaddy(idMaquila).iterator();
        double masa_blanca=0;
        while(it.hasNext()){
            LiquidacionDePaddyPorMaquilaDTO obj=it.next();
            masa_blanca+=obj.getMasablanca();//obj.getPesoliquidado()*0.68;
        }
        return masa_blanca;
    }
    public double ObtenerPesoLiquidado(Object idMaquila){
        Iterator<LiquidacionDePaddyPorMaquilaDTO> it=new LiquidacionDePaddyService().getPaddydao().ObtenerLiquidacionesDePaddy(idMaquila).iterator();
        double suma=0;
        while(it.hasNext()){
            LiquidacionDePaddyPorMaquilaDTO obj=it.next();
            suma+=obj.getPesoliquidado();
        }
        return suma;
    }
    // verifica Maquilas descuadradas con
    // el documento contable
    public void Verificar(){
        Iterator<Maquila> it=this.maquiladao.ObtenerMaquilas("").iterator();
        DocumentoService ds=new DocumentoService();
        while(it.hasNext()){
            Maquila m=it.next();
            boolean sw=true;
            if(m.isCerrada() && m.isActiva()){
                Documento d=ds.ObtenerDocumento(m.getIdDocumentoDeCierre());
                if(d.getAbreviatura().equals("CM")==false){
                    System.out.println(m.getId());
                }
                Iterator<Asiento> itr=d.getAsientos().iterator();
                while(itr.hasNext()){
                    Asiento a=itr.next();
                    if(a.getDetalle().contains("Cierre Maq. "+m.getId())){
                        sw=true;
                    }else{
                        sw=false;
                        break;
                    }
                }
                if(sw==false){
                    System.out.println("Error maq. "+m.getId());
                }
            }
        }
    }
}

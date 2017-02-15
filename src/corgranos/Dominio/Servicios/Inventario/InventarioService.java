/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Servicios.Inventario;

import corgranos.Dominio.Core.Articulo.Articulo;
import corgranos.Dominio.Core.Articulo.ArticuloService;
import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddyService;
import corgranos.Dominio.Core.Maquila.Maquila;
import corgranos.Dominio.Core.Maquila.MaquilaService;
import corgranos.Dominio.Core.Produccion.ProduccionService;
import corgranos.Infraestructura.JDBC.Impl.InventarioDAO;
import corgranos.InterfacesDAO.I_InventarioDAO;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import sic.Aplicacion.Servicios.FechaService;
import sic.Dominio.Core.Usuario.Usuario;
import sic.Dominio.Core.Usuario.UsuarioService;

/**
 *
 * @author FANNY BURGOS
 */
public class InventarioService {
    private I_InventarioDAO dao;
    public InventarioService() {
        dao=new InventarioDAO();
    }
    public Vector <Kardex> ObtenerKardex(Object idArticulo, Date fechainicio, Date fechafin){         
         UsuarioService us=new UsuarioService();
         ArticuloService as=new ArticuloService();
         //MaquilaService ms= new MaquilaService();
         Iterator<MovimientoInventario> it=getDao().ObtenerMovimientos(idArticulo, fechainicio, fechafin).iterator();
         Vector<Kardex> l=new  Vector<Kardex>();
         long dia=3600*24*1000;
         double existenciaanterior=this.ObtenerExistencia(idArticulo,new Date(fechainicio.getTime()-dia));
         double existencia=existenciaanterior;
         while(it.hasNext()){
             MovimientoInventario m=it.next();
             Kardex k=new Kardex();
             k.setFechafin(fechafin);
             k.setFechainicio(fechainicio);
             k.setExistencia_anterior(existenciaanterior);
             String articulo=as.getArticulo_dao().ObtenerArticulo(idArticulo).getDescripcion();
             k.setIdarticulo(idArticulo);             
             k.setDescripcion(articulo);
             Usuario u=us.getDao().ObtenerUsuario(m.getIdusuario());
             k.setFecha(m.getFecha());
             if(u!=null){
                k.setNit(u.getNoDocumentoCompleto());
                k.setUsuario(u.NombreCompleto());
             }else{
                k.setNit("");
                k.setUsuario(""); 
             }
             if(m.getIdmaquila()!=null){
                k.setMaquila(m.getIdmaquila().toString());
             }else{
                k.setMaquila(""); 
             }
             k.setSoporte(m.getSoporte());
             k.setDetalle(m.getDetalle());
             if(m.getEntradas()>=0){
                k.setEntradas(m.getEntradas());
                k.setSalidas(0);
             }else{
                k.setEntradas(0);
                k.setSalidas(Math.abs(m.getEntradas()));                 
             }
             if(m.getSalidas()>=0){
                k.setSalidas(k.getSalidas()+m.getSalidas());                
             }else{
                k.setEntradas(k.getEntradas()+Math.abs(m.getSalidas()));                     
             }
             existencia+=m.getEntradas()-m.getSalidas();
             k.setExistencia(existencia);
             l.add(k);
         }         
         return l;
    }
    public double ObtenerExistencia(Object idArticulo, Date fechacorte){
        double producciones=getDao().ObtenerEntradasPorProducciones(idArticulo, fechacorte);
        System.out.println("Producciones="+producciones);
        double facturas=getDao().ObtenerSalidasPorFacturasNoRemisionadas(idArticulo, fechacorte);
        System.out.println("Facturas="+facturas);
        double existencia_remisiones=getDao().ObtenerExisteciaPorRemisiones(idArticulo, fechacorte);       
        System.out.println("Remisiones="+existencia_remisiones);
        System.out.println("Inventario="+((producciones-facturas)+existencia_remisiones));
        return ((producciones-facturas)+existencia_remisiones);
    }
    public Vector<InventarioResumido> ObtenerResumenDeInventario(Date fechacorte){
        ArticuloService as=new ArticuloService();
        Iterator<Articulo> it=as.ObtenerProductosYSubproductos().iterator();
        Vector<InventarioResumido> lista=new Vector<InventarioResumido>();
        while(it.hasNext()){
            Articulo a=it.next();
            InventarioResumido ir=new InventarioResumido();
            ir.setFechacorte(fechacorte);
            ir.setIdarticulo(a.getId());
            ir.setDescripcion(a.getDescripcion());
            ir.setExistencia(this.ObtenerExistencia(a.getId(),fechacorte));
            lista.add(ir);
        }
        return lista;
    }
    public double ObtenerExistencia(Object idArticulo,Object idMaquila){
        double producciones=getDao().ObtenerEntradasPorProducciones(idMaquila,idArticulo);        
        double facturas=getDao().ObtenerSalidasPorFacturasNoRemisionadas(idMaquila,idArticulo);
        double existencia_remisiones=getDao().ObtenerExisteciaPorRemisiones(idMaquila,idArticulo);       
        System.out.println("Maq"+idMaquila+""+producciones+" "+facturas+" "+existencia_remisiones+" "+((producciones-facturas)+existencia_remisiones));
        return ((producciones-facturas)+existencia_remisiones);
    }
    public Vector<InventarioDeArticuloPorMaquila> ObtenerReporteArticuloPorMaquilas(Object idArticulo){
        Articulo a=new ArticuloService().getArticulo_dao().ObtenerArticulo(idArticulo);
        MaquilaService ms=new MaquilaService();
        Iterator<Maquila> it=ms.ObtenerMaquilas(false,true,"").iterator();        
        Vector <InventarioDeArticuloPorMaquila> lista=new Vector <InventarioDeArticuloPorMaquila>();
        while(it.hasNext()){
            Maquila m=it.next();
            InventarioDeArticuloPorMaquila rep=new InventarioDeArticuloPorMaquila();
            rep.setId(a.getId().toString());
            rep.setDescripcion(a.getDescripcion());
            rep.setMaquila(""+m.getId());
            rep.setExistencia(ObtenerExistencia(idArticulo,m.getId()));
            if(rep.getExistencia()!=0){
               rep.setFacturas(this.dao.ObtenerSalidasPorFacturasNoRemisionadas(m.getId(), idArticulo));                 
               rep.setProducciones(this.dao.ObtenerEntradasPorProducciones(m.getId(), idArticulo));
               rep.setRemisiones(this.dao.ObtenerExisteciaPorRemisiones(m.getId(), idArticulo));
               lista.add(rep);
            }
        }
        return lista;
    }
    public Vector<InventarioResumidoPorMaquila>  ObtenerResumidoPorMaquila(Object idMaquila){
        Vector<InventarioResumidoPorMaquila> lista=new Vector<InventarioResumidoPorMaquila>();
        ArticuloService as=new ArticuloService();
        Iterator<Articulo> it=as.ObtenerProductosYSubproductos().iterator();
        while(it.hasNext()){
           Articulo a=it.next();  
           InventarioResumidoPorMaquila rep=new InventarioResumidoPorMaquila();
           rep.setMaquila(idMaquila.toString());
           rep.setIdarticulo(a.getId().toString());
           rep.setDescripcion(a.getDescripcion());
           rep.setExistencia(this.ObtenerExistencia(a.getId(),idMaquila));                 
           rep.setProducciones(this.dao.ObtenerEntradasPorProducciones(idMaquila,a.getId()));
           rep.setFacturas(this.dao.ObtenerSalidasPorFacturasNoRemisionadas(idMaquila,a.getId()));
           rep.setRemisiones(this.dao.ObtenerExisteciaPorRemisiones(idMaquila,a.getId()));
           lista.add(rep);
      
        }
        return lista;      
    } 
    public I_InventarioDAO getDao() {
        return dao;
    }
    public Vector <KardexPorMaquila> ObtenerKardex(Object idArticulo,Object idMaquila){         
         UsuarioService us=new UsuarioService();
         ArticuloService as=new ArticuloService();         
         Iterator<MovimientoInventario> it=getDao().ObtenerMovimientos(idArticulo,idMaquila).iterator();
         Vector<KardexPorMaquila> l=new  Vector<KardexPorMaquila>();        
         double existencia=0;
         while(it.hasNext()){
             MovimientoInventario m=it.next();
             KardexPorMaquila k=new KardexPorMaquila();            
             String articulo=as.getArticulo_dao().ObtenerArticulo(idArticulo).getDescripcion();
             k.setIdarticulo(idArticulo);             
             k.setDescripcion(articulo);
             Usuario u=us.getDao().ObtenerUsuario(m.getIdusuario());
             k.setFecha(m.getFecha());
             if(u!=null){
                k.setNit(u.getNoDocumentoCompleto());
                k.setUsuario(u.NombreCompleto());
             }else{
                k.setNit("");
                k.setUsuario(""); 
             }             
             k.setMaquila(m.getIdmaquila().toString());             
             k.setSoporte(m.getSoporte());
             k.setDetalle(m.getDetalle());
             k.setEntradas(m.getEntradas());
             k.setSalidas(m.getSalidas());
             existencia+=m.getEntradas()-m.getSalidas();
             k.setExistencia(existencia);
             l.add(k);
         }         
         return l;
    }
    public double InventarioMasaBlanca(Date fecha_inicial,Date fecha_final){
        return this.ObtenerTotalMasaBlancaDeLiquidaciones(fecha_inicial,fecha_final)-this.ObtenerTotalMasaBlancaDeProducciones(fecha_inicial,fecha_final);
    } 
    public double ObtenerTotalMasaBlancaDeLiquidaciones(Date fecha_inicial,Date fecha_final){
        LiquidacionDePaddyService service=new LiquidacionDePaddyService();        
        double val=service.ObtenerMasaBlancaTotalLiquidaciones(fecha_inicial,fecha_final);
        System.out.println(NumberFormat.getInstance().format(val));
        return val;
    }
    public double ObtenerTotalMasaBlancaDeProducciones(Date fecha_inicial,Date fecha_final){
        ProduccionService service=new ProduccionService();
        double val=service.ObtenerMasaBlancaDeProducciones(fecha_inicial,fecha_final);
        System.out.println(NumberFormat.getInstance().format(val));
        return val;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Produccion;

import corgranos.Dominio.Core.Articulo.Articulo;
import corgranos.Dominio.Core.Articulo.ArticuloService;
import corgranos.Dominio.Core.Maquila.MaquilaService;
import corgranos.Dominio.Servicios.Inventario.InventarioService;
import corgranos.Infraestructura.JDBC.Impl.ProduccionDAO;
import corgranos.InterfacesDAO.IProduccionDAO;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author FANNY BURGOS
 */
public class ProduccionService {
    private IProduccionDAO dao;
    private String mensaje="";
    public ProduccionService() {
        dao=new ProduccionDAO();
    }
    public IProduccionDAO getDao() {
        return dao;
    }
    public boolean IngresarProduccion(Produccion p){
        long ultimo=dao.ObtenerIdUltimaProduccion();
        long id=1+ultimo;
        p.setId(id);
        if(dao.PersistirProduccion(p)!=null){
            Iterator <ProduccionDeArticulo> it=p.getLista().iterator();
            int sw=0;
            while(it.hasNext()){
                ProduccionDeArticulo pa=it.next();
                pa.setProduccion(p);                
                if(dao.PersistirProduccionDeArticulo(pa)!=null){
                    ArticuloService as=new ArticuloService();
                    String aux_empaque=pa.getArticulo().getAuxempaque();                    
                    if(aux_empaque!=null){
                       String idArticuloEmpaque=aux_empaque.substring(8,aux_empaque.length());
                       Articulo a=as.getArticulo_dao().ObtenerArticulo(idArticuloEmpaque);
                       ProduccionDeArticulo pempaque=new ProduccionDeArticulo();
                       pempaque.setCantidad(pa.getCantidad()*-1);
                       pempaque.setProduccion(p);
                       pempaque.setArticulo(a);
                       pempaque.setPreciounitario(0);
                       dao.PersistirProduccionDeArticulo(pempaque);
                    }
                }else{
                    sw=1;                    
                }
            }            
            if(sw==0){
               this.mensaje="Produccion Ingresada Con Exito"; 
               return true;               
            }else{
               this.mensaje="Hubo un Error , No se Pudo Ingresar la Produccion"; 
               return false; 
            }
        }else{
            this.mensaje="Hubo un Error , No se Pudo Ingresar la Produccion";
            return false;    
        }
        
    }
    public Produccion ObtenerProduccion(Object idProduccion){
        Produccion p=this.dao.ObtenerProduccion(idProduccion);
        if(p!=null)
           p.setLista(this.dao.ObtenerProduccionesDeArticulos(idProduccion));
        return p;
    }
    public Produccion ModificarProduccion(Produccion p){
        this.getDao().ModificarProduccion(p);        
        Iterator<ProduccionDeArticulo> it=p.getLista().iterator();
        Vector <ProduccionDeArticulo> listac=new Vector <ProduccionDeArticulo>(); 
        while(it.hasNext()){
            ProduccionDeArticulo pa=it.next();
            ProduccionDeArticulo pac;
            pa.setProduccion(p);
            if(pa.getId()!=null){                
                pac=this.getDao().ModificarProduccionDeArticulo(pa);
            }else{
                pac=this.getDao().PersistirProduccionDeArticulo(pa);
            }            
            listac.add(pac);
        }        
        p.setLista(listac);
        return p;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void AnularProduccion(Produccion p,String anulador){
        p.setAnulador(anulador);
        p.setActivo(false);
        Calendar cal=Calendar.getInstance();
        p.setFechaanulacion(cal.getTime());
        this.ModificarProduccion(p);
    }
    public Vector <ProduccionDTO> ObtenerReporte(Produccion p){        
        Vector <ProduccionDTO> lista=new Vector <ProduccionDTO>();
        Iterator <ProduccionDeArticulo> it=p.getLista().iterator();
        while(it.hasNext()){
            ProduccionDeArticulo pa=it.next();
            ProduccionDTO pd=new ProduccionDTO();
            pd.setId(p.getId());
            pd.setAnulador(p.getAnulador());
            pd.setCreador(p.getCreador());
            pd.setFechacontable(p.getFechacontable());
            pd.setFechacreacion(p.getFechacreacion());
            pd.setFechaanulacion(p.getFechaanulacion());
            pd.setIdmaquila(p.getMaquila().getId());            
            pd.setIdarticulo(pa.getArticulo().getId());
            pd.setArticulo(pa.getArticulo().getDescripcion());
            pd.setCantidad(pa.getCantidad());
            pd.setPreciounitario(pa.getPreciounitario());            
            pd.setActivo(p.isActivo());
            lista.add(pd);
        }
        return lista;
    }    
    public double ObtenerMasaBlancaDeProducciones(Date fecha_inicial,Date fecha_final){           
        return this.dao.ObtenerMasaBlancaProducciones(fecha_inicial,fecha_final);
    }        
    public double ObtenerMasaBlancaDeMaquilaPorProducciones(long maquila){
        InventarioService service=new InventarioService();
        double arrozx45=service.getDao().ObtenerEntradasPorProducciones(maquila,"87")*45;
        double arrozx50=service.getDao().ObtenerEntradasPorProducciones(maquila,"1709")*50;
        double perlax50=service.getDao().ObtenerEntradasPorProducciones(maquila,"89")*50;
        double granzax50=service.getDao().ObtenerEntradasPorProducciones(maquila,"112")*50;
        double granzax40=service.getDao().ObtenerEntradasPorProducciones(maquila,"349")*40;
        double harinax40=service.getDao().ObtenerEntradasPorProducciones(maquila,"110")*40;        
        double harinax30=service.getDao().ObtenerEntradasPorProducciones(maquila,"1132")*30;        
        double masa_blanca=arrozx45+arrozx50+perlax50+granzax50+granzax40+harinax40+harinax30;
        return masa_blanca;        
    }
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Articulo;

import corgranos.Infraestructura.JDBC.Impl.ArticuloDAO;
import corgranos.InterfacesDAO.IArticuloDAO;
import java.util.Iterator;
import java.util.Vector;
import sic.Dominio.Core.Cta_T.CtaT_Service;
import sic.Dominio.Core.Cta_T.Cta_T;
import sic.Dominio.Core.PUC.Cta_PUC;
import sic.Dominio.Core.PUC.PucService;


/**
 *
 * @author FANNY BURGOS
 */
public class ArticuloService extends CtaT_Service{
    private IArticuloDAO articulo_dao;    
    public ArticuloService() {
        this.articulo_dao=new ArticuloDAO();
    }
    public boolean IngresarArticulo(String nombre, String referencia, int cantidadkg, String auxingreso, String auxactivo, String auxcosto, String tipo, String categoria, double preciounitario, double porcentageiva,long codigodebarra,String auxiva,String auxempaque) {              
        if(this.getArticulo_dao().ObtenerArticulo(codigodebarra)==null){
           Articulo a=new Articulo(nombre, referencia, cantidadkg, auxingreso, auxactivo, auxcosto, tipo, categoria, preciounitario, porcentageiva,codigodebarra,auxiva,auxempaque);
           if(this.InsertarCtaT(a.getDescripcion())==true){
              Cta_T t=this.ObtenerCtaT(a.getDescripcion());
              a.setId(t.getId());
              this.getArticulo_dao().PersistirArticulo(a);
              PucService ps=new PucService();          
             if(auxactivo.length()==8){
                Cta_PUC cta=ps.getDao().ObtenerCtaPuc(auxactivo);
                ps.IngresarEnElPUC(auxactivo+t.getId(),t.getDescripcion(),cta.getTipoCta(),cta.isRequiereNit(),t,cta.isPublico(),cta.getCategoria());
             }          
             this.mensaje="Ingresado Con Exito";
             return true;
           }            
           this.mensaje="Hubo un Error , Operacion Cancelada";
           return false;
        }else{
           this.mensaje="Ese Articulo ya Existe";
           return false; 
        }
   }
    public Vector <Articulo> ObtenerProductosYSubproductos(){
       Vector<Articulo> lista=new Vector<Articulo>(); 
       Iterator<Articulo> it=this.articulo_dao.ObtenerArtticulosPorCategoria("producto procesado").iterator();
       while(it.hasNext()){
           Articulo a=it.next();
           lista.add(a);
       }
       it=this.articulo_dao.ObtenerArtticulosPorCategoria("subproductos").iterator();
       while(it.hasNext()){
           Articulo a=it.next();
           lista.add(a);
       }
       return lista;
   }
    public boolean ModificarArticulo(Object idArticulo,String nombre, String referencia, int cantidadkg, String auxingreso, String auxactivo, String auxcosto, String tipo, String categoria, double preciounitario, double porcentageiva,long codigodebarra,String auxiva,String auxempaque) {                          
            Articulo a=new Articulo(nombre, referencia, cantidadkg, auxingreso, auxactivo, auxcosto, tipo, categoria, preciounitario, porcentageiva,codigodebarra,auxiva,auxempaque);
            if(this.ModificarCtaT(idArticulo, a)==true){             
                if(this.getArticulo_dao().ModificarArticulo(idArticulo,a)!=null){
                    this.mensaje="Modificado Con Exito";
                    return true;
                }else{
                    this.mensaje="Modificado Con Errores,favor Repetir Modificacion";
                    return false;
                }
            }            
            this.mensaje="No Pudo Modificar, Hubo un Error";
            return false;
    }
    public Vector <Articulo> ObtenerArrozBlanco(){
       Vector<Articulo> lista=new Vector<Articulo>(); 
       Iterator<Articulo> it=this.articulo_dao.ObtenerArtticulosPorCategoria("producto procesado").iterator();
       while(it.hasNext()){
           Articulo a=it.next();
           lista.add(a);
       }       
       return lista;
   }
    public Vector <Articulo> ObtenerSubproductos(){
       Vector<Articulo> lista=new Vector<Articulo>(); 
       Iterator<Articulo> it;
       it=this.articulo_dao.ObtenerArtticulosPorCategoria("subproductos").iterator();
       while(it.hasNext()){
           Articulo a=it.next();
           lista.add(a);
       }
       return lista;
   } 
    public IArticuloDAO getArticulo_dao() {
        return articulo_dao;
    }    
}

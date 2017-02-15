/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.InterfacesDAO;

import corgranos.Dominio.Core.Articulo.Articulo;
import java.util.Vector;

/**
 *
 * @author FANNY BURGOS
 */
public interface IArticuloDAO {
   Articulo PersistirArticulo(Articulo articulo);    
   Vector ObtenerCategorias();
   Vector <Articulo> ObtenerArticulos(String busqueda);
   Articulo ObtenerArticulo(long CodigoDeBarra);
   Articulo ObtenerArticulo(Object idArticulo);
   Vector <Articulo> ObtenerArtticulosPorCategoria(String categoria);
   Articulo ModificarArticulo(Object id,Articulo articulo);    
}

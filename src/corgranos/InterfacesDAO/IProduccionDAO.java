/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.InterfacesDAO;

import corgranos.Dominio.Core.Produccion.Produccion;
import corgranos.Dominio.Core.Produccion.ProduccionDeArticulo;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author FANNY BURGOS
 */
public interface IProduccionDAO {
    Produccion PersistirProduccion(Produccion p);   
    ProduccionDeArticulo PersistirProduccionDeArticulo(ProduccionDeArticulo pa);
    long ObtenerIdUltimaProduccion();  
    Produccion ObtenerProduccion(Object id);
    public double ObtenerMasaBlancaProducciones(Date fecha_inicial,Date fecha_final);
    Vector <ProduccionDeArticulo> ObtenerProduccionesDeArticulos(Object idProduccion);
    Produccion ModificarProduccion(Produccion p);
    ProduccionDeArticulo ModificarProduccionDeArticulo(ProduccionDeArticulo pa);    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.InterfacesDAO;

import corgranos.Dominio.Core.Maquila.Maquila;
import java.util.Vector;

/**
 *
 * @author FANNY BURGOS
 */
public interface IMaquilaDAO {
    Maquila PersistirMaquila(Maquila m);   
    Maquila ObtenerMaquila(Object id);
    Maquila ObtenerMaquilaDeLiquidacion(Object idDocumentoLiquidacion);
    Maquila ObtenerUltimaMaquila();
    Vector <Maquila> ObtenerMaquilasAbiertas(String busqueda);
    Vector <Maquila> ObtenerMaquilasCerradas(String busqueda);    
    Vector <Maquila> ObtenerMaquilas(String busqueda);    
    Maquila ModificarMaquila(Maquila m);
    boolean ModificarAsientosDeFacturas(Object idMaquila);
    double ObtenerIngresosPorVenta(Object idMaquila,Object idArticulo);
}

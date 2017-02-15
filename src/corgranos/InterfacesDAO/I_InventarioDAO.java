/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.InterfacesDAO;

import corgranos.Dominio.Servicios.Inventario.MovimientoInventario;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author FANNY BURGOS
 */
public interface I_InventarioDAO {
 Vector <MovimientoInventario> ObtenerMovimientos(Object idArticulo,Date fechainicio,Date fechafin);
 Vector <MovimientoInventario> ObtenerMovimientos(Object idArticulo,Object idMaquila);
 double ObtenerExisteciaPorRemisiones(Object idArticulo,Date fechacorte);
 double ObtenerSalidasPorFacturasNoRemisionadas(Object idArticulo,Date fechacorte);
 double ObtenerEntradasPorProducciones(Object idArticulo,Date fechacorte); 
 // Inventario Por Maquila
 double ObtenerEntradasPorProducciones(Object idMaquila,Object idArticulo);
 double ObtenerSalidasPorFacturasNoRemisionadas(Object idMaquila,Object idArticulo);
 double ObtenerSalidasPorFacturas_(Object idMaquila,Object idArticulo);
 double ObtenerExisteciaPorRemisiones(Object idMaquila,Object idArticulo);
 double ObtenerEntradasPorRemisiones(Object idMaquila,Object idArticulo);
 // Obtiene Saldo de Subrpoductos
 double ObtenerSaldoPorProducciones(Object idMaquila,Object idArticulo);
 double ObtenerSaldoPorProducciones(Object idArticulo,Date fechacorte); 
 
}

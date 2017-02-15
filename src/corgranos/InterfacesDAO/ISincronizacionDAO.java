/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.InterfacesDAO;

import corgranos.Servicios.Mantenimiento.FADocumento;
import corgranos.Servicios.Mantenimiento.LPDocumento;
import java.util.Vector;

/**
 *
 * @author FANNY BURGOS
 */
public interface ISincronizacionDAO {
   // Este LPDocumento se Obtiene 
   // de las Liquidaciones de paddy
   Vector <LPDocumento> ObtenerTodos();
   LPDocumento Modificar(LPDocumento lp);
   // Este FADocumento se Obtiene 
   // de las Facturas
   Vector <FADocumento> ObtenerTodas();
   FADocumento Modificar(FADocumento fa);
}

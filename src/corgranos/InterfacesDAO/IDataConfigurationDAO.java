/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.InterfacesDAO;

import corgranos.Dominio.Servicios.DataConfiguration;

/**
 *
 * @author FANNY BURGOS
 */
public interface IDataConfigurationDAO {
   DataConfiguration PersistirConfiguracion(DataConfiguration dc);     
   DataConfiguration ObtenerConfiguracion();       
   DataConfiguration ModificarConfiguracion(DataConfiguration dc);    
}

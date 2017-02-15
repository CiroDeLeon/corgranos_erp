/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Servicios;

import corgranos.Infraestructura.JDBC.Impl.DataConfigurationDAO;
import corgranos.InterfacesDAO.IDataConfigurationDAO;

/**
 *
 * @author FANNY BURGOS
 */
public class DataConfigurationService {
    private IDataConfigurationDAO dao;
    private String mensaje;

    public DataConfigurationService() {
        dao=new DataConfigurationDAO();
    }

    /**
     * @return the dao
     */
    public IDataConfigurationDAO getDao() {
        return dao;
    }
    public boolean AsignarDataConfiguration(String Aux_Clientes, String Aux_Iva, String Aux_Proveedor, String Aux_Paddy, String Aux_Retefuente, String Aux_Caja, String Aux_BolsaPorPagar, String Aux_GastoBolsa, double PorcentageBolsa, String Aux_FomentoPorPagar, String Aux_GastoFomento, double PorcentageFomento, Object idUsuarioProduccion,double PorcentageRetefuente,String dian1,String dian2,String Aux_Trilla,String aux_descargues){
        if(dao.ObtenerConfiguracion()!=null){
            if(dao.ModificarConfiguracion(new corgranos.Dominio.Servicios.DataConfiguration(Aux_Clientes, Aux_Iva, Aux_Proveedor, Aux_Paddy, Aux_Retefuente, Aux_Caja, Aux_BolsaPorPagar, Aux_GastoBolsa, PorcentageBolsa, Aux_FomentoPorPagar, Aux_GastoFomento, PorcentageFomento, idUsuarioProduccion,PorcentageRetefuente,dian1,dian2,Aux_Trilla,aux_descargues))!=null){
                mensaje="Configuracion Realizada Exitosamente";
                return true;
            }          
        }else{            
            if(dao.PersistirConfiguracion(new corgranos.Dominio.Servicios.DataConfiguration(Aux_Clientes, Aux_Iva, Aux_Proveedor, Aux_Paddy, Aux_Retefuente, Aux_Caja, Aux_BolsaPorPagar, Aux_GastoBolsa, PorcentageBolsa, Aux_FomentoPorPagar, Aux_GastoFomento, PorcentageFomento, idUsuarioProduccion,PorcentageRetefuente,dian1,dian2,Aux_Trilla,aux_descargues))!=null){                
                mensaje="Configuracion Realizada Exitosamente";
                return true;
            }
        }       
        mensaje="Error No se Pudo Configurar";
        return false;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }
}

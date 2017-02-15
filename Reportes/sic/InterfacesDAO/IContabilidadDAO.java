/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.InterfacesDAO;

import java.util.Date;
import java.util.Vector;
import sic.Dominio.Servicios.Contabilidad.AsientoContableDTO;
import sic.Dominio.Servicios.Contabilidad.EstadoFinancieroDTO;
import sic.Dominio.Servicios.Contabilidad.AsientoPorFechaDTO;
import sic.Dominio.Servicios.Contabilidad.ResumenAuxDTO;

/**
 *
 * @author FANNY BURGOS
 */
public interface IContabilidadDAO {
    // Obtiene Asientos de Un Auxiliar
    Vector <AsientoContableDTO> ObtenerAsientos(String aux,Date fechai,Date fechaf);   
    Vector <ResumenAuxDTO> ObtenerResumen(String aux,Date fechainicio,Date fechacorte);       
    double ObtenerSaldo(String aux,Date fecha);
    double ObtenerSaldo(String aux,Date fechainicio,Date fechafin);
    double ObtenerExistencia(String aux,Date fecha);
    boolean isDebito(String cta);
    double ObtenerDebito(String aux,Date fechainicio,Date fechafin); 
    double ObtenerCredito(String aux,Date fechainicio,Date fechafin); 
    Vector <AsientoPorFechaDTO> ObtenerMovimientos(Date fechainicio,Date fechafin);    
    boolean EstaCuadradoElSistema();
}

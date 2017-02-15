/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.InterfacesDAO;

import sic.Servicios.Reportes.DatosDeReporte;

/**
 *
 * @author FANNY BURGOS
 */
public interface IDatosDeReporteDAO {
DatosDeReporte PersistirDatosDeReporte(DatosDeReporte dr);   
DatosDeReporte ModificarDatosDeReporte(DatosDeReporte dr);   
DatosDeReporte ObtenerDatosDeReporte();
}

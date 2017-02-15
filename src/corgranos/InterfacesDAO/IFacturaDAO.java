/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.InterfacesDAO;

import corgranos.Dominio.Core.Factura.Factura;
import corgranos.Dominio.Core.Factura.FacturacionDeArticulo;
import corgranos.Dominio.Core.Factura.Prefijo;
import corgranos.Dominio.Core.Factura.dto.InformeFacturaDTO;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author FANNY BURGOS
 */
public interface IFacturaDAO {
   Factura PersistirFactura(Factura f);    
   FacturacionDeArticulo PersistirFacturacionDeArticulo(FacturacionDeArticulo fa);  
   Factura ObtenerFactura(int NumeracionFactura,long idprefijo);
   Vector <FacturacionDeArticulo> ObtenerFacturacionesDeArticulo(Object idDocumento);
   Factura ModificarFactura(Factura f);
   FacturacionDeArticulo ModificarFacturacionDeArticulo(FacturacionDeArticulo fa);   
   Vector<Factura> ObtenerFacturasCreditosVencidas();
   Vector<Prefijo> ObtenerPrefijos();
   Prefijo ObtenerPrefijo(long id);
   Vector<InformeFacturaDTO> ObtenerInforme(Prefijo prefijo,Date fecha_inicio,Date fecha_fin);
}
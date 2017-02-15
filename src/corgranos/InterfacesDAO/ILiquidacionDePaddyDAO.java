/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.InterfacesDAO;

import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddy;
import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddyPorMaquilaDTO;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author FANNY BURGOS
 */
public interface ILiquidacionDePaddyDAO {
   LiquidacionDePaddy PersistirLiquidacion(LiquidacionDePaddy lp);
   LiquidacionDePaddy ObtenerLiquidacion(int NoTiquete);    
   LiquidacionDePaddy ObtenerLiquidacion(Object idNumeracionLiquidacion);    
   Vector<LiquidacionDePaddyPorMaquilaDTO> ObtenerLiquidacionesDePaddyLibres(String busqueda);
   Vector<LiquidacionDePaddyPorMaquilaDTO> ObtenerLiquidacionesDePaddy(Object idMaquila);   
   Vector<LiquidacionDePaddy> ObtenerLiquidacionesDePaddy(Date fechainicial,Date fechafinal);   
   Vector<LiquidacionDePaddy> ObtenerLiquidacionesDePaddy(Object idUsuario,Date fechainicial,Date fechafinal,String Aux_Proveedor);   
   boolean AgregarLiquidacionEnMaquila(Object idDocumentoLP,Object idMaquila); 
   boolean EliminarLiquidacionEnMaquila(Object idDocumentoLP,Object idMaquila);    
   LiquidacionDePaddy ObtenerUltimaLiquidacion(); 
   LiquidacionDePaddy ModificarLiquidacion(LiquidacionDePaddy lp);         
   double ObtenerTotalMasaBlanca(Date fecha_inicial,Date fecha_final);
}

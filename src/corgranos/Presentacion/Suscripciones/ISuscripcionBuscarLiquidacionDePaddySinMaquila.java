/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.Suscripciones;

import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddyPorMaquilaDTO;
import sic.Presentacion.Suscripciones.ISuscripcion;

/**
 *
 * @author FANNY BURGOS
 */
public interface ISuscripcionBuscarLiquidacionDePaddySinMaquila extends ISuscripcion{
public void EventoDeSeleccionDeLiquidacionDePaddySinMaquila();
public void setLiquidacionDePaddySinMaquila(LiquidacionDePaddyPorMaquilaDTO lp);    
}

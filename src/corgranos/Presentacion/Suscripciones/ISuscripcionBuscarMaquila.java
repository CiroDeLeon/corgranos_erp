/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.Suscripciones;

import corgranos.Dominio.Core.Maquila.Maquila;
import sic.Presentacion.Suscripciones.ISuscripcion;

/**
 *
 * @author FANNY BURGOS
 */
public interface ISuscripcionBuscarMaquila extends ISuscripcion{
   public void EventoDeSeleccionMaquila();
   public void setMaquila(Maquila m);
}

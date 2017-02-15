/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.Suscripciones;

import corgranos.Dominio.Core.Remision.Remision;
import sic.Presentacion.Suscripciones.ISuscripcion;

/**
 *
 * @author FANNY BURGOS
 */
public interface ISuscripcionBuscarRemision extends ISuscripcion{
   public void EventoDeSeleccionRemesion();
   public void setRemision(Remision r);    
}

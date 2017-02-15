/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.Suscripciones;

import corgranos.Dominio.Core.Articulo.Articulo;
import sic.Presentacion.Suscripciones.ISuscripcion;

/**
 *
 * @author FANNY BURGOS
 */
public interface ISuscripcionBuscarArticulo extends ISuscripcion{
public void EventoDeSeleccionArticulo();
   public void setArticulo(Articulo articulo);    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Produccion;

import corgranos.Dominio.Core.Articulo.Articulo;
import sic.Dominio.DomainObject;


/**
 *
 * @author FANNY BURGOS
 */
public class ProduccionDeArticulo extends DomainObject{
   private Produccion produccion;
   private Articulo articulo;
   private double cantidad;
   private double preciounitario;

    /**
     * @return the produccion
     */
    public Produccion getProduccion() {
        return produccion;
    }

    /**
     * @param produccion the produccion to set
     */
    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    /**
     * @return the articulo
     */
    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    /**
     * @return the cantidad
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the preciounitario
     */
    public double getPreciounitario() {
        return preciounitario;
    }

    /**
     * @param preciounitario the preciounitario to set
     */
    public void setPreciounitario(double preciounitario) {
        this.preciounitario = preciounitario;
    }
}

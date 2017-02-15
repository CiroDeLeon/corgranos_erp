/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Factura;

import corgranos.Dominio.Core.Articulo.Articulo;
import sic.Dominio.DomainObject;
import corgranos.Dominio.Core.Maquila.Maquila;
import corgranos.Dominio.Core.Remision.Remision;

/**
 *
 * @author FANNY BURGOS
 */
public class FacturacionDeArticulo extends DomainObject{
    private Factura factura;
    private Articulo articulo;
    private Remision remision;
    private Maquila maquila;    
    private double cantidad;
    private double preciounitario;
    private double valoriva;
    private boolean remisionada;

    /**
     * @return the factura
     */
    public Factura getFactura() {
        return factura;
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(Factura factura) {
        this.factura = factura;
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

    /**
     * @return the valoriva
     */
    public double getValoriva() {
        return valoriva;
    }

    /**
     * @param valoriva the valoriva to set
     */
    public void setValoriva(double valoriva) {
        this.valoriva = valoriva;
    }

    /**
     * @return the maquila
     */
    public Maquila getMaquila() {
        return maquila;
    }

    /**
     * @param maquila the maquila to set
     */
    public void setMaquila(Maquila maquila) {
        this.maquila = maquila;
    }

    /**
     * @return the remisionada
     */
    public boolean isRemisionada() {
        return remisionada;
    }

    /**
     * @param remisionada the remisionada to set
     */
    public void setRemisionada(boolean remisionada) {
        this.remisionada = remisionada;
    }

    /**
     * @return the remision
     */
    public Remision getRemision() {
        return remision;
    }

    /**
     * @param remision the remision to set
     */
    public void setRemision(Remision remision) {
        this.remision = remision;
    }
}

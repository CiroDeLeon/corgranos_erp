/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Servicios.Inventario;

import sic.Aplicacion.Servicios.Reportes.ReporteContable;

/**
 *
 * @author FANNY BURGOS
 */
public class InventarioDeArticuloPorMaquila extends ReporteContable{
   private String id;
   private String descripcion;
   private String maquila;
   private double producciones;
   private double facturas;
   private double remisiones;
   private double existencia;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the maquila
     */
    public String getMaquila() {
        return maquila;
    }

    /**
     * @param maquila the maquila to set
     */
    public void setMaquila(String maquila) {
        this.maquila = maquila;
    }

    /**
     * @return the existencia
     */
    public double getExistencia() {
        return existencia;
    }

    /**
     * @param existencia the existencia to set
     */
    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }

    /**
     * @return the producciones
     */
    public double getProducciones() {
        return producciones;
    }

    /**
     * @param producciones the producciones to set
     */
    public void setProducciones(double producciones) {
        this.producciones = producciones;
    }

    /**
     * @return the facturas
     */
    public double getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(double facturas) {
        this.facturas = facturas;
    }

    /**
     * @return the remisiones
     */
    public double getRemisiones() {
        return remisiones;
    }

    /**
     * @param remisiones the remisiones to set
     */
    public void setRemisiones(double remisiones) {
        this.remisiones = remisiones;
    }
}

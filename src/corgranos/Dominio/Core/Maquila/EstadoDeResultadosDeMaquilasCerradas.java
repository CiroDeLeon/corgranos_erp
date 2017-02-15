/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package corgranos.Dominio.Core.Maquila;

import java.text.NumberFormat;

/**
 *
 * @author Usuario1
 */
public class EstadoDeResultadosDeMaquilasCerradas {
   private String   id_maquila;
   private double producido;
   private double costo_bulto;
   private double vendido;
   private double ingreso_ventas;   

    /**
     * @return the id_maquila
     */
    public String getId_maquila() {
        return id_maquila;
    }

    /**
     * @param id_maquila the id_maquila to set
     */
    public void setId_maquila(String id_maquila) {
        this.id_maquila = id_maquila;
    }

    /**
     * @return the producido
     */
    public double getProducido() {
        return producido;
    }

    /**
     * @param producido the producido to set
     */
    public void setProducido(double producido) {
        this.producido = producido;
    }

    /**
     * @return the costo_bulto
     */
    public double getCosto_bulto() {
        return costo_bulto;
    }

    /**
     * @param costo_bulto the costo_bulto to set
     */
    public void setCosto_bulto(double costo_bulto) {
        this.costo_bulto = costo_bulto;
    }

    /**
     * @return the vendido
     */
    public double getVendido() {
        return vendido;
    }

    /**
     * @param vendido the vendido to set
     */
    public void setVendido(double vendido) {
        this.vendido = vendido;
    }

    
    public double getCosto(){
       return this.costo_bulto*this.producido;    
    }    

    /**
     * @return the ingreso_ventas
     */
    public double getIngreso_ventas() {
        return ingreso_ventas;
    }

    /**
     * @param ingreso_ventas the ingreso_ventas to set
     */
    public void setIngreso_ventas(double ingreso_ventas) {
        this.ingreso_ventas = ingreso_ventas;
    }
    public double getUtilidad(){
        return this.ingreso_ventas-this.getCosto();
    }
}

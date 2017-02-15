/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Servicios.Inventario;

import java.util.Date;

/**
 *
 * @author FANNY BURGOS
 */
public class MovimientoInventario {
   private Date fecha;
   private String soporte;
   private Object idusuario;
   private Object idmaquila;
   private String detalle;
   private double entradas;
   private double salidas;

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the soporte
     */
    public String getSoporte() {
        return soporte;
    }

    /**
     * @param soporte the soporte to set
     */
    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    /**
     * @return the idusuario
     */
    public Object getIdusuario() {
        return idusuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdusuario(Object idusuario) {
        this.idusuario = idusuario;
    }

    /**
     * @return the idmaquila
     */
    public Object getIdmaquila() {
        return idmaquila;
    }

    /**
     * @param idmaquila the idmaquila to set
     */
    public void setIdmaquila(Object idmaquila) {
        this.idmaquila = idmaquila;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the entradas
     */
    public double getEntradas() {
        return entradas;
    }

    /**
     * @param entradas the entradas to set
     */
    public void setEntradas(double entradas) {
        this.entradas = entradas;
    }

    /**
     * @return the salidas
     */
    public double getSalidas() {
        return salidas;
    }

    /**
     * @param salidas the salidas to set
     */
    public void setSalidas(double salidas) {
        this.salidas = salidas;
    }
}

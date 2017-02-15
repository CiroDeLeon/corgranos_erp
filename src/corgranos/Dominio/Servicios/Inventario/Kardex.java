/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Servicios.Inventario;

import java.util.Date;
import sic.Aplicacion.Servicios.Reportes.ReporteContable;


/**
 *
 * @author FANNY BURGOS
 */
public class Kardex extends ReporteContable{
    private Object idarticulo;
    private String descripcion;
    private Date   fechainicio;
    private Date   fechafin;
    private double existencia_anterior;    
    private Date fecha;
    private String Nit;
    private String usuario;
    private String soporte;
    private String maquila;
    private String detalle;
    private double entradas;
    private double salidas;
    private double existencia;

    /**
     * @return the idarticulo
     */
    public Object getIdarticulo() {
        return idarticulo;
    }

    /**
     * @param idarticulo the idarticulo to set
     */
    public void setIdarticulo(Object idarticulo) {
        this.idarticulo = idarticulo;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    /**
     * @return the fechainicio
     */
    public Date getFechainicio() {
        return fechainicio;
    }

    /**
     * @param fechainicio the fechainicio to set
     */
    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    /**
     * @return the fechafin
     */
    public Date getFechafin() {
        return fechafin;
    }

    /**
     * @param fechafin the fechafin to set
     */
    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    /**
     * @return the existencia_anterior
     */
    public double getExistencia_anterior() {
        return existencia_anterior;
    }

    /**
     * @param existencia_anterior the existencia_anterior to set
     */
    public void setExistencia_anterior(double existencia_anterior) {
        this.existencia_anterior = existencia_anterior;
    }

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
     * @return the Nit
     */
    public String getNit() {
        return Nit;
    }

    /**
     * @param Nit the Nit to set
     */
    public void setNit(String Nit) {
        this.Nit = Nit;
    }

    /**
     * @return the Usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param Usuario the Usuario to set
     */
    public void setUsuario(String Usuario) {
        this.usuario = Usuario;
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

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Produccion;

import java.util.Date;
import sic.Aplicacion.Servicios.Reportes.ReporteContable;

/**
 *
 * @author FANNY BURGOS
 */
public class ProduccionDTO extends ReporteContable{
    private Object id;
    private Object idmaquila="";
    private Date fechacontable;
    private Date fechaanulacion;
    private Date fechacreacion;
    private String creador;
    private String anulador;
    private boolean activo;
    private Object idarticulo;
    private String articulo;
    private double cantidad;
    private double preciounitario;

    /**
     * @return the id
     */
    public Object getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Object id) {
        this.id = id;
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
     * @return the fechacontable
     */
    public Date getFechacontable() {
        return fechacontable;
    }

    /**
     * @param fechacontable the fechacontable to set
     */
    public void setFechacontable(Date fechacontable) {
        this.fechacontable = fechacontable;
    }

    /**
     * @return the fechaanulacion
     */
    public Date getFechaanulacion() {
        return fechaanulacion;
    }

    /**
     * @param fechaanulacion the fechaanulacion to set
     */
    public void setFechaanulacion(Date fechaanulacion) {
        this.fechaanulacion = fechaanulacion;
    }

    /**
     * @return the fechacreacion
     */
    public Date getFechacreacion() {
        return fechacreacion;
    }

    /**
     * @param fechacreacion the fechacreacion to set
     */
    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    /**
     * @return the creador
     */
    public String getCreador() {
        return creador;
    }

    /**
     * @param creador the creador to set
     */
    public void setCreador(String creador) {
        this.creador = creador;
    }

    /**
     * @return the anulador
     */
    public String getAnulador() {
        return anulador;
    }

    /**
     * @param anulador the anulador to set
     */
    public void setAnulador(String anulador) {
        this.anulador = anulador;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the idArticulo
     */
    public Object getIdarticulo() {
        return idarticulo;
    }

    /**
     * @param idArticulo the idArticulo to set
     */
    public void setIdarticulo(Object idArticulo) {
        this.idarticulo = idArticulo;
    }

    /**
     * @return the articulo
     */
    public String getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(String articulo) {
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
    public String getEstado(){
        if(this.activo==true){
            return "";
        }else{
            return "Anulada";
        }
    }
}

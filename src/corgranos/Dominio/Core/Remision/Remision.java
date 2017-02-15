/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Remision;

import corgranos.Dominio.Core.Articulo.Articulo;
import corgranos.Dominio.Core.Maquila.Maquila;
import java.util.Date;
import sic.Aplicacion.Servicios.Reportes.ReporteContable;
import sic.Dominio.Core.Usuario.Usuario;


/**
 *
 * @author FANNY BURGOS
 */
public class Remision extends ReporteContable{
   private Object id;

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
   private Maquila maquila;
   private Articulo articulo;
   private Usuario usuario;
   private Date fechacontable;
   private Date fechacreacion;
   private Date fechaanulacion;
   private String creador;
   private String anulador;
   private boolean activo;
   private double entradas;
   private double salidas;
   private String detalle;

    public Remision(Maquila maquila, Articulo articulo, Usuario usuario, Date fechacontable, String creador,double entradas, double salidas,String detalle) {
        this.maquila = maquila;
        this.articulo = articulo;
        this.usuario = usuario;
        this.fechacontable = fechacontable;      
        this.creador = creador;                
        this.entradas = entradas;
        this.salidas = salidas;
        this.detalle=detalle;
    }

    public Remision() {
        
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
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    public String getStrusuario(){
        return this.usuario.NombreCompleto();
    }
    public String getStrnitusuario(){
        return ""+this.usuario.getNoDocumentoCompleto();
    }
    public String getStrmaquila(){
        if(maquila!=null){
           return this.maquila.getId().toString();
        }else{
            return "";
        }
    }
    public String getStrarticulo(){
        return this.articulo.getDescripcion();
    }    
    public String getStrestado(){
        if(this.activo==true){
            return "";
        }else{
            return "Anulada";
        }
    }
}

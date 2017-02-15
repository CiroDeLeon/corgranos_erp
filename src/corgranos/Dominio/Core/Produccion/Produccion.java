/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Produccion;


import corgranos.Dominio.Core.Maquila.Maquila;
import java.util.Date;
import java.util.Vector;
import sic.Dominio.DomainObject;


/**
 *
 * @author FANNY BURGOS
 */
public class Produccion extends DomainObject{    
    private Maquila maquila;
    private Date fechacontable;
    private Date fechacreacion;
    private Date fechaanulacion;
    private String creador;
    private String anulador;
    private boolean activo;
    private Vector <ProduccionDeArticulo> lista;

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
     * @return the lista
     */
    public Vector <ProduccionDeArticulo> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(Vector <ProduccionDeArticulo> lista) {
        this.lista = lista;
    }
    public String getEstado(){
        if(this.isActivo()){
            return "";
        }else{
            return "ANULADA";
        }
    }   
}

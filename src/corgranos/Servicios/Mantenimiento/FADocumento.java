/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Servicios.Mantenimiento;

import java.util.Date;

/**
 *
 * @author FANNY BURGOS
 */
public class FADocumento {
    private Object idDocumento;
    private Object idCtaTUsuario;
    private int nofactura;
    private String cliente;
    private String nodocumento;
    private Date fechacontable;
    private String ubicacion;
    private boolean activo;

    /**
     * @return the idDocumento
     */
    public Object getIdDocumento() {
        return idDocumento;
    }

    /**
     * @param idDocumento the idDocumento to set
     */
    public void setIdDocumento(Object idDocumento) {
        this.idDocumento = idDocumento;
    }

    /**
     * @return the idCtaTUsuario
     */
    public Object getIdCtaTUsuario() {
        return idCtaTUsuario;
    }

    /**
     * @param idCtaTUsuario the idCtaTUsuario to set
     */
    public void setIdCtaTUsuario(Object idCtaTUsuario) {
        this.idCtaTUsuario = idCtaTUsuario;
    }

    /**
     * @return the nofactura
     */
    public int getNofactura() {
        return nofactura;
    }

    /**
     * @param nofactura the nofactura to set
     */
    public void setNofactura(int nofactura) {
        this.nofactura = nofactura;
    }

    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the nodocumento
     */
    public String getNodocumento() {
        return nodocumento;
    }

    /**
     * @param nodocumento the nodocumento to set
     */
    public void setNodocumento(String nodocumento) {
        this.nodocumento = nodocumento;
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
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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
    
}

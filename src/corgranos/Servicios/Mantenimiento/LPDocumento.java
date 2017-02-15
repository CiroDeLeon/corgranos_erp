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
public class LPDocumento {
    private Object idDocumento;
    private Object idCtaTUsuario;
    private int numeracion;
    private String proveedor;
    private String nodocumento;
    private Date fechacontable;
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
     * @return the numeracion
     */
    public int getNumeracion() {
        return numeracion;
    }

    /**
     * @param numeracion the numeracion to set
     */
    public void setNumeracion(int numeracion) {
        this.numeracion = numeracion;
    }

    /**
     * @return the proveedor
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
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

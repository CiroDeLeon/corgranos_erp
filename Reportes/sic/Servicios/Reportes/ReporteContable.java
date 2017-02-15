/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Servicios.Reportes;

/**
 *
 * @author FANNY BURGOS
 */
public class ReporteContable {
    public String getNitempresa() {
        return DatosGeneralesDeUnReporte.ObtenerInstancia().getNit();
    }
    /**
     * @return the razonsocial
     */
    public String getRazonsocialempresa() {
        return DatosGeneralesDeUnReporte.ObtenerInstancia().getRazonsocial();
    }
    /**
     * @return the direccion
     */
    public String getDireccionempresa() {
        return DatosGeneralesDeUnReporte.ObtenerInstancia().getDireccion();
    }
    /**
     * @return the telefono
     */
    public String getTelefonoempresa() {
        return DatosGeneralesDeUnReporte.ObtenerInstancia().getTelefono();
    }
    /**
     * @return the observacionsuperior
     */
    public String getObservacionsuperior() {
        return DatosGeneralesDeUnReporte.ObtenerInstancia().getObservacionsuperior();
    }
    /**
     * @return the observacioninferior
     */
    public String getObservacioninferior() {
        return DatosGeneralesDeUnReporte.ObtenerInstancia().getObservacioninferior();
    }
     /**
     * @return the publicidad
     */
    public String getPublicidad() {
        return DatosGeneralesDeUnReporte.ObtenerInstancia().getPublicidad();
    }
}

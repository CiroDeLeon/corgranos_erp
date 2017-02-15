/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package corgranos.Dominio.Core.Factura;

/**
 *
 * @author Usuario1
 */
public class Prefijo {
    private long idPrefijo;
    private String descripcion;
    private String dian1;
    private String dian2;
    private int ultima_numeracion;
    private String documento_factura;
    private String abreviatura_factura;
    private String abreviatura;

    /**
     * @return the idPrefijo
     */
    public long getIdPrefijo() {
        return idPrefijo;
    }

    /**
     * @param idPrefijo the idPrefijo to set
     */
    public void setIdPrefijo(long idPrefijo) {
        this.idPrefijo = idPrefijo;
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
     * @return the dian1
     */
    public String getDian1() {
        return dian1;
    }

    /**
     * @param dian1 the dian1 to set
     */
    public void setDian1(String dian1) {
        this.dian1 = dian1;
    }

    /**
     * @return the dian2
     */
    public String getDian2() {
        return dian2;
    }

    /**
     * @param dian2 the dian2 to set
     */
    public void setDian2(String dian2) {
        this.dian2 = dian2;
    }

    /**
     * @return the ultima_numeracion
     */
    public int getUltima_numeracion() {
        return ultima_numeracion;
    }

    /**
     * @param ultima_numeracion the ultima_numeracion to set
     */
    public void setUltima_numeracion(int ultima_numeracion) {
        this.ultima_numeracion = ultima_numeracion;
    }

    /**
     * @return the documento_factura
     */
    public String getDocumento_factura() {
        return documento_factura;
    }

    /**
     * @param documento_factura the documento_factura to set
     */
    public void setDocumento_factura(String documento_factura) {
        this.documento_factura = documento_factura;
    }

    /**
     * @return the abreviatura_factura
     */
    public String getAbreviatura_factura() {
        return abreviatura_factura;
    }

    /**
     * @param abreviatura_factura the abreviatura_factura to set
     */
    public void setAbreviatura_factura(String abreviatura_factura) {
        this.abreviatura_factura = abreviatura_factura;
    }

    /**
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura the abreviatura to set
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    
}

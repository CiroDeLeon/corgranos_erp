/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package corgranos.Dominio.Core.Factura.dto;

import java.util.Date;

/**
 *
 * @author Usuario1
 */
public class InformeFacturaDTO extends sic.Aplicacion.Servicios.Reportes.ReporteContable{
    private String prefijo;
    private Date fecha_inicial;
    private Date fecha_final;
    private Date fecha;
    private String no_documento;
    private String cliente;
    private int no_factura;
    private double subtotal;
    private double total_iva;
    private double total;

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
     * @return the no_documento
     */
    public String getNo_documento() {
        return no_documento;
    }

    /**
     * @param no_documento the no_documento to set
     */
    public void setNo_documento(String no_documento) {
        this.no_documento = no_documento;
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
     * @return the no_factura
     */
    public int getNo_factura() {
        return no_factura;
    }

    /**
     * @param no_factura the no_factura to set
     */
    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }

    /**
     * @return the subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the total_iva
     */
    public double getTotal_iva() {
        return total_iva;
    }

    /**
     * @param total_iva the total_iva to set
     */
    public void setTotal_iva(double total_iva) {
        this.total_iva = total_iva;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the prefijo
     */
    public String getPrefijo() {
        return prefijo;
    }

    /**
     * @param prefijo the prefijo to set
     */
    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    /**
     * @return the fecha_inicial
     */
    public Date getFecha_inicial() {
        return fecha_inicial;
    }

    /**
     * @param fecha_inicial the fecha_inicial to set
     */
    public void setFecha_inicial(Date fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    /**
     * @return the fecha_final
     */
    public Date getFecha_final() {
        return fecha_final;
    }

    /**
     * @param fecha_final the fecha_final to set
     */
    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }
}

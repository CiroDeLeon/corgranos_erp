/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Factura;

import java.util.Date;
import java.util.Vector;
import sic.Dominio.Core.Documento.Documento;


/**
 *
 * @author FANNY BURGOS
 */
public class Factura extends Documento{
    private Date fechaplazo;
    private String tipofactura;// contado o credito
    private double subtotal;
    private double totaliva;
    private double totalrtf;
    private double total;
    private double totaldescuento;    
    private int nofactura;
    private String observaciones;
    private String dian1;
    private String dian2;    
    private Object idcta_t_usuario;
    private String cliente;
    private String ubicacion;
    private Vector <FacturacionDeArticulo> lista;
    private Prefijo prefijo;
    public Factura() {
        
        
    }


    /**
     * @return the fechaplazo
     */
    public Date getFechaplazo() {
        return fechaplazo;
    }

    /**
     * @param fechaplazo the fechaplazo to set
     */
    public void setFechaplazo(Date fechaplazo) {
        this.fechaplazo = fechaplazo;
    }

    /**
     * @return the tipofactura
     */
    public String getTipofactura() {
        return tipofactura;
    }

    /**
     * @param tipofactura the tipofactura to set
     */
    public void setTipofactura(String tipofactura) {
        this.tipofactura = tipofactura;
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
     * @return the totaliva
     */
    public double getTotaliva() {
        return totaliva;
    }

    /**
     * @param totaliva the totaliva to set
     */
    public void setTotaliva(double totaliva) {
        this.totaliva = totaliva;
    }

    /**
     * @return the totalrtf
     */
    public double getTotalrtf() {
        return totalrtf;
    }

    /**
     * @param totalrtf the totalrtf to set
     */
    public void setTotalrtf(double totalrtf) {
        this.totalrtf = totalrtf;
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
     * @return the totaldescuento
     */
    public double getTotaldescuento() {
        return totaldescuento;
    }

    /**
     * @param totaldescuento the totaldescuento to set
     */
    public void setTotaldescuento(double totaldescuento) {
        this.totaldescuento = totaldescuento;
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
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    @Override
    public String getAbreviatura() {
        return prefijo.getAbreviatura_factura();
    }

    @Override
    public String getTdocumento() {
        return prefijo.getDocumento_factura();
    }

    /**
     * @return the lista
     */
    public Vector <FacturacionDeArticulo> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(Vector <FacturacionDeArticulo> lista) {
        this.lista = lista;
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
    public String getEstado(){
        if(!this.isActivo()){
            return "ANULADA";
        }else{
            return "";
        }
    }

    @Override
    public int getResolucion() {
        return 1;
    }

    /**
     * @return the prefijo
     */
    public Prefijo getPrefijo() {
        return prefijo;
    }

    /**
     * @param prefijo the prefijo to set
     */
    public void setPrefijo(Prefijo prefijo) {
        this.prefijo = prefijo;
    }
    
}
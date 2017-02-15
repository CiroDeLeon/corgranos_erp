/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Factura;

import java.util.Date;
import sic.Aplicacion.Servicios.Reportes.ReporteContable;
import sic.Aplicacion.Servicios.Util.ConvertirNumeroEnLetras;

/**
 *
 * @author FANNY BURGOS
 */
public class FacturaRep extends ReporteContable{
    private Object iddocumento;
    private Date fechaplazo;
    private String tipofactura;
    private double subtotal;
    private double totaliva;
    private double totalrtf;
    private double totaldescuento;
    private int nofactura;
    private String observaciones;
    private String dian1;
    private String dian2;
    private Object idctausuario;
    private String cliente;
    private String ubicacion;
    private boolean activa;
    private Date fechacontable;
    private Date fechacreacion;
    private String creador;
    private String modificador;
    private Date fechamodificacion;
    private double total;
    private long nodocumento;
    private Object idfacturaciondearticulo;
    private Object idremision;
    private Object idctaarticulo;
    private Object idmaquila;
    private double preciounitario;
    private double cantidad;
    private double valoriva;
    private boolean remisionada;
    private String articulo;    

    /**
     * @return the iddocumento
     */
    public Object getIddocumento() {
        return iddocumento;
    }

    /**
     * @param iddocumento the iddocumento to set
     */
    public void setIddocumento(Object iddocumento) {
        this.iddocumento = iddocumento;
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

    /**
     * @return the idctausuario
     */
    public Object getIdctausuario() {
        return idctausuario;
    }

    /**
     * @param idctausuario the idctausuario to set
     */
    public void setIdctausuario(Object idctausuario) {
        this.idctausuario = idctausuario;
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

    /**
     * @return the activa
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * @param activa the activa to set
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
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
     * @return the modificador
     */
    public String getModificador() {
        return modificador;
    }

    /**
     * @param modificador the modificador to set
     */
    public void setModificador(String modificador) {
        this.modificador = modificador;
    }

    /**
     * @return the fechamodificacion
     */
    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    /**
     * @param fechamodificacion the fechamodificacion to set
     */
    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
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
     * @return the nodocumento
     */
    public long getNodocumento() {
        return nodocumento;
    }

    /**
     * @param nodocumento the nodocumento to set
     */
    public void setNodocumento(long nodocumento) {
        this.nodocumento = nodocumento;
    }

    /**
     * @return the idfacturaciondearticulo
     */
    public Object getIdfacturaciondearticulo() {
        return idfacturaciondearticulo;
    }

    /**
     * @param idfacturaciondearticulo the idfacturaciondearticulo to set
     */
    public void setIdfacturaciondearticulo(Object idfacturaciondearticulo) {
        this.idfacturaciondearticulo = idfacturaciondearticulo;
    }

    /**
     * @return the idremision
     */
    public Object getIdremision() {
        return idremision;
    }

    /**
     * @param idremision the idremision to set
     */
    public void setIdremision(Object idremision) {
        this.idremision = idremision;
    }

    /**
     * @return the idctaarticulo
     */
    public Object getIdctaarticulo() {
        return idctaarticulo;
    }

    /**
     * @param idctaarticulo the idctaarticulo to set
     */
    public void setIdctaarticulo(Object idctaarticulo) {
        this.idctaarticulo = idctaarticulo;
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
     * @return the valoriva
     */
    public double getValoriva() {
        return valoriva;
    }

    /**
     * @param valoriva the valoriva to set
     */
    public void setValoriva(double valoriva) {
        this.valoriva = valoriva;
    }

    /**
     * @return the remisionada
     */
    public boolean isRemisionada() {
        return remisionada;
    }

    /**
     * @param remisionada the remisionada to set
     */
    public void setRemisionada(boolean remisionada) {
        this.remisionada = remisionada;
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

    public String getValorletras(){
        double t=Math.round(total);
        return ConvertirNumeroEnLetras.getNumero_en_letras(t).toUpperCase()+"(Pesos)";
    }
    public String getEstado(){
        if(this.isActiva()){
            return "";
        }else{
            return "ANULADA";
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Dominio.Servicios.Contabilidad;

import java.util.Date;
import sic.Servicios.Reportes.ReporteContable;
import sic.Dominio.Core.PUC.PucService;

/**
 *
 * @author FANNY BURGOS
 */
public class AsientoContableDTO extends ReporteContable{    
    private Object iddocumento;
    private String soporte;
    private String nitusuario;
    private String usuario;
    private Date fechacontable;
    private String tipodocumento;
    private long idasiento;
    private Object idctapuc;
    private String detalle;
    private double debito;
    private double credito;
    private double entradas;
    private double salidas;
    private int NoFactura;
    private int NoFacturaCompra;
    private double BaseIVA;
    private double BaseRTF;
    private double saldo;
    private double existencia;
    private int numeracion;
    public static String fechainicial;
    public static String fechafinal;
    public static double saldoanterior;
    public static double existenciaanterior;
    public static double sumartf;
    /**
     * @return the sumartf
     */
    public double getSumartf() {
        return AsientoContableDTO.sumartf;
    }

    /**
     * @param aSumartf the sumartf to set
     */
    public void setSumartf(double aSumartf) {
        AsientoContableDTO.sumartf = aSumartf;
    }
    
    public String getFechainicial(){
        return AsientoContableDTO.fechainicial;
    }
    public String getFechafinal(){
        return AsientoContableDTO.fechafinal;
    }
    public void setFechainicial(String fi){        
        AsientoContableDTO.fechainicial=fi;
    }
    public void setFechafinal(String ff){        
        AsientoContableDTO.fechafinal=ff;
    }
    public String getFecha(){
        java.sql.Date f=new java.sql.Date(this.fechacontable.getTime());
        return f.toString();
    }
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
     * @return the soporte
     */
    public String getSoporte() {
        return soporte;
    }

    /**
     * @param soporte the soporte to set
     */
    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    /**
     * @return the nit
     */
    public String getNitusuario() {
        return nitusuario;
    }

    /**
     * @param nit the nit to set
     */
    public void setNitusuario(String nit) {
        this.nitusuario = nit;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
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
     * @return the tipodocumento
     */
    public String getTipodocumento() {
        return tipodocumento;
    }

    /**
     * @param tipodocumento the tipodocumento to set
     */
    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    /**
     * @return the idasiento
     */
    public long getIdasiento() {
        return idasiento;
    }

    /**
     * @param idasiento the idasiento to set
     */
    public void setIdasiento(long idasiento) {
        this.idasiento = idasiento;
    }

    /**
     * @return the idctapuc
     */
    public Object getIdctapuc() {
        return idctapuc;
    }

    /**
     * @param idctapuc the idctapuc to set
     */
    public void setIdctapuc(Object idctapuc) {
        this.idctapuc = idctapuc;
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

    /**
     * @return the debito
     */
    public double getDebito() {
        return debito;
    }

    /**
     * @param debito the debito to set
     */
    public void setDebito(double debito) {
        this.debito = debito;
    }

    /**
     * @return the credito
     */
    public double getCredito() {
        return credito;
    }

    /**
     * @param credito the credito to set
     */
    public void setCredito(double credito) {
        this.credito = credito;
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
     * @return the NoFactura
     */
    public int getNoFactura() {
        return NoFactura;
    }

    /**
     * @param NoFactura the NoFactura to set
     */
    public void setNoFactura(int NoFactura) {
        this.NoFactura = NoFactura;
    }

    /**
     * @return the NoFacturaCompra
     */
    public int getNoFacturaCompra() {
        return NoFacturaCompra;
    }

    /**
     * @param NoFacturaCompra the NoFacturaCompra to set
     */
    public void setNoFacturaCompra(int NoFacturaCompra) {
        this.NoFacturaCompra = NoFacturaCompra;
    }

    /**
     * @return the BaseIVA
     */
    public double getBaseiva() {
        return BaseIVA;
    }

    /**
     * @param BaseIVA the BaseIVA to set
     */
    public void setBaseiva(double BaseIVA) {
        this.BaseIVA = BaseIVA;
    }

    /**
     * @return the BaseRTF
     */
    public double getBasertf() {
        return BaseRTF;
    }

    /**
     * @param BaseRTF the BaseRTF to set
     */
    public void setBasertf(double BaseRTF) {
        this.BaseRTF = BaseRTF;
    }

    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the existencia
     */
    public double getExistencia() {
        return existencia;
    }

    /**
     * @param existencia the existencia to set
     */
    public void setExistencia(double existencia) {
        this.existencia = existencia;
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
    public String getAuxiliar(){
        return this.idctapuc.toString().substring(0,8);
    }
    public String getDenominacion(){
        PucService ps=new PucService();
        return ps.ObtenerCtaPuc(this.getAuxiliar()).getDenominacion();
    }
    public String getTitulo(){
        PucService ps=new PucService();
        return ps.ObtenerCtaPuc(this.getIdctapuc().toString()).getDenominacion();
    }

    /**
     * @return the saldoanterior
     */
    public double getSaldoanterior() {
        return saldoanterior;
    }

    /**
     * @param saldoanterior the saldoanterior to set
     */
    public void setSaldoanterior(double saldoanterior) {
        AsientoContableDTO.saldoanterior = saldoanterior;
    }
    public double getExistenciaanterior() {
        return existenciaanterior;
    }

    /**
     * @param saldoanterior the saldoanterior to set
     */
    public void setExistenciaanterior(double can) {
        AsientoContableDTO.existenciaanterior = can;
    }
    public int getFactura(){
        return this.getNoFactura()+this.getNoFacturaCompra();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Liquidacion;

import java.util.Date;
import sic.Aplicacion.Servicios.Reportes.ReporteContable;

/**
 *
 * @author FANNY BURGOS
 */
public class LiquidacionDePaddyPorProveedorDTO extends ReporteContable{
    
public static String usuario;
public static Date fechainicial;
public static Date fechafinal;
private Date fecha;
private int liquidacion;
private int tiquete;
private double humedadpactada;
private double impurezapactada;
private double humedadlaboratorio;
private double impurezalaboratorio;
private double pesobruto;
private double pesodestare;
private double pesoliquidado;
private double rojolaboratorio;
private double partidolaboratorio;
private double yesolaboratorio;
private double harinalaboratorio;
private double valorpactado;
private double valorliquidado;
private double valorcompra;
private double fomento;
private double bolsa;
private double retefuente;
private double total;
private String cuenta_t;
 /**
     * @return the usuario
     */
    public String getUsuario() {
        return LiquidacionDePaddyPorProveedorDTO.usuario;
    }

    /**
     * @param aUsuario the usuario to set
     */
    public void setUsuario(String aUsuario) {
        LiquidacionDePaddyPorProveedorDTO.usuario = aUsuario;
    }

    /**
     * @return the fechainicial
     */
    public Date getFechainicial() {
        return LiquidacionDePaddyPorProveedorDTO.fechainicial;
    }

    /**
     * @param aFechainicial the fechainicial to set
     */
    public void setFechainicial(Date aFechainicial) {
        LiquidacionDePaddyPorProveedorDTO.fechainicial = aFechainicial;
    }

    /**
     * @return the fechafinal
     */
    public Date getFechafinal() {
        return LiquidacionDePaddyPorProveedorDTO.fechafinal;
    }

    /**
     * @param aFechafinal the fechafinal to set
     */
    public void setFechafinal(Date aFechafinal) {
        LiquidacionDePaddyPorProveedorDTO.fechafinal = aFechafinal;
    }
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
     * @return the liquidacion
     */
    public int getLiquidacion() {
        return liquidacion;
    }

    /**
     * @param liquidacion the liquidacion to set
     */
    public void setLiquidacion(int liquidacion) {
        this.liquidacion = liquidacion;
    }

    /**
     * @return the tiquete
     */
    public int getTiquete() {
        return tiquete;
    }

    /**
     * @param tiquete the tiquete to set
     */
    public void setTiquete(int tiquete) {
        this.tiquete = tiquete;
    }

    /**
     * @return the humedadpactada
     */
    public double getHumedadpactada() {
        return humedadpactada;
    }

    /**
     * @param humedadpactada the humedadpactada to set
     */
    public void setHumedadpactada(double humedadpactada) {
        this.humedadpactada = humedadpactada;
    }

    /**
     * @return the impurezapactada
     */
    public double getImpurezapactada() {
        return impurezapactada;
    }

    /**
     * @param impurezapactada the impurezapactada to set
     */
    public void setImpurezapactada(double impurezapactada) {
        this.impurezapactada = impurezapactada;
    }

    /**
     * @return the humedadlaboratorio
     */
    public double getHumedadlaboratorio() {
        return humedadlaboratorio;
    }

    /**
     * @param humedadlaboratorio the humedadlaboratorio to set
     */
    public void setHumedadlaboratorio(double humedadlaboratorio) {
        this.humedadlaboratorio = humedadlaboratorio;
    }

    /**
     * @return the impurezlaboratorio
     */
    public double getImpurezalaboratorio() {
        return impurezalaboratorio;
    }

    /**
     * @param impurezlaboratorio the impurezlaboratorio to set
     */
    public void setImpurezalaboratorio(double impurezlaboratorio) {
        this.impurezalaboratorio = impurezlaboratorio;
    }

    /**
     * @return the pesobruto
     */
    public double getPesobruto() {
        return pesobruto;
    }

    /**
     * @param pesobruto the pesobruto to set
     */
    public void setPesobruto(double pesobruto) {
        this.pesobruto = pesobruto;
    }

    /**
     * @return the pesodestare
     */
    public double getPesodestare() {
        return pesodestare;
    }

    /**
     * @param pesodestare the pesodestare to set
     */
    public void setPesodestare(double pesodestare) {
        this.pesodestare = pesodestare;
    }

    /**
     * @return the pesoliquidado
     */
    public double getPesoliquidado() {
        return pesoliquidado;
    }

    /**
     * @param pesoliquidado the pesoliquidado to set
     */
    public void setPesoliquidado(double pesoliquidado) {
        this.pesoliquidado = pesoliquidado;
    }

    /**
     * @return the rojolaboratorio
     */
    public double getRojolaboratorio() {
        return rojolaboratorio;
    }

    /**
     * @param rojolaboratorio the rojolaboratorio to set
     */
    public void setRojolaboratorio(double rojolaboratorio) {
        this.rojolaboratorio = rojolaboratorio;
    }

    /**
     * @return the partidolaboratorio
     */
    public double getPartidolaboratorio() {
        return partidolaboratorio;
    }

    /**
     * @param partidolaboratorio the partidolaboratorio to set
     */
    public void setPartidolaboratorio(double partidolaboratorio) {
        this.partidolaboratorio = partidolaboratorio;
    }

    /**
     * @return the yesolaboratorio
     */
    public double getYesolaboratorio() {
        return yesolaboratorio;
    }

    /**
     * @param yesolaboratorio the yesolaboratorio to set
     */
    public void setYesolaboratorio(double yesolaboratorio) {
        this.yesolaboratorio = yesolaboratorio;
    }

    /**
     * @return the harinalaboratorio
     */
    public double getHarinalaboratorio() {
        return harinalaboratorio;
    }

    /**
     * @param harinalaboratorio the harinalaboratorio to set
     */
    public void setHarinalaboratorio(double harinalaboratorio) {
        this.harinalaboratorio = harinalaboratorio;
    }

    /**
     * @return the valorpactado
     */
    public double getValorpactado() {
        return valorpactado;
    }

    /**
     * @param valorpactado the valorpactado to set
     */
    public void setValorpactado(double valorpactado) {
        this.valorpactado = valorpactado;
    }

    /**
     * @return the valorliquidado
     */
    public double getValorliquidado() {
        return valorliquidado;
    }

    /**
     * @param valorliquidado the valorliquidado to set
     */
    public void setValorliquidado(double valorliquidado) {
        this.valorliquidado = valorliquidado;
    }

    /**
     * @return the valorcompra
     */
    public double getValorcompra() {
        return valorcompra;
    }

    /**
     * @param valorcompra the valorcompra to set
     */
    public void setValorcompra(double valorcompra) {
        this.valorcompra = valorcompra;
    }

    /**
     * @return the fomento
     */
    public double getFomento() {
        return fomento;
    }

    /**
     * @param fomento the fomento to set
     */
    public void setFomento(double fomento) {
        this.fomento = fomento;
    }

    /**
     * @return the bolsa
     */
    public double getBolsa() {
        return bolsa;
    }

    /**
     * @param bolsa the bolsa to set
     */
    public void setBolsa(double bolsa) {
        this.bolsa = bolsa;
    }

    /**
     * @return the retefuente
     */
    public double getRetefuente() {
        return retefuente;
    }

    /**
     * @param retefuente the retefuente to set
     */
    public void setRetefuente(double retefuente) {
        this.retefuente = retefuente;
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
     * @return the cuenta_t
     */
    public String getCuenta_t() {
        return cuenta_t;
    }

    /**
     * @param cuenta_t the cuenta_t to set
     */
    public void setCuenta_t(String cuenta_t) {
        this.cuenta_t = cuenta_t;
    }
}

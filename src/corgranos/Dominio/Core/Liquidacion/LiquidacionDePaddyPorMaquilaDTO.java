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
public class LiquidacionDePaddyPorMaquilaDTO extends ReporteContable{
    private Object idDocumento;
    private int liquidacion;
    private int tiquete;
    private long nit;
    private int maquila;
    private String usuario;
    private double humedad;
    private double impureza;
    private double rojo;
    private double partido;
    private double yeso;
    private double harina;
    private double pesobruto;
    private double pesoliquidado;
    private double pesodestarado;
    private double peso14_1;
    private double masablanca;    
    private double valor;
    private Date fechacontable;

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
     * @return the nit
     */
    public long getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(long nit) {
        this.nit = nit;
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
     * @return the humedad
     */
    public double getHumedad() {
        return humedad;
    }

    /**
     * @param humedad the humedad to set
     */
    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

    /**
     * @return the impureza
     */
    public double getImpureza() {
        return impureza;
    }

    /**
     * @param impureza the impureza to set
     */
    public void setImpureza(double impureza) {
        this.impureza = impureza;
    }

    /**
     * @return the pesodestarado
     */
    public double getPesodestarado() {
        return pesodestarado;
    }

    /**
     * @param pesodestarado the pesodestarado to set
     */
    public void setPesodestarado(double pesodestarado) {
        this.pesodestarado = pesodestarado;
    }

    /**
     * @return the peso14_1
     */
    public double getPeso14_1() {
        return peso14_1;
    }

    /**
     * @param peso14_1 the peso14_1 to set
     */
    public void setPeso14_1(double peso14_1) {
        this.peso14_1 = peso14_1;
    }

    /**
     * @return the masablanca
     */
    public double getMasablanca() {
        return masablanca;
    }

    /**
     * @param masablanca the masablanca to set
     */
    public void setMasablanca(double masablanca) {
        this.masablanca = masablanca;
    }   
    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the rojo
     */
    public double getRojo() {
        return rojo;
    }

    /**
     * @param rojo the rojo to set
     */
    public void setRojo(double rojo) {
        this.rojo = rojo;
    }

    /**
     * @return the partido
     */
    public double getPartido() {
        return partido;
    }

    /**
     * @param partido the partido to set
     */
    public void setPartido(double partido) {
        this.partido = partido;
    }

    /**
     * @return the yeso
     */
    public double getYeso() {
        return yeso;
    }

    /**
     * @param yeso the yeso to set
     */
    public void setYeso(double yeso) {
        this.yeso = yeso;
    }

    /**
     * @return the harina
     */
    public double getHarina() {
        return harina;
    }

    /**
     * @param harina the harina to set
     */
    public void setHarina(double harina) {
        this.harina = harina;
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
     * @return the maquila
     */
    public int getMaquila() {
        return maquila;
    }

    /**
     * @param maquila the maquila to set
     */
    public void setMaquila(int maquila) {
        this.maquila = maquila;
    }

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
}

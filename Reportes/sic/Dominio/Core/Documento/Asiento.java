/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Dominio.Core.Documento;

import sic.Dominio.DomainObject;
import sic.Dominio.Core.PUC.Cta_PUC;

/**
 *
 * @author FANNY BURGOS
 */
public class Asiento extends DomainObject{
    private Cta_PUC CtaPuc;
    private Documento documento;
    private String detalle;
    private double debito;
    private double credito;
    private double entradas;
    private double salidas;
    private int NoFactura;
    private int NoFacturaCompra;
    private double BaseIVA;
    private double BaseRTF;
    private int tiporegistro=1;

    /**
     * @return the CtaPuc
     */
    public Cta_PUC getCtaPuc() {
        return CtaPuc;
    }

    /**
     * @param CtaPuc the CtaPuc to set
     */
    public void setCtaPuc(Cta_PUC CtaPuc) {
        this.CtaPuc = CtaPuc;
    }

    /**
     * @return the documento
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(Documento documento) {
        this.documento = documento;
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
    public double getBaseIVA() {
        return BaseIVA;
    }

    /**
     * @param BaseIVA the BaseIVA to set
     */
    public void setBaseIVA(double BaseIVA) {
        this.BaseIVA = BaseIVA;
    }

    /**
     * @return the BaseRTF
     */
    public double getBaseRTF() {
        return BaseRTF;
    }

    /**
     * @param BaseRTF the BaseRTF to set
     */
    public void setBaseRTF(double BaseRTF) {
        this.BaseRTF = BaseRTF;
    }

    /**
     * @return the tiporegistro
     */
    public int getTiporegistro() {
        return tiporegistro;
    }

    /**
     * @param tiporegistro the tiporegistro to set
     */
    public void setTiporegistro(int tiporegistro) {
        this.tiporegistro = tiporegistro;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Maquila;

import sic.Dominio.DomainObject;





/**
 *
 * @author FANNY BURGOS
 */
public class Maquila extends DomainObject{
   private double totalpeso14_1;
   private double totalpesodestarado;
   private double totalcosto;
   private double totalmasablanca;
   private double inicialpeso14_1;
   private double inicialpesodestarado;
   private double inicialcosto;
   private double inicialmasablanca;   
   private boolean cerrada=false;
   private boolean activa;
   private double valortrilla;
   private String idDocumentoDeCierre;   
   private double totalpesobruto;
   private double totalpesoliquidado;
   private double inicialpesobruto;
   private double inicialpesoliquidado;


    public Maquila() {
    }

    public Maquila(double totalpeso14_1, double totalpesodestarado, double totalcosto, double totalmasablanca, double inicialpeso14_1, double inicialpesodestarado, double inicialcosto, double inicialmasablanca, boolean cerrada, boolean activa,double valortrilla,String idDocumentoDeCierre) {
        this.totalpeso14_1 = totalpeso14_1;
        this.totalpesodestarado = totalpesodestarado;
        this.totalcosto = totalcosto;
        this.totalmasablanca = totalmasablanca;
        this.inicialpeso14_1 = inicialpeso14_1;
        this.inicialpesodestarado = inicialpesodestarado;
        this.inicialcosto = inicialcosto;
        this.inicialmasablanca = inicialmasablanca;
        this.cerrada = cerrada;
        this.activa = activa;
        this.valortrilla=valortrilla;
        this.idDocumentoDeCierre=idDocumentoDeCierre;
    }

    /**
     * @return the totalpesodestarado
     */
    public double getTotalpesodestarado() {
        return totalpesodestarado;
    }

    /**
     * @param totalpesodestarado the totalpesodestarado to set
     */
    public void setTotalpesodestarado(double totalpesodestarado) {
        this.totalpesodestarado = totalpesodestarado;
    }

    /**
     * @return the totalcosto
     */
    public double getTotalcosto() {
        return totalcosto;
    }

    /**
     * @param totalcosto the totalcosto to set
     */
    public void setTotalcosto(double totalcosto) {
        this.totalcosto = totalcosto;
    }

    /**
     * @return the cerrada
     */
    public boolean isCerrada() {
        return cerrada;
    }

    /**
     * @param cerrada the cerrada to set
     */
    public void setCerrada(boolean cerrada) {
        this.cerrada = cerrada;
    }

    /**
     * @return the totalpeso14_1
     */
    public double getTotalpeso14_1() {
        return totalpeso14_1;
    }

    /**
     * @param totalpeso14_1 the totalpeso14_1 to set
     */
    public void setTotalpeso14_1(double totalpeso14_1) {
        this.totalpeso14_1 = totalpeso14_1;
    }

    /**
     * @return the totalmasablanca
     */
    public double getTotalmasablanca() {
        return totalmasablanca;
    }

    /**
     * @param totalmasablanca the totalmasablanca to set
     */
    public void setTotalmasablanca(double totalmasablanca) {
        this.totalmasablanca = totalmasablanca;
    }

    /**
     * @return the inicialpeso14_1
     */
    public double getInicialpeso14_1() {
        return inicialpeso14_1;
    }

    /**
     * @param inicialpeso14_1 the inicialpeso14_1 to set
     */
    public void setInicialpeso14_1(double inicialpeso14_1) {
        this.inicialpeso14_1 = inicialpeso14_1;
    }

    /**
     * @return the inicialpesodestarado
     */
    public double getInicialpesodestarado() {
        return inicialpesodestarado;
    }

    /**
     * @param inicialpesodestarado the inicialpesodestarado to set
     */
    public void setInicialpesodestarado(double inicialpesodestarado) {
        this.inicialpesodestarado = inicialpesodestarado;
    }

    /**
     * @return the inicialcosto
     */
    public double getInicialcosto() {
        return inicialcosto;
    }

    /**
     * @param inicialcosto the inicialcosto to set
     */
    public void setInicialcosto(double inicialcosto) {
        this.inicialcosto = inicialcosto;
    }

    /**
     * @return the inicialmasablanca
     */
    public double getInicialmasablanca() {
        return inicialmasablanca;
    }

    /**
     * @param inicialmasablanca the inicialmasablanca to set
     */
    public void setInicialmasablanca(double inicialmasablanca) {
        this.inicialmasablanca = inicialmasablanca;
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
     * @return the valortrilla
     */
    public double getValortrilla() {
        return valortrilla;
    }

    /**
     * @param valortrilla the valortrilla to set
     */
    public void setValortrilla(double valortrilla) {
        this.valortrilla = valortrilla;
    }

    /**
     * @return the idDocumentoDeCierre
     */
    public String getIdDocumentoDeCierre() {
        return idDocumentoDeCierre;
    }

    /**
     * @param idDocumentoDeCierre the idDocumentoDeCierre to set
     */
    public void setIdDocumentoDeCierre(String idDocumentoDeCierre) {
        this.idDocumentoDeCierre = idDocumentoDeCierre;
    }

    /**
     * @return the totalpesobruto
     */
    public double getTotalpesobruto() {
        return totalpesobruto;
    }

    /**
     * @param totalpesobruto the totalpesobruto to set
     */
    public void setTotalpesobruto(double totalpesobruto) {
        this.totalpesobruto = totalpesobruto;
    }

    /**
     * @return the totalpesoliquidado
     */
    public double getTotalpesoliquidado() {
        return totalpesoliquidado;
    }

    /**
     * @param totalpesoliquidado the totalpesoliquidado to set
     */
    public void setTotalpesoliquidado(double totalpesoliquidado) {
        this.totalpesoliquidado = totalpesoliquidado;
    }

    /**
     * @return the inicialpesobruto
     */
    public double getInicialpesobruto() {
        return inicialpesobruto;
    }

    /**
     * @param inicialpesobruto the inicialpesobruto to set
     */
    public void setInicialpesobruto(double inicialpesobruto) {
        this.inicialpesobruto = inicialpesobruto;
    }

    /**
     * @return the inicialpesoliquidado
     */
    public double getInicialpesoliquidado() {
        return inicialpesoliquidado;
    }

    /**
     * @param inicialpesoliquidado the inicialpesoliquidado to set
     */
    public void setInicialpesoliquidado(double inicialpesoliquidado) {
        this.inicialpesoliquidado = inicialpesoliquidado;
    }
}

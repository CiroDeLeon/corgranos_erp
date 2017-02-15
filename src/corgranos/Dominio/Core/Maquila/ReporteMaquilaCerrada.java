/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Maquila;

import sic.Aplicacion.Servicios.Reportes.ReporteContable;



/**
 *
 * @author FANNY BURGOS
 */
public class ReporteMaquilaCerrada extends ReporteContable{
    private String idMaquila;
    
    private double totalhumedo;
    private double totalmasablanca;
    private double total14_1;
    private double totalcosto;
    private double totalmasablancaproducida;
    
    private String descripcion;
    private double debito;
    private double entradas;
    private double credito;
    private double salidas;

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
     * @return the idMaquila
     */
    public String getIdMaquila() {
        return idMaquila;
    }

    /**
     * @param idMaquila the idMaquila to set
     */
    public void setIdMaquila(String idMaquila) {
        this.idMaquila = idMaquila;
    }
    public double getCostounitario(){
        if(entradas+salidas!=0){
           return Math.round((debito+credito)/(entradas+salidas));
        }else{
           return 0;    
        }
    }

    /**
     * @return the totalhumedo
     */
    public double getTotalhumedo() {
        return totalhumedo;
    }

    /**
     * @param totalhumedo the totalhumedo to set
     */
    public void setTotalhumedo(double totalhumedo) {
        this.totalhumedo = totalhumedo;
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
     * @return the total14_1
     */
    public double getTotal14_1() {
        return total14_1;
    }

    /**
     * @param total14_1 the total14_1 to set
     */
    public void setTotal14_1(double total14_1) {
        this.total14_1 = total14_1;
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
     * @return the totalmasablancaproducida
     */
    public double getTotalmasablancaproducida() {
        return totalmasablancaproducida;
    }

    /**
     * @param totalmasablancaproducida the totalmasablancaproducida to set
     */
    public void setTotalmasablancaproducida(double totalmasablancaproducida) {
        this.totalmasablancaproducida = totalmasablancaproducida;
    }
    
}

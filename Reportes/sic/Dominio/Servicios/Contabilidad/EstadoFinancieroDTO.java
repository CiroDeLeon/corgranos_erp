/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Dominio.Servicios.Contabilidad;

import java.util.Date;


/**
 *
 * @author FANNY BURGOS
 */
public class EstadoFinancieroDTO extends ReporteSaldoDTO{    
    public static Date fechacorte;
    public static Date fechainicio;
    public static double sumaactivos;
    public static double sumapasivos;
    public static double sumapatrimonio;
    public static double sumaingresosop;
    public static double sumaingresosnop;
    public static double sumagastosopadm;
    public static double sumagastosopvta;
    public static double sumagastosnop;
    public static double sumacostosvta;
    public static double sumacostosprod;
    public static double sumagastosrta;
    public static double sumagastospyg;
    public static double utilidad;

    public EstadoFinancieroDTO() {
    }

    public EstadoFinancieroDTO(String codigocta, String denominacion, double saldo) {
        this.codigocta = codigocta;
        this.denominacion = denominacion;
        this.saldo = saldo;
    }    
    public double getUtilidad(){
        return EstadoFinancieroDTO.utilidad;
    }
    public void setUtilidad(double utilidad){
        EstadoFinancieroDTO.utilidad=utilidad;
    }
    /**
     * @return the fechacorte
     */
    public Date getFechacorte() {
        return EstadoFinancieroDTO.fechacorte;
    }

    /**
     * @param fechacorte the fechacorte to set
     */
    public void setFechacorte(Date fechacorte) {
        EstadoFinancieroDTO.fechacorte = fechacorte;
    }
      /**
     * @return the fechacorte
     */
    public Date getFechainicio() {
        return EstadoFinancieroDTO.fechainicio;
    }

    /**
     * @param fechacorte the fechacorte to set
     */
    public void setFechainicio(Date fechainicio) {
        EstadoFinancieroDTO.fechainicio = fechainicio;
    }

    /**
     * @return the sumaactivos
     */
    public double getSumaactivos() {
        return EstadoFinancieroDTO.sumaactivos;
    }

    /**
     * @param sumaactivos the sumaactivos to set
     */
    public void setSumaactivos(double sumaactivos) {
        EstadoFinancieroDTO.sumaactivos = sumaactivos;
    }

    /**
     * @return the sumapasivos
     */
    public double getSumapasivos() {
        return EstadoFinancieroDTO.sumapasivos;
    }

    /**
     * @param sumapasivos the sumapasivos to set
     */
    public void setSumapasivos(double sumapasivos) {
        EstadoFinancieroDTO.sumapasivos = sumapasivos;
    }

    /**
     * @return the sumapatrimonio
     */
    public double getSumapatrimonio() {
        return EstadoFinancieroDTO.sumapatrimonio;
    }

    /**
     * @param sumapatrimonio the sumapatrimonio to set
     */
    public void setSumapatrimonio(double sumapatrimonio) {
        EstadoFinancieroDTO.sumapatrimonio = sumapatrimonio;
    }

    /**
     * @return the sumaingresosop
     */
    public double getSumaingresosop() {
        return EstadoFinancieroDTO.sumaingresosop;
    }

    /**
     * @param sumaingresosop the sumaingresosop to set
     */
    public void setSumaingresosop(double sumaingresosop) {
        EstadoFinancieroDTO.sumaingresosop = sumaingresosop;
    }

    /**
     * @return the sumaingresosnop
     */
    public double getSumaingresosnop() {
        return EstadoFinancieroDTO.sumaingresosnop;
    }

    /**
     * @param sumaingresosnop the sumaingresosnop to set
     */
    public void setSumaingresosnop(double sumaingresosnop) {
        EstadoFinancieroDTO.sumaingresosnop = sumaingresosnop;
    }

    /**
     * @return the sumagastosop
     */
    public double getSumagastosopadm() {
        return EstadoFinancieroDTO.sumagastosopadm;
    }

    /**
     * @param sumagastosop the sumagastosop to set
     */
    public void setSumagastosopadm(double sumagastosopadm) {
        EstadoFinancieroDTO.sumagastosopadm = sumagastosopadm;
    }

    /**
     * @return the sumagastosnop
     */
    public double getSumagastosnop() {
        return EstadoFinancieroDTO.sumagastosnop;
    }

    /**
     * @param sumagastosnop the sumagastosnop to set
     */
    public void setSumagastosnop(double sumagastosnop) {
        EstadoFinancieroDTO.sumagastosnop = sumagastosnop;
    }

    /**
     * @return the sumacostosvta
     */
    public double getSumacostosvta() {
        return EstadoFinancieroDTO.sumacostosvta;
    }

    /**
     * @param sumacostosvta the sumacostosvta to set
     */
    public void setSumacostosvta(double sumacostosvta) {
        EstadoFinancieroDTO.sumacostosvta = sumacostosvta;
    }

    /**
     * @return the sumacostosprod
     */
    public double getSumacostosprod() {
        return EstadoFinancieroDTO.sumacostosprod;
    }

    /**
     * @param sumacostosprod the sumacostosprod to set
     */
    public void setSumacostosprod(double sumacostosprod) {
        EstadoFinancieroDTO.sumacostosprod = sumacostosprod;
    }
     public double getSumagastosopvta() {
        return EstadoFinancieroDTO.sumagastosopvta;
    }

    /**
     * @param sumagastosop the sumagastosop to set
     */
    public void setSumagastosopvta(double sumagastosop) {
        EstadoFinancieroDTO.sumagastosopvta = sumagastosop;
    }
    
     public double getSumagastosrta() {
        return EstadoFinancieroDTO.sumagastosrta;
    }

    /**
     * @param sumagastosnop the sumagastosnop to set
     */
    public void setSumagastosrta(double sumagastosrta) {
        EstadoFinancieroDTO.sumagastosrta = sumagastosrta;
    }
     public double getSumagastospyg() {
        return EstadoFinancieroDTO.sumagastospyg;
    }

    /**
     * @param sumagastosnop the sumagastosnop to set
     */
    public void setSumagastospyg(double sumagastospyg) {
        EstadoFinancieroDTO.sumagastospyg = sumagastospyg;
    }
    public double getUtilidadbruta(){
        return EstadoFinancieroDTO.sumaingresosop-EstadoFinancieroDTO.sumacostosvta-EstadoFinancieroDTO.sumacostosprod;
    }
    public static double getUtilidadbrutaestatica(){
        return EstadoFinancieroDTO.sumaingresosop-EstadoFinancieroDTO.sumacostosvta-EstadoFinancieroDTO.sumacostosprod;
    }
    public double getUtilidadoperacional(){
        return this.getUtilidadbruta()-EstadoFinancieroDTO.sumagastosopadm-EstadoFinancieroDTO.sumagastosopvta;
    }
    public static double getUtilidadoperacionalestatica(){
        return EstadoFinancieroDTO.getUtilidadbrutaestatica()-EstadoFinancieroDTO.sumagastosopadm-EstadoFinancieroDTO.sumagastosopvta;
    }
    public double getUtilidadnooperacional(){
        return EstadoFinancieroDTO.sumaingresosnop-EstadoFinancieroDTO.sumagastosnop;
    }
    public static double getUtilidadnooperacionalestatica(){
        return EstadoFinancieroDTO.sumaingresosnop-EstadoFinancieroDTO.sumagastosnop;
    }
    public double getUtilidadantesimpuestos(){
        return EstadoFinancieroDTO.getUtilidadoperacionalestatica()+EstadoFinancieroDTO.getUtilidadnooperacionalestatica();
    }
    public static double getUtilidadantesimpuestosestatica(){
        return EstadoFinancieroDTO.getUtilidadoperacionalestatica()+EstadoFinancieroDTO.getUtilidadnooperacionalestatica();
    }
    public double getUtilidadejercicio(){
        return EstadoFinancieroDTO.getUtilidadantesimpuestosestatica()-EstadoFinancieroDTO.sumagastosrta-EstadoFinancieroDTO.sumagastospyg;
    }
    public static double getUtilidadejercicioestatica(){
        return EstadoFinancieroDTO.getUtilidadantesimpuestosestatica()-EstadoFinancieroDTO.sumagastosrta-EstadoFinancieroDTO.sumagastospyg;
    }
}

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
public class AnexoDTO extends ReporteSaldoDTO{
    private double parcial;
    public static Date fechainicio;
    public static Date fechacorte;
    public static int clase;
    public static String denominacionclase;
    public double getParcial() {
        return parcial;
    }
    public void setParcial(double parcial) {
        this.parcial = parcial;
    }
    public Date getFechainicio() {
        return AnexoDTO.fechainicio;
    }
    public Date getFechacorte() {
        return AnexoDTO.fechacorte;
    }
    public int getClase() {
        return AnexoDTO.clase;
    }
    public String getDenominacionclase() {
        return AnexoDTO.denominacionclase;
    }
    @Override
    public String getCodigocta(){
        if(this.codigocta.length()<=8){
            return this.codigocta;
        }else{
            return this.codigocta.substring(8,this.codigocta.length());
        }
    }
}

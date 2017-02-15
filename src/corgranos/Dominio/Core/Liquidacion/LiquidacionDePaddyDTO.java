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
public class LiquidacionDePaddyDTO extends ReporteContable{
    private int numeracion;
    private Date fechacontable;    
    private int    tiquete;    
    private double humedad_pact;
    private double impureza_pact;
    private double humedad_lab;
    private double impureza_lab;
    private double pesobruto;
    private double pesodestarado;
    private double peso_liquidado;    
    private double rojo_pact;
    private double partido_pact;
    private double yeso_pact;
    private double harina_pact;    
    private double rojo_lab;
    private double partido_lab;
    private double yeso_lab;
    private double harina_lab;   
    private double valor_kg;
    private double valor_liquidado;
    private double valor_compra;
    private double fomento;
    private double bolsa;
    private double retefuente;    
    private double total;
    private String proveedor;
    private long nit;
    private boolean activa;
    private Date fechacreacion;
    private String creador;
    private Date fechamodificacion;
    private String modificador;
    private String cuenta_t;
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
     * @return the humedad_pact
     */
    public double getHumedad_pact() {
        return humedad_pact;
    }

    /**
     * @param humedad_pact the humedad_pact to set
     */
    public void setHumedad_pact(double humedad_pact) {
        this.humedad_pact = humedad_pact;
    }

    /**
     * @return the impureza_pact
     */
    public double getImpureza_pact() {
        return impureza_pact;
    }

    /**
     * @param impureza_pact the impureza_pact to set
     */
    public void setImpureza_pact(double impureza_pact) {
        this.impureza_pact = impureza_pact;
    }

    /**
     * @return the humedad_lab
     */
    public double getHumedad_lab() {
        return humedad_lab;
    }

    /**
     * @param humedad_lab the humedad_lab to set
     */
    public void setHumedad_lab(double humedad_lab) {
        this.humedad_lab = humedad_lab;
    }

    /**
     * @return the impureza_lab
     */
    public double getImpureza_lab() {
        return impureza_lab;
    }

    /**
     * @param impureza_lab the impureza_lab to set
     */
    public void setImpureza_lab(double impureza_lab) {
        this.impureza_lab = impureza_lab;
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
     * @return the peso_liquidado
     */
    public double getPeso_liquidado() {
        return peso_liquidado;
    }

    /**
     * @param peso_liquidado the peso_liquidado to set
     */
    public void setPeso_liquidado(double peso_liquidado) {
        this.peso_liquidado = peso_liquidado;
    }

    /**
     * @return the rojo_pact
     */
    public double getRojo_pact() {
        return rojo_pact;
    }

    /**
     * @param rojo_pact the rojo_pact to set
     */
    public void setRojo_pact(double rojo_pact) {
        this.rojo_pact = rojo_pact;
    }

    /**
     * @return the partido_pact
     */
    public double getPartido_pact() {
        return partido_pact;
    }

    /**
     * @param partido_pact the partido_pact to set
     */
    public void setPartido_pact(double partido_pact) {
        this.partido_pact = partido_pact;
    }

    /**
     * @return the yeso_pact
     */
    public double getYeso_pact() {
        return yeso_pact;
    }

    /**
     * @param yeso_pact the yeso_pact to set
     */
    public void setYeso_pact(double yeso_pact) {
        this.yeso_pact = yeso_pact;
    }

    /**
     * @return the harina_pact
     */
    public double getHarina_pact() {
        return harina_pact;
    }

    /**
     * @param harina_pact the harina_pact to set
     */
    public void setHarina_pact(double harina_pact) {
        this.harina_pact = harina_pact;
    }

    /**
     * @return the rojo_lab
     */
    public double getRojo_lab() {
        return rojo_lab;
    }

    /**
     * @param rojo_lab the rojo_lab to set
     */
    public void setRojo_lab(double rojo_lab) {
        this.rojo_lab = rojo_lab;
    }

    /**
     * @return the partido_lab
     */
    public double getPartido_lab() {
        return partido_lab;
    }

    /**
     * @param partido_lab the partido_lab to set
     */
    public void setPartido_lab(double partido_lab) {
        this.partido_lab = partido_lab;
    }

    /**
     * @return the yeso_lab
     */
    public double getYeso_lab() {
        return yeso_lab;
    }

    /**
     * @param yeso_lab the yeso_lab to set
     */
    public void setYeso_lab(double yeso_lab) {
        this.yeso_lab = yeso_lab;
    }

    /**
     * @return the harina_lab
     */
    public double getHarina_lab() {
        return harina_lab;
    }

    /**
     * @param harina_lab the harina_lab to set
     */
    public void setHarina_lab(double harina_lab) {
        this.harina_lab = harina_lab;
    }

    /**
     * @return the valor_kg
     */
    public double getValor_kg() {
        return valor_kg;
    }

    /**
     * @param valor_kg the valor_kg to set
     */
    public void setValor_kg(double valor_kg) {
        this.valor_kg = valor_kg;
    }

    /**
     * @return the valor_liquidado
     */
    public double getValor_liquidado() {
        return valor_liquidado;
    }

    /**
     * @param valor_liquidado the valor_liquidado to set
     */
    public void setValor_liquidado(double valor_liquidado) {
        this.valor_liquidado = valor_liquidado;
    }

    /**
     * @return the valor_compra
     */
    public double getValor_compra() {
        return valor_compra;
    }

    /**
     * @param valor_compra the valor_compra to set
     */
    public void setValor_compra(double valor_compra) {
        this.valor_compra = valor_compra;
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
     * @return the proveedor
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
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
    public String getEstado(){
        if(!this.activa){
            return "Anulado";
        }else{
            return "";
        }
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
    public double getIncentivo(){
         java.util.Date inicio=new java.util.Date(114,7,14);
        java.util.Date _final=new java.util.Date(115,3,15);
        System.out.println(inicio.toString());
        System.out.println(_final.toString());
        if(this.getFechacontable().after(inicio) && this.fechacontable.before(_final))
        return this.getPeso_liquidado()*100;
        else
        return 0;    
    }
    public double getTotalizado(){       
        return this.getTotal()+this.getIncentivo();       
    }
}
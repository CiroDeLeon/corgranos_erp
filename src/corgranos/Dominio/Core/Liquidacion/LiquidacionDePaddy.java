/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Liquidacion;


import sic.Dominio.Core.Documento.Documento;

/**
 *
 * @author FANNY BURGOS
 */
public class LiquidacionDePaddy extends Documento{
    private int tiquete;
    private double bultos;
    private double humedad_pact;
    private double impureza_pact;
    private double rojo_pact;
    private double partido_pact;
    private double yeso_pact;
    private double harina_pact;
    private double humedad_lab;
    private double impureza_lab;
    private double rojo_lab;
    private double partido_lab;
    private double yeso_lab;
    private double harina_lab;
    private double pesobruto;
    private double pesodestarado;
    private double peso_liquidado;
    private double peso14_1;
    private double valor_kg;
    private double valor_liquidado;
    private double valor_compra;
    private double fomento;
    private double bolsa;
    private double retefuente;
    private double ajuste;
    private double total;
    private boolean polipropileno;        
    private Object idcta_t_usuario;
    private double masablanca;
    private String aux_proveedor;
    private String aux_descargue;
    private double valor_descargue;
    
 

    

    public LiquidacionDePaddy(int tiquete, double bultos, double humedad_pact, double impureza_pact, double rojo_pact, double partido_pact, double yeso_pact, double harina_pact, double humedad_lab, double impureza_lab, double rojo_lab, double partido_lab, double yeso_lab, double harina_lab, double pesobruto, double pesodestarado, double peso_liquidado, double peso14_1, double valor_kg, double valor_liquidado, double valor_compra, double fomento, double bolsa, double retefuente, double ajuste, double total, boolean polipropileno, Object idcta_t_usuario,double valor_descargue) {
        super();        
        this.tiquete = tiquete;
        this.bultos = bultos;
        this.humedad_pact = humedad_pact;
        this.impureza_pact = impureza_pact;
        this.rojo_pact = rojo_pact;
        this.partido_pact = partido_pact;
        this.yeso_pact = yeso_pact;
        this.harina_pact = harina_pact;
        this.humedad_lab = humedad_lab;
        this.impureza_lab = impureza_lab;
        this.rojo_lab = rojo_lab;
        this.partido_lab = partido_lab;
        this.yeso_lab = yeso_lab;
        this.harina_lab = harina_lab;
        this.pesobruto = pesobruto;
        this.pesodestarado = pesodestarado;
        this.peso_liquidado = peso_liquidado;
        this.peso14_1 = peso14_1;
        this.valor_kg = valor_kg;
        this.valor_liquidado = valor_liquidado;
        this.valor_compra = valor_compra;
        this.fomento = fomento;
        this.bolsa = bolsa;
        this.retefuente = retefuente;
        this.ajuste = ajuste;
        this.total = total;
        this.polipropileno = polipropileno;
        this.idcta_t_usuario = idcta_t_usuario;        
        this.valor_descargue=valor_descargue;
    }
    


    public LiquidacionDePaddy() {
             super();
    }



   


    @Override
    public String getTdocumento() {
        return "Liquidacion de Paddy";
    }
    

    @Override
    public String getAbreviatura() {
        return "LP";
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
     * @return the bultos
     */
    public double getBultos() {
        return bultos;
    }

    /**
     * @param bultos the bultos to set
     */
    public void setBultos(double bultos) {
        this.bultos = bultos;
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
     * @return the ajuste
     */
    public double getAjuste() {
        return ajuste;
    }

    /**
     * @param ajuste the ajuste to set
     */
    public void setAjuste(double ajuste) {
        this.ajuste = ajuste;
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
     * @return the polipropileno
     */
    public boolean isPolipropileno() {
        return polipropileno;
    }

    /**
     * @param polipropileno the polipropileno to set
     */
    public void setPolipropileno(boolean polipropileno) {
        this.polipropileno = polipropileno;
    }

    /**
     * @return the idcta_t_usuario
     */
    public Object getIdcta_t_usuario() {
        return idcta_t_usuario;
    }

    /**
     * @param idcta_t_usuario the idcta_t_usuario to set
     */
    public void setIdcta_t_usuario(Object idcta_t_usuario) {
        this.idcta_t_usuario = idcta_t_usuario;
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
    public String getProveedor(){
        return this.getUsuario().NombreCompleto();
    }
    public long getNodocumento(){
       return  this.getUsuario().getNoDocumento();
    }
    public String getEstado(){
        if(this.isActivo()==false){
            return "ANULADA";
        }else{
            return "";
        }
    }

    /**
     * @return the aux_proveedor
     */
    public String getAux_proveedor() {
        return aux_proveedor;
    }

    /**
     * @param aux_proveedor the aux_proveedor to set
     */
    public void setAux_proveedor(String aux_proveedor) {
        this.aux_proveedor = aux_proveedor;
    }

    /**
     * @return the aux_descargue
     */
    public String getAux_descargue() {
        return aux_descargue;
    }

    /**
     * @param aux_descargue the aux_descargue to set
     */
    public void setAux_descargue(String aux_descargue) {
        this.aux_descargue = aux_descargue;
    }

    /**
     * @return the valor_descargue
     */
    public double getValor_descargue() {
        return valor_descargue;
    }

    /**
     * @param valor_descargue the valor_descargue to set
     */
    public void setValor_descargue(double valor_descargue) {
        this.valor_descargue = valor_descargue;
    }
}

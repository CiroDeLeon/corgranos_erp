/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Servicios;


import sic.Dominio.DomainObject;

/**
 *
 * @author FANNY BURGOS
 */
public class DataConfiguration extends DomainObject{
    private String Aux_Clientes;
    private String Aux_Iva;
    private String Aux_Proveedor;
    private String Aux_Paddy;
    private String Aux_Retefuente;
    private String Aux_Caja; 
    private String Aux_BolsaPorPagar;
    private String Aux_GastoBolsa;
    private double PorcentageBolsa;
    private String Aux_FomentoPorPagar;
    private String Aux_GastoFomento;
    private double PorcentageFomento;
    private Object idUsuarioProduccion;
    private double PorcentageRetefuente;
    public static int configuracion=1;
    private String dian1;
    private String dian2;
    private String Aux_Trilla;
    private String aux_descargues;

    public DataConfiguration(String Aux_Clientes, String Aux_Iva, String Aux_Proveedor, String Aux_Paddy, String Aux_Retefuente, String Aux_Caja, String Aux_BolsaPorPagar, String Aux_GastoBolsa, double PorcentageBolsa, String Aux_FomentoPorPagar, String Aux_GastoFomento, double PorcentageFomento, Object idUsuarioProduccion,double PorcentageRetefuente,String dian1,String dian2,String Aux_Trilla,String aux_descargues) {
        this.setId(DataConfiguration.configuracion);
        this.Aux_Clientes = Aux_Clientes;
        this.Aux_Iva = Aux_Iva;
        this.Aux_Proveedor = Aux_Proveedor;
        this.Aux_Paddy = Aux_Paddy;
        this.Aux_Retefuente = Aux_Retefuente;
        this.Aux_Caja = Aux_Caja;
        this.Aux_BolsaPorPagar = Aux_BolsaPorPagar;
        this.Aux_GastoBolsa = Aux_GastoBolsa;
        this.PorcentageBolsa = PorcentageBolsa;
        this.Aux_FomentoPorPagar = Aux_FomentoPorPagar;
        this.Aux_GastoFomento = Aux_GastoFomento;
        this.PorcentageFomento = PorcentageFomento;
        this.idUsuarioProduccion = idUsuarioProduccion;
        this.PorcentageRetefuente=PorcentageRetefuente;
        this.dian1=dian1;
        this.dian2=dian2;
        this.Aux_Trilla=Aux_Trilla;
        this.aux_descargues=aux_descargues;
    }


    public DataConfiguration() {
        this.setId(DataConfiguration.configuracion);
    }
    

   


    /**
     * @return the Aux_Clientes
     */
    public String getAux_Clientes() {
        return Aux_Clientes;
    }

    /**
     * @param Aux_Clientes the Aux_Clientes to set
     */
    public void setAux_Clientes(String Aux_Clientes) {
        this.Aux_Clientes = Aux_Clientes;
    }

    /**
     * @return the Aux_Iva
     */
    public String getAux_Iva() {
        return Aux_Iva;
    }

    /**
     * @param Aux_Iva the Aux_Iva to set
     */
    public void setAux_Iva(String Aux_Iva) {
        this.Aux_Iva = Aux_Iva;
    }

    /**
     * @return the Aux_Proveedor
     */
    public String getAux_Proveedor() {
        return Aux_Proveedor;
    }

    /**
     * @param Aux_Proveedor the Aux_Proveedor to set
     */
    public void setAux_Proveedor(String Aux_Proveedor) {
        this.Aux_Proveedor = Aux_Proveedor;
    }

    /**
     * @return the Aux_Paddy
     */
    public String getAux_Paddy() {
        return Aux_Paddy;
    }

    /**
     * @param Aux_Paddy the Aux_Paddy to set
     */
    public void setAux_Paddy(String Aux_Paddy) {
        this.Aux_Paddy = Aux_Paddy;
    }

    /**
     * @return the Aux_Retefuente
     */
    public String getAux_Retefuente() {
        return Aux_Retefuente;
    }

    /**
     * @param Aux_Retefuente the Aux_Retefuente to set
     */
    public void setAux_Retefuente(String Aux_Retefuente) {
        this.Aux_Retefuente = Aux_Retefuente;
    }

    /**
     * @return the Aux_Caja
     */
    public String getAux_Caja() {
        return Aux_Caja;
    }

    /**
     * @param Aux_Caja the Aux_Caja to set
     */
    public void setAux_Caja(String Aux_Caja) {
        this.Aux_Caja = Aux_Caja;
    }

    /**
     * @return the Aux_BolsaPorPagar
     */
    public String getAux_BolsaPorPagar() {
        return Aux_BolsaPorPagar;
    }

    /**
     * @param Aux_BolsaPorPagar the Aux_BolsaPorPagar to set
     */
    public void setAux_BolsaPorPagar(String Aux_BolsaPorPagar) {
        this.Aux_BolsaPorPagar = Aux_BolsaPorPagar;
    }

    /**
     * @return the Aux_GastoBolsa
     */
    public String getAux_GastoBolsa() {
        return Aux_GastoBolsa;
    }

    /**
     * @param Aux_GastoBolsa the Aux_GastoBolsa to set
     */
    public void setAux_GastoBolsa(String Aux_GastoBolsa) {
        this.Aux_GastoBolsa = Aux_GastoBolsa;
    }

    /**
     * @return the PorcentageBolsa
     */
    public double getPorcentageBolsa() {
        return PorcentageBolsa;
    }

    /**
     * @param PorcentageBolsa the PorcentageBolsa to set
     */
    public void setPorcentageBolsa(double PorcentageBolsa) {
        this.PorcentageBolsa = PorcentageBolsa;
    }

    /**
     * @return the Aux_FomentoPorPagar
     */
    public String getAux_FomentoPorPagar() {
        return Aux_FomentoPorPagar;
    }

    /**
     * @param Aux_FomentoPorPagar the Aux_FomentoPorPagar to set
     */
    public void setAux_FomentoPorPagar(String Aux_FomentoPorPagar) {
        this.Aux_FomentoPorPagar = Aux_FomentoPorPagar;
    }

    /**
     * @return the Aux_GastoFomento
     */
    public String getAux_GastoFomento() {
        return Aux_GastoFomento;
    }

    /**
     * @param Aux_GastoFomento the Aux_GastoFomento to set
     */
    public void setAux_GastoFomento(String Aux_GastoFomento) {
        this.Aux_GastoFomento = Aux_GastoFomento;
    }

    /**
     * @return the PorcentageFomento
     */
    public double getPorcentageFomento() {
        return PorcentageFomento;
    }

    /**
     * @param PorcentageFomento the PorcentageFomento to set
     */
    public void setPorcentageFomento(double PorcentageFomento) {
        this.PorcentageFomento = PorcentageFomento;
    }

    /**
     * @return the idUsuarioProduccion
     */
    public Object getIdUsuarioProduccion() {
        return idUsuarioProduccion;
    }

    /**
     * @param idUsuarioProduccion the idUsuarioProduccion to set
     */
    public void setIdUsuarioProduccion(Object idUsuarioProduccion) {
        this.idUsuarioProduccion = idUsuarioProduccion;
    }

    /**
     * @return the PorcentageRetefuente
     */
    public double getPorcentageRetefuente() {
        return PorcentageRetefuente;
    }

    /**
     * @param PorcentageRetefuente the PorcentageRetefuente to set
     */
    public void setPorcentageRetefuente(double PorcentageRetefuente) {
        this.PorcentageRetefuente = PorcentageRetefuente;
    }

    /**
     * @return the dian1
     */
    public String getDian1() {
        return dian1;
    }

    /**
     * @param dian1 the dian1 to set
     */
    public void setDian1(String dian1) {
        this.dian1 = dian1;
    }

    /**
     * @return the dian2
     */
    public String getDian2() {
        return dian2;
    }

    /**
     * @param dian2 the dian2 to set
     */
    public void setDian2(String dian2) {
        this.dian2 = dian2;
    }

    /**
     * @return the Aux_Trilla
     */
    public String getAux_Trilla() {
        return Aux_Trilla;
    }

    /**
     * @param Aux_Trilla the Aux_Trilla to set
     */
    public void setAux_Trilla(String Aux_Trilla) {
        this.Aux_Trilla = Aux_Trilla;
    }

    /**
     * @return the aux_descargues
     */
    public String getAux_descargues() {
        return aux_descargues;
    }

    /**
     * @param aux_descargues the aux_descargues to set
     */
    public void setAux_descargues(String aux_descargues) {
        this.aux_descargues = aux_descargues;
    }
    
}
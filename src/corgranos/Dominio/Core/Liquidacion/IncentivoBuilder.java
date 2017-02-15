/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package corgranos.Dominio.Core.Liquidacion;

import corgranos.Dominio.Servicios.DataConfiguration;
import corgranos.Dominio.Servicios.DataConfigurationService;

/**
 *
 * @author Usuario1
 */
public class IncentivoBuilder implements ILiquidacionDePaddyBuilder{
    LiquidacionDePaddy lp;
    boolean bolsa;

    public IncentivoBuilder() {
        lp=null;
    }
    public IncentivoBuilder(LiquidacionDePaddy lp) {
        this.lp = lp;
    }
    @Override
    public void setLiquidacionDePaddy(LiquidacionDePaddy lp) {
        this.lp=lp;
    }
    @Override
    public LiquidacionDePaddy BuildLiquidacion() {
        if(lp!=null){
        this.AsignarPesoDestarado();
        this.AsignarPesoLiquidado();
        this.AsignarPeso14_1();
        this.AsignarValorLiquidadoKg();
        this.AsignarValorCompra();
        this.AsignarImpuestos();
        this.AsignarTotal();        
        this.AsignarMasaBlanca();
        }
        return lp;
    }
    
    void AsignarPesoDestarado(){
        if(lp.isPolipropileno()==false){
            lp.setPesodestarado(Math.round(lp.getPesobruto()-lp.getBultos()));            
        }else{
            lp.setPesodestarado(Math.round(lp.getPesobruto()-lp.getBultos()*0.25));
        }
    }
    void AsignarPesoLiquidado(){
       double Hum,Imp;
        if(lp.getHumedad_lab()<lp.getHumedad_pact())
            Hum=1;
        else
            Hum=(100-lp.getHumedad_lab())/(100-lp.getHumedad_pact());
        if(lp.getImpureza_lab()<lp.getImpureza_pact())
            Imp=1;
        else
            Imp=(100-lp.getImpureza_lab())/(100-lp.getImpureza_pact());
        
        double vr=Math.round(lp.getValor_kg()-lp.getValor_kg()*(EvaluarEnPaddy(lp.getRojo_lab(),lp.getRojo_pact())+EvaluarEnPaddy(lp.getYeso_lab(),lp.getYeso_pact())+EvaluarEnPaddy(lp.getPartido_lab(),lp.getPartido_pact())+this.EvaluarEnPaddyHarina(lp.getHarina_lab(),lp.getHarina_pact())));        
        System.out.println("vr="+vr);
        System.out.println("peso="+Math.round(lp.getPesodestarado()*Hum*Imp));
        double saldo=lp.getValor_kg()*Math.round(lp.getPesodestarado()*Hum*Imp)-vr*Math.round(lp.getPesodestarado()*Hum*Imp);
        System.out.println("saldo="+saldo);
        double desc=saldo/Math.round(lp.getValor_kg());
        lp.setPeso_liquidado(Math.round((lp.getPesodestarado()*Hum*Imp)-desc));
    }
    void AsignarPeso14_1(){
        double ph=(lp.getHumedad_lab()-14)/100;
        if(ph<0){
            ph=0;
        }
        double pi=(lp.getImpureza_lab()-1)/100;
        if(pi<0){
            pi=0;
        }
        double p=ph+pi;
        lp.setPeso14_1(Math.round(lp.getPesodestarado()-lp.getPesodestarado()*p));
    }
    void AsignarValorLiquidadoKg(){
        double vr=Math.round(lp.getValor_kg());
        lp.setValor_liquidado(vr);
    }
    void AsignarValorCompra(){
        lp.setValor_compra(lp.getValor_liquidado()*lp.getPeso_liquidado());
    }
    void AsignarImpuestos(){
        DataConfigurationService dcs=new DataConfigurationService();
        DataConfiguration dc=dcs.getDao().ObtenerConfiguracion();
        if(bolsa==true){
            lp.setRetefuente(0);
            lp.setBolsa(Math.round(lp.getValor_compra()*dc.getPorcentageBolsa()));                
        }else{
            lp.setRetefuente(Math.round(lp.getValor_compra()*dc.getPorcentageRetefuente()));
            lp.setBolsa(0);                
        }
        lp.setFomento(Math.round(lp.getValor_compra()*dc.getPorcentageFomento()));
    }
    void AsignarTotal(){
        lp.setTotal(lp.getValor_compra()-lp.getBolsa()-lp.getRetefuente()-lp.getFomento());
    }
    
    
    // Evaluar En Paddy
    public double EvaluarEnPaddy(double lab,double tol){
       if(lab<tol)
           return 0;
       else
           return (lab-tol)*0.005;
   }
    // Evaluar En Paddy
    public double EvaluarEnPaddyHarina(double lab,double tol){
       if(lab<tol)
           return 0;
       else
           return (lab-tol)*0.0015;
   }
    
    void AsignarMasaBlanca(){
       lp.setMasablanca(Math.round(((95-lp.getHumedad_lab()-lp.getImpureza_lab())*lp.getPesodestarado())/100));           
    }

    @Override
    public void setBolsa(boolean bolsa) {
        this.bolsa=bolsa;
    }
}


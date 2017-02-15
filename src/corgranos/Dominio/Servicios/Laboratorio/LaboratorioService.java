/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package corgranos.Dominio.Servicios.Laboratorio;

import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddy;
import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddyDTO;
import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddyPorMaquilaDTO;
import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddyService;
import corgranos.Dominio.Core.Maquila.Maquila;
import corgranos.Dominio.Core.Maquila.MaquilaService;
import corgranos.Dominio.Servicios.Inventario.InventarioResumidoPorMaquila;
import corgranos.Dominio.Servicios.Inventario.InventarioService;
import java.util.Iterator;

/**
 *
 * @author Usuario1
 */
public class LaboratorioService {
    public double porcentaje_real_de_partido(double bultos_de_arroz,double bultos_de_partido,double porcentaje_de_partido){
               
        double relacion=(bultos_de_arroz)/(bultos_de_partido);
        double kilos_por_relacion=45*relacion;
        double kilos_de_partido_por_relacion=(kilos_por_relacion*porcentaje_de_partido);        
        double arroz_sin_partido=kilos_por_relacion-kilos_de_partido_por_relacion;
        double kilos_solo_partido=50+kilos_de_partido_por_relacion;
        double total_kilos=arroz_sin_partido+kilos_solo_partido;
        double porcentaje_partido=(kilos_solo_partido*100)/total_kilos;
        return porcentaje_partido;
    }
    public void Calcula(){
        LiquidacionDePaddyService ls=new LiquidacionDePaddyService();
        InventarioService is=new InventarioService();
        Iterator<Maquila> it=new MaquilaService().getMaquilaDao().ObtenerMaquilasCerradas("").iterator();                
        int registros=0;
        int registros_erroneos=0;
        double error=0;
        while(it.hasNext()){       
           double suma_liquidacion=0;
           double suma_partido=0;  
           Maquila m=it.next();
           if(m.getId().toString().equals("1")==false){
              Iterator<LiquidacionDePaddyPorMaquilaDTO> it2=ls.getPaddydao().ObtenerLiquidacionesDePaddy(m.getId()).iterator();
              registros=registros+1;
              while(it2.hasNext()){
                 LiquidacionDePaddyPorMaquilaDTO lp=it2.next();
                 double cantidad=lp.getPesodestarado();
                 suma_liquidacion+=cantidad;
                 double partido=lp.getPesodestarado()*(lp.getPartido()/100);
                 suma_partido+=partido;               
              }
              double porcentaje_en_laboratorio=(suma_partido*100)/suma_liquidacion;
              Iterator<InventarioResumidoPorMaquila> it3=is.ObtenerResumidoPorMaquila(m.getId()).iterator();
              double suma_arroz=0;
              double suma_partido_=0;
              while(it3.hasNext()){
                  InventarioResumidoPorMaquila rep=it3.next();
                  if(rep.getIdarticulo().equals("87")){
                      suma_arroz+=rep.getProducciones();
                  }
                  if(rep.getIdarticulo().equals("89") || rep.getIdarticulo().equals("112")){
                      suma_partido_+=rep.getProducciones();
                  }
              }
              double porcentaje_real=this.porcentaje_real_de_partido(suma_arroz,suma_partido_,0.10);
              if(porcentaje_real>porcentaje_en_laboratorio){
                 System.out.println(">>>Maquila="+m.getId()+" porcentaje_lab="+porcentaje_en_laboratorio+" porcentaje real="+porcentaje_real);                 
                 registros_erroneos=registros_erroneos+1;
                 error+=porcentaje_real-porcentaje_en_laboratorio;
              }else{
                 System.out.println("Maquila="+m.getId()+" porcentaje_lab="+porcentaje_en_laboratorio+" porcentaje real="+porcentaje_real); 
              }
           }
        }
        System.out.println("registros="+registros);
        System.out.println("erroneos="+registros_erroneos);
        System.out.println("error="+error/registros_erroneos);
    }
}

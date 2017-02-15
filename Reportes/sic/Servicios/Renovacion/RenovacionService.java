/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Servicios.Renovacion;

import sic.Infraestructura.JDBC.Impl.Mysql.RenovacionDAO;
import sic.InterfacesDAO.IRenovacionDAO;
import sic.Servicios.Reportes.DatosDeReporte;
import sic.Servicios.Reportes.ReporteService;

/**
 *
 * @author FANNY BURGOS
 */
public class RenovacionService {
   private IRenovacionDAO dao;
   private String mensaje="";    
   public RenovacionService() {
        dao=new RenovacionDAO();
    }
   public boolean ValidarRenovacion(Renovacion r){
       Renovacion res=getDao().ObtenerRenovacion(r.getAño(),r.getMes());
       if(res!=null){
           res.setClave(r.getClave());
           if(this.isValida(res)){
                getDao().ModificarRenovacion(res);
               this.mensaje="VALIDADO CORRECTAMENTE";
               return true;
           }else{
               this.mensaje="Clave Incorrecta";
               return false;
           }
       }else{
           if(this.isValida(r)){
                getDao().PersistirRenovacion(r);
               this.mensaje="VALIDADO CORRECTAMENTE";
               return true;
           }else{
               this.mensaje="Clave Incorrecta";
               return false;
           }
       }
   } 
   private String ObtenerClave(String nit, int año, int mes) {
        int n1=0+Integer.parseInt(nit.substring(0,5));
        int n2=0;        
        n2=0+Integer.parseInt(nit.substring(5,nit.length()));        
        int n=500+mes;
        int k=(n*(n+1))/2;
        int k2=((año-1900)*((año-1900)+1))/2;
        if(mes%2==0){
          n1=n1+k2+(mes*k);
          n2=n2-k2+(mes*k);          
        }else{
          n2=n2+k2+(mes*k);
          n1=n1-k2+(mes*k);  
        }
        long clave=Long.parseLong(n2+""+n1);        
        String r=(clave+"").replace('0','c').replace('1','i').replace('2','r').replace('3','o')
                .replace('4','m').replace('5','a').replace('6','n').replace('7','u').replace('8','e').replace('9','l').toUpperCase();
       
        return r;
    }    
   public boolean isValida(Renovacion r){
       DatosDeReporte dr=new ReporteService().getDao().ObtenerDatosDeReporte();
       return this.isValidaClave(""+dr.getNit(),r.getAño(),r.getMes(),r.getClave());
   }
   private boolean isValidaClave(String nit, int año, int mes,String clave){
       String key=clave.replace("-","").replace("_","").toUpperCase();       
       System.out.println("key="+key);
      if(this.ObtenerClave(nit, año, mes).equals(key))       {
          return true;
      }else{
          return false;
      }
   }
   public String getMensaje() {
        return mensaje;
    }

    /**
     * @return the dao
     */
    public IRenovacionDAO getDao() {
        return dao;
    }
}

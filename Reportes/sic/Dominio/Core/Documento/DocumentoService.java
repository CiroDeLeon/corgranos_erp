/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Dominio.Core.Documento;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;
import sic.InterfacesDAO.IDocumentoDAO;
import sic.Infraestructura.JDBC.Impl.Mysql.DocumentoDAO;

/**
 *
 * @author FANNY BURGOS
 */
public class DocumentoService {
   private IDocumentoDAO dao;
   protected String mensaje="";
   public DocumentoService() {
        dao=new DocumentoDAO();        
    }
   public Vector<TipoDocumentoContable> ObtenerTiposDeDocumentosContables(){
       return getDao().ObtenerTiposDeDocumentosContables();
   }
   public boolean Guardar(Documento d){
       long id=getDao().ObtenerIdUltimoDocumento()+1;
       d.setId(new Long(id).toString());
       int n=getDao().ObtenerNumeracion(d.getTdocumento(),d.getResolucion());
       if(n!=0){          
          d.setNumeracion(n+1);
       }
        if(getDao().PersistirDocumento(d)!=null){
            this.mensaje="Ingresado con Exito";
            Iterator <Asiento> it=d.getAsientos().iterator();
            int sw=0;
            while(it.hasNext()){
                Asiento a=it.next();
                a.setDocumento(d);
                dao.PersistirAsiento(a);
                if(a==null){
                    sw=1;
                }
            }
            if(sw==1){
                this.mensaje="Se Creo Con Errores";
            }
            return true;
        }
       return false;
   }
   public String ObtenerMensaje(){
       return this.mensaje;
   }
   public IDocumentoDAO getDao() {
        return dao;
    }
   public boolean HaySumasIguales(Vector<Asiento> asientos){
       Iterator <Asiento> it=asientos.iterator();
       double sumadebito=0;
       double sumacredito=0;
       while(it.hasNext()){
           Asiento a=it.next();
           sumadebito+=a.getDebito();
           sumacredito+=a.getCredito();
       }
       if(sumadebito==sumacredito){
           return true;   
       }
       return false;
   } 
   public double ObtenerSumaDebitos(Vector<Asiento> asientos){
       Iterator <Asiento> it=asientos.iterator();
       double sumadebito=0;       
       while(it.hasNext()){
           Asiento a=it.next();
           sumadebito+=a.getDebito();           
       }
       return sumadebito;       
   }
   public double ObtenerSumaCreditos(Vector<Asiento> asientos){
       Iterator <Asiento> it=asientos.iterator();
       double sumacredito=0;       
       while(it.hasNext()){
           Asiento a=it.next();
           sumacredito+=a.getCredito();           
       }
       return sumacredito; 
   }   
   public Documento ObtenerDocumento(String tipodocumento, int numeracion){
      Documento d=dao.ObtenerDocumento(tipodocumento, numeracion);
      if(d!=null)
          d.setAsientos(dao.ObtenerAsientos(d.getId()));
      return d;
   }
   public boolean Modificar(Documento d){
       if(dao.ModificarDocumento(d.getId(), d)){
       Iterator <Asiento> it=d.getAsientos().iterator();       
       while(it.hasNext()){           
           Asiento a=it.next();
           a.setDocumento(d);
           if(a.getId().equals("0")==false){
           if(dao.ModificarAsiento(a.getId(), a)==false){
               this.mensaje="Error al Modificar un Asiento";
               return false;
           }
           }else{
               a.setDocumento(d);
               if(dao.PersistirAsiento(a)==null){
                   this.mensaje="Error al Modificar un Asiento,No se Pudo Anexar";
                   return false;
               }
           }
       }
       this.mensaje="Moficado con Exito";
       return true;
       }
       return false;
   }
   public void OrdenarDocumentos(String tipodocumento){
       Vector<Documento> lista=dao.ObtenerDocumentos(tipodocumento);
       Collections.sort(lista);
       Iterator<Documento>it=lista.iterator();
       System.out.println(lista.size());
       int i=1;
       while(it.hasNext()){
           Documento d=it.next();
           System.out.println(""+d.getFechaContable());
           d.setNumeracion(i);
           this.dao.ModificarDocumento(d.getId(),d);
           i++;           
       }
   }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Servicios.Mantenimiento;

import corgranos.Infraestructura.JDBC.Impl.SincronizacionDAO;
import corgranos.InterfacesDAO.ISincronizacionDAO;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import sic.Dominio.Core.Documento.Documento;
import sic.Dominio.Core.Documento.DocumentoService;

/**
 *
 * @author FANNY BURGOS
 */
public class SincronizacionDeDocumentosContables implements Runnable{    
   ISincronizacionDAO dao;
   Thread t;
   public SincronizacionDeDocumentosContables() {
        dao=new SincronizacionDAO();
        Sincronizar();
        t=new Thread(this);
        t.start();
    }   
   @Override
   public void run() {
        int minutos=15;
        long time=minutos*60000;
        while(true){           
           try {
              Thread.sleep(time);
           }catch (InterruptedException ex) {
              Logger.getLogger(SincronizacionDeDocumentosContables.class.getName()).log(Level.SEVERE, null, ex);
           }
           this.Sincronizar();
        }
    }
   public void Sincronizar(){
       this.SincronizarLP();
       this.SincronizarFA();
       System.out.println("SINCRONIZADO");
   }    
   void SincronizarLP(){
      Iterator<LPDocumento> it=dao.ObtenerTodos().iterator();
      DocumentoService ds=new DocumentoService();
      while(it.hasNext()){
          LPDocumento lp=it.next();
          Documento d=ds.getDao().ObtenerDocumento(lp.getIdDocumento());
          if(d!=null){
             lp.setProveedor(d.getUsuario().NombreCompleto());
             lp.setNodocumento(d.getUsuario().getNoDocumentoCompleto());
             lp.setActivo(d.isActivo());
             lp.setFechacontable(d.getFechaContable());
             lp.setNumeracion(d.getNumeracion());
             lp.setIdCtaTUsuario(d.getUsuario().getId());
             dao.Modificar(lp);
             //System.out.println(lp.getIdDocumento());
          }
      }      
   }
   void SincronizarFA(){
      Iterator<FADocumento> it=dao.ObtenerTodas().iterator();
      DocumentoService ds=new DocumentoService();
      while(it.hasNext()){
          FADocumento fa=it.next();
          Documento d=ds.getDao().ObtenerDocumento(fa.getIdDocumento());
          if(d!=null){
             fa.setCliente(d.getUsuario().NombreCompleto());
             fa.setNodocumento(d.getUsuario().getNoDocumentoCompleto());
             fa.setActivo(d.isActivo());
             fa.setFechacontable(d.getFechaContable());
             fa.setNofactura(d.getNumeracion());
             fa.setIdCtaTUsuario(d.getUsuario().getId());
             fa.setUbicacion(d.getUsuario().getUbicacionCompleta());
             dao.Modificar(fa);
             //System.out.println(fa.getIdDocumento());
          }
      }      
   }
}

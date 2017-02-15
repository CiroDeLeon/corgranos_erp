/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import sic.Dominio.Core.PUC.Cta_PUC;
import sic.Dominio.Core.PUC.PucService;
import sic.Infraestructura.JDBC.Pool;

/**
 *
 * @author FANNY BURGOS
 */
public class TrasladoDeSaldo {
   public void TraslasdoDeSaldo(){
       
   }   
   void Trasladar(){
       PucService ps=new PucService();
       Iterator<Cta_PUC> it=ps.getDao().InspeccionarAuxiliar("","22050512").iterator();
       while(it.hasNext()){
           Cta_PUC cta=it.next();
           this.ModificarCta("22050501"+cta.getCtat().getId().toString(),"22050512"+cta.getCtat().getId().toString());
       }
   }
   void ModificarCta(Object CtaOrigen,Object CtaDestino){         
            String sql ="update asiento set asiento.idctapuc='"+CtaDestino+"' where(asiento.idctapuc='"+CtaOrigen+"') ";
                  
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                                                
            ps.executeUpdate();                        
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }finally{
            sic.Infraestructura.JDBC.Pool.LiberarConexion(con);
        }           
   }
}

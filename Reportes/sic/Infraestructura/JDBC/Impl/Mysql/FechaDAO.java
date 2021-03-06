/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Infraestructura.JDBC.Impl.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sic.Infraestructura.JDBC.Pool;
import sic.InterfacesDAO.IFechaDAO;

/**
 *
 * @author FANNY BURGOS
 */
public class FechaDAO implements IFechaDAO{

    @Override
    public Date ObtenerMenorFecha() {
        Connection con=null;
        String sql ="select MIN(fechacontable) from documento \n";               
               
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);            
            ResultSet rs=ps.executeQuery();                        
            if(rs.next()){
               java.sql.Date d=rs.getDate(1);   
               return new java.util.Date(d.getTime());
            }            
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ContabilidadDAO.class.getName()).log(Level.SEVERE, null, ex);             
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public Date ObtenerFechaDelServidor() {
        Connection con=null;
        String sql ="select curdate() \n";               
               
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);            
            ResultSet rs=ps.executeQuery();                        
            if(rs.next()){
               java.sql.Date d=rs.getDate(1);   
               return new java.util.Date(d.getTime());
            }            
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ContabilidadDAO.class.getName()).log(Level.SEVERE, null, ex);             
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    
}

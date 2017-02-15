/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Infraestructura.JDBC.Impl;

import corgranos.Servicios.Mantenimiento.FADocumento;
import corgranos.Servicios.Mantenimiento.LPDocumento;
import corgranos.Infraestructura.JDBC.Pool;
import corgranos.InterfacesDAO.ISincronizacionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FANNY BURGOS
 */
public class SincronizacionDAO implements ISincronizacionDAO{

    @Override
    public Vector<LPDocumento> ObtenerTodos() {
        String sql ="select iddocumento,idcta_t_usuario, ";
               sql+="numeracion,proveedor,nodocumento,fechacontable,activo ";  
               sql+="from liquidaciondepaddy ";
        Connection con=null; 
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            Vector <LPDocumento> lista=new Vector <LPDocumento>();
            while(rs.next()){
                LPDocumento lp=new LPDocumento();
                lp.setIdDocumento(rs.getObject(1));
                lp.setIdCtaTUsuario(rs.getObject(2));
                lp.setNumeracion(rs.getInt(3));
                lp.setProveedor(rs.getString(4));
                lp.setNodocumento(rs.getString(5));
                lp.setFechacontable(new java.util.Date(rs.getDate(6).getTime()));
                lp.setActivo(rs.getBoolean(7));
                lista.add(lp);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(SincronizacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public LPDocumento Modificar(LPDocumento lp) {
            String sql ="update liquidaciondepaddy set ";
                   sql+="iddocumento="+lp.getIdDocumento()+",idcta_t_usuario="+lp.getIdCtaTUsuario()+", "; 
                   sql+="numeracion=?, proveedor=?,nodocumento="+lp.getNodocumento()+",fechacontable=?,activo=? "; 
                   sql+="where(iddocumento="+lp.getIdDocumento()+") ";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                                    
            ps.setInt(1,lp.getNumeracion());
            ps.setString(2,lp.getProveedor());
            
            ps.setDate(3,new java.sql.Date(lp.getFechacontable().getTime()));
            ps.setBoolean(4,lp.isActivo());
            ps.executeUpdate();                        
            return lp;
        } catch (SQLException ex) {
            Logger.getLogger(MaquilaDAO.class.getName()).log(Level.SEVERE, null, ex);            
            return null;
        }finally{
            sic.Infraestructura.JDBC.Pool.LiberarConexion(con);
        }       
    }

    @Override
    public Vector<FADocumento> ObtenerTodas() {
        String sql ="select iddocumento,idcta_t_usuario, ";
               sql+="nofactura,cliente,nodocumento,fechacontable,activa,ubicacion ";  
               sql+="from factura ";
        Connection con=null; 
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            Vector <FADocumento> lista=new Vector <FADocumento>();
            while(rs.next()){
                FADocumento fa=new FADocumento();
                fa.setIdDocumento(rs.getObject(1));
                fa.setIdCtaTUsuario(rs.getObject(2));
                fa.setNofactura(rs.getInt(3));
                fa.setCliente(rs.getString(4));
                fa.setNodocumento(rs.getString(5));
                fa.setFechacontable(new java.util.Date(rs.getDate(6).getTime()));
                fa.setActivo(rs.getBoolean(7));
                fa.setUbicacion(rs.getString(8));
                lista.add(fa);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(SincronizacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public FADocumento Modificar(FADocumento fa) {
        String sql ="update factura set ";
                   sql+="iddocumento="+fa.getIdDocumento()+",idcta_t_usuario="+fa.getIdCtaTUsuario()+", "; 
                   sql+="nofactura=?,cliente=?,nodocumento=?,fechacontable=?,activa=?,ubicacion=? "; 
                   sql+="where(iddocumento="+fa.getIdDocumento()+") ";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                                    
            ps.setInt(1,fa.getNofactura());
            ps.setString(2,fa.getCliente());
            ps.setString(3,fa.getNodocumento());
            ps.setDate(4,new java.sql.Date(fa.getFechacontable().getTime()));
            ps.setBoolean(5,fa.isActivo());
            ps.setString(6,fa.getUbicacion());
            ps.executeUpdate();                        
            return fa;
        } catch (SQLException ex) {
            Logger.getLogger(MaquilaDAO.class.getName()).log(Level.SEVERE, null, ex);            
            return null;
        }finally{
            sic.Infraestructura.JDBC.Pool.LiberarConexion(con);
        }   
    }
    
}

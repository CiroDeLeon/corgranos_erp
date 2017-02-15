/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Infraestructura.JDBC.Impl.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import sic.Dominio.Servicios.Dian.RetencionDian;
import sic.Infraestructura.JDBC.Pool;
import sic.InterfacesDAO.IDianDAO;

/**
 *
 * @author FANNY BURGOS
 */
public class DianDAO implements IDianDAO{
    @Override
    public Vector<RetencionDian> ObtenerTablaDeRetenciones(int año) {
        Connection con=null;
        String sql =" select descripcion,base,porcentage,idauxiliar,idretenciondian ";
               sql+=" from retenciondian where(ano="+año+")  ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);            
            ResultSet rs=ps.executeQuery();
            Vector <RetencionDian> lista=new Vector <RetencionDian>();
            while(rs.next()){                
                RetencionDian rd=new RetencionDian();
                rd.setDescripcion(rs.getString(1));
                rd.setBase(rs.getDouble(2));
                rd.setPorcentage(rs.getDouble(3));
                rd.setIdauxiliar(rs.getString(4));
                rd.setAño(año);
                rd.setId(rs.getString(5));
                lista.add(rd);                
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DianDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public RetencionDian AgregarRegistroEnTablaDeRetenciones(RetencionDian rd) {
          String sql =" insert into retenciondian ";
               sql+=" (ano,descripcion,base,porcentage,idauxiliar) values ";
               sql+=" (?,?,?,?,'"+rd.getIdauxiliar()+"')";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                        
            ps.setInt(1,rd.getAño());
            ps.setString(2,rd.getDescripcion());
            ps.setDouble(3,rd.getBase());
            ps.setDouble(4,rd.getPorcentage());            
            ps.executeUpdate();
            return rd;
        } catch (SQLException ex) {
            Logger.getLogger(DianDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public RetencionDian ModificarRegistroEnTablaDeRetenciones(Object id, RetencionDian rd) {
        String sql =" update retenciondian set ";
               sql+=" ano=?,descripcion=?,base=?,porcentage=?,idauxiliar='"+rd.getIdauxiliar()+"'";
               sql+=" where(idretenciondian="+id+") ";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                        
            ps.setInt(1,rd.getAño());
            ps.setString(2,rd.getDescripcion());
            ps.setDouble(3,rd.getBase());
            ps.setDouble(4,rd.getPorcentage());            
            ps.executeUpdate();
            return rd;
        } catch (SQLException ex) {
            Logger.getLogger(DianDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public RetencionDian ObtenerRetencionDian(Object idAuxiliar) {
        Connection con=null;
        String sql =" select descripcion,base,porcentage,idauxiliar,idretenciondian,ano ";
               sql+=" from retenciondian where(idauxiliar='"+idAuxiliar+"')order by ano Desc  ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);            
            ResultSet rs=ps.executeQuery();            
            if(rs.next()){                
                RetencionDian rd=new RetencionDian();
                rd.setDescripcion(rs.getString(1));
                rd.setBase(rs.getDouble(2));
                rd.setPorcentage(rs.getDouble(3));
                rd.setIdauxiliar(rs.getString(4));                
                rd.setId(rs.getString(5));
                rd.setAño(rs.getInt(6));
                return rd;   
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DianDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
}

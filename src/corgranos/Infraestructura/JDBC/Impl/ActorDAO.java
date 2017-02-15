/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Infraestructura.JDBC.Impl;

import corgranos.Servicios.Login.Actor;
import corgranos.Infraestructura.JDBC.Pool;
import corgranos.InterfacesDAO.IActorDAO;
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
public class ActorDAO implements IActorDAO{

    @Override
    public Actor PersistirActor(Actor a) {
        String sql =" insert into actor ";
               sql+=" (idactor,login,clave,rol,nombre,activo) ";               
               sql+=" values ";
               sql+=" ("+a.getId()+",?,?,?,?,?)";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                        
            ps.setString(1,a.getLogin());
            ps.setString(2,a.getClave());
            ps.setString(3,a.getRol());
            ps.setString(4,a.getNombre());
            ps.setBoolean(5,a.isActivo());            
            ps.executeUpdate();
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(ActorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public Actor ObtenerActor(Object idActor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Actor> BuscarActores(String CampoBusqueda) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Actor ModificarActor(Object idActor, Actor actor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Actor ObtenerActor(String login) {
        Connection con=null;
        String sql ="select  ";     
               sql+=" idactor,";
               sql+=" login,";
               sql+=" clave,";
               sql+=" rol,";
               sql+=" nombre,";
               sql+=" activo";               
               sql+=" from   actor ";
               sql+="where( ";               
               sql+="   activo=true and      ";               
               sql+="   login=?      ";               
               sql+=" ) limit 1 ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql); 
            ps.setString(1,login);
            ResultSet rs=ps.executeQuery();            
            if(rs.next()){
                Actor a=new Actor();
                a.setId(rs.getObject(1));
                a.setLogin(rs.getString(2));
                a.setClave(rs.getString(3));
                a.setRol(rs.getString(4));
                a.setNombre(rs.getString(5));
                a.setActivo(rs.getBoolean(6));               
                return a;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Infraestructura.JDBC.Impl;

import corgranos.Dominio.Core.Articulo.Articulo;
import corgranos.Infraestructura.JDBC.Pool;
import corgranos.InterfacesDAO.IArticuloDAO;
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
public class ArticuloDAO implements IArticuloDAO{

    @Override
    public Articulo PersistirArticulo(Articulo a) {
               String sql =" insert into articulo ";
               sql+=" (idcta_t,nombre,referencia,cantidadkg,ingreso_aux,activo_aux, ";
               sql+=" costos_aux,tipo,categoria,preciounitario,porcentageiva,codigodebarra,activo,iva_aux,empaque_aux) ";
               sql+=" values ";
               sql+=" ("+a.getId()+",?,?,?,?,?,?,?,?,?,?,?,true,?,?)";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                        
            ps.setString(1,a.getNombre());
            ps.setString(2,a.getReferencia());
            ps.setInt(3,a.getCantidadkg());
            ps.setString(4,a.getAuxingreso());
            ps.setString(5,a.getAuxactivo());
            ps.setString(6,a.getAuxcosto());
            ps.setString(7,a.getTipo());
            ps.setString(8,a.getCategoria());
            ps.setDouble(9,a.getPreciounitario());
            ps.setDouble(10,a.getPorcentageiva());            
            ps.setLong(11,a.getCodigodebarra());
            ps.setString(12,a.getAuxiva());
            ps.setString(13,a.getAuxempaque());
            ps.executeUpdate();
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector ObtenerCategorias() {
        String sql =" select distinct categoria from articulo group by categoria";               
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            Vector lista=new Vector();
            while(rs.next()){                                
                lista.add(rs.getString(1));                
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<Articulo> ObtenerArticulos(String busqueda) {
         Connection con=null;
        String sql ="select  ";     
               sql+=" idcta_t,";
               sql+=" nombre,";
               sql+=" referencia,";
               sql+=" cantidadkg,";
               sql+=" ingreso_aux,";
               sql+=" activo_aux,";
               sql+=" costos_aux,";
               sql+=" tipo,";
               sql+=" categoria,";
               sql+=" preciounitario,";
               sql+=" porcentageiva,";
               sql+=" codigodebarra, ";
               sql+=" activo, ";               
               sql+=" iva_aux, ";
               sql+=" empaque_aux ";
               sql+=" from   articulo ";
               sql+="where( ";
               sql+="   activo=true and (    ";
               sql+="   idcta_t like  ? or    ";
               sql+="   CONCAT_WS(' ',nombre,referencia) like ? or    ";
               sql+="   nombre like  ? or    ";
               sql+="   referencia like  ? or    ";
               sql+="   categoria like  ? or    ";
               sql+="   tipo like ? or     ";               
               sql+="   codigodebarra like ?   )   ";               
               sql+=" ) order by codigodebarra ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,busqueda+"%");
            ps.setString(2,"%"+busqueda+"%");
            ps.setString(3,busqueda+"%");
            ps.setString(4,busqueda+"%");
            ps.setString(5,busqueda+"%");
            ps.setString(6,busqueda+"%");
            ps.setString(7,busqueda+"%");
            ResultSet rs=ps.executeQuery();
            Vector <Articulo> lista=new Vector <Articulo>();
            while(rs.next()){
                Articulo a=new Articulo();
                a.setId(rs.getObject(1));
                a.setNombre(rs.getString(2));
                a.setReferencia(rs.getString(3));
                a.setCantidadkg(rs.getInt(4));
                a.setAuxingreso(rs.getString(5));
                a.setAuxactivo(rs.getString(6));
                a.setAuxcosto(rs.getString(7));
                a.setTipo(rs.getString(8));
                a.setCategoria(rs.getString(9));
                a.setPreciounitario(rs.getDouble(10));
                a.setPorcentageiva(rs.getDouble(11));
                a.setCodigodebarra(rs.getLong(12));
                a.setActivo(rs.getBoolean(13));
                a.setAuxiva(rs.getString(14));
                a.setAuxempaque(rs.getString(15));
                lista.add(a);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Articulo ObtenerArticulo(long CodigoDeBarra) {
        Connection con=null;
        String sql ="select  ";     
               sql+=" idcta_t,";
               sql+=" nombre,";
               sql+=" referencia,";
               sql+=" cantidadkg,";
               sql+=" ingreso_aux,";
               sql+=" activo_aux,";
               sql+=" costos_aux,";
               sql+=" tipo,";
               sql+=" categoria,";
               sql+=" preciounitario,";
               sql+=" porcentageiva,";
               sql+=" codigodebarra, ";
               sql+=" activo , ";               
               sql+=" iva_aux ,";               
               sql+=" empaque_aux ";
               sql+=" from   articulo ";
               sql+="where( ";               
               sql+="   activo=true and      ";               
               sql+="   codigodebarra=?      ";               
               sql+=" ) order by idcta_t ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setLong(1,CodigoDeBarra);
            ResultSet rs=ps.executeQuery();            
            if(rs.next()){
                Articulo a=new Articulo();
                a.setId(rs.getObject(1));
                a.setNombre(rs.getString(2));
                a.setReferencia(rs.getString(3));
                a.setCantidadkg(rs.getInt(4));
                a.setAuxingreso(rs.getString(5));
                a.setAuxactivo(rs.getString(6));
                a.setAuxcosto(rs.getString(7));
                a.setTipo(rs.getString(8));
                a.setCategoria(rs.getString(9));
                a.setPreciounitario(rs.getDouble(10));
                a.setPorcentageiva(rs.getDouble(11));
                a.setCodigodebarra(rs.getLong(12));
                a.setActivo(rs.getBoolean(13));
                a.setAuxiva(rs.getString(14));
                a.setAuxempaque(rs.getString(15));
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
    @Override
    public Articulo ObtenerArticulo(Object idArticulo) {
        Connection con=null;
        String sql ="select  ";     
               sql+=" idcta_t,";
               sql+=" nombre,";
               sql+=" referencia,";
               sql+=" cantidadkg,";
               sql+=" ingreso_aux,";
               sql+=" activo_aux,";
               sql+=" costos_aux,";
               sql+=" tipo,";
               sql+=" categoria,";
               sql+=" preciounitario,";
               sql+=" porcentageiva,";
               sql+=" codigodebarra, ";
               sql+=" activo , ";               
               sql+=" iva_aux, ";               
               sql+=" empaque_aux ";
               sql+=" from   articulo ";
               sql+="where( ";               
               sql+="   activo=true and      ";               
               sql+="   idcta_t=?      ";               
               sql+=" ) order by idcta_t ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql); 
            ps.setObject(1,idArticulo);
            ResultSet rs=ps.executeQuery();            
            if(rs.next()){
                Articulo a=new Articulo();
                a.setId(rs.getObject(1));
                a.setNombre(rs.getString(2));
                a.setReferencia(rs.getString(3));
                a.setCantidadkg(rs.getInt(4));
                a.setAuxingreso(rs.getString(5));
                a.setAuxactivo(rs.getString(6));
                a.setAuxcosto(rs.getString(7));
                a.setTipo(rs.getString(8));
                a.setCategoria(rs.getString(9));
                a.setPreciounitario(rs.getDouble(10));
                a.setPorcentageiva(rs.getDouble(11));
                a.setCodigodebarra(rs.getLong(12));
                a.setActivo(rs.getBoolean(13));
                a.setAuxiva(rs.getString(14));
                a.setAuxempaque(rs.getString(15));
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
    @Override
    public Vector<Articulo> ObtenerArtticulosPorCategoria(String categoria) {
        Connection con=null;
        String sql =" select  ";     
               sql+=" idcta_t,";
               sql+=" nombre,";
               sql+=" referencia,";
               sql+=" cantidadkg,";
               sql+=" ingreso_aux,";
               sql+=" activo_aux,";
               sql+=" costos_aux,";
               sql+=" tipo,";
               sql+=" categoria,";
               sql+=" preciounitario,";
               sql+=" porcentageiva,";
               sql+=" codigodebarra, ";
               sql+=" activo, ";               
               sql+=" iva_aux, ";               
               sql+=" empaque_aux ";
               sql+=" from   articulo ";
               sql+="where( ";
               sql+="   activo=true and     ";                              
               sql+="   categoria=?    ";               
               sql+=" ) order by codigodebarra ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,categoria);
            ResultSet rs=ps.executeQuery();
            Vector <Articulo> lista=new Vector <Articulo>();
            while(rs.next()){
                Articulo a=new Articulo();
                a.setId(rs.getObject(1));
                a.setNombre(rs.getString(2));
                a.setReferencia(rs.getString(3));
                a.setCantidadkg(rs.getInt(4));
                a.setAuxingreso(rs.getString(5));
                a.setAuxactivo(rs.getString(6));
                a.setAuxcosto(rs.getString(7));
                a.setTipo(rs.getString(8));
                a.setCategoria(rs.getString(9));
                a.setPreciounitario(rs.getDouble(10));
                a.setPorcentageiva(rs.getDouble(11));
                a.setCodigodebarra(rs.getLong(12));
                a.setActivo(rs.getBoolean(13));
                a.setAuxiva(rs.getString(14));
                a.setAuxempaque(rs.getString(15));
                lista.add(a);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Articulo ModificarArticulo(Object id, Articulo a) {
        String sql =" update articulo set ";
               sql+=" nombre=?,referencia=?,cantidadkg=?,ingreso_aux=?,activo_aux=?, ";
               sql+=" costos_aux=?,tipo=?,categoria=?,preciounitario=?,porcentageiva=?,codigodebarra=?,activo=?,iva_aux=?,empaque_aux=? ";
               sql+=" where(idcta_t="+id+")";
               System.out.println(sql);
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                        
            ps.setString(1,a.getNombre());
            ps.setString(2,a.getReferencia());
            ps.setInt(3,a.getCantidadkg());
            ps.setString(4,a.getAuxingreso());
            ps.setString(5,a.getAuxactivo());
            ps.setString(6,a.getAuxcosto());
            ps.setString(7,a.getTipo());
            ps.setString(8,a.getCategoria());
            ps.setDouble(9,a.getPreciounitario());
            ps.setDouble(10,a.getPorcentageiva());            
            ps.setLong(11,a.getCodigodebarra());
            ps.setBoolean(12,a.isActivo());
            ps.setString(13,a.getAuxiva());
            ps.setString(14,a.getAuxempaque());
            ps.executeUpdate();
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    
}

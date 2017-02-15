/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Infraestructura.JDBC.Impl;

import corgranos.Dominio.Core.Articulo.Articulo;
import corgranos.Dominio.Core.Articulo.ArticuloService;
import corgranos.Dominio.Core.Maquila.Maquila;
import corgranos.Dominio.Core.Maquila.MaquilaService;
import corgranos.Dominio.Core.Produccion.Produccion;
import corgranos.Dominio.Core.Produccion.ProduccionDeArticulo;
import corgranos.Infraestructura.JDBC.Pool;
import corgranos.InterfacesDAO.IProduccionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FANNY BURGOS
 */
public class ProduccionDAO implements IProduccionDAO{

    @Override
    public Produccion PersistirProduccion(Produccion p) {
        String sql =" insert into produccion ";
               sql+=" (idproduccion,idmaquila,fechacontable,fechacreacion, ";
               sql+=" creador,activa) ";
               sql+=" values ";
               sql+=" ("+p.getId()+","+p.getMaquila().getId()+",?,?,?,true)";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                       
            ps.setDate(1,new java.sql.Date(p.getFechacontable().getTime()));
            ps.setDate(2,new java.sql.Date(p.getFechacreacion().getTime()));            
            ps.setString(3,p.getCreador());            
            ps.executeUpdate();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(ProduccionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public ProduccionDeArticulo PersistirProduccionDeArticulo(ProduccionDeArticulo pa) {
        String sql =" insert into producciondearticulo ";
               sql+=" (idproduccion,articulo_idcta_t,cantidad,preciounitario) ";               
               sql+=" values ";
               sql+=" ("+pa.getProduccion().getId()+","+pa.getArticulo().getId()+",?,?)";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                       
            ps.setDouble(1,pa.getCantidad());
            ps.setDouble(2,pa.getPreciounitario());
            ps.executeUpdate();
            return pa;
        } catch (SQLException ex) {
            Logger.getLogger(ProduccionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public long ObtenerIdUltimaProduccion() {
        Connection con=null;
        String sql="select Max(idproduccion) from produccion ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getLong(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProduccionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Produccion ObtenerProduccion(Object id) {
       String sql ="select ";
              sql+="idproduccion,";
              sql+="idmaquila, ";
              sql+="fechacontable, ";
              sql+="fechacreacion, ";
              sql+="fechaanulacion, ";
              sql+="creador, ";
              sql+="anulador, ";
              sql+="activa ";
              sql+="from produccion ";
              sql+="where( ";
              sql+="idproduccion="+id+" ";
              sql+=" )";
              Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql); 
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Produccion p=new Produccion();
                p.setId(id);
                MaquilaService mservice=new MaquilaService();
                Maquila m=mservice.getMaquilaDao().ObtenerMaquila(rs.getObject(2));
                p.setMaquila(m);
                p.setFechacontable(new Date(rs.getDate(3).getTime()));
                p.setFechacreacion(new Date(rs.getDate(4).getTime()));
                if(rs.getDate(5)!=null){
                    p.setFechaanulacion(new Date(rs.getDate(5).getTime()));
                    p.setAnulador(rs.getString(7));
                }
                p.setCreador(rs.getString(6));
                p.setActivo(rs.getBoolean(8));
                return p;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ProduccionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }        
    }
    @Override
    public Vector<ProduccionDeArticulo> ObtenerProduccionesDeArticulos(Object idProduccion) {
        String sql ="select ";        
               sql+="idproducciondearticulo, ";
               sql+="articulo_idcta_t, ";
               sql+="idproduccion, ";
               sql+="cantidad, ";
               sql+="preciounitario ";
               sql+="from ";
               sql+="producciondearticulo ";
               sql+="where(idproduccion="+idProduccion+") ";
               Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql); 
            ResultSet rs=ps.executeQuery();
            Vector <ProduccionDeArticulo> lista=new Vector <ProduccionDeArticulo>();
            ArticuloService aservice=new ArticuloService();
            while(rs.next()){
                ProduccionDeArticulo pa=new ProduccionDeArticulo();
                pa.setId(rs.getObject(1));
                Articulo a=aservice.getArticulo_dao().ObtenerArticulo(rs.getObject(2));
                pa.setArticulo(a);
                pa.setProduccion(this.ObtenerProduccion(idProduccion));
                pa.setCantidad(rs.getDouble(4));
                pa.setPreciounitario(rs.getDouble(5));
                lista.add(pa);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProduccionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }        
    }
    @Override
    public Produccion ModificarProduccion(Produccion p) {
         String sql =" update produccion set ";
                sql+=" idproduccion="+p.getId()+",idmaquila="+p.getMaquila().getId()+", ";
                sql+=" fechacontable=?,fechacreacion=?,creador=?,activa=?, ";
                sql+=" fechaanulacion=?,anulador=? where(idproduccion="+p.getId()+")";
               
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                       
            ps.setDate(1,new java.sql.Date(p.getFechacontable().getTime()));
            ps.setDate(2,new java.sql.Date(p.getFechacreacion().getTime()));            
            ps.setString(3,p.getCreador());            
            ps.setBoolean(4,p.isActivo());
            if(p.getFechaanulacion()!=null){
               ps.setDate(5,new java.sql.Date(p.getFechaanulacion().getTime()));            
               ps.setString(6,p.getAnulador());
            }else{                
               Calendar cal=Calendar.getInstance(); 
               ps.setDate(5,new java.sql.Date(cal.getTime().getTime()));            
               ps.setString(6,p.getAnulador()); 
            }
            ps.executeUpdate();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(ProduccionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public ProduccionDeArticulo ModificarProduccionDeArticulo(ProduccionDeArticulo pa) {
        String sql =" update producciondearticulo set";
               sql+=" idproduccion="+pa.getProduccion().getId()+",articulo_idcta_t="+pa.getArticulo().getId()+", ";               
               sql+=" cantidad=?,preciounitario=? ";
               sql+=" where(idproducciondearticulo="+pa.getId()+")";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                       
            ps.setDouble(1,pa.getCantidad());
            ps.setDouble(2,pa.getPreciounitario());
            ps.executeUpdate();
            return pa;
        } catch (SQLException ex) {
            Logger.getLogger(ProduccionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    
    @Override
    public double ObtenerMasaBlancaProducciones(Date fecha_inicial,Date fecha_final) {
        String sql =" select ";
               sql+="    SUM(producciondearticulo.Cantidad*articulo.CantidadKg) ";
               sql+=" from ";
               sql+=" produccion,producciondearticulo,articulo  ";
               sql+=" where( ";
               sql+="  produccion.idProduccion=producciondearticulo.idProduccion and ";
               sql+="  articulo.idCta_T=producciondearticulo.Articulo_idCta_T and ";
               sql+="  produccion.Activa=true and ";
               sql+="  produccion.fechacontable>=? and ";
               sql+="  produccion.fechacontable<=? and ";
               sql+="  produccion.idMaquila!=1 and ( ";
               sql+="  articulo.Categoria='subproductos' or ";
               sql+="  articulo.Categoria='producto procesado' ) ";               
               sql+=" )";
              Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql); 
            ps.setDate(1,new java.sql.Date(fecha_inicial.getTime()));
            ps.setDate(2,new java.sql.Date(fecha_final.getTime()));
            ResultSet rs=ps.executeQuery();            
            double val=0;
            if(rs.next()){
               val=rs.getDouble(1);
            }
            return val;
        } catch (SQLException ex) {
            Logger.getLogger(ProduccionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1000000000;        
        }finally{
            Pool.LiberarConexion(con);
        }        
    }
    
}

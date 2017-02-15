/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Infraestructura.JDBC.Impl;

import corgranos.Dominio.Servicios.Inventario.MovimientoInventario;
import corgranos.Infraestructura.JDBC.Pool;
import corgranos.InterfacesDAO.I_InventarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FANNY BURGOS
 */
public class InventarioDAO implements I_InventarioDAO{

    @Override
    public Vector<MovimientoInventario> ObtenerMovimientos(Object idArticulo, Date fechainicio, Date fechafin) {
        Connection con=null;
        String sql =" \n";
               sql+="select  \n";
               sql+="produccion.FechaContable, \n";
               sql+="CONCAT_WS('','PD',produccion.idProduccion) as soporte, \n";
               sql+="0 as idcta_t_usuario, \n";
               sql+="produccion.idMaquila, \n";
               sql+="CONCAT_WS(' ',' ')as detalle, \n";
               sql+="producciondearticulo.Cantidad as entradas, \n";
               sql+="0 as salidas, \n";
               sql+="1 as tipo ,\n";
               sql+="produccion.idProduccion as numero \n";
               sql+="from \n";
               sql+="produccion,producciondearticulo \n";
               sql+="where( \n";
               sql+=" produccion.idProduccion=producciondearticulo.idProduccion and \n";
               sql+=" producciondearticulo.Articulo_idCta_T="+idArticulo+"  and \n";
               sql+=" produccion.fechacontable>=? and ";
               sql+=" produccion.fechacontable<=? and ";
               sql+=" produccion.Activa=true \n";
               sql+=" )  \n";
               sql+="union all \n";
               sql+="select  \n";
               sql+="remision.FechaContable, \n";
               sql+="CONCAT_WS('','RE',remision.idRemision), \n";
               sql+="remision.idCta_t_Usuario, \n";
               sql+="remision.idMaquila, \n";
               sql+="remision.Detalle, \n";
               sql+="remision.Entradas, \n";
               sql+="remision.Salidas ,  \n";
               sql+="2 as tipo ,\n";
               sql+="remision.idRemision as numero \n";
               sql+="from  \n";
               sql+="remision \n";
               sql+="where( \n";
               sql+="remision.Articulo_idCta_T="+idArticulo+" and \n";
               sql+="remision.fechacontable>=? and ";
               sql+="remision.fechacontable<=? and ";
               sql+="remision.Activa=true \n";               
               sql+=") \n";
               sql+="union all \n";
               sql+="select  \n";
               sql+="factura.fechacontable, \n";
               sql+="CONCAT_WS('','FA',factura.nofactura), \n";
               sql+="factura.idCta_t_Usuario, \n";
               sql+="facturaciondearticulo.idMaquila, \n";
               sql+="factura.Observaciones, \n";
               sql+="0 as entradas, \n";
               sql+="facturaciondearticulo.Cantidad as salidas , \n";
               sql+="3 as tipo, \n";
               sql+="factura.nofactura as numero \n";
               sql+="from \n";
               sql+="   factura,facturaciondearticulo \n";
               sql+="where( \n";
               sql+="   factura.idDocumento=facturaciondearticulo.idDocumento and \n";
               sql+="   factura.fechacontable>=? and ";
               sql+="   factura.fechacontable<=? and ";
               sql+="   facturaciondearticulo.Articulo_idCta_T="+idArticulo+" and \n";
               sql+="   facturaciondearticulo.Remisionada=false and \n";
               sql+="   factura.activa=true \n";               
               sql+=") \n";
               sql+="order by fechacontable,tipo,numero \n";
               try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);           
            ps.setDate(1,new java.sql.Date(fechainicio.getTime()));
            ps.setDate(2,new java.sql.Date(fechafin.getTime()));
            ps.setDate(3,new java.sql.Date(fechainicio.getTime()));
            ps.setDate(4,new java.sql.Date(fechafin.getTime()));
            ps.setDate(5,new java.sql.Date(fechainicio.getTime()));
            ps.setDate(6,new java.sql.Date(fechafin.getTime()));
            ResultSet rs=ps.executeQuery();
            Vector <MovimientoInventario> lista=new Vector <MovimientoInventario>();
            while(rs.next()){
                MovimientoInventario m=new MovimientoInventario();
                m.setFecha(new Date(rs.getDate(1).getTime()));
                m.setSoporte(rs.getString(2));                       
                m.setIdusuario(rs.getObject(3));
                m.setIdmaquila(rs.getObject(4));
                m.setDetalle(rs.getString(5));
                m.setEntradas(rs.getDouble(6));
                m.setSalidas(rs.getDouble(7));
                lista.add(m);
            }
            System.out.println("Lista de Movimientos : "+lista.size());
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public double ObtenerExisteciaPorRemisiones(Object idArticulo, Date fechacorte) {
        Connection con=null;
        String sql =" \n";
               sql+="select \n";             
               sql+="SUM(remision.Entradas)-SUM(remision.Salidas) \n";               
               sql+="from  \n";
               sql+="remision \n";
               sql+="where( \n";
               sql+="remision.Articulo_idCta_T="+idArticulo+" and \n";         
               sql+="remision.fechacontable<=? and ";
               sql+="remision.Activa=true \n";               
               sql+=") \n";
               try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fechacorte.getTime()));            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getDouble(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
               
    @Override
    public double ObtenerSalidasPorFacturasNoRemisionadas(Object idArticulo, Date fechacorte) {
        Connection con=null;
        String sql =" \n";
               sql+="select  \n";               
               sql+="SUM(facturaciondearticulo.Cantidad) \n";
               sql+="from \n";
               sql+="   factura,facturaciondearticulo \n";
               sql+="where( \n";
               sql+="   factura.idDocumento=facturaciondearticulo.idDocumento and \n";               
               sql+="   factura.fechacontable<=? and ";
               sql+="   facturaciondearticulo.Articulo_idCta_T="+idArticulo+" and \n";
               sql+="   facturaciondearticulo.Remisionada=false and \n";
               sql+="   factura.activa=true \n";               
               sql+=") \n";
               try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fechacorte.getTime()));
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getDouble(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public double ObtenerEntradasPorProducciones(Object idArticulo, Date fechacorte) {
        Connection con=null;
        String sql =" \n";
               sql+="select  \n";      
               sql+="SUM(producciondearticulo.Cantidad) \n";               
               sql+="from \n";
               sql+="produccion,producciondearticulo \n";
               sql+="where( \n";
               sql+=" produccion.idProduccion=producciondearticulo.idProduccion and \n";
               sql+=" producciondearticulo.Articulo_idCta_T="+idArticulo+"  and \n";               
               sql+=" produccion.fechacontable<=? and ";
               sql+=" produccion.Activa=true \n";
               sql+=" )  \n";
               try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fechacorte.getTime()));
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getDouble(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public double ObtenerEntradasPorProducciones(Object idMaquila, Object idArticulo) {
        Connection con=null;
        String sql =" \n";
               sql+="select  \n";      
               sql+="SUM(producciondearticulo.Cantidad) \n";               
               sql+="from \n";
               sql+="produccion,producciondearticulo \n";
               sql+="where( \n";
               sql+=" produccion.idProduccion=producciondearticulo.idProduccion and \n";
               sql+=" producciondearticulo.Articulo_idCta_T="+idArticulo+"  and \n";               
               sql+=" produccion.idmaquila="+idMaquila+" and ";
               sql+=" produccion.Activa=true \n";
               sql+=" )  \n";
               try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
         
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getDouble(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public double ObtenerSaldoPorProducciones(Object idMaquila, Object idArticulo) {
        Connection con=null;
        String sql =" \n";
               sql+="select  \n";      
               sql+="SUM(producciondearticulo.Cantidad*producciondearticulo.preciounitario) \n";               
               sql+="from \n";
               sql+="produccion,producciondearticulo \n";
               sql+="where( \n";
               sql+=" produccion.idProduccion=producciondearticulo.idProduccion and \n";
               sql+=" producciondearticulo.Articulo_idCta_T="+idArticulo+"  and \n";               
               sql+=" produccion.idmaquila="+idMaquila+" and ";
               sql+=" produccion.Activa=true \n";
               sql+=" )  \n";
               try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);         
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getDouble(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public double ObtenerSalidasPorFacturasNoRemisionadas(Object idMaquila, Object idArticulo) {
        Connection con=null;
        String sql =" \n";
               sql+="select  \n";               
               sql+="SUM(facturaciondearticulo.Cantidad) \n";
               sql+="from \n";
               sql+="   factura,facturaciondearticulo \n";
               sql+="where( \n";
               sql+="   factura.idDocumento=facturaciondearticulo.idDocumento and \n";               
               sql+="   facturaciondearticulo.idmaquila="+idMaquila+" and ";
               sql+="   facturaciondearticulo.Articulo_idCta_T="+idArticulo+" and \n";
               sql+="   facturaciondearticulo.Remisionada=false and \n";
               sql+="   factura.activa=true \n";               
               sql+=") \n";
               try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getDouble(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public double ObtenerExisteciaPorRemisiones(Object idMaquila, Object idArticulo) {
        Connection con=null;
        String sql =" \n";
               sql+="select \n";             
               sql+="SUM(remision.Entradas)-SUM(remision.Salidas) \n";               
               sql+="from  \n";
               sql+="remision \n";
               sql+="where( \n";
               sql+="remision.Articulo_idCta_T="+idArticulo+" and \n";         
               sql+="remision.idmaquila="+idMaquila+" and ";
               sql+="remision.Activa=true \n";               
               sql+=") \n";
               try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getDouble(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public double ObtenerSaldoPorProducciones(Object idArticulo, Date fechacorte) {
        Connection con=null;
        String sql =" \n";
               sql+="select  \n";      
               sql+="SUM(producciondearticulo.Cantidad*producciondearticulo.preciounitario) \n";               
               sql+="from \n";
               sql+="produccion,producciondearticulo \n";
               sql+="where( \n";
               sql+=" produccion.idProduccion=producciondearticulo.idProduccion and \n";
               sql+=" producciondearticulo.Articulo_idCta_T="+idArticulo+"  and \n";               
               sql+=" produccion.fechacontable<=? and ";
               sql+=" produccion.Activa=true \n";
               sql+=" )  \n";
               try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);         
            ps.setDate(1,new java.sql.Date(fechacorte.getTime()));
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getDouble(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public Vector<MovimientoInventario> ObtenerMovimientos(Object idArticulo, Object idMaquila) {
        Connection con=null;
        String sql =" \n";
               sql+="select  \n";
               sql+="produccion.FechaContable, \n";
               sql+="CONCAT_WS('','PD',produccion.idProduccion) as soporte, \n";
               sql+="0 as idcta_t_usuario, \n";
               sql+="produccion.idMaquila, \n";
               sql+="CONCAT_WS(' ',' ')as detalle, \n";
               sql+="producciondearticulo.Cantidad as entradas, \n";
               sql+="0 as salidas \n";
               sql+="from \n";
               sql+="produccion,producciondearticulo \n";
               sql+="where( \n";
               sql+=" produccion.idProduccion=producciondearticulo.idProduccion and \n";
               sql+=" producciondearticulo.Articulo_idCta_T="+idArticulo+"  and \n";
               sql+=" produccion.idmaquila="+idMaquila+"  and \n";
               
               sql+=" produccion.Activa=true \n";
               sql+=" )  \n";
               sql+="union all \n";
               sql+="select  \n";
               sql+="remision.FechaContable, \n";
               sql+="CONCAT_WS('','RE',remision.idRemision), \n";
               sql+="remision.idCta_t_Usuario, \n";
               sql+="remision.idMaquila, \n";
               sql+="remision.Detalle, \n";
               sql+="remision.Entradas, \n";
               sql+="remision.Salidas   \n";
               sql+="from  \n";
               sql+="remision \n";
               sql+="where( \n";
               sql+="remision.Articulo_idCta_T="+idArticulo+" and \n";
               sql+="remision.idmaquila="+idMaquila+" and \n";
               
               sql+="remision.Activa=true \n";               
               sql+=") \n";
               sql+="union all \n";
               sql+="select  \n";
               sql+="factura.fechacontable, \n";
               sql+="CONCAT_WS('','FA',factura.nofactura), \n";
               sql+="factura.idCta_t_Usuario, \n";
               sql+="facturaciondearticulo.idMaquila, \n";
               sql+="factura.Observaciones, \n";
               sql+="0 as entradas, \n";
               sql+="facturaciondearticulo.Cantidad as salidas \n";
               sql+="from \n";
               sql+="   factura,facturaciondearticulo \n";
               sql+="where( \n";
               sql+="   factura.idDocumento=facturaciondearticulo.idDocumento and \n";
               
               sql+="   facturaciondearticulo.Articulo_idCta_T="+idArticulo+" and \n";
               sql+="   facturaciondearticulo.idMaquila="+idMaquila+" and \n";
               sql+="   facturaciondearticulo.Remisionada=false and \n";
               sql+="   factura.activa=true \n";               
               sql+=") \n";
               sql+="order by fechacontable \n";
               try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);                       
            ResultSet rs=ps.executeQuery();
            Vector <MovimientoInventario> lista=new Vector <MovimientoInventario>();
            while(rs.next()){
                MovimientoInventario m=new MovimientoInventario();
                m.setFecha(new Date(rs.getDate(1).getTime()));
                m.setSoporte(rs.getString(2));                       
                m.setIdusuario(rs.getObject(3));
                m.setIdmaquila(rs.getObject(4));
                m.setDetalle(rs.getString(5));
                m.setEntradas(rs.getDouble(6));
                m.setSalidas(rs.getDouble(7));
                lista.add(m);
            }
            System.out.println("Lista de Movimientos : "+lista.size());
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public double ObtenerEntradasPorRemisiones(Object idMaquila, Object idArticulo) {
        Connection con=null;
        String sql =" \n";
               sql+="select \n";             
               sql+="SUM(remision.Entradas) \n";               
               sql+="from  \n";
               sql+="remision \n";
               sql+="where( \n";
               sql+="remision.Articulo_idCta_T="+idArticulo+" and \n";         
               sql+="remision.idmaquila="+idMaquila+" and ";
               sql+="remision.Activa=true \n";               
               sql+=") \n";
               try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getDouble(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
     @Override
    public double ObtenerSalidasPorFacturas_(Object idMaquila, Object idArticulo) {
        Connection con=null;
        String sql =" \n";
               sql+="select  \n";               
               sql+="SUM(facturaciondearticulo.Cantidad) \n";
               sql+="from \n";
               sql+="   factura,facturaciondearticulo \n";
               sql+="where( \n";
               sql+="   factura.idDocumento=facturaciondearticulo.idDocumento and \n";               
               sql+="   facturaciondearticulo.idmaquila="+idMaquila+" and ";
               sql+="   facturaciondearticulo.Articulo_idCta_T="+idArticulo+" and \n";
               //sql+="   facturaciondearticulo.Remisionada=false and \n";
               sql+="   factura.activa=true \n";               
               sql+=") \n";
               try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getDouble(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
}

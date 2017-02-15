/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Infraestructura.JDBC.Impl;

import corgranos.Dominio.Core.Articulo.Articulo;
import corgranos.Dominio.Core.Articulo.ArticuloService;
import corgranos.Dominio.Core.Maquila.Maquila;
import corgranos.Dominio.Core.Maquila.MaquilaService;
import corgranos.Dominio.Core.Remision.Remision;
import corgranos.Infraestructura.JDBC.Pool;
import corgranos.InterfacesDAO.IRemisionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import sic.Dominio.Core.Usuario.Usuario;
import sic.Dominio.Core.Usuario.UsuarioService;

/**
 *
 * @author FANNY BURGOS
 */
public class RemisionDAO implements IRemisionDAO{
    @Override
    public Remision PersistirRemision(Remision r) {
        String sql =" insert into remision ";
               sql+=" (idremision,idmaquila,articulo_idcta_t,usuario, ";
               sql+="  idcta_t_usuario,fechacontable,fechacreacion,";
               sql+="  creador,entradas,salidas,activa,detalle) ";
               sql+=" values ";
               sql+=" ("+r.getId()+",?,"+r.getArticulo().getId()+",?,"+r.getUsuario().getId()+",?,curdate(),?,?,?,true,?)";
              
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                                            
            Object idmaquila;
            if(r.getMaquila()!=null){
               idmaquila=r.getMaquila().getId();
            }else{
               idmaquila=null; 
            }
            ps.setObject(1,idmaquila);
            ps.setString(2,r.getUsuario().getDescripcion());
            ps.setDate(3,new java.sql.Date(r.getFechacontable().getTime()));            
            ps.setString(4,r.getCreador());
            ps.setDouble(5,r.getEntradas());
            ps.setDouble(6,r.getSalidas());            
            ps.setString(7,r.getDetalle());            
            ps.executeUpdate();
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(RemisionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public long ObtenerIdUltimaRemision() {
        Connection con=null;
        String sql="select Max(idremision) from remision ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getLong(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(RemisionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Remision ObtenerRemision(Object id) {
        String sql= " select ";
               sql+=" remision.idRemision, ";
               sql+=" remision.idMaquila, ";
               sql+=" remision.Articulo_idCta_T, ";
               sql+=" remision.Usuario, ";
               sql+=" remision.idCta_t_Usuario,  ";
               sql+=" remision.FechaContable ,   ";
               sql+=" remision.fechacreacion , ";
               sql+=" remision.FechaAnulacion, ";
               sql+=" remision.Creador,  ";
               sql+=" remision.Anulador, ";
               sql+=" remision.Entradas, ";
               sql+=" remision.Salidas,  ";
               sql+=" remision.Activa,  ";
               sql+=" remision.Detalle,   ";
               sql+=" articulo.idcta_t,";
               sql+=" articulo.nombre,";
               sql+=" articulo.referencia,";
               sql+=" articulo.cantidadkg,";
               sql+=" articulo.ingreso_aux,";
               sql+=" articulo.activo_aux,";
               sql+=" articulo.costos_aux,";
               sql+=" articulo.tipo,";
               sql+=" articulo.categoria,";
               sql+=" articulo.preciounitario,";
               sql+=" articulo.porcentageiva,";
               sql+=" articulo.codigodebarra, ";
               sql+=" articulo.activo , ";               
               sql+=" articulo.iva_aux ";               
               sql+=" from  ";
               sql+=" remision, articulo ";
               sql+=" where( ";
               sql+=" remision.idremision=? and";
               sql+=" remision.articulo_idcta_t=articulo.idcta_t ";
               sql+=" ) ";
               Connection con=null;
               try {
                  con=Pool.ObtenerConexion();               
                  PreparedStatement ps;        
                  ps = con.prepareStatement(sql);
                  ps.setObject(1,id);
                  ResultSet rs=ps.executeQuery();
                  if(rs.next()){
                      Remision r=new Remision();
                      r.setId(rs.getObject(1));
                      if(rs.getObject(2)!=null){
                         Maquila m=new Maquila();
                         m.setId(rs.getObject(2));
                         r.setMaquila(m);
                      }
                      
                      Usuario u=(new UsuarioService().getDao().ObtenerUsuario(rs.getObject(5)));                                          
                      r.setUsuario(u);
                      r.setFechacontable(new Date(rs.getDate(6).getTime()));
                      r.setFechacreacion(new Date(rs.getDate(7).getTime()));
                      if(rs.getDate(8)!=null){
                         r.setFechaanulacion(new Date(rs.getDate(8).getTime()));
                      }
                      r.setCreador(rs.getString(9));
                      r.setAnulador(rs.getString(10));
                      r.setEntradas(rs.getDouble(11));
                      r.setSalidas(rs.getDouble(12));
                      r.setActivo(rs.getBoolean(13));
                      r.setDetalle(rs.getString(14));
                      Articulo a=new Articulo();
                      a.setId(rs.getObject(15));
                      a.setNombre(rs.getString(16));
                      a.setReferencia(rs.getString(17));
                      a.setCantidadkg(rs.getInt(18));
                      a.setAuxingreso(rs.getString(19));
                      a.setAuxactivo(rs.getString(20));
                      a.setAuxcosto(rs.getString(21));
                      a.setTipo(rs.getString(22));
                      a.setCategoria(rs.getString(23));
                      a.setPreciounitario(rs.getDouble(24));
                      a.setPorcentageiva(rs.getDouble(25));
                      a.setCodigodebarra(rs.getLong(26));
                      a.setActivo(rs.getBoolean(27));
                      a.setAuxiva(rs.getString(28));
                      r.setArticulo(a);
                      return r;
                  }
                  return null;
               }catch (SQLException ex) {
                  Logger.getLogger(RemisionDAO.class.getName()).log(Level.SEVERE, null, ex);
                  return null;
               }finally{
                   Pool.LiberarConexion(con);
               }
               
               
    }
    @Override
    public Vector<Remision> ObtenerRemisionesNoLegalizadasEnFactura(Object idUsuario) {
        String sql="";
        sql+="select \n ";
        sql+="remision.idRemision,   \n";
        sql+="remision.idMaquila,   \n";
        sql+="remision.Articulo_idCta_T,   \n";
        sql+="remision.Usuario,     \n";
        sql+="remision.idCta_t_Usuario,    \n";
        sql+="remision.FechaContable ,     \n";
        sql+="remision.fechacreacion ,   \n";        
        sql+="remision.Creador,    \n";        
        sql+="remision.Entradas,   \n";
        sql+="remision.Salidas,   \n";        
        sql+="remision.Detalle   \n";        
        sql+="from   \n";
        sql+="articulo,remision left outer join   \n";
        sql+="factura inner join   \n";
        sql+="facturaciondearticulo on   \n";
        sql+="factura.idDocumento=facturaciondearticulo.idDocumento and   \n";
        sql+="factura.activa=true   \n";
        sql+="on   \n";
        sql+="remision.idRemision=facturaciondearticulo.idRemision   \n";
        sql+="where(   \n";
        sql+="    remision.idCta_t_Usuario="+idUsuario+"  and   \n";
        sql+="    remision.Salidas!=0 and   \n";
        sql+="    articulo.idCta_T=remision.Articulo_idCta_T and \n";
        sql+="    remision.Activa=true \n";
        sql+="   ) \n";       
        sql+="  union all \n";
        sql+="select \n ";
        sql+="remision.idRemision,   \n";
        sql+="remision.idMaquila,   \n";
        sql+="remision.Articulo_idCta_T,   \n";
        sql+="remision.Usuario,     \n";
        sql+="remision.idCta_t_Usuario,    \n";
        sql+="remision.FechaContable ,     \n";
        sql+="remision.fechacreacion ,   \n";        
        sql+="remision.Creador,    \n";        
        sql+="remision.Entradas,   \n";
        sql+="remision.Salidas,   \n";        
        sql+="remision.Detalle   \n";        
        sql+="from   \n";
        sql+="articulo,remision right outer join   \n";
        sql+="factura inner join   \n";
        sql+="facturaciondearticulo on   \n";
        sql+="factura.idDocumento=facturaciondearticulo.idDocumento and   \n";
        sql+="factura.activa=true   \n";
        sql+="on   \n";
        sql+="remision.idRemision=facturaciondearticulo.idRemision   \n";
        sql+="where(   \n";
        sql+="    remision.idCta_t_Usuario="+idUsuario+"  and   \n";
        sql+="    remision.Salidas!=0 and   \n";
        sql+="    articulo.idCta_T=remision.Articulo_idCta_T and \n";
        sql+="    remision.Activa=true \n";
        sql+="   ) \n";
        sql+="  order by idremision \n";        
        Connection con=null;
        try {
           con=Pool.ObtenerConexion();
           Statement st;
           st = con.createStatement();
           ResultSet rs=st.executeQuery(sql);
           Vector <Remision> lista=new Vector <Remision>();
           while(rs.next()){
                long idremision=rs.getLong(1);
                boolean isRepetido=false;
                if(rs.isLast()==false){
                  if(rs.next()){
                    long idr=rs.getLong(1);
                    if(idr!=idremision){
                           rs.previous();
                    }else{
                           isRepetido=true;
                    }
                 }
               }
               if(isRepetido==false){
                   Object idRemision=rs.getObject(1);
                   Object idMaquila=rs.getObject(2);
                   Object idArticulo=rs.getObject(3);
                   String usuario=rs.getString(4);
                   Object idusuario=rs.getObject(5);
                   Date fechacontable=new Date(rs.getDate(6).getTime());
                   Date fechacreacion=new Date(rs.getDate(7).getTime());
                   String creador=rs.getString(8);
                   double entradas=rs.getDouble(9);
                   double salidas=rs.getDouble(10);
                   boolean activa=true;
                   String detalle=rs.getString(11);
                   Remision r=new Remision(null, null, null, fechacontable, creador, entradas, salidas, detalle);
                   MaquilaService ms=new MaquilaService();
                   Maquila m=ms.getMaquilaDao().ObtenerMaquila(idMaquila);
                   r.setMaquila(m);
                   ArticuloService as=new ArticuloService();
                   Articulo a=as.getArticulo_dao().ObtenerArticulo(idArticulo);
                   r.setArticulo(a);
                   UsuarioService us=new UsuarioService();
                   Usuario u=us.getDao().ObtenerUsuario(idUsuario);
                   r.setUsuario(u);        
                   r.setId(idRemision);
                   lista.add(r);                   
               }
           }
           return lista;
        }catch (SQLException ex){
             Logger.getLogger(RemisionDAO.class.getName()).log(Level.SEVERE, null, ex);       
        }   
        return null;
    }
    @Override
    public Remision ModificarRemision(Remision r){
        String sql =" update remision set ";
               sql+=" idremision="+r.getId()+",idmaquila=?,articulo_idcta_t="+r.getArticulo().getId()+",usuario=?, ";
               sql+=" idcta_t_usuario="+r.getUsuario().getId()+",fechacontable=?,creador=?,";
               sql+=" entradas=?,salidas=?,activa=?,detalle=?,fechacreacion=?,fechaanulacion=?,anulador=? ";
               sql+=" where(idremision="+r.getId()+") ";              
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                                            
            Object idmaquila;
            if(r.getMaquila()!=null){
               idmaquila=r.getMaquila().getId();
            }else{
               idmaquila=null; 
            }
            ps.setObject(1,idmaquila);
            ps.setString(2,r.getUsuario().getDescripcion());
            ps.setDate(3,new java.sql.Date(r.getFechacontable().getTime()));            
            ps.setString(4,r.getCreador());
            ps.setDouble(5,r.getEntradas());
            ps.setDouble(6,r.getSalidas());            
            ps.setBoolean(7,r.isActivo());
            ps.setString(8,r.getDetalle());            
            ps.setDate(9,new java.sql.Date(r.getFechacreacion().getTime()));            
            ps.setDate(10,new java.sql.Date(r.getFechaanulacion().getTime()));            
            ps.setString(11,r.getAnulador());
            ps.executeUpdate();
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(RemisionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }        
    }

    @Override
    public int FueLegalizadaEnFactura(Object idRemision) {
        String sql =" ";
               sql+="select factura.nofactura from remision,factura,facturaciondearticulo  ";
               sql+="where( ";
               sql+="factura.idDocumento=facturaciondearticulo.idDocumento and ";
               sql+="factura.activa=true and ";
               sql+="facturaciondearticulo.Remisionada=true   and ";
              // sql+="facturaciondearticulo.Articulo_idCta_T=remision.Articulo_idCta_T  and ";
               sql+="facturaciondearticulo.idRemision=remision.idRemision and ";
               sql+="facturaciondearticulo.idRemision="+idRemision+" ";
               sql+=")group by idfacturaciondearticulo ";
               Connection con=null;
               try {
                  con=Pool.ObtenerConexion();               
                  PreparedStatement ps;        
                  ps = con.prepareStatement(sql);                  
                  ResultSet rs=ps.executeQuery();
                  if(rs.next()){
                      return rs.getInt(1);
                  }
                  return 0;
              }catch (SQLException ex) {
                  Logger.getLogger(RemisionDAO.class.getName()).log(Level.SEVERE, null, ex);
                  return -1;
               }finally{
                   Pool.LiberarConexion(con);
               }    
    }
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Infraestructura.JDBC.Impl;

import corgranos.Dominio.Core.Articulo.Articulo;
import corgranos.Dominio.Core.Articulo.ArticuloService;
import corgranos.Dominio.Core.Factura.Factura;
import corgranos.Dominio.Core.Factura.FacturacionDeArticulo;
import corgranos.Dominio.Core.Factura.Prefijo;
import corgranos.Dominio.Core.Factura.dto.InformeFacturaDTO;
import corgranos.Dominio.Core.Maquila.Maquila;
import corgranos.Dominio.Core.Maquila.MaquilaService;
import corgranos.Dominio.Core.Remision.Remision;
import corgranos.Dominio.Core.Remision.RemisionService;
import corgranos.Infraestructura.JDBC.Pool;
import corgranos.InterfacesDAO.IFacturaDAO;
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
public class FacturaDAO implements IFacturaDAO{
    @Override
    public Factura PersistirFactura(Factura f) {
           String sql =" insert into factura ";
               sql+=" (iddocumento,fechaplazo,tipofactura,subtotal,totaliva,totalrtf, ";
               sql+=" totaldescuento,nofactura,observaciones,dian1,dian2,idcta_t_usuario, ";
               sql+=" cliente,ubicacion,activa,fechacontable,fechacreacion,creador,total,nodocumento,idPrefijo) ";
               sql+=" values ";
               sql+=" ("+f.getId()+",?,?,?,?,?,";
               sql+=" ?,?,?,?,?,"+f.getUsuario().getId()+",";
               sql+=" ?,?,true,?,curdate(),?,?,?,?)";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                                  
            ps.setDate(1,new java.sql.Date(f.getFechaplazo().getTime()));
            ps.setString(2,f.getTipofactura());
            ps.setDouble(3,f.getSubtotal());
            ps.setDouble(4,f.getTotaliva());
            ps.setDouble(5,f.getTotalrtf());
            ps.setDouble(6,f.getTotaldescuento());
            ps.setInt(7,f.getNumeracion());
            ps.setString(8,f.getObservaciones());
            ps.setString(9,f.getDian1());
            ps.setString(10,f.getDian2());
            ps.setString(11,f.getUsuario().NombreCompleto());
            ps.setString(12,f.getUsuario().getDireccion()+","+f.getUsuario().getMunicipio().toString());
            ps.setDate(13,new java.sql.Date(f.getFechaContable().getTime()));            
            ps.setString(14,f.getCreador());
            ps.setDouble(15,f.getTotal());
            ps.setString(16,f.getUsuario().getNoDocumentoCompleto());
            ps.setLong(17,f.getPrefijo().getIdPrefijo());
            ps.executeUpdate();
            return f;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public FacturacionDeArticulo PersistirFacturacionDeArticulo(FacturacionDeArticulo fa) {
            String sql =" insert into facturaciondearticulo ";
               sql+=" (idremision,articulo_idcta_t,idmaquila,iddocumento,";
               sql+="  preciounitario,cantidad,valoriva,remisionada) ";
               sql+="  values ";
               sql+="  (?,?,?,?, ";
               sql+="  ?,?,?,?) ";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                                  
            Object idmaquila;
            if(fa.getMaquila()!=null){
               idmaquila=fa.getMaquila().getId();
            }else{
               idmaquila=null; 
            }
            Object idremision;
            if(fa.getRemision()!=null){
               idremision=fa.getRemision().getId();
            }else{
               idremision=null; 
            }
            ps.setObject(1,idremision);
            ps.setObject(2,fa.getArticulo().getId());
            ps.setObject(3,idmaquila);
            ps.setObject(4,fa.getFactura().getId());
            ps.setDouble(5,fa.getPreciounitario());
            ps.setDouble(6,fa.getCantidad());
            ps.setDouble(7,fa.getValoriva());
            ps.setBoolean(8,fa.isRemisionada());
            ps.executeUpdate();
            return fa;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Factura ObtenerFactura(int NumeracionFactura,long idPrefijo) {
        String sql="";
        sql+="select \n";
        sql+=" iddocumento, \n";
        sql+=" fechaplazo,\n";
        sql+=" tipofactura,\n";
        sql+=" subtotal,\n";
        sql+=" totaliva,\n";
        sql+=" totalrtf,\n";
        sql+=" totaldescuento,\n";
        sql+=" nofactura,\n";
        sql+=" observaciones,\n";
        sql+=" dian1,\n";
        sql+=" dian2,\n";
        sql+=" idcta_t_usuario,\n";
        sql+=" cliente,\n";
        sql+=" ubicacion,\n";
        sql+=" activa,\n";
        sql+=" fechacontable,\n";
        sql+=" fechacreacion,\n";
        sql+=" creador,\n";
        sql+=" anulador,\n";
        sql+=" fechaanulacion,\n";
        sql+=" total,\n";
        sql+=" nodocumento,idprefijo\n";
        sql+=" from\n";
        sql+=" factura\n";
        sql+=" where(\n";
        sql+="    factura.nofactura=? and \n";
        sql+="    factura.idprefijo=?  \n";
        sql+=" )\n";
               Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql); 
            ps.setInt(1,NumeracionFactura);
            ps.setLong(2,idPrefijo);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Factura f=new Factura();
                f.setId(rs.getObject(1));
                f.setFechaplazo(new java.util.Date(rs.getDate(2).getTime()));                 
                f.setTipofactura(rs.getString(3));                 
                f.setSubtotal(rs.getDouble(4));                             
                f.setTotaliva(rs.getDouble(5));
                f.setTotalrtf(rs.getDouble(6));
                f.setTotaldescuento(rs.getDouble(7));
                f.setNofactura(rs.getInt(8));
                f.setObservaciones(rs.getString(9));
                f.setDian1(rs.getString(10));                        
                f.setDian2(rs.getString(11));
                UsuarioService us=new UsuarioService();
                Usuario u=us.getDao().ObtenerUsuario(rs.getObject(12));
                f.setUsuario(u);
                f.setCliente(rs.getString(13));                 
                f.setUbicacion(rs.getString(14));
                f.setActivo(rs.getBoolean(15));
                f.setFechaContable(new java.util.Date(rs.getDate(16).getTime()));
                f.setFechaCreacion(new java.util.Date(rs.getDate(17).getTime()));
                f.setCreador(rs.getString(18));
                f.setAnulador(rs.getString(19));
                if(rs.getDate(20)!=null){
                   f.setFechaAnulacion(new java.util.Date(rs.getDate(20).getTime()));
                }
                f.setTotal(rs.getDouble(21));                                                          
                f.setPrefijo(this.ObtenerPrefijo(rs.getLong(22)));
                return f;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }                    
    }
    @Override
    public Vector<FacturacionDeArticulo> ObtenerFacturacionesDeArticulo(Object idDocumento) {
        String sql="";
        sql+="select \n";
        sql+="idfacturaciondearticulo, \n";
        sql+="idremision, \n";
        sql+="articulo_idcta_t, \n";
        sql+="idmaquila, \n";
        sql+="iddocumento, \n";
        sql+="preciounitario, \n";
        sql+="cantidad, \n";
        sql+="valoriva, \n";
        sql+="remisionada \n";
        sql+="from \n";
        sql+="facturacionDeArticulo \n";
        sql+="where(iddocumento=?) \n";        
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql); 
            ps.setObject(1,idDocumento);
            ResultSet rs=ps.executeQuery();
            Vector <FacturacionDeArticulo> lista=new Vector <FacturacionDeArticulo>();
            while(rs.next()){
                FacturacionDeArticulo fa=new FacturacionDeArticulo();
                fa.setId(rs.getObject(1));                
                if(rs.getObject(2)!=null){
                   RemisionService rems=new RemisionService(); 
                   Remision r=rems.getDao().ObtenerRemision(rs.getObject(2));
                   fa.setRemision(r);
                }
                if(rs.getObject(3)!=null){                   
                   ArticuloService as=new ArticuloService();
                   Articulo a=as.getArticulo_dao().ObtenerArticulo(rs.getObject(3));
                   fa.setArticulo(a);                                      
                }
                if(rs.getObject(4)!=null){
                   MaquilaService ms=new MaquilaService();
                   Maquila m=ms.getMaquilaDao().ObtenerMaquila(rs.getObject(4));
                   fa.setMaquila(m);
                }
                fa.setPreciounitario(rs.getDouble(6));
                fa.setCantidad(rs.getDouble(7));
                fa.setValoriva(rs.getDouble(8));
                fa.setRemisionada(rs.getBoolean(9));
                lista.add(fa);                
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }  
    }
    @Override
    public Factura ModificarFactura(Factura f) {
         String sql =" update factura set ";                
                sql+=" fechaplazo=? , ";
                sql+=" tipofactura=? , ";
                sql+=" subtotal=? , ";
                sql+=" totaliva=? , ";
                sql+=" totalrtf=? ,";
                sql+=" totaldescuento=? ,";
                sql+=" nofactura=? , ";
                sql+=" observaciones=?, ";
                sql+=" dian1=? , ";
                sql+=" dian2=? , ";
                sql+=" idcta_t_usuario=? , ";                
                sql+=" cliente=?, ";
                sql+=" ubicacion=?, ";
                sql+=" activa=?, ";
                sql+=" fechacontable=?, ";
                sql+=" fechacreacion=?, ";
                sql+=" creador=?, ";
                sql+=" anulador=?, ";                
                sql+=" total=?, ";
                sql+=" nodocumento=?, ";
                sql+=" idprefijo=? ";
                if(f.getFechaAnulacion()!=null)
                sql+=" ,fechaanulacion=? ";                
                sql+=" where(iddocumento="+f.getId()+") ";
                
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                       
            ps.setDate(1,new java.sql.Date(f.getFechaplazo().getTime()));                 
            ps.setString(2,f.getTipofactura());
            ps.setDouble(3,f.getSubtotal());
            ps.setDouble(4,f.getTotaliva());
            ps.setDouble(5,f.getTotalrtf());
            ps.setDouble(6,f.getTotaldescuento());
            ps.setInt(7,f.getNofactura());
            ps.setString(8,f.getObservaciones());
            ps.setString(9,f.getDian1());
            ps.setString(10,f.getDian2());
            ps.setObject(11,f.getUsuario().getId());
            Usuario u=new UsuarioService().getDao().ObtenerUsuario(f.getUsuario().getId());
            ps.setString(12,u.NombreCompleto());
            ps.setString(13,f.getUsuario().getUbicacionCompleta());
            ps.setBoolean(14,f.isActivo());
            ps.setDate(15,new java.sql.Date(f.getFechaContable().getTime()));
            ps.setDate(16,new java.sql.Date(f.getFechaCreacion().getTime()));
            ps.setString(17,f.getCreador());
            ps.setString(18,f.getAnulador());                        
            ps.setDouble(19,f.getTotal());
            ps.setLong(20,f.getUsuario().getNoDocumento());
            ps.setLong(21,f.getPrefijo().getIdPrefijo());            
            if(f.getFechaAnulacion()!=null)
                ps.setDate(22,new java.sql.Date(f.getFechaAnulacion().getTime()));            
            ps.executeUpdate();
            return f;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public FacturacionDeArticulo ModificarFacturacionDeArticulo(FacturacionDeArticulo fa) {
        String sql =" update facturaciondearticulo set ";
               sql+=" idremision=? ,";
               sql+=" articulo_idcta_t=? ,";
               sql+=" idmaquila=? ,";
               sql+=" iddocumento=? ,";
               sql+=" preciounitario=? ,";
               sql+=" cantidad=? ,";
               sql+=" valoriva=? ,";
               sql+=" remisionada=? ";
               sql+=" where(idfacturaciondearticulo="+fa.getId()+")";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);     
            if(fa.getRemision()!=null){
               ps.setObject(1,fa.getRemision().getId());
            }else{
               ps.setObject(1,null); 
            }
            ps.setObject(2,fa.getArticulo().getId());
            if(fa.getMaquila()!=null){
               ps.setObject(3,fa.getMaquila().getId());                 
            }else{
               ps.setObject(3,null);                  
            }
            ps.setObject(4,fa.getFactura().getId());
            ps.setDouble(5,fa.getPreciounitario());
            ps.setDouble(6,fa.getCantidad());
            ps.setDouble(7,fa.getValoriva());
            ps.setBoolean(8,fa.isRemisionada());
            ps.executeUpdate();
            return fa;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public Vector<Factura> ObtenerFacturasCreditosVencidas() {
        String sql="";
        sql+="select \n";
        sql+=" iddocumento, \n";
        sql+=" fechaplazo,\n";
        sql+=" tipofactura,\n";
        sql+=" subtotal,\n";
        sql+=" totaliva,\n";
        sql+=" totalrtf,\n";
        sql+=" totaldescuento,\n";
        sql+=" nofactura,\n";
        sql+=" observaciones,\n";
        sql+=" dian1,\n";
        sql+=" dian2,\n";
        sql+=" idcta_t_usuario,\n";
        sql+=" cliente,\n";
        sql+=" ubicacion,\n";
        sql+=" activa,\n";
        sql+=" fechacontable,\n";
        sql+=" fechacreacion,\n";
        sql+=" creador,\n";
        sql+=" anulador,\n";
        sql+=" fechaanulacion,\n";
        sql+=" total,\n";
        sql+=" nodocumento,idprefijo\n";
        sql+=" from\n";
        sql+=" factura\n";
        sql+=" where(\n";
        sql+="    factura.activa=true and \n";
        sql+="    factura.tipofactura='credito' and \n";
        sql+="    CURDATE()>factura.fechaplazo  \n";
        sql+=" )order by fechacontable \n";
               Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);             
            ResultSet rs=ps.executeQuery();
            Vector<Factura> lista=new Vector<Factura>();
            while(rs.next()){
                Factura f=new Factura();
                f.setId(rs.getObject(1));
                f.setFechaplazo(new java.util.Date(rs.getDate(2).getTime()));                 
                f.setTipofactura(rs.getString(3));                 
                f.setSubtotal(rs.getDouble(4));                             
                f.setTotaliva(rs.getDouble(5));
                f.setTotalrtf(rs.getDouble(6));
                f.setTotaldescuento(rs.getDouble(7));
                f.setNofactura(rs.getInt(8));
                f.setObservaciones(rs.getString(9));
                f.setDian1(rs.getString(10));                        
                f.setDian2(rs.getString(11));
                UsuarioService us=new UsuarioService();
                Usuario u=us.getDao().ObtenerUsuario(rs.getObject(12));
                f.setUsuario(u);
                f.setCliente(rs.getString(13));                 
                f.setUbicacion(rs.getString(14));
                f.setActivo(rs.getBoolean(15));
                f.setFechaContable(new java.util.Date(rs.getDate(16).getTime()));
                f.setFechaCreacion(new java.util.Date(rs.getDate(17).getTime()));
                f.setCreador(rs.getString(18));
                f.setAnulador(rs.getString(19));
                if(rs.getDate(20)!=null){
                   f.setFechaAnulacion(new java.util.Date(rs.getDate(20).getTime()));
                }
                f.setTotal(rs.getDouble(21));                                                          
                f.setPrefijo(this.ObtenerPrefijo(rs.getLong(22)));
                lista.add(f);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }                    
    }

    @Override
    public Vector<Prefijo> ObtenerPrefijos() {
        String sql=" select idPrefijo,descripcion,resoluciondian1,resoluciondian2,ultimanumeracion,documentofactura,abreviaturafactura,abreviatura from prefijo";
        Connection con=null;
        Vector<Prefijo> lista=new Vector<Prefijo>();
        con=Pool.ObtenerConexion();
        try {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                Prefijo p=new Prefijo();
                p.setIdPrefijo(rs.getLong(1));
                p.setDescripcion(rs.getString(2));
                p.setDian1(rs.getString(3));
                p.setDian2(rs.getString(4));
                p.setUltima_numeracion(rs.getInt(5));
                p.setDocumento_factura(rs.getString(6));
                p.setAbreviatura_factura(rs.getString(7));
                p.setAbreviatura(rs.getString(8));
                lista.add(p);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Pool.LiberarConexion(con);
        }
        return lista;
    }

    @Override
    public Prefijo ObtenerPrefijo(long id) {
        String sql=" select idPrefijo,descripcion,resoluciondian1,resoluciondian2,ultimanumeracion,documentofactura,abreviaturafactura,abreviatura from prefijo where(idprefijo="+id+")";
        Connection con=null;    
        con=Pool.ObtenerConexion();
        try {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()){
                Prefijo p=new Prefijo();
                p.setIdPrefijo(rs.getLong(1));
                p.setDescripcion(rs.getString(2));
                p.setDian1(rs.getString(3));
                p.setDian2(rs.getString(4));
                p.setUltima_numeracion(rs.getInt(5));
                p.setDocumento_factura(rs.getString(6));
                p.setAbreviatura_factura(rs.getString(7));
                p.setAbreviatura(rs.getString(8));
                return p; 
            }            
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Pool.LiberarConexion(con);
        }
        return null;
    }

    @Override
    public Vector<InformeFacturaDTO> ObtenerInforme(Prefijo prefijo, Date fecha_inicio, Date fecha_fin) {
        String sql =" select  ";
               sql+=" factura.fechacontable, ";
               sql+=" factura.NoDocumento, ";
               sql+=" factura.cliente, ";
               sql+=" factura.nofactura, ";
               sql+=" factura.Subtotal, ";
               sql+=" factura.Totaliva, ";
               sql+=" factura.total ";
               sql+=" from ";
               sql+=" factura ";
               sql+=" where( ";
               sql+=" factura.idPrefijo=? and ";
               sql+=" factura.fechacontable>=? and ";
               sql+=" factura.fechacontable<=? ";
               sql+=" )order by fechacontable,nofactura";
               Connection con=null;               
               try {             
                  con=Pool.ObtenerConexion();
                  PreparedStatement ps=con.prepareStatement(sql);
                  ps.setLong(1, prefijo.getIdPrefijo());
                  ps.setDate(2, new java.sql.Date(fecha_inicio.getTime()));
                  ps.setDate(3, new java.sql.Date(fecha_fin.getTime()));
                  ResultSet rs=ps.executeQuery();
                  Vector<InformeFacturaDTO> lista=new Vector<InformeFacturaDTO>();
                  while(rs.next()){
                      InformeFacturaDTO dto=new InformeFacturaDTO();
                      dto.setPrefijo(prefijo.getDescripcion());
                      dto.setFecha_inicial(fecha_inicio);
                      dto.setFecha_final(fecha_fin);
                      dto.setFecha(new java.util.Date(rs.getDate(1).getTime()));
                      dto.setNo_documento(rs.getString(2));
                      dto.setCliente(rs.getString(3));
                      dto.setNo_factura(rs.getInt(4));
                      dto.setSubtotal(rs.getDouble(5));
                      dto.setTotal_iva(rs.getDouble(6));
                      dto.setTotal(rs.getDouble(7));
                      lista.add(dto);
                  }
                  return lista;
               } catch (SQLException ex) {
                  Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
                  return new Vector<InformeFacturaDTO>();
               }finally{
                   Pool.LiberarConexion(con);
               }
        
    }
}

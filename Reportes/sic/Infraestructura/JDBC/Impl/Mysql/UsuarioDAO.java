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
import sic.Dominio.Core.Usuario.Municipio;
import sic.Dominio.Core.Usuario.TipoDocumento;
import sic.Dominio.Core.Usuario.Usuario;
import sic.InterfacesDAO.IUsuarioDAO;
import sic.Infraestructura.JDBC.Pool;

/**
 *
 * @author FANNY BURGOS
 */
public class UsuarioDAO implements IUsuarioDAO{
    @Override
    public Usuario PersistirUsuario(Usuario usuario) {
        String sql =" insert into usuario ";
               sql+="(idcta_t,idmunicipio,idTipoDocumento,nodocumento,nombre,snombre,apellido,sapellido,";
               sql+="razonsocial,sobrenombre,regimen,agenteretenedor,telefono,direccion,correo,digitodian) ";
               sql+=" values ";
               sql+=" ("+usuario.getId()+","+usuario.getMunicipio().getId()+","+usuario.getTipodocumento().getId()+",?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);            
            ps.setLong(1,usuario.getNoDocumento());
            ps.setString(2,usuario.getNombre());
            ps.setString(3,usuario.getsNombre());
            ps.setString(4,usuario.getApellido());
            ps.setString(5,usuario.getsApellido());
            ps.setString(6,usuario.getRazonSocial());
            ps.setString(7,usuario.getSobreNombre());
            ps.setString(8,usuario.getRegimen());
            ps.setString(9,usuario.getAgenteRetenedor());
            ps.setString(10,usuario.getTelefono());
            ps.setString(11,usuario.getDireccion());
            ps.setString(12,usuario.getCorreo());            
            ps.setString(13,usuario.getDigitoDIAN());
            ps.executeUpdate();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }       
    }
    @Override
    public Usuario ObtenerUsuario(long NoDocumento, Object idTipoDocumento) {
        Connection con=null;
        String sql ="select idcta_t,idmunicipio,idtipodocumento,nodocumento, ";
               sql+="       nombre,snombre,apellido,sapellido,razonsocial, ";
               sql+="       sobrenombre,regimen,agenteretenedor,telefono,";
               sql+="       direccion,correo,digitoDIAN ";
               sql+="       from usuario ";
               sql+="       where( ";               
               sql+="         nodocumento="+NoDocumento+" and ";
               sql+="         idtipodocumento="+idTipoDocumento+" ";               
               sql+="       ) limit 1 ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Object idcta_t=rs.getObject(1);
                Object idmunicipio=rs.getObject(2);
                Object idtipodocumento=rs.getObject(3);
                long nodocumento=rs.getLong(4);
                String nombre=rs.getString(5);
                String snombre=rs.getString(6);
                String apellido=rs.getString(7);
                String sapellido=rs.getString(8);
                String razonsocial=rs.getString(9);
                String sobrenombre=rs.getString(10);
                String regimen=rs.getString(11);
                String agenteretenedor=rs.getString(12);
                String telefono=rs.getString(13);
                String direccion=rs.getString(14);
                String correo=rs.getString(15);
                String digitoDIAN=rs.getString(16);
                Municipio m=new Municipio();
                m.setId(idmunicipio);
                TipoDocumento td=new TipoDocumento();
                td.setId(idtipodocumento);
                Usuario u=new Usuario(m,td, NoDocumento, nombre, snombre, apellido, sapellido, razonsocial, sobrenombre, telefono, direccion, correo, regimen, agenteretenedor,digitoDIAN);
                u.setId(idcta_t);
                return u;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<Usuario> ObtenerUsuarios(String busqueda) {
         Connection con=null;
        String sql ="select usuario.idcta_t,usuario.idmunicipio,usuario.idtipodocumento,usuario.nodocumento, ";               
               sql+="       usuario.nombre,usuario.snombre,usuario.apellido,usuario.sapellido,usuario.razonsocial, ";
               sql+="       usuario.sobrenombre,usuario.regimen,usuario.agenteretenedor,usuario.telefono,";
               sql+="       usuario.direccion,usuario.correo,usuario.digitoDIAN, ";
               sql+="       municipio.descripcion,municipio.descripciondpto,municipio.descripcionpais, ";
               sql+="       tipodocumento.descripcion,tipodocumento.abreviatura ";
               sql+="       from usuario,municipio,tipodocumento ";
               sql+="       where( ";               
               sql+="           municipio.idmunicipio=usuario.idmunicipio and ";
               sql+="           tipodocumento.idtipodocumento=usuario.idtipodocumento and ( ";
               sql+="           usuario.NoDocumento like ?  or ";               
               sql+="           Concat_WS(' ',usuario.nombre,usuario.apellido) like ?  or ";               
               sql+="           Concat_WS(' ',usuario.sobrenombre) like ?  or ";   
               sql+="           Concat_WS(' ',usuario.razonsocial) like ?  or ";   
               sql+="           Concat_WS(' ',usuario.nombre,usuario.snombre,usuario.apellido,usuario.sapellido) like ?  ) ";
               sql+="       ) ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,"%"+busqueda+"%");            
            ps.setString(2,"%"+busqueda+"%");  
            ps.setString(3,"%"+busqueda+"%"); 
            ps.setString(4,"%"+busqueda+"%"); 
            ps.setString(5,"%"+busqueda+"%"); 
            ResultSet rs=ps.executeQuery();
            Vector <Usuario> lista=new Vector <Usuario>();
            while(rs.next()){
                Object idcta_t=rs.getObject(1);
                Object idmunicipio=rs.getObject(2);
                Object idtipodocumento=rs.getObject(3);
                long nodocumento=rs.getLong(4);
                String nombre=rs.getString(5);
                String snombre=rs.getString(6);
                String apellido=rs.getString(7);
                String sapellido=rs.getString(8);
                String razonsocial=rs.getString(9);
                String sobrenombre=rs.getString(10);
                String regimen=rs.getString(11);
                String agenteretenedor=rs.getString(12);
                String telefono=rs.getString(13);
                String direccion=rs.getString(14);
                String correo=rs.getString(15);
                String digitoDIAN=rs.getString(16);
                Municipio m=new Municipio();
                m.setId(idmunicipio);
                String descripcion=rs.getString(17);
                m.setDescripcion(descripcion);
                String descripciondpto=rs.getString(18);
                m.setDescripciondpto(descripciondpto);
                String descripcionpais=rs.getString(19);
                m.setDescripcionpais(descripcionpais);
                TipoDocumento td=new TipoDocumento();
                td.setId(idtipodocumento);
                String descripciontd=rs.getString(20);
                td.setDescripcion(descripciontd);
                String abreviatura=rs.getString(21);
                td.setAbreviatura(abreviatura);
                Usuario u=new Usuario(m,td,nodocumento, nombre, snombre, apellido, sapellido, razonsocial, sobrenombre, telefono, direccion, correo, regimen, agenteretenedor,digitoDIAN);
                u.setId(idcta_t);
                lista.add(u);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Municipio PersistirMunicipio(Municipio municipio) {
        String sql =" insert into municipio ";
               sql+=" (idmunicipio,descripcion,iddpto,descripciondpto,idpais,descripcionpais)";
               sql+="values ("+municipio.getId()+",?,"+municipio.getIddpto()+",?,"+municipio.getIdpais()+",?)";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);            
            ps.setString(1,municipio.getDescripcion());
            ps.setString(2,municipio.getDescripciondpto());
            ps.setString(3,municipio.getDescripcionpais());
            ps.executeUpdate();
            return municipio;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Municipio ObtenerMunicipio(Object idMunicipio) {
        Connection con=null;
        String sql ="select idmunicipio,descripcion,iddpto,descripciondpto,idpais,descripcionpais ";
               sql+="from municipio where(idmunicipio="+idMunicipio+") limit 1";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Object id=rs.getObject(1);
                String descripcion=rs.getString(2);
                int iddpto=rs.getInt(3);
                String descripciondpto=rs.getString(4);
                int idpais=rs.getInt(5);
                String descripcionpais=rs.getString(6);
                Municipio m=new Municipio();
                m.setId(id);
                m.setDescripcion(descripcion);
                m.setIddpto(iddpto);
                m.setDescripciondpto(descripciondpto);
                m.setIdpais(idpais);
                m.setDescripcionpais(descripcionpais);
                return m;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<Municipio> ObtenerMunicipios(String busqueda) {
        Connection con=null;
        String sql =" select idmunicipio,descripcion,iddpto,descripciondpto,idpais,descripcionpais ";
               sql+=" from municipio where(descripcion like ? or descripciondpto like ? or idmunicipio like ?)  ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,"%"+busqueda+"%");            
            ps.setString(2,"%"+busqueda+"%");  
            ps.setString(3,""+busqueda+""); 
            ResultSet rs=ps.executeQuery();
            Vector <Municipio> lista=new Vector <Municipio>();
            while(rs.next()){
                Object id=rs.getObject(1);
                String descripcion=rs.getString(2);
                int iddpto=rs.getInt(3);
                String descripciondpto=rs.getString(4);
                int idpais=rs.getInt(5);
                String descripcionpais=rs.getString(6);
                Municipio m=new Municipio();
                m.setId(id);
                m.setDescripcion(descripcion);
                m.setIddpto(iddpto);
                m.setDescripciondpto(descripciondpto);
                m.setIdpais(idpais);
                m.setDescripcionpais(descripcionpais);
                lista.add(m);                
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public TipoDocumento PersistirTipoDocumento(TipoDocumento tipodocumento) {
        String sql =" insert into tipodocumento ";
               sql+=" (idtipodocumento,descripcion,abreviatura) values ("+tipodocumento.getId()+",?,?)";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);            
            ps.setString(1,tipodocumento.getDescripcion());
            ps.setString(2,tipodocumento.getAbreviatura());
            ps.executeUpdate();
            return tipodocumento;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public TipoDocumento ObtenerTipoDocumento(Object idTipoDocumento) {
        Connection con=null;
        String sql="select idtipodocumento,descripcion,abreviatura from tipodocumento where(idtipodocumento="+idTipoDocumento+") limit 1";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Object id=rs.getObject(1);
                String descripcion=rs.getString(2);
                String abreviatura=rs.getString(3);
                TipoDocumento td=new TipoDocumento();
                td.setId(id);
                td.setAbreviatura(abreviatura);
                td.setDescripcion(descripcion);
                return td;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<TipoDocumento> ObtenerTipoDocumentos(String busqueda) {
        Connection con=null;
        String sql =" select idtipodocumento,descripcion,abreviatura from tipodocumento ";
               sql+=" where(abreviatura like '"+busqueda+"%' or descripcion like '%"+busqueda+"%')";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            Vector <TipoDocumento> lista=new Vector <TipoDocumento>();
            while(rs.next()){
                Object id=rs.getObject(1);
                String descripcion=rs.getString(2);
                String abreviaturaa=rs.getString(3);
                TipoDocumento td=new TipoDocumento();
                td.setId(id);
                td.setAbreviatura(abreviaturaa);
                td.setDescripcion(descripcion);
                lista.add(td);                
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public TipoDocumento ObtenerTipoDocumento(String abreviatura) {
        Connection con=null;
        String sql="select idtipodocumento,descripcion,abreviatura from tipodocumento where(abreviatura='"+abreviatura+"') limit 1";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Object id=rs.getObject(1);
                String descripcion=rs.getString(2);
                String abreviaturaa=rs.getString(3);
                TipoDocumento td=new TipoDocumento();
                td.setId(id);
                td.setAbreviatura(abreviaturaa);
                td.setDescripcion(descripcion);
                return td;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Usuario ModificarUsuario(Object id, Usuario usuario) {
        String sql =" update usuario ";
               sql+="set idcta_t="+id+",idmunicipio="+usuario.getMunicipio().getId()+",idTipoDocumento="+usuario.getTipodocumento().getId()+",nodocumento=?,nombre=?,snombre=?,apellido=?,sapellido=?,";
               sql+="razonsocial=?,sobrenombre=?,regimen=?,agenteretenedor=?,telefono=?,direccion=?,correo=?,digitodian=? ";
               sql+="where(usuario.idcta_t=?) ";            
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);            
            ps.setLong(1,usuario.getNoDocumento());
            ps.setString(2,usuario.getNombre());
            ps.setString(3,usuario.getsNombre());
            ps.setString(4,usuario.getApellido());
            ps.setString(5,usuario.getsApellido());
            ps.setString(6,usuario.getRazonSocial());
            ps.setString(7,usuario.getSobreNombre());
            ps.setString(8,usuario.getRegimen());
            ps.setString(9,usuario.getAgenteRetenedor());
            ps.setString(10,usuario.getTelefono());
            ps.setString(11,usuario.getDireccion());
            ps.setString(12,usuario.getCorreo());            
            ps.setString(13,usuario.getDigitoDIAN());
            ps.setObject(14,id);
            ps.executeUpdate();
            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }       
    }

    @Override
    public Usuario ObtenerUsuario(Object idUsuario) {
         Connection con=null;
        String sql ="select idcta_t,idmunicipio,idtipodocumento,nodocumento, ";
               sql+="       nombre,snombre,apellido,sapellido,razonsocial, ";
               sql+="       sobrenombre,regimen,agenteretenedor,telefono,";
               sql+="       direccion,correo,digitoDIAN ";
               sql+="       from usuario ";
               sql+="       where( ";               
               sql+="         idcta_t="+idUsuario+"  ";               
               sql+="       ) limit 1 ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Object idcta_t=rs.getObject(1);
                Object idmunicipio=rs.getObject(2);
                Object idtipodocumento=rs.getObject(3);
                long nodocumento=rs.getLong(4);
                String nombre=rs.getString(5);
                String snombre=rs.getString(6);
                String apellido=rs.getString(7);
                String sapellido=rs.getString(8);
                String razonsocial=rs.getString(9);
                String sobrenombre=rs.getString(10);
                String regimen=rs.getString(11);
                String agenteretenedor=rs.getString(12);
                String telefono=rs.getString(13);
                String direccion=rs.getString(14);
                String correo=rs.getString(15);
                String digitoDIAN=rs.getString(16);
                Municipio m=new Municipio();
                m.setId(idmunicipio);
                TipoDocumento td=new TipoDocumento();
                td.setId(idtipodocumento);
                Usuario u=new Usuario(m,td,nodocumento, nombre, snombre, apellido, sapellido, razonsocial, sobrenombre, telefono, direccion, correo, regimen, agenteretenedor,digitoDIAN);
                u.setId(idcta_t);
                return u;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
}

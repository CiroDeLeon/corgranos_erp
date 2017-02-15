/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Dominio.Core.Usuario;

import java.util.Vector;
import sic.Dominio.Core.Cta_T.CtaT_Service;
import sic.Dominio.Core.Cta_T.Cta_T;
import sic.InterfacesDAO.IUsuarioDAO;

/**
 *
 * @author FANNY BURGOS
 */
public class UsuarioService extends CtaT_Service{
    private IUsuarioDAO dao;    
    public  UsuarioService(){
        dao=new sic.Infraestructura.JDBC.Impl.Mysql.UsuarioDAO();
    }    
    public  boolean InsertarTipoDocumento(Object id,String descripcion,String abreviatura){
        if(getDao().ObtenerTipoDocumento(id)==null){
            if(getDao().ObtenerTipoDocumento(abreviatura)==null){
                if(getDao().ObtenerTipoDocumento(abreviatura)==null){
                   TipoDocumento td=new TipoDocumento();
                   td.setId(id);
                   td.setAbreviatura(abreviatura);
                   td.setDescripcion(descripcion);
                   if(getDao().PersistirTipoDocumento(td)!=null){
                      mensaje="Tipo de Documento Ingresado Con EXITO"; 
                      return true;    
                   }else{
                      mensaje="Hubo Un Error";  
                      return false;
                   }                   
                }else{
                    mensaje="La abreviatura debe ser de Maximo dos letras";
                    return false;
                 }   
            }else{
                mensaje="Esa Abreviatura ya esta en Uso";
                return false;
            }
        }else{
            mensaje="Ya Existe Ese Tipo de Documento";
            return false;
        }
    }
    public  boolean InsertarMunicipio(Object id,String descripcion,int iddpto,String desdpto,int idpais,String despais){
        if(getDao().ObtenerMunicipio(id)==null){
            Municipio m=new Municipio();
            m.setId(id);
            m.setDescripcion(descripcion);
            m.setIddpto(iddpto);
            m.setDescripciondpto(desdpto);
            m.setIdpais(idpais);
            m.setDescripcionpais(despais);
            if(getDao().PersistirMunicipio(m)!=null){
                mensaje="Ingresado el Municipio con EXITO";
                return true;
            }else{
                mensaje="Hubo un Error";
                return false;
            }
        }else{
            mensaje="Ya Existe Ese Municipio";
            return false;
        } 
    }
    public Vector<TipoDocumento> ObtenerTiposDeDocumentos(String busqueda){        
        return getDao().ObtenerTipoDocumentos(busqueda);
    }
      public Vector<Usuario> ObtenerUsuarios(String busqueda){        
        return getDao().ObtenerUsuarios(busqueda);
    }
    public Vector<Municipio> ObtenerMunicipios(String busqueda){
        return getDao().ObtenerMunicipios(busqueda);
    }
    public boolean InsertarUsuario(
            Municipio municipio, TipoDocumento tipodocumento,
            long NoDocumento, String Nombre, String sNombre,
            String Apellido, String sApellido, String RazonSocial,
            String SobreNombre, String telefono, String direccion,
            String correo, String Regimen, String AgenteRetenedor,String digitoDIAN){
            
        Usuario p=getDao().ObtenerUsuario(NoDocumento, tipodocumento.getId());            
        if(p==null){            
            Usuario u=new Usuario(municipio, tipodocumento, NoDocumento, Nombre, sNombre, Apellido, sApellido, RazonSocial, SobreNombre, telefono, direccion, correo, Regimen, AgenteRetenedor,digitoDIAN);
            if(this.InsertarCtaT(u.getDescripcion())==true){
               Cta_T t=this.ObtenerCtaT(u.getDescripcion());
               u.setId(t.getId());
                getDao().PersistirUsuario(u);
               mensaje="Ingresado Usuario Con Exito";
               return true;
            }
            mensaje="Hubo Un Error";
            return false;
        }else{
            mensaje="Ese No Documento ya Existe";
            return false;
        }
        
    }

    /**
     * @return the dao
     */
    public IUsuarioDAO getDao() {
        return dao;
    }
public boolean ModificarUsuario(
            Municipio municipio, TipoDocumento tipodocumento,
            long NoDocumento, String Nombre, String sNombre,
            String Apellido, String sApellido, String RazonSocial,
            String SobreNombre, String telefono, String direccion,
            String correo, String Regimen, String AgenteRetenedor,String digitoDIAN,Object id){
        
           Usuario u=new Usuario(municipio, tipodocumento, NoDocumento, Nombre, sNombre, Apellido, sApellido, RazonSocial, SobreNombre, telefono, direccion, correo, Regimen, AgenteRetenedor, digitoDIAN);
           if(this.dao.ModificarUsuario(id,u)!=null){
               this.ModificarCtaT(id,u);
               this.mensaje="Modificado con Exito este Usuario";
               return true;
           }else{
               this.mensaje="No Se Pudo Modificar";
               return false;
           }
    }    
}

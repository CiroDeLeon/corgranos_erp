/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.InterfacesDAO;

import java.util.Vector;
import sic.Dominio.Core.Usuario.Municipio;
import sic.Dominio.Core.Usuario.TipoDocumento;
import sic.Dominio.Core.Usuario.Usuario;

/**
 *
 * @author FANNY BURGOS
 */
public interface IUsuarioDAO {
    Usuario PersistirUsuario(Usuario usuario);
    Usuario ObtenerUsuario(long nodocumento,Object idTipoDocumento);    
    Usuario ObtenerUsuario(Object idUsuario);    
    Usuario ModificarUsuario(Object id,Usuario u);
    Vector<Usuario> ObtenerUsuarios(String busqueda);
    Municipio PersistirMunicipio(Municipio municipio);
    Municipio ObtenerMunicipio(Object idMunicipio);
    Vector<Municipio> ObtenerMunicipios(String busqueda);
    TipoDocumento PersistirTipoDocumento(TipoDocumento tipodocumento);
    TipoDocumento ObtenerTipoDocumento(Object idTipoDocumento);
    TipoDocumento ObtenerTipoDocumento(String abreviatura);
    Vector<TipoDocumento> ObtenerTipoDocumentos(String busqueda);   
}

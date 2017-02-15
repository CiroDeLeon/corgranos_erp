/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.InterfacesDAO;

import corgranos.Dominio.Core.Remision.Remision;
import java.util.Vector;

/**
 *
 * @author FANNY BURGOS
 */
public interface IRemisionDAO {
    Remision PersistirRemision(Remision r);
    long ObtenerIdUltimaRemision();
    Remision ObtenerRemision(Object id);
    Vector <Remision> ObtenerRemisionesNoLegalizadasEnFactura(Object idUsuario);
    public Remision ModificarRemision(Remision r);    
    int FueLegalizadaEnFactura(Object idRemision);
}

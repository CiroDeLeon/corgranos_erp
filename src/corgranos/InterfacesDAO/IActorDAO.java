/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.InterfacesDAO;

import corgranos.Servicios.Login.Actor;
import java.util.Vector;

/**
 *
 * @author FANNY BURGOS
 */
public interface IActorDAO {
   Actor PersistirActor(Actor actor);
   Actor ObtenerActor(Object idActor);
   Vector<Actor> BuscarActores(String CampoBusqueda);
   Actor ModificarActor(Object idActor,Actor actor);
   Actor ObtenerActor(String login);
}

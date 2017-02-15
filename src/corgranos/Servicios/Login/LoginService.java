/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Servicios.Login;

import corgranos.Infraestructura.JDBC.Impl.ActorDAO;
import corgranos.InterfacesDAO.IActorDAO;
import java.util.Vector;

/**
 *
 * @author FANNY BURGOS
 */
public class LoginService {
    IActorDAO dao;
    private String mensaje;
    public LoginService() {
        dao=new ActorDAO();
    }

   public boolean IngresarActor(String login,String clave,String rol,String nombre,boolean activo){
       return false;
   }    
   boolean ModificarActor(Object idActor,Actor actor){
       return true;
   }
   Vector<Actor> BusquedaIncremental(String CampoDeBusqueda){
       return null;
   }
   public Actor HacerLogin(String login,String clave){
       Actor a=dao.ObtenerActor(login);
       if(a!=null){
           if(clave.equals(""+a.getClave().toLowerCase())){
               return a;
           }else{
               mensaje="Clave Incorrecta";
               return null;
           }
       }else{
          mensaje="Ese Login No Existe en la Basa de Datos";
          return null;
       }
   }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }
}

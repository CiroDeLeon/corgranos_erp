/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Remision;

import corgranos.Dominio.Core.Articulo.Articulo;
import corgranos.Dominio.Core.Maquila.Maquila;
import corgranos.Infraestructura.JDBC.Impl.RemisionDAO;
import corgranos.InterfacesDAO.IRemisionDAO;
import java.util.Date;
import sic.Dominio.Core.Usuario.Usuario;

/**
 *
 * @author FANNY BURGOS
 */
public class RemisionService {
    private IRemisionDAO dao; 
    private String mensaje="";
    public RemisionService() {
        dao=new RemisionDAO();
    }
    public IRemisionDAO getDao() {
        return dao;
    }    
    public boolean IngresarRemision(Maquila maquila, Articulo articulo, Usuario usuario, Date fechacontable,String creador,boolean activo, double entradas, double salidas,String detalle){
        long id=dao.ObtenerIdUltimaRemision();
        Remision r=new Remision(maquila, articulo, usuario, fechacontable,creador,entradas, salidas,detalle);
        r.setId(++id);
        if(dao.PersistirRemision(r)!=null){
            this.mensaje=" Ingresada la Remision con Exito";
            return true;
        }
        this.mensaje=" Hubo un Error No se Pudo Ingresar la Remision";
        return false;
    }
    public void ModificarRemision(long id,Maquila maquila, Articulo articulo, Usuario usuario, Date fechacontable,String creador,boolean activo, double entradas, double salidas,String detalle,Date fechaanulacion,String anulador){        
        Remision r=new Remision(maquila, articulo, usuario, fechacontable,creador,entradas, salidas,detalle);
        r.setId(id);
        r.setFechaanulacion(fechaanulacion);
        r.setAnulador(anulador);        
        ModificarRemision(r);
    }
    public boolean ModificarRemision(Remision r){
        if(dao.ModificarRemision(r)!=null){
            this.mensaje="Modificada Con Exito la Remision";
            return true;
        }else{
            this.mensaje="No se Pudo Modificar la Remision";
            return false;
        }
    }
    public void AnularRemision(Object id,String anulador,Date fechaanulacion){
        Remision r=dao.ObtenerRemision(id);
        r.setAnulador(anulador);
        r.setFechaanulacion(fechaanulacion);
        r.setActivo(false);
        this.ModificarRemision(r);
    }
    public String getMensaje() {
        return mensaje;
    }
}

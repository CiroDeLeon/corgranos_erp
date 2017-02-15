/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Servicios;

import java.util.Date;
import sic.Infraestructura.JDBC.Impl.Mysql.FechaDAO;
import sic.InterfacesDAO.IFechaDAO;

/**
 *
 * @author FANNY BURGOS
 */
public class FechaService {
    private IFechaDAO dao;
    public FechaService() {
        dao=new FechaDAO();
    }
    public int RestarFechasPorDias(Date f1,Date f2){
        long tf1=f1.getTime();
        long tf2=f2.getTime();
        long r=Math.abs(tf2-tf1);
        long dia=60*60*24*1000;        
        return Math.round(r/dia);
    }
    public Date getFechaActual(){
        return dao.ObtenerFechaDelServidor();
    }
    /**
     * @return the dao
     */
    public IFechaDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(IFechaDAO dao) {
        this.dao = dao;
    }
    
}

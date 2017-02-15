/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Infraestructura.JDBC.Impl;

import corgranos.Dominio.Servicios.DataConfiguration;
import corgranos.Infraestructura.JDBC.Pool;
import corgranos.InterfacesDAO.IDataConfigurationDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FANNY BURGOS
 */
public class DataConfigurationDAO implements IDataConfigurationDAO{
    @Override
    public DataConfiguration PersistirConfiguracion(DataConfiguration dc) {
 String sql =" insert into dataconfiguration ";
        sql+=" (iddataconfiguration,Aux_Clientes,Aux_Iva,";//3
        sql+="  Aux_Proveedor,Aux_Paddy,Aux_Retefuente,Aux_Caja, ";//4
        sql+="  Aux_BolsaPorPagar,Aux_GastoBolsa,PorcentageBolsa,Aux_FomentoPorPagar,";//4
        sql+="  Aux_GastoFomento,PorcentageFomento,idcta_t_usuarioprod,PorcentageRetefuente,dian1,dian2,Aux_Trilla,aux_descargues) ";//3
        sql+="  values ";
        sql+=" ("+dc.getId()+",?,?, ?,?,?,?, ?,?,?,?, ?,?,"+dc.getIdUsuarioProduccion()+",?,?,?,?,?)";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                        
            ps.setString(1,dc.getAux_Clientes());
            ps.setString(2,dc.getAux_Iva());
            ps.setString(3,dc.getAux_Proveedor());
            ps.setString(4,dc.getAux_Paddy());
            ps.setString(5,dc.getAux_Retefuente());
            ps.setString(6,dc.getAux_Caja());
            ps.setString(7,dc.getAux_BolsaPorPagar());
            ps.setString(8,dc.getAux_GastoBolsa());
            ps.setDouble(9,dc.getPorcentageBolsa());
            ps.setString(10,dc.getAux_FomentoPorPagar());
            ps.setString(11,dc.getAux_GastoFomento());
            ps.setDouble(12,dc.getPorcentageFomento());            
            ps.setDouble(13,dc.getPorcentageRetefuente());
            ps.setString(14,dc.getDian1());
            ps.setString(15,dc.getDian2());
            ps.setString(16,dc.getAux_Trilla());
            ps.setString(17,dc.getAux_descargues());
            ps.executeUpdate();
            return dc;
        } catch (SQLException ex) {
            Logger.getLogger(DataConfigurationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public DataConfiguration ObtenerConfiguracion() {
        String sql =" select Aux_Clientes,Aux_Iva,";               
               sql+=" Aux_Proveedor,Aux_Paddy,Aux_Retefuente,Aux_Caja,";
               sql+=" Aux_BolsaPorPagar,Aux_GastoBolsa,PorcentageBolsa,Aux_FomentoPorPagar, ";
               sql+=" Aux_GastoFomento,PorcentageFomento,idcta_t_usuarioprod,PorcentageRetefuente,dian1,dian2,aux_trilla,aux_descargues ";
               sql+=" from dataconfiguration where(iddataconfiguration="+DataConfiguration.configuracion+")";
               sql+=" limit 1";
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();            
            if(rs.next()){                
                DataConfiguration dc=new DataConfiguration();
                dc.setAux_Clientes(rs.getString(1));
                dc.setAux_Iva(rs.getString(2));
                dc.setAux_Proveedor(rs.getString(3));
                dc.setAux_Paddy(rs.getString(4));
                dc.setAux_Retefuente(rs.getString(5));
                dc.setAux_Caja(rs.getString(6));
                dc.setAux_BolsaPorPagar(rs.getString(7));
                dc.setAux_GastoBolsa(rs.getString(8));
                dc.setPorcentageBolsa(rs.getDouble(9));
                dc.setAux_FomentoPorPagar(rs.getString(10));
                dc.setAux_GastoFomento(rs.getString(11));
                dc.setPorcentageFomento(rs.getDouble(12));
                dc.setIdUsuarioProduccion(rs.getObject(13));
                dc.setPorcentageRetefuente(rs.getDouble(14));
                dc.setDian1(rs.getString(15));
                dc.setDian2(rs.getString(16));
                dc.setAux_Trilla(rs.getString(17));
                dc.setAux_descargues(rs.getString(18));
                return dc;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DataConfigurationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public DataConfiguration ModificarConfiguracion(DataConfiguration dc) {
        String sql =" update dataconfiguration set Aux_Clientes=?,Aux_Iva=?,";               
               sql+=" Aux_Proveedor=?,Aux_Paddy=?,Aux_Retefuente=?,Aux_Caja=?,";
               sql+=" Aux_BolsaPorPagar=?,Aux_GastoBolsa=?,PorcentageBolsa=?,Aux_FomentoPorPagar=?, ";
               sql+=" Aux_GastoFomento=?,PorcentageFomento=?,idcta_t_usuarioprod="+dc.getIdUsuarioProduccion()+", ";
               sql+=" PorcentageRetefuente=?,dian1=?,dian2=?,aux_trilla=?,aux_descargues=? ";
               sql+=" where(iddataconfiguration="+dc.getId()+")";
               
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                        
            ps.setString(1,dc.getAux_Clientes());
            ps.setString(2,dc.getAux_Iva());
            ps.setString(3,dc.getAux_Proveedor());
            ps.setString(4,dc.getAux_Paddy());
            ps.setString(5,dc.getAux_Retefuente());
            ps.setString(6,dc.getAux_Caja());
            ps.setString(7,dc.getAux_BolsaPorPagar());
            ps.setString(8,dc.getAux_GastoBolsa());
            ps.setDouble(9,dc.getPorcentageBolsa());
            ps.setString(10,dc.getAux_FomentoPorPagar());
            ps.setString(11,dc.getAux_GastoFomento());
            ps.setDouble(12,dc.getPorcentageFomento());  
            ps.setDouble(13,dc.getPorcentageRetefuente());  
            ps.setString(14,dc.getDian1());
            ps.setString(15,dc.getDian2());
            ps.setString(16,dc.getAux_Trilla());
            ps.setString(17,dc.getAux_descargues());
            ps.executeUpdate();
            return dc;
        } catch (SQLException ex) {
            Logger.getLogger(DataConfigurationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }    
}

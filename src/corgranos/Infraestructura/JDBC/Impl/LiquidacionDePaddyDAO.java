/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Infraestructura.JDBC.Impl;

import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddy;
import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddyPorMaquilaDTO;
import corgranos.Infraestructura.JDBC.Pool;
import corgranos.InterfacesDAO.ILiquidacionDePaddyDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class LiquidacionDePaddyDAO implements ILiquidacionDePaddyDAO{
    @Override
    public LiquidacionDePaddy PersistirLiquidacion(LiquidacionDePaddy lp) {        
 String sql ="insert into liquidaciondepaddy ";
        sql+="(iddocumento,bultos,tiquete,humedad_pact,impureza_pact,rojo_pact,partido_pact,";//7
        sql+=" yeso_pact,harina_pact,humedad_lab,impureza_lab,rojo_lab,partido_lab,yeso_lab,";//7
        sql+=" harina_lab,pesobruto,pesodestarado,peso_liquidado,peso14_1,valor_kg,";//6
        sql+=" valor_liquidado,valor_compra,fomento,bolsa,retefuente,ajuste,total,polipropileno,idcta_t_usuario,masablanca,activo,numeracion,proveedor,nodocumento,fechacontable,aux_proveedor,valor_descargue,aux_descargue)";//8
        sql+=" values ";
        sql+=" ("+lp.getId()+",?,?,?,?,?,?,?, ?,?,?,?,?,?,?, ?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,true,?,?,?,?,"+lp.getAux_proveedor()+",?,?) ";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                        
            ps.setDouble(1,lp.getBultos());
            ps.setInt(2,lp.getTiquete());
            ps.setDouble(3,lp.getHumedad_pact());
            ps.setDouble(4,lp.getImpureza_pact());
            ps.setDouble(5,lp.getRojo_pact());
            ps.setDouble(6,lp.getPartido_pact());
            ps.setDouble(7,lp.getYeso_pact());
            ps.setDouble(8,lp.getHarina_pact());
            ps.setDouble(9,lp.getHumedad_lab());
            ps.setDouble(10,lp.getImpureza_lab());
            ps.setDouble(11,lp.getRojo_lab());
            ps.setDouble(12,lp.getPartido_lab());
            ps.setDouble(13,lp.getYeso_lab());
            ps.setDouble(14,lp.getHarina_lab());
            ps.setDouble(15,lp.getPesobruto());
            ps.setDouble(16,lp.getPesodestarado());
            ps.setDouble(17,lp.getPeso_liquidado());
            ps.setDouble(18,lp.getPeso14_1());
            ps.setDouble(19,lp.getValor_kg());
            ps.setDouble(20,lp.getValor_liquidado());
            ps.setDouble(21,lp.getValor_compra());
            ps.setDouble(22,lp.getFomento());
            ps.setDouble(23,lp.getBolsa());
            ps.setDouble(24,lp.getRetefuente());
            ps.setDouble(25,lp.getAjuste());
            ps.setDouble(26,lp.getTotal());
            ps.setBoolean(27,lp.isPolipropileno());            
            ps.setObject(28,lp.getIdcta_t_usuario());
            ps.setDouble(29,lp.getMasablanca());
            ps.setDouble(30,lp.getNumeracion());
            ps.setString(31,lp.getUsuario().NombreCompleto());
            ps.setLong(32,lp.getUsuario().getNoDocumento());
            ps.setDate(33,new java.sql.Date(lp.getFechaContable().getTime()));            
            ps.setDouble(34,lp.getValor_descargue());
            ps.setString(35,lp.getAux_descargue());
            ps.executeUpdate();
            return lp;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDePaddyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public LiquidacionDePaddy ObtenerLiquidacion(int NoTiquete) {
         String sql =" select ";               
                sql+=" iddocumento,";         
                sql+=" bultos,";
                sql+=" tiquete,";
                sql+=" humedad_pact,";
                sql+=" impureza_pact,";
                sql+=" rojo_pact,";
                sql+=" partido_pact,";
                sql+=" yeso_pact,";
                sql+=" harina_pact,";
                sql+=" humedad_lab,";
                sql+=" impureza_lab,";
                sql+=" rojo_lab,";
                sql+=" partido_lab,";
                sql+=" yeso_lab,";
                sql+=" harina_lab,";
                sql+=" pesobruto,";
                sql+=" pesodestarado,";
                sql+=" peso_liquidado,";
                sql+=" peso14_1,";
                sql+=" valor_kg,";
                sql+=" valor_liquidado,";
                sql+=" valor_compra,";
                sql+=" fomento,";
                sql+=" bolsa,";
                sql+=" retefuente,";
                sql+=" ajuste,";
                sql+=" total,";
                sql+=" polipropileno,";
                sql+=" idcta_t_usuario,";
                sql+=" masablanca,";
                sql+=" activo,";
                sql+=" numeracion,";
                sql+=" proveedor,";
                sql+=" nodocumento, ";
                sql+=" fechacontable, ";
                sql+=" aux_proveedor,valor_descargue,aux_descargue ";
                sql+=" from ";
                sql+=" liquidaciondepaddy";
                sql+=" where(";
                sql+="     activo=true and";
                sql+="     tiquete=? ";
                sql+=" )";
                
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, NoTiquete);
            ResultSet rs=ps.executeQuery();            
            if(rs.next()){                
               LiquidacionDePaddy lp=new LiquidacionDePaddy();
               lp.setId(rs.getObject(1));
               lp.setBultos(rs.getDouble(2));
               lp.setTiquete(rs.getInt(3));
               lp.setHumedad_pact(rs.getDouble(4));
               lp.setImpureza_pact(rs.getDouble(5));
               lp.setRojo_pact(rs.getDouble(6));
               lp.setPartido_pact(rs.getDouble(7));
               lp.setYeso_pact(rs.getDouble(8));
               lp.setHarina_pact(rs.getDouble(9));
               lp.setHumedad_lab(rs.getDouble(10));
               lp.setImpureza_lab(rs.getDouble(11));
               lp.setRojo_lab(rs.getDouble(12));
               lp.setPartido_lab(rs.getDouble(13));
               lp.setYeso_lab(rs.getDouble(14));
               lp.setHarina_lab(rs.getDouble(15));
               lp.setPesobruto(rs.getDouble(16));
               lp.setPesodestarado(rs.getDouble(17));
               lp.setPeso_liquidado(rs.getDouble(18));
               lp.setPeso14_1(rs.getDouble(19));
               lp.setValor_kg(rs.getDouble(20));
               lp.setValor_liquidado(rs.getDouble(21));
               lp.setValor_compra(rs.getDouble(22));
               lp.setFomento(rs.getDouble(23));
               lp.setBolsa(rs.getDouble(24));
               lp.setRetefuente(rs.getDouble(25));
               lp.setAjuste(rs.getDouble(26));
               lp.setTotal(rs.getDouble(27));
               lp.setPolipropileno(rs.getBoolean(28));
               lp.setIdcta_t_usuario(rs.getObject(29));
               lp.setMasablanca(rs.getDouble(30));
               lp.setActivo(rs.getBoolean(31));
               lp.setNumeracion(rs.getInt(32));
               Usuario u=new Usuario();
               u.setNombre(rs.getString(33));
               u.setNoDocumento(rs.getLong(34));
               lp.setUsuario(u);
               lp.setFechaContable(new java.util.Date(rs.getDate(35).getTime()));
               lp.setAux_proveedor(rs.getString(36));
               lp.setValor_descargue(rs.getDouble(37));
               lp.setAux_descargue(rs.getString(38));
               return lp;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDePaddyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<LiquidacionDePaddyPorMaquilaDTO> ObtenerLiquidacionesDePaddyLibres(String busqueda) {
         String sql =" select ";               
                sql+=" numeracion,";
                sql+=" tiquete,";
                sql+=" nodocumento, ";
                sql+=" proveedor,";
                sql+=" humedad_lab,";
                sql+=" impureza_lab,";
                sql+=" rojo_lab,";
                sql+=" partido_lab,";
                sql+=" yeso_lab,";
                sql+=" harina_lab,";
                sql+=" pesodestarado,";
                sql+=" peso14_1,";
                sql+=" masablanca,";
                sql+=" valor_compra,";                
                sql+=" fechacontable,";                
                sql+=" iddocumento, ";         
                sql+=" peso_liquidado, ";
                sql+=" pesobruto ";
                sql+=" from ";
                sql+=" liquidaciondepaddy";
                sql+=" where(";
                sql+="     activo=true and";
                sql+="     idmaquila is null and ";
                sql+="     (tiquete like ? or numeracion like ? or proveedor like ? or nodocumento like ?) ";
                sql+=" )order by fechacontable,numeracion ";
                
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);            
            ps.setString(1,busqueda+"%");
            ps.setString(2,busqueda+"%");
            ps.setString(3,"%"+busqueda+"%");
            ps.setString(4,busqueda+"%");
            ResultSet rs=ps.executeQuery();            
            Vector <LiquidacionDePaddyPorMaquilaDTO> lista=new Vector <LiquidacionDePaddyPorMaquilaDTO>();
            while(rs.next()){          
               LiquidacionDePaddyPorMaquilaDTO lp=new LiquidacionDePaddyPorMaquilaDTO();
               lp.setLiquidacion(rs.getInt(1));
               lp.setTiquete(rs.getInt(2));
               lp.setNit(rs.getLong(3));
               lp.setUsuario(rs.getString(4));
               lp.setHumedad(rs.getDouble(5));
               lp.setImpureza(rs.getDouble(6));
               lp.setRojo(rs.getDouble(7));
               lp.setPartido(rs.getDouble(8));
               lp.setYeso(rs.getDouble(9));
               lp.setHarina(rs.getDouble(10));
               lp.setPesodestarado(rs.getDouble(11));
               lp.setPeso14_1(rs.getDouble(12));
               lp.setMasablanca(rs.getDouble(13));
               lp.setValor(rs.getDouble(14));
               lp.setFechacontable(new java.util.Date(rs.getDate(15).getTime()));
               lp.setIdDocumento(rs.getObject(16));               
               lp.setPesoliquidado(rs.getDouble(17));
               lp.setPesobruto(rs.getDouble(18));
               lista.add(lp);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDePaddyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<LiquidacionDePaddyPorMaquilaDTO> ObtenerLiquidacionesDePaddy(Object idMaquila) {
        String sql =" select ";               
                sql+=" numeracion,";
                sql+=" tiquete,";
                sql+=" nodocumento, ";
                sql+=" proveedor,";
                sql+=" humedad_lab,";
                sql+=" impureza_lab,";
                sql+=" rojo_lab,";
                sql+=" partido_lab,";
                sql+=" yeso_lab,";
                sql+=" harina_lab,";
                sql+=" pesodestarado,";
                sql+=" peso14_1,";
                sql+=" masablanca,";
                sql+=" valor_compra,";                
                sql+=" fechacontable,";                
                sql+=" iddocumento, ";         
                sql+=" peso_liquidado, ";
                sql+=" pesobruto ";
                sql+=" from ";
                sql+=" liquidaciondepaddy";
                sql+=" where(";
                sql+="     activo=true and";
                sql+="     idmaquila="+idMaquila+" ";                
                sql+=" )order by fechacontable,numeracion ";
                
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);                        
            ResultSet rs=ps.executeQuery();            
            Vector <LiquidacionDePaddyPorMaquilaDTO> lista=new Vector <LiquidacionDePaddyPorMaquilaDTO>();
            while(rs.next()){          
               LiquidacionDePaddyPorMaquilaDTO lp=new LiquidacionDePaddyPorMaquilaDTO();
               lp.setLiquidacion(rs.getInt(1));
               lp.setTiquete(rs.getInt(2));
               lp.setNit(rs.getLong(3));
               lp.setUsuario(rs.getString(4));
               lp.setHumedad(rs.getDouble(5));
               lp.setImpureza(rs.getDouble(6));
               lp.setRojo(rs.getDouble(7));
               lp.setPartido(rs.getDouble(8));
               lp.setYeso(rs.getDouble(9));
               lp.setHarina(rs.getDouble(10));
               lp.setPesodestarado(rs.getDouble(11));
               lp.setPeso14_1(rs.getDouble(12));
               lp.setMasablanca(rs.getDouble(13));
               lp.setValor(rs.getDouble(14));
               lp.setFechacontable(new java.util.Date(rs.getDate(15).getTime()));
               lp.setIdDocumento(rs.getObject(16));
               lp.setPesoliquidado(rs.getDouble(17));
               lp.setPesobruto(rs.getDouble(18));
               int maquila=Integer.parseInt(idMaquila.toString());
               lp.setMaquila(maquila);
               lista.add(lp);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDePaddyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<LiquidacionDePaddy> ObtenerLiquidacionesDePaddy(Date fechainicial, Date fechafinal) {
        String sql  =" select ";               
                sql+=" iddocumento,";         
                sql+=" bultos,";
                sql+=" tiquete,";
                sql+=" humedad_pact,";
                sql+=" impureza_pact,";
                sql+=" rojo_pact,";
                sql+=" partido_pact,";
                sql+=" yeso_pact,";
                sql+=" harina_pact,";
                sql+=" humedad_lab,";
                sql+=" impureza_lab,";
                sql+=" rojo_lab,";
                sql+=" partido_lab,";
                sql+=" yeso_lab,";
                sql+=" harina_lab,";
                sql+=" pesobruto,";
                sql+=" pesodestarado,";
                sql+=" peso_liquidado,";
                sql+=" peso14_1,";
                sql+=" valor_kg,";
                sql+=" valor_liquidado,";
                sql+=" valor_compra,";
                sql+=" fomento,";
                sql+=" bolsa,";
                sql+=" retefuente,";
                sql+=" ajuste,";
                sql+=" total,";
                sql+=" polipropileno,";
                sql+=" idcta_t_usuario,";
                sql+=" masablanca,";
                sql+=" activo,";
                sql+=" numeracion,";
                sql+=" proveedor,";
                sql+=" nodocumento, ";
                sql+=" fechacontable, ";
                sql+=" aux_proveedor,valor_descargue,aux_descargue ";
                sql+=" from ";
                sql+=" liquidaciondepaddy";
                sql+=" where(";
                sql+="     activo=true and";         
                sql+="     fechacontable>=? and ";
                sql+="     fechacontable<=? ";
                sql+=" )";
                Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fechainicial.getTime()));
            ps.setDate(2,new java.sql.Date(fechafinal.getTime()));
            ResultSet rs=ps.executeQuery();            
            Vector<LiquidacionDePaddy> lista=new Vector<LiquidacionDePaddy>();
            while(rs.next()){                
               LiquidacionDePaddy lp=new LiquidacionDePaddy();
               lp.setId(rs.getObject(1));
               lp.setBultos(rs.getDouble(2));
               lp.setTiquete(rs.getInt(3));
               lp.setHumedad_pact(rs.getDouble(4));
               lp.setImpureza_pact(rs.getDouble(5));
               lp.setRojo_pact(rs.getDouble(6));
               lp.setPartido_pact(rs.getDouble(7));
               lp.setYeso_pact(rs.getDouble(8));
               lp.setHarina_pact(rs.getDouble(9));
               lp.setHumedad_lab(rs.getDouble(10));
               lp.setImpureza_lab(rs.getDouble(11));
               lp.setRojo_lab(rs.getDouble(12));
               lp.setPartido_lab(rs.getDouble(13));
               lp.setYeso_lab(rs.getDouble(14));
               lp.setHarina_lab(rs.getDouble(15));
               lp.setPesobruto(rs.getDouble(16));
               lp.setPesodestarado(rs.getDouble(17));
               lp.setPeso_liquidado(rs.getDouble(18));
               lp.setPeso14_1(rs.getDouble(19));
               lp.setValor_kg(rs.getDouble(20));
               lp.setValor_liquidado(rs.getDouble(21));
               lp.setValor_compra(rs.getDouble(22));
               lp.setFomento(rs.getDouble(23));
               lp.setBolsa(rs.getDouble(24));
               lp.setRetefuente(rs.getDouble(25));
               lp.setAjuste(rs.getDouble(26));
               lp.setTotal(rs.getDouble(27));
               lp.setPolipropileno(rs.getBoolean(28));
               lp.setIdcta_t_usuario(rs.getObject(29));
               lp.setMasablanca(rs.getDouble(30));
               lp.setActivo(rs.getBoolean(31));
               lp.setNumeracion(rs.getInt(32));
               Usuario u=new Usuario();
               u.setNombre(rs.getString(33));
               u.setNoDocumento(rs.getLong(34));
               lp.setUsuario(u);
               lp.setFechaContable(new java.util.Date(rs.getDate(35).getTime()));               
               lp.setAux_proveedor(rs.getString(36));
               lp.setValor_descargue(rs.getDouble(37));
               lp.setAux_descargue(rs.getString(38));
               lista.add(lp);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDePaddyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<LiquidacionDePaddy> ObtenerLiquidacionesDePaddy(Object idUsuario, Date fechainicial, Date fechafinal,String Aux_Proveedor) {
        String sql  =" select ";               
                sql+=" iddocumento,";         
                sql+=" bultos,";
                sql+=" tiquete,";
                sql+=" humedad_pact,";
                sql+=" impureza_pact,";
                sql+=" rojo_pact,";
                sql+=" partido_pact,";
                sql+=" yeso_pact,";
                sql+=" harina_pact,";
                sql+=" humedad_lab,";
                sql+=" impureza_lab,";
                sql+=" rojo_lab,";
                sql+=" partido_lab,";
                sql+=" yeso_lab,";
                sql+=" harina_lab,";
                sql+=" pesobruto,";
                sql+=" pesodestarado,";
                sql+=" peso_liquidado,";
                sql+=" peso14_1,";
                sql+=" valor_kg,";
                sql+=" valor_liquidado,";
                sql+=" valor_compra,";
                sql+=" fomento,";
                sql+=" bolsa,";
                sql+=" retefuente,";
                sql+=" ajuste,";
                sql+=" total,";
                sql+=" polipropileno,";
                sql+=" idcta_t_usuario,";
                sql+=" masablanca,";
                sql+=" activo,";
                sql+=" numeracion,";
                sql+=" proveedor,";
                sql+=" nodocumento, ";
                sql+=" fechacontable, ";
                sql+=" aux_proveedor,valor_descargue,aux_descargue ";
                sql+=" from ";
                sql+=" liquidaciondepaddy";
                sql+=" where(";
                sql+="     activo=true and";
                sql+="     idcta_t_usuario="+idUsuario+" and ";
                sql+="     aux_proveedor="+Aux_Proveedor+" and";
                sql+="     fechacontable>=? and ";
                sql+="     fechacontable<=? ";
                sql+=" )";
                Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fechainicial.getTime()));
            ps.setDate(2,new java.sql.Date(fechafinal.getTime()));
            ResultSet rs=ps.executeQuery();            
            Vector<LiquidacionDePaddy> lista=new Vector<LiquidacionDePaddy>();
            while(rs.next()){                
               LiquidacionDePaddy lp=new LiquidacionDePaddy();
               lp.setId(rs.getObject(1));
               lp.setBultos(rs.getDouble(2));
               lp.setTiquete(rs.getInt(3));
               lp.setHumedad_pact(rs.getDouble(4));
               lp.setImpureza_pact(rs.getDouble(5));
               lp.setRojo_pact(rs.getDouble(6));
               lp.setPartido_pact(rs.getDouble(7));
               lp.setYeso_pact(rs.getDouble(8));
               lp.setHarina_pact(rs.getDouble(9));
               lp.setHumedad_lab(rs.getDouble(10));
               lp.setImpureza_lab(rs.getDouble(11));
               lp.setRojo_lab(rs.getDouble(12));
               lp.setPartido_lab(rs.getDouble(13));
               lp.setYeso_lab(rs.getDouble(14));
               lp.setHarina_lab(rs.getDouble(15));
               lp.setPesobruto(rs.getDouble(16));
               lp.setPesodestarado(rs.getDouble(17));
               lp.setPeso_liquidado(rs.getDouble(18));
               lp.setPeso14_1(rs.getDouble(19));
               lp.setValor_kg(rs.getDouble(20));
               lp.setValor_liquidado(rs.getDouble(21));
               lp.setValor_compra(rs.getDouble(22));
               lp.setFomento(rs.getDouble(23));
               lp.setBolsa(rs.getDouble(24));
               lp.setRetefuente(rs.getDouble(25));
               lp.setAjuste(rs.getDouble(26));
               lp.setTotal(rs.getDouble(27));
               lp.setPolipropileno(rs.getBoolean(28));
               lp.setIdcta_t_usuario(rs.getObject(29));
               lp.setMasablanca(rs.getDouble(30));
               lp.setActivo(rs.getBoolean(31));
               lp.setNumeracion(rs.getInt(32));
               Usuario u=new Usuario();
               u.setNombre(rs.getString(33));
               u.setNoDocumento(rs.getLong(34));
               lp.setUsuario(u);
               lp.setFechaContable(new java.util.Date(rs.getDate(35).getTime()));               
               lp.setAux_proveedor(rs.getString(36));
               lp.setValor_descargue(rs.getDouble(37));
               lp.setAux_descargue(rs.getString(38));
               lista.add(lp);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDePaddyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public boolean AgregarLiquidacionEnMaquila(Object idDocumentoLP, Object idMaquila) {
        String sql =" update liquidaciondepaddy set idmaquila="+idMaquila+" ";               
               sql+=" where(iddocumento="+idDocumentoLP+") ";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                                    
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDePaddyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Pool.LiberarConexion(con);
        }       
    }
    @Override
    public boolean EliminarLiquidacionEnMaquila(Object idDocumentoLP, Object idMaquila) {
        String sql =" update liquidaciondepaddy set idmaquila=null ";               
               sql+=" where(iddocumento="+idDocumentoLP+") ";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                                    
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDePaddyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Pool.LiberarConexion(con);
        }       
    }
    @Override
    public LiquidacionDePaddy ObtenerLiquidacion(Object idNumeracionLiquidacion) {
        String sql =" select ";               
                sql+=" iddocumento,";         
                sql+=" bultos,";
                sql+=" tiquete,";
                sql+=" humedad_pact,";
                sql+=" impureza_pact,";
                sql+=" rojo_pact,";
                sql+=" partido_pact,";
                sql+=" yeso_pact,";
                sql+=" harina_pact,";
                sql+=" humedad_lab,";
                sql+=" impureza_lab,";
                sql+=" rojo_lab,";
                sql+=" partido_lab,";
                sql+=" yeso_lab,";
                sql+=" harina_lab,";
                sql+=" pesobruto,";
                sql+=" pesodestarado,";
                sql+=" peso_liquidado,";
                sql+=" peso14_1,";
                sql+=" valor_kg,";
                sql+=" valor_liquidado,";
                sql+=" valor_compra,";
                sql+=" fomento,";
                sql+=" bolsa,";
                sql+=" retefuente,";
                sql+=" ajuste,";
                sql+=" total,";
                sql+=" polipropileno,";
                sql+=" idcta_t_usuario,";
                sql+=" masablanca,";
                sql+=" activo,";
                sql+=" numeracion,";
                sql+=" proveedor,";
                sql+=" nodocumento, ";
                sql+=" fechacontable, ";
                sql+=" aux_proveedor,valor_descargue,aux_descargue ";
                sql+=" from ";
                sql+=" liquidaciondepaddy";
                sql+=" where(";                
                sql+="     numeracion=? ";
                sql+=" )";
                
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setObject(1,idNumeracionLiquidacion);
            ResultSet rs=ps.executeQuery();            
            if(rs.next()){                
               LiquidacionDePaddy lp=new LiquidacionDePaddy();
               lp.setId(rs.getObject(1));
               lp.setBultos(rs.getDouble(2));
               lp.setTiquete(rs.getInt(3));
               lp.setHumedad_pact(rs.getDouble(4));
               lp.setImpureza_pact(rs.getDouble(5));
               lp.setRojo_pact(rs.getDouble(6));
               lp.setPartido_pact(rs.getDouble(7));
               lp.setYeso_pact(rs.getDouble(8));
               lp.setHarina_pact(rs.getDouble(9));
               lp.setHumedad_lab(rs.getDouble(10));
               lp.setImpureza_lab(rs.getDouble(11));
               lp.setRojo_lab(rs.getDouble(12));
               lp.setPartido_lab(rs.getDouble(13));
               lp.setYeso_lab(rs.getDouble(14));
               lp.setHarina_lab(rs.getDouble(15));
               lp.setPesobruto(rs.getDouble(16));
               lp.setPesodestarado(rs.getDouble(17));
               lp.setPeso_liquidado(rs.getDouble(18));
               lp.setPeso14_1(rs.getDouble(19));
               lp.setValor_kg(rs.getDouble(20));
               lp.setValor_liquidado(rs.getDouble(21));
               lp.setValor_compra(rs.getDouble(22));
               lp.setFomento(rs.getDouble(23));
               lp.setBolsa(rs.getDouble(24));
               lp.setRetefuente(rs.getDouble(25));
               lp.setAjuste(rs.getDouble(26));
               lp.setTotal(rs.getDouble(27));
               lp.setPolipropileno(rs.getBoolean(28));
               lp.setIdcta_t_usuario(rs.getObject(29));
               lp.setMasablanca(rs.getDouble(30));
               lp.setActivo(rs.getBoolean(31));
               lp.setNumeracion(rs.getInt(32));               
               UsuarioService us=new UsuarioService();               
               Usuario u=us.getDao().ObtenerUsuario(lp.getIdcta_t_usuario());               
               lp.setUsuario(u);
               lp.setFechaContable(new java.util.Date(rs.getDate(35).getTime()));
               lp.setAux_proveedor(rs.getString(36));
               lp.setValor_descargue(rs.getDouble(37));
               lp.setAux_descargue(rs.getString(38));
               return lp;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDePaddyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public LiquidacionDePaddy ObtenerUltimaLiquidacion() {
         String sql =" select ";               
                sql+=" iddocumento,";         
                sql+=" bultos,";
                sql+=" tiquete,";
                sql+=" humedad_pact,";
                sql+=" impureza_pact,";
                sql+=" rojo_pact,";
                sql+=" partido_pact,";
                sql+=" yeso_pact,";
                sql+=" harina_pact,";
                sql+=" humedad_lab,";
                sql+=" impureza_lab,";
                sql+=" rojo_lab,";
                sql+=" partido_lab,";
                sql+=" yeso_lab,";
                sql+=" harina_lab,";
                sql+=" pesobruto,";
                sql+=" pesodestarado,";
                sql+=" peso_liquidado,";
                sql+=" peso14_1,";
                sql+=" valor_kg,";
                sql+=" valor_liquidado,";
                sql+=" valor_compra,";
                sql+=" fomento,";
                sql+=" bolsa,";
                sql+=" retefuente,";
                sql+=" ajuste,";
                sql+=" total,";
                sql+=" polipropileno,";
                sql+=" idcta_t_usuario,";
                sql+=" masablanca,";
                sql+=" activo,";
                sql+=" numeracion,";
                sql+=" proveedor,";
                sql+=" nodocumento, ";
                sql+=" fechacontable, ";
                sql+=" aux_proveedor,valor_descargue,aux_descargue ";
                sql+=" from ";
                sql+=" liquidaciondepaddy";
                sql+=" ";                
                sql+=" order by numeracion desc limit 1 ";
                sql+="  ";
                
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);            
            ResultSet rs=ps.executeQuery();            
            if(rs.next()){                
               LiquidacionDePaddy lp=new LiquidacionDePaddy();
               lp.setId(rs.getObject(1));
               lp.setBultos(rs.getDouble(2));
               lp.setTiquete(rs.getInt(3));
               lp.setHumedad_pact(rs.getDouble(4));
               lp.setImpureza_pact(rs.getDouble(5));
               lp.setRojo_pact(rs.getDouble(6));
               lp.setPartido_pact(rs.getDouble(7));
               lp.setYeso_pact(rs.getDouble(8));
               lp.setHarina_pact(rs.getDouble(9));
               lp.setHumedad_lab(rs.getDouble(10));
               lp.setImpureza_lab(rs.getDouble(11));
               lp.setRojo_lab(rs.getDouble(12));
               lp.setPartido_lab(rs.getDouble(13));
               lp.setYeso_lab(rs.getDouble(14));
               lp.setHarina_lab(rs.getDouble(15));
               lp.setPesobruto(rs.getDouble(16));
               lp.setPesodestarado(rs.getDouble(17));
               lp.setPeso_liquidado(rs.getDouble(18));
               lp.setPeso14_1(rs.getDouble(19));
               lp.setValor_kg(rs.getDouble(20));
               lp.setValor_liquidado(rs.getDouble(21));
               lp.setValor_compra(rs.getDouble(22));
               lp.setFomento(rs.getDouble(23));
               lp.setBolsa(rs.getDouble(24));
               lp.setRetefuente(rs.getDouble(25));
               lp.setAjuste(rs.getDouble(26));
               lp.setTotal(rs.getDouble(27));
               lp.setPolipropileno(rs.getBoolean(28));
               lp.setIdcta_t_usuario(rs.getObject(29));
               lp.setMasablanca(rs.getDouble(30));
               lp.setActivo(rs.getBoolean(31));
               lp.setNumeracion(rs.getInt(32));
               Usuario u=new Usuario();
               u.setNombre(rs.getString(33));
               u.setNoDocumento(rs.getLong(34));               
               lp.setUsuario(u);
               lp.setFechaContable(new java.util.Date(rs.getDate(35).getTime()));
               lp.setAux_proveedor(rs.getString(36));
               lp.setValor_descargue(rs.getDouble(37));
               lp.setAux_descargue(rs.getString(38));
               return lp;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDePaddyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public LiquidacionDePaddy ModificarLiquidacion(LiquidacionDePaddy lp) {
 String sql ="update liquidaciondepaddy ";
        sql+="set iddocumento="+lp.getId()+",bultos=?,tiquete=?,humedad_pact=?,impureza_pact=?,rojo_pact=?,partido_pact=?,";//7
        sql+=" yeso_pact=?,harina_pact=?,humedad_lab=?,impureza_lab=?,rojo_lab=?,partido_lab=?,yeso_lab=?,";//7
        sql+=" harina_lab=?,pesobruto=?,pesodestarado=?,peso_liquidado=?,peso14_1=?,valor_kg=?,";//6
        sql+=" valor_liquidado=?,valor_compra=?,fomento=?,bolsa=?,retefuente=?,ajuste=?,total=?,polipropileno=?,idcta_t_usuario=?,masablanca=?,activo="+lp.isActivo()+",numeracion=?,proveedor=?,nodocumento=?,fechacontable=?,aux_proveedor="+lp.getAux_proveedor()+",valor_descargue=?,aux_descargue=? ";//8
        sql+=" where(iddocumento="+lp.getId()+") ";

        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                        
            ps.setDouble(1,lp.getBultos());
            ps.setInt(2,lp.getTiquete());
            ps.setDouble(3,lp.getHumedad_pact());
            ps.setDouble(4,lp.getImpureza_pact());
            ps.setDouble(5,lp.getRojo_pact());
            ps.setDouble(6,lp.getPartido_pact());
            ps.setDouble(7,lp.getYeso_pact());
            ps.setDouble(8,lp.getHarina_pact());
            ps.setDouble(9,lp.getHumedad_lab());
            ps.setDouble(10,lp.getImpureza_lab());
            ps.setDouble(11,lp.getRojo_lab());
            ps.setDouble(12,lp.getPartido_lab());
            ps.setDouble(13,lp.getYeso_lab());
            ps.setDouble(14,lp.getHarina_lab());
            ps.setDouble(15,lp.getPesobruto());
            ps.setDouble(16,lp.getPesodestarado());
            ps.setDouble(17,lp.getPeso_liquidado());
            ps.setDouble(18,lp.getPeso14_1());
            ps.setDouble(19,lp.getValor_kg());
            ps.setDouble(20,lp.getValor_liquidado());
            ps.setDouble(21,lp.getValor_compra());
            ps.setDouble(22,lp.getFomento());
            ps.setDouble(23,lp.getBolsa());
            ps.setDouble(24,lp.getRetefuente());
            ps.setDouble(25,lp.getAjuste());
            ps.setDouble(26,lp.getTotal());
            ps.setBoolean(27,lp.isPolipropileno());            
            ps.setObject(28,lp.getIdcta_t_usuario());
            ps.setDouble(29,lp.getMasablanca());
            ps.setDouble(30,lp.getNumeracion());
            ps.setString(31,lp.getUsuario().NombreCompleto());
            ps.setLong(32,lp.getUsuario().getNoDocumento());
            ps.setDate(33,new java.sql.Date(lp.getFechaContable().getTime()));
            ps.setDouble(34,lp.getValor_descargue());
            ps.setString(35,lp.getAux_descargue());
            ps.executeUpdate();
            return lp;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDePaddyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }    
    }
    @Override
    public double ObtenerTotalMasaBlanca(Date fecha_inicial,Date fecha_final) {
         String sql =" select ";               
                sql+=" SUM(masablanca)";                
                sql+=" from ";
                sql+=" liquidaciondepaddy ";
                sql+=" where(";
                sql+="     activo=true and idmaquila!=1 and ";               
                sql+=" liquidaciondepaddy.fechacontable>=? and ";
                sql+=" liquidaciondepaddy.fechacontable<=?  ";
                sql+=" ) ";
                
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);                        
            ps.setDate(1,new java.sql.Date(fecha_inicial.getTime()));
            ps.setDate(2,new java.sql.Date(fecha_final.getTime()));
            ResultSet rs=ps.executeQuery();                        
            double val=0;
            if(rs.next()){          
                val=rs.getDouble(1);
            }
            return val;
        } catch (SQLException ex) {
            Logger.getLogger(LiquidacionDePaddyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
}
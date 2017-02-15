/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Infraestructura.JDBC.Impl;

import corgranos.Dominio.Core.Maquila.Maquila;
import corgranos.Infraestructura.JDBC.Pool;
import corgranos.InterfacesDAO.IMaquilaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FANNY BURGOS
 */
public class MaquilaDAO implements IMaquilaDAO{

    @Override
    public Maquila PersistirMaquila(Maquila m) {        
        String sql =" insert into maquila (idmaquila,inicialpeso14_1,";
               sql+=" inicialpesodestarado,inicialcosto,inicialmasablanca,";
               sql+=" totalpeso14_1,totalpesodestarado,totalcosto,totalmasablanca,cerrada,";
               sql+=" activa,valortrilla,iddocumentodecierre,totalpesobruto,totalpesoliquidado,inicialpesobruto,inicialpesoliquidado) values ("+m.getId()+",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";               
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);            
            ps.setDouble(1,m.getInicialpeso14_1());
            ps.setDouble(2,m.getInicialpesodestarado());
            ps.setDouble(3,m.getInicialcosto());
            ps.setDouble(4,m.getInicialmasablanca());
            ps.setDouble(5,m.getTotalpeso14_1());
            ps.setDouble(6,m.getTotalpesodestarado());
            ps.setDouble(7,m.getTotalcosto());
            ps.setDouble(8,m.getTotalmasablanca());
            ps.setBoolean(9,m.isCerrada());
            ps.setBoolean(10,m.isActiva());
            ps.setDouble(11,m.getValortrilla());
            ps.setString(12,m.getIdDocumentoDeCierre());
            ps.setDouble(13,m.getTotalpesobruto());
            ps.setDouble(14,m.getTotalpesoliquidado());
            ps.setDouble(15,m.getInicialpesobruto());
            ps.setDouble(16,m.getInicialpesoliquidado());
            ps.executeUpdate();
            return m;
        } catch (SQLException ex) {
            Logger.getLogger(MaquilaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Maquila ObtenerMaquila(Object id) {
        Connection con=null;
        String sql ="select \n ";    
               sql+="idmaquila, \n";
               sql+="inicialpeso14_1, \n";
               sql+="inicialpesodestarado, \n";
               sql+="inicialcosto, \n";
               sql+="inicialmasablanca, \n";
               sql+="totalpeso14_1, \n";
               sql+="totalpesodestarado,\n";
               sql+="totalcosto,\n";
               sql+="totalmasablanca,\n";
               sql+="cerrada,\n";
               sql+="activa, \n";
               sql+="valortrilla, \n";
               sql+="iddocumentodecierre, \n";
               sql+="totalpesobruto, ";
               sql+="totalpesoliquidado, ";
               sql+="inicialpesobruto, ";
               sql+="inicialpesoliquidado ";
               sql+="from   maquila \n";
               sql+="where( \n";
               sql+="      idmaquila="+id+" \n";
               sql+=" ) \n";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Maquila m=new Maquila();
                m.setId(rs.getObject(1));
                m.setInicialpeso14_1(rs.getDouble(2));
                m.setInicialpesodestarado(rs.getDouble(3));
                m.setInicialcosto(rs.getDouble(4));
                m.setInicialmasablanca(rs.getDouble(5));
                m.setTotalpeso14_1(rs.getDouble(6));
                m.setTotalpesodestarado(rs.getDouble(7));
                m.setTotalcosto(rs.getDouble(8));
                m.setTotalmasablanca(rs.getDouble(9));
                m.setCerrada(rs.getBoolean(10));
                m.setActiva(rs.getBoolean(11));
                m.setValortrilla(rs.getDouble(12));
                m.setIdDocumentoDeCierre(rs.getString(13));
                
                m.setTotalpesobruto(rs.getDouble(14));
                m.setTotalpesoliquidado(rs.getDouble(15));
                m.setInicialpesobruto(rs.getDouble(16));
                m.setInicialpesoliquidado(rs.getDouble(17));
                return m;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(MaquilaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Maquila ObtenerUltimaMaquila() {
        Connection con=null;
        String sql ="select  \n";    
               sql+="idmaquila, \n";
               sql+="inicialpeso14_1, \n";
               sql+="inicialpesodestarado,\n";
               sql+="inicialcosto, \n";
               sql+="inicialmasablanca,\n";
               sql+="totalpeso14_1,\n";
               sql+="totalpesodestarado,\n";
               sql+="totalcosto,\n";
               sql+="totalmasablanca,\n";
               sql+="cerrada,\n";
               sql+="activa, \n";
               sql+="valortrilla, \n";
               sql+="iddocumentodecierre, \n";
               sql+="totalpesobruto, ";
               sql+="totalpesoliquidado, ";
               sql+="inicialpesobruto, ";
               sql+="inicialpesoliquidado ";
               sql+="from   maquila\n ";
               sql+="order by idmaquila desc limit 1 \n";
               sql+="  \n";
               sql+="  \n";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Maquila m=new Maquila();
                m.setId(rs.getObject(1));
                m.setInicialpeso14_1(rs.getDouble(2));
                m.setInicialpesodestarado(rs.getDouble(3));
                m.setInicialcosto(rs.getDouble(4));
                m.setInicialmasablanca(rs.getDouble(5));
                m.setTotalpeso14_1(rs.getDouble(6));
                m.setTotalpesodestarado(rs.getDouble(7));
                m.setTotalcosto(rs.getDouble(8));
                m.setTotalmasablanca(rs.getDouble(9));
                m.setCerrada(rs.getBoolean(10));
                m.setActiva(rs.getBoolean(11));
                m.setValortrilla(rs.getDouble(12));
                m.setIdDocumentoDeCierre(rs.getString(13));
                m.setTotalpesobruto(rs.getDouble(14));
                m.setTotalpesoliquidado(rs.getDouble(15));
                m.setInicialpesobruto(rs.getDouble(16));
                m.setInicialpesoliquidado(rs.getDouble(17));
                return m;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(MaquilaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<Maquila> ObtenerMaquilasAbiertas(String busqueda) {
        Connection con=null;
        String sql ="select \n ";    
               sql+="idmaquila, \n";
               sql+="inicialpeso14_1, \n";
               sql+="inicialpesodestarado, \n";
               sql+="inicialcosto, \n";
               sql+="inicialmasablanca, \n";
               sql+="totalpeso14_1, \n";
               sql+="totalpesodestarado,\n";
               sql+="totalcosto,\n";
               sql+="totalmasablanca,\n";
               sql+="cerrada,\n";
               sql+="activa, \n";
               sql+="valortrilla, \n";
               sql+="iddocumentodecierre, \n";
               sql+="totalpesobruto, ";
               sql+="totalpesoliquidado, ";
               sql+="inicialpesobruto, ";
               sql+="inicialpesoliquidado ";
               sql+="from   maquila \n";
               sql+="where( \n";
               sql+="    maquila.idmaquila like ?  and     \n";
               sql+="    maquila.cerrada=false   \n";               
               sql+=" ) \n";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,busqueda+"%");
            ResultSet rs=ps.executeQuery();
            Vector <Maquila> lista=new Vector<Maquila>();
            while(rs.next()){
                Maquila m=new Maquila();
                m.setId(rs.getObject(1));
                m.setInicialpeso14_1(rs.getDouble(2));
                m.setInicialpesodestarado(rs.getDouble(3));
                m.setInicialcosto(rs.getDouble(4));
                m.setInicialmasablanca(rs.getDouble(5));
                m.setTotalpeso14_1(rs.getDouble(6));
                m.setTotalpesodestarado(rs.getDouble(7));
                m.setTotalcosto(rs.getDouble(8));
                m.setTotalmasablanca(rs.getDouble(9));
                m.setCerrada(rs.getBoolean(10));
                m.setActiva(rs.getBoolean(11));          
                m.setValortrilla(rs.getDouble(12));
                m.setIdDocumentoDeCierre(rs.getString(13));
                m.setTotalpesobruto(rs.getDouble(14));
                m.setTotalpesoliquidado(rs.getDouble(15));
                m.setInicialpesobruto(rs.getDouble(16));
                m.setInicialpesoliquidado(rs.getDouble(17));
                lista.add(m);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(MaquilaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }    
    @Override
    public Vector<Maquila> ObtenerMaquilasCerradas(String busqueda) {
        Connection con=null;
        String sql ="select \n ";    
               sql+="idmaquila, \n";
               sql+="inicialpeso14_1, \n";
               sql+="inicialpesodestarado, \n";
               sql+="inicialcosto, \n";
               sql+="inicialmasablanca, \n";
               sql+="totalpeso14_1, \n";
               sql+="totalpesodestarado,\n";
               sql+="totalcosto,\n";
               sql+="totalmasablanca,\n";
               sql+="cerrada,\n";
               sql+="activa, \n";
               sql+="valortrilla, \n";
               sql+="iddocumentodecierre, \n";
               sql+="totalpesobruto, ";
               sql+="totalpesoliquidado, ";
               sql+="inicialpesobruto, ";
               sql+="inicialpesoliquidado ";
               sql+="from   maquila \n";
               sql+="where( \n";
               sql+="    maquila.idmaquila like ?  and     \n";
               sql+="    maquila.cerrada=true   \n";               
               sql+=" ) \n";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,busqueda+"%");
            ResultSet rs=ps.executeQuery();
            Vector <Maquila> lista=new Vector<Maquila>();
            while(rs.next()){
                Maquila m=new Maquila();
                m.setId(rs.getObject(1));
                m.setInicialpeso14_1(rs.getDouble(2));
                m.setInicialpesodestarado(rs.getDouble(3));
                m.setInicialcosto(rs.getDouble(4));
                m.setInicialmasablanca(rs.getDouble(5));
                m.setTotalpeso14_1(rs.getDouble(6));
                m.setTotalpesodestarado(rs.getDouble(7));
                m.setTotalcosto(rs.getDouble(8));
                m.setTotalmasablanca(rs.getDouble(9));
                m.setCerrada(rs.getBoolean(10));
                m.setActiva(rs.getBoolean(11));          
                m.setValortrilla(rs.getDouble(12));
                m.setIdDocumentoDeCierre(rs.getString(13));
                m.setTotalpesobruto(rs.getDouble(14));
                m.setTotalpesoliquidado(rs.getDouble(15));
                m.setInicialpesobruto(rs.getDouble(16));
                m.setInicialpesoliquidado(rs.getDouble(17));
                lista.add(m);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(MaquilaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Maquila ModificarMaquila(Maquila m) {
        String sql =" update maquila ";
               sql+="set idmaquila="+m.getId()+",";
               sql+=" inicialpeso14_1="+m.getInicialpeso14_1()+", ";
               sql+=" inicialpesodestarado="+m.getInicialpesodestarado()+", ";
               sql+=" inicialmasablanca="+m.getInicialmasablanca()+", ";
               sql+=" inicialcosto="+m.getInicialcosto()+", ";
               sql+=" totalpeso14_1="+m.getTotalpeso14_1()+", ";
               sql+=" totalpesodestarado="+m.getTotalpesodestarado()+", ";
               sql+=" totalmasablanca="+m.getTotalmasablanca()+", ";
               sql+=" totalcosto="+m.getTotalcosto()+", ";
               sql+=" valortrilla="+m.getValortrilla()+", ";               
               sql+=" iddocumentodecierre='"+m.getIdDocumentoDeCierre()+"',  ";            
               sql+=" cerrada="+m.isCerrada()+" , ";            
               sql+=" totalpesobruto="+m.getTotalpesobruto()+", ";
               sql+=" totalpesoliquidado="+m.getTotalpesoliquidado()+", ";
               sql+=" inicialpesobruto="+m.getInicialpesobruto()+", ";
               sql+=" inicialpesoliquidado="+m.getInicialpesoliquidado()+" ";
               sql+="where(idmaquila="+m.getId()+") ";            
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                                    
            ps.executeUpdate();
            return m;
        } catch (SQLException ex) {
            Logger.getLogger(MaquilaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }       
    }
    @Override
    public Vector<Maquila> ObtenerMaquilas(String busqueda) {
        Connection con=null;
        String sql ="select \n ";    
               sql+="idmaquila, \n";
               sql+="inicialpeso14_1, \n";
               sql+="inicialpesodestarado, \n";
               sql+="inicialcosto, \n";
               sql+="inicialmasablanca, \n";
               sql+="totalpeso14_1, \n";
               sql+="totalpesodestarado,\n";
               sql+="totalcosto,\n";
               sql+="totalmasablanca,\n";
               sql+="cerrada,\n";
               sql+="activa, \n";
               sql+="valortrilla , \n";
               sql+="iddocumentodecierre, \n";
               sql+="totalpesobruto, ";
               sql+="totalpesoliquidado, ";
               sql+="inicialpesobruto, ";
               sql+="inicialpesoliquidado ";
               sql+="from   maquila \n";
               sql+="where( \n";
               sql+="    maquila.idmaquila like ?       \n";         
               sql+=" ) \n";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,busqueda+"%");
            ResultSet rs=ps.executeQuery();
            Vector <Maquila> lista=new Vector<Maquila>();
            while(rs.next()){
                Maquila m=new Maquila();
                m.setId(rs.getObject(1));
                m.setInicialpeso14_1(rs.getDouble(2));
                m.setInicialpesodestarado(rs.getDouble(3));
                m.setInicialcosto(rs.getDouble(4));
                m.setInicialmasablanca(rs.getDouble(5));
                m.setTotalpeso14_1(rs.getDouble(6));
                m.setTotalpesodestarado(rs.getDouble(7));
                m.setTotalcosto(rs.getDouble(8));
                m.setTotalmasablanca(rs.getDouble(9));
                m.setCerrada(rs.getBoolean(10));
                m.setActiva(rs.getBoolean(11));          
                m.setValortrilla(rs.getDouble(12));
                m.setIdDocumentoDeCierre(rs.getString(13));
                m.setTotalpesobruto(rs.getDouble(14));
                m.setTotalpesoliquidado(rs.getDouble(15));
                m.setInicialpesobruto(rs.getDouble(16));
                m.setInicialpesoliquidado(rs.getDouble(17));
                lista.add(m);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(MaquilaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    // METODO INSEGURO
    // Este Metodo se Conecta a la Plataforma Contable
    // Para Modificar los asientos de la MaqXXX_
    @Override
    public boolean ModificarAsientosDeFacturas(Object idMaquila) {
        String sql =" update asiento set detalle='SE REABRIO Maq "+idMaquila+"',debito=0,credito=0,entradas=0,salidas=0,nofactura=0 where(detalle='Maq"+idMaquila+"_')";
               
        Connection con=null;        
        try {                        
            con=sic.Infraestructura.JDBC.Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                                    
            ps.executeUpdate();            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MaquilaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            sic.Infraestructura.JDBC.Pool.LiberarConexion(con);
        }       
    }

    @Override
    public Maquila ObtenerMaquilaDeLiquidacion(Object idDocumentoLiquidacion) {
        Connection con=null;
        String sql ="select \n ";    
               sql+="maquila.idmaquila, \n";
               sql+="maquila.inicialpeso14_1, \n";
               sql+="maquila.inicialpesodestarado, \n";
               sql+="maquila.inicialcosto, \n";
               sql+="maquila.inicialmasablanca, \n";
               sql+="maquila.totalpeso14_1, \n";
               sql+="maquila.totalpesodestarado,\n";
               sql+="maquila.totalcosto,\n";
               sql+="maquila.totalmasablanca,\n";
               sql+="maquila.cerrada,\n";
               sql+="maquila.activa, \n";
               sql+="maquila.valortrilla, \n";
               sql+="maquila.iddocumentodecierre, \n";
               sql+="maquila.totalpesobruto, ";
               sql+="maquila.totalpesoliquidado, ";
               sql+="maquila.inicialpesobruto, ";
               sql+="maquila.inicialpesoliquidado ";
               sql+="from   maquila,liquidaciondepaddy \n";
               sql+="where( \n";
               sql+="      maquila.idmaquila=liquidaciondepaddy.idmaquila and \n";
               sql+="      liquidaciondepaddy.iddocumento=? \n";
               sql+=" )limit 1 \n";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setObject(1,idDocumentoLiquidacion);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Maquila m=new Maquila();
                m.setId(rs.getObject(1));
                m.setInicialpeso14_1(rs.getDouble(2));
                m.setInicialpesodestarado(rs.getDouble(3));
                m.setInicialcosto(rs.getDouble(4));
                m.setInicialmasablanca(rs.getDouble(5));
                m.setTotalpeso14_1(rs.getDouble(6));
                m.setTotalpesodestarado(rs.getDouble(7));
                m.setTotalcosto(rs.getDouble(8));
                m.setTotalmasablanca(rs.getDouble(9));
                m.setCerrada(rs.getBoolean(10));
                m.setActiva(rs.getBoolean(11));
                m.setValortrilla(rs.getDouble(12));
                m.setIdDocumentoDeCierre(rs.getString(13));
                m.setTotalpesobruto(rs.getDouble(14));
                m.setTotalpesoliquidado(rs.getDouble(15));
                m.setInicialpesobruto(rs.getDouble(16));
                m.setInicialpesoliquidado(rs.getDouble(17));
                return m;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(MaquilaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public double ObtenerIngresosPorVenta(Object idMaquila,Object idArticulo) {
        Connection con=null;
        String sql =" select distinct  \n";                                  
               sql+=" sum(facturaciondearticulo.PrecioUnitario*cantidad) \n";
               sql+=" from  \n";
               sql+=" factura , facturaciondearticulo ,maquila \n";
               sql+=" where( \n";
               sql+="  factura.idDocumento=facturaciondearticulo.idDocumento and \n";
               sql+="  maquila.idMaquila=facturaciondearticulo.idMaquila and \n";
               sql+="  factura.activa=true   and \n";
               sql+="  maquila.idMaquila="+idMaquila+"  and  ";
               sql+="  facturaciondearticulo.Articulo_idCta_T="+idArticulo+"  \n";               
               sql+="  )\n";               
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getDouble(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(MaquilaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    
}

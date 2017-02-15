/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Infraestructura.JDBC.Impl.Mysql;

import sic.Dominio.Core.Cta_T.Cta_T;
import sic.Dominio.Core.PUC.Cta_PUC;
import sic.InterfacesDAO.IPucDAO;
import sic.Infraestructura.JDBC.Pool;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;


/**
 *
 * @author FANNY BURGOS
 */
public class PucDAO implements IPucDAO{
    @Override
    public Cta_PUC PersistirCtaPuc(Cta_PUC cta) {
        Cta_T ctat=cta.getCtat();
        String sql =" insert into puc ";
        if(ctat!=null){        
               sql+=" (idCtaPuc,idCta_T,denominacion,tipocta,requierenit,publico,categoria)";
               sql+="values ("+cta.getId()+","+ctat.getId()+",?,?,?,?,?)";
        }else{
               sql+=" (idCtaPuc,idCta_T,denominacion,tipocta,requierenit,publico,categoria)";
               sql+="values ("+cta.getId()+",null,?,?,?,?,?)";
        }         
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);            
            ps.setString(1,cta.getDenominacion());
            ps.setString(2,cta.getTipoCta());
            ps.setBoolean(3,cta.isRequiereNit());
            ps.setBoolean(4,cta.isPublico());
            ps.setString(5,cta.getCategoria());
            ps.executeUpdate();
            return cta;
        } catch (SQLException ex) {
            Logger.getLogger(PucDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Cta_PUC ObtenerCtaPuc(Object id) {
        Connection con=null;
        String sql ="select puc.idctapuc,puc.denominacion,puc.tipocta,puc.requierenit,puc.idcta_t,puc.publico,puc.categoria ";               
               sql+="from   puc ";
               sql+="where( ";
               sql+="       puc.idctapuc="+id+" ";
               sql+=" ) ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Object id_=rs.getObject(1);
                String denominacion=rs.getString(2);
                String tipo=rs.getString(3);
                boolean requierenit=rs.getBoolean(4);                                
                Object idt=rs.getObject(5);
                boolean publico=rs.getBoolean(6);
                String categoria=rs.getString(7);
                Cta_PUC cta=new Cta_PUC();
                cta.setId(id_);
                cta.setDenominacion(denominacion);
                cta.setTipoCta(tipo);
                cta.setRequiereNit(requierenit);
                cta.setPublico(publico);
                cta.setCategoria(categoria);
                if(idt!=null){
                   Cta_T t=new Cta_T();
                   t.setDescripcion(denominacion);
                   t.setId(idt);
                   cta.setCtat(t);
                }
                return cta;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(PucDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<Cta_PUC> ObtenerPUC(String busqueda) {
        Connection con=null;
        String sql ="select puc.idctapuc,puc.denominacion,puc.tipocta,puc.requierenit,puc.idcta_t,puc.publico,puc.categoria ";               
               sql+="from   puc ";
               sql+="where( ";
               sql+="       puc.idctapuc<99999999 and ( ";
               sql+="       puc.idctapuc like ? or ";
               sql+="       puc.denominacion like ? ) ";               
               sql+=" ) order by idctapuc ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,busqueda+"%");
            ps.setString(2,"%"+busqueda+"%");
            ResultSet rs=ps.executeQuery();
            Vector <Cta_PUC> lista=new Vector <Cta_PUC>();
            while(rs.next()){
                Object id_=rs.getObject(1);
                String denominacion=rs.getString(2);
                String tipo=rs.getString(3);
                boolean requierenit=rs.getBoolean(4);               
                Object idt=rs.getObject(5);
                boolean publico=rs.getBoolean(6);                
                String categoria=rs.getString(7);
                Cta_PUC cta=new Cta_PUC();
                cta.setId(id_);
                cta.setDenominacion(denominacion);
                cta.setTipoCta(tipo);
                cta.setRequiereNit(requierenit);
                cta.setPublico(publico);
                cta.setCategoria(categoria);
                if(idt!=null){
                   Cta_T t=new Cta_T();
                   t.setDescripcion(denominacion);
                   t.setId(idt);   
                   cta.setCtat(t);
                }
                lista.add(cta);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PucDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<Cta_PUC> InspeccionarAuxiliar(String busqueda, Object idAuxiliar) {
        Connection con=null;
        String sql ="select puc.idctapuc,puc.denominacion,puc.tipocta,puc.requierenit,puc.idcta_t,puc.publico,puc.categoria ";               
               sql+="from   puc ";
               sql+="where( ";
               sql+="       puc.idctapuc like '"+idAuxiliar+"%' and ";
               sql+="       length(puc.idctapuc)>8 and ( ";
               sql+="       puc.idctapuc like ? or ";
               sql+="       puc.denominacion like ? ) ";               
               sql+=" ) order by idctapuc";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,busqueda+"%");
            ps.setString(2,"%"+busqueda+"%");
            ResultSet rs=ps.executeQuery();
            Vector <Cta_PUC> lista=new Vector <Cta_PUC>();
            while(rs.next()){
                Object id_=rs.getObject(1);
                String denominacion=rs.getString(2);
                String tipo=rs.getString(3);
                boolean requierenit=rs.getBoolean(4);                
                Object idt=rs.getObject(5);
                boolean publico=rs.getBoolean(6);                
                String categoria=rs.getString(7);
                Cta_PUC cta=new Cta_PUC();
                cta.setId(id_);
                cta.setDenominacion(denominacion);
                cta.setTipoCta(tipo);
                cta.setRequiereNit(requierenit);
                cta.setPublico(publico);
                cta.setCategoria(categoria);
                if(idt!=null){
                   Cta_T t=new Cta_T();
                   t.setDescripcion(denominacion);
                   t.setId(idt);   
                   cta.setCtat(t);
                }
                lista.add(cta);
            }
            System.out.println(lista.size());
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PucDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public Vector<Cta_PUC> ObtenerPUC(int clase, int digito) {
        Connection con=null;
        String sql ="select puc.idctapuc,puc.denominacion,puc.tipocta,puc.requierenit,puc.idcta_t,puc.publico,puc.categoria ";               
               sql+="from   puc ";
               sql+="where( ";
               sql+="       puc.idctapuc<99999999 and  ";
               sql+="       puc.idctapuc like ?  and";               
               sql+="       length(puc.idctapuc)=?  ";               
               sql+=" ) order by idctapuc ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,clase+"%");
            ps.setInt(2,digito);
            ResultSet rs=ps.executeQuery();
            Vector <Cta_PUC> lista=new Vector <Cta_PUC>();
            while(rs.next()){
                Object id_=rs.getObject(1);
                String denominacion=rs.getString(2);
                String tipo=rs.getString(3);
                boolean requierenit=rs.getBoolean(4);               
                Object idt=rs.getObject(5);
                boolean publico=rs.getBoolean(6);                
                String categoria=rs.getString(7);
                Cta_PUC cta=new Cta_PUC();
                cta.setId(id_);
                cta.setDenominacion(denominacion);
                cta.setTipoCta(tipo);
                cta.setRequiereNit(requierenit);
                cta.setPublico(publico);
                cta.setCategoria(categoria);
                if(idt!=null){
                   Cta_T t=new Cta_T();
                   t.setDescripcion(denominacion);
                   t.setId(idt);   
                   cta.setCtat(t);
                }
                lista.add(cta);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PucDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public Vector<Cta_PUC> ObtenerPUC(Object idCtaT) {
        Connection con=null;
        String sql ="select puc.idctapuc,puc.denominacion,puc.tipocta,puc.requierenit,puc.idcta_t,puc.publico,puc.categoria ";               
               sql+="from   puc ";
               sql+="where( ";             
               sql+="       puc.idcta_t=?  ";               
               sql+=" ) order by idctapuc ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setObject(1,idCtaT);            
            ResultSet rs=ps.executeQuery();
            Vector <Cta_PUC> lista=new Vector <Cta_PUC>();
            while(rs.next()){
                Object id_=rs.getObject(1);
                String denominacion=rs.getString(2);
                String tipo=rs.getString(3);
                boolean requierenit=rs.getBoolean(4);               
                Object idt=rs.getObject(5);
                boolean publico=rs.getBoolean(6);                
                String categoria=rs.getString(7);
                Cta_PUC cta=new Cta_PUC();
                cta.setId(id_);
                cta.setDenominacion(denominacion);
                cta.setTipoCta(tipo);
                cta.setRequiereNit(requierenit);
                cta.setPublico(publico);
                cta.setCategoria(categoria);
                if(idt!=null){
                   Cta_T t=new Cta_T();
                   t.setDescripcion(denominacion);
                   t.setId(idt);   
                   cta.setCtat(t);
                }
                lista.add(cta);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PucDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public boolean ModificarEnPuc(Object idCtaT, Cta_T t) {
        String sql =" update puc ";
               sql+=" set denominacion=? where(idcta_t="+idCtaT+")";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                        
            ps.setString(1,t.getDescripcion());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PucDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public Cta_PUC ModificarCtaPuc(Object oldid, Cta_PUC cta) {
        String sql =" update puc ";
               sql+=" set idctapuc="+cta.getId()+",denominacion=?,";
               if(cta.getCtat()!=null){
                   sql+=" idCta_T="+cta.getCtat().getId()+",";
               }else{
                   sql+=" idCta_T=null,";
               }
               sql+=" tipocta=?,requierenit=?,publico=?,categoria=? where(idctapuc="+oldid+")";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                        
            ps.setString(1,cta.getDenominacion());
            ps.setString(2,cta.getTipoCta());
            ps.setBoolean(3,cta.isRequiereNit());
            ps.setBoolean(4,cta.isPublico());
            ps.setString(5,cta.getCategoria());
            ps.executeUpdate();
            return cta;
        } catch (SQLException ex) {
            Logger.getLogger(PucDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public Vector<Cta_PUC> ObtenerCuentas(Object idctai, Object idctaf) {
        Connection con=null;
        String sql ="select puc.idctapuc,puc.denominacion,puc.tipocta,puc.requierenit,puc.idcta_t,puc.publico,puc.categoria ";               
               sql+="from   puc ";
               sql+="where( ";             
               sql+="  puc.idctapuc>="+idctai+"  and ";               
               sql+="  puc.idctapuc<="+idctaf+"   ";                              
               sql+=" ) order by idctapuc ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);            
            ResultSet rs=ps.executeQuery();
            Vector <Cta_PUC> lista=new Vector <Cta_PUC>();
            while(rs.next()){
                Object id_=rs.getObject(1);
                String denominacion=rs.getString(2);
                String tipo=rs.getString(3);
                boolean requierenit=rs.getBoolean(4);               
                Object idt=rs.getObject(5);
                boolean publico=rs.getBoolean(6);                
                String categoria=rs.getString(7);
                Cta_PUC cta=new Cta_PUC();
                cta.setId(id_);
                cta.setDenominacion(denominacion);
                cta.setTipoCta(tipo);
                cta.setRequiereNit(requierenit);
                cta.setPublico(publico);
                cta.setCategoria(categoria);
                if(idt!=null){
                   Cta_T t=new Cta_T();
                   t.setDescripcion(denominacion);
                   t.setId(idt);   
                   cta.setCtat(t);
                }
                lista.add(cta);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PucDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
}
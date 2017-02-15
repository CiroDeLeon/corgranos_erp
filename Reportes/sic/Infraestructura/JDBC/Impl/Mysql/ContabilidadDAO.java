/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Infraestructura.JDBC.Impl.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import sic.Dominio.Core.PUC.PucService;
import sic.Dominio.Servicios.Contabilidad.AsientoContableDTO;
import sic.Dominio.Servicios.Contabilidad.AsientoPorFechaDTO;
import sic.Dominio.Servicios.Contabilidad.ResumenAuxDTO;
import sic.Servicios.FechaService;
import sic.Infraestructura.JDBC.Pool;
import sic.InterfacesDAO.IContabilidadDAO;

/**
 *
 * @author FANNY BURGOS
 */
public class ContabilidadDAO implements IContabilidadDAO{
    @Override
    public Vector<AsientoContableDTO> ObtenerAsientos(String aux,Date fechai,Date fechaf) {
        Connection con=null;
        String sql ="select \n";
               sql+="documento.idDocumento, \n";
               sql+="Concat(documento.Abreviatura,documento.Numeracion) as soporte, \n";
               sql+="usuario.NoDocumento , \n";
               sql+="Concat(usuario.RazonSocial,' ',usuario.Nombre,' ',usuario.Snombre,' ',usuario.Apellido,' ',usuario.Sapellido) as Usuario , \n";
               sql+="documento.FechaContable, \n";
               sql+="documento.TDocumento, \n";
               sql+="asiento.idAsiento , \n";
               sql+="asiento.idCtaPUC , \n";
               sql+="asiento.Detalle, \n";
               sql+="asiento.Debito , \n";
               sql+="asiento.Credito , \n";
               sql+="asiento.Entradas , \n";
               sql+="asiento.Salidas , \n";
               sql+="asiento.NoFactura, \n";
               sql+="asiento.NoFacturaCompra, \n";
               sql+="asiento.BaseIVA, \n";
               sql+="asiento.BaseRTF, \n";
               sql+="documento.numeracion \n";
               sql+="from \n";
               sql+="documento,asiento,usuario \n";
               sql+="where( \n";
               sql+="documento.idDocumento=asiento.idDocumento and \n";
               sql+="usuario.idCta_T=documento.IdCta_T_Usuario and \n";
               sql+="documento.Activo=true and \n";
               sql+="documento.FechaContable>=? and \n";
               sql+="documento.FechaContable<=? and \n";
               sql+="asiento.idCtaPUC=? \n";
               sql+=")order by fechacontable,iddocumento,idasiento       \n";
               
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fechai.getTime()));
            ps.setDate(2,new java.sql.Date(fechaf.getTime()));
            ps.setString(3,aux);
            ResultSet rs=ps.executeQuery();
            Vector<AsientoContableDTO> lista= new Vector<AsientoContableDTO>();
            while(rs.next()){
                AsientoContableDTO a=new AsientoContableDTO();
                a.setIddocumento(rs.getObject(1));
                a.setSoporte(rs.getString(2));
                a.setNitusuario(rs.getString(3));
                String u=rs.getString(4);
                String us="";
                if(u.charAt(0)==' '){
                    us=u.substring(1,u.length());
                    a.setUsuario(us);
                }else{
                    a.setUsuario(u);
                }
                a.setFechacontable(new java.util.Date(rs.getDate(5).getTime()));
                a.setTipodocumento(rs.getString(6));                    
                a.setIdasiento(rs.getLong(7));
                a.setIdctapuc(rs.getObject(8));
                a.setDetalle(rs.getString(9));
                a.setDebito(rs.getDouble(10));
                a.setCredito(rs.getDouble(11));
                a.setEntradas(rs.getDouble(12));
                a.setSalidas(rs.getDouble(13));
                a.setNoFactura(rs.getInt(14));
                a.setNoFacturaCompra(rs.getInt(15));
                a.setBaseiva(rs.getDouble(16));
                a.setBasertf(rs.getDouble(17));
                a.setNumeracion(rs.getInt(18));
                lista.add(a);
            }            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ContabilidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public double ObtenerSaldo(String aux, Date fecha) {
        Connection con=null;
        String sql ="select \n";
               if(this.isDebito(aux)){
                   sql+="sum(asiento.debito)-sum(asiento.credito) \n";
               }else{
                   sql+="sum(asiento.credito)-sum(asiento.debito) \n";
               }
               sql+="from \n";
               sql+="documento,asiento \n";
               sql+="where( \n";
               sql+="documento.idDocumento=asiento.idDocumento and \n";               
               sql+="documento.Activo=true and \n";
               sql+="documento.FechaContable<=? and \n";    
               if(aux.length()>8){
               sql+="asiento.idCtaPUC=? \n";
               }else{
               sql+="asiento.idCtaPUC like ? \n";    
               }
               sql+=")       \n";
               
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fecha.getTime()));            
             if(aux.length()>8)
                 ps.setString(2,aux);
             else
                 ps.setString(2,aux+"%");
            ResultSet rs=ps.executeQuery();
            Vector<AsientoContableDTO> lista= new Vector<AsientoContableDTO>();
            double saldo=0;
            if(rs.next()){
               saldo=rs.getDouble(1);   
            }            
            return saldo;
        } catch (SQLException ex) {
            Logger.getLogger(ContabilidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public boolean isDebito(String cta) {
        PucService s=new PucService();
        if(s.ObtenerCtaPuc(cta).getTipoCta().toLowerCase().equals("debito")){
            return true;      
        }
        return false;
    }
    @Override
    public double ObtenerExistencia(String aux, Date fecha) {
        Connection con=null;
        String sql ="select \n";
               if(this.isDebito(aux)){
                   sql+="sum(asiento.entradas)-sum(asiento.salidas) \n";
               }else{
                   sql+="sum(asiento.salidas)-sum(asiento.entradas) \n";
               }
               sql+="from \n";
               sql+="documento,asiento \n";
               sql+="where( \n";
               sql+="documento.idDocumento=asiento.idDocumento and \n";               
               sql+="documento.Activo=true and \n";
               sql+="documento.FechaContable<=? and \n";               
               sql+="asiento.idCtaPUC=? \n";
               sql+=")       \n";
               
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fecha.getTime()));            
            ps.setString(2,aux);
            ResultSet rs=ps.executeQuery();
            Vector<AsientoContableDTO> lista= new Vector<AsientoContableDTO>();
            double saldo=0;
            if(rs.next()){
               saldo=rs.getDouble(1);   
            }            
            return saldo;
        } catch (SQLException ex) {
            Logger.getLogger(ContabilidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<ResumenAuxDTO> ObtenerResumen(String aux, Date fechainicio, Date fechacorte) {
        Connection con=null;
        String sql ="select \n";
               sql+="asiento.idCtaPUC as idc, \n";
               sql+="(select puc.Denominacion from puc where(puc.idCtaPUC=idc)) as denominacion, \n";
               if(this.isDebito(aux)){
                  sql+="SUM(asiento.Debito)-SUM(asiento.Credito), \n";
                  sql+="SUM(asiento.entradas)-SUM(asiento.salidas), \n";
               }else{
                  sql+="SUM(asiento.Credito)-SUM(asiento.Debito), \n";
                  sql+="SUM(asiento.salidas)-SUM(asiento.entradas), \n";
               }
               sql+=" (select SUM(asiento.debito+asiento.credito) from asiento,documento where(documento.iddocumento=asiento.iddocumento and documento.activo=true and asiento.idctaPUC=idc and asiento.basertf!=0 and documento.fechacontable>=? and documento.fechacontable<=?)) as rtf,";
               sql+=" (select SUM(asiento.basertf) from asiento,documento where(documento.iddocumento=asiento.iddocumento and documento.activo=true and asiento.idctaPUC=idc and asiento.basertf!=0 and documento.fechacontable>=? and documento.fechacontable<=?)) as basertf,";
               sql+=" (select SUM(asiento.debito+asiento.credito) from asiento,documento where(documento.iddocumento=asiento.iddocumento and documento.activo=true and asiento.idctaPUC=idc and asiento.baseiva!=0 and documento.fechacontable>=? and documento.fechacontable<=?)) as iva,";
               sql+=" (select SUM(asiento.baseiva) from asiento,documento where(documento.iddocumento=asiento.iddocumento and documento.activo=true and asiento.idctaPUC=idc and asiento.baseiva!=0 and documento.fechacontable>=? and documento.fechacontable<=?)) as baseiva ";
               sql+="from \n";
               sql+="asiento,documento \n";
               sql+=" where( \n";
               sql+="    asiento.idCtaPUC like ? and\n";
               sql+="    asiento.idDocumento=documento.idDocumento and \n";
               sql+="    documento.Activo=true and \n";               
               sql+="    documento.fechacontable>=? and\n";
               sql+="    documento.fechacontable<=? \n";
               sql+=")group by idc order by denominacion \n";               
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);                    
            ResumenAuxDTO.fechainicio=fechainicio;
            ResumenAuxDTO.fechacorte=fechacorte;
            ResumenAuxDTO.codaux=aux;
            PucService pucs=new PucService();
            FechaService fs=new FechaService();
            ResumenAuxDTO.denominacionaux=pucs.ObtenerCtaPuc(aux).getDenominacion();
            ps.setDate(1,new java.sql.Date(fechainicio.getTime()));
            ps.setDate(2,new java.sql.Date(fechacorte.getTime()));            
            ps.setDate(3,new java.sql.Date(fechainicio.getTime()));
            ps.setDate(4,new java.sql.Date(fechacorte.getTime()));            
            ps.setDate(5,new java.sql.Date(fechainicio.getTime()));
            ps.setDate(6,new java.sql.Date(fechacorte.getTime()));            
            ps.setDate(7,new java.sql.Date(fechainicio.getTime()));
            ps.setDate(8,new java.sql.Date(fechacorte.getTime()));            
            ps.setString(9,aux+"%");  
            int clase=Integer.parseInt(""+aux.charAt(0));
            if(clase<=3)
            ps.setDate(10,new java.sql.Date(fs.getDao().ObtenerMenorFecha().getTime()));
            else
            ps.setDate(10,new java.sql.Date(fechainicio.getTime()));    
            ps.setDate(11,new java.sql.Date(fechacorte.getTime()));            
            ResultSet rs=ps.executeQuery();
            Vector<ResumenAuxDTO> lista= new Vector<ResumenAuxDTO>();
            while(rs.next()){
                ResumenAuxDTO r=new ResumenAuxDTO();
                r.setCtat(rs.getString(1).substring(8));
                r.setDenominacion(rs.getString(2));
                r.setSaldo(rs.getDouble(3));
                r.setExistencia(rs.getDouble(4));
                r.setRtf(rs.getDouble(5));
                r.setBasertf(rs.getDouble(6));
                r.setIva(rs.getDouble(7));
                r.setBaseiva(rs.getDouble(8));
                if(r.getSaldo()+r.getExistencia()+r.getBaseiva()+r.getBasertf()+r.getIva()+r.getRtf()!=0){
                   lista.add(r);
                }
            }            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ContabilidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public double ObtenerSaldo(String aux, Date fechainicio, Date fechafin) {
        Connection con=null;
        String sql ="select \n";
               if(this.isDebito(aux)){
                   sql+="sum(asiento.debito)-sum(asiento.credito) \n";
               }else{
                   sql+="sum(asiento.credito)-sum(asiento.debito) \n";
               }
               sql+="from \n";
               sql+="documento,asiento \n";
               sql+="where( \n";
               sql+="documento.idDocumento=asiento.idDocumento and \n";               
               sql+="documento.Activo=true and \n";
               sql+="documento.FechaContable<=? and \n";    
               if(aux.length()>8){
               sql+="asiento.idCtaPUC=? and\n";
               }else{
               sql+="asiento.idCtaPUC like ? and \n";    
               }
               sql+="documento.FechaContable>=?  \n";    
               sql+=")       \n";
               
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fechafin.getTime()));                                  
            if(aux.length()>8)
                 ps.setString(2,aux);
            else
                 ps.setString(2,aux+"%");             
            ps.setDate(3,new java.sql.Date(fechainicio.getTime()));  
            ResultSet rs=ps.executeQuery();
            Vector<AsientoContableDTO> lista= new Vector<AsientoContableDTO>();
            double saldo=0;
            if(rs.next()){
               saldo=rs.getDouble(1);   
            }            
            return saldo;
        } catch (SQLException ex) {
            Logger.getLogger(ContabilidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public double ObtenerDebito(String aux, Date fechainicio, Date fechafin) {
        Connection con=null;
        String sql ="select \n";         
               sql+="sum(asiento.debito) \n";         
               sql+="from \n";
               sql+="documento,asiento \n";
               sql+="where( \n";
               sql+="documento.idDocumento=asiento.idDocumento and \n";               
               sql+="documento.Activo=true and \n";
               sql+="documento.FechaContable<=? and \n";    
               if(aux.length()>8){
               sql+="asiento.idCtaPUC=? and\n";
               }else{
               sql+="asiento.idCtaPUC like ? and \n";    
               }
               sql+="documento.FechaContable>=?  \n";    
               sql+=")       \n";
               
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fechafin.getTime()));                                  
            if(aux.length()>8)
                 ps.setString(2,aux);
            else
                 ps.setString(2,aux+"%");             
            ps.setDate(3,new java.sql.Date(fechainicio.getTime()));  
            ResultSet rs=ps.executeQuery();
            Vector<AsientoContableDTO> lista= new Vector<AsientoContableDTO>();
            double saldo=0;
            if(rs.next()){
               saldo=rs.getDouble(1);   
            }            
            return saldo;
        } catch (SQLException ex) {
            Logger.getLogger(ContabilidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public double ObtenerCredito(String aux, Date fechainicio, Date fechafin) {
        Connection con=null;
        String sql ="select \n";         
               sql+="sum(asiento.credito) \n";         
               sql+="from \n";
               sql+="documento,asiento \n";
               sql+="where( \n";
               sql+="documento.idDocumento=asiento.idDocumento and \n";               
               sql+="documento.Activo=true and \n";
               sql+="documento.FechaContable<=? and \n";    
               if(aux.length()>8){
               sql+="asiento.idCtaPUC=? and\n";
               }else{
               sql+="asiento.idCtaPUC like ? and \n";    
               }
               sql+="documento.FechaContable>=?  \n";    
               sql+=")       \n";
               
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1,new java.sql.Date(fechafin.getTime()));                                  
            if(aux.length()>8)
                 ps.setString(2,aux);
            else
                 ps.setString(2,aux+"%");             
            ps.setDate(3,new java.sql.Date(fechainicio.getTime()));  
            ResultSet rs=ps.executeQuery();
            Vector<AsientoContableDTO> lista= new Vector<AsientoContableDTO>();
            double saldo=0;
            if(rs.next()){
               saldo=rs.getDouble(1);   
            }            
            return saldo;
        } catch (SQLException ex) {
            Logger.getLogger(ContabilidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public Vector<AsientoPorFechaDTO> ObtenerMovimientos(Date fechainicio, Date fechafin) {
        Connection con=null;
        String sql ="";
               sql+="select \n";        
               sql+="documento.idDocumento, \n";        
               sql+="documento.FechaContable, \n";        
               sql+="asiento.idCtaPUC as cta, \n";        
               sql+="substr(asiento.idCtaPUC,1,8) as aux, \n";        
               sql+="(select puc.Denominacion from puc where(puc.idCtaPUC=cta)), \n";        
               sql+="(select puc.Denominacion from puc where(puc.idCtaPUC=aux)),  \n";        
               sql+="asiento.Debito, \n";        
               sql+="asiento.Credito, \n";        
               sql+="CONCAT('',documento.abreviatura,documento.numeracion) \n";        
               sql+="from \n";        
               sql+="documento,asiento \n";        
               sql+="where( \n";        
               sql+="   asiento.idDocumento=documento.idDocumento and \n";        
               sql+="   documento.Activo=true and \n";        
               sql+="   documento.FechaContable>=? and\n";        
               sql+="   documento.FechaContable<=?  \n";                
               sql+=" )order by fechacontable,iddocumento \n";        
          try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);         
            ps.setDate(1,new java.sql.Date(fechainicio.getTime()));
            ps.setDate(2,new java.sql.Date(fechafin.getTime()));  
            AsientoPorFechaDTO.fechainicio=fechainicio;
            AsientoPorFechaDTO.fechacorte=fechafin;                         
            ResultSet rs=ps.executeQuery();
            Vector<AsientoPorFechaDTO> lista= new Vector<AsientoPorFechaDTO>();
            while(rs.next()){
                AsientoPorFechaDTO m=new AsientoPorFechaDTO();
                m.setFecha(new java.util.Date(rs.getDate(2).getTime()));
                m.setIdcta(rs.getObject(3));
                m.setDenominacion("("+rs.getString(6).toUpperCase()+") "+rs.getString(5));
                m.setDebito(rs.getDouble(7));
                m.setCredito(rs.getDouble(8));
                m.setSoporte(rs.getString(9));
                lista.add(m);
            }            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ContabilidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }      
    }

    @Override
    public boolean EstaCuadradoElSistema() {
      Connection con=null;
      String sql ="   ";
             sql+="select \n";
             sql+="   if(sum(asiento.Debito)-sum(asiento.Credito)!=0,false,true) as cuadrado \n";
             sql+="from \n";
             sql+="   asiento,documento \n";
             sql+="where( \n";
             sql+="   documento.idDocumento=asiento.idDocumento and \n";
             sql+="   documento.Activo=true and (   \n";
             sql+="   asiento.idCtaPUC like '1%' or \n";
             sql+="   asiento.idCtaPUC like '2%' or \n";
             sql+="   asiento.idCtaPUC like '3%' or \n";
             sql+="   asiento.idCtaPUC like '4%' or \n";
             sql+="   asiento.idCtaPUC like '5%' or \n";
             sql+="   asiento.idCtaPUC like '6%' or\n";
             sql+="   asiento.idCtaPUC like '7%'  )\n";
             sql+="   )\n";             
        try {
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                boolean val=rs.getBoolean(1);
                return val;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ContabilidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Pool.LiberarConexion(con);
        }
        
    }
}

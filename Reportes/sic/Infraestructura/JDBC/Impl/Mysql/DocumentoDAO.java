/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Infraestructura.JDBC.Impl.Mysql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import sic.Dominio.Core.Usuario.Municipio;
import sic.Dominio.Core.Usuario.TipoDocumento;
import sic.Dominio.Core.Usuario.Usuario;
import sic.Dominio.Core.Documento.Asiento;
import sic.Dominio.Core.Documento.Documento;
import sic.Dominio.Core.Documento.DocumentoRep;
import sic.Dominio.Core.Documento.TipoDocumentoContable;
import sic.Dominio.Core.PUC.Cta_PUC;
import sic.InterfacesDAO.IDocumentoDAO;
import sic.Infraestructura.JDBC.Pool;
import java.sql.Connection;

/**
 *
 * @author FANNY BURGOS
 */
public class DocumentoDAO implements IDocumentoDAO{
    @Override
    public Documento PersistirDocumento(Documento d) {
        String sql="";
        sql+=" insert into documento ";
        sql+="(iddocumento,idCta_T_Usuario,fechacreacion,fechacontable,resumen,creador,numeracion,tdocumento,abreviatura,activo,resolucion) ";
        sql+=" values ";
        sql+=" ("+d.getId()+","+d.getUsuario().getId()+",curdate(),?,?,?,?,?,?,true,?) ";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);                     
            ps.setDate(1,new java.sql.Date(d.getFechaContable().getTime()));
            ps.setString(2,d.getResumen());
            ps.setString(3,d.getCreador());
            ps.setInt(4,d.getNumeracion());
            ps.setString(5,d.getTdocumento());
            ps.setString(6,d.getAbreviatura());
            ps.setInt(7,d.getResolucion());
            ps.executeUpdate();
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public long ObtenerIdUltimoDocumento() {
        Connection con=null;
        String sql="select Max(iddocumento) from documento ";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getLong(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Asiento PersistirAsiento(Asiento a) {
     String sql =" insert into asiento ";
     sql+="(idCtaPUC,idDocumento,detalle,debito,credito,entradas,salidas,nofactura,nofacturacompra,baseiva,basertf,tiporegistro) ";
     sql+=" values ";
     sql+=" ("+a.getCtaPuc().getId()+","+a.getDocumento().getId()+",?,?,?,?,?,?,?,?,?,?) ";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);            
            ps.setString(1,a.getDetalle());                
            ps.setDouble(2,a.getDebito());
            ps.setDouble(3,a.getCredito());
            ps.setDouble(4,a.getEntradas());
            ps.setDouble(5,a.getSalidas());
            ps.setInt(6,a.getNoFactura());
            ps.setInt(7,a.getNoFacturaCompra());
            ps.setDouble(8,a.getBaseIVA());
            ps.setDouble(9,a.getBaseRTF());
            ps.setInt(10,a.getTiporegistro());
            ps.executeUpdate();
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public int ObtenerNumeracion(String tipodocumento,int resolucion) {
        Connection con=null;
        String sql="select Max(numeracion) from documento where(tdocumento=? and resolucion=?)";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,tipodocumento);
            ps.setInt(2,resolucion);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public boolean AnularDocumento(Object idDocumento, String anulador, String razon) {
        String sql =" update documento set ";
               sql+=" anulador=?,razonanulacion=?,fechaanulacion=curdate(),activo=false,modificador=? where(iddocumento="+idDocumento+")";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);            
            ps.setString(1,anulador);
            ps.setString(2,razon);
            ps.setString(3,anulador);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Pool.LiberarConexion(con);
        }
    }   
    @Override
    public boolean ModificarDocumento(Object idDocumento, Documento documento) {
        String sql =" update documento set ";
               sql+=" idCta_T_Usuario=?,fechacontable=?,resumen=?,Modificador=?,fechaanulacion=curdate(),numeracion=? ";
               sql+=" where(iddocumento="+idDocumento+") ";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);            
            ps.setObject(1,documento.getUsuario().getId());
            ps.setDate(2,new java.sql.Date(documento.getFechaContable().getTime()));
            ps.setString(3,documento.getResumen());
            ps.setString(4,documento.getModificador());
            ps.setInt(5,documento.getNumeracion());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<TipoDocumentoContable> ObtenerTiposDeDocumentosContables() {        
        String sql =" select tdocumento,abreviatura from documento group by tdocumento order by iddocumento";               
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            Vector <TipoDocumentoContable> lista=new Vector <TipoDocumentoContable>();
            while(rs.next()){                
                String descripcion=rs.getString(1);
                String abreviaturaa=rs.getString(2);                
                TipoDocumentoContable td=new TipoDocumentoContable();                
                td.setAbreviatura(abreviaturaa);
                td.setDescripcion(descripcion);
                td.setUltimanumeracion(this.ObtenerNumeracion(descripcion));
                lista.add(td);                
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
     public int ObtenerNumeracion(String tipodocumento) {
        Connection con=null;
        String sql="select Max(numeracion) from documento where(tdocumento=?)";
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,tipodocumento);            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){                
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<DocumentoRep> ObtenerReporteDocumento(String tipodocumento, int numeracion) {
        String sql ="";
               sql+=" select \n";
               sql+=" usuario.NoDocumento as NoDocumento  , \n";
               sql+=" concat_ws(' ',usuario.RazonSocial,usuario.Nombre,usuario.Apellido,usuario.Sapellido) as Usuario ,\n";
               sql+=" CONCAT(municipio.Descripcion,',',municipio.DescripcionDPTO,'-',municipio.DescripcionPAIS)as Ubicacion,\n";
               sql+=" usuario.Direccion,\n";
               sql+=" documento.idDocumento,\n";
               sql+=" documento.FechaContable,\n";
               sql+=" documento.FechaCreacion,   \n";
               sql+=" documento.FechaAnulacion,\n";
               sql+=" documento.Resumen,\n";
               sql+=" documento.RazonAnulacion,\n";
               sql+=" documento.Creador,\n";
               sql+=" documento.Anulador,\n";
               sql+=" documento.Modificador,\n";
               sql+=" documento.Numeracion,\n";
               sql+=" documento.TDocumento,\n";
               sql+=" documento.Abreviatura,\n";
               sql+=" documento.Activo ,\n";
               sql+=" asiento.idAsiento,\n";
               sql+=" asiento.idCtaPUC as idc,\n";
               sql+=" asiento.Detalle,\n";
               sql+=" asiento.Debito,\n";
               sql+=" asiento.Credito,\n";
               sql+=" asiento.Entradas,\n";
               sql+=" asiento.Salidas,\n";
               sql+=" asiento.NoFactura,\n";
               sql+=" asiento.NoFacturaCompra,\n";
               sql+=" asiento.BaseIVA,\n";
               sql+=" asiento.BaseRTF, \n";
               sql+=" (select denominacion from puc where(puc.idctapuc=substr(CONCAT(idc,''),1,8))) as denominacion \n";               
               sql+=" from\n";
               sql+=" documento,municipio,asiento,usuario\n";
               sql+=" where( \n";
               sql+="         documento.tdocumento='"+tipodocumento+"'  and     \n";
               sql+="         documento.Numeracion="+numeracion+"    and\n";
               sql+="         documento.idDocumento=asiento.idDocumento  and\n";
               sql+="         documento.IdCta_T_Usuario=usuario.idCta_T and\n";
               sql+="         usuario.idMunicipio=municipio.idMunicipio \n";
               sql+="       )        \n";
         
               
               Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            Vector <DocumentoRep> lista=new Vector <DocumentoRep>();
            double sumadebito=0;
            double sumacredito=0;
            while(rs.next()){                
               DocumentoRep d=new DocumentoRep();
               d.setNodocumento(rs.getString(1));
               d.setUsuario(rs.getString(2));
               d.setUbicacion(rs.getString(3));
               d.setDireccion(rs.getString(4));
               d.setIdDocumento(rs.getObject(5));
               d.setFechacontable(rs.getString(6));
               d.setFechacreacion(rs.getString(7).replace("00:00:00.0",""));
               if(rs.getString(8)!=null){
                  d.setFechaanulacion(rs.getString(8).replace("00:00:00.0",""));
               }
               d.setResumen(rs.getString(9));
               if(rs.getString(10)!=null){
                  d.setRazonanulacion(rs.getString(10));
               }
               d.setCreador(rs.getString(11));
               d.setAnulador(rs.getString(12));
               d.setModificador(rs.getString(13));
               d.setNumeracion(rs.getInt(14));
               d.setTDocumento(rs.getString(15));
               d.setAbreviatura(rs.getString(16));
               d.setActivo(rs.getBoolean(17));
               d.setIdAsiento(rs.getObject(18));
               d.setIdctapuc(rs.getString(19));
               d.setDetalle(rs.getString(20));
               d.setDebito(rs.getDouble(21));
               d.setCredito(rs.getDouble(22));
               d.setEntradas(rs.getDouble(23));
               d.setSalidas(rs.getDouble(24));
               d.setNoFactura(rs.getInt(25));
               d.setNoFacturaCompra(rs.getInt(26));
               d.setBaseIVA(rs.getDouble(27));
               d.setBaseRTF(rs.getDouble(28));
               d.setDenominacion(rs.getString(29));
               sumadebito+=d.getDebito();
               sumacredito+=d.getCredito();
               if(rs.isLast()){
                   d.setSumadebito(sumadebito);
                   d.setSumacredito(sumacredito);
               }
               lista.add(d);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
        
    }
    @Override
    public Documento ObtenerDocumento(String tipodocumento, int numeracion) {
        String sql =" ";
               sql+=" select \n";                        
               sql+=" documento.idDocumento,\n";
               sql+=" documento.FechaContable,\n";
               sql+=" documento.FechaCreacion,   \n";
               sql+=" documento.FechaAnulacion,\n";
               sql+=" documento.Resumen,\n";
               sql+=" documento.RazonAnulacion,\n";
               sql+=" documento.Creador,\n";
               sql+=" documento.Anulador,\n";               
               sql+=" documento.Numeracion,\n";
               sql+=" documento.TDocumento,\n";
               sql+=" documento.Abreviatura,\n";
               sql+=" documento.Activo ,\n";               
               sql+=" usuario.idcta_t ,\n";               
               sql+=" usuario.nodocumento ,\n";  
               sql+=" usuario.nombre ,\n";               
               sql+=" usuario.snombre ,\n";               
               sql+=" usuario.apellido ,\n";               
               sql+=" usuario.sapellido ,\n";               
               sql+=" usuario.razonsocial ,\n";               
               sql+=" usuario.sobrenombre ,\n";    
               sql+=" usuario.regimen ,\n";               
               sql+=" usuario.agenteretenedor ,\n";               
               sql+=" usuario.telefono ,\n";               
               sql+=" usuario.direccion ,\n";               
               sql+=" usuario.correo ,\n";                                         
               sql+=" usuario.digitodian , \n"; 
               sql+=" municipio.idmunicipio , \n";                                           
               sql+=" municipio.descripcion , \n";                                           
               sql+=" municipio.iddpto , \n";                                           
               sql+=" municipio.descripciondpto , \n";                                           
               sql+=" municipio.idpais , \n";                                           
               sql+=" municipio.descripcionpais , \n";                                           
               sql+=" documento.Modificador,\n";
               sql+=" tipodocumento.idtipodocumento ,\n";
               sql+=" tipodocumento.descripcion ,\n";
               sql+=" tipodocumento.abreviatura \n";
               sql+=" from\n";
               sql+=" documento,municipio,usuario,tipodocumento\n";
               sql+=" where( \n";               
               sql+="         documento.tdocumento='"+tipodocumento+"'  and     \n";
               sql+="         documento.Numeracion="+numeracion+"    and\n";               
               sql+="         usuario.idtipodocumento=tipodocumento.idtipodocumento  and     \n";
               sql+="         documento.IdCta_T_Usuario=usuario.idCta_T and\n";
               sql+="         usuario.idMunicipio=municipio.idMunicipio \n";
               sql+="       )        \n";        
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();            
            if(rs.next()){                
                Documento d=new Documento();
                d.setId(rs.getObject(1));
                d.setFechaContable(new java.util.Date(rs.getDate(2).getTime()));
                d.setFechaCreacion(new java.util.Date(rs.getDate(3).getTime()));
                d.setActivo(rs.getBoolean(12));
                if(d.isActivo()==false){
                   d.setRazonAnulacion(rs.getString(6));
                   d.setFechaAnulacion(new java.util.Date(rs.getDate(4).getTime()));
                   d.setAnulador(rs.getString(8));
                }else{
                   if(rs.getDate(4)!=null)
                   d.setFechaAnulacion(new java.util.Date(rs.getDate(4).getTime()));                    
                }
                d.setResumen(rs.getString(5));                
                d.setCreador(rs.getString(7));                
                d.setNumeracion(rs.getInt(9));
                d.setTdocumento(rs.getString(10));
                d.setAbreviatura(rs.getString(11));
                d.setModificador(rs.getString(33));
                
                
                Usuario u=new Usuario();
                u.setId(rs.getObject(13));                                
                u.setNoDocumento(rs.getLong(14));
                u.setNombre(rs.getString(15));
                u.setsNombre(rs.getString(16));
                u.setApellido(rs.getString(17));
                u.setsApellido(rs.getString(18));
                u.setRazonSocial(rs.getString(19));
                u.setSobreNombre(rs.getString(20));
                u.setRegimen(rs.getString(21));
                u.setAgenteRetenedor(rs.getString(22));
                u.setTelefono(rs.getString(23));
                u.setDireccion(rs.getString(24));
                u.setCorreo(rs.getString(25));
                u.setDigitoDIAN(rs.getString(26));
                
                
                Municipio m=new Municipio();
                m.setId(rs.getObject(27));
                m.setDescripcion(rs.getString(28));
                m.setIddpto(rs.getInt(29));
                m.setDescripciondpto(rs.getString(30));
                m.setIdpais(rs.getInt(31));
                m.setDescripcionpais(rs.getString(32));
                
                TipoDocumento td=new TipoDocumento();
                td.setId(rs.getObject(34));
                td.setDescripcion(rs.getString(35));
                td.setAbreviatura(rs.getString(36));
                
                u.setTipodocumento(td);
                u.setMunicipio(m);
                d.setUsuario(u);
                return d;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public Vector<Asiento> ObtenerAsientos(Object idDocumento) {
         String sql ="";
               sql+=" select \n";         
               sql+=" asiento.idAsiento,\n";
               sql+=" asiento.idCtaPUC,\n";
               sql+=" asiento.Detalle,\n";
               sql+=" asiento.Debito,\n";
               sql+=" asiento.Credito,\n";
               sql+=" asiento.Entradas,\n";
               sql+=" asiento.Salidas,\n";
               sql+=" asiento.NoFactura,\n";
               sql+=" asiento.NoFacturaCompra,\n";
               sql+=" asiento.BaseIVA,\n";
               sql+=" asiento.BaseRTF, \n";               
               sql+=" asiento.tiporegistro \n";               
               sql+=" from\n";
               sql+=" asiento \n";
               sql+=" where( \n";               
               sql+="         asiento.idDocumento="+idDocumento+"   \n";                              
               sql+="       )        \n";
         
               
               Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            Vector <Asiento> lista=new Vector <Asiento>();
            while(rs.next()){                                                                
                Asiento a=new Asiento();
                a.setId(rs.getObject(1));
                   Cta_PUC cta=new Cta_PUC();
                   cta.setId(rs.getObject(2));
                a.setCtaPuc(cta);                   
                a.setDetalle(rs.getString(3));
                a.setDebito(rs.getDouble(4));
                a.setCredito(rs.getDouble(5));
                a.setEntradas(rs.getDouble(6));
                a.setSalidas(rs.getDouble(7));
                a.setNoFactura(rs.getInt(8));
                a.setNoFacturaCompra(rs.getInt(9));
                a.setBaseIVA(rs.getDouble(10));
                a.setBaseRTF(rs.getDouble(11));
                a.setTiporegistro(rs.getInt(12));
                Documento d=new Documento();
                d.setId(idDocumento);
                a.setDocumento(d);
                lista.add(a);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
    @Override
    public boolean ModificarAsiento(Object idAsiento, Asiento a) {
        String sql =" update asiento set ";
               sql+=" idctapuc=?,iddocumento=?, ";
               sql+=" detalle=?,debito=?,credito=?,entradas=?, ";
               sql+=" salidas=?,nofactura=?,nofacturacompra=?,baseiva=?,basertf=?,tiporegistro=? where(idasiento="+idAsiento+") ";
        Connection con=null;        
        try {                        
            con=Pool.ObtenerConexion();        
            PreparedStatement ps=con.prepareStatement(sql);            
            ps.setObject(1,a.getCtaPuc().getId());
            ps.setObject(2,a.getDocumento().getId());
            ps.setString(3,a.getDetalle());
            ps.setDouble(4,a.getDebito());
            ps.setDouble(5,a.getCredito());
            ps.setDouble(6,a.getEntradas());
            ps.setDouble(7,a.getSalidas());
            ps.setInt(8,a.getNoFactura());
            ps.setInt(9,a.getNoFacturaCompra());
            ps.setDouble(10,a.getBaseIVA());
            ps.setDouble(11,a.getBaseRTF());
            ps.setInt(12,a.getTiporegistro());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public Documento ObtenerDocumentoDescuadrado() {
        Connection con=null;
      String sql ="   ";
             sql+="select \n";
             sql+="asiento.idDocumento , \n";
             sql+="documento.TDocumento, \n";
             sql+="documento.Numeracion, \n";
             sql+="sum(asiento.Debito) as debito, \n";
             sql+="sum(asiento.Credito) as credito, \n";
             sql+="abs(sum(asiento.Debito)-sum(asiento.Credito)) as diferencia \n";
             sql+="from  \n";
             sql+=" documento,asiento \n";
             sql+=" where( \n";
             sql+=" documento.idDocumento=asiento.idDocumento and \n";
             sql+=" documento.Activo=true  \n";
             sql+=" )group by idDocumento order by diferencia DESC limit 1 \n";
             
        try {
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                double diferencia=rs.getDouble(6);
                if(diferencia>0){
                   Documento d=this.ObtenerDocumento(rs.getString(2),rs.getInt(3));
                   return d;
                }                
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public Documento ObtenerDocumento(Object idDocumento) {
        String sql =" ";
               sql+=" select \n";                        
               sql+=" documento.idDocumento,\n";
               sql+=" documento.FechaContable,\n";
               sql+=" documento.FechaCreacion,   \n";
               sql+=" documento.FechaAnulacion,\n";
               sql+=" documento.Resumen,\n";
               sql+=" documento.RazonAnulacion,\n";
               sql+=" documento.Creador,\n";
               sql+=" documento.Anulador,\n";               
               sql+=" documento.Numeracion,\n";
               sql+=" documento.TDocumento,\n";
               sql+=" documento.Abreviatura,\n";
               sql+=" documento.Activo ,\n";               
               sql+=" usuario.idcta_t ,\n";               
               sql+=" usuario.nodocumento ,\n";  
               sql+=" usuario.nombre ,\n";               
               sql+=" usuario.snombre ,\n";               
               sql+=" usuario.apellido ,\n";               
               sql+=" usuario.sapellido ,\n";               
               sql+=" usuario.razonsocial ,\n";               
               sql+=" usuario.sobrenombre ,\n";    
               sql+=" usuario.regimen ,\n";               
               sql+=" usuario.agenteretenedor ,\n";               
               sql+=" usuario.telefono ,\n";               
               sql+=" usuario.direccion ,\n";               
               sql+=" usuario.correo ,\n";                                         
               sql+=" usuario.digitodian , \n"; 
               sql+=" municipio.idmunicipio , \n";                                           
               sql+=" municipio.descripcion , \n";                                           
               sql+=" municipio.iddpto , \n";                                           
               sql+=" municipio.descripciondpto , \n";                                           
               sql+=" municipio.idpais , \n";                                           
               sql+=" municipio.descripcionpais , \n";                                           
               sql+=" documento.Modificador,\n";
               sql+=" tipodocumento.idtipodocumento ,\n";
               sql+=" tipodocumento.descripcion ,\n";
               sql+=" tipodocumento.abreviatura \n";
               sql+=" from\n";
               sql+=" documento,municipio,usuario,tipodocumento\n";
               sql+=" where( \n";               
               sql+="         documento.iddocumento="+idDocumento+"  and     \n";               
               sql+="         usuario.idtipodocumento=tipodocumento.idtipodocumento  and     \n";
               sql+="         documento.IdCta_T_Usuario=usuario.idCta_T and\n";
               sql+="         usuario.idMunicipio=municipio.idMunicipio \n";
               sql+="       )        \n";        
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();            
            if(rs.next()){                
                Documento d=new Documento();
                d.setId(rs.getObject(1));
                d.setFechaContable(new java.util.Date(rs.getDate(2).getTime()));
                d.setFechaCreacion(new java.util.Date(rs.getDate(3).getTime()));
                d.setActivo(rs.getBoolean(12));
                if(d.isActivo()==false){
                   d.setRazonAnulacion(rs.getString(6));
                   d.setFechaAnulacion(new java.util.Date(rs.getDate(4).getTime()));
                   d.setAnulador(rs.getString(8));
                }else{
                   if(rs.getDate(4)!=null)
                   d.setFechaAnulacion(new java.util.Date(rs.getDate(4).getTime()));                    
                }
                d.setResumen(rs.getString(5));                
                d.setCreador(rs.getString(7));                
                d.setNumeracion(rs.getInt(9));
                d.setTdocumento(rs.getString(10));
                d.setAbreviatura(rs.getString(11));
                d.setModificador(rs.getString(33));
                
                
                Usuario u=new Usuario();
                u.setId(rs.getObject(13));                                
                u.setNoDocumento(rs.getLong(14));
                u.setNombre(rs.getString(15));
                u.setsNombre(rs.getString(16));
                u.setApellido(rs.getString(17));
                u.setsApellido(rs.getString(18));
                u.setRazonSocial(rs.getString(19));
                u.setSobreNombre(rs.getString(20));
                u.setRegimen(rs.getString(21));
                u.setAgenteRetenedor(rs.getString(22));
                u.setTelefono(rs.getString(23));
                u.setDireccion(rs.getString(24));
                u.setCorreo(rs.getString(25));
                u.setDigitoDIAN(rs.getString(26));
                
                
                Municipio m=new Municipio();
                m.setId(rs.getObject(27));
                m.setDescripcion(rs.getString(28));
                m.setIddpto(rs.getInt(29));
                m.setDescripciondpto(rs.getString(30));
                m.setIdpais(rs.getInt(31));
                m.setDescripcionpais(rs.getString(32));
                
                TipoDocumento td=new TipoDocumento();
                td.setId(rs.getObject(34));
                td.setDescripcion(rs.getString(35));
                td.setAbreviatura(rs.getString(36));
                
                u.setTipodocumento(td);
                u.setMunicipio(m);
                d.setUsuario(u);
                return d;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }

    @Override
    public Vector<Documento> ObtenerDocumentos(String tipodocumento) {
        String sql =" ";
               sql+=" select \n";                        
               sql+=" documento.idDocumento,\n";
               sql+=" documento.FechaContable,\n";
               sql+=" documento.FechaCreacion,   \n";
               sql+=" documento.FechaAnulacion,\n";
               sql+=" documento.Resumen,\n";
               sql+=" documento.RazonAnulacion,\n";
               sql+=" documento.Creador,\n";
               sql+=" documento.Anulador,\n";               
               sql+=" documento.Numeracion,\n";
               sql+=" documento.TDocumento,\n";
               sql+=" documento.Abreviatura,\n";
               sql+=" documento.Activo ,\n";               
               sql+=" usuario.idcta_t ,\n";               
               sql+=" usuario.nodocumento ,\n";  
               sql+=" usuario.nombre ,\n";               
               sql+=" usuario.snombre ,\n";               
               sql+=" usuario.apellido ,\n";               
               sql+=" usuario.sapellido ,\n";               
               sql+=" usuario.razonsocial ,\n";               
               sql+=" usuario.sobrenombre ,\n";    
               sql+=" usuario.regimen ,\n";               
               sql+=" usuario.agenteretenedor ,\n";               
               sql+=" usuario.telefono ,\n";               
               sql+=" usuario.direccion ,\n";               
               sql+=" usuario.correo ,\n";                                         
               sql+=" usuario.digitodian , \n"; 
               sql+=" municipio.idmunicipio , \n";                                           
               sql+=" municipio.descripcion , \n";                                           
               sql+=" municipio.iddpto , \n";                                           
               sql+=" municipio.descripciondpto , \n";                                           
               sql+=" municipio.idpais , \n";                                           
               sql+=" municipio.descripcionpais , \n";                                           
               sql+=" documento.Modificador,\n";
               sql+=" tipodocumento.idtipodocumento ,\n";
               sql+=" tipodocumento.descripcion ,\n";
               sql+=" tipodocumento.abreviatura \n";
               sql+=" from\n";
               sql+=" documento,municipio,usuario,tipodocumento\n";
               sql+=" where( \n";               
               sql+="         documento.tdocumento='"+tipodocumento+"'  and     \n";
               //sql+="         documento.Numeracion="+numeracion+"    and\n";               
               sql+="         usuario.idtipodocumento=tipodocumento.idtipodocumento  and     \n";
               sql+="         documento.IdCta_T_Usuario=usuario.idCta_T and\n";
               sql+="         usuario.idMunicipio=municipio.idMunicipio \n";
               sql+="       )        \n";        
        Connection con=null;
        try {            
            con=Pool.ObtenerConexion();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();            
            Vector <Documento> lista=new Vector <Documento>();
            while(rs.next()){                
                Documento d=new Documento();
                d.setId(rs.getObject(1));
                d.setFechaContable(new java.util.Date(rs.getDate(2).getTime()));
                d.setFechaCreacion(new java.util.Date(rs.getDate(3).getTime()));
                d.setActivo(rs.getBoolean(12));
                if(d.isActivo()==false){
                   d.setRazonAnulacion(rs.getString(6));
                   d.setFechaAnulacion(new java.util.Date(rs.getDate(4).getTime()));
                   d.setAnulador(rs.getString(8));
                }else{
                   if(rs.getDate(4)!=null)
                   d.setFechaAnulacion(new java.util.Date(rs.getDate(4).getTime()));                    
                }
                d.setResumen(rs.getString(5));                
                d.setCreador(rs.getString(7));                
                d.setNumeracion(rs.getInt(9));
                d.setTdocumento(rs.getString(10));
                d.setAbreviatura(rs.getString(11));
                d.setModificador(rs.getString(33));
                
                
                Usuario u=new Usuario();
                u.setId(rs.getObject(13));                                
                u.setNoDocumento(rs.getLong(14));
                u.setNombre(rs.getString(15));
                u.setsNombre(rs.getString(16));
                u.setApellido(rs.getString(17));
                u.setsApellido(rs.getString(18));
                u.setRazonSocial(rs.getString(19));
                u.setSobreNombre(rs.getString(20));
                u.setRegimen(rs.getString(21));
                u.setAgenteRetenedor(rs.getString(22));
                u.setTelefono(rs.getString(23));
                u.setDireccion(rs.getString(24));
                u.setCorreo(rs.getString(25));
                u.setDigitoDIAN(rs.getString(26));
                
                
                Municipio m=new Municipio();
                m.setId(rs.getObject(27));
                m.setDescripcion(rs.getString(28));
                m.setIddpto(rs.getInt(29));
                m.setDescripciondpto(rs.getString(30));
                m.setIdpais(rs.getInt(31));
                m.setDescripcionpais(rs.getString(32));
                
                TipoDocumento td=new TipoDocumento();
                td.setId(rs.getObject(34));
                td.setDescripcion(rs.getString(35));
                td.setAbreviatura(rs.getString(36));
                
                u.setTipodocumento(td);
                u.setMunicipio(m);
                d.setUsuario(u);
                lista.add(d);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;        
        }finally{
            Pool.LiberarConexion(con);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Liquidacion;

import corgranos.Dominio.Core.Maquila.Maquila;
import corgranos.Dominio.Core.Maquila.MaquilaService;
import corgranos.Dominio.Servicios.DataConfiguration;
import corgranos.Dominio.Servicios.DataConfigurationService;
import corgranos.Infraestructura.JDBC.Impl.LiquidacionDePaddyDAO;
import corgranos.InterfacesDAO.ILiquidacionDePaddyDAO;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import sic.Dominio.Core.Cta_T.CtaT_Service;
import sic.Dominio.Core.Cta_T.Cta_T;
import sic.Dominio.Core.Usuario.Usuario;
import sic.Dominio.Core.Usuario.UsuarioService;
import sic.Dominio.Core.Documento.Asiento;
import sic.Dominio.Core.Documento.Documento;
import sic.Dominio.Core.Documento.DocumentoService;
import sic.Dominio.Core.PUC.Cta_PUC;
import sic.Dominio.Core.PUC.PucService;

/**
 *
 * @author FANNY BURGOS
 */
public class LiquidacionDePaddyService extends DocumentoService{
    private ILiquidacionDePaddyBuilder builder;
    private ILiquidacionDePaddyDAO paddydao;
    private Cta_PUC auxproveedor;
    public LiquidacionDePaddyService() {
        super();
        builder=new CorgranosBuilder();        
        paddydao=new LiquidacionDePaddyDAO();
    }
    public boolean GuardarLiquidacion(int numeracion,Usuario usuario, Date FechaContable,
                              String Resumen, String Creador,int tiquete, double bultos,
                              double humedad_pact,double impureza_pact, double rojo_pact, double partido_pact,
                              double yeso_pact,double harina_pact, double humedad_lab, double impureza_lab,
                              double rojo_lab,double partido_lab, double yeso_lab, double harina_lab, double pesobruto,
                              double pesodestarado, double peso_liquidado, double peso14_1, double valor_kg,
                              double valor_liquidado, double valor_compra, double fomento, double bolsa,
                              double retefuente, double ajuste, double total, boolean polipropileno,boolean registrabolsa,double valor_descargue){
    if(paddydao.ObtenerLiquidacion(tiquete)==null){         
        LiquidacionDePaddy lp=new LiquidacionDePaddy(tiquete, bultos, humedad_pact, impureza_pact, rojo_pact,
                                                     partido_pact, yeso_pact, harina_pact, humedad_lab, 
                                                     impureza_lab, rojo_lab, partido_lab, yeso_lab, harina_lab,
                                                     pesobruto, pesodestarado, peso_liquidado, peso14_1, valor_kg,
                                                     valor_liquidado, valor_compra, fomento, bolsa, retefuente,
                                                     ajuste, total, polipropileno, usuario.getId(),valor_descargue);
        lp.setNumeracion(numeracion);
        lp.setUsuario(usuario);
        lp.setFechaContable(FechaContable);        
        lp.setResumen(Resumen);
        lp.setCreador(Creador);        
        lp.setAux_proveedor(this.auxproveedor.getId().toString());
        lp.setAux_descargue(new corgranos.Dominio.Servicios.DataConfigurationService().getDao().ObtenerConfiguracion().getAux_descargues());
        this.getBuilder().setBolsa(registrabolsa);
        getBuilder().setLiquidacionDePaddy(lp);
        lp=getBuilder().BuildLiquidacion();
        Vector <Asiento> vr=this.GenerarAsientos(lp);
        lp.setAsientos(vr);    
        lp.setNorma_local(true);
        lp.setNorma_internacional(true);        
            if(this.Guardar(lp)){                                     
                 if(this.getPaddydao().PersistirLiquidacion(lp)!=null){
                     return true;
                 }
            }               
            return false;
       }
        this.mensaje="Ese Tiquete Ya Existe";
        return false;
    }    
    public LiquidacionDePaddy ObtenerLiquidacionDePaddy(Usuario usuario, Date FechaContable,
                              String Resumen, String Creador,int tiquete, double bultos,
                              double humedad_pact,double impureza_pact, double rojo_pact, double partido_pact,
                              double yeso_pact,double harina_pact, double humedad_lab, double impureza_lab,
                              double rojo_lab,double partido_lab, double yeso_lab, double harina_lab, double pesobruto,
                              double pesodestarado, double peso_liquidado, double peso14_1, double valor_kg,
                              double valor_liquidado, double valor_compra, double fomento, double bolsa,
                              double retefuente, double ajuste, double total, boolean polipropileno,boolean registrabolsa,double valor_descargue){
            
     LiquidacionDePaddy lp=new LiquidacionDePaddy(tiquete, bultos, humedad_pact, impureza_pact, rojo_pact,
                                                     partido_pact, yeso_pact, harina_pact, humedad_lab, 
                                                     impureza_lab, rojo_lab, partido_lab, yeso_lab, harina_lab,
                                                     pesobruto, pesodestarado, peso_liquidado, peso14_1, valor_kg,
                                                     valor_liquidado, valor_compra, fomento, bolsa, retefuente,
                                                     ajuste, total, polipropileno, usuario.getId(),valor_descargue);
        
        lp.setUsuario(usuario);
        lp.setFechaContable(FechaContable);        
        lp.setResumen(Resumen);
        lp.setCreador(Creador);        
        getBuilder().setLiquidacionDePaddy(lp);
        this.getBuilder().setBolsa(registrabolsa);
        lp.setAsientos(this.GenerarAsientos(lp));
        LiquidacionDePaddy lp2=getBuilder().BuildLiquidacion();
        return lp2;   
    }
    public ILiquidacionDePaddyBuilder getBuilder() {
        return builder;
    }
    public ILiquidacionDePaddyDAO getPaddydao() {
        return paddydao;
    }
    private Vector<Asiento> GenerarAsientos(LiquidacionDePaddy lp){        
        Vector<Asiento> lista=new Vector<Asiento>();
        PucService ps=new PucService();
        DataConfigurationService dcs=new DataConfigurationService();                
        DataConfiguration dc=dcs.getDao().ObtenerConfiguracion();
        if(lp.getTotal()!=0){
           Asiento a=new Asiento();
           a.setId("0");
           a.setCtaPuc(ps.ObtenerCtaPuc(dc.getAux_Paddy()));
           a.setDetalle(" Tiquete  "+lp.getTiquete());
           a.setDebito(lp.getValor_compra());
           a.setCredito(0);
           a.setBaseIVA(0);
           a.setBaseRTF(0);
           a.setEntradas(lp.getPeso14_1());
           a.setNoFactura(0);
           a.setNoFacturaCompra(0);
           lista.add(a);
        }
        if(lp.getRetefuente()!=0){
           Asiento a=new Asiento();
           a.setId("0");
           Cta_PUC cta=ps.ObtenerCtaPuc(dc.getAux_Retefuente()+""+lp.getUsuario().getId());
           if(cta==null){
              Cta_PUC aux=ps.ObtenerCtaPuc(dc.getAux_Retefuente()); 
              ps.IngresarEnElPUC(dc.getAux_Retefuente()+""+lp.getUsuario().getId(),lp.getUsuario().getDescripcion(),aux.getTipoCta(), true,lp.getUsuario(),true,aux.getCategoria());                                
              cta=ps.ObtenerCtaPuc(dc.getAux_Retefuente()+""+lp.getUsuario().getId());                 
           }
           a.setCtaPuc(cta);
           a.setDetalle(" Tiquete  "+lp.getTiquete());
           a.setDebito(0);
           a.setCredito(lp.getRetefuente());
           a.setBaseIVA(0);
           a.setBaseRTF(lp.getValor_compra());
           a.setEntradas(0);
           a.setNoFactura(0);
           a.setNoFacturaCompra(0);
           lista.add(a); 
        }
        if(lp.getBolsa()!=0){
           Asiento a=new Asiento();
           a.setId("0");
           Cta_PUC cta=ps.ObtenerCtaPuc(dc.getAux_BolsaPorPagar());           
           a.setCtaPuc(cta);
           a.setDetalle(" Tiquete  "+lp.getTiquete());
           a.setDebito(0);
           a.setCredito(lp.getBolsa());
           a.setBaseIVA(0);
           a.setBaseRTF(0);
           a.setEntradas(0);
           a.setNoFactura(0);
           a.setNoFacturaCompra(0);
           lista.add(a); 
        }
        if(lp.getFomento()!=0){
           Asiento a=new Asiento();
           a.setId("0");
           Cta_PUC cta=ps.ObtenerCtaPuc(dc.getAux_FomentoPorPagar());           
           a.setCtaPuc(cta);
           a.setDetalle(" Tiquete  "+lp.getTiquete());
           a.setDebito(0);
           a.setCredito(lp.getFomento());
           a.setBaseIVA(0);
           a.setBaseRTF(0);
           a.setEntradas(0);
           a.setNoFactura(0);
           a.setNoFacturaCompra(0);
           lista.add(a); 
        }
        if(lp.getValor_descargue()!=0){
           Asiento a=new Asiento();
           a.setId("0");
           Cta_PUC cta=ps.ObtenerCtaPuc(dc.getAux_descargues()+""+lp.getUsuario().getId());           
           if(cta==null){
              Cta_PUC aux=ps.ObtenerCtaPuc(dc.getAux_descargues()); 
              ps.IngresarEnElPUC(dc.getAux_descargues()+""+lp.getUsuario().getId(),lp.getUsuario().getDescripcion(),aux.getTipoCta(), true,lp.getUsuario(),true,aux.getCategoria());                                
              cta=ps.ObtenerCtaPuc(dc.getAux_descargues()+""+lp.getUsuario().getId());                  
           }
           a.setCtaPuc(cta);
           a.setDetalle(" Tiquete  "+lp.getTiquete());
           a.setDebito(0);
           a.setCredito(lp.getValor_descargue());
           a.setBaseIVA(0);
           a.setBaseRTF(0);
           a.setEntradas(0);
           a.setNoFactura(0);
           a.setNoFacturaCompra(0);
           lista.add(a);  
        }
        if(lp.getTotal()-lp.getBolsa()-lp.getRetefuente()-lp.getFomento()-lp.getValor_descargue()!=0){
           Asiento a=new Asiento();
           a.setId("0");
           Cta_PUC cta=ps.ObtenerCtaPuc(this.auxproveedor.getId());           
           lp.setAux_proveedor(this.auxproveedor.getId().toString());
           a.setCtaPuc(cta);
           a.setDetalle(" Tiquete  "+lp.getTiquete());
           a.setDebito(0);
           a.setCredito(lp.getTotal()-lp.getValor_descargue());
           a.setBaseIVA(0);
           a.setBaseRTF(0);
           a.setEntradas(0);
           a.setNoFactura(0);
           a.setNoFacturaCompra(0);
           lista.add(a); 
        }        
        return lista;
    }
    public Vector<LiquidacionDePaddyPorProveedorDTO> ObtenerReportePorProveedor(Object idusuario,Date fechainicial,Date fechafinal,String idCuenta_T){
       UsuarioService us=new UsuarioService();
       LiquidacionDePaddyPorProveedorDTO.usuario=us.getDao().ObtenerUsuario(idusuario).getDescripcion();
       LiquidacionDePaddyPorProveedorDTO.fechainicial=fechainicial;
       LiquidacionDePaddyPorProveedorDTO.fechafinal=fechafinal;
       Vector<LiquidacionDePaddy> lista=paddydao.ObtenerLiquidacionesDePaddy(idusuario, fechainicial, fechafinal,idCuenta_T);
       Vector<LiquidacionDePaddyPorProveedorDTO> listaReporte=new Vector<LiquidacionDePaddyPorProveedorDTO>();
       Iterator <LiquidacionDePaddy> it=lista.iterator();
       while(it.hasNext()){
           LiquidacionDePaddy lp=it.next();
           LiquidacionDePaddyPorProveedorDTO li=Transformar(lp);
           listaReporte.add(li);
       }
       return listaReporte;    
    }
    private LiquidacionDePaddyPorProveedorDTO Transformar(LiquidacionDePaddy lp){
        LiquidacionDePaddyPorProveedorDTO li=new LiquidacionDePaddyPorProveedorDTO();
        li.setFecha(lp.getFechaContable());
        li.setLiquidacion(lp.getNumeracion());
        li.setTiquete(lp.getTiquete());
        li.setHumedadpactada(lp.getHumedad_pact());
        li.setImpurezapactada(lp.getImpureza_pact());
        li.setHumedadlaboratorio(lp.getHumedad_lab());
        li.setImpurezalaboratorio(lp.getImpureza_lab());
        li.setPesobruto(lp.getPesobruto());
        li.setPesodestare(lp.getPesodestarado());
        li.setPesoliquidado(lp.getPeso_liquidado());
        li.setRojolaboratorio(lp.getRojo_lab());
        li.setPartidolaboratorio(lp.getPartido_lab());
        li.setYesolaboratorio(lp.getYeso_lab());
        li.setHarinalaboratorio(lp.getHarina_lab());
        li.setValorpactado(lp.getValor_kg());
        li.setValorliquidado(lp.getValor_liquidado());
        li.setValorcompra(lp.getValor_compra());
        li.setFomento(lp.getFomento());
        li.setBolsa(lp.getBolsa());
        li.setRetefuente(lp.getRetefuente());
        li.setTotal(lp.getTotal());        
        PucService ps=new PucService();
        String aux=ps.ObtenerCtaPuc(lp.getAux_proveedor()).getDenominacion();
        li.setCuenta_t(aux+" - "+lp.getAux_proveedor());
        return li;
    }
    private LiquidacionDePaddyPorRangoDTO Transformar_(LiquidacionDePaddy lp){
        LiquidacionDePaddyPorRangoDTO lpr=new LiquidacionDePaddyPorRangoDTO();
        lpr.setFechacontable(lp.getFechaContable());
        lpr.setHarina(lp.getHarina_lab());
        lpr.setHumedad(lp.getHumedad_lab());
        lpr.setIdDocumento(lp.getId());
        lpr.setImpureza(lp.getImpureza_lab());
        lpr.setLiquidacion(lp.getNumeracion());
        lpr.setMasablanca(lp.getMasablanca());
        lpr.setNit(lp.getNodocumento());
        lpr.setPartido(lp.getPartido_lab());
        lpr.setPeso14_1(lp.getPeso14_1());
        lpr.setPesodestarado(lp.getPesodestarado());
        lpr.setRojo(lp.getRojo_lab());
        lpr.setTiquete(lp.getTiquete());
        lpr.setUsuario(lp.getUsuario().getNombre());
        lpr.setValor(lp.getValor_compra());
        lpr.setFomento(lp.getFomento());
        lpr.setBolsa(lp.getBolsa());
        lpr.setRetefuente(lp.getRetefuente());
        lpr.setPesoliquidado(lp.getPeso_liquidado());
        lpr.setPrecioliquidado(lp.getValor_liquidado());
        lpr.setTotal(lp.getTotal());
        return lpr;
    }
    public Vector<LiquidacionDePaddyPorRangoDTO> ObtenerLiquidacionesDePaddy(Date fechainicial,Date fechafinal){
        LiquidacionDePaddyPorRangoDTO.fechainicial=fechainicial;
        LiquidacionDePaddyPorRangoDTO.fechafinal=fechafinal;
        Iterator<LiquidacionDePaddy> it=paddydao.ObtenerLiquidacionesDePaddy(fechainicial, fechafinal).iterator();
        Vector<LiquidacionDePaddyPorRangoDTO> lista=new Vector<LiquidacionDePaddyPorRangoDTO>();
        while(it.hasNext()){
            LiquidacionDePaddy lp=it.next();
            LiquidacionDePaddyPorRangoDTO lpr=this.Transformar_(lp);
            lista.add(lpr);
        }
        return lista;
    }  
    public void AnularLiquidacion(Object idNumeracionLiquidacion,String actor,String razon){
        LiquidacionDePaddy lp=this.paddydao.ObtenerLiquidacion(idNumeracionLiquidacion);
        if(this.getDao().AnularDocumento(lp.getId(),actor,razon)){               
           lp.setActivo(false);            
           if(this.paddydao.ModificarLiquidacion(lp)!=null){
               this.mensaje="Anulada Con Exito Liquidacion No "+lp.getNumeracion();
           }else{
               this.mensaje="Se Anulo Contablemente pero No se Anulo Administrativamente ";
           }
        }else{
               this.mensaje="No se Pudo Anular";
        }       
    } 
    public void ModificarLiquidacion(LiquidacionDePaddy lp){        
            //Inicializamos los servicios a USAR
            DataConfigurationService dct=new  DataConfigurationService();            
            PucService pservice=new PucService();
            // Inicializo el Objeto DATACONFIGURATION
            DataConfiguration dc=dct.getDao().ObtenerConfiguracion();
            // Obtenemos los Asientos contables de la liquidacion
            Vector<Asiento> lista=this.getDao().ObtenerAsientos(lp.getId());
            this.getDao().EliminarAsientos(lp.getId());
            lp.setAsientos(this.GenerarAsientos(lp));            
            if(this.Modificar(lp)){
                if(this.paddydao.ModificarLiquidacion(lp)!=null){
                    MaquilaService maquila_service=new MaquilaService();
                    Maquila m=maquila_service.getMaquilaDao().ObtenerMaquilaDeLiquidacion(lp.getId());                    
                    if(m!=null)
                       maquila_service.ActualizarMaquila(m.getId());
                    this.mensaje="Modificado Con Exito";
                }else{
                    this.mensaje="Modificado Contablemente y No se Pudo Modificar Administrativamente";
                }
            }else{
                this.mensaje="No Se Pudo Modificar En Contabilidad";
            }        
    }
    public LiquidacionDePaddyDTO ObtenerLiquidacionDePaddyDTO(Object idNumeracionLiquidacion){
        LiquidacionDePaddy lp=this.paddydao.ObtenerLiquidacion(idNumeracionLiquidacion);
        Documento d=this.getDao().ObtenerDocumento(lp.getTdocumento(),lp.getNumeracion());
        if(d!=null){
           lp.setCreador(d.getCreador());
           lp.setFechaCreacion(d.getFechaCreacion());
           lp.setFechaAnulacion(d.getFechaAnulacion());           
           lp.setActivo(d.isActivo());
           lp.setModificador(d.getModificador());          
           return this.TransformarLiquidacionDTO(lp);
        }
        return null;
    }
    private LiquidacionDePaddyDTO TransformarLiquidacionDTO(LiquidacionDePaddy lp){
        LiquidacionDePaddyDTO l=new LiquidacionDePaddyDTO();
        l.setActiva(lp.isActivo());
        l.setNumeracion(lp.getNumeracion());        
        l.setBolsa(lp.getBolsa());
        l.setCreador(lp.getCreador());
        l.setFechacontable(lp.getFechaContable());
        l.setFechacreacion(lp.getFechaCreacion());
        l.setFechamodificacion(lp.getFechaAnulacion());
        l.setFomento(lp.getFomento());
        l.setHarina_lab(lp.getHarina_lab());
        l.setHarina_pact(lp.getHarina_pact());
        l.setHumedad_lab(lp.getHumedad_lab());
        l.setHumedad_pact(lp.getHumedad_pact());
        l.setImpureza_lab(lp.getImpureza_lab());
        l.setImpureza_pact(lp.getImpureza_pact());
        l.setModificador(lp.getModificador());
        l.setNit(lp.getNodocumento());
        l.setPartido_lab(lp.getPartido_lab());
        l.setPartido_pact(lp.getPartido_pact());
        l.setPeso_liquidado(lp.getPeso_liquidado());
        l.setPesobruto(lp.getPesobruto());
        l.setPesodestarado(lp.getPesodestarado());
        l.setProveedor(lp.getUsuario().NombreCompleto());
        l.setRetefuente(lp.getRetefuente());
        l.setRojo_lab(lp.getRojo_lab());
        l.setRojo_pact(lp.getRojo_pact());
        l.setTiquete(lp.getTiquete());
        l.setTotal(lp.getTotal());
        l.setValor_compra(lp.getValor_compra());
        l.setValor_kg(lp.getValor_kg());
        l.setValor_liquidado(lp.getValor_liquidado());
        l.setYeso_lab(lp.getYeso_lab());
        l.setYeso_pact(lp.getYeso_pact());        
        PucService ps=new PucService();
        String aux=ps.ObtenerCtaPuc(lp.getAux_proveedor()).getDenominacion();
        l.setCuenta_t(aux);
        return l;
    }

    /**
     * @param auxproveedor the auxproveedor to set
     */
    public void setAuxproveedor(Cta_PUC auxproveedor) {
        this.auxproveedor = auxproveedor;
    }
    public double ObtenerMasaBlancaTotalLiquidaciones(Date fecha_inicial,Date fecha_final){        
        return this.paddydao.ObtenerTotalMasaBlanca(fecha_inicial,fecha_final);
    }
}
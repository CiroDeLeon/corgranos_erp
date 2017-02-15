/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Dominio.Servicios.Contabilidad;

import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import sic.Dominio.Core.Cta_T.CtaT_Service;
import sic.Dominio.Core.PUC.Cta_PUC;
import sic.Dominio.Core.PUC.PucService;
import sic.Servicios.FechaService;
import sic.Infraestructura.JDBC.Impl.Mysql.ContabilidadDAO;
import sic.InterfacesDAO.IContabilidadDAO;

/**
 *
 * @author FANNY BURGOS
 */
public class ContabilidadService {
     // La variable suma es la suma 
    // cuando se llama al metodo transformar
    private double suma=0;
    private IContabilidadDAO dao;   
    public  ContabilidadService() {
        dao=new ContabilidadDAO();
    }
    public  IContabilidadDAO getDao() {
        return dao;
    }
    public  Vector<EstadoFinancieroDTO> ObtenerEstadoDeResultados(Date fechacorte,Date fechainicio,int digitos){
        Vector<EstadoFinancieroDTO> vr=new Vector<EstadoFinancieroDTO>();
        PucService ps=new PucService();        
        vr.add(new EstadoFinancieroDTO("","INGRESOS OPERACIONALES",0));
        vr.addAll(this.Trasformar(ps.getDao().ObtenerPUC(41,digitos),fechainicio,fechacorte,false));
        vr.add(new EstadoFinancieroDTO("","                             TOTAL INGRESOS OPERACIONALES=========================>", getSuma()));
        EstadoFinancieroDTO.sumaingresosop=getSuma();        
        vr.add(new EstadoFinancieroDTO("","COSTOS DE VENTAS",0));
        vr.addAll(this.Trasformar(ps.getDao().ObtenerPUC(6,digitos),fechainicio,fechacorte,true));
        vr.add(new EstadoFinancieroDTO("","                             TOTAL COSTOS DE VENTAS===============================>", getSuma()));
        EstadoFinancieroDTO.sumacostosvta=getSuma();    
        vr.add(new EstadoFinancieroDTO("","COSTOS DE PRODUCCION",0));
        vr.addAll(this.Trasformar(ps.getDao().ObtenerPUC(7,digitos),fechainicio,fechacorte,true));
        vr.add(new EstadoFinancieroDTO("","                             TOTAL COSTOS DE PRODUCCION===========================>", getSuma()));
        EstadoFinancieroDTO.sumacostosprod=getSuma();
        vr.add(new EstadoFinancieroDTO("","GASTOS OPERACIONALES DE ADMINISTRACION",0));
        vr.addAll(this.Trasformar(ps.getDao().ObtenerPUC(51,digitos),fechainicio,fechacorte,true));
        vr.add(new EstadoFinancieroDTO("","                             TOTAL GASTOS OPERACIONALES DE ADMINISTRACION=========>", getSuma()));
        EstadoFinancieroDTO.sumagastosopadm=getSuma();
        vr.add(new EstadoFinancieroDTO("","GASTOS OPERACIONALES DE VENTAS",0));
        vr.addAll(this.Trasformar(ps.getDao().ObtenerPUC(52,digitos),fechainicio,fechacorte,true));
        vr.add(new EstadoFinancieroDTO("","                             TOTAL GASTOS OPERACIONALES DE VENTAS=================>", getSuma()));        
        EstadoFinancieroDTO.sumagastosopvta=getSuma();
        vr.add(new EstadoFinancieroDTO("","GASTOS NO OPERACIONALES",0));
        vr.addAll(this.Trasformar(ps.getDao().ObtenerPUC(53,digitos),fechainicio,fechacorte,true));
        vr.add(new EstadoFinancieroDTO("","                             TOTAL GASTOS NO OPERACIONALES =======================>", getSuma()));        
        EstadoFinancieroDTO.sumagastosnop=getSuma();
        vr.add(new EstadoFinancieroDTO("","GASTOS IMPUESTO DE RENTA",0));
        vr.addAll(this.Trasformar(ps.getDao().ObtenerPUC(54,digitos),fechainicio,fechacorte,true));
        vr.add(new EstadoFinancieroDTO("","                             TOTAL GASTOS IMPUESTO DE RENTA ======================>", getSuma()));        
        EstadoFinancieroDTO.sumagastosrta=getSuma();
        vr.add(new EstadoFinancieroDTO("","GASTOS GANACIA Y PERDIDA",0));
        vr.addAll(this.Trasformar(ps.getDao().ObtenerPUC(59,digitos),fechainicio,fechacorte,false));
        vr.add(new EstadoFinancieroDTO("","                             TOTAL GASTOS GANANCIA Y PERDIDA =====================>", getSuma()));        
        EstadoFinancieroDTO.sumagastospyg=getSuma();
        return vr; 
    }
    public  Vector<EstadoFinancieroDTO> ObtenerBalance(Date fechacorte,int digitos){
        Vector<EstadoFinancieroDTO> vr=new Vector<EstadoFinancieroDTO>();
        PucService ps=new PucService();
        Date fechainicio=(new FechaService()).getDao().ObtenerMenorFecha();
        vr.add(new EstadoFinancieroDTO("","ACTIVO",0));
        vr.addAll(this.Trasformar(ps.getDao().ObtenerPUC(1,digitos),fechainicio,fechacorte,true));
        vr.add(new EstadoFinancieroDTO("","                                       Total Activos==============================>", getSuma()));
        EstadoFinancieroDTO.sumaactivos=getSuma();        
        vr.add(new EstadoFinancieroDTO("","PASIVO",0));
        vr.addAll(this.Trasformar(ps.getDao().ObtenerPUC(2,digitos),fechainicio,fechacorte,false));
        vr.add(new EstadoFinancieroDTO("","                                       Total Pasivos=============================>", getSuma()));
        EstadoFinancieroDTO.sumapasivos=getSuma();    
        vr.add(new EstadoFinancieroDTO("","PATRIMONIO",0));
        vr.addAll(this.Trasformar(ps.getDao().ObtenerPUC(3,digitos),fechainicio,fechacorte,false));
        vr.add(new EstadoFinancieroDTO("","                                       Total Patrimonio===========================>", getSuma()));
        EstadoFinancieroDTO.sumapatrimonio=getSuma();
        EstadoFinancieroDTO.utilidad=getDao().ObtenerSaldo("4",fechacorte)-getDao().ObtenerSaldo("5",fechacorte)-getDao().ObtenerSaldo("6",fechacorte)-getDao().ObtenerSaldo("7",fechacorte);
        return vr; 
    }
    private Vector<EstadoFinancieroDTO> Trasformar(Vector <Cta_PUC> vr,Date fechainicio,Date fechacorte,boolean isDebitoLaClase){
        Iterator <Cta_PUC> it=vr.iterator();
        Vector<EstadoFinancieroDTO> v=new Vector<EstadoFinancieroDTO>();
        EstadoFinancieroDTO.fechacorte=fechacorte;
        EstadoFinancieroDTO.fechainicio=fechainicio;
        suma=0;
        while(it.hasNext()){
            Cta_PUC cta=it.next();
            EstadoFinancieroDTO ef=new EstadoFinancieroDTO();
            ef.setCodigocta(cta.getId().toString());
            ef.setDenominacion(cta.getDenominacion());            
            ef.setSaldo(dao.ObtenerSaldo(ef.getCodigocta(),fechainicio,fechacorte));            
            if(ef.getSaldo()!=0){               
               if((isDebitoLaClase && cta.getTipoCta().equals("Debito")) || (isDebitoLaClase==false && cta.getTipoCta().equals("Credito"))){
                  suma+=ef.getSaldo();
               }else{
                  double sal=ef.getSaldo()*-1; 
                  suma+=sal;
                  ef.setSaldo(sal);
               }
               v.add(ef);
            }
        }
        return v;
    }
    public  Vector<ReporteDeSaldoCtaTDTO> ObtenerSaldos(Object idCtat,Date fechacorte){
        PucService ps=new PucService();
        CtaT_Service ts=new CtaT_Service();
        Iterator<Cta_PUC>it=ps.getDao().ObtenerPUC(idCtat).iterator();
        Vector<ReporteDeSaldoCtaTDTO> vr=new Vector<ReporteDeSaldoCtaTDTO>();
        ReporteDeSaldoCtaTDTO.fechacorte=fechacorte;
        ReporteDeSaldoCtaTDTO.idctat=idCtat;
        ReporteDeSaldoCtaTDTO.titulo=ts.getDaot().ObtenerCtaT(idCtat).getDescripcion();
        while(it.hasNext()){
            Cta_PUC cta=it.next();
            ReporteDeSaldoCtaTDTO rs=new ReporteDeSaldoCtaTDTO();
            String aux=cta.getId().toString().substring(0,8);
            rs.setCodigocta(aux);
            rs.setDenominacion(ps.ObtenerCtaPuc(aux).getDenominacion());
            rs.setSaldo(this.getDao().ObtenerSaldo(cta.getId().toString(),fechacorte));
            vr.add(rs);
        }
        return vr;
    }
    public  Vector<EstractoCtaDTO> ObtenerEstractoPorCta(Object idCtaI,Object idCtaF,Date fechainicio,Date fechacorte){
        PucService ps=new PucService();
        Iterator <Cta_PUC> it=ps.ObtenerAuxiliaresT(idCtaI,idCtaF).iterator();
        Vector<EstractoCtaDTO> vr=new Vector<EstractoCtaDTO>();
        EstractoCtaDTO.cta=idCtaI.toString();
        EstractoCtaDTO.denominacioncta=ps.ObtenerCtaPuc(idCtaI).getDenominacion();
        EstractoCtaDTO.ctab=idCtaF.toString();
        EstractoCtaDTO.denominacionctab=ps.ObtenerCtaPuc(idCtaF).getDenominacion();
        EstractoCtaDTO.fechainicio=fechainicio;
        EstractoCtaDTO.fechacorte=fechacorte;
        while(it.hasNext()){
            Cta_PUC cta=it.next();
            EstractoCtaDTO ec=new EstractoCtaDTO();
            ec.setCodigocta(cta.getId().toString());
            ec.setDenominacion(cta.getDenominacion());
            ec.setSaldoanterior(this.getDao().ObtenerSaldo(cta.getId().toString(),new java.util.Date(fechainicio.getTime()-86400000)));
            ec.setDebito(this.getDao().ObtenerDebito(cta.getId().toString(),fechainicio, fechacorte));
            ec.setCredito(this.getDao().ObtenerCredito(cta.getId().toString(),fechainicio, fechacorte));
            ec.setSaldo(this.getDao().ObtenerSaldo(cta.getId().toString(),fechacorte));               
            if(ec.getSaldoanterior()!=0 || ec.getDebito()!=0 || ec.getCredito()!=0 || ec.getSaldo()!=0){
               vr.add(ec);    
            }
        }
        return vr;
    } 
    public  Vector<AnexoDTO> ObtenerAnexos(int clase,boolean detallado,Date fechainicio,Date fechacorte,boolean ClaseDebito){
        PucService ps=new PucService();
        Iterator<Cta_PUC> it=ps.getDao().ObtenerPUC(clase,8).iterator();
        AnexoDTO.fechainicio=fechainicio;
        AnexoDTO.fechacorte=fechacorte;
        AnexoDTO.clase=clase;
        AnexoDTO.denominacionclase=ps.ObtenerCtaPuc(""+clase).getDenominacion();
        Vector<AnexoDTO> lista=new Vector<AnexoDTO>();
        this.suma=0;
        while(it.hasNext()){
            Cta_PUC aux=it.next();
            AnexoDTO a=new AnexoDTO();
                   a.setCodigocta(aux.getId().toString());
                   a.setDenominacion(aux.getDenominacion());
                   a.setParcial(0);
                   a.setSaldo(this.getDao().ObtenerSaldo(aux.getId().toString(), fechainicio, fechacorte));                   
                   if(a.getSaldo()!=0){
                      if((ClaseDebito && aux.getTipoCta().equals("Debito")) || (ClaseDebito==false && aux.getTipoCta().equals("Credito"))){
                       
                      }else{
                         double s=a.getSaldo(); 
                         a.setSaldo(s*-1);                            
                      } 
                      suma+=a.getSaldo();
                      lista.add(a);            
                      if(detallado){                                             
                         Iterator<Cta_PUC> it2=ps.InspeccionarAuxiliar("",aux.getId()).iterator(); 
                         while(it2.hasNext()){
                            Cta_PUC cta=it2.next();
                            System.out.println(cta.getId()+" faro");
                            AnexoDTO an=new AnexoDTO();
                            an.setCodigocta(cta.getId().toString());
                            an.setDenominacion(cta.getDenominacion());
                            an.setParcial(this.getDao().ObtenerSaldo(cta.getId().toString(), fechainicio, fechacorte));
                            an.setSaldo(0);
                            if(an.getParcial()!=0){
                               if((ClaseDebito && cta.getTipoCta().equals("Debito")) || (ClaseDebito==false && cta.getTipoCta().equals("Credito"))){
                       
                               }else{          
                                  double s=an.getParcial(); 
                                  an.setParcial(s*-1);                            
                               }  
                               lista.add(an);
                            }
                          }
                      }
                   }
        }
        return lista;
    }

    /**
     * @return the suma
     */
    public double getSuma() {
        return suma;
    }
}

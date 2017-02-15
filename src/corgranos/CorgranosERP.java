/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos;


import corgranos.Dominio.Core.Maquila.MaquilaService;
import corgranos.Dominio.Servicios.Costo.CostoService;
import corgranos.Dominio.Servicios.Laboratorio.LaboratorioService;
import corgranos.Servicios.Mantenimiento.SincronizacionDeDocumentosContables;
import corgranos.Presentacion.LoginGUI;
import corgranos.Servicios.Seguridad.HiloDeBackupAutomatico;
import java.util.Date;
import sic.Dominio.Core.Documento.DocumentoService;

/**
 *
 * @author FANNY BURGOS
 */
public class CorgranosERP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //new MaquilaService().ReabrirYCerrarMaquilasCerradas();        
        //new MaquilaService().ObtenerEstadoDeResultadosDeMaquilasCerradas();
        //DocumentoService ds=new DocumentoService();
        //ds.OrdenarDocumentos("Nomina");               
        //new MaquilaService().VerFaltantes();
        //new MaquilaService().Verificar();
        LoginGUI login=new LoginGUI();
        login.show();                        
        
        //LaboratorioService ls=new LaboratorioService();
        //ls.Calcula();
        //new DocumentoService().Imprimir("Comprobante De Egreso",new Date(),new Date());
       //SincronizacionDeDocumentosContables s=new SincronizacionDeDocumentosContables();                
        //HiloDeBackupAutomatico hb=new HiloDeBackupAutomatico();
    }   
    
    
}

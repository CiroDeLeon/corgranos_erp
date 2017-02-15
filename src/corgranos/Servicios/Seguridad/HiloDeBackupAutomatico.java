/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Servicios.Seguridad;

import java.util.logging.Level;
import java.util.logging.Logger;
import sic.Dominio.Servicios.Contabilidad.ContabilidadService;

/**
 *
 * @author FANNY BURGOS
 */
public class HiloDeBackupAutomatico implements Runnable{
    Thread t;
    

    public HiloDeBackupAutomatico() {
        t=new Thread(this);
        t.start();
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        while(true){
        try {
           int minutos=40;
           long time=minutos*60000;        
           Thread.sleep(time);           
           if(new ContabilidadService().getDao().EstaCuadradoElSistema()){
              BackupService bs=new BackupService(); 
              bs.RealizarBackup();
              System.out.println(""+bs.getMensaje());              
           }
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloDeBackupAutomatico.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
}

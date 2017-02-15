/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Servicios.Seguridad;

import corgranos.Infraestructura.JDBC.DataBaseConfiguration;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import util.Backup;

/**
 *
 * @author FANNY BURGOS
 */
public class BackupService {
  private String mensaje="";  
  public void RealizarBackup(){
        Backup b;
        b=new Backup(DataBaseConfiguration.ObtenerInstancia().getDriverclass(),
                DataBaseConfiguration.ObtenerInstancia().getUser(),
                DataBaseConfiguration.ObtenerInstancia().getPassword(),
                DataBaseConfiguration.ObtenerInstancia().getUrl());
        Date d=Calendar.getInstance().getTime();
        String hora=""+d.getHours()+"h";
        String mes=(d.getYear()+1900)+"_"+(d.getMonth()+1);
        String fecha=(d.getYear()+1900)+"_"+(d.getMonth()+1)+"_"+d.getDate();
        String curDir = System.getProperty("user.dir")+"\\Copias de Seguridad\\"+mes+"\\"+fecha+"\\"+hora;            
        File directorio = new File(curDir);
        directorio.mkdirs();
        
        String c=curDir+"\\PlataformaAdministrativa"+fecha+"-"+d.getHours()+"h.sql";        
        b.RealizarBackup(c);
        String administrativo=b.getMensaje();
        b=new Backup(sic.Infraestructura.JDBC.DataBaseConfiguration.ObtenerInstancia().getDriverclass(),
                sic.Infraestructura.JDBC.DataBaseConfiguration.ObtenerInstancia().getUser(),
                sic.Infraestructura.JDBC.DataBaseConfiguration.ObtenerInstancia().getPassword(),
                sic.Infraestructura.JDBC.DataBaseConfiguration.ObtenerInstancia().getUrl());                
        c=curDir+"\\PlataformaContable"+fecha+"-"+d.getHours()+"h.sql";        
        b.RealizarBackup(c);
        String contable=b.getMensaje();
        mensaje="Plataforma Administrativa : "+administrativo+" \n";
        mensaje+="Plataforma Contable : "+contable;
    }
  public String getMensaje() {
        return mensaje;
    }
}

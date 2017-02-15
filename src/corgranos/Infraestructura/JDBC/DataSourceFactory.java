/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Infraestructura.JDBC;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author FANNY BURGOS
 */
public class DataSourceFactory {       
    public DataSourceFactory() {        
    }
    public DataSource ObtenerDataSource(String tipo){        
            if(tipo.equals("BasicDataSource")){
                BasicDataSource basicDataSource = new BasicDataSource();
                basicDataSource.setDriverClassName(DataBaseConfiguration.ObtenerInstancia().getDriverclass());                
                basicDataSource.setUsername(DataBaseConfiguration.ObtenerInstancia().getUser());
                basicDataSource.setPassword(DataBaseConfiguration.ObtenerInstancia().getPassword());
                basicDataSource.setUrl(DataBaseConfiguration.ObtenerInstancia().getUrl());                
                basicDataSource.setInitialSize(20);
                basicDataSource.setMaxActive(1000);
                basicDataSource.setMaxIdle(2000);
                basicDataSource.setMinIdle(15);
                basicDataSource.setMaxWait(5000);
                basicDataSource.setValidationQuery("select 1 ");
                /*
                basicDataSource.setTestOnBorrow(false);
                basicDataSource.setTestWhileIdle(true);
                basicDataSource.setTimeBetweenEvictionRunsMillis(600000);
                basicDataSource.setNumTestsPerEvictionRun(5);
                basicDataSource.setMinEvictableIdleTimeMillis(420000);
                basicDataSource.setRemoveAbandoned(true);
                basicDataSource.setLogAbandoned(false);                 
                 */
                return basicDataSource;
            }
            return null;
    }
    
}

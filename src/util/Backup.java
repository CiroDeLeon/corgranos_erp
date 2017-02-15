/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FANNY BURGOS
 */
public class Backup {
    String driverclass;
    String user;
    String password;
    String url;
    Connection con;
    FileWriter op = null;
    PrintWriter out = null;
    private String mensaje="";
    public  Backup(String driverclass, String user, String password, String url) {
        this.driverclass = driverclass;
        this.user = user;
        this.password = password;
        this.url = url;                
    } 
    private void EstablecerConexion(){        
        try {          
            Class.forName(this.driverclass);
            con=DriverManager.getConnection(url,user,password);                    
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void CerrarConexion(Connection con){
        if(con!=null){
            try {
                 if(con.isClosed()==false){                  
                     con.close();
                 }
            } catch (SQLException ex) {
                 Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    private void RecorrerTablas(){        
        this.EstablecerConexion();
        if(con==null){
            System.out.println("Conexion NULA");
            return;
        }
        try {            
            DatabaseMetaData dmd=con.getMetaData();
            ResultSet rs=dmd.getTables(null,null,"%",null);
            while(rs.next()){
                String catalogo=rs.getString(1);
                String tabla=rs.getString(3);                
                System.out.println("#Tabla "+tabla);
                System.out.println("");                
                this.generateCreateTable(catalogo,tabla);
                this.GenerateInserts(catalogo,tabla);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.CerrarConexion(con);
        }
    }
    private void generateCreateTable(String catalogo,String tabla){          
        this.EstablecerConexion();
        if(con==null){
            System.out.println("Conexion NULA");
            return;
        }
        out.println("DROP TABLE IF EXISTS "+tabla+";" );
        out.println("CREATE TABLE "+tabla+" (" );
        try {            
            DatabaseMetaData dmd=con.getMetaData();
            ResultSet rs=dmd.getColumns(catalogo,null,tabla,null);
            while(rs.next()){
                String columna = rs.getString(4);                
                String tipocolumna = this.ObtenerTipocolumna(rs.getString(6).replace("UNSIGNED",""));
                int size=this.ObtenerSize(rs.getInt(7),tipocolumna);
                String nullable=this.ObtenerNullable(rs.getString(18));
                String autoincrement=this.ObtenerAutoincrement(rs.getString(23));                
                String tipocol="";
                if(tipocolumna.equals("DOUBLE")==true || tipocolumna.equals("DATE")==true || tipocolumna.equals("DATETIME")==true){
                    tipocol=tipocolumna+" "+nullable+" "+autoincrement;                               
                }else{
                    tipocol=tipocolumna+" ("+size+") "+nullable+" "+autoincrement;           
                }               
                out.print(columna+" "+tipocol+" ");
                if(!rs.isLast()){
                   out.println(" ,");                   
                }else{
                   out.print("");                    
                }
            }
            ResultSet r;     
            r =dmd.getIndexInfo(catalogo,null,tabla,false, true);
            while(r.next()){  
                if(r.isFirst()){
                    out.print(" ,\n");
                }
                String index=r.getString(6);
                String columna=r.getString(9);                
                String line=("KEY "+index+" ").replace("KEY PRIMARY","PRIMARY KEY")+"("+columna+")";
                if(r.isLast()==false){
                   out.println(line+" ,");
                }else{
                   out.println(line+" "); 
                }                
            }            
            out.println(" )ENGINE=InnoDB AUTO_INCREMENT=835 DEFAULT CHARSET=latin1; ");
            out.println("");
        } catch (SQLException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.CerrarConexion(con);
        }
    }
    private void GenerateInserts(String catalogo,String tabla){
        this.EstablecerConexion();
        if(con==null){
            System.out.println("Conexion NULA");
            return;
        }
        
        try {                        
            Statement st=con.createStatement();
            String sql="select * from "+tabla+" ";
            ResultSet rs=st.executeQuery(sql);
            ResultSetMetaData rsmd=rs.getMetaData();
            int n=rsmd.getColumnCount();
            while(rs.next()){                
                if(rs.isFirst()){
                    out.println("LOCK TABLES "+tabla+" WRITE;");
                    out.println("ALTER TABLE "+tabla+" DISABLE KEYS;");
                    out.println("INSERT INTO "+tabla+" VALUES ");
                }
                out.print("(");
                for(int i=1;i<=n;i++){
                    String value="";
                    if(rs.getString(i)==null){
                       value="NULL"; 
                    }else{
                       String coln=rsmd.getColumnName(i); 
                       String colt=rsmd.getColumnTypeName(i);  
                       if(colt.equals("VARCHAR") || colt.equals("CHAR") || colt.equals("DATE") || colt.equals("DATETIME"))
                          value="'"+rs.getString(i)+"'";  
                       else
                          value=rs.getString(i);                         
                    }
                    if(i!=n)
                       out.print(value+",");
                    else
                       out.print(value);    
                }   
                if(rs.isLast()==false)
                   out.print("),");
                else
                   out.print(");");    
            }
            out.println("");
            out.println("ALTER TABLE "+tabla+" ENABLE KEYS;");
            out.println("UNLOCK TABLES;");
        } catch (SQLException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private String ObtenerNullable(String nullable){
        if(nullable.equals("YES")){
            nullable="NULL";
        }else{
            nullable="NOT NULL";
        }
        return nullable;
    }
    private String ObtenerAutoincrement(String autoincrement){
        if(autoincrement.equals("YES")){
            autoincrement="AUTO_INCREMENT";
        }else{
            autoincrement="";
        }
        return autoincrement;
    }
    private String ObtenerTipocolumna(String tipocolumna){        
        if(tipocolumna.equals("BIT")){
           tipocolumna="TINYINT";
        }
        return tipocolumna;
    }
    private int ObtenerSize(int size,String tipocolumna){
        if(tipocolumna.equals("BIT")){
            size=4;
        }
        return size;    
    }
    public void RealizarBackup(String archivo){
        try {
            System.out.println(archivo);
            op = new FileWriter(archivo);
            out = new PrintWriter(op);
            this.RecorrerTablas();            
            this.mensaje="Generado Con Exito el Backup";
        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
            this.mensaje="No se Pudo Generar el Backup";
        }finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != op)
              op.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    public String getMensaje() {
        return mensaje;
    }
}

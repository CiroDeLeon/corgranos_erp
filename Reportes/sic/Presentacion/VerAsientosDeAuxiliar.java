/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VerAsientosDeAuxiliar.java
 *
 * Created on 17/05/2012, 01:34:38 PM
 */
package sic.Presentacion;


import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.text.NumberFormat;
import sic.Dominio.Core.PUC.Cta_PUC;
import sic.Dominio.Core.PUC.PucService;
import sic.Dominio.Servicios.Contabilidad.AsientoContableDTO;
import sic.Dominio.Servicios.Contabilidad.ContabilidadService;
import sic.Servicios.Reportes.ReporteService;
import sic.Presentacion.ModelosDeTabla.AsientoContableDTOModelCliente;
import sic.Presentacion.ModelosDeTabla.AsientoContableDTOModelIva;
import sic.Presentacion.ModelosDeTabla.AsientoContableDTOModelMercancia;
import sic.Presentacion.ModelosDeTabla.AsientoContableDTOModelNormal;
import sic.Presentacion.Suscripciones.ISuscripcionBuscarEnAuxiliar;

/**
 *
 * @author FANNY BURGOS
 */
public class VerAsientosDeAuxiliar extends javax.swing.JFrame implements ISuscripcionBuscarEnAuxiliar{
    BuscarEnAuxiliar ba;
    Cta_PUC cta;
    AsientoContableDTOModelNormal mnormal;
    AsientoContableDTOModelCliente mcliente;
    AsientoContableDTOModelMercancia mmercancia;
    AsientoContableDTOModelIva miva;
    ContabilidadService service;
    Vector <AsientoContableDTO> lista;
    int fila;
    /** Creates new form VerAsientosDeAuxiliar */
    public VerAsientosDeAuxiliar() {
        initComponents();
        service=new ContabilidadService();
        this.initModelo();
        this.jLabel8.setVisible(false);
        this.jTextField5.setVisible(false);
        this.jLabel11.setVisible(false);
        this.jTextField8.setVisible(false);
        this.jLabel12.setVisible(false);
        this.jTextField9.setVisible(false);
        this.BuscarEnAuxiliar();
    }
    public void setFechaInicial(Calendar a){
        this.dateChooserCombo1.setSelectedDate(a);
    }
    public void setFechaFinal(Calendar a){
        this.dateChooserCombo2.setSelectedDate(a);
    }
    public void BuscarEnAuxiliar(){
        ba=new BuscarEnAuxiliar();        
        ba.setSuscripcion(this);  
        ba.setCtat(null);
        this.dispose();
    }
    public void initModelo(){
        mnormal=new AsientoContableDTOModelNormal();        
        this.jTable1.setModel(mnormal);
    }
    public void CargarNormal(){
        mnormal=new AsientoContableDTOModelNormal();        
        Iterator <AsientoContableDTO> it=service.getDao().ObtenerAsientos(cta.getId().toString(),this.dateChooserCombo1.getSelectedDate().getTime(),this.dateChooserCombo2.getSelectedDate().getTime()).iterator();
        long dm=86400000;// dia en milisegundos
        Date fecha=this.dateChooserCombo1.getSelectedDate().getTime();
        double saldo=service.getDao().ObtenerSaldo(cta.getId().toString(),new Date(fecha.getTime()-dm));
        this.jTextField4.setText(""+NumberFormat.getInstance().format(saldo));
        AsientoContableDTO.saldoanterior=saldo;
        double existencia=service.getDao().ObtenerExistencia(cta.getId().toString(),new Date(fecha.getTime()-dm));
        lista=new Vector<AsientoContableDTO>();
        double sumad=0;
        double sumac=0;
        double sumae=0;
        double sumas=0;
        while(it.hasNext()){
            AsientoContableDTO a=it.next();
            Object [] fila=new Object[15];
            fila[0]=a.getFechacontable();
            fila[1]=a.getNitusuario();
            fila[2]=a.getUsuario();
            fila[3]=a.getSoporte();
            fila[4]=a.getDetalle();
            fila[5]=a.getDebito();
            fila[6]=a.getCredito();
            if(service.getDao().isDebito(cta.getId().toString())){
                saldo=saldo+a.getDebito()-a.getCredito();
                existencia=existencia+a.getEntradas()-a.getSalidas();
            }else{
                saldo=saldo+a.getCredito()-a.getDebito();
                existencia=existencia+a.getSalidas()-a.getEntradas();
            }
            fila[7]=saldo;
            fila[8]=a.getEntradas();
            fila[9]=a.getSalidas();
            fila[10]=existencia;
            fila[11]=a.getNoFactura();
            fila[12]=a.getNoFacturaCompra();
            fila[13]=a.getBaseiva();
            fila[14]=a.getBasertf();
            a.setSaldo(saldo);
            a.setExistencia(existencia);
            lista.add(a);
            mnormal.addRow(fila);
            sumad+=a.getDebito();
            sumac+=a.getCredito();
            sumae+=a.getEntradas();
            sumas+=a.getSalidas();
        }
        this.jTextField6.setText(NumberFormat.getInstance().format(sumad));
        this.jTextField7.setText(NumberFormat.getInstance().format(sumac));
        this.jTable1.setModel(mnormal);            
        this.jTable1.repaint();
    }
    public void CargarMercancia(){
        mmercancia=new AsientoContableDTOModelMercancia();        
        Iterator <AsientoContableDTO> it=service.getDao().ObtenerAsientos(cta.getId().toString(),this.dateChooserCombo1.getSelectedDate().getTime(),this.dateChooserCombo2.getSelectedDate().getTime()).iterator();
        long dm=86400000;// dia en milisegundos
        Date fecha=this.dateChooserCombo1.getSelectedDate().getTime();
        double saldo=service.getDao().ObtenerSaldo(cta.getId().toString(),new Date(fecha.getTime()-dm));
        AsientoContableDTO.saldoanterior=saldo;
        this.jTextField4.setText(""+NumberFormat.getInstance().format(saldo));
        double existencia=service.getDao().ObtenerExistencia(cta.getId().toString(),new Date(fecha.getTime()-dm));
        AsientoContableDTO.existenciaanterior=existencia;
        this.jLabel8.setVisible(true);
        this.jTextField5.setVisible(true);
        this.jTextField5.setText(""+NumberFormat.getInstance().format(existencia));
        lista=new Vector<AsientoContableDTO>();
        double sumad=0;
        double sumac=0;
        double sumae=0;
        double sumas=0;
        while(it.hasNext()){
            AsientoContableDTO a=it.next();
            Object [] fila=new Object[15];
            fila[0]=a.getFechacontable();
            fila[1]=a.getNitusuario();
            fila[2]=a.getUsuario();
            fila[3]=a.getSoporte();
            fila[4]=a.getDetalle();
            fila[5]=a.getEntradas();
            fila[6]=a.getDebito();
            if(service.getDao().isDebito(cta.getId().toString())){
                saldo=saldo+a.getDebito()-a.getCredito();
                existencia=existencia+a.getEntradas()-a.getSalidas();
            }else{
                saldo=saldo+a.getCredito()-a.getDebito();
                existencia=existencia+a.getSalidas()-a.getEntradas();
            }
            fila[7]=a.getSalidas();
            fila[8]=a.getCredito();
            fila[9]=existencia;
            fila[10]=saldo;           
            if(existencia!=0)
            fila[11]=saldo/existencia;
            a.setSaldo(saldo);
            a.setExistencia(existencia);
            lista.add(a);
            mmercancia.addRow(fila);
            sumad+=a.getDebito();
            sumac+=a.getCredito();
            sumae+=a.getEntradas();
            sumas+=a.getSalidas();
        }
        this.jTextField6.setText(NumberFormat.getInstance().format(sumad));
        this.jTextField7.setText(NumberFormat.getInstance().format(sumac));
        this.jTextField8.setText(NumberFormat.getInstance().format(sumae));
        this.jTextField9.setText(NumberFormat.getInstance().format(sumas));
        this.jLabel11.setVisible(true);
        this.jTextField8.setVisible(true);
        this.jLabel12.setVisible(true);
        this.jTextField9.setVisible(true);
        this.jTable1.setModel(mmercancia);            
        this.jTable1.repaint();
    }
    public void CargarCliente(){
        mcliente=new AsientoContableDTOModelCliente();        
        Iterator <AsientoContableDTO> it=service.getDao().ObtenerAsientos(cta.getId().toString(),this.dateChooserCombo1.getSelectedDate().getTime(),this.dateChooserCombo2.getSelectedDate().getTime()).iterator();
        long dm=86400000;// dia en milisegundos
        Date fecha=this.dateChooserCombo1.getSelectedDate().getTime();
        double saldo=service.getDao().ObtenerSaldo(cta.getId().toString(),new Date(fecha.getTime()-dm));
        AsientoContableDTO.saldoanterior=saldo;
        this.jTextField4.setText(""+NumberFormat.getInstance().format(saldo));
        double existencia=service.getDao().ObtenerExistencia(cta.getId().toString(),new Date(fecha.getTime()-dm));
        lista=new Vector<AsientoContableDTO>();
        double sumad=0;
        double sumac=0;
        double sumae=0;
        double sumas=0;
        while(it.hasNext()){
            AsientoContableDTO a=it.next();
            Object [] fila=new Object[15];
            fila[0]=a.getFechacontable();
            fila[1]=a.getNitusuario();
            fila[2]=a.getUsuario();
            fila[3]=a.getSoporte();
            fila[4]=a.getDetalle();
            fila[5]=a.getNoFactura();
            fila[6]=a.getDebito();
            fila[7]=a.getCredito();
            if(service.getDao().isDebito(cta.getId().toString())){
                saldo=saldo+a.getDebito()-a.getCredito();
                existencia=existencia+a.getEntradas()-a.getSalidas();
            }else{
                saldo=saldo+a.getCredito()-a.getDebito();
                existencia=existencia+a.getSalidas()-a.getEntradas();
            }
            fila[8]=saldo;            
            a.setSaldo(saldo);
            a.setExistencia(existencia);
            lista.add(a);
            mcliente.addRow(fila);
            sumad+=a.getDebito();
            sumac+=a.getCredito();
            sumae+=a.getEntradas();
            sumas+=a.getSalidas();
        }
        this.jTextField6.setText(NumberFormat.getInstance().format(sumad));
        this.jTextField7.setText(NumberFormat.getInstance().format(sumac));
        this.jTable1.setModel(mcliente);            
        this.jTable1.repaint();
    }
    public void CargarProveedor(){
        mcliente=new AsientoContableDTOModelCliente();        
        Iterator <AsientoContableDTO> it=service.getDao().ObtenerAsientos(cta.getId().toString(),this.dateChooserCombo1.getSelectedDate().getTime(),this.dateChooserCombo2.getSelectedDate().getTime()).iterator();
        long dm=86400000;// dia en milisegundos
        Date fecha=this.dateChooserCombo1.getSelectedDate().getTime();
        double saldo=service.getDao().ObtenerSaldo(cta.getId().toString(),new Date(fecha.getTime()-dm));
        AsientoContableDTO.saldoanterior=saldo;
        this.jTextField4.setText(""+NumberFormat.getInstance().format(saldo));
        double existencia=service.getDao().ObtenerExistencia(cta.getId().toString(),new Date(fecha.getTime()-dm));
        lista=new Vector<AsientoContableDTO>();
        double sumad=0;
        double sumac=0;
        double sumae=0;
        double sumas=0;        
        while(it.hasNext()){
            AsientoContableDTO a=it.next();
            Object [] fila=new Object[15];
            fila[0]=a.getFechacontable();
            fila[1]=a.getNitusuario();
            fila[2]=a.getUsuario();
            fila[3]=a.getSoporte();
            fila[4]=a.getDetalle();
            fila[5]=a.getNoFacturaCompra();
            fila[6]=a.getDebito();
            fila[7]=a.getCredito();
            if(service.getDao().isDebito(cta.getId().toString())){
                saldo=saldo+a.getDebito()-a.getCredito();
                existencia=existencia+a.getEntradas()-a.getSalidas();
            }else{
                saldo=saldo+a.getCredito()-a.getDebito();
                existencia=existencia+a.getSalidas()-a.getEntradas();
            }
            fila[8]=saldo;            
            a.setSaldo(saldo);
            a.setExistencia(existencia);
            lista.add(a);
            mcliente.addRow(fila);
            sumad+=a.getDebito();
            sumac+=a.getCredito();
            sumae+=a.getEntradas();
            sumas+=a.getSalidas();            
        }
        this.jTextField6.setText(NumberFormat.getInstance().format(sumad));
        this.jTextField7.setText(NumberFormat.getInstance().format(sumac));
        this.jTable1.setModel(mcliente);            
        this.jTable1.repaint();
    }
    public void CargarIva(){
        miva=new AsientoContableDTOModelIva();        
        Iterator <AsientoContableDTO> it=service.getDao().ObtenerAsientos(cta.getId().toString(),this.dateChooserCombo1.getSelectedDate().getTime(),this.dateChooserCombo2.getSelectedDate().getTime()).iterator();
        long dm=86400000;// dia en milisegundos
        Date fecha=this.dateChooserCombo1.getSelectedDate().getTime();
        double saldo=service.getDao().ObtenerSaldo(cta.getId().toString(),new Date(fecha.getTime()-dm));
        AsientoContableDTO.saldoanterior=saldo;
        this.jTextField4.setText(""+NumberFormat.getInstance().format(saldo));
        double existencia=service.getDao().ObtenerExistencia(cta.getId().toString(),new Date(fecha.getTime()-dm));
        lista=new Vector<AsientoContableDTO>();
        double sumad=0;
        double sumac=0;
        double sumae=0;
        double sumas=0;
        double sumab=0;
        double sumar=0;
        while(it.hasNext()){
            AsientoContableDTO a=it.next();
            Object [] fila=new Object[15];
            fila[0]=a.getFechacontable();
            fila[1]=a.getNitusuario();
            fila[2]=a.getUsuario();
            fila[3]=a.getSoporte();
            fila[4]=a.getDetalle();
            fila[5]=a.getNoFactura()+a.getNoFacturaCompra();
            fila[6]=a.getDebito();
            fila[7]=a.getCredito();
            if(service.getDao().isDebito(cta.getId().toString())){
                saldo=saldo+a.getDebito()-a.getCredito();
                existencia=existencia+a.getEntradas()-a.getSalidas();
            }else{
                saldo=saldo+a.getCredito()-a.getDebito();
                existencia=existencia+a.getSalidas()-a.getEntradas();
            }
            fila[8]=a.getBaseiva();
            fila[9]=saldo;           
            a.setSaldo(saldo);
            a.setExistencia(existencia);
            lista.add(a);
            miva.addRow(fila);
            sumad+=a.getDebito();
            sumac+=a.getCredito();
            sumae+=a.getEntradas();
            sumas+=a.getSalidas();
            sumab+=a.getBaseiva();
            if(a.getBaseiva()!=0){
               sumar+=a.getDebito()+a.getCredito();
            }            
        }
        this.jTextField6.setText(NumberFormat.getInstance().format(sumad));
        this.jTextField7.setText(NumberFormat.getInstance().format(sumac));
        this.jLabel11.setText("Suma Base : ");
        this.jTextField8.setText(NumberFormat.getInstance().format(sumab));
        this.jLabel12.setText("Suma Iva :");
        this.jTextField9.setText(NumberFormat.getInstance().format(sumar));
        this.jLabel11.setVisible(true);
        this.jTextField8.setVisible(true);
        this.jTextField9.setVisible(true);
        this.jLabel12.setVisible(true);
        this.jTable1.setModel(miva);            
        this.jTable1.repaint();
        sic.Dominio.Servicios.Contabilidad.AsientoContableDTO.sumartf=sumar;
    }
    public void CargarRtf(){
        miva=new AsientoContableDTOModelIva();        
        Iterator <AsientoContableDTO> it=service.getDao().ObtenerAsientos(cta.getId().toString(),this.dateChooserCombo1.getSelectedDate().getTime(),this.dateChooserCombo2.getSelectedDate().getTime()).iterator();
        long dm=86400000;// dia en milisegundos
        Date fecha=this.dateChooserCombo1.getSelectedDate().getTime();
        double saldo=service.getDao().ObtenerSaldo(cta.getId().toString(),new Date(fecha.getTime()-dm));
        AsientoContableDTO.saldoanterior=saldo;
        this.jTextField4.setText(""+NumberFormat.getInstance().format(saldo));
        double existencia=service.getDao().ObtenerExistencia(cta.getId().toString(),new Date(fecha.getTime()-dm));
        lista=new Vector<AsientoContableDTO>();
        double sumad=0;
        double sumac=0;
        double sumae=0;
        double sumas=0;
        double sumab=0;
        double sumar=0;
        while(it.hasNext()){
            AsientoContableDTO a=it.next();
            Object [] fila=new Object[15];
            fila[0]=a.getFechacontable();
            fila[1]=a.getNitusuario();
            fila[2]=a.getUsuario();
            fila[3]=a.getSoporte();
            fila[4]=a.getDetalle();
            fila[5]=a.getNoFactura()+a.getNoFacturaCompra();
            fila[6]=a.getDebito();
            fila[7]=a.getCredito();
            if(service.getDao().isDebito(cta.getId().toString())){
                saldo=saldo+a.getDebito()-a.getCredito();
                existencia=existencia+a.getEntradas()-a.getSalidas();
            }else{
                saldo=saldo+a.getCredito()-a.getDebito();
                existencia=existencia+a.getSalidas()-a.getEntradas();
            }
            fila[8]=a.getBasertf();
            fila[9]=saldo;           
            a.setSaldo(saldo);
            a.setExistencia(existencia);
            lista.add(a);
            miva.addRow(fila);
            sumad+=a.getDebito();
            sumac+=a.getCredito();
            sumae+=a.getEntradas();
            sumas+=a.getSalidas();
            sumab+=a.getBasertf();
            if(a.getBasertf()!=0){
               sumar+=a.getDebito()+a.getCredito();
            }
        }
        this.jTextField6.setText(NumberFormat.getInstance().format(sumad));
        this.jTextField7.setText(NumberFormat.getInstance().format(sumac));
        this.jLabel11.setText("Suma Base : ");
        this.jTextField8.setText(NumberFormat.getInstance().format(sumab));        
        this.jLabel12.setText("Suma Rtf :");
        this.jTextField9.setText(NumberFormat.getInstance().format(sumar));
        this.jLabel11.setVisible(true);
        this.jTextField8.setVisible(true);
        this.jLabel12.setVisible(true);
        this.jTextField9.setVisible(true);
        this.jTable1.setModel(miva);            
        this.jTable1.repaint();
        sic.Dominio.Servicios.Contabilidad.AsientoContableDTO.sumartf=sumar;        
    }
    void Cargar(){
        AsientoContableDTO.fechainicial=(new java.sql.Date(this.dateChooserCombo1.getSelectedDate().getTimeInMillis())).toString();
        AsientoContableDTO.fechafinal=(new java.sql.Date(this.dateChooserCombo2.getSelectedDate().getTimeInMillis())).toString();
        this.jLabel8.setVisible(false);
        this.jTextField5.setVisible(false);
        this.jLabel11.setVisible(false);
        this.jTextField8.setVisible(false);
        this.jLabel12.setVisible(false);
        this.jTextField9.setVisible(false);
        this.jLabel11.setText("Suma Entradas :");
        this.jLabel12.setText("Suma Salidas :");
        if(this.cta.getCategoria().equals("Normal")){
            this.CargarNormal();
        }
        if(this.cta.getCategoria().equals("Mercancia")){
            this.CargarMercancia();
        }
        if(this.cta.getCategoria().equals("Cliente")){
            this.CargarCliente();
        }
        if(this.cta.getCategoria().equals("Proveedor")){
            this.CargarProveedor();
        }
        if(this.cta.getCategoria().equals("Iva")){
            this.CargarIva();
        }
         if(this.cta.getCategoria().equals("Rtf")){
            this.CargarRtf();
        }
    }
    void VerSoporte(){        
        if(fila!=-1){
            FolderDeDocumentos fd=new FolderDeDocumentos();
            fd.CargarDocumento(this.lista.get(fila).getTipodocumento(),this.lista.get(fila).getNumeracion());
            fd.show();
        }
    }
    void VerReporte(){
        if(this.cta.getCategoria().equals("Normal")){
            ReporteService.VerReporte("VerAsientosDeAuxiliarNormal.jasper", lista,"Cuenta T Detallada");
        }
        if(this.cta.getCategoria().equals("Cliente")){
            ReporteService.VerReporte("VerAsientosDeAuxiliarCliente.jasper", lista,"Cuenta T Detallada");
        }
        if(this.cta.getCategoria().equals("Proveedor")){
            ReporteService.VerReporte("VerAsientosDeAuxiliarCliente.jasper", lista,"Cuenta T Detallada");
        }
        if(this.cta.getCategoria().equals("Rtf")){
            ReporteService.VerReporte("VerAsientosDeAuxiliarRtf.jasper", lista,"Cuenta T Detallada");
        }
        if(this.cta.getCategoria().equals("Iva")){
            ReporteService.VerReporte("VerAsientosDeAuxiliarIva.jasper", lista,"Cuenta T Detallada");
        }
        if(this.cta.getCategoria().equals("Mercancia")){
            ReporteService.VerReporte("VerAsientosDeAuxiliarMercancia.jasper", lista,"Cuenta T Detallada(Kardex Contable)");
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel6 = new javax.swing.JLabel();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cuenta T Detallada");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configuracion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel2.setText("Denominacion :");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel3.setText("Aux :");

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel4.setText("Titulo Cuenta T :");

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel5.setText("Fecha Inicial :");

        dateChooserCombo1.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
        dateChooserCombo1.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel6.setText("Fecha Final :");

        dateChooserCombo2.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
        dateChooserCombo2.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton2.setText("CARGAR");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("VER REPORTE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5)
                        .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2))
                    .addComponent(jButton3))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel7.setText("Saldo Anterior :");

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 24));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel8.setText("Existencia Anterior :");

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 24));
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel9.setText("Suma Debito  :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel10.setText("Suma Credito :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel11.setText("Suma Entradas :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel12.setText("Suma Salidas :");

        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 14));
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setEnabled(false);

        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 14));
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setEnabled(false);

        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 14));
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setEnabled(false);

        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 14));
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField9.setEnabled(false);

        jMenu1.setText("Opciones");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItem1.setText("Ver Reporte");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem2.setText("Ver Soporte");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(27, 27, 27)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(27, 27, 27)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(30, 30, 30))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(19, 19, 19)))
                            .addComponent(jLabel12))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField9)
                            .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.BuscarEnAuxiliar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.Cargar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:        
        fila=this.jTable1.getSelectedRow();        
        this.VerSoporte();        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.VerReporte();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.VerReporte();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VerAsientosDeAuxiliar().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

    @Override
    public void EventoDeSeleccionAuxiliar() {
        ba.dispose();
        this.initModelo();        
        this.show();
        this.setCtaAuxiliar(ba.ObtenerAuxiliarSeleccionado());
       
    }

    @Override
    public void setCtaAuxiliar(Cta_PUC cta) {
        this.cta=cta;
        String aux=cta.getId().toString().substring(0,8);
        this.jTextField2.setText(aux);
        this.jTextField3.setText(cta.getDenominacion());
        PucService s=new PucService();
        this.jTextField1.setText(s.ObtenerCtaPuc(aux).getDenominacion());  
        this.jButton2.setEnabled(true);
    }
}

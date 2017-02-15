/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CrearFactura.java
 *
 * Created on 23/07/2012, 05:22:07 AM
 */
package corgranos.Presentacion;

import corgranos.Dominio.Core.Articulo.Articulo;
import corgranos.Dominio.Core.Factura.Factura;
import corgranos.Dominio.Core.Factura.FacturaRep;
import corgranos.Dominio.Core.Factura.FacturaService;
import corgranos.Dominio.Core.Factura.FacturacionDeArticulo;
import corgranos.Dominio.Core.Factura.Prefijo;
import corgranos.Dominio.Core.Maquila.Maquila;
import corgranos.Dominio.Core.Remision.Remision;
import corgranos.Presentacion.ModelosDeTabla.FacturacionDeArticuloModel;
import corgranos.Presentacion.Suscripciones.ISuscripcionBuscarArticulo;
import corgranos.Presentacion.Suscripciones.ISuscripcionBuscarMaquila;
import corgranos.Presentacion.Suscripciones.ISuscripcionBuscarRemision;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import sic.Dominio.Core.Usuario.Usuario;
import sic.Aplicacion.Servicios.Reportes.ReporteService;
import sic.Presentacion.Modulos.Usuario.BuscarUsuario;
import sic.Presentacion.Suscripciones.ISuscripcionBuscarUsuario;

/**
 *
 * @author FANNY BURGOS
 */
public class FolderDeFacturas extends javax.swing.JFrame implements ISuscripcionBuscarUsuario,ISuscripcionBuscarArticulo,ISuscripcionBuscarMaquila,ISuscripcionBuscarRemision,KeyListener{
    BuscarUsuario bu;
    Usuario usuario;
    BuscarArticulo ba;
    Articulo articulo;
    BuscarMaquila bm;
    Maquila maquila;
    BuscarRemisionesNoFacturadas br;
    Remision remision;
    FacturacionDeArticuloModel modelo;
    Vector <FacturacionDeArticulo> lista;
    FacturacionDeArticulo fa;
    int filaseleccionada=-1;
    double SumatoriaSubtotal=0;
    double SumatoriaIva=0;
    double SumatoriaTotal=0;
    FacturaService service;
    Factura fc;
    private String actor="developer";
    Vector<Prefijo> prefijos;
    Prefijo prefijo;
    /** Creates new form CrearFactura */
    public FolderDeFacturas() {
        initComponents();
        this.jButton9.addKeyListener(this);
        this.jButton10.addKeyListener(this);
        lista=new Vector<FacturacionDeArticulo>();        
        modelo=new FacturacionDeArticuloModel();
        this.jTable1.setModel(modelo);
        this.setAnchoColumnas();
        this.jTable1.repaint();        
        this.jFormattedTextField4.setValue(new Double(0));
        this.jFormattedTextField3.setValue(new Double(0));
        service=new FacturaService();        
        this.LLenarPrefijos();
        int n=service.getDao().ObtenerNumeracion(prefijo.getDocumento_factura());
        this.Cargar(n,prefijo.getIdPrefijo());
    }
    public void LLenarPrefijos(){
        prefijos=service.getFacturadao().ObtenerPrefijos();
        Iterator<Prefijo> it=prefijos.iterator();
        while(it.hasNext()){
            Prefijo p=it.next();
            this.jComboBox2.addItem(p.getDescripcion());
        }
        prefijo=prefijos.get(0);
    }
    private void HallarTotalizados(){
        Iterator <FacturacionDeArticulo> it=lista.iterator();
        this.SumatoriaSubtotal=0;
        this.SumatoriaIva=0;
        this.SumatoriaTotal=0;
        while(it.hasNext()){
            FacturacionDeArticulo fa=it.next();
            this.SumatoriaSubtotal+=(fa.getCantidad()*fa.getPreciounitario());
            this.SumatoriaIva+=fa.getValoriva();
            this.SumatoriaTotal+=((fa.getCantidad()*fa.getPreciounitario())+fa.getValoriva());
        }
        this.jFormattedTextField7.setValue(new Double(Math.round(this.SumatoriaSubtotal)));
        this.jFormattedTextField6.setValue(new Double(Math.round(this.SumatoriaIva)));
        this.jFormattedTextField5.setValue(new Double(Math.round(this.SumatoriaTotal)));
    }
    private void setAnchoColumnas(){       
        JViewport scroll =  (JViewport) this.jTable1.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna=0;
        TableColumnModel modeloColumna = this.jTable1.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < this.jTable1.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch(i){
                case 0: anchoColumna = (8*ancho)/100;
                        break;
                case 1: anchoColumna = (35*ancho)/100;
                        break;
                case 2: anchoColumna = (7*ancho)/100;
                        break;
                case 3: anchoColumna = (7*ancho)/100;
                        break; 
                case 4: anchoColumna = (7*ancho)/100;
                        break;    
                case 5: anchoColumna = (9*ancho)/100;
                        break;    
                case 6: anchoColumna = (9*ancho)/100;
                        break;        
                case 7: anchoColumna = (9*ancho)/100;
                        break;           
                case 8: anchoColumna = (9*ancho)/100;
                        break;              
            }                     
            columnaTabla.setPreferredWidth(anchoColumna);           
        }
    }  
    void BuscarUsuario(){
        bu=new BuscarUsuario();
        bu.setSuscripcion(this);
        bu.show();
        this.dispose();
    }
    private void BuscarArticulo(){
        ba=new BuscarArticulo();
        ba.setSuscripcion(this);
        ba.show();
        this.dispose();
    }
    private void BuscarMaquila(){
        bm=new BuscarMaquila();
        bm.setSuscripcion(this);
        bm.VerTodaslasMaquilas=true;
        bm.Cargar();
        bm.show();
        this.dispose();
    }
    void BuscarRemisiones(){
        if(this.usuario!=null){ 
           br=new BuscarRemisionesNoFacturadas();
           br.setSuscripcion(this);
           br.setUsuario(usuario);
           br.Cargar();
           br.show();
           this.dispose();
        }else{
           JOptionPane.showMessageDialog(this,"Debes escoger un Usuario ");
        }
    }
    void BuildFacturacionDeArticulo(){        
        fa.setArticulo(articulo);
        fa.setCantidad(((Double)this.jFormattedTextField4.getValue()).doubleValue());
        fa.setPreciounitario(((Double)this.jFormattedTextField3.getValue()).doubleValue());
        fa.setMaquila(maquila);
        fa.setValoriva(Math.round(fa.getCantidad()*fa.getPreciounitario()*articulo.getPorcentageiva()));
        if(remision!=null){
           fa.setRemision(remision);
           fa.setRemisionada(true);           
        }else{
           fa.setRemision(null);
           fa.setRemisionada(false); 
        }
    }
    void IngresarFacturacionDeArticulo(){
        fa=new FacturacionDeArticulo();
        fa.setId("0");
        this.BuildFacturacionDeArticulo();
        lista.add(fa);        
        modelo=new FacturacionDeArticuloModel();
        this.modelo.setLista(lista);   
        this.jTable1.setModel(modelo);
        this.setAnchoColumnas();
        this.HallarTotalizados();
        this.jTable1.repaint();        
        this.setMaquila(null);
        this.setArticulo(null);
        this.setRemision(null);
        this.jFormattedTextField4.setValue(new Double(0));
        this.jFormattedTextField3.setValue(new Double(0));
        this.jFormattedTextField4.setEditable(true);
        this.jButton2.requestFocus();
    }
    void ModificarFacturacionDeArticulo(){
        this.BuildFacturacionDeArticulo();
        lista.set(this.filaseleccionada,fa);
        modelo=new FacturacionDeArticuloModel();
        this.modelo.setLista(lista);   
        this.jTable1.setModel(modelo);
        this.setAnchoColumnas();
        this.jTable1.repaint();
        this.HallarTotalizados();
        this.setMaquila(null);
        this.setArticulo(null);
        this.setRemision(null);
        this.jFormattedTextField4.setValue(new Double(0));
        this.jFormattedTextField3.setValue(new Double(0));
        this.jFormattedTextField4.setEditable(true);
        this.jButton2.requestFocus();
        this.jButton3.setEnabled(true);
        this.jButton4.setEnabled(false);
    }
    void EventoTabla(){
        if(this.filaseleccionada!=-1){
           fa=lista.get(this.filaseleccionada);
           this.setArticulo(fa.getArticulo());
           this.setMaquila(fa.getMaquila());
           this.setRemision(fa.getRemision());
           this.jFormattedTextField3.setValue(new Double(fa.getPreciounitario()));
           this.jFormattedTextField4.setValue(new Double(fa.getCantidad()));
           this.jButton3.setEnabled(false);
           this.jButton4.setEnabled(true);
           if(fa.getRemision()==null){
             this.jFormattedTextField4.setEditable(true);                
           }else{
             this.jFormattedTextField4.setEditable(false);                 
           }
        }else{
           JOptionPane.showMessageDialog(this,"Debes Seleccionar una Fila");             
        }
    }
    void Modificar(){
        this.HallarTotalizados();
        int n=Integer.parseInt(this.jTextField1.getText());
        Factura f=service.ObtenerFactura(n,prefijo.getIdPrefijo());
        f.setUsuario(usuario);
        f.setObservaciones(this.jTextField4.getText());
        f.setLista(lista);
        System.out.println("sit="+SumatoriaSubtotal+" "+SumatoriaIva+" "+SumatoriaTotal);
        f.setTotal(SumatoriaTotal);
        f.setTotaliva(SumatoriaIva);
        f.setSubtotal(SumatoriaSubtotal);
        f.setNofactura(n);
        f.setNumeracion(n);
        f.setModificador(actor);
        f.setPrefijo(prefijo);
        f.setTipofactura(this.jComboBox1.getSelectedItem().toString());
        long dia=24*60*60*1000;
        f.setFechaplazo(this.dateChooserCombo2.getSelectedDate().getTime());
        f.setFechaContable(this.dateChooserCombo1.getSelectedDate().getTime());                      
        fc=f;
        service=new FacturaService();        
        service.ModificarFactura(f);
        JOptionPane.showMessageDialog(this,service.getMensaje());
    }
    void Limpiar(){
        this.setUsuario(null);
        this.setMaquila(null);
        this.setRemision(null);
        this.setArticulo(null);
        this.jFormattedTextField3.setValue(new Double(0));
        this.jFormattedTextField4.setValue(new Double(0));        
        this.jFormattedTextField4.setEditable(true);
        this.jTextField4.setText("");        
        this.modelo=new FacturacionDeArticuloModel();
        this.lista=new Vector<FacturacionDeArticulo>();
        this.jTable1.setModel(modelo);
        this.setAnchoColumnas();
        this.jTable1.repaint();        
        this.HallarTotalizados();        
    }
    public void Buscar(){
        String num=JOptionPane.showInputDialog("Digite el numero de la Factura que desea ver");
        try{
           int n=Integer.parseInt(num);
           this.Cargar(n,prefijo.getIdPrefijo());
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this,"Debes Digitar Un Numero");
            this.Buscar();
        }
    }
    void Cargar(int NumeracionFactura,long idprefijo){
        service=new FacturaService();
        System.out.println(NumeracionFactura+" "+prefijo.getIdPrefijo());
        Factura f=this.service.ObtenerFactura(NumeracionFactura,prefijo.getIdPrefijo());        
        if(f!=null){
           f.setNofactura(NumeracionFactura);
           f.setNumeracion(NumeracionFactura);            
           fc=f;           
           Calendar cal=Calendar.getInstance();
           cal.setTime(f.getFechaContable());
           this.dateChooserCombo1.setSelectedDate(cal);
           Calendar cl=Calendar.getInstance();
           cl.setTime(f.getFechaplazo());
           this.dateChooserCombo2.setSelectedDate(cl);
           this.setUsuario(f.getUsuario());
           this.lista=f.getLista();
           this.jComboBox1.setSelectedItem(f.getTipofactura());
           this.jTextField4.setText(f.getObservaciones());
           this.setMaquila(null);
           this.setRemision(null);
           this.setArticulo(null);
           this.jFormattedTextField3.setValue(new Double(0));
           this.jFormattedTextField4.setValue(new Double(0));        
           this.jFormattedTextField4.setEditable(true);
           this.jTextField1.setText(""+NumeracionFactura);
           this.modelo=new FacturacionDeArticuloModel();
           modelo.setLista(lista);
           this.jTable1.setModel(modelo);
           this.setAnchoColumnas();
           this.HallarTotalizados();
           this.jLabel14.setText(f.getEstado());
        }else{
            JOptionPane.showMessageDialog(this,"la factura "+NumeracionFactura+" No Existe");
        }
    }
    void IrAdelante(){
        int n=Integer.parseInt(this.jTextField1.getText());
        n++;
        this.Cargar(n,prefijo.getIdPrefijo());
        this.jButton10.requestFocus();
    }
    void IrAtras(){
        int n=Integer.parseInt(this.jTextField1.getText());
        n--;
        this.Cargar(n,prefijo.getIdPrefijo());
        this.jButton9.requestFocus();
    }
    void Anular(){
        service=new FacturaService();        
        int n=Integer.parseInt(this.jTextField1.getText());
        service.AnularFactura(n,prefijo.getIdPrefijo(),"developer","");
        this.Cargar(n,prefijo.getIdPrefijo());
    }
    void VerReporte(){
        int n=Integer.parseInt(this.jTextField1.getText());                
        ReporteService.VerReporte("FolderDeFacturas.jasper",service.ObtenerReporte(fc),"Factura de Venta");
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
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jTextField2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("   Factura ");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Datos Generales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel8.setText("Fecha :");

        jLabel9.setText("No. Documento :");

        jTextField2.setEditable(false);

        jLabel10.setText("Usuario :");

        jTextField3.setEditable(false);

        jButton1.setText("Buscar Usuario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Detalle Factura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setText("Tipo :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Credito", "Contado" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jLabel4.setText("Observaciones :");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jLabel3.setText("Plazo :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Facturacion De Articulo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel5.setText("Cod Articulo  :");

        jFormattedTextField2.setEditable(false);

        jLabel11.setText("Descripcion :");

        jTextField5.setEditable(false);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Buscar Articulo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jLabel6.setText("Cantidad :");

        jFormattedTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextField3KeyPressed(evt);
            }
        });

        jLabel7.setText("Precio Unit :");

        jFormattedTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextField4KeyPressed(evt);
            }
        });

        jButton3.setText("Agregar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        jButton4.setText("Modificar");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton4KeyPressed(evt);
            }
        });

        jLabel12.setText("Maquila :");

        jTextField6.setEditable(false);

        jButton5.setText("Buscar Maquila");
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel13.setText("Remision :");

        jTextField7.setEditable(false);

        jButton7.setText("Buscar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Sin Remision");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .addGap(23, 23, 23)
                        .addComponent(jButton3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(10, 10, 10)
                        .addComponent(jButton8)))
                .addContainerGap(118, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel11)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21)
                    .addComponent(jButton2)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7)
                        .addComponent(jButton8))
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2))
                    .addContainerGap(82, Short.MAX_VALUE)))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Articulo", "Descripcion", "Cantidad", "Precio Unit.", "Subtotal", "Iva", "Total"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jFormattedTextField7.setEditable(false);
        jFormattedTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel4.add(jFormattedTextField7);

        jFormattedTextField6.setEditable(false);
        jFormattedTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel4.add(jFormattedTextField6);

        jFormattedTextField5.setEditable(false);
        jFormattedTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel4.add(jFormattedTextField5);

        jButton9.setText("<<");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText(">>");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Anular");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));

        jButton6.setText("Ver Reporte");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton12.setText("Modificar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel15.setText("PREFIJO :");

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jMenu1.setText("Opciones");

        jMenuItem1.setText("Remisiones No Facturadas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        jMenuItem2.setText("Buscar Factura");
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton11)
                            .addComponent(jButton6)
                            .addComponent(jButton12)))
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.BuscarUsuario();
        
}//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.BuscarArticulo();
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==evt.VK_ENTER){
            this.BuscarArticulo();
        }        
}//GEN-LAST:event_jButton2KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.IngresarFacturacionDeArticulo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.BuscarMaquila();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==evt.VK_ENTER){
            if(this.jComboBox1.getSelectedIndex()==0){
                
            }else{
                this.jTextField4.requestFocus();
            }
        }
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==evt.VK_ENTER){
            this.BuscarArticulo();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jFormattedTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField3KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==evt.VK_ENTER){
            if(this.jButton3.isEnabled())
               this.jButton3.requestFocus();
            else
               this.jButton4.requestFocus(); 
        }
        
    }//GEN-LAST:event_jFormattedTextField3KeyPressed

    private void jFormattedTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField4KeyPressed
        // TODO add your handling code here:
       if(evt.getKeyCode()==evt.VK_ENTER){
            this.jFormattedTextField3.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextField4KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==evt.VK_ENTER){
            this.IngresarFacturacionDeArticulo();
        }
    }//GEN-LAST:event_jButton3KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        filaseleccionada=this.jTable1.getSelectedRow();
        this.EventoTabla();
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.ModificarFacturacionDeArticulo();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==evt.VK_ENTER){
            this.ModificarFacturacionDeArticulo();
        }
    }//GEN-LAST:event_jButton4KeyPressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.BuscarRemisiones();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        this.BuscarRemisiones();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
       this.setRemision(null);
       this.jFormattedTextField4.setEditable(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==evt.VK_ENTER){
            this.BuscarUsuario();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        this.IrAtras();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        this.IrAdelante();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        int op=JOptionPane.showConfirmDialog(this,"Esta Seguro de Anular esta Factura");
        if(op==0)
        this.Anular();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.VerReporte();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.Buscar();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        int op=JOptionPane.showConfirmDialog(this,"Esta Seguro de Modificar esta Factura");
        if(op==0)
        this.Modificar();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
         int pos=this.jComboBox2.getSelectedIndex();
        prefijo=this.prefijos.get(pos);
        System.out.println(pos);
        int n=service.getDao().ObtenerNumeracion(prefijo.getDocumento_factura());
        this.Cargar(n,prefijo.getIdPrefijo());
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FolderDeFacturas().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JFormattedTextField jFormattedTextField7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables

    @Override
    public void EventoDeSeleccionUsuario() {
        this.setUsuario(bu.ObtenerUsuarioSeleccionado());
        this.show();
        this.bu.dispose();
        this.jComboBox1.requestFocus();
    }
    @Override
    public void setUsuario(Usuario u) {
        if(u!=null){            
            this.jTextField2.setText(u.getNoDocumentoCompleto());
            this.jTextField3.setText(u.NombreCompleto());
        }else{
            this.jTextField2.setText("");
            this.jTextField3.setText("");
        }
        this.usuario=u;
    }
    @Override
    public void EventoDeSeleccionArticulo() {
        this.show();
        this.ba.dispose();
        this.setArticulo(ba.ObtenerArticuloSeleccionado());        
        if(ba.ObtenerArticuloSeleccionado().getCategoria().equals("producto procesado"))
           this.BuscarMaquila();
        else
           this.jFormattedTextField4.requestFocus();
    }
    @Override
    public void setArticulo(Articulo a) {
        if(a!=null){
            this.jFormattedTextField2.setText(a.getId().toString());
            this.jTextField5.setText(a.getDescripcion());
            if(a.getCategoria().equals("producto procesado")==true || a.getCategoria().equals("subproductos")==true){
                this.jButton5.setEnabled(true);               
            }else{
                this.jButton5.setEnabled(false);                
            }
        }else{
            this.jFormattedTextField2.setText("");
            this.jTextField5.setText("");
            this.jButton5.setEnabled(false);
            
        }
        this.articulo=a;
    }
    @Override
    public void EventoDeSeleccionMaquila() {
        this.setMaquila(bm.getMaquila());
        this.show();
        bm.dispose();
        this.jFormattedTextField4.requestFocus();
    }
    @Override
    public void setMaquila(Maquila m) {
        if(m!=null){
            this.jTextField6.setText(m.getId().toString());
        }else{
            this.jTextField6.setText("");
        }
        maquila=m;
    }
    @Override
    public void EventoDeSeleccionRemesion() {
        if(modelo.ExisteEstaRemision(br.ObtenerRemisionSeleccionada().getId())==false){
           this.setRemision(br.ObtenerRemisionSeleccionada());
           this.fa=service.Transformar(remision);    
           this.setMaquila(fa.getMaquila());
           this.setArticulo(fa.getArticulo());
           this.setRemision(fa.getRemision());
           this.jFormattedTextField4.setValue(new Double(fa.getCantidad()));
           this.jFormattedTextField4.setEditable(false);
           this.show();
           br.dispose();
           this.jFormattedTextField3.requestFocus();
        }else{
            JOptionPane.showMessageDialog(this.br,"Esa Remision ya Fue Ingresada");               
        }
    }
    @Override
    public void setRemision(Remision r) {
        if(r!=null){
            this.jTextField7.setText(r.getId().toString());
        }else{
            this.jTextField7.setText("");
        }
        this.remision=r;
    }
    //////////////////
    //   KEY EVENTS ////////////////////////////////
    //////////////////                            //  
    @Override                                     //
    public void keyTyped(KeyEvent e) {
    }       
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==e.VK_LEFT){
            this.IrAtras();
        }
        if(e.getKeyCode()==e.VK_RIGHT){
            this.IrAdelante();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    /**
     * @param actor the actor to set
     */
    public void setActor(String actor) {
        this.actor = actor;
    }
}

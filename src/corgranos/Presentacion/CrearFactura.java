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
import corgranos.Dominio.Core.Factura.FacturaService;
import corgranos.Dominio.Core.Factura.FacturacionDeArticulo;
import corgranos.Dominio.Core.Factura.Prefijo;
import corgranos.Dominio.Core.Maquila.Maquila;
import corgranos.Dominio.Core.Remision.Remision;
import corgranos.Dominio.Servicios.DataConfiguration;
import corgranos.Dominio.Servicios.DataConfigurationService;
import corgranos.Dominio.Servicios.Inventario.InventarioService;
import corgranos.Presentacion.ModelosDeTabla.FacturacionDeArticuloModel;
import corgranos.Presentacion.Suscripciones.ISuscripcionBuscarArticulo;
import corgranos.Presentacion.Suscripciones.ISuscripcionBuscarMaquila;
import corgranos.Presentacion.Suscripciones.ISuscripcionBuscarRemision;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import sic.Dominio.Core.Usuario.Usuario;
import sic.Dominio.Servicios.Contabilidad.ContabilidadService;
import sic.Presentacion.Modulos.Usuario.BuscarUsuario;
import sic.Presentacion.Suscripciones.ISuscripcionBuscarUsuario;

/**
 *
 * @author FANNY BURGOS
 */
public class CrearFactura extends javax.swing.JFrame implements ISuscripcionBuscarUsuario,ISuscripcionBuscarArticulo,ISuscripcionBuscarMaquila,ISuscripcionBuscarRemision{
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
    private String actor="developer";
    double existencia=0;
    Vector<corgranos.Dominio.Core.Factura.Prefijo> prefijos;
    Prefijo prefijo;
    /** Creates new form CrearFactura */
    public CrearFactura() {
        //this.setContentPane(new JScrollPane());
        initComponents();
        lista=new Vector<FacturacionDeArticulo>();        
        modelo=new FacturacionDeArticuloModel();
        this.jTable1.setModel(modelo);
        this.setAnchoColumnas();
        this.jTable1.repaint();
        this.jFormattedTextField1.setValue(new Double(0));
        this.jFormattedTextField4.setValue(new Double(0));
        this.jFormattedTextField3.setValue(new Double(0));
        service=new FacturaService();        
        this.LLenarPrefijos();
        this.AsignarNumeracion();        
        this.jComboBox2.requestFocus();
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
    
    
    void AsignarNumeracion(){
        Factura f=new Factura();
        int n=service.getDao().ObtenerNumeracion(prefijo.getDocumento_factura());        
        if(n<prefijo.getUltima_numeracion()){
            n=prefijo.getUltima_numeracion();
        }
        n++;
        this.jTextField1.setText(""+n);
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
                case 0: anchoColumna = (6*ancho)/100;
                        break;
                case 1: anchoColumna = (35*ancho)/100;
                        break;
                case 2: anchoColumna = (7*ancho)/100;
                        break;
                case 3: anchoColumna = (7*ancho)/100;
                        break;
                case 4: anchoColumna = (7*ancho)/100;
                        break;    
                case 5: anchoColumna = (7*ancho)/100;
                        break;    
                case 6: anchoColumna = (7*ancho)/100;
                        break;        
                case 7: anchoColumna = (12*ancho)/100;
                        break;            
                case 8: anchoColumna = (12*ancho)/100;
                        break;        
                case 9: anchoColumna = (10*ancho)/100;
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
        fa=new FacturacionDeArticulo();
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
    void CargarExistencia(){        
        existencia=0;
        Articulo a=fa.getArticulo();                
        if(a.getCategoria().equals("producto procesado") || a.getCategoria().equals("subproductos")){
            InventarioService is=new InventarioService();        
            Maquila  m=fa.getMaquila();
            existencia=is.ObtenerExistencia(a.getId(),m.getId());
        }else{           
              ContabilidadService cs=new  ContabilidadService();
              Date fechacorte=this.dateChooserCombo1.getSelectedDate().getTime();
              existencia=cs.getDao().ObtenerExistencia(a.getAuxactivo()+""+a.getId(),fechacorte,true,null);                                
        }
    }
    void IngresarFacturacionDeArticulo(){        
        this.BuildFacturacionDeArticulo();                
        if(fa.getArticulo().getTipo().equals("producto")){
           this.CargarExistencia(); 
           if(existencia>=fa.getCantidad() || fa.isRemisionada()==true){
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
           }else{
               JOptionPane.showMessageDialog(this,"No Puede Vender en Inventario hay : "+existencia);
           }
        }else{
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
    }
    void ModificarFacturacionDeArticulo(){
        this.BuildFacturacionDeArticulo();
        if(fa.getArticulo().getTipo().equals("producto")){
           this.CargarExistencia();
           if(existencia>=fa.getCantidad() || fa.isRemisionada()==true){
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
        }else{
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
    void Guardar(){
        DataConfigurationService dcs=new DataConfigurationService();
        DataConfiguration dc=dcs.getDao().ObtenerConfiguracion();
        prefijo=prefijos.get(this.jComboBox2.getSelectedIndex());
        Factura f=new Factura();
        f.setPrefijo(prefijo);
        f.setUsuario(usuario);
        f.setObservaciones(this.jTextField4.getText());
        f.setLista(lista);
        f.setTotal(SumatoriaTotal);
        f.setTotaliva(SumatoriaIva);
        f.setSubtotal(SumatoriaSubtotal);        
        f.setDian1(prefijo.getDian1());
        f.setDian2(prefijo.getDian2());
        f.setCreador(this.actor);
        f.setTipofactura(this.jComboBox1.getSelectedItem().toString());
        
        long dia=24*60*60*1000;
        int dias_de_plazo=((Double)this.jFormattedTextField1.getValue()).intValue();
        long dias_milisegundos=dia*dias_de_plazo;        
        f.setFechaplazo(new Date(this.dateChooserCombo1.getSelectedDate().getTime().getTime()+dias_milisegundos));
        f.setFechaContable(this.dateChooserCombo1.getSelectedDate().getTime());              
        if(service.IngresarFactura(f)){
            this.Limpiar();
            JOptionPane.showMessageDialog(this,service.ObtenerMensaje());
            this.jButton1.requestFocus();
        }else{
            JOptionPane.showMessageDialog(this,service.ObtenerMensaje());
        }
    }
    void Limpiar(){
        this.jComboBox1.setSelectedIndex(0);
        this.setUsuario(null);
        this.setMaquila(null);
        this.setRemision(null);
        this.setArticulo(null);
        this.jFormattedTextField4.setValue(new Double(0));
        this.jFormattedTextField3.setValue(new Double(0));
        this.jFormattedTextField4.setEditable(true);
        this.jTextField4.setText("");
        this.jFormattedTextField1.setValue(new Double(30));
        this.modelo=new FacturacionDeArticuloModel();
        this.lista=new Vector<FacturacionDeArticulo>();
        this.jTable1.setModel(modelo);
        this.setAnchoColumnas();
        this.jTable1.repaint();        
        this.HallarTotalizados();
        this.AsignarNumeracion();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
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
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
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
        jButton6 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

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
                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contado", "Credito" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
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

        jLabel3.setText("No Dias :");

        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setText("30");
        jFormattedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextField1KeyPressed(evt);
            }
        });

        jLabel4.setText("Observaciones :");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(252, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
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
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
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

        jButton6.setText("Ingresar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton6KeyPressed(evt);
            }
        });

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jLabel14.setText("Prefijo :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE))
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton6)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel5);

        jMenu1.setText("Opciones");

        jMenuItem1.setText("Remisiones No Facturadas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
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
        if(evt.getKeyCode()==evt.VK_DOWN){
            this.jButton6.requestFocus();
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
                this.jTextField4.requestFocus();
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

    private void jFormattedTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField1KeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==evt.VK_ENTER){
            if(this.jComboBox1.getSelectedItem().equals("Contado")){
               this.jFormattedTextField1.setValue(new Double(0));
            }
            this.jTextField4.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextField1KeyPressed

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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int op=JOptionPane.showConfirmDialog(this,"Esta Seguro de Guardar esta Factura");
        if(op==0)
        this.Guardar();
    }//GEN-LAST:event_jButton6ActionPerformed

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

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==evt.VK_ENTER){
            int op=JOptionPane.showConfirmDialog(this,"Esta Seguro de Guardar esta Factura");
            if(op==0)
              this.Guardar();
        }
    }//GEN-LAST:event_jButton6KeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==evt.VK_ENTER){
            this.BuscarUsuario();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        if(this.jComboBox1.getSelectedItem().equals("Credito")){
            this.jFormattedTextField1.setValue(new Double(30));
        }
        if(this.jComboBox1.getSelectedItem().equals("Contado")){
            this.jFormattedTextField1.setValue(new Double(0));
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        int pos=this.jComboBox2.getSelectedIndex();
        prefijo=this.prefijos.get(pos);
        System.out.println(pos);
        this.AsignarNumeracion();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.BuscarUsuario();
        }
    }//GEN-LAST:event_jComboBox2KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CrearFactura();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
        if(ba.ObtenerArticuloSeleccionado().getCategoria().equals("producto procesado") || ba.ObtenerArticuloSeleccionado().getCategoria().equals("subproductos") )
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

    /**
     * @param actor the actor to set
     */
    public void setActor(String actor) {
        this.actor = actor;
    }
}

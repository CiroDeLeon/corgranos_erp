/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Servicios.Inventario.InventarioDeArticuloPorMaquila;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class InventarioDeArticuloPorMaquilaModel extends AbstractTableModel{
    private Vector <InventarioDeArticuloPorMaquila> lista;
    String columns[]={"Cod. Maquila","Producido","Facturado","Remisionado","Existencia"};
    
    public InventarioDeArticuloPorMaquilaModel() {
        lista=new Vector <InventarioDeArticuloPorMaquila>();
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InventarioDeArticuloPorMaquila ir=this.getLista().get(rowIndex);
        switch(columnIndex){
            case 0 : return "Maq."+ir.getMaquila();       
            case 1 : return ir.getProducciones();  
            case 2 : return ir.getFacturas();      
            case 3 : return ir.getRemisiones();     
            case 4 : return ir.getExistencia();           
            default: return "";    
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0 : return Object.class;            
            case 1 : return Double.class;            
            case 2 : return Double.class;            
            case 3 : return Double.class;            
            case 4 : return Double.class;                
            default: return String.class;    
        }
    }    
    @Override
    public String getColumnName(int column) {
        return this.columns[column];
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    public void setLista(Vector <InventarioDeArticuloPorMaquila> lista) {
        this.lista = lista;
    }
    public InventarioDeArticuloPorMaquila getRow(int rowIndex){
        return getLista().get(rowIndex);
    }
    @Override
    public int getRowCount() {
        return getLista().size();
    }

    /**
     * @return the lista
     */
    public Vector <InventarioDeArticuloPorMaquila> getLista() {
        return lista;
    }
}

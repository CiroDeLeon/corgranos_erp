/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Servicios.Inventario.InventarioResumidoPorMaquila;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class InventarioResumidoPorMaquilaModel extends AbstractTableModel{
private Vector <InventarioResumidoPorMaquila> lista;
    String columns[]={"Cod. Articulo","Descripcion","Producido","Vendido","Remisionado","Inventario"};
    
    public InventarioResumidoPorMaquilaModel() {
        lista=new Vector <InventarioResumidoPorMaquila>();
    }
    

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InventarioResumidoPorMaquila m=this.getLista().get(rowIndex);
        switch(columnIndex){
            case 0 : return ""+m.getIdarticulo();
            case 1 : return m.getDescripcion();
            case 2 : return m.getProducciones();                
            case 3 : return m.getFacturas();            
            case 4 : return m.getRemisiones();            
            case 5 : return m.getExistencia();    
            default: return "";    
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0 : return String.class;
            case 1 : return String.class;
            case 2 : return Double.class;     
            case 3 : return Double.class;     
            case 4 : return Double.class;     
            case 5 : return Double.class;         
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

    /**
     * @param lista the lista to set
     */
    public void setLista(Vector <InventarioResumidoPorMaquila> lista) {
        this.lista = lista;
    }
    public InventarioResumidoPorMaquila getRow(int rowIndex){
        return getLista().get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return getLista().size();
    }

    /**
     * @return the lista
     */
    public Vector <InventarioResumidoPorMaquila> getLista() {
        return lista;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Core.Produccion.ProduccionDeArticulo;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class ProduccionDeArticuloModel extends AbstractTableModel{
    private Vector <ProduccionDeArticulo> lista;    

    public ProduccionDeArticuloModel() {
        lista=new Vector<ProduccionDeArticulo>();
    }

    String columns[]={"Cod.Producto","Descripcion","Cantidad","Precio Kg"};
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProduccionDeArticulo pa=lista.get(rowIndex);
        switch(columnIndex){
            case 0 : return pa.getArticulo().getId();
            case 1 : return pa.getArticulo().getDescripcion();
            case 2 : return pa.getCantidad();    
            case 3 : return pa.getPreciounitario()/pa.getArticulo().getCantidadkg();    
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
    public void setLista(Vector <ProduccionDeArticulo> lista) {
        this.lista = lista;
    }
    public ProduccionDeArticulo getRow(int rowIndex){
        return lista.get(rowIndex);
    }    
    
}

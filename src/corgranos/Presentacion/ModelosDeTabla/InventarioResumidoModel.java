/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Servicios.Inventario.InventarioResumido;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class InventarioResumidoModel extends AbstractTableModel{
private Vector <InventarioResumido> lista;
    String columns[]={"Cod. Articulo","Descripcion","Saldo"};
    
    public InventarioResumidoModel() {
        lista=new Vector <InventarioResumido>();
    }
    

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InventarioResumido ir=this.getLista().get(rowIndex);
        switch(columnIndex){
            case 0 : return ir.getIdarticulo();
            case 1 : return ir.getDescripcion();
            case 2 : return ir.getExistencia();           
            default: return "";    
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0 : return Object.class;
            case 1 : return String.class;
            case 2 : return Double.class;            
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
    public void setLista(Vector <InventarioResumido> lista) {
        this.lista = lista;
    }
    public InventarioResumido getRow(int rowIndex){
        return getLista().get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return getLista().size();
    }

    /**
     * @return the lista
     */
    public Vector <InventarioResumido> getLista() {
        return lista;
    }
}

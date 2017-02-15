/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Servicios.Inventario.Kardex;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class KardexModel extends AbstractTableModel{
    private Vector <Kardex> lista;
    String columns[]={"fecha","Nit/Cedula","Usuario","Soporte","Maq","Detalle","Entradas","Salidas","Existencia"};

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
        Kardex k=lista.get(rowIndex);
        switch(columnIndex){
            case 0 : return k.getFecha();
            case 1 : return k.getNit();
            case 2 : return k.getUsuario();    
            case 3 : return k.getSoporte();
            case 4 : return k.getMaquila();    
            case 5 : return k.getDetalle();
            case 6 : return k.getEntradas();
            case 7 : return k.getSalidas();
            case 8 : return k.getExistencia();    
            default: return "";    
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case  0 : return Date.class;       
            case  1 : return String.class;    
            case  2 : return String.class;        
            case  3 : return String.class;        
            case  4 : return Integer.class;    
            case  5 : return String.class;        
            case  6 : return Double.class;        
            case  7 : return Double.class;        
            case  8 : return Double.class;            
            default : return String.class;    
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
    public void setLista(Vector <Kardex> lista) {
        this.lista = lista;
    }
    public Vector <Kardex> getLista(){
        return this.lista;
    }
    public Kardex getRow(int rowIndex){
        return lista.get(rowIndex);
    }

    
    
}

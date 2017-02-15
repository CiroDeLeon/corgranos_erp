/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Core.Maquila.Maquila;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class MaquilaModel extends AbstractTableModel{
    private Vector <Maquila> lista;
    String columns[]={"No. Maquila","Peso14_1","Peso_Destarado","Masa Blanca","Costo","Abierta"};
    
    public MaquilaModel() {
        lista=new Vector <Maquila>();
    }
    

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Maquila m=this.lista.get(rowIndex);
        switch(columnIndex){
            case 0 : return ""+m.getId();
            case 1 : return m.getTotalpeso14_1();
            case 2 : return m.getTotalpesodestarado();
            case 3 : return m.getTotalmasablanca();
            case 4 : return m.getTotalcosto();            
            case 5 : return !m.isCerrada();            
            default: return "";    
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0 : return String.class;
            case 1 : return Double.class;
            case 2 : return Double.class;
            case 3 : return Double.class;
            case 4 : return Double.class;            
            case 5 : return Boolean.class;            
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
    public void setLista(Vector <Maquila> lista) {
        this.lista = lista;
    }
    public Maquila getRow(int rowIndex){
        return lista.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }
}

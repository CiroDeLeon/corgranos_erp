/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Core.Maquila.ReporteMaquilaCerrada;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class ReporteMaquilaCerradaModel extends AbstractTableModel{
    Vector <ReporteMaquilaCerrada> lista;
    String columns[]={"Descripcion","Debito","Entradas","Credito","Salidas","Costo Unitario"};
    

    public ReporteMaquilaCerradaModel() {
        lista =new Vector <ReporteMaquilaCerrada>();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ReporteMaquilaCerrada rep=lista.get(rowIndex);
        switch(columnIndex){
            case 0 : return rep.getDescripcion();
            case 1 : return rep.getDebito();
            case 2 : return rep.getEntradas();
            case 3 : return rep.getCredito();    
            case 4 : return rep.getSalidas();        
            case 5 : return rep.getCostounitario();            
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
    public void setLista(Vector <ReporteMaquilaCerrada> lista) {
        if(lista!=null){
           this.lista = lista;
        }
    }
    public ReporteMaquilaCerrada getRow(int rowIndex){
        return lista.get(rowIndex);
    }
}

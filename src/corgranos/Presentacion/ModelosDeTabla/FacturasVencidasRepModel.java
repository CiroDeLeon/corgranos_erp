/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Core.Factura.FacturasVencidasRep;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class FacturasVencidasRepModel extends AbstractTableModel{
private Vector <FacturasVencidasRep> lista;
    String columns[]={"Factura","fecha","Nit/Cedula","Cliente","Plazo","Retraso(Dias)","Total","Saldo"};

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
        FacturasVencidasRep f=lista.get(rowIndex);
        switch(columnIndex){
            case 0 : return f.getFactura();
            case 1 : return f.getFecha();
            case 2 : return f.getNit();
            case 3 : return f.getCliente();    
            case 4 : return f.getPlazo();
            case 5 : return f.getRetraso();    
            case 6 : return f.getTotal();
            case 7 : return f.getSaldo();            
            default: return "";    
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case  0 : return String.class;       
            case  1 : return Date.class;    
            case  2 : return String.class;        
            case  3 : return String.class;        
            case  4 : return Date.class;    
            case  5 : return Integer.class;        
            case  6 : return Double.class;        
            case  7 : return Double.class;                    
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
    public void setLista(Vector <FacturasVencidasRep> lista) {
        this.lista = lista;
    }
    public Vector <FacturasVencidasRep> getLista(){
        return this.lista;
    }
    public FacturasVencidasRep getRow(int rowIndex){
        return lista.get(rowIndex);
    }
    
}

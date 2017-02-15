/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Presentacion.ModelosDeTabla;

import java.util.Date;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import sic.Dominio.Servicios.Contabilidad.AsientoPorFechaDTO;

/**
 *
 * @author FANNY BURGOS
 */
public class AsientoPorFechaDTOModel extends AbstractTableModel{
    private Vector <AsientoPorFechaDTO> lista;
    String columns[]={"Fecha","Soporte","Cod.Cta","Denominacion","Debito","Credito",};
    

    public AsientoPorFechaDTOModel() {
        lista=new Vector<AsientoPorFechaDTO>();
    }

    @Override
    public int getRowCount() {
        return getLista().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AsientoPorFechaDTO m=getLista().get(rowIndex);
        switch(columnIndex){
            case 0: return m.getFecha();
            case 1: return m.getSoporte();    
            case 2: return m.getIdcta();
            case 3: return m.getDenominacion();
            case 4: return m.getDebito();
            case 5: return m.getCredito();    
            default: return "";
        }
    }
     @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0 : return Date.class;
            case 1 : return String.class;
            case 2 : return String.class;
            case 3 : return String.class;
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
    
    public AsientoPorFechaDTO getRow(int rowIndex){
        return getLista().get(rowIndex);
    }
    public void setLista(Vector <AsientoPorFechaDTO> lista) {
        this.lista = lista;
    }

    /**
     * @return the lista
     */
    public Vector <AsientoPorFechaDTO> getLista() {
        return lista;
    }
}

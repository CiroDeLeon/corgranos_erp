/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Core.Articulo.Articulo;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class ArticuloModel extends AbstractTableModel{
    private Vector <Articulo> lista;
    String columns[]={"Cod.Contable","Cod. Barra","Descripcion","Referencia","No Kg","Precio U.","Tipo","Categoria","Porcentage Iva","Aux_Ingreso","Aux_Activo","Aux_Costos","Aux_Empaque"};
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
        Articulo a=lista.get(rowIndex);
        switch(columnIndex){
            case 0 : return a.getId();
            case 1 : return a.getCodigodebarra();
            case 2 : return a.getNombre();
            case 3 : return a.getReferencia();
            case 4 : return a.getCantidadkg();
            case 5 : return a.getPreciounitario();
            case 6 : return a.getTipo();
            case 7 : return a.getCategoria();
            case 8 : return a.getPorcentageiva();
            case 9 : return a.getAuxingreso();
            case 10: return a.getAuxactivo();
            case 11: return a.getAuxcosto();
            case 12: return a.getAuxempaque();    
            default: return "";
        }
    }
     @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case  0 : return String.class;
            case  1 : return Long.class;
            case  2 : return String.class;
            case  3 : return String.class;
            case  4 : return Integer.class;            
            case  5 : return Double.class;   
            case  6 : return String.class;   
            case  7 : return String.class;   
            case  8 : return Double.class;
            case  9 : return String.class;
            case 10 : return String.class;    
            case 11 : return String.class;   
            case 12 : return String.class;   
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
    public void setLista(Vector <Articulo> lista) {
        this.lista = lista;
    }
    public Articulo getRow(int rowIndex){
        return lista.get(rowIndex);
    }    
}

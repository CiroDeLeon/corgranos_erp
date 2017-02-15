/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Core.Remision.Remision;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class RemisionModel extends AbstractTableModel{
    private Vector <Remision> lista;
    String columns[]={"Cod.","Fecha","Nit/Cedula","Usuario","Maquila","Articulo","Salidas"};
    

    public RemisionModel() {
        this.lista=new Vector <Remision>();
    }

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
        Remision r=this.lista.get(rowIndex);
        switch(columnIndex){
            case 0 : return r.getId();
            case 1 : return r.getUsuario().getNoDocumentoCompleto();    
            case 2 : return r.getUsuario().NombreCompleto();    
            case 3 : return r.getFechacontable();
            case 4 : 
                    if(r.getMaquila()!=null)
                    return r.getMaquila().getId();
                    else
                    return "";    
            case 5 : return r.getArticulo().getDescripcion();
            case 6 : return r.getSalidas();
            default : return "";
        }
    }
      @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0 : return String.class;
            case 1 : return String.class;
            case 2 : return String.class;
            case 3 : return Date.class;
            case 4 : return Object.class;            
            case 5 : return String.class; 
            case 6 : return Double.class;    
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
    public void setLista(Vector <Remision> lista) {
        if(lista!=null){
           this.lista = lista;
        }
    }
    public Remision getRow(int rowIndex){
        return lista.get(rowIndex);
    }

    
    
    
}

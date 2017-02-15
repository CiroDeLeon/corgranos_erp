/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Core.Factura.FacturacionDeArticulo;
import corgranos.Dominio.Core.Remision.Remision;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class FacturacionDeArticuloModel extends AbstractTableModel{
    private Vector <FacturacionDeArticulo> lista;    

    public FacturacionDeArticuloModel() {
        lista=new Vector<FacturacionDeArticulo>();
    }

    String columns[]={"Cod.Articulo","Descripcion","Remision","Maquila","Cantidad","Precio Unit","Subtotal","iva","Total"};
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
        FacturacionDeArticulo fa=lista.get(rowIndex);
        switch(columnIndex){
            case 0 : return fa.getArticulo().getId();
            case 1 : return fa.getArticulo().getDescripcion();
            case 2 : 
                      if(fa.getRemision()!=null){
                         return fa.getRemision().getId(); 
                      }else{
                         return ""; 
                      }    
            case 3 : 
                      if(fa.getMaquila()!=null){
                          return fa.getMaquila().getId();
                      }else{
                          return "";
                      }       
            case 4 : return fa.getCantidad();    
            case 5 : return fa.getPreciounitario();
            case 6 : return Math.round(fa.getPreciounitario()*fa.getCantidad());
            case 7 : return Math.round(fa.getValoriva());
            case 8 : return Math.round(fa.getPreciounitario()*fa.getCantidad())+Math.round(fa.getValoriva());
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0 : return String.class;
            case 1 : return String.class;
            case 2 : return Object.class;    
            case 3 : return Object.class;        
            case 4 : return Double.class;    
            case 5 : return Double.class;    
            case 6 : return Double.class;    
            case 7 : return Double.class;        
            case 8 : return Double.class;            
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
    public void setLista(Vector <FacturacionDeArticulo> lista) {
        this.lista = lista;
    }
    public FacturacionDeArticulo getRow(int rowIndex){
        return lista.get(rowIndex);
    }    
    public boolean ExisteEstaRemision(Object idRemision){
        Iterator <FacturacionDeArticulo> it=this.lista.iterator();
        while(it.hasNext()){
            FacturacionDeArticulo fa=it.next();
            Remision r=fa.getRemision();
            if(r!=null){
               if(fa.getRemision().getId()==idRemision){
                  return true;
               }
            }
        }
        return false;
    }
}

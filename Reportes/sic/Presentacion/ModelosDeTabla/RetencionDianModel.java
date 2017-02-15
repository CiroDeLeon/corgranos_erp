/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Presentacion.ModelosDeTabla;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import sic.Dominio.Core.PUC.PucService;
import sic.Dominio.Servicios.Dian.RetencionDian;

/**
 *
 * @author FANNY BURGOS
 */
public class RetencionDianModel extends AbstractTableModel{
    private Vector <RetencionDian> lista;
    String columns[]={"Descripcion","Aux","Detalle Aux","Base","% RTF"};
    public RetencionDianModel() {
        lista=new Vector <RetencionDian>();
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
        RetencionDian rd=getLista().get(rowIndex);
        switch(columnIndex){
            case 0 : return rd.getDescripcion();
            case 1 : return rd.getIdauxiliar();
            case 2 : return new PucService().ObtenerCtaPuc(rd.getIdauxiliar()).getDenominacion();
            case 3 : return rd.getBase();
            case 4 : return rd.getPorcentage();    
            default: return "";
        }
    }
@Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){            
            case 3 : return Double.class;
            case 4 : return Double.class;                
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
    
    public RetencionDian getRow(int rowIndex){
        return getLista().get(rowIndex);
    }
    public void setLista(Vector <RetencionDian> lista) {
        this.lista = lista;
    }

    /**
     * @return the lista
     */
    public Vector <RetencionDian> getLista() {
        return lista;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddyPorMaquilaDTO;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class LiquidacionDePaddyDTOModel extends AbstractTableModel{
    private Vector <LiquidacionDePaddyPorMaquilaDTO> lista;
    String columns[]={"Fecha","No. Liq","Tiq","Nit/Cedula","Usuario","Hum","Imp","Rojo","Partido","Yeso","Harina","Peso 14_1","Peso Des","Masa Blanca","Valor"};
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
        LiquidacionDePaddyPorMaquilaDTO lp=lista.get(rowIndex);
        switch(columnIndex){
            case  0: return lp.getFechacontable();
            case  1: return lp.getLiquidacion();
            case  2: return lp.getTiquete();
            case  3: return lp.getNit();
            case  4: return lp.getUsuario();
            case  5: return lp.getHumedad();
            case  6: return lp.getImpureza();
            case  7: return lp.getRojo();    
            case  8: return lp.getPartido();
            case  9: return lp.getYeso();
            case 10: return lp.getHarina();   
            case 11: return lp.getPeso14_1();
            case 12: return lp.getPesodestarado();
            case 13: return lp.getMasablanca();
            case 14: return lp.getValor();
            default: return"";    
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case  0: return Date.class;
            case  1: return Integer.class;
            case  2: return Integer.class;
            case  3: return Long.class;
            case  4: return String.class;
            case  5: return Double.class;
            case  6: return Double.class;
            case  7: return Double.class;    
            case  8: return Double.class;
            case  9: return Double.class;
            case 10: return Double.class;
            case 11: return Double.class;
            case 12: return Double.class;
            case 13: return Double.class;
            case 14: return Double.class;
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
    public void setLista(Vector <LiquidacionDePaddyPorMaquilaDTO> lista) {
        this.lista = lista;
    }
    public LiquidacionDePaddyPorMaquilaDTO getRow(int rowIndex){
        return lista.get(rowIndex);
    }    
}

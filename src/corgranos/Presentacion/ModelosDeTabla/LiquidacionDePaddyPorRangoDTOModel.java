/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddyPorRangoDTO;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class LiquidacionDePaddyPorRangoDTOModel extends AbstractTableModel{
    private Vector<LiquidacionDePaddyPorRangoDTO> lista;
    String columns[]={"Fecha","No. Liq","Tiq","Nit/Cedula","Usuario","Hum","Imp","Partido","Peso Destarado","Peso 14/1","Peso Liquidado","Precio","Masa Blanca","Valor","Fomento","Bolsa","RTF","Total"};
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
        LiquidacionDePaddyPorRangoDTO lp=getLista().get(rowIndex);
        switch(columnIndex){
            case 0 : return lp.getFechacontable();
            case 1 : return lp.getLiquidacion();
            case 2 : return lp.getTiquete();
            case 3 : return lp.getNit();
            case 4 : return lp.getUsuario();
            case 5 : return lp.getHumedad();
            case 6 : return lp.getImpureza();
            case 7 : return lp.getPartido();
            case 8 : return lp.getPesodestarado();
            case 9 : return lp.getPeso14_1();
            case 10: return lp.getPesoliquidado();
            case 11: return lp.getPrecioliquidado();
            case 12: return lp.getMasablanca();
            case 13: return lp.getValor();
            case 14: return lp.getFomento();
            case 15: return lp.getBolsa();
            case 16: return lp.getRetefuente();
            case 17: return lp.getTotal();    
            default: return "";    
        }        
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex>=5){
            return Double.class;
        }else{
            switch(columnIndex){
                case 0 : return Date.class;
                case 1 : return Integer.class;
                case 2 : return Integer.class;    
                case 3 : return Long.class;                    
                default : return String.class;    
            }
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
    public void setLista(Vector <LiquidacionDePaddyPorRangoDTO> lista) {
        this.lista = lista;
    }
    
    public LiquidacionDePaddyPorRangoDTO getRow(int rowIndex){
        return getLista().get(rowIndex);
    }

    /**
     * @return the lista
     */
    public Vector<LiquidacionDePaddyPorRangoDTO> getLista() {
        return lista;
    }
}

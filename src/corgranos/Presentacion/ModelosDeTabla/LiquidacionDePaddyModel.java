/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Presentacion.ModelosDeTabla;

import corgranos.Dominio.Core.Liquidacion.LiquidacionDePaddy;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FANNY BURGOS
 */
public class LiquidacionDePaddyModel extends AbstractTableModel{
    private Vector <LiquidacionDePaddy> lista;
    String columns[]={"Fecha","No. Liq","Tiq","Nit/Cedula","Usuario",
                      "Hum Pact.","Imp Pact.","Hum Lab","Imp Lab","Peso Bruto",
                      "Peso Destare","Peso Liq","Rojo Lab","Partido Lab","Yeso Lab",
                      "Harina Lab","Vr Pactado","Vr Liquidado","Vr Compra","Fomento",
                      "Bolsa","Retefuente","Total"};    
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
        LiquidacionDePaddy lp=this.lista.get(rowIndex);
        switch(columnIndex){
            case 0 : return lp.getFechaContable();
            case 1 : return lp.getNumeracion();
            case 2 : return lp.getTiquete();
            case 3 : return lp.getNodocumento();    
            case 4 : return lp.getUsuario().NombreCompleto();
            case 5 : return lp.getHumedad_pact();
            case 6 : return lp.getImpureza_pact();
            case 7 : return lp.getHumedad_lab();
            case 8 : return lp.getImpureza_lab();
            case 9 : return lp.getPesobruto();    
            case 10: return lp.getPesodestarado();
            case 11: return lp.getPeso_liquidado();
            case 12: return lp.getRojo_lab();    
            case 13: return lp.getPartido_lab();
            case 14: return lp.getYeso_lab();
            case 15: return lp.getHarina_lab();
            case 16: return lp.getValor_kg();
            case 17: return lp.getValor_liquidado();
            case 18: return lp.getValor_compra();
            case 19: return lp.getFomento();
            case 20: return lp.getBolsa();
            case 21: return lp.getRetefuente();
            case 22: return lp.getTotal();    
            default : return "";    
        }
    }
     @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0 : return Date.class;           
            case 1 : return String.class;    
            case 2 : return String.class;    
            case 3 : return String.class;    
            case 4 : return String.class;                    
            default: return Double.class;
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
    public void setLista(Vector <LiquidacionDePaddy> lista) {
        this.lista = lista;
    }
    public LiquidacionDePaddy getRow(int rowIndex){
        return lista.get(rowIndex);
    }    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Maquila;

import sic.Dominio.Core.Documento.Documento;

/**
 *
 * @author FANNY BURGOS
 */
public class CierreDeMaquila extends Documento{ 
    @Override
    public String getAbreviatura() {
        return "CM";
    }

    @Override
    public String getTdocumento() {
        return "Cierre de Maquila";
    }
}

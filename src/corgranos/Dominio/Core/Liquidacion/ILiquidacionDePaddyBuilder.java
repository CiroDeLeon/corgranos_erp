/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Liquidacion;

/**
 *
 * @author FANNY BURGOS
 */
public interface ILiquidacionDePaddyBuilder {
    void setLiquidacionDePaddy(LiquidacionDePaddy lp);
    void setBolsa(boolean bolsa);
    LiquidacionDePaddy BuildLiquidacion();
}

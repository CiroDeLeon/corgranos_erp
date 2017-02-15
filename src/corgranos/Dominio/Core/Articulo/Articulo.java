/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corgranos.Dominio.Core.Articulo;

import sic.Dominio.Core.Cta_T.Cta_T;

/**
 *
 * @author FANNY BURGOS
 */
public class Articulo extends Cta_T{
   private String nombre;
   private String referencia;
   private int cantidadkg;
   private String auxingreso;
   private String auxactivo;
   private String auxcosto;
   private String auxiva;
   private String tipo;
   private String categoria;
   private double preciounitario;
   private double porcentageiva;
   private boolean activo=true;
   private long codigodebarra;
   private String auxempaque;


    

    public Articulo(String nombre, String referencia, int cantidadkg, String auxingreso, String auxactivo, String auxcosto, String tipo, String categoria, double preciounitario, double porcentageiva,long codigodebarra,String auxiva,String auxempaque) {
        this.nombre = nombre;
        this.referencia = referencia;
        this.cantidadkg = cantidadkg;
        this.auxingreso = auxingreso;
        this.auxactivo = auxactivo;
        this.auxcosto = auxcosto;
        this.tipo = tipo;
        this.categoria = categoria;
        this.preciounitario = preciounitario;
        this.porcentageiva = porcentageiva;     
        this.activo = true;
        this.codigodebarra=codigodebarra;
        this.auxiva=auxiva;
        this.auxempaque=auxempaque;
    }

    public Articulo() {
        
    }


    

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the cantidadkg
     */
    public int getCantidadkg() {
        return cantidadkg;
    }

    /**
     * @param cantidadkg the cantidadkg to set
     */
    public void setCantidadkg(int cantidadkg) {
        this.cantidadkg = cantidadkg;
    }

    /**
     * @return the auxingreso
     */
    public String getAuxingreso() {
        return auxingreso;
    }

    /**
     * @param auxingreso the auxingreso to set
     */
    public void setAuxingreso(String auxingreso) {
        this.auxingreso = auxingreso;
    }

    /**
     * @return the auxactivo
     */
    public String getAuxactivo() {
        return auxactivo;
    }

    /**
     * @param auxactivo the auxactivo to set
     */
    public void setAuxactivo(String auxactivo) {
        this.auxactivo = auxactivo;
    }

    /**
     * @return the auxcosto
     */
    public String getAuxcosto() {
        return auxcosto;
    }

    /**
     * @param auxcosto the auxcosto to set
     */
    public void setAuxcosto(String auxcosto) {
        this.auxcosto = auxcosto;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the preciounitario
     */
    public double getPreciounitario() {
        return preciounitario;
    }

    /**
     * @param preciounitario the preciounitario to set
     */
    public void setPreciounitario(double preciounitario) {
        this.preciounitario = preciounitario;
    }

    /**
     * @return the porcentageiva
     */
    public double getPorcentageiva() {
        return porcentageiva;
    }

    /**
     * @param porcentageiva the porcentageiva to set
     */
    public void setPorcentageiva(double porcentageiva) {
        this.porcentageiva = porcentageiva;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String getDescripcion(){
        return this.getNombre()+" "+this.getReferencia();
    }

    /**
     * @return the codigodebarra
     */
    public long getCodigodebarra() {
        return codigodebarra;
    }

    /**
     * @param codigodebarra the codigodebarra to set
     */
    public void setCodigodebarra(long codigodebarra) {
        this.codigodebarra = codigodebarra;
    }

    /**
     * @return the auxiva
     */
    public String getAuxiva() {
        return auxiva;
    }

    /**
     * @param auxiva the auxiva to set
     */
    public void setAuxiva(String auxiva) {
        this.auxiva = auxiva;
    }

    /**
     * @return the auxempaque
     */
    public String getAuxempaque() {
        return auxempaque;
    }

    /**
     * @param auxempaque the auxempaque to set
     */
    public void setAuxempaque(String auxempaque) {
        this.auxempaque = auxempaque;
    }
}

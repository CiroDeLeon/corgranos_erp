/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sic.Dominio.Core.Usuario;

import sic.Dominio.Core.Cta_T.Cta_T;

/**
 *
 * @author FANNY BURGOS
 */
public class Usuario extends Cta_T{
   private Municipio municipio;
   private TipoDocumento tipodocumento;
   private long NoDocumento;
   private String Nombre;
   private String sNombre;
   private String Apellido;
   private String sApellido;
   private String RazonSocial;
   private String SobreNombre;
   private String telefono;
   private String direccion;
   private String correo;
   private String Regimen;
   private String AgenteRetenedor;
   private String digitoDIAN;

    public Usuario(Municipio municipio, TipoDocumento tipodocumento, long NoDocumento, String Nombre, String sNombre, String Apellido, String sApellido, String RazonSocial, String SobreNombre, String telefono, String direccion, String correo, String Regimen, String AgenteRetenedor,String digitoDIAN) {
        this.municipio = municipio;
        this.tipodocumento = tipodocumento;
        this.NoDocumento = NoDocumento;
        this.Nombre = Nombre;
        this.sNombre = sNombre;
        this.Apellido = Apellido;
        this.sApellido = sApellido;
        this.RazonSocial = RazonSocial;
        this.SobreNombre = SobreNombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.Regimen = Regimen;
        this.AgenteRetenedor = AgenteRetenedor;
        this.digitoDIAN=digitoDIAN;
    }

    public Usuario() {
        
    }

    /**
     * @return the municipio
     */
    public Municipio getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the tipodocumento
     */
    public TipoDocumento getTipodocumento() {
        return tipodocumento;
    }

    /**
     * @param tipodocumento the tipodocumento to set
     */
    public void setTipodocumento(TipoDocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    /**
     * @return the NoDocumento
     */
    public long getNoDocumento() {
        return NoDocumento;
    }

    /**
     * @param NoDocumento the NoDocumento to set
     */
    public void setNoDocumento(long NoDocumento) {
        this.NoDocumento = NoDocumento;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the sNombre
     */
    public String getsNombre() {
        return sNombre;
    }

    /**
     * @param sNombre the sNombre to set
     */
    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    /**
     * @return the Apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * @param Apellido the Apellido to set
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    /**
     * @return the sApellido
     */
    public String getsApellido() {
        return sApellido;
    }

    /**
     * @param sApellido the sApellido to set
     */
    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    /**
     * @return the RazonSocial
     */
    public String getRazonSocial() {
        return RazonSocial;
    }

    /**
     * @param RazonSocial the RazonSocial to set
     */
    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }

    /**
     * @return the SobreNombre
     */
    public String getSobreNombre() {
        return SobreNombre;
    }

    /**
     * @param SobreNombre the SobreNombre to set
     */
    public void setSobreNombre(String SobreNombre) {
        this.SobreNombre = SobreNombre;
    }

    /**
     * @return the Regimen
     */
    public String getRegimen() {
        return Regimen;
    }

    /**
     * @param Regimen the Regimen to set
     */
    public void setRegimen(String Regimen) {
        this.Regimen = Regimen;
    }

    /**
     * @return the AgenteRetenedor
     */
    public String getAgenteRetenedor() {
        return AgenteRetenedor;
    }

    /**
     * @param AgenteRetenedor the AgenteRetenedor to set
     */
    public void setAgenteRetenedor(String AgenteRetenedor) {
        this.AgenteRetenedor = AgenteRetenedor;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @Override
    public String getDescripcion(){
        return this.RazonSocial+"".trim()+this.getNombre()+" "+this.getsNombre()+" "+this.getApellido()+" "+this.getsApellido()+" - "+this.getNoDocumento()+""+this.digitoDIAN;
    }

    /**
     * @return the digitoDIAN
     */
    public String getDigitoDIAN() {
        return digitoDIAN;
    }

    /**
     * @param digitoDIAN the digitoDIAN to set
     */
    public void setDigitoDIAN(String digitoDIAN) {
        this.digitoDIAN = digitoDIAN;
    }
    @Override
    public String toString(){
        return this.RazonSocial+""+this.getNombre()+" "+this.getsNombre()+" "+this.getApellido()+" "+this.getsApellido()+"  ("+this.getSobreNombre()+")";
    }
    public String NombreCompleto(){
        return this.RazonSocial+""+this.getNombre()+" "+this.getsNombre()+" "+this.getApellido()+" "+this.getsApellido();
    }
    public String getNoDocumentoCompleto(){
        if(digitoDIAN!=null && digitoDIAN.trim().equals("")==false ){
           return this.NoDocumento+"-"+this.digitoDIAN;
        }else{
           return ""+this.NoDocumento; 
        }
    }
    public String getUbicacionCompleta(){
        return this.direccion+" "+this.municipio.toString();
    }
    public boolean isAutoretenedor(){
        return this.AgenteRetenedor.toUpperCase().equals("AUTORETENEDOR");
    }
}

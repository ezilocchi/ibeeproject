/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.actividad;

/**
 *
 * @author Fede
 */
public class DatoDeRecoleccion {

    private String codigoTipoActividad;
    private String codigoTipoTarea;
    private String codigo;
    private String nombreDato;
    private TipoDato tipoDato;
    private String valoresPermitidos;
    private String extension;

    public DatoDeRecoleccion()
    {
        this.tipoDato = new TipoDato();
    }
    /**
     * @return the nombreDato
     */
    public String getNombreDato() {
        return nombreDato;
    }

    /**
     * @param nombreDato the nombreDato to set
     */
    public void setNombreDato(String nombreDato) {
        this.nombreDato = nombreDato;
    }

    /**
     * @return the tipoDato
     */
//    public TipoDato getTipoDato() {
//        return tipoDato;
//    }

    /**
     * @param tipoDato the tipoDato to set
     */
//    public void setTipoDato(TipoDato tipoDato) {
//        this.tipoDato = tipoDato;
//    }

    /**
     * @return the valoresPermitidos
     */
    public String getValoresPermitidos() {
        return valoresPermitidos;
    }

    /**
     * @param valoresPermitidos the valoresPermitidos to set
     */
    public void setValoresPermitidos(String valoresPermitidos) {
        this.valoresPermitidos = valoresPermitidos;
    }

    /**
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codigoTipoActividad
     */
    public String getCodigoTipoActividad() {
        return codigoTipoActividad;
    }

    /**
     * @param codigoTipoActividad the codigoTipoActividad to set
     */
    public void setCodigoTipoActividad(String codigoTipoActividad) {
        this.codigoTipoActividad = codigoTipoActividad;
    }

    /**
     * @return the codigoTipoTarea
     */
    public String getCodigoTipoTarea() {
        return codigoTipoTarea;
    }

    /**
     * @param codigoTipoTarea the codigoTipoTarea to set
     */
    public void setCodigoTipoTarea(String codigoTipoTarea) {
        this.codigoTipoTarea = codigoTipoTarea;
    }

    /**
     * @return the tipoDato
     */
    public TipoDato getTipoDato() {
        return tipoDato;
    }

    /**
     * @param tipoDato the tipoDato to set
     */
    public void setTipoDato(TipoDato tipoDato) {
        this.tipoDato = tipoDato;
    }

}

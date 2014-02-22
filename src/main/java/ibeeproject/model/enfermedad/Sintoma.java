/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.enfermedad;

/**
 *
 * @author Fede 
 */
public class Sintoma {

    private int numero;
    private Enfermedad enfermedad;
    private String denominacion;
    private String descripcion;
    private String observaciones;

    public Sintoma() {
        //this.enfermedad = new Enfermedad();
    }
    /**
     * @return the numeroSintoma
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numeroSintoma the numeroSintoma to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the denominacion
     */
    public String getDenominacion() {
        return denominacion;
    }

    /**
     * @param denominacion the denominacion to set
     */
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    /**
     * @return the enfermedad
     */
    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    /**
     * @param enfermedad the enfermedad to set
     */
    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }


}

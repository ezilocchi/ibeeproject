/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.enfermedad;

/**
 *
 * @author Fede 
 */
public class Tratamiento {

    private int numero;
    private String denominacion;
    private String descripcion;
    private double costeo;

    public Tratamiento() {
        
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
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
     * @return the costeo
     */
    public double getCosteo() {
        return costeo;
    }

    /**
     * @param costeo the costeo to set
     */
    public void setCosteo(double costeo) {
        this.costeo = Math.round(costeo);
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

}

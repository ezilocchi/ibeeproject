/*
 * Enfermedad.java
 *
 * Created on 01-jul-2009, 19:36:04
 * Copyright farias.facundo
 */

package ibeeproject.model.enfermedad;

import java.util.ArrayList;

public class Enfermedad {

    private int numero;
    private String denominacion;
    private String duracionAprox;
    private String descripcion;
    private String observaciones;
    private Criticidad criticidad;
    private ArrayList<Sintoma> sintomas;
    private ArrayList<Tratamiento> tratamientos;

    public Enfermedad()
    {
        this.criticidad = new Criticidad();
        this.sintomas = new ArrayList();
        this.tratamientos = new ArrayList();
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
     * @return the duracionAprox
     */
    public String getDuracionAprox() {
        return duracionAprox;
    }

    /**
     * @param duracionAprox the duracionAprox to set
     */
    public void setDuracionAprox(String duracionAprox) {
        this.duracionAprox = duracionAprox;
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
     * @return the criticidad
     */
    public Criticidad getCriticidad() {
        return criticidad;
    }

    /**
     * @param criticidad the criticidad to set
     */
    public void setCriticidad(Criticidad criticidad) {
        this.criticidad = criticidad;
    }

    /**
     * @return the sintomas
     */
    public ArrayList<Sintoma> getSintomas() {
        return sintomas;
    }

    /**
     * @param sintomas the sintomas to set
     */
    public void setSintomas(ArrayList<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }

    /**
     * @return the tratamientos
     */
    public ArrayList<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    /**
     * @param tratamientos the tratamientos to set
     */
    public void setTratamientos(ArrayList<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.monitoreo;

import java.util.Date;

/**
 *
 * @author carranza.matias
 */
public class Alarma {
    private int numero;
    private String denominacion;
    private String descripcion;
    private String origen;
    private HistorialAlarma historialAlarma;
    private Atributo atributo;
    private Date fechaInicioMedicion;
    private Date fechaFinMedicion;
    private Date fechaCreacion;
    private String cotaSuperiorAtributo;
    private String cotaInferiorAtributo;

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
     * @return the historialAlarma
     */
    public HistorialAlarma getHistorialAlarma() {
        return historialAlarma;
    }

    /**
     * @param historialAlarma the historialAlarma to set
     */
    public void setHistorialAlarma(HistorialAlarma historialAlarma) {
        this.historialAlarma = historialAlarma;
    }

    /**
     * @return the atributo
     */
    public Atributo getAtributo() {
        return atributo;
    }

    /**
     * @param atributo the atributo to set
     */
    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    /**
     * @return the fechaInicioMedicion
     */
    public Date getFechaInicioMedicion() {
        return fechaInicioMedicion;
    }

    /**
     * @param fechaInicioMedicion the fechaInicioMedicion to set
     */
    public void setFechaInicioMedicion(Date fechaInicioMedicion) {
        this.fechaInicioMedicion = fechaInicioMedicion;
    }

    /**
     * @return the fechaFinMedicion
     */
    public Date getFechaFinMedicion() {
        return fechaFinMedicion;
    }

    /**
     * @param fechaFinMedicion the fechaFinMedicion to set
     */
    public void setFechaFinMedicion(Date fechaFinMedicion) {
        this.fechaFinMedicion = fechaFinMedicion;
    }

    /**
     * @return the cotaSuperiorAtributo
     */
    public String getCotaSuperiorAtributo() {
        return cotaSuperiorAtributo;
    }

    /**
     * @param cotaSuperiorAtributo the cotaSuperiorAtributo to set
     */
    public void setCotaSuperiorAtributo(String cotaSuperiorAtributo) {
        this.cotaSuperiorAtributo = cotaSuperiorAtributo;
    }

    /**
     * @return the cotaInferiorAtributo
     */
    public String getCotaInferiorAtributo() {
        return cotaInferiorAtributo;
    }

    /**
     * @param cotaInferiorAtributo the cotaInferiorAtributo to set
     */
    public void setCotaInferiorAtributo(String cotaInferiorAtributo) {
        this.cotaInferiorAtributo = cotaInferiorAtributo;
    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }



}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.zona;

import java.util.ArrayList;

/**
 *
 * @author Fede 
 */
public class Flora {

    private int idFlora;
    private String denominacion;
    private String observaciones;
    private String descripcion;
    private TipoFlora tipoFlora;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public int getIdFlora() {
        return idFlora;
    }

    public void setIdFlora(int idFlora) {
        this.idFlora = idFlora;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoFlora getTipoFlora() {
        return tipoFlora;
    }

    public void setTipoFlora(TipoFlora tipoFlora) {
        this.tipoFlora = tipoFlora;
    }

}

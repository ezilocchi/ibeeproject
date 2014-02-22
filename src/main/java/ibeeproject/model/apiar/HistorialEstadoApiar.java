/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.apiar;

import java.util.Date;

/**
 *
 * @author farias.facundo
 */
public class HistorialEstadoApiar {

    private int idApiar;
    private Date fecha;
    private EstadoApiar estado;
    private String observaciones;

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the estado
     */
    public EstadoApiar getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoApiar estado) {
        this.estado = estado;
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
     * @return the idApiar
     */
    public int getIdApiar() {
        return idApiar;
    }

    /**
     * @param idApiar the idApiar to set
     */
    public void setIdApiar(int idApiar) {
        this.idApiar = idApiar;
    }

}

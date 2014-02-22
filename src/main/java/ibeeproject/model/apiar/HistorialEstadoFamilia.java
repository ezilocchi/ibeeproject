/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.apiar;

import ibeeproject.model.familia.EstadoFamilia;
import java.util.Date;

/**
 *
 * @author farias.facundo
 */
public class HistorialEstadoFamilia {

    private int numeroFamilia;
    private Date fecha;
    private EstadoFamilia estado;
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
    public EstadoFamilia getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoFamilia estado) {
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
     * @return the numeroFamilia
     */
    public int getNumeroFamilia() {
        return numeroFamilia;
    }

    /**
     * @param numeroFamilia the numeroFamilia to set
     */
    public void setNumeroFamilia(int numeroFamilia) {
        this.numeroFamilia = numeroFamilia;
    }

}

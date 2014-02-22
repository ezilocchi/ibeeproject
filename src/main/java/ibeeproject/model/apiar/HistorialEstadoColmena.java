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
public class HistorialEstadoColmena {

    private int numeroColmena;
    private Date fecha;
    private EstadoColmena estado;
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
    public EstadoColmena getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoColmena estado) {
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
     * @return the numeroColmena
     */
    public int getNumeroColmena() {
        return numeroColmena;
    }

    /**
     * @param numeroColmena the numeroColmena to set
     */
    public void setNumeroColmena(int numeroColmena) {
        this.numeroColmena = numeroColmena;
    }

}

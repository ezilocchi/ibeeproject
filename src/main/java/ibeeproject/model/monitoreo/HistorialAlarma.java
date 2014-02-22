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
public class HistorialAlarma {
    private EstadoAlarma estadoAlarma;
    private Date fecha;
    private Date hora;
    private String solucion;

    /**
     * @return the estadoAlarma
     */
    public EstadoAlarma getEstadoAlarma() {
        return estadoAlarma;
    }

    /**
     * @param estadoAlarma the estadoAlarma to set
     */
    public void setEstadoAlarma(EstadoAlarma estadoAlarma) {
        this.estadoAlarma = estadoAlarma;
    }

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
     * @return the hora
     */
    public Date getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Date hora) {
        this.hora = hora;
    }

    /**
     * @return the solucion
     */
    public String getSolucion() {
        return solucion;
    }

    /**
     * @param solucion the solucion to set
     */
    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

}

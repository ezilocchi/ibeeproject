/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.zona;

import java.util.Date;

/**
 *
 * @author farias.facundo
 */
public class Mensaje {
    private int idMensaje;
    private String texto;
    private Date fecha;
    private Alarma alarma;

    public Mensaje() {
        this.alarma = new Alarma();
    }

    /**
     * @return the idMensaje
     */
    public int getIdMensaje() {
        return idMensaje;
    }

    /**
     * @param idMensaje the idMensaje to set
     */
    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
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
     * @return the alarma
     */
    public Alarma getAlarma() {
        return alarma;
    }

    /**
     * @param alarma the alarma to set
     */
    public void setAlarma(Alarma alarma) {
        this.alarma = alarma;
    }

}

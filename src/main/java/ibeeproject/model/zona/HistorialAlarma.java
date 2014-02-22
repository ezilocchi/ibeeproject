/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.zona;

import java.util.Date;

/**
 *
 * @author carranza.matias
 */
public class HistorialAlarma {
    private int numero;
    private int idAlarma;
    private Date fecha;
    private float valor;
    private Criticidad criticidad;
    private boolean acciones;

    public HistorialAlarma() {
        this.criticidad = new Criticidad();
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
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
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
     * @return the idAlarma
     */
    public int getIdAlarma() {
        return idAlarma;
    }

    /**
     * @param idAlarma the idAlarma to set
     */
    public void setIdAlarma(int idAlarma) {
        this.idAlarma = idAlarma;
    }

    /**
     * @return the acciones
     */
    public boolean isAcciones() {
        return acciones;
    }

    /**
     * @param acciones the acciones to set
     */
    public void setAcciones(boolean acciones) {
        this.acciones = acciones;
    }


}

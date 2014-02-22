/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.zona;
import ibeeproject.model.persona.Cargo;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fede 
 */
public class Alarma {

    private int numero;
    private int idZona;
    private String denominacion;
    private String descripcion;
    private String origen;
    private Date fechaCreacion;
    private Date fechaInicio;
    private Date fechaFin;
    private Cargo cargo;
    private boolean activado;
    private double  rangoDesde;
    private double rangoHasta;
    private ArrayList <HistorialAlarma> historialAlarma;

    public Alarma() {
        this.cargo = new Cargo();
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
     * @return the idZona
     */
    public int getIdZona() {
        return idZona;
    }

    /**
     * @param idZona the idZona to set
     */
    public void setIdZona(int idZona) {
        this.idZona = idZona;
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

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the activado
     */
    public boolean isActivado() {
        return activado;
    }

    /**
     * @param activado the activado to set
     */
    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    /**
     * @return the rangoDesde
     */
    public double getRangoDesde() {
        return rangoDesde;
    }

    /**
     * @param rangoDesde the rangoDesde to set
     */
    public void setRangoDesde(double rangoDesde) {
        this.rangoDesde = rangoDesde;
    }

    /**
     * @return the rangoHasta
     */
    public double getRangoHasta() {
        return rangoHasta;
    }

    /**
     * @param rangoHasta the rangoHasta to set
     */
    public void setRangoHasta(double rangoHasta) {
        this.rangoHasta = rangoHasta;
    }

    /**
     * @return the historialAlarma
     */
    public ArrayList<HistorialAlarma> getHistorialAlarma() {
        return historialAlarma;
    }

    /**
     * @param historialAlarma the historialAlarma to set
     */
    public void setHistorialAlarma(ArrayList<HistorialAlarma> historialAlarma) {
        this.historialAlarma = historialAlarma;
    }

    public String getCriticidad() {
        ArrayList historial = getHistorialAlarma();
        if (historial.size() == 0) {
            return "Baja";
        }
        return this.historialAlarma.get(this.historialAlarma.size() - 1).getCriticidad().getDenominacion();
    }

    /**
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

}

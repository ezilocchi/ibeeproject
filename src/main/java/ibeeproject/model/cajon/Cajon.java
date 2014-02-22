/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.cajon;

import ibeeproject.model.persona.Empleado;
import java.util.Date;

/**
 *
 * @author Fede 
 */
public class Cajon implements Cloneable {

    private int nroCajon;
    private String descripcion;
    private EstadoCajon estado;
    private TipoCajon tipoCajon;
    private Empleado empleado;
    private Date fechaFabricacion;
    private String observaciones;
    private int cantidadAlzas;
    private int marcosPorAlza;

    public Cajon () {
        this.empleado = new Empleado();
        this.estado = new EstadoCajon();
        this.tipoCajon = new TipoCajon();
    }

    /**
     * @return the nroCajon
     */
    public int getNroCajon() {
        return nroCajon;
    }

    /**
     * @param nroCajon the nroCajon to set
     */
    public void setNroCajon(int nroCajon) {
        this.nroCajon = nroCajon;
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
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the fechaFabricacion
     */
    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    /**
     * @param fechaFabricacion the fechaFabricacion to set
     */
    public void setFechaFabricacion(Date fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
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
     * @return the estado
     */
    public EstadoCajon getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoCajon estado) {
        this.estado = estado;
    }

        /**
     * @return the estado
     */
    public String getDescripcionEstado() {
        return estado.getEstado();
    }

    /**
     * @param estado the estado to set
     */
    public void setDescripcionEstado(String estado) {
        this.estado.setEstado(estado);
    }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            // No deberia ocurrir
        }
        return clone;
    }


    /**
     * @return the cantidadAlzas
     */
    public int getCantidadAlzas() {
        return cantidadAlzas;
    }

    /**
     * @param cantidadAlzas the cantidadAlzas to set
     */
    public void setCantidadAlzas(int cantidadAlzas) {
        this.cantidadAlzas = cantidadAlzas;
    }

    /**
     * @return the marcosPorAlza
     */
    public int getMarcosPorAlza() {
        return marcosPorAlza;
    }

    /**
     * @param marcosPorAlza the marcosPorAlza to set
     */
    public void setMarcosPorAlza(int marcosPorAlza) {
        this.marcosPorAlza = marcosPorAlza;
    }

    /**
     * @return the tipoCajon
     */
    public TipoCajon getTipoCajon() {
        return tipoCajon;
    }

    /**
     * @param tipoCajon the tipoCajon to set
     */
    public void setTipoCajon(TipoCajon tipoCajon) {
        this.tipoCajon = tipoCajon;
    }
}

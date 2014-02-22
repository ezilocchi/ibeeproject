/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.apiar;

import java.util.Date;

/**
 *
 * @author Fede 
 */
public class Layout {

    private String denominacion;
    private String disenio;
    private String ventajas;
    private String observaciones;
    private Date fechaCreacion = new Date();
    private int idLayout;
    private int largo;
    private int ancho;
    private EstadoLayout asignado;

    public EstadoLayout getAsignado() {
        return asignado;
    }

    public void setAsignado(EstadoLayout asignado) {
        this.asignado = asignado;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getIdLayout() {
        return idLayout;
    }

    public void setIdLayout(int idLayout) {
        this.idLayout = idLayout;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getDisenio() {
        return disenio;
    }

    public void setDisenio(String disenio) {
        this.disenio = disenio;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getVentajas() {
        return ventajas;
    }

    public void setVentajas(String ventajas) {
        this.ventajas = ventajas;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.model.actividad;

import ibeeproject.model.soporte.UtilFecha;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fede 
 */
public class Actividad {

    private int numero;
    private String denominacion;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaAnulacion;
    private Date fechaEsperadaDeCierre;
    private EstadoActividad estado;
    private String observaciones;
    private TipoActividad tipoActividad;
    private ArrayList<Tarea> tareas;

    public Actividad()
    {
        this.estado = new EstadoActividad();
        this.tipoActividad = new TipoActividad();
        this.tareas = new ArrayList();
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
     * @return the tipoActividad
     */
    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    /**
     * @param tipoActividad the tipoActividad to set
     */
    public void setTipoActividad(TipoActividad tipoActividad) {
        this.tipoActividad = tipoActividad;
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
     * @return the estado
     */
    public EstadoActividad getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoActividad estado) {
        this.estado = estado;
    }

    /**
     * Método para calcular como están las actividades (icono)
     */
    public String getIcon() {
        String icon = null;
        int dias = this.diasDeCumplimiento();
        if (dias < 0) {
            icon = "/resources/icons/accept.png";
        }
        if (dias == 0) {
            icon = "/resources/icons/error.png";
        }
        if (dias > 0) {
            icon = "/resources/icons/exclamation.png";
        }
        return icon;
    }

     /**
     * Método para calcular como están las actividades (descripcion)
     */
    public String getDescrip() {
        String descrip = null;
        int dias = this.diasDeCumplimiento();
        if (dias < 0) {
            descrip = "Actividad dentro de Cumplimiento";
        }
        if (dias == 0) {
            descrip = "En día de cumplimiento";
        }
        if (dias > 0) {
            descrip = "Actividad fuera de cumplimiento";
        }
        return descrip;
    }

    public int diasDeCumplimiento() {
        if (this.getFechaInicio() != null && this.getFechaFin() != null) {
            return UtilFecha.fechasDiferenciaEnDias(this.getFechaEsperadaDeCierre(), this.getFechaFin());
        } else {
            return 0;
        }
    }

    /**
     * @return the fechaEsperadaDeCierre
     */
    public Date getFechaEsperadaDeCierre() {
        return fechaEsperadaDeCierre;
    }

    /**
     * @param fechaEsperadaDeCierre the fechaEsperadaDeCierre to set
     */
    public void setFechaEsperadaDeCierre(Date fechaEsperadaDeCierre) {
        this.fechaEsperadaDeCierre = fechaEsperadaDeCierre;
    }

    /**
     * @return the tareas
     */
    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    /**
     * @param tareas the tareas to set
     */
    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }

    /**
     * @return the fechaAnulacion
     */
    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    /**
     * @param fechaAnulacion the fechaAnulacion to set
     */
    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    /**
     *  Devuelve true si está Finalizada
     */
    public boolean isFinalizada() {
        if (this.getEstado().getNumero()==5)
            return true;
        else
            return false;
    }

}

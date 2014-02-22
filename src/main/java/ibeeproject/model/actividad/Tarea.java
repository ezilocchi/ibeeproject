/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.actividad;

import ibeeproject.model.apiar.Apiar;
import ibeeproject.model.apiar.Colmena;
import ibeeproject.model.apiar.Layout;
import ibeeproject.model.cajon.Cajon;
import ibeeproject.model.enfermedad.Enfermedad;
import ibeeproject.model.enfermedad.Sintoma;
import ibeeproject.model.enfermedad.Tratamiento;
import ibeeproject.model.familia.Familia;
import ibeeproject.model.persona.Empleado;
import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.model.zona.Alarma;
import ibeeproject.model.zona.Zona;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fede 
 */
public class Tarea {

    private int idTarea;
    private int idActividad;
    private String descripcion;
    private EstadoTarea estado;
    private Date fechaRealizacion;
    private Date fechaPrevista;
    private String observaciones;
    private Empleado empleado;
    private TipoTarea tipoTarea;
    private Apiar apiar;
    private Colmena colmena;
    private Familia familia;
    private Cajon cajon;
    private Sintoma sintoma;
    private Tratamiento tratamiento;
    private Enfermedad enfermedad;
    private Layout layout;
    private Zona zona;
    private Alarma alarma;
    private String descripcionRealizacion;
    private int cantidadHoras;
    private double cantidadMiel;
    private ArrayList<RecoleccionDato> detalleTarea;

    public Tarea()
    {
        this.estado = new EstadoTarea();
        this.empleado = new Empleado();
        this.tipoTarea = new TipoTarea();
        this.apiar = new Apiar();
        this.colmena = new Colmena();
        this.familia = new Familia();
        this.cajon = new Cajon();
        this.sintoma = new Sintoma();
        this.tratamiento = new Tratamiento();
        this.enfermedad = new Enfermedad();
        this.layout = new Layout();
        this.zona = new Zona();
        this.alarma = new Alarma();
        this.detalleTarea = new ArrayList();
    }
    /**
     * @return the idTarea
     */
    public int getIdTarea() {
        return idTarea;
    }

    /**
     * @param idTarea the idTarea to set
     */
    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    /**
     * @return the idActividad
     */
    public int getIdActividad() {
        return idActividad;
    }

    /**
     * @param idActividad the idActividad to set
     */
    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
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
     * @return the estado
     */
    public EstadoTarea getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    /**
     * @return the fechaRealizacion
     */
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    /**
     * @param fechaRealizacion the fechaRealizacion to set
     */
    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    /**
     * @return the fechaPrevista
     */
    public Date getFechaPrevista() {
        return fechaPrevista;
    }

    /**
     * @param fechaPrevista the fechaPrevista to set
     */
    public void setFechaPrevista(Date fechaPrevista) {
        this.fechaPrevista = fechaPrevista;
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
     * @return the tipoTarea
     */
    public TipoTarea getTipoTarea() {
        return tipoTarea;
    }

    /**
     * @param tipoTarea the tipoTarea to set
     */
    public void setTipoTarea(TipoTarea tipoTarea) {
        this.tipoTarea = tipoTarea;
    }

    /**
     * @return the colmena
     */
    public Colmena getColmena() {
        return colmena;
    }

    /**
     * @param colmena the colmena to set
     */
    public void setColmena(Colmena colmena) {
        this.colmena = colmena;
    }

    /**
     * @return the familia
     */
    public Familia getFamilia() {
        return familia;
    }

    /**
     * @param familia the familia to set
     */
    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    /**
     * @return the cajon
     */
    public Cajon getCajon() {
        return cajon;
    }

    /**
     * @param cajon the cajon to set
     */
    public void setCajon(Cajon cajon) {
        this.cajon = cajon;
    }

    /**
     * @return the sintoma
     */
    public Sintoma getSintoma() {
        return sintoma;
    }

    /**
     * @param sintoma the sintoma to set
     */
    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }

    /**
     * @return the tratamiento
     */
    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    /**
     * @param tratamiento the tratamiento to set
     */
    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * @return the enfermedad
     */
    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    /**
     * @param enfermedad the enfermedad to set
     */
    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    /**
     * @return the apiar
     */
    public Apiar getApiar() {
        return apiar;
    }

    /**
     * @param apiar the apiar to set
     */
    public void setApiar(Apiar apiar) {
        this.apiar = apiar;
    }

    /**
     * @return the layout
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * @param layout the layout to set
     */
    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    /**
     * @return the zona
     */
    public Zona getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(Zona zona) {
        this.zona = zona;
    }

     /**
     * Metodo para calcular como estan las actividades (icono)
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
     * Metodo para calcular como estan las actividades (descripcion)
     */
    public String getDescrip() {
        String descrip = null;
        int dias = this.diasDeCumplimiento();
        if (dias < 0) {
            descrip = "Tarea dentro de Cumplimiento";
        }
        if (dias == 0) {
            descrip = "En dia de cumplimiento";
        }
        if (dias > 0) {
            descrip = "Tarea fuera de cumplimiento";
        }
        return descrip;
    }

    public int diasDeCumplimiento() {
        if (this.getFechaPrevista() != null && this.getFechaRealizacion() != null) {
            return UtilFecha.fechasDiferenciaEnDias(this.getFechaRealizacion(), this.getFechaPrevista());
        } else {
            return 0;
        }
    }

    public String getTitle()
    {
        String descrip = null;
        if (this.getDescripcion() == null)
        {
            descrip = "Falta configurar";
        }
        else
        {
            descrip = this.getDescripcion();
        }
        return descrip;
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

    /**
     * @return the detalleTarea
     */
    public ArrayList<RecoleccionDato> getDetalleTarea() {
        return detalleTarea;
    }

    /**
     * @param detalleTarea the detalleTarea to set
     */
    public void setDetalleTarea(ArrayList<RecoleccionDato> detalleTarea) {
        this.setDetalleTarea(detalleTarea);
    }

    /**
     * @return the descripcionRealizacion
     */
    public String getDescripcionRealizacion() {
        return descripcionRealizacion;
    }

    /**
     * @param descripcionRealizacion the descripcionRealizacion to set
     */
    public void setDescripcionRealizacion(String descripcionRealizacion) {
        this.descripcionRealizacion = descripcionRealizacion;
    }

    /**
     * @return the cantidadHoras
     */
    public int getCantidadHoras() {
        return cantidadHoras;
    }

    /**
     * @param cantidadHoras the cantidadHoras to set
     */
    public void setCantidadHoras(int cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    /**
     * @return the cantidadMiel
     */
    public double getCantidadMiel() {
        return cantidadMiel;
    }

    /**
     * @param cantidadMiel the cantidadMiel to set
     */
    public void setCantidadMiel(double cantidadMiel) {
        this.cantidadMiel = cantidadMiel;
    }



}

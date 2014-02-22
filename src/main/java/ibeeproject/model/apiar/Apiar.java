/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.apiar;

import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.model.zona.Zona;
import ibeeproject.model.apiar.EstadoApiar;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Fede 
 */
public class Apiar {

    private int idApiar;
    private String denominacion;
    private Date fechaCreacion;
    private Date fechaAlta;
    private Date fechaBaja;
    private EstadoApiar estado;
    private Layout layout;
    private Ubicacion ubicacion;
    private Zona zona;
    private ArrayList<Colmena> colmenasAsignadas;

    public Apiar() {
        this.ubicacion = new Ubicacion();
        this.zona = new Zona();
        this.layout = new Layout();
        this.colmenasAsignadas = new ArrayList();
    }
    
    public String getDenominacion() {
        return denominacion;
    }

    public java.util.Date getFechaAlta() {
        return UtilFecha.convertiFecha(this.fechaAlta);
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public void setFechaAlta(java.util.Date fechaAlta) {
        this.fechaAlta = UtilFecha.convertiFecha(fechaAlta);
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdApiar() {
        return idApiar;
    }

    public void setIdApiar(int idApiar) {
        this.idApiar = idApiar;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    /**
     * @return the colmenasAsignadas
     */
    public ArrayList<Colmena> getColmenasAsignadas() {
        return colmenasAsignadas;
    }

    /**
     * @param colmenasAsignadas the colmenasAsignadas to set
     */
    public void setColmenasAsignadas(ArrayList<Colmena> colmenasAsignadas) {
        this.colmenasAsignadas = colmenasAsignadas;
    }

    public EstadoApiar getEstado() {
        return estado;
    }

    public void setEstado(EstadoApiar estado) {
        this.estado = estado;
    }

    


}

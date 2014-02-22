/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.zona;

/**
 *
 * @author Fede 
 */
public class Agroquimico {

    private int idAgroquimico;
    private String denominacion;
    private String descripcion;
    private String observaciones;
    private TipoAgroquimico tipoAgroquimico;

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String demonimacion) {
        this.denominacion = demonimacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdAgroquimico() {
        return idAgroquimico;
    }

    public void setIdAgroquimico(int idAgroquimico) {
        this.idAgroquimico = idAgroquimico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoAgroquimico getTipoAgroquimico() {
        return tipoAgroquimico;
    }

    public void setTipoAgroquimico(TipoAgroquimico tipoAgroquimico) {
        this.tipoAgroquimico = tipoAgroquimico;
    }

}

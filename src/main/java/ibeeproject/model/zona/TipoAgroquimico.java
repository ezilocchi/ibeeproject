/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.zona;

/**
 *
 * @author Fede 
 */
public class TipoAgroquimico {

    private int idTipoAgroquimico;
    private String denominacion;
    private String descripcion;

    public TipoAgroquimico() {
        this.idTipoAgroquimico = -1;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTipoAgroquimico() {
        return idTipoAgroquimico;
    }

    public void setIdTipoAgroquimico(int idTipoAgroquimico) {
        this.idTipoAgroquimico = idTipoAgroquimico;
    }
    
}

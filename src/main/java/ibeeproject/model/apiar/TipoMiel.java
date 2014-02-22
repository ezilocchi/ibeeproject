/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.apiar;

/**
 *
 * @author Fede 
 */
public class TipoMiel {

    private int idTipoMiel;
    private String denominacion;

    public TipoMiel() {
        
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

    public int getIdTipoMiel() {
        return idTipoMiel;
    }

    public void setIdTipoMiel(int idTipoMiel) {
        this.idTipoMiel = idTipoMiel;
    }
    private String descripcion;

}

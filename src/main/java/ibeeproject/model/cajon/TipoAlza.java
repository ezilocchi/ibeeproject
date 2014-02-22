/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.cajon;

/**
 *
 * @author Fede 
 */
public class TipoAlza {

    private int idTipoAlza;
    private String denominacion;
    private String descripcion;

    /**
     * @return the idTipoAlza
     */
    public int getIdTipoAlza() {
        return idTipoAlza;
    }

    /**
     * @param idTipoAlza the idTipoAlza to set
     */
    public void setIdTipoAlza(int idTipoAlza) {
        this.idTipoAlza = idTipoAlza;
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

}

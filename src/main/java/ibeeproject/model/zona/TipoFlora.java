/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.zona;

/**
 *
 * @author Fede 
 */
public class TipoFlora {

    private int idTipoFlora=-1;
    private String denonimacion;
    private String descripcion;
    private String caracteristicas;

    public TipoFlora() {
        this.idTipoFlora = -1;
        this.denonimacion = "";
        this.descripcion = "";
        this.caracteristicas = "";
    }


    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getDenonimacion() {
        return denonimacion;
    }

    public void setDenonimacion(String demonimacion) {
        this.denonimacion = demonimacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTipoFlora() {
        return idTipoFlora;
    }

    public void setIdTipoFlora(int idTipoFlora) {
        this.idTipoFlora = idTipoFlora;
    }

}

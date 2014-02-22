/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.familia;

/**
 *
 * @author Fede 
 */
public class Especie {

    private int idEspecie;
    private String denominacion;
    private String descripcion;

    public Especie() {
        
    }

    /**
     * @return the demonimacion
     */
    public String getDenominacion() {
        return denominacion;
    }

    /**
     * @param demonimacion the demonimacion to set
     */
    public void setDenominacion(String demonimacion) {
        this.denominacion = demonimacion;
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
     * @return the idEspecie
     */
    public int getIdEspecie() {
        return idEspecie;
    }

    /**
     * @param idEspecie the idEspecie to set
     */
    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

}

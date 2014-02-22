/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.persona;

import java.io.Serializable;

/**
 *
 * @author Fede 
 */
public class Recurso implements Serializable {

    private int idRecursos;
    private String nombre;

    /**
     * @return the idRecursos
     */
    public int getIdRecursos() {
        return idRecursos;
    }

    /**
     * @param idRecursos the idRecursos to set
     */
    public void setIdRecursos(int idRecursos) {
        this.idRecursos = idRecursos;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

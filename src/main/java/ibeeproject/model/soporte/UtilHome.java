/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.soporte;

import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class UtilHome {

    private String name;
    private ArrayList detalle;

    public UtilHome(String name)
    {
        this.name = name;
        detalle = new ArrayList();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the detalle
     */
    public ArrayList getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(ArrayList detalle) {
        this.detalle = detalle;
    }

}

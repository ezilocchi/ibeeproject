/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.familia;

/**
 *
 * @author Fede 
 */
public class EstadoFamilia {

    private String denominacion;
    private String descripcion;
    private int idEstadoFamilia;

    public EstadoFamilia() {
        
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
     * @return the idEstadoFamilia
     */
    public int getIdEstadoFamilia() {
        return idEstadoFamilia;
    }

    /**
     * @param idEstadoFamilia the idEstadoFamilia to set
     */
    public void setIdEstadoFamilia(int idEstadoFamilia) {
        this.idEstadoFamilia = idEstadoFamilia;
    }

    /**
     * @return the icono para el estado
     */
    public String getIconEstado() {
        String icon = null;
        if (this.getIdEstadoFamilia() == 1) {
            icon = "/resources/icons/bulb green.png";
        }
        if (this.getIdEstadoFamilia() == 2) {
            icon = "/resources/icons/bulb.png";
        }
        if (this.getIdEstadoFamilia() == 3) {
            icon = "/resources/icons/bulb red.png";
        }
        return icon;
    }

}

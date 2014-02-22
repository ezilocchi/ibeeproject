/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.apiar;

/**
 *
 * @author Fede 
 */
public class EstadoColmena {

    private int numero;
    private String denominacion;
    private String descripcion;

    public EstadoColmena() {
        
    }
    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
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

    /**
     * @return the icono para el estado
     */
    public String getIconEstado() {
        String icon = null;
        if (this.getNumero() == 1) {
            icon = "/resources/icons/bulb.png";
        }
        if (this.getNumero() == 2) {
            icon = "/resources/icons/bulb green.png";
        }
        if (this.getNumero() == 3) {
            icon = "/resources/icons/bulb yellow.png";
        }
        if (this.getNumero() == 4) {
            icon = "/resources/icons/bulb red.png";
        }
        return icon;
    }

}

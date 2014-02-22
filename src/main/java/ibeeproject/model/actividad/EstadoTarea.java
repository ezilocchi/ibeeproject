/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.actividad;

/**
 *
 * @author Fede 
 */
public class EstadoTarea {

    private int numero;
    private String descripcion;

    public EstadoTarea(){

    }

    public EstadoTarea (int estado)
    {
        this.numero = estado;
        if (this.getNumero() == 1) {
            this.descripcion = "Pendiente";
        }
        if (this.getNumero() == 2) {
            this.descripcion = "Asignada";
        }
        if (this.getNumero() == 3) {
            this.descripcion = "En ejecución";
        }
        if (this.getNumero() == 4) {
            this.descripcion = "Anulada";
        }
        if (this.getNumero() == 5) {
            this.descripcion = "Finalizada";
        }
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
     * @return the icono para el estado
     */
    public String getIconEstado() {
        String icon = null;
        if (this.getNumero() == 1) {
            icon = "/resources/icons/bulb.png";
        }
        if (this.getNumero() == 2) {
            icon = "/resources/icons/bulb yellow.png";
        }
        if (this.getNumero() == 3) {
            icon = "/resources/icons/bulb blue.png";
        }
        if (this.getNumero() == 4) {
            icon = "/resources/icons/bulb red.png";
        }
        if (this.getNumero() == 5) {
            icon = "/resources/icons/bulb green.png";
        }
        return icon;
    }

}

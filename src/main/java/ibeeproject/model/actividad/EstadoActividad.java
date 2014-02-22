/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.model.actividad;

/**
 *
 * @author farias.facundo
 */
public class EstadoActividad {

    private int numero;
    private String estado;

    public EstadoActividad()
    {

    }

    public EstadoActividad(int estado)
    {
        this.numero = estado;
        if (this.getNumero() == 1) {
            this.estado = "Pendiente";
        }
        if (this.getNumero() == 2) {
            this.estado = "Asignada";
        }
        if (this.getNumero() == 3) {
            this.estado = "En ejecución";
        }
        if (this.getNumero() == 4) {
            this.estado = "Anulada";
        }
        if (this.getNumero() == 5) {
            this.estado = "Finalizada";
        }
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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
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

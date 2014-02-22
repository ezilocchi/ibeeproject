/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.cajon;

/**
 *
 * @author farias.facundo
 */

public class EstadoCajon {

    private int numero;
    private String estado;

    /** Creates a new instance of EstadoCajon */
    public EstadoCajon() {
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
            icon = "/resources/icons/bulb green.png";
        }
        return icon;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.apiar;

/**
 *
 * @author farias.facundo
 */
public class EstadoApiar {

    private int numero;
    private String estado;

    public EstadoApiar() {
        this.numero=1;
        this.estado="Creado";
    }
    
    public EstadoApiar(int numero) {
        if (numero == 1)
        {
            this.numero=1;
            this.estado="Creado";
        }
        if (numero == 2)
        {
            this.numero=2;
            this.estado="Vigente";
        }
        if (numero == 3)
        {
            this.numero=3;
            this.estado="No Vigente";
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

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
        if (this.getNumero() == 3) {
             icon = "/resources/icons/bulb red.png";
        }
        return icon;
    }
}

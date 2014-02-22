/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.apiar;

/**
 *
 * @author Sharky
 */
public class EstadoLayout {
    private int numero;
    private String estado;

    public EstadoLayout() {
        this.numero=1;
        this.estado="Sin Asignar";
    }

    public EstadoLayout(int numero) {
        if (numero == 1)
        {
            this.numero=1;
            this.estado="Asignado";
        }
        if (numero == 2)
        {
            this.numero=2;
            this.estado="Sin Asignar";
        }
    }

    public boolean getBoolean(){
        if( this.numero == 1)
        return true;
        else
        return false;
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
            icon = "/resources/icons/bulb green.png";
        }
        if (this.getNumero() == 2) {
             icon = "/resources/icons/bulb red.png";
        }
        return icon;
    }
}

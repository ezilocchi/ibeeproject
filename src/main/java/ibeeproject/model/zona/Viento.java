/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.zona;

/**
 *
 * @author CyberShark
 */
public class Viento {

    private Double velocidad;
    private String direccion;

    public Viento(){
        this.velocidad = 0.0;
        this.direccion = "";
    }
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }


}

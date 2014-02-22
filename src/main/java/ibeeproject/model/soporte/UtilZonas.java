/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.soporte;

import ibeeproject.model.apiar.Ubicacion;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class UtilZonas {

    private ArrayList<Ubicacion> puntos = new ArrayList();
    private String denominacion;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public ArrayList<Ubicacion> getPuntos() {
        return puntos;
    }

    public void setPuntos(ArrayList<Ubicacion> puntos) {
        this.puntos = puntos;
    }

}

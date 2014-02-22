/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.apiar;

import com.googlecode.gmaps4jsf.component.point.Point;

/**
 *
 * @author Fede 
 */
public class Ubicacion {

    int idUbicacion;
    private double latitud;
    private double longitud;
    private String observaciones;
    

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }
    public Ubicacion(){
        this.latitud = -1;
        this.longitud = -1;
    }
    
    public Ubicacion(double latitud, double longitud)
    {
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Point getPoint()
    {
        Point p = new Point();
        p.setLatitude(String.valueOf(this.latitud));
        p.setLongitude(String.valueOf(this.longitud));
        return p;
    }

}

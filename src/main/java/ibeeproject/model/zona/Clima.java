/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.zona;

import java.util.Date;

/**
 *
 * @author Fede 
 */
public class Clima {


    private int idZona;
    private int idClima;
    private String localidad; // se lo agrego para ver el top5 del home
    private Date fecha;
    private double temperatura;
    private double humedad;
    private double indiceUV;
    private double presion;
    private Viento viento;

    /**
     * @return the numero
     */
    public int getIdZona() {
        return idZona;
    }

    /**
     * @param numero the numero to set
     */
    public void setIdZona(int idzona) {
        this.idZona = idzona;
    }

    /**
     * @return the idZona
     */
    public int getIdClima() {
        return idClima;
    }

    /**
     * @param idZona the idZona to set
     */
    public void setIdClima(int idClima) {
        this.idClima = idClima;
    }

    /**
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * @param localidad the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the temperatura
     */
    public double getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * @return the humedad
     */
    public double getHumedad() {
        return humedad;
    }

    /**
     * @param humedad the humedad to set
     */
    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

    /**
     * @return the indiceUV
     */
    public double getIndiceUV() {
        return indiceUV;
    }

    /**
     * @param indiceUV the indiceUV to set
     */
    public void setIndiceUV(double indiceUV) {
        this.indiceUV = indiceUV;
    }

    /**
     * @return the presion
     */
    public double getPresion() {
        return presion;
    }

    /**
     * @param presion the presion to set
     */
    public void setPresion(double presion) {
        this.presion = presion;
    }

    /**
     * @return the viento
     */
    public Viento getViento() {
        return viento;
    }

    /**
     * @param viento the viento to set
     */
    public void setViento(Viento viento) {
        this.viento = viento;
    }

}

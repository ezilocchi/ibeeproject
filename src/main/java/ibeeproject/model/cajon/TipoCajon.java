/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.cajon;

/**
 *
 * @author carranza.matias
 */
public class TipoCajon {
    private int idTipoCajon;
    private String denominacion;
    private float tamanoPrimeraAlza;
    private float tamanoAlza;
    private String observaciones;

    /**
     * @return the idTipoCajon
     */
    public int getIdTipoCajon() {
        return idTipoCajon;
    }

    /**
     * @param idTipoCajon the idTipoCajon to set
     */
    public void setIdTipoCajon(int idTipoCajon) {
        this.idTipoCajon = idTipoCajon;
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
     * @return the tamanoPrimeraAlza
     */
    public float getTamanoPrimeraAlza() {
        return tamanoPrimeraAlza;
    }

    /**
     * @param tamanoPrimeraAlza the tamanoPrimeraAlza to set
     */
    public void setTamanoPrimeraAlza(float tamanoPrimeraAlza) {
        this.tamanoPrimeraAlza = tamanoPrimeraAlza;
    }

    /**
     * @return the tamanoAlza
     */
    public float getTamanoAlza() {
        return tamanoAlza;
    }

    /**
     * @param tamanoAlza the tamanoAlza to set
     */
    public void setTamanoAlza(float tamanoAlza) {
        this.tamanoAlza = tamanoAlza;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}

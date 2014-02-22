/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.zona;

import ibeeproject.model.apiar.Ubicacion;
import ibeeproject.persistencia.GestorAgroquimico;
import ibeeproject.persistencia.GestorFlora;
import ibeeproject.persistencia.GestorZona;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.model.SelectItem;

/**
 *
 * @author Fede 
 */
public class Zona {

    private int idZona;
    private String Zona;
    private double latitud;
    private double longitud;
    private String observaciones;
    private Date fechaAlta;
    private Date fechaBaja;
    private EstadoZona estado = new EstadoZona();
    private TipoAgroquimico tipoAgroquimico;
    private Clima clima;
    private TipoFlora tipoFlora = new TipoFlora();
    private ArrayList<Ubicacion> ubicacion;

    public ArrayList<Ubicacion> getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(ArrayList<Ubicacion> ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public Zona()
    {
        tipoFlora = new TipoFlora();
        tipoAgroquimico = new TipoAgroquimico();
        ubicacion = new ArrayList<Ubicacion>();

    }

    public TipoAgroquimico getTipoAgroquimico() {
        return tipoAgroquimico;
    }

    public void setTipoAgroquimico(TipoAgroquimico agroquimico) {
        this.tipoAgroquimico = agroquimico;
    }

    public TipoFlora getTipoFlora() {
        return tipoFlora;
    }

    public void setTipoFlora(TipoFlora flora) {
        this.tipoFlora = flora;
    }

        public String getZona() {
        return Zona;
    }

    public void setZona(String Zona) {
        this.Zona = Zona;
    }

    public EstadoZona getEstado() {
        return estado;
    }

    public void setEstado(EstadoZona estado) {
        this.estado = estado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
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

    /**
     * @return the clima
     */
    public Clima getClima() {
        return clima;
    }

    /**
     * @param clima the clima to set
     */
    public void setClima(Clima clima) {
        this.clima = clima;
    }

}

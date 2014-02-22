/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.model.familia;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fede 
 */
public class Familia implements Cloneable {

    private int nroFamilia;
    private String denominacion;
    private String caracteristicas;
    private Date fechaAlta;
    private String observaciones;
    private Especie especie;
    private EstadoFamilia estado;
    //No se para que es por ende no lo cargo...?
    private ArrayList<EstadoFamilia> historialEstadoGrupo;
    private ArrayList<MiembroFamilia> miembrosFamilia;

    public Familia() {
        this.especie = new Especie();
        this.estado = new EstadoFamilia();
        this.miembrosFamilia = new ArrayList();
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public EstadoFamilia getEstado() {
        return estado;
    }

    public void setEstado(EstadoFamilia estado) {
        this.estado = estado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getNroFamilia() {
        return nroFamilia;
    }

    public void setNroGrupo(int nroFamilia) {
        this.setNroFamilia(nroFamilia);
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the historialEstadoGrupo
     */
    public ArrayList getHistorialEstadoGrupo() {
        return historialEstadoGrupo;
    }

    /**
     * @param historialEstadoGrupo the historialEstadoGrupo to set
     */
    public void setHistorialEstadoGrupo(ArrayList historialEstadoGrupo) {
        this.historialEstadoGrupo = historialEstadoGrupo;
    }

    /**
     * @param nroFamilia the nroFamilia to set
     */
    public void setNroFamilia(int nroFamilia) {
        this.nroFamilia = nroFamilia;
    }

    /**
     * @return the miembrosFamilia
     */
    public ArrayList<MiembroFamilia> getMiembrosFamilia() {
        return miembrosFamilia;
    }

    /**
     * @param miembrosFamilia the miembrosFamilia to set
     */
    public void setMiembrosFamilia(ArrayList<MiembroFamilia> miembrosFamilia) {
        this.miembrosFamilia = miembrosFamilia;
    }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            // No deberia ocurrir
        }
        return clone;
    }

    public Familia cloneFamilia() {
        Familia familia = (Familia) this.clone();
        familia.setMiembrosFamilia((ArrayList<MiembroFamilia>) familia.getMiembrosFamilia().clone());
        return familia;
    }
}

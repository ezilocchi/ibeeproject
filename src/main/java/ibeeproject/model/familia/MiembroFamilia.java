/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.familia;

import java.util.Date;

/**
 *
 * @author Fede 
 */
public class MiembroFamilia  implements Cloneable{

    private int idMiembroFamilia;
    private String denominacion;
    private Date fechaNacimiento;
    private Date fechaAlta;
    private Date fechaBaja;
    private TipoAbeja tipoAbeja;
    private int cantidad;

    public MiembroFamilia()
    {
        this.tipoAbeja=new TipoAbeja();
    }
    
    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the fechaAlta
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta the fechaAlta to set
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return the fechaBaja
     */
    public Date getFechaBaja() {
        return fechaBaja;
    }

    /**
     * @param fechaBaja the fechaBaja to set
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * @return the tipoAbeja
     */
    public TipoAbeja getTipoAbeja() {
        return tipoAbeja;
    }

    /**
     * @param tipoAbeja the tipoAbeja to set
     */
    public void setTipoAbeja(TipoAbeja tipoAbeja) {
        this.tipoAbeja = tipoAbeja;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the idMiembroFamilia
     */
    public int getIdMiembroFamilia() {
        return idMiembroFamilia;
    }

    /**
     * @param idMiembroFamilia the idMiembroFamilia to set
     */
    public void setIdMiembroFamilia(int idMiembroFamilia) {
        this.idMiembroFamilia = idMiembroFamilia;
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

    public MiembroFamilia cloneMiembroFamilia () {
        MiembroFamilia miembroFamilia = (MiembroFamilia) this.clone();
        return miembroFamilia;
    }
}

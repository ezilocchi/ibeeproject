/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.actividad;

import ibeeproject.model.persona.Cargo;
import java.util.ArrayList;

/**
 *
 * @author Fede 
 */
public class TipoActividad {

    private int numero;
    private String codigo;
    private String denominacion;
    private String descripcion;
    private Cargo cargo;
    private ArrayList<TipoTarea> tipoTareas;

    public TipoActividad()
    {
        this.tipoTareas = new ArrayList();
        this.cargo = new Cargo();
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the tipoTareas
     */
    public ArrayList<TipoTarea> getTipoTareas() {
        return tipoTareas;
    }

    /**
     * @param tipoTareas the tipoTareas to set
     */
    public void setTipoTareas(ArrayList<TipoTarea> tipoTareas) {
        this.tipoTareas = tipoTareas;
    }

}

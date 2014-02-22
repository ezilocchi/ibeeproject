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
public class TipoTarea {

    private String codigoTipoActividad;
    private String codigo;
    private String denominacion;
    private String descripcion;
    private ArrayList<DatoDeRecoleccion> detalleTipoTarea;
    private Cargo cargo;
    private boolean usaLayout;
    private boolean usaZona;
    private boolean usaApiar;
    private boolean usaColmena;
    private boolean usaFamilia;
    private boolean usaCajon;
    private boolean usaTratamiento;
    private boolean usaEnfermedad;
    private boolean usaSintoma;
    private boolean usaAlarma;

    public TipoTarea()
    {
        this.detalleTipoTarea = new ArrayList();
        this.cargo = new Cargo();
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
     * @return the usaCajon
     */
    public boolean isUsaCajon() {
        return usaCajon;
    }

    /**
     * @param usaCajon the usaCajon to set
     */
    public void setUsaCajon(boolean usaCajon) {
        this.usaCajon = usaCajon;
    }

    /**
     * @return the usaTratamiento
     */
    public boolean isUsaTratamiento() {
        return usaTratamiento;
    }

    /**
     * @param usaTratamiento the usaTratamiento to set
     */
    public void setUsaTratamiento(boolean usaTratamiento) {
        this.usaTratamiento = usaTratamiento;
    }

    /**
     * @return the usaEnfermedad
     */
    public boolean isUsaEnfermedad() {
        return usaEnfermedad;
    }

    /**
     * @param usaEnfermedad the usaEnfermedad to set
     */
    public void setUsaEnfermedad(boolean usaEnfermedad) {
        this.usaEnfermedad = usaEnfermedad;
    }

    /**
     * @return the usaSintoma
     */
    public boolean isUsaSintoma() {
        return usaSintoma;
    }

    /**
     * @param usaSintoma the usaSintoma to set
     */
    public void setUsaSintoma(boolean usaSintoma) {
        this.usaSintoma = usaSintoma;
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
     * @return the usaFamilia
     */
    public boolean isUsaFamilia() {
        return usaFamilia;
    }

    /**
     * @param usaFamilia the usaFamilia to set
     */
    public void setUsaFamilia(boolean usaFamilia) {
        this.usaFamilia = usaFamilia;
    }

    /**
     * @return the detalleTipoTarea
     */
    public ArrayList<DatoDeRecoleccion> getDetalleTipoTarea() {
        return detalleTipoTarea;
    }

    /**
     * @param detalleTipoTarea the detalleTipoTarea to set
     */
    public void setDetalleTipoTarea(ArrayList<DatoDeRecoleccion> detalleTipoTarea) {
        this.detalleTipoTarea = detalleTipoTarea;
    }

    /**
     * @return the codigoTipoActividad
     */
    public String getCodigoTipoActividad() {
        return codigoTipoActividad;
    }

    /**
     * @param codigoTipoActividad the codigoTipoActividad to set
     */
    public void setCodigoTipoActividad(String codigoTipoActividad) {
        this.codigoTipoActividad = codigoTipoActividad;
    }

    /**
     * @return the usaLayout
     */
    public boolean isUsaLayout() {
        return usaLayout;
    }

    /**
     * @param usaLayout the usaLayout to set
     */
    public void setUsaLayout(boolean usaLayout) {
        this.usaLayout = usaLayout;
    }

    /**
     * @return the usaZona
     */
    public boolean isUsaZona() {
        return usaZona;
    }

    /**
     * @param usaZona the usaZona to set
     */
    public void setUsaZona(boolean usaZona) {
        this.usaZona = usaZona;
    }

    /**
     * @return the usaApiar
     */
    public boolean isUsaApiar() {
        return usaApiar;
    }

    /**
     * @param usaApiar the usaApiar to set
     */
    public void setUsaApiar(boolean usaApiar) {
        this.usaApiar = usaApiar;
    }

    /**
     * @return the usaColmena
     */
    public boolean isUsaColmena() {
        return usaColmena;
    }

    /**
     * @param usaColmena the usaColmena to set
     */
    public void setUsaColmena(boolean usaColmena) {
        this.usaColmena = usaColmena;
    }

    /**
     * @return the usaAlarma
     */
    public boolean isUsaAlarma() {
        return usaAlarma;
    }

    /**
     * @param usaAlarma the usaAlarma to set
     */
    public void setUsaAlarma(boolean usaAlarma) {
        this.usaAlarma = usaAlarma;
    }

     /**
     * @int Cantidad de Booleanos variables
     */
    public int getCantidadChecks() {
        int count = 0;
        if (this.isUsaAlarma()) {
            count++;
        }
        if (this.isUsaLayout()) {
            count++;
        }
        if (this.isUsaZona()) {
            count++;
        }
        if (this.isUsaApiar()) {
            count++;
        }
        if (this.isUsaColmena()) {
            count++;
        }
        if (this.isUsaFamilia()) {
            count++;
        }
        if (this.isUsaCajon()) {
            count++;
        }
        if (this.isUsaTratamiento()) {
            count++;
        }
        if (this.isUsaEnfermedad()) {
            count++;
        }
        if (this.isUsaSintoma()) {
            count++;
        }
        return count;
    }

}
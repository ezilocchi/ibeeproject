/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.familia;

/**
 *
 * @author Fede 
 */
public class TipoAbeja {

    private int idTipoAbeja;
    private String denominacion;
    private String descripcion;
    private String cuidados;
    private String tiempoRecomendado; //nose bien a q se refiere este atributo

    /**
     * @return the demonimacion
     */
    public String getDenominacion() {
        return denominacion;
    }

    /**
     * @param demonimacion the demonimacion to set
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
     * @return the cuidados
     */
    public String getCuidados() {
        return cuidados;
    }

    /**
     * @param cuidados the cuidados to set
     */
    public void setCuidados(String cuidados) {
        this.cuidados = cuidados;
    }

    /**
     * @return the tiempoRecomendado
     */
    public String getTiempoRecomendado() {
        return tiempoRecomendado;
    }

    /**
     * @param tiempoRecomendado the tiempoRecomendado to set
     */
    public void setTiempoRecomendado(String tiempoRecomendado) {
        this.tiempoRecomendado = tiempoRecomendado;
    }

    /**
     * @return the idTipoAbeja
     */
    public int getIdTipoAbeja() {
        return idTipoAbeja;
    }

    /**
     * @param idTipoAbeja the idTipoAbeja to set
     */
    public void setIdTipoAbeja(int idTipoAbeja) {
        this.idTipoAbeja = idTipoAbeja;
    }

}

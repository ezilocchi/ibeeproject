/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.model.apiar;

import ibeeproject.model.cajon.Cajon;
import ibeeproject.model.familia.Familia;
import java.util.Date;

/**
 *
 * @author Fede 
 */
public class Colmena implements Cloneable {

    private int idColmena;
    private String denominacion;
    private Date fechaAlta;
    private Date fechaBaja;
    private EstadoColmena estado;
    private TipoMiel tipoMiel;
    private Cajon cajon;
    private Posicion posicion;
    private Familia familia;
    //private Colmena colmenaOrignal;

    public Colmena() {
        this.estado = new EstadoColmena();
        this.tipoMiel = new TipoMiel();
        this.cajon = new Cajon();
        this.posicion = new Posicion();
        this.familia = new Familia();
        //this.colmenaOrignal = new Colmena();
    }

    /**
     * @return the idColmena
     */
    public int getIdColmena() {
        return idColmena;
    }

    /**
     * @param idColmena the idColmena to set
     */
    public void setIdColmena(int idColmena) {
        this.idColmena = idColmena;
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
     * @return the estado
     */
    public EstadoColmena getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoColmena estado) {
        this.estado = estado;
    }

    /**
     * @return the tipoMiel
     */
    public TipoMiel getTipoMiel() {
        return tipoMiel;
    }

    /**
     * @param tipoMiel the tipoMiel to set
     */
    public void setTipoMiel(TipoMiel tipoMiel) {
        this.tipoMiel = tipoMiel;
    }

    /**
     * @return the cajon
     */
    public Cajon getCajon() {
        return cajon;
    }

    /**
     * @param cajon the cajon to set
     */
    public void setCajon(Cajon cajon) {
        this.cajon = cajon;
    }

    /**
     * @return the posicion
     */
    public Posicion getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the familia
     */
    public Familia getFamilia() {
        return familia;
    }

    /**
     * @param familia the familia to set
     */
    public void setFamilia(Familia familia) {
        this.familia = familia;
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

    public Colmena cloneColmena()
    {
        Colmena colmena = (Colmena) this.clone();
        if (colmena.getFamilia().getNroFamilia() != 0) {
            colmena.setFamilia((Familia) colmena.getFamilia().cloneFamilia());
        }
        else {
            colmena.setFamilia(null);
        }
        if (colmena.getCajon().getNroCajon() != 0) {
            colmena.setCajon((Cajon) colmena.getCajon().clone());
        }
        else {
            colmena.setCajon(null);
        }
        return colmena;
    }
}

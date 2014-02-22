/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.model.actividad;

/**
 *
 * @author Fede 
 */
public class RecoleccionDato {

    private DatoDeRecoleccion tipoDato;
    private double valor;

    public RecoleccionDato() {
        this.tipoDato = new DatoDeRecoleccion();
    }

    /**
     * @return the tipoDato
     */
    public DatoDeRecoleccion getTipoDato() {
        return tipoDato;
    }

    /**
     * @param tipoDato the tipoDato to set
     */
    public void setTipoDato(DatoDeRecoleccion tipoDato) {
        this.tipoDato = tipoDato;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
}

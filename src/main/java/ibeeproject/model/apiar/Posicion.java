/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.apiar;

/**
 *
 * @author Fede 
 */
public class Posicion {

    private String posHorizontal;
    private String posVertical;
    private int idPosicion;
    private Layout layout;

    public Posicion() {
        this.layout = new Layout();
        this.posHorizontal= "";
        this.posVertical="";
    }
    public int getIdPosicion() {
        return idPosicion;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public void setIdPosicion(int idPosicion) {
        this.idPosicion = idPosicion;
    }

    public String getPosHorizontal() {
        return posHorizontal;
    }

    public void setPosHorizontal(String posHorizontal) {
        this.posHorizontal = posHorizontal;
    }

    public String getPosVertical() {
        return posVertical;
    }

    public void setPosVertical(String posVertical) {
        this.posVertical = posVertical;
    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.model.soporte;

import ibeeproject.model.familia.MiembroFamilia;

/**
 *
 * @author burni.matias
 */
public class UtilMiembroFamilia {

    private boolean seleccionado = false;
    private int numeroFamilia;
    private MiembroFamilia miembroFamilia;

    public UtilMiembroFamilia() {
        miembroFamilia = new MiembroFamilia();
    }

    /**
     * @return the seleccionado
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    /**
     * @return the miembroFamilia
     */
    public MiembroFamilia getMiembroFamilia() {
        return miembroFamilia;
    }

    /**
     * @param miembroFamilia the miembroFamilia to set
     */
    public void setMiembroFamilia(MiembroFamilia miembroFamilia) {
        this.miembroFamilia = miembroFamilia;
    }

    /**
     * @return the numeroFamilia
     */
    public int getNumeroFamilia() {
        return numeroFamilia;
    }

    /**
     * @param numeroFamilia the numeroFamilia to set
     */
    public void setNumeroFamilia(int numeroFamilia) {
        this.numeroFamilia = numeroFamilia;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.familia.MiembroFamilia;
import ibeeproject.persistencia.GestorMiembroFamilia;
import javax.faces.FacesException;

/**
 * @version modificarMiembroFamilia.java
 * @version Created on 11-oct-2009, 15:03:40
 * @author burni.matias
 */
public class modificarMiembroFamilia extends AbstractFragmentBean {

    private MiembroFamilia miembroFamilia;

    private void _init() throws Exception {
    }
    // </editor-fold>

    public modificarMiembroFamilia() {
    }

    /**
     * <p>Callback method that is called whenever a page containing
     * this page fragment is navigated to, either directly via a URL,
     * or indirectly via page navigation.  Override this method to acquire
     * resources that will be needed for event handlers and lifecycle methods.</p>
     * 
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();

        try {
            _init();
        } catch (Exception e) {
            log("Page1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }


    }

    /**
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called.  Override this
     * method to release resources acquired in the <code>init()</code>
     * resources that will be needed for event handlers and lifecycle methods.</p>
     * 
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void destroy() {
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

    public String actionCancelar() {
        agregarFamilia agregar = (agregarFamilia) getBean("agregarFamilia");
        agregar.agregarMiembroFamilia();
        return "familias";
    }

    public String actionAceptar() throws Exception {
        GestorMiembroFamilia gestor=new GestorMiembroFamilia();

        try {
            gestor.updateUno(this.getMiembroFamilia());
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean modificarMiembroFamilia.actionAceptar");
        }

        //reutilizo el codigo que tenia en el modificarFamilia y entro al POPup por ahi!
        modificarFamilia modificar = (modificarFamilia) getBean("modificarFamilia");
        modificar.agregarMiembroFamilia();
        return "familias";
    }
}

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
 *
 * @version agregarMiembroFamilia.java
 * @version Created on 09-oct-2009, 19:13:44
 * @author burni.matias
 */
public class agregarMiembroFamilia extends AbstractFragmentBean {

    private MiembroFamilia miembroFamilia;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public agregarMiembroFamilia() {
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
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
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

    public String actionAceptar() {
        // Busco el último número
        GestorMiembroFamilia gestorMiembroFamilia = new GestorMiembroFamilia();
        MiembroFamilia aux = (MiembroFamilia) gestorMiembroFamilia.getUltimo();
        // Registro el miembroFamilia
        miembroFamilia.setIdMiembroFamilia(aux.getIdMiembroFamilia() + 1);

        try {
            gestorMiembroFamilia.insertUno(miembroFamilia);
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean agregarMiembroFamilia");
        }
        modificarFamilia modificar = (modificarFamilia) getBean("modificarFamilia");
        if (modificar.isModifico()) {
            modificar.agregarMiembroFamilia();
        } else {
            agregarFamilia agregar = (agregarFamilia) getBean("agregarFamilia");
            agregar.agregarMiembroFamilia();
        }
        return "familias";
    }

    public String actionCancelar() {
        //ModalPopupMiembrosFamilia modalPopupMiembrosFamilia = (ModalPopupMiembrosFamilia) getBean("modalPopupMiembrosFamilia");
        agregarFamilia agregar = (agregarFamilia) getBean("agregarFamilia");
        agregar.agregarMiembroFamilia();
        return "familias";
    }
}

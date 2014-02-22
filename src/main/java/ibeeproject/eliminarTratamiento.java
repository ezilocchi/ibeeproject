/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.enfermedad.Tratamiento;
import ibeeproject.persistencia.GestorTratamiento;
import java.sql.SQLException;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version eliminarTratamiento.java
 * @version Created on 23-ago-2009, 13:40:39
 * @author carranza.matias
 */

public class eliminarTratamiento extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private Tratamiento tratamiento;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public eliminarTratamiento() {
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
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here
        
        
        // <editor-fold defaultstate="collapsed" desc="Visual-Web-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("Page1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
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
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
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

    public String actionAceptar() {
        // Acepta
        GestorTratamiento gestor = new GestorTratamiento();
        try {
            gestor.deleteUno(tratamiento);
            } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("El Tratamiento está asignado a una Enfermedad");
                message.setDetail("No se puede eliminar el Tratamiento por que está asignado a una Enfermedad...");
                context.addMessage("eliminar:eliminarTratamiento:tratamientoDefault", message);
                return null;
            }
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la eliminación BD: ManagedBean eliminarrTratamiento");
        }
        consultarTratamientos cons = (consultarTratamientos) getBean("consultarTratamientos");
        cons.updateTable();
        cons.queryAll_action();
        return "tratamientos";
    }

    public String actionCancelar() {
        // Cancela
        consultarTratamientos cons = (consultarTratamientos) getBean("consultarTratamientos");
        cons.queryAll_action();
        return "tratamientos";
    }

    /**
     * @return the tratamiento
     */
    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    /**
     * @param tratamiento the tratamiento to set
     */
    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

}

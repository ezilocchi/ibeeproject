/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.cajon.Cajon;
import ibeeproject.persistencia.GestorCajon;
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
 * @version eliminarCajon.java
 * @version Created on 26-ago-2009, 18:39:50
 * @author farias.facundo
 */
public class eliminarCajon extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private Cajon cajon;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public eliminarCajon() {
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
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
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

    public String actionAceptar() {
        // Acepta
        GestorCajon gestor = new GestorCajon();
        try {
            gestor.deleteUno(cajon);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("El Cajón pertenece a una Colmena");
                message.setDetail("No se puede eliminar el Cajón por que está asignado a una Colmena...");
                context.addMessage("eliminar:eliminarCajon:cajonDefault", message);
                return null;
            }
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la eliminación BD: ManagedBean eliminarCajon");
        }
        consultarCajones cons = (consultarCajones) getBean("consultarCajones");
        cons.updateTable();
        cons.queryAll_action();
        return "cajones";
    }

    public String actionCancelar() {
        // Cancela
        consultarCajones cons = (consultarCajones) getBean("consultarCajones");
        cons.queryAll_action();
        return "cajones";
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
}

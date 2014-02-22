/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.persona.Cargo;
import ibeeproject.persistencia.GestorCargo;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version eliminarCargo.java
 * @version Created on 06/03/2010, 15:43:49
 * @author burni.matias
 */
public class eliminarCargo extends AbstractFragmentBean {

    private Cargo cargo;
    private UIParameter parametro;
    private ArrayList recursoXCargos;

    public eliminarCargo() {
    }

    private void _init() throws Exception {
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
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
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

    public String actionAceptar() throws Exception {
        GestorCargo gestor = new GestorCargo();

        try {
            int res = gestor.deleteUno(cargo);
            // Verifico que el Cargo no corresponda a un Empleado
            if (res <= 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("El Cargo no puede se Eliminado porque hay un Empleado asignado al mismo");
                message.setDetail("El Cargo no puede se Eliminado porque hay un Empleado asignado al mismo");
                context.addMessage("eliminar:eliminarCargo:EmpleadoDefault", message);
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean eliminarCargo.actionAceptar");
        }
        consultarCargos cons = (consultarCargos) getBean("consultarCargos");
        cons.updateTable();
        cons.queryAll_action();
        return "cargos";
    }

    public String actionCancelar() {
        consultarCargos cons = (consultarCargos) getBean("consultarCargos");
        cons.queryAll_action();
        return null;
    }

    /**
     * @return the parametro
     */
    public UIParameter getParametro() {
        return parametro;
    }

    /**
     * @param parametro the parametro to set
     */
    public void setParametro(UIParameter parametro) {
        this.parametro = parametro;
    }

    /**
     * @return the recursoXCargos
     */
    public ArrayList getRecursoXCargos() {
        return recursoXCargos;
    }

    /**
     * @param recursoXCargos the recursoXCargos to set
     */
    public void setRecursoXCargos(ArrayList recursoXCargos) {
        this.recursoXCargos = recursoXCargos;
    }
}

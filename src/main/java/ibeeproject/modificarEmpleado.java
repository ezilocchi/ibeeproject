/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.persona.Empleado;
import ibeeproject.model.soporte.UtilEmailValidate;
import ibeeproject.persistencia.GestorEmpleado;
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
 * @version modificarEmpleado.java
 * @version Created on 13-feb-2010, 13:56:45
 * @author burni.matias
 */
public class modificarEmpleado extends AbstractFragmentBean {

    private Empleado empleado;
    private String usuarioAnterior;

    private void _init() throws Exception {
    }
    // </editor-fold>

    public modificarEmpleado() {
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
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
        usuarioAnterior = empleado.getUsuario();
    }

    public String actionAceptar() throws Exception {
        // Verifico que el Email este bien formado
        if (!datosValidos()) {
            return null;
        }

        GestorEmpleado gestor = new GestorEmpleado();

        try {
            gestor.updateUno(empleado);
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean modificarEmpleado");
        }

        consultarEmpleados consultar = (consultarEmpleados) getBean("consultarEmpleados");
        consultar.queryAll_action();
        consultar.updateTable();
        return "empleados";
    }

    public String actionCancelar() {
        // Volver
        consultarEmpleados consultar = (consultarEmpleados) getBean("consultarEmpleados");
        consultar.queryAll_action();
        return null;
    }

    public boolean datosValidos() {
        if (!UtilEmailValidate.isEmailValid(empleado.getEmail())) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("El Email posee un formato incorrecto");
            message.setDetail("Debe modificar su email, ejemplo: ibee@ibee.com");
            context.addMessage("modificar:modificarEmpleado:emailDefault", message);
            return false;
        }
        //hago esto porque si no cambia el empleado, daria un error de que ay existe ese usuario!
        if (!empleado.getUsuario().equals(usuarioAnterior)) {
            GestorEmpleado gestorEmpleado = new GestorEmpleado();
            if (gestorEmpleado.getUno(empleado.getUsuario()) != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Ese usuario ya existe");
                message.setDetail("Debe modificar su usuario, Ese usuario ya existe!");
                context.addMessage("modificar:modificarEmpleado:usuarioDefault", message);
                return false;
            }
        }

        return true;
    }
}

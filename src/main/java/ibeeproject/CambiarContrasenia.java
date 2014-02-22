/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.persona.Empleado;
import ibeeproject.persistencia.GestorEmpleado;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version CambiarContrasenia.java
 * @version Created on 09/04/2010, 17:41:24
 * @author burni.matias
 */
public class CambiarContrasenia extends AbstractPageBean {

    private Empleado empleado;
    private String passwordViejo;
    private String passwordNuevo;
    private String passwordNuevoConf;

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public CambiarContrasenia() {
    }

    private void _init() throws Exception {
    }

    /**
     * <p>Callback method that is called whenever a page is navigated to,
     * either directly via a URL, or indirectly via page navigation.
     * Customize this method to acquire resources that will be needed
     * for event handlers and lifecycle methods, whether or not this
     * page is performing post back processing.</p>
     * 
     * <p>Note that, if the current request is a postback, the property
     * values of the components do <strong>not</strong> represent any
     * values submitted with this request.  Instead, they represent the
     * property values that were saved for this view when it was rendered.</p>
     */
    @Override
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here

        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("CambiarContrasenia Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        this.empleado = (Empleado) getSessionBean1().getEmpleado();


    // </editor-fold>
    // Perform application initialization that must complete
    // *after* managed components are initialized
    // TODO - add your own initialization code here
    }

    /**
     * <p>Callback method that is called after the component tree has been
     * restored, but before any event processing takes place.  This method
     * will <strong>only</strong> be called on a postback request that
     * is processing a form submit.  Customize this method to allocate
     * resources that will be required in your event handlers.</p>
     */
    @Override
    public void preprocess() {
    }

    /**
     * <p>Callback method that is called just before rendering takes place.
     * This method will <strong>only</strong> be called for the page that
     * will actually be rendered (and not, for example, on a page that
     * handled a postback and then navigated to a different page).  Customize
     * this method to allocate resources that will be required for rendering
     * this page.</p>
     */
    @Override
    public void prerender() {
    }

    /**
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called (regardless of whether
     * or not this was the page that was actually rendered).  Customize this
     * method to release resources acquired in the <code>init()</code>,
     * <code>preprocess()</code>, or <code>prerender()</code> methods (or
     * acquired during execution of an event handler).</p>
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
    }

    /**
     * @return the passwordNuevo
     */
    public String getPasswordNuevo() {
        return passwordNuevo;
    }

    /**
     * @param passwordNuevo the passwordNuevo to set
     */
    public void setPasswordNuevo(String passwordNuevo) {
        this.passwordNuevo = passwordNuevo;
    }

    /**
     * @return the passwordNuevoConf
     */
    public String getPasswordNuevoConf() {
        return passwordNuevoConf;
    }

    /**
     * @param passwordNuevoConf the passwordNuevoConf to set
     */
    public void setPasswordNuevoConf(String passwordNuevoConf) {
        this.passwordNuevoConf = passwordNuevoConf;
    }

    /**
     * @return the passwordViejo
     */
    public String getPasswordViejo() {
        return passwordViejo;
    }

    /**
     * @param passwordViejo the passwordViejo to set
     */
    public void setPasswordViejo(String passwordViejo) {
        this.passwordViejo = passwordViejo;
    }

    public String actionAceptar() throws Exception {

        // Verifico datos ingresados
        if (!datosValidos()) {
            return null;
        }

        GestorEmpleado gestor = new GestorEmpleado();
        this.empleado.setPassword(this.passwordNuevo);
        try {
            gestor.updateUno(empleado);
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean CambiarContrasenia");
        }

        getSessionBean1().setEmpleado(empleado);

        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        message.setSummary("Su Contrase&#241;a fue Actualizada con éxito!");
        message.setDetail("Su Contrase&#241;a fue Actualizada con éxito!");
        context.addMessage("CambiarContrasenia:confirmacion", message);
        return null;
//        return "home";
    }

    public String actionCancelar() {
        // Limpiar Campos
        this.empleado = (Empleado) getSessionBean1().getEmpleado();
        return "cambiarContrasenia";
    }

    public boolean datosValidos() {
        // la contrasenia actual esta bien???
        if (!empleado.getPassword().equals((String)this.passwordViejo)) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Su Contrase&#241;a Anterior no coincide");
            message.setDetail("Su Contrase&#241;a Anterior no coincide");
            context.addMessage("CambiarContrasenia:passwordViejoErroneo", message);
            return false;
        }
        // la contrasenia nueva y su confirmacion son iguales???
        if (!this.passwordNuevo.equals(this.passwordNuevoConf)) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("No coinciden las Contrase&#241;a Nueva y su Confirmacion");
            message.setDetail("No coinciden las Contrase&#241;a Nueva y su Confirmacion");
            context.addMessage("CambiarContrasenia:passwordConfirmacion", message);
            return false;
        }
        return true;
    }
}


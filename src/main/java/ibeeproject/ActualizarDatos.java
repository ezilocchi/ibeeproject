package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.persona.Empleado;
import ibeeproject.model.soporte.UtilEmailValidate;
import ibeeproject.persistencia.GestorEmpleado;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @version ActualizarDatos.java
 * @version Created on 16/03/2010, 06:42:44
 * @author burni.matias
 */
public class ActualizarDatos extends AbstractPageBean {

    private Empleado empleado;
    private String usuarioAnterior;

    private void _init() throws Exception {
    }

    // </editor-fold>
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public ActualizarDatos() {
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
            log("ActualizarDatos Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        this.empleado = (Empleado) getSessionBean1().getEmpleado();
        this.setUsuarioAnterior(empleado.getUsuario());
        this.setCabecera();
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

    public String actionAceptar() throws Exception {
//        if (getSessionBean1().getEmpleado().equals(empleado)) {
//            FacesContext context = FacesContext.getCurrentInstance();
//            FacesMessage message = new FacesMessage();
//            message.setSeverity(FacesMessage.SEVERITY_ERROR);
//            message.setSummary("Debe modificar algun Dato!");
//            message.setDetail("Debe modificar algun Dato!");
//            context.addMessage("ActualizarDatos:datosIguales", message);
//            return null;
//        }

        // Verifico datos ingresados
        if (!datosValidos()) {
            this.empleado = (Empleado) getSessionBean1().getEmpleado();
            return null;
        }

        GestorEmpleado gestor = new GestorEmpleado();

        try {
            gestor.updateUno(empleado);
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean ActualizarDatos");
        }

        getSessionBean1().setEmpleado(empleado);
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        message.setSummary("Sus datos fueron Actualizados con éxito!");
        message.setDetail("Sus datos fueron Actualizados con éxito!");
        context.addMessage("ActualizarDatos:confirmacion", message);
        return null;
//        return "home";
    }

    public String actionCancelar() {
        // Limpiar Campos
        this.empleado = (Empleado) getSessionBean1().getEmpleado();
        return "actualizarDatos";
    }

    public boolean datosValidos() {
        if (!UtilEmailValidate.isEmailValid(empleado.getEmail())) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("El Email posee un formato incorrecto");
            message.setDetail("Debe modificar su email, ejemplo: ibee@ibee.com");
            context.addMessage("ActualizarDatos:emailDefault", message);
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
                context.addMessage("ActualizarDatos:usuarioDefault", message);
                return false;
            }
        }
        return true;
    }

    /**
     * @return the usuarioAnterior
     */
    public String getUsuarioAnterior() {
        return usuarioAnterior;
    }

    /**
     * @param usuarioAnterior the usuarioAnterior to set
     */
    public void setUsuarioAnterior(String usuarioAnterior) {
        this.usuarioAnterior = usuarioAnterior;
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Actualizar Datos");
    }
}


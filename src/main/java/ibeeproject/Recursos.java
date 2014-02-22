/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.persona.Cargo;
import ibeeproject.model.soporte.UtilRecursoXCargo;
import ibeeproject.persistencia.GestorCargo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version Recursos.java
 * @version Created on 07/04/2010, 19:19:22
 * @author burni.matias
 */
public class Recursos extends AbstractPageBean {

    private Cargo cargo;
    private ArrayList<SelectItem> cargos = new ArrayList();
    private ArrayList recursoXCargos;

    private void _init() throws Exception {
    }

    // </editor-fold>
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public Recursos() {
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

        try {
            _init();
        } catch (Exception e) {
            log("Recursos Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        this.setCabecera();

        this.getCargos().clear();
        try {
            this.getDBCargos();
        // </editor-fold>
        } catch (Exception ex) {
            Logger.getLogger(Recursos.class.getName()).log(Level.SEVERE, null, ex);
            log("Cargos Array charge Failure in Recursos.jsp", ex);
        }
        this.cargo = new Cargo();
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

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Recursos");
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

    /**
     * @return the cargos
     */
    public ArrayList<SelectItem> getCargos() {
        return cargos;
    }

    /**
     * @param cargos the cargos to set
     */
    public void setCargos(ArrayList<SelectItem> cargos) {
        this.cargos = cargos;
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

    /**
     * Setea inicialmente todos los Cargos
     */
    public void getDBCargos() throws Exception {
        GestorCargo gestor = new GestorCargo();
        Cargo c = new Cargo();
        ArrayList arrCargos = gestor.getTodos();
        for (int i = 0; i < arrCargos.size(); i++) {
            c = (Cargo) arrCargos.get(i);
            this.getCargos().add(new SelectItem(c.getIdCargo(), c.getDenominacion()));
        }
    }

    // Es disparado cuando se selecciona algo en el combo!
    public void cargarRecursos() throws Exception {
        this.setRecursoXCargos(UtilRecursoXCargo.dameHabilitadosyNo(cargo.getIdCargo()));

    }

    public void actionAceptar() throws Exception {
        cargo.setRecursos(UtilRecursoXCargo.dameHabilitados(recursoXCargos));

        GestorCargo gestor = new GestorCargo();

        try {
            gestor.updateUno(cargo);
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean Recurso");
        }
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Los Recursos del Cargo fueron asignados correctamente");
            message.setDetail("Los Recursos del Cargo fueron asignados correctamente");
            context.addMessage("recursos:saveDefault", message);
    //aca poner una validacion que diga que fue insertado correctamente!!
    }


    }

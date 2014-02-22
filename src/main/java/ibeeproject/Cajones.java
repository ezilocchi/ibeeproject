/*
 * Cajones.java
 *
 * Created on 25-ago-2009, 22:29:05
 * Copyright farias.facundo
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.cajon.EstadoCajon;
import ibeeproject.model.cajon.TipoCajon;
import ibeeproject.model.persona.Empleado;
import ibeeproject.persistencia.GestorEstadoCajon;
import ibeeproject.persistencia.GestorEmpleado;
import ibeeproject.persistencia.GestorTipoCajon;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.model.SelectItem;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class Cajones extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private int __placeholder;
    private boolean consultarAll = true;
    private boolean consultar = false;
    private boolean modificar = false;
    private boolean agregar = false;
    private boolean eliminar = false;
    private ArrayList<SelectItem> estados = new ArrayList();
    private ArrayList<SelectItem> empleados = new ArrayList();
    private ArrayList<SelectItem> tiposCajon = new ArrayList();

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }

    // </editor-fold>
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public Cajones() {
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
            log("Cajones Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        this.vaciarArrays();
        this.getDBEstado();
        this.getDBEmpleados();
        this.getDBTipoCajon();
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
     * @return the consultarAll
     */
    public boolean isConsultarAll() {
        return consultarAll;
    }

    /**
     * @param consultarAll the consultarAll to set
     */
    public void setConsultarAll(boolean consultarAll) {
        this.consultarAll = consultarAll;
        this.consultar = false;
        this.agregar = false;
        this.eliminar = false;
        this.modificar = false;
    }

    /**
     * @return the consultar
     */
    public boolean isConsultar() {
        return consultar;
    }

    /**
     * @param consultar the consultar to set
     */
    public void setConsultar(boolean consultar) {
        this.consultar = consultar;
        this.consultarAll = false;
        this.agregar = false;
        this.eliminar = false;
        this.modificar = false;
    }

    /**
     * @return the modificar
     */
    public boolean isModificar() {
        return modificar;
    }

    /**
     * @param modificar the modificar to set
     */
    public void setModificar(boolean modificar) {
        this.modificar = modificar;
        this.consultarAll = false;
        this.agregar = false;
        this.consultar = false;
        this.eliminar = false;
    }

    /**
     * @return the agregar
     */
    public boolean isAgregar() {
        return agregar;
    }

    /**
     * @param agregar the agregar to set
     */
    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
        this.consultarAll = false;
        this.consultar = false;
        this.eliminar = false;
        this.modificar = false;
    }

    /**
     * @return the eliminar
     */
    public boolean isEliminar() {
        return eliminar;
    }

    /**
     * @param eliminar the eliminar to set
     */
    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
        this.consultarAll = false;
        this.agregar = false;
        this.consultar = false;
        this.modificar = false;
    }

    /**
     * @return the estado
     */
    public ArrayList<SelectItem> getEstados() {
        return estados;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstados(ArrayList<SelectItem> estado) {
        this.estados = estado;
    }

    /**
     * @return the empleados
     */
    public ArrayList<SelectItem> getEmpleados() {
        return empleados;
    }

    /**
     * @param empleados the empleados to set
     */
    public void setEmpleados(ArrayList<SelectItem> empleados) {
        this.empleados = empleados;
    }

    /**
     * Limpia los Arrays de la Página
     */
    public void vaciarArrays() {
        this.getEmpleados().clear();
        this.getEstados().clear();
        this.getTiposCajon().clear();
    }

    /**
     * Setea inicialmente los Estados
     */
    public void getDBEstado() {
        GestorEstadoCajon gestorEstCajon = new GestorEstadoCajon();
        EstadoCajon estadoCajon = new EstadoCajon();
        ArrayList estadosCaj = gestorEstCajon.getTodos();
        for (int i = 0; i < estadosCaj.size(); i++) {
            estadoCajon = (EstadoCajon) estadosCaj.get(i);
            this.getEstados().add(new SelectItem(estadoCajon.getNumero(), estadoCajon.getEstado()));
        }
    }

    /**
     * Setea inicialmente los TipoCajon
     */
    public void getDBTipoCajon() {
        GestorTipoCajon gestorTipoCajon = new GestorTipoCajon();
        TipoCajon tipoCajon = new TipoCajon();
        ArrayList tiposCaj = gestorTipoCajon.getTodos();
        for (int i = 0; i < tiposCaj.size(); i++) {
            tipoCajon = (TipoCajon) tiposCaj.get(i);
            this.getTiposCajon().add(new SelectItem(tipoCajon.getIdTipoCajon(), tipoCajon.getDenominacion()));
        }
    }

    /**
     * Setea inicialmente los Empleados
     */
    public void getDBEmpleados() {
        GestorEmpleado gestorEmpleado = new GestorEmpleado();
        Empleado empleado = new Empleado();
        ArrayList empl = null;
        try {
            empl = gestorEmpleado.getTodos();
        } catch (Exception ex) {
            Logger.getLogger(Cajones.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < empl.size(); i++) {
            empleado = (Empleado) empl.get(i);
            this.getEmpleados().add(new SelectItem(empleado.getLegajo(),
                    empleado.getNombre() + " " + empleado.getApellido()));
        }
    }

    /**
     * @return the tiposCajon
     */
    public ArrayList<SelectItem> getTiposCajon() {
        return tiposCajon;
    }

    /**
     * @param tiposCajon the tiposCajon to set
     */
    public void setTiposCajon(ArrayList<SelectItem> tiposCajon) {
        this.tiposCajon = tiposCajon;
    }
}


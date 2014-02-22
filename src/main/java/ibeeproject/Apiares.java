/*
 * Apiares.java
 *
 * Created on 27-jun-2009, 17:36:09
 * Copyright farias.facundo 
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.apiar.Layout;
import ibeeproject.model.zona.Zona;
import ibeeproject.persistencia.GestorColmena;
import ibeeproject.persistencia.GestorLayout;
import ibeeproject.persistencia.GestorZona;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.model.SelectItem;


/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class Apiares extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private int __placeholder;
    private boolean consultarAll=true;
    private boolean consultar = false;
    private boolean modificar = false;
    private boolean agregar = false;
    private boolean eliminar = false;
    private GestorZona gestZ;
    private ArrayList zonas;
    private GestorColmena gestC;
    private GestorLayout gestLay;
    private ArrayList layouts;
    private ArrayList colmenasSinAsignar = new ArrayList();

    public ArrayList<SelectItem> getLayouts() {
        return layouts;
    }

    public void setLayouts(ArrayList layouts) {
        this.layouts = layouts;
    }

    public ArrayList<SelectItem> getZonas() {
        return zonas;
    }

    public void setZonas(ArrayList zonas) {
        this.zonas = zonas;
    }

    public boolean isConsultarAll() {
        return consultarAll;
    }

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
        this.consultar = false;
        this.consultarAll = false;
        this.agregar = false;
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
        this.consultar = false;
        this.consultarAll = false;
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
        this.consultar = false;
        this.consultarAll = false;
        this.agregar = false;
        this.modificar = false;
    }
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
    public Apiares() {
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
            log("Apiares Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        this.cargarColmenas();
        this.cargarLayouts();
        this.cargarZonas();
    }

        public void cargarZonas() {
        gestZ = new GestorZona();
        ArrayList nuevasZonas = new ArrayList();
        this.zonas = new ArrayList();
        nuevasZonas = gestZ.getTodos();
        for (int i = 0; i < nuevasZonas.size(); i++) {
            Zona zone = (Zona) nuevasZonas.get(i);
            this.getZonas().add(new SelectItem(zone.getIdZona(), zone.getZona()));
        }
    }

    public void cargarColmenas() {
        //Busco todas las colmenas sin asignar a algún apiar
        gestC = new GestorColmena();
        this.colmenasSinAsignar = new ArrayList();
        this.setColmenasSinAsignar(gestC.getSinAsignar());
        }

    public void cargarLayouts() {
        //Busco todos los layout con sus respectivas posiciones
        gestLay = new GestorLayout();
        this.layouts = new ArrayList();
        ArrayList nuevosLayouts = new ArrayList();
        nuevosLayouts = gestLay.getTodos();
        for (int i = 0; i < nuevosLayouts.size(); i++) {
            Layout lay = (Layout) nuevosLayouts.get(i);
            this.getLayouts().add(new SelectItem(lay.getIdLayout(), lay.getDenominacion()));
        }
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

    public void setColmenasSinAsignar(ArrayList sinAsignar) {
        this.colmenasSinAsignar = sinAsignar;
    }
    public ArrayList getColmenasSinAsignar()    {
       return this.colmenasSinAsignar;
    }
}


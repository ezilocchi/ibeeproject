/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.apiar.Apiar;
import ibeeproject.model.soporte.AxisChart;
import ibeeproject.model.soporte.AxisChartBean;
import ibeeproject.model.soporte.UtilGraph;
import ibeeproject.persistencia.GestorApiar;
import ibeeproject.persistencia.GestorPuntosGMap;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version Monitoreo.java
 * @version Created on 12-oct-2009, 19:41:39
 * @author farias.facundo
 */
public class Monitoreo extends AbstractPageBean {

    private UtilGraph util = new UtilGraph();
    private boolean mostrar = false;

    /**
     * @return the wheaterVar
     */
    public SelectItem[] getWheaterVar() {
        return wheaterVar;
    }
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private ArrayList<SelectItem> apiares = new ArrayList();
    private SelectItem[] wheaterVar = new SelectItem[]{
        new SelectItem(1, "temperatura"),
        new SelectItem(2, "sensacionTermica"),
        new SelectItem(3, "presion"),
        new SelectItem(4, "velocidadDelViento"),
        new SelectItem(5, "humedad"),
        new SelectItem(6, "indiceUV"),};
    private boolean loadData = false;
    private int idApiar1,  idApiar2,  idApiar3;
    private int var;

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
    public Monitoreo() {
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
            log("Monitoreo Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        this.doQuerys();
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
     * Setea inicialmente los Apiares
     */
    private void getDBApiares() {
        GestorApiar gestor = new GestorApiar();
        Apiar apiar = new Apiar();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Monitoreo.getDBApiares");
        }
        for (int i = 0; i < datos.size(); i++) {
            apiar = (Apiar) datos.get(i);
            this.getApiares().add(new SelectItem(apiar.getIdApiar(), apiar.getDenominacion()));
        }
    }

    /**
     * @return the apiares
     */
    public ArrayList<SelectItem> getApiares() {
        return apiares;
    }

    /**
     * @param apiares the apiares to set
     */
    public void setApiares(ArrayList<SelectItem> apiares) {
        this.apiares = apiares;
    }

    public void doQuerys() {
        if (this.loadData == false) {
            this.getApiares().clear();
            this.getDBApiares();
            this.loadData = true;
        }
    }

    /**
     * @return the var
     */
    public int getVar() {
        return var;
    }

    /**
     * @param var the var to set
     */
    public void setVar(int var) {
        this.var = var;
        this.mostrar = false;
    }

    /**
     * Actualiza el gráfico
     */
    public String updateGraph() {
        GestorPuntosGMap gestor = new GestorPuntosGMap();
        setUtil(gestor.getClimas(idApiar1, idApiar2, idApiar3, this.wheaterVar[var - 1].getLabel()));
        this.setMostrar(true);
        return null;
    }

    /**
     * @return the idApiar1
     */
    public int getIdApiar1() {
        return idApiar1;
    }

    /**
     * @param idApiar1 the idApiar1 to set
     */
    public void setIdApiar1(int idApiar1) {
        this.idApiar1 = idApiar1;
        this.mostrar = false;
    }

    /**
     * @return the idApiar2
     */
    public int getIdApiar2() {
        return idApiar2;
    }

    /**
     * @param idApiar2 the idApiar2 to set
     */
    public void setIdApiar2(int idApiar2) {
        this.idApiar2 = idApiar2;
        this.mostrar = false;
    }

    /**
     * @return the idApiar3
     */
    public int getIdApiar3() {
        return idApiar3;
    }

    /**
     * @param idApiar3 the idApiar3 to set
     */
    public void setIdApiar3(int idApiar3) {
        this.idApiar3 = idApiar3;
        this.mostrar = false;
    }

    /**
     * @return the mostrar
     */
    public boolean isMostrar() {
        return mostrar;
    }

    /**
     * @param mostrar the mostrar to set
     */
    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    /**
     * @return the util
     */
    public UtilGraph getUtil() {
        return util;
    }

    /**
     * @param util the util to set
     */
    public void setUtil(UtilGraph util) {
        this.util = util;
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

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Monitoreo");
    }
}


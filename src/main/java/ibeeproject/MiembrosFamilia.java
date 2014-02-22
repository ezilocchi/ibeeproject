/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.familia.TipoAbeja;
import ibeeproject.persistencia.GestorTipoAbeja;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.model.SelectItem;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version MiembrosFamilia.java
 * @version Created on 06-oct-2009, 15:43:08
 * @author burni.matias
 */
public class MiembrosFamilia extends AbstractPageBean {

    private boolean consultar = true;
    private boolean modificar = false;
    private boolean agregar = false;
    private boolean eliminar = false;
    private ArrayList<SelectItem> tiposAbeja = new ArrayList();

    private void _init() throws Exception {
    }

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public MiembrosFamilia() {
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

        try {
            _init();
        } catch (Exception e) {
            log("MiembrosFamilia Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        //limpio el Array y lo recargo
        this.getTiposAbeja().clear();
        try {
            this.getDBTipoAbeja();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("error en el MiembrosFamilia.init, cargando el combo");
        }
    }

    @Override
    public void preprocess() {
    }

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
        this.agregar=false;
        this.eliminar=false;
        this.modificar=false;
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
        this.agregar = false;
        this.eliminar = false;
        this.consultar=false;
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
        this.eliminar=false;
        this.consultar=false;
        this.modificar=false;
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
        this.agregar=false;
        this.modificar=false;
        this.consultar=false;
    }

    /**
     * @return the tiposAbeja
     */
    public ArrayList<SelectItem> getTiposAbeja() {
        return tiposAbeja;
    }

    /**
     * @param tiposAbeja the tiposAbeja to set
     */
    public void setTiposAbeja(ArrayList<SelectItem> tiposAbeja) {
        this.tiposAbeja = tiposAbeja;
    }

    public void getDBTipoAbeja() throws Exception {
        GestorTipoAbeja gestor = new GestorTipoAbeja();
        TipoAbeja tipoAbeja = new TipoAbeja();
        ArrayList arrTipoAbeja = gestor.getTodos();
        for (int i = 0; i < arrTipoAbeja.size(); i++) {
            tipoAbeja = (TipoAbeja) arrTipoAbeja.get(i);
            this.getTiposAbeja().add(new SelectItem(tipoAbeja.getIdTipoAbeja(), tipoAbeja.getDenominacion()));
        }
    }
}


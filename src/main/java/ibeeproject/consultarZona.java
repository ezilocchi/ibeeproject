/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.zona.Zona;
import ibeeproject.persistencia.GestorZona;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.model.SelectItem;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version consultarZona.java
 * @version Created on 13/03/2010, 17:32:12
 * @author CyberShark
 */
public class consultarZona extends AbstractFragmentBean {

    private int idtipoZona;
    private ArrayList<SelectItem> opciones = new ArrayList();
    private int selected = 1;
    private boolean agro;
    private boolean flor;
    private String tipoZona;
    public Zona zona;
    private GestorZona gestZ = new GestorZona();

    public boolean isAgro() {
        return agro;
    }

    public void setAgro() {

        this.flor = false;
        this.agro = true;
    }

    public boolean isFlor() {
        return flor;
    }

    public void setFlor() {

        this.flor = true;
        this.agro = false;
    }

    public int getSelected() {
        return selected;
    }

    public String getTipo() {
        if (selected == 1) { // Flora
            return "Flora";
        }
        if (selected == 2) { // Agroquímico
            return "Agroquímico";
        }
        return null;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(int selected) {
        this.selected = selected;
        if (selected == 1) { // Flora
            this.setFlor();
        }
        if (selected == 2) { // Agroquímico
            this.setAgro();
        }
    }

    public void cargarRadio() {
        this.getOpciones().add(new SelectItem(1, "Flora"));
        this.getOpciones().add(new SelectItem(2, "Agroquimicos"));
    }

    /**
     * @return the opciones
     */
    public ArrayList<SelectItem> getOpciones() {
        return opciones;
    }

    /**
     * @param opciones the opciones to set
     */
    public void setOpciones(ArrayList<SelectItem> opciones) {
        this.opciones = opciones;
    }

    public int getIdtipoZona() {
        return idtipoZona;
    }

    public void setIdtipoZona(int idtipoZona) {
        this.idtipoZona = idtipoZona;
    }

    public Zona getZona() {
        mostrarZona mZ = (mostrarZona) getBean("mostrarZona");
        if (mZ.lat != 0.0 && mZ.longi != 0.0) {
            this.zona.setLatitud(mZ.lat);
            this.zona.setLongitud(mZ.longi);
        }
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
        this.cargarDenominacion();
    }

    private void _init() throws Exception {
   }
    // </editor-fold>

    public consultarZona() {
        this.cargarRadio();
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

    public String actionCancelar() {
        // Cancela
        mostrarZona mostrar = (mostrarZona) getBean("mostrarZona");
        mostrar.setDibujar(true);
        consultarZonas consulta = (consultarZonas) getBean("consultarZonas");
        consulta.cleanTodo();
        consulta.queryAll_action();
        return "zonas";
    }

    public String getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(String tipoZona) {
        this.tipoZona = tipoZona;
    }

    private void cargarDenominacion() {
       if(this.zona.getTipoFlora().getIdTipoFlora()!=-1)
          this.tipoZona = this.zona.getTipoFlora().getDenonimacion();
       else
          this.tipoZona = this.zona.getTipoAgroquimico().getDenominacion();
    }
}

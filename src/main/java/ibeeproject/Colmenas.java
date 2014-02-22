/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.apiar.EstadoColmena;
import ibeeproject.model.apiar.TipoMiel;
import ibeeproject.model.cajon.Cajon;
import ibeeproject.model.familia.Familia;
import ibeeproject.persistencia.GestorCajon;
import ibeeproject.persistencia.GestorEstadoColmena;
import ibeeproject.persistencia.GestorFamilia;
import ibeeproject.persistencia.GestorTipoMiel;
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
 *
 * @version Colmenas.java
 * @version Created on 30-ago-2009, 17:07:19
 * @author carranza.matias
 */
public class Colmenas extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private int __placeholder;
    private boolean consultarAll = true;
    private boolean consultar = false;
    private boolean modificar = false;
    private boolean agregar = false;
    private boolean eliminar = false;
    private ArrayList<SelectItem> estados = new ArrayList();
    private ArrayList<SelectItem> cajones = new ArrayList();
    private ArrayList<SelectItem> cajonesSinAsignar = new ArrayList();
    private ArrayList<SelectItem> familias = new ArrayList();
    private ArrayList<SelectItem> familiasSinAsignar = new ArrayList();
    private ArrayList<SelectItem> tiposMiel = new ArrayList();

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
    public Colmenas() {
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
            log("Colmenas Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        this.vaciarArrays();
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
     * @return the __placeholder
     */
    public int get_placeholder() {
        return __placeholder;
    }

    /**
     * @param _placeholder the __placeholder to set
     */
    public void set_placeholder(int _placeholder) {
        this.__placeholder = _placeholder;
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
     * @return the estados
     */
    public ArrayList<SelectItem> getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(ArrayList<SelectItem> estados) {
        this.estados = estados;
    }

    /**
     * @return the cajones
     */
    public ArrayList<SelectItem> getCajones() {
        return cajones;
    }

    /**
     * @param cajones the cajones to set
     */
    public void setCajones(ArrayList<SelectItem> cajones) {
        this.cajones = cajones;
    }

    /**
     * @return the familias
     */
    public ArrayList<SelectItem> getFamilias() {
        return familias;
    }

    /**
     * @param familias the familias to set
     */
    public void setFamilias(ArrayList<SelectItem> familias) {
        this.familias = familias;
    }

    /**
     * @return the tiposMiel
     */
    public ArrayList<SelectItem> getTiposMiel() {
        return tiposMiel;
    }

    /**
     * @param tiposMiel the tiposMiel to set
     */
    public void setTiposMiel(ArrayList<SelectItem> tiposMiel) {
        this.tiposMiel = tiposMiel;
    }

    private void getDBCajones() {
        GestorCajon gestorCajon = new GestorCajon();
        Cajon cajon = new Cajon();
        ArrayList caj = gestorCajon.getTodos();
        for (int i = 0; i < caj.size(); i++) {
            cajon = (Cajon) caj.get(i);
            this.getCajones().add(new SelectItem(cajon.getNroCajon(), cajon.getDescripcion()));
        }
    }

    public void getDBCajonesSinAsignar() {
        GestorCajon gestorCajon = new GestorCajon();
        Cajon cajon = new Cajon();
        ArrayList caj = gestorCajon.getSinAsignar();
        if (caj.size() == 0) {
            //this.getCajonesSinAsignar().add(new SelectItem(0, "No hay cajones"));
        } else {
            for (int i = 0; i < caj.size(); i++) {
                cajon = (Cajon) caj.get(i);
                this.getCajonesSinAsignar().add(new SelectItem(cajon.getNroCajon(), cajon.getDescripcion()));
            }
        }
    }

    private void getDBEstados() {
        GestorEstadoColmena gestorEstColmena = new GestorEstadoColmena();
        EstadoColmena estadoColmena = new EstadoColmena();
        ArrayList estadosCol = null;
        try {
            estadosCol = gestorEstColmena.getTodos();
        } catch (Exception ex) {
            Logger.getLogger(Colmenas.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < estadosCol.size(); i++) {
            estadoColmena = (EstadoColmena) estadosCol.get(i);
            this.getEstados().add(new SelectItem(estadoColmena.getNumero(), estadoColmena.getDenominacion()));
        }
    }

    private void getDBFamilias() {
        GestorFamilia gestorFamilia = new GestorFamilia();
        Familia familia = new Familia();
        ArrayList flias = gestorFamilia.getTodos();
        for (int i = 0; i < flias.size(); i++) {
            familia = (Familia) flias.get(i);
            this.getFamilias().add(new SelectItem(familia.getNroFamilia(), familia.getDenominacion()));
        }
    }

    public void getDBFamiliasSinAsignar() {
        try {
            GestorFamilia gestorFamilia = new GestorFamilia();
            Familia familia = new Familia();
            ArrayList flias = gestorFamilia.getSinAsignar();
            for (int i = 0; i < flias.size(); i++) {
                familia = (Familia) flias.get(i);
                this.getFamiliasSinAsignar().add(new SelectItem(familia.getNroFamilia(), familia.getDenominacion()));
            }
        } catch (Exception ex) {
            Logger.getLogger(Colmenas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getDBTiposMiel() {
        GestorTipoMiel gestorTipoMiel = new GestorTipoMiel();
        TipoMiel tipoMiel = new TipoMiel();
        ArrayList tiposMieles = null;
        try {
            tiposMieles = gestorTipoMiel.getTodos();
        } catch (Exception ex) {
            Logger.getLogger(Colmenas.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < tiposMieles.size(); i++) {
            tipoMiel = (TipoMiel) tiposMieles.get(i);
            this.getTiposMiel().add(new SelectItem(tipoMiel.getIdTipoMiel(), tipoMiel.getDenominacion()));
        }
    }

    public void vaciarArrays() {
        this.getCajones().clear();
        this.getCajonesSinAsignar().clear();
        this.getEstados().clear();
        this.getFamilias().clear();
        this.getFamiliasSinAsignar().clear();
        this.getTiposMiel().clear();
        
        this.getDBCajones();
        this.getDBCajonesSinAsignar();
        this.getDBEstados();
        this.getDBFamilias();
        this.getDBFamiliasSinAsignar();
        this.getDBTiposMiel();
    }

    /**
     * @return the familiasSinAsignar
     */
    public ArrayList<SelectItem> getFamiliasSinAsignar() {
        return familiasSinAsignar;
    }

    /**
     * @param familiasSinAsignar the familiasSinAsignar to set
     */
    public void setFamiliasSinAsignar(ArrayList<SelectItem> familiasSinAsignar) {
        this.familiasSinAsignar = familiasSinAsignar;
    }

    /**
     * @return the cajonesSinAsignar
     */
    public ArrayList<SelectItem> getCajonesSinAsignar() {
        return cajonesSinAsignar;
    }

    /**
     * @param cajonesSinAsignar the cajonesSinAsignar to set
     */
    public void setCajonesSinAsignar(ArrayList<SelectItem> cajonesSinAsignar) {
        this.cajonesSinAsignar = cajonesSinAsignar;
    }
}


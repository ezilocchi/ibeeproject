/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.actividad.TipoDato;
import ibeeproject.model.persona.Cargo;
import ibeeproject.persistencia.GestorCargo;
import ibeeproject.persistencia.GestorTipoDato;
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
 * @version TiposDeActividad.java
 * @version Created on 05-sep-2009, 17:35:53
 * @author farias.facundo
 */
public class TiposDeActividad extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private String accion;
    private boolean consultarAll = true;
    private boolean consultar = false;
    private boolean modificar = false;
    private boolean agregar = false;
    private boolean eliminar = false;
    private ArrayList<SelectItem> cargos = new ArrayList();
    private ArrayList<SelectItem> tipoDatos = new ArrayList();

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
    public TiposDeActividad() {
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
            log("TiposDeActividad Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        this.vaciarArrays();
        this.getDBCargos();
        this.getDBTipoDato();
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
        this.agregar = false;
        this.eliminar = false;
        this.modificar = false;
        this.consultarAll = false;
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
        this.consultar = false;
        this.eliminar = false;
        this.consultarAll = false;
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
        this.eliminar = false;
        this.modificar = false;
        this.consultarAll = false;
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
        this.agregar = false;
        this.consultar = false;
        this.modificar = false;
        this.consultarAll = false;
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
     * @return the cargos
     */
    public ArrayList<SelectItem> getCargos() {
        return cargos;
    }

    /**
     * @param cargos the cargos to set
     */
    public void setCargos(ArrayList<SelectItem> cargos) {
        this.setCargos(cargos);
    }

    /**
     * Limpia los Arrays de la Página
     */
    public void vaciarArrays() {
        this.getCargos().clear();
        this.getTipoDatos().clear();
    }

    /**
     * Setea inicialmente los Cargos
     */
    public void getDBCargos() {
        GestorCargo gestor = new GestorCargo();
        Cargo cargo = new Cargo();
        ArrayList carg = null;
        try {
            carg = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: TiposDeActividad.getDBCargos");
        }
        for (int i = 0; i < carg.size(); i++) {
            cargo = (Cargo) carg.get(i);
            this.getCargos().add(new SelectItem(cargo.getIdCargo(), cargo.getDescripcion()));
        }
    }

    /**
     * Setea inicialmente los Tipos de Dato
     */
    public void getDBTipoDato() {
        GestorTipoDato gestor = new GestorTipoDato();
        TipoDato tipo = new TipoDato();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: TiposDeActividad.getDBTipoDato");
        }
        for (int i = 0; i < datos.size(); i++) {
            tipo = (TipoDato) datos.get(i);
            this.getTipoDatos().add(new SelectItem(tipo.getNumero(), tipo.getDescripcion()));
        }
    }

    /**
     * @return the accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * @return the tipoDatos
     */
    public ArrayList<SelectItem> getTipoDatos() {
        return tipoDatos;
    }

    /**
     * @param tipoDatos the tipoDatos to set
     */
    public void setTipoDatos(ArrayList<SelectItem> tipoDatos) {
        this.tipoDatos = tipoDatos;
    }
}


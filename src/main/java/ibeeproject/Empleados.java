/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.persona.Cargo;
import ibeeproject.model.persona.EstadoEmpleado;
import ibeeproject.persistencia.GestorCargo;
import ibeeproject.persistencia.GestorEstadoEmpleado;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.model.SelectItem;

public class Empleados extends AbstractPageBean {

    private boolean consultarAll = true;
    private boolean consultar = false;
    private boolean modificar = false;
    private boolean agregar = false;
    private boolean eliminar = false;
    private ArrayList<SelectItem> estadosEmpleado = new ArrayList();
    private ArrayList<SelectItem> cargos = new ArrayList();

    private void _init() throws Exception {
    }

    public Empleados() {
    }

    @Override
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();

        try {
            _init();
        } catch (Exception e) {
            log("Empleados Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        this.vaciarArrays();
        try {
            this.getDBCargos();
            this.getDBEstadosEmpleado();
        } catch (Exception ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
            log("Empleados Array charge Failure", ex);
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
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
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
     * @return the estadosEmpleado
     */
    public ArrayList<SelectItem> getEstadosEmpleado() {
        return estadosEmpleado;
    }

    /**
     * @param estadosEmpleado the estadosEmpleado to set
     */
    public void setEstadosEmpleado(ArrayList<SelectItem> estadosEmpleado) {
        this.estadosEmpleado = estadosEmpleado;
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

    public void vaciarArrays() {
        this.getEstadosEmpleado().clear();
        this.getCargos().clear();
    }

    /**
     * Setea inicialmente las EstadosEmpleado
     */
    public void getDBEstadosEmpleado() throws Exception {
        GestorEstadoEmpleado gestor = new GestorEstadoEmpleado();
        EstadoEmpleado eEmpleado = new EstadoEmpleado();
        ArrayList arrEstados = gestor.getTodos();
        for (int i = 0; i < arrEstados.size(); i++) {
            eEmpleado = (EstadoEmpleado) arrEstados.get(i);
            this.getEstadosEmpleado().add(new SelectItem(eEmpleado.getNumero(), eEmpleado.getEstado()));
        }
    }

    /**
     * Setea inicialmente los Cargos
     */
    public void getDBCargos() throws Exception {
        GestorCargo gestor = new GestorCargo();
        Cargo cargo = new Cargo();
        ArrayList arrCargos = gestor.getTodos();
        for (int i = 0; i < arrCargos.size(); i++) {
            cargo = (Cargo) arrCargos.get(i);
            this.getCargos().add(new SelectItem(cargo.getIdCargo(), cargo.getDescripcion()));
        }
    }
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.persona.Cargo;
import ibeeproject.model.soporte.UtilRecursoXCargo;
import ibeeproject.persistencia.GestorCargo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.component.UIParameter;

public class consultarCargos extends AbstractFragmentBean {

    private String campoBusq;
    private ArrayList cargos = new ArrayList();
    private GestorCargo gestor = new GestorCargo();
    private UIParameter parametro;

    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarCargos() {
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
        try {
            this.updateTable();
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        } catch (Exception ex) {
            Logger.getLogger(consultarCargos.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return the campoBusq
     */
    public String getCampoBusq() {
        return campoBusq;
    }

    /**
     * @param campoBusq the campoBusq to set
     */
    public void setCampoBusq(String campoBusq) {
        this.campoBusq = campoBusq;
    }

    /**
     * @return the cargos
     */
    public ArrayList getCargos() {
        return cargos;
    }

    /**
     * @param cargos the cargos to set
     */
    public void setCargos(ArrayList cargos) {
        this.cargos = cargos;
    }

    /**
     * @return the gestor
     */
    public GestorCargo getGestor() {
        return gestor;
    }

    /**
     * @param gestor the gestor to set
     */
    public void setGestor(GestorCargo gestor) {
        this.gestor = gestor;
    }

    /**
     * @return the parametro
     */
    public UIParameter getParametro() {
        return parametro;
    }

    /**
     * @param parametro the parametro to set
     */
    public void setParametro(UIParameter parametro) {
        this.parametro = parametro;
    }

    public void updateTable() throws Exception {
        this.getCargos().clear();
        setCargos(gestor.getTodos());
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Cargos");
    }

    public String add_action() {
        this.setCabecera("Home &raquo; Cargos &raquo; Agregar");
        Cargos c = (Cargos) getBean("Cargos");
        c.setAgregar(true);
        agregarCargo agregar = (agregarCargo) getBean("agregarCargo");
        agregar.setCargo(new Cargo());
        return null;
    }

    public String modif_action() {

        this.setCabecera("Home &raquo; Cargos &raquo; Modificar");
        Cargos c = (Cargos) getBean("Cargos");
        c.setModificar(true);
        Cargo cargo = (Cargo) this.parametro.getValue();
        modificarCargo modificar = (modificarCargo) getBean("modificarCargo");

        //se lo asigno antes, porque en el Init todavia no tengo el cargo
        modificar.setRecursoXCargos(UtilRecursoXCargo.dameHabilitadosyNo(cargo.getIdCargo()));

        modificar.setCargo(cargo);
        return null;
    }

    public String delete_action() {
        this.setCabecera("Home &raquo; Cargos &raquo; Eliminar");
        Cargos c = (Cargos) getBean("Cargos");
        c.setEliminar(true);
        Cargo cargo = (Cargo) this.parametro.getValue();
        eliminarCargo eliminar = (eliminarCargo) getBean("eliminarCargo");

        //se lo asigno antes, porque en el Init todavia no tengo el cargo
        eliminar.setRecursoXCargos(UtilRecursoXCargo.dameHabilitadosyNo(cargo.getIdCargo()));

        eliminar.setCargo(cargo);
        return null;
    }

    public String query_action() {
        this.setCabecera("Home &raquo; Cargos &raquo; Consultar");
        Cargos c = (Cargos) getBean("Cargos");
        c.setConsultar(true);
        Cargo cargo = (Cargo) this.parametro.getValue();
        consultarCargo consultar = (consultarCargo) getBean("consultarCargo");

        //se lo asigno antes, porque en el Init todavia no tengo el cargo
        consultar.setRecursoXCargos(UtilRecursoXCargo.dameHabilitadosyNo(cargo.getIdCargo()));

        consultar.setCargo(cargo);
        return null;
    }

    public String queryAll_action() {
        this.setCabecera();
        Cargos c = (Cargos) getBean("Cargos");
        c.setConsultarAll(true);
        return null;
    }
}

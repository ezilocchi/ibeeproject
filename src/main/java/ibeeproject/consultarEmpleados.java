/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.persona.Empleado;
import ibeeproject.persistencia.GestorEmpleado;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.component.UIParameter;

/**
 *
 * @version consultarEmpleados.java
 * @version Created on 30-ene-2010, 15:57:59
 * @author burni.matias
 */
public class consultarEmpleados extends AbstractFragmentBean {

    private String campoBusq;
    private ArrayList empleados = new ArrayList();
    private GestorEmpleado gestor = new GestorEmpleado();
    private UIParameter parametro;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarEmpleados() {
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
            // </editor-fold>
            // Perform application initialization that must complete
            // *after* managed components are initialized
            // TODO - add your own initialization code here
            this.updateTable();
        } catch (Exception ex) {
            Logger.getLogger(consultarEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setCabecera();
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
     * @return the empleados
     */
    public ArrayList getEmpleados() {
        return empleados;
    }

    /**
     * @param empleados the empleados to set
     */
    public void setEmpleados(ArrayList empleados) {
        this.empleados = empleados;
    }

    /**
     * @return the gestor
     */
    public GestorEmpleado getGestor() {
        return gestor;
    }

    /**
     * @param gestor the gestor to set
     */
    public void setGestor(GestorEmpleado gestor) {
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
        this.getEmpleados().clear();
        setEmpleados(gestor.getTodos());
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Usuarios");
    }

    public String add_action() {
        this.setCabecera("Home &raquo; Usuarios &raquo; Agregar");
        Empleados e = (Empleados) getBean("Empleados");
        e.setAgregar(true);
        agregarEmpleado agregar = (agregarEmpleado) getBean("agregarEmpleado");
        agregar.setEmpleado(new Empleado());
        return null;
    }

    public String modif_action() {

        this.setCabecera("Home &raquo; Usuarios &raquo; Modificar");
        Empleados e = (Empleados) getBean("Empleados");
        e.setModificar(true);
        Empleado empleado = (Empleado) this.parametro.getValue();
        modificarEmpleado modificar = (modificarEmpleado) getBean("modificarEmpleado");
        modificar.setEmpleado(empleado);
        return null;
    }

    public String delete_action() {
        this.setCabecera("Home &raquo; Usuarios &raquo; Eliminar");
        Empleados e = (Empleados) getBean("Empleados");
        e.setEliminar(true);
        Empleado empleado = (Empleado) this.parametro.getValue();
        eliminarEmpleado eliminar = (eliminarEmpleado) getBean("eliminarEmpleado");
        eliminar.setEmpleado(empleado);
        return null;
    }

    public String query_action() {
        this.setCabecera("Home &raquo; Usuarios &raquo; Consultar");
        Empleados e = (Empleados) getBean("Empleados");
        e.setConsultar(true);
        Empleado empleado = (Empleado) this.parametro.getValue();
        consultarEmpleado consultar = (consultarEmpleado) getBean("consultarEmpleado");
        consultar.setEmpleado(empleado);
        return null;
    }

    public String queryAll_action() {
        this.setCabecera();
        Empleados e = (Empleados) getBean("Empleados");
        e.setConsultarAll(true);
        return null;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.actividad.Actividad;
import ibeeproject.persistencia.GestorActividad;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.component.UIParameter;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version consultarActividades.java
 * @version Created on 06-oct-2009, 20:47:01
 * @author farias.facundo
 */
public class consultarActividades extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private String campoBusq;
    private ArrayList<Actividad> actividades = new ArrayList();
    private GestorActividad gestor = new GestorActividad();
    private UIParameter parametro;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarActividades() {
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
        this.updateTable();
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
     * @return the actividades
     */
    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    /**
     * @param actividades the actividades to set
     */
    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

    /**
     * @return the gestor
     */
    public GestorActividad getGestor() {
        return gestor;
    }

    /**
     * @param gestor the gestor to set
     */
    public void setGestor(GestorActividad gestor) {
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

    public void updateTable() {
        try {
            this.getActividades().clear();
            this.setActividades(gestor.getTodos());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: consultarActividades! (updateTable)");
        }
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Actividades");
    }

    public String add_action() {
        setCabecera("Home &raquo; Actividades &raquo; Agregar");
        Actividades a = (Actividades) getBean("Actividades");
        a.setAgregar(true);
        agregarActividad agregar = (agregarActividad) getBean("agregarActividad");
        agregar.setActividad(new Actividad());
        return null;
    }

    public String modif_action() {
        setCabecera("Home &raquo; Actividades &raquo; Resolver");
        Actividades a = (Actividades) getBean("Actividades");
        a.setModificar(true);
        Actividad actividad = (Actividad) this.parametro.getValue();
        modificarActividad modificar = (modificarActividad) getBean("modificarActividad");
        modificar.setActividad(actividad);
        return null;
    }

    public String delete_action() {
        setCabecera("Home &raquo; Actividades &raquo; Anular");
        Actividades a = (Actividades) getBean("Actividades");
        a.setEliminar(true);
        Actividad actividad = (Actividad) this.parametro.getValue();
        eliminarActividad eliminar = (eliminarActividad) getBean("eliminarActividad");
        eliminar.setActividad(actividad);
        return null;
    }

    public String query_action() {
        setCabecera("Home &raquo; Actividades &raquo; Consultar");
        Actividades a = (Actividades) getBean("Actividades");
        a.setConsultar(true);
        Actividad actividad = (Actividad) this.parametro.getValue();
        consultarActividad consultar = (consultarActividad) getBean("consultarActividad");
        consultar.setActividad(actividad);
        return null;
    }

    public String queryAll_action() {
        setCabecera();
        Actividades a = (Actividades) getBean("Actividades");
        a.setConsultarAll(true);
        return null;
    }

    public String pendientes_action() {
        this.getActividades().clear();
        try {
            setActividades(gestor.getTodosConFiltro(" WHERE idEstado NOT IN (4,5) "));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: consultarTareas! (pendientes_action)");
        }
        return null;
    }

    public String resueltos_action() {
        this.getActividades().clear();
        try {
            setActividades(gestor.getTodosConFiltro(" WHERE idEstado IN (5) "));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: consultarTareas! (resueltos_action)");
        }
        return null;
    }

    public String anulados_action() {
        this.getActividades().clear();
        try {
            setActividades(gestor.getTodosConFiltro(" WHERE idEstado IN (4) "));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: consultarTareas! (anulados_action)");
        }
        return null;
    }

    public String todos_action() {
        this.getActividades().clear();
        try {
            setActividades(gestor.getTodos());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: consultarTareas! (todos_action)");
        }
        return null;
    }
}

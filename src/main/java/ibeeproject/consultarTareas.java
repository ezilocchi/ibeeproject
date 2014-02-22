/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.actividad.EstadoTarea;
import ibeeproject.model.actividad.Tarea;
import ibeeproject.model.persona.Empleado;
import ibeeproject.persistencia.GestorTarea;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version consultarTareas.java
 * @version Created on 06-abr-2010, 20:44:45
 * @author farias.facundo
 */
public class consultarTareas extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private String campoBusq;
    private ArrayList<Tarea> tareas = new ArrayList();
    private GestorTarea gestor = new GestorTarea();
    private UIParameter parametro;
    private boolean resolve = false;
    private boolean readOnly = true;
    private boolean resolver = false;
    private boolean anular = false;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarTareas() {
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
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
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
     * @return the tareas
     */
    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    /**
     * @param tareas the tareas to set
     */
    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }

    /**
     * @return the gestor
     */
    public GestorTarea getGestor() {
        return gestor;
    }

    /**
     * @param gestor the gestor to set
     */
    public void setGestor(GestorTarea gestor) {
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
        Empleado e = (Empleado) getSessionBean1().getEmpleado();
        try {
            this.getTareas().clear();
            this.setTareas(gestor.getTodosUser(e.getLegajo()));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: consultarTareas! (updateTable)");
        }
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Tareas");
    }

    public String pendientes_action() {
        this.getTareas().clear();
        Empleado e = (Empleado) getSessionBean1().getEmpleado();
        try {
            setTareas(gestor.getTodosConFiltro(" WHERE idEstadoTarea NOT IN (4,5) ", e.getLegajo()));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: consultarTareas! (pendientes_action)");
        }
        this.setResolve(true);
        return null;
    }

    public String resueltos_action() {
        this.getTareas().clear();
        Empleado e = (Empleado) getSessionBean1().getEmpleado();
        try {
            setTareas(gestor.getTodosConFiltro(" WHERE idEstadoTarea IN (5) ", e.getLegajo()));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: consultarTareas! (resueltos_action)");
        }
        this.setReadOnly(true);
        return null;
    }

    public String anulados_action() {
        this.getTareas().clear();
        Empleado e = (Empleado) getSessionBean1().getEmpleado();
        try {
            setTareas(gestor.getTodosConFiltro(" WHERE idEstadoTarea IN (4) ", e.getLegajo()));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: consultarTareas! (anulados_action)");
        }
        this.setReadOnly(true);
        return null;
    }

    public String todos_action() {
        this.getTareas().clear();
        Empleado e = (Empleado) getSessionBean1().getEmpleado();
        try {
            setTareas(gestor.getTodosUser(e.getLegajo()));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: consultarTareas! (todos_action)");
        }
        this.setReadOnly(true);
        return null;
    }

    /**
     * @return the resolve
     */
    public boolean isResolve() {
        return resolve;
    }

    /**
     * @param resolve the resolve to set
     */
    public void setResolve(boolean resolve) {
        this.resolve = resolve;
        this.readOnly = false;
    }

    /**
     * @return the readOnly
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * @param readOnly the readOnly to set
     */
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        this.resolve = false;
    }

    /**
     * Resolver Tareas
     */
    public String resolverTareas() {
        this.setResolver();
        Tarea tarea = (Tarea) this.getParametro().getValue();
        if (tarea.getEstado().getNumero() != 4) {

            tarea.setEstado(new EstadoTarea(3));

            // Hago visible la ventana
            ModalPopupTareas pop = (ModalPopupTareas) getBean("ModalPopupTareas");
            pop.getPanelPopup1Bean().setShowModalPanel(true);
            pop.getPanelPopup1().setVisible(true);
            pop.getPanelPopup1().setRendered(true);
            pop.setVieneDeTareas();

            // Configuro los tipos de tarea que puede seleccionar
            Tareas tareas = (Tareas) getBean("Tareas");
            //tareas.getTiposDeTarea().clear();
            //tareas.getDBTiposDeTarea(tarea.getTipoActividad().getCodigo());

            // Configuro el Popup
            pop.setTitle("Complete los datos correspondientes a la tarea");
            pop.setTarea(tarea);
            pop.setConfigure(true);
            pop.setResolve(true);

            pop.setearTamaño(600, 330 + tarea.getTipoTarea().getCantidadChecks() * 25 + 35 * 4);

            //Armo los arreylist necesarios
            tareas.doQuerysOnDemand(tarea.getTipoTarea());

        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("La tarea debe poseer un estado valido...");
            message.setDetail("La tarea se encuentra en estado Anulada...");
            context.addMessage("consultarAll:tareaDefault", message);

        }
        return null;
    }

    /**
     * Configuro la tarea
     */
    public String consultarTareas() {
        // Hago visible la ventana
        ModalPopupTareas pop = (ModalPopupTareas) getBean("ModalPopupTareas");
        pop.getPanelPopup1Bean().setShowModalPanel(true);
        pop.getPanelPopup1().setVisible(true);
        pop.getPanelPopup1().setRendered(true);

        // Configuro los tipos de tarea que puede seleccionar
        Tareas tareas = (Tareas) getBean("Tareas");
        //tareas.getTiposDeTarea().clear();
        //tareas.getDBTiposDeTarea(tarea.getTipoActividad().getCodigo());

        // Configuro el Popup
        pop.setTitle("Configure los datos de la tarea");
        pop.setearTamaño(600, 300);

        Tarea tarea = (Tarea) this.getParametro().getValue();
        pop.setTarea(tarea);
        pop.setReadOnly(true);
        pop.setVieneDeTareas();

        pop.setearTamaño(600, 330 + tarea.getTipoTarea().getCantidadChecks() * 25 + 35 * 4);

        //Armo los arreylist necesarios
        tareas.doQuerysOnDemand(tarea.getTipoTarea());
        return null;
    }

    /**
     * Resolver Tareas
     */
    public String anularTareas() {
        this.setAnular();
        Tarea tarea = (Tarea) this.getParametro().getValue();
        if (tarea.getEstado().getNumero() != 4) {

            tarea.setEstado(new EstadoTarea(3));

            // Hago visible la ventana
            ModalPopupTareas pop = (ModalPopupTareas) getBean("ModalPopupTareas");
            pop.getPanelPopup1Bean().setShowModalPanel(true);
            pop.getPanelPopup1().setVisible(true);
            pop.getPanelPopup1().setRendered(true);
            pop.setVieneDeTareas();

            // Configuro los tipos de tarea que puede seleccionar
            Tareas tareas = (Tareas) getBean("Tareas");
            //tareas.getTiposDeTarea().clear();
            //tareas.getDBTiposDeTarea(tarea.getTipoActividad().getCodigo());

            // Configuro el Popup
            pop.setTitle("Complete los datos correspondientes a la tarea");
            pop.setTarea(tarea);
            pop.setConfigure(true);
            pop.setResolve(true);

            pop.setearTamaño(600, 330 + tarea.getTipoTarea().getCantidadChecks() * 25 + 35 * 4);

            //Armo los arreylist necesarios
            tareas.doQuerysOnDemand(tarea.getTipoTarea());

        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("La tarea debe poseer un estado valido...");
            message.setDetail("La tarea se encuentra en estado Anulada...");
            context.addMessage("consultarAll:tareaDefault", message);

        }
        return null;
    }

    /**
     * @return the resolver
     */
    public boolean isResolver() {
        return resolver;
    }

    /**
     * @param resolver the resolver to set
     */
    public void setResolver() {
        this.resolver = true;
        this.anular = false;
    }

    /**
     * @return the anular
     */
    public boolean isAnular() {
        return anular;
    }

    /**
     * @param anular the anular to set
     */
    public void setAnular() {
        this.anular = true;
        this.resolver = false;
    }
}

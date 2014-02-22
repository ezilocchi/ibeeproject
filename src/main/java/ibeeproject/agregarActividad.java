/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.actividad.Actividad;
import ibeeproject.model.actividad.EstadoActividad;
import ibeeproject.model.actividad.EstadoTarea;
import ibeeproject.model.actividad.Tarea;
import ibeeproject.model.actividad.TipoTarea;
import ibeeproject.persistencia.GestorActividad;
import ibeeproject.persistencia.GestorTipoTarea;
import java.util.Date;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version agregarActividad.java
 * @version Created on 10-oct-2009, 16:00:59
 * @author farias.facundo
 */
public class agregarActividad extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private Actividad actividad;
    private UIParameter parametro;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public agregarActividad() {
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
     * @return the actividad
     */
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * @param actividad the actividad to set
     */
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
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

    /**
     * Eliminar Tareas
     */
    public String eliminarTareas() {
        this.getActividad().getTareas().remove((Tarea) this.getParametro().getValue());
        return null;
    }

    /**
     * Agregar Tareas
     */
    public String agregarTareas() {
        // Configuro los tipos de tarea que puede seleccionar
        Actividades actividades = (Actividades) getBean("Actividades");
        actividades.getTiposDeTarea().clear();
        actividades.getTiposDeTarea().add(new SelectItem("00", "Seleccione.."));
        actividades.getDBTiposDeTarea(this.getActividad().getTipoActividad().getCodigo());
        Tarea tarea = new Tarea();
        tarea.setIdTarea(this.getActividad().getTareas().size() + 1);
        tarea.setEstado(new EstadoTarea(1));
        this.getActividad().getTareas().add(tarea);
        return null;
    }

    /**
     * Configuro la tarea
     */
    public String configurarTareas() {
        // Configuro los tipos de tarea que puede seleccionar
        Actividades actividades = (Actividades) getBean("Actividades");
        actividades.getTiposDeTarea().clear();
        actividades.getDBTiposDeTarea(this.getActividad().getTipoActividad().getCodigo());

        // Hago visible la ventana
        ModalPopupTareas pop = (ModalPopupTareas) getBean("ModalPopupTareas");
        pop.getPanelPopup1Bean().setShowModalPanel(true);
        pop.getPanelPopup1().setVisible(true);
        pop.getPanelPopup1().setRendered(true);
        pop.setVieneDeActividad();

        // Configuro el Popup
        pop.setTitle("Configure los datos de la tarea");
        pop.setearTamaño(600, 400);

        Tarea tarea = (Tarea) this.getParametro().getValue();
        GestorTipoTarea gestor = new GestorTipoTarea();
        try {
            TipoTarea tipo = new TipoTarea();
            tipo = (TipoTarea) gestor.getUno(this.getActividad().getTipoActividad().getCodigo(),
                    tarea.getTipoTarea().getCodigo());

            tarea.setTipoTarea(tipo);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la recuperación TipoTarea: ManagedBean agregarActividad");
        }
        pop.setTarea(tarea);
        pop.setReadOnly(false);
        pop.setearTamaño(600, 325 + tarea.getTipoTarea().getCantidadChecks() * 25);

        //Armo los arreylist necesarios
        actividades.doQuerysOnDemand(tarea.getTipoTarea());

        return null;
    }

    public String actionAceptar() {

        // Verifico que la actividad tenga tareas parametrizadas
        if (this.getActividad().getTareas().size() == 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("La actividad no posee tareas asociadas");
            message.setDetail("Debe parametrizar tareas para la actividad..");
            context.addMessage("agregar:agregarActividad:actividadDefault", message);
            return null;
        }

        // Verifico la no exitencia de tareas finalizadas
        int cont = 0;
        for (int i = 0; i < this.getActividad().getTareas().size(); i++) {
            Tarea t = (Tarea) this.getActividad().getTareas().get(i);
            if (t.getEstado().getNumero() == 1) {
                cont++;
            }
        }
        // En caso de tenerlas, no se puede anular la actividad
        if (cont > 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("La actividad posee tareas pendientes de configurar");
            message.setDetail("Debe configurar todas las tareas agregadas ..");
            context.addMessage("agregar:agregarActividad:actividadDefault", message);
            return null;
        }

        // Acepta
        GestorActividad gestorActividad = new GestorActividad();
        try {
            // Recupero el numero y le añado 1
            Actividad aux = (Actividad) gestorActividad.getUltimo();
            this.getActividad().setNumero(aux.getNumero() + 1);
            // Inserto la actividad
            this.getActividad().setEstado(new EstadoActividad(2));
            this.getActividad().setFechaCreacion(new Date());
            gestorActividad.insertUno(this.actividad);
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean agregarActividad");
        }
        consultarActividades consulta = (consultarActividades) getBean("consultarActividades");
        consulta.updateTable();
        consulta.queryAll_action();
        return "actividades";
    }

    public String actionCancelar() {
        // Cancela
        consultarActividades consulta = (consultarActividades) getBean("consultarActividades");
        consulta.updateTable();
        consulta.queryAll_action();
        return "actividades";
    }

    public String updateTiposDeTarea() {
        // Configuro los tipos de tarea que puede seleccionar
        Actividades actividades = (Actividades) getBean("Actividades");
        actividades.getTiposDeTarea().clear();
        actividades.getDBTiposDeTarea(this.getActividad().getTipoActividad().getCodigo());
        return null;
    }
}

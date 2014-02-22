/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.actividad.Actividad;
import ibeeproject.model.actividad.Tarea;
import javax.faces.FacesException;
import javax.faces.component.UIParameter;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version consultarActividad.java
 * @version Created on 30-ene-2010, 14:23:04
 * @author farias.facundo
 */
public class consultarActividad extends AbstractFragmentBean {
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

    public consultarActividad() {
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
     * Volver a la consulta de Actividad
     */
    public String actionVolver() {
        consultarActividades consulta = (consultarActividades) getBean("consultarActividades");
        consulta.queryAll_action();
        return "actividad";
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
        Actividades actividades = (Actividades) getBean("Actividades");
        actividades.getTiposDeTarea().clear();
        actividades.getDBTiposDeTarea(this.getActividad().getTipoActividad().getCodigo());

        // Configuro el Popup
        pop.setTitle("Configure los datos de la tarea");
        pop.setearTamaño(600, 300);
        pop.setVieneDeActividad();

        Tarea tarea = (Tarea) this.getParametro().getValue();
        pop.setTarea(tarea);
        pop.setReadOnly(true);
        pop.setearTamaño(600, 330 + tarea.getTipoTarea().getCantidadChecks() * 25 + 35 * 4);

        //Armo los arraylist necesarios
        actividades.doQuerysOnDemand(tarea.getTipoTarea());
        return null;
    }
}

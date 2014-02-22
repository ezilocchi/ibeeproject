/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.icesoft.faces.component.jsfcl.data.PopupBean;
import com.icesoft.faces.component.panelpopup.PanelPopup;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.actividad.Actividad;
import ibeeproject.model.actividad.EstadoActividad;
import ibeeproject.model.actividad.EstadoTarea;
import ibeeproject.model.actividad.Tarea;
import ibeeproject.persistencia.GestorActividad;
import ibeeproject.persistencia.GestorTarea;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version ModalPopupTareas.java
 * @version Created on 11-oct-2009, 19:54:08
 * @author farias.facundo
 */
public class ModalPopupTareas extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private Tarea tarea;
    private String title;
    private boolean readOnly = false;
    private boolean configure = false;
    private boolean resolve = false;
    private boolean vieneDeActividad = false;
    private boolean vieneDeTareas = false;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>
    private PopupBean panelPopup1Bean = new PopupBean();

    public PopupBean getPanelPopup1Bean() {
        return panelPopup1Bean;
    }

    public void setPanelPopup1Bean(PopupBean pb) {
        this.panelPopup1Bean = pb;
    }
    private PanelPopup panelPopup1 = new PanelPopup();

    public PanelPopup getPanelPopup1() {
        return panelPopup1;
    }

    public void setPanelPopup1(PanelPopup pp) {
        this.panelPopup1 = pp;
    }

    public ModalPopupTareas() {
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
        this.panelPopup1Bean.setShowModalPanel(false);
        this.panelPopup1.setRendered(false);
        this.panelPopup1.setVisible(false);
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
     * @return the tarea
     */
    public Tarea getTarea() {
        return tarea;
    }

    /**
     * @param tarea the tarea to set
     */
    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public String cerrarPopup() {
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
        this.setTarea(new Tarea());
        return null;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public void setearTamaño(int ancho, int alto) {
        String style = " width: " + ancho + "px; height: " + alto + "px;";
        this.panelPopup1.setStyle(style);
    }

    public String acepta() {

        // La fecha de finalización de la tarea, debe ser menor que la actividad
        Actividades actividades = (Actividades) getBean("Actividades");
        if (actividades != null) {
            if (actividades.isAgregar()) {
                agregarActividad agregar = (agregarActividad) getBean("agregarActividad");
                if (this.tarea.getFechaPrevista().compareTo(agregar.getActividad().getFechaEsperadaDeCierre()) > 0) {
                    FacesContext context = FacesContext.getCurrentInstance();
                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Error en el ingreso de fechas");
                    message.setDetail("La fecha de fin debe respetar la fecha fin de la actividad");
                    context.addMessage("ModalPopupTareas:formTareas:tareasDefault", message);
                    return null;
                }
            }
            if (actividades.isAgregar()) {
                this.getTarea().setEstado(new EstadoTarea(2));
                this.getTarea().setFechaRealizacion(new Date());
                agregarActividad agregar = (agregarActividad) getBean("agregarActividad");
                this.getTarea().getTipoTarea().setCodigoTipoActividad(agregar.getActividad().getTipoActividad().getCodigo());
            }
            if (actividades.isModificar()) {
                //this.getTarea().setEstado(new EstadoTarea(3));
            }
        }
        consultarTareas consultar = (consultarTareas) getBean("consultarTareas");
        if (consultar != null) {
            if (consultar.isResolver()) {
                try {
                    GestorTarea gestor = new GestorTarea();
                    this.getTarea().setEstado(new EstadoTarea(5));
                    this.getTarea().setFechaRealizacion(new Date());
                    ArrayList tareas = new ArrayList();
                    tareas.add(this.getTarea());
                    gestor.updateUno(tareas);

                    GestorActividad gestorActividad = new GestorActividad();
                    Actividad actividad = (Actividad) gestorActividad.getUno(this.getTarea().getIdActividad());
                    boolean finalizado = true;
                    for (int i = 0; i < actividad.getTareas().size(); i++) {
                        Tarea tarea_aux = (Tarea) actividad.getTareas().get(i);
                        if (tarea_aux.getEstado().getNumero() != 5 && tarea_aux.getEstado().getNumero() != 4) {
                            finalizado = false;
                        }
                    }
                    if (finalizado) {
                        actividad.setEstado(new EstadoActividad(5));
                        actividad.setFechaFin(new Date());
                        gestorActividad.updateUno(actividad);
                    }
                } catch (Exception ex) {
                    // @TODO
                    ex.printStackTrace();
                    System.out.print("Error en la eliminación BD: ManagedBean ModalPopupTareas.consultar.isResolver()");
                }
            }
            if (consultar.isAnular()) {
                try {
                    GestorTarea gestor = new GestorTarea();
                    this.getTarea().setEstado(new EstadoTarea(4));
                    this.getTarea().setFechaRealizacion(new Date());
                    gestor.deleteUno(this.getTarea().getIdTarea(), this.getTarea().getIdActividad());

                    GestorActividad gestorActividad = new GestorActividad();
                    Actividad actividad = (Actividad) gestorActividad.getUno(this.getTarea().getIdActividad());
                    boolean finalizado = true;
                    for (int i = 0; i < actividad.getTareas().size(); i++) {
                        Tarea tarea_aux = (Tarea) actividad.getTareas().get(i);
                        if (tarea_aux.getEstado().getNumero() != 5 && tarea_aux.getEstado().getNumero() != 4) {
                            finalizado = false;
                        }
                    }
                    if (finalizado) {
                        actividad.setEstado(new EstadoActividad(4));
                        actividad.setFechaAnulacion(new Date());
                        gestorActividad.deleteUno(actividad);
                    }

                } catch (Exception ex) {
                    // @TODO
                    ex.printStackTrace();
                    System.out.print("Error en la eliminación BD: ManagedBean ModalPopupTareas.consultar.isAnular()");
                }
            }
        }
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
        return null;
    }

    public String cancela() {
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
        this.setTarea(new Tarea());
        return null;
    }

    public String linkAlarmas() {
        return "alarmas";
    }

    public String linkLayouts() {
        return "layouts";
    }

    public String linkZonas() {
        return "zonas";
    }

    public String linkApiares() {
        return "apiares";
    }

    public String linkColmenas() {
        return "colmenas";
    }

    public String linkFamilias() {
        return "familias";
    }

    public String linkCajones() {
        return "cajones";
    }

    public String linkTratamientos() {
        return "tratamientos";
    }

    public String linkEnfermedades() {
        return "enfermedades";
    }

    public String linkSintomas() {
        return "sintomas";
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
        this.configure = false;
        this.resolve = false;
    }

    /**
     * @return the configure
     */
    public boolean isConfigure() {
        return configure;
    }

    /**
     * @param configure the configure to set
     */
    public void setConfigure(boolean configure) {
        this.configure = configure;
        this.readOnly = false;
        this.resolve = false;
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
        this.configure = false;
    }

    /**
     * @return the vieneDeActividad
     */
    public boolean isVieneDeActividad() {
        return vieneDeActividad;
    }

    /**
     * @param vieneDeActividad the vieneDeActividad to set
     */
    public void setVieneDeActividad() {
        this.vieneDeActividad = true;
        this.vieneDeTareas = false;
    }

    /**
     * @return the vieneDeTareas
     */
    public boolean isVieneDeTareas() {
        return vieneDeTareas;
    }

    /**
     * @param vieneDeTareas the vieneDeTareas to set
     */
    public void setVieneDeTareas() {
        this.vieneDeTareas = true;
        this.vieneDeActividad = false;
    }
}

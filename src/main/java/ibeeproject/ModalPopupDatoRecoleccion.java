/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.icesoft.faces.component.jsfcl.data.PopupBean;
import com.icesoft.faces.component.panelpopup.PanelPopup;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.actividad.DatoDeRecoleccion;
import ibeeproject.model.actividad.TipoTarea;
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
 * @version ModalPopupDatoRecoleccion.java
 * @version Created on 12-sep-2009, 14:03:17
 * @author farias.facundo
 */
public class ModalPopupDatoRecoleccion extends AbstractFragmentBean {

    private String title;
    private String codigoTipoActividad;
    private String codigoTipoTarea;
    private ArrayList<DatoDeRecoleccion> datoRecoleccion = new ArrayList();
    private UIParameter parametro;
    private boolean readOnly = false;

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

    public ModalPopupDatoRecoleccion() {
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
        this.panelPopup1Bean.setShowModalPanel(false);
        this.panelPopup1.setRendered(false);
        this.panelPopup1.setVisible(false);
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

    public String cerrarPopup() {
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
        this.getDatoRecoleccion().clear();
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
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
        try {
            TiposDeActividad a = (TiposDeActividad) getBean("TiposDeActividad");
            if (a.isAgregar())
            {
                agregarTipoDeActividad agregar = (agregarTipoDeActividad) getBean("agregarTipoDeActividad");
                ArrayList aux = agregar.getTipoActividad().getTipoTareas();
                for (int i = 0; i < aux.size(); i++) {
                    TipoTarea tipoTarea = (TipoTarea) aux.get(i);
                    if(tipoTarea.getCodigo().equals(this.getCodigoTipoTarea()))
                    {
                        tipoTarea.setDetalleTipoTarea(this.getDatoRecoleccion());
                        aux.set(i, tipoTarea);
                    }
                }
            }
            if (a.isModificar()) {
                //modificarEnfermedad modificar = (modificarEnfermedad) getBean("modificarEnfermedad");
                //modificar.getSintomas().add(e.getSintomas().get(this.getSintoma() - 1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en Popup DatoRecoleccion.acepta");
        }
        return null;
    }

    public String cancela() {
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
        this.getDatoRecoleccion().clear();
        return null;
    }

    /**
     * @return the datoRecoleccion
     */
    public ArrayList<DatoDeRecoleccion> getDatoRecoleccion() {
        return datoRecoleccion;
    }

    /**
     * @param datoRecoleccion the datoRecoleccion to set
     */
    public void setDatoRecoleccion(ArrayList<DatoDeRecoleccion> datoRecoleccion) {
        this.datoRecoleccion = datoRecoleccion;
    }

    public String agregarDatoRecoleccion() {
        this.getDatoRecoleccion().add(new DatoDeRecoleccion());
        return null;
    }

    public String eliminarDatoRecoleccion() {
        this.getDatoRecoleccion().remove(this.getParametro().getValue());
        return null;
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
     * @return the codigoTipoActividad
     */
    public String getCodigoTipoActividad() {
        return codigoTipoActividad;
    }

    /**
     * @param codigoTipoActividad the codigoTipoActividad to set
     */
    public void setCodigoTipoActividad(String codigoTipoActividad) {
        this.codigoTipoActividad = codigoTipoActividad;
    }

    /**
     * @return the codigoTipoTarea
     */
    public String getCodigoTipoTarea() {
        return codigoTipoTarea;
    }

    /**
     * @param codigoTipoTarea the codigoTipoTarea to set
     */
    public void setCodigoTipoTarea(String codigoTipoTarea) {
        this.codigoTipoTarea = codigoTipoTarea;
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
    }

    public boolean isTableEmpty()
    {
        if(this.getDatoRecoleccion().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

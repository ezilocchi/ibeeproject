/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.icesoft.faces.component.jsfcl.data.PopupBean;
import com.icesoft.faces.component.panelpopup.PanelPopup;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.apiar.HistorialEstadoFamilia;
import java.util.ArrayList;
import javax.faces.FacesException;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version ModalPopupHistorialFamilia.java
 * @version Created on 18-abr-2010, 14:02:37
 * @author farias.facundo
 */
public class ModalPopupHistorialFamilia extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private String title;
    private ArrayList<HistorialEstadoFamilia> historialEstadoFamilia;

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

    public ModalPopupHistorialFamilia() {
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

    /**
     * @return the historialEstadoFamilia
     */
    public ArrayList<HistorialEstadoFamilia> getHistorialEstadoFamilia() {
        return historialEstadoFamilia;
    }

    /**
     * @param historialEstadoFamilia the historialEstadoFamilia to set
     */
    public void setHistorialEstadoFamilia(ArrayList<HistorialEstadoFamilia> historialEstadoFamilia) {
        this.historialEstadoFamilia = historialEstadoFamilia;
    }

    public String acepta() {
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
        return null;
    }
}

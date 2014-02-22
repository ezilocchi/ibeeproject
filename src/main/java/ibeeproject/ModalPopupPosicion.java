/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.icesoft.faces.component.jsfcl.data.PopupBean;
import com.icesoft.faces.component.panelpopup.PanelPopup;
import com.icesoft.faces.component.ext.HtmlPanelGrid;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import javax.faces.FacesException;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version ModalPopupTratamientos.java
 * @version Created on 29-ago-2009, 18:25:44
 * @author farias.facundo
 */
public class ModalPopupPosicion extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private String title;
    private int tratamiento;
    private HtmlPanelGrid panelPopup2;

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
        public HtmlPanelGrid getPanelPopup2() {
        return panelPopup2;
    }

    public void setPanelPopup2(HtmlPanelGrid pp) {
        this.panelPopup2 = pp;
    }

    public ModalPopupPosicion() {
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
        agregarApiar agre = (agregarApiar) getBean("agregarApiar");
        agre.cargarColmenasAsignadas();
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

    public String acepta() {
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
//        try {
//            GestorEnfermedad gestorEnfermedad = new GestorEnfermedad();
//            Enfermedades e = (Enfermedades) getBean("Enfermedades");
//            if (e.isAgregar()) {
//                agregarEnfermedad agregar = (agregarEnfermedad) getBean("agregarEnfermedad");
//                gestorEnfermedad.insertUnTratamiento(agregar.getNumero(), this.getTratamiento());
//                agregar.updateTratamientos(agregar.getNumero());
//            }
//            if (e.isModificar()) {
//                modificarEnfermedad modificar = (modificarEnfermedad) getBean("modificarEnfermedad");
//                gestorEnfermedad.insertUnTratamiento(modificar.getNumero(), this.getTratamiento());
//                modificar.updateTratamientos(modificar.getNumero());
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.out.print("Error en Popup Tratamientos.acepta");
//        }
        this.getPanelPopup2().getChildren().clear();
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
        return null;
    }

    public String cancela() {
        //this.getPanelPopup1Bean().setShowModalPanel(false);
        this.destroy();
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
        this.panelPopup2.getChildren().clear();
        return null;
    }

    /**
     * @return the tratamiento
     */
    public int getTratamiento() {
        return tratamiento;
    }

    /**
     * @param tratamiento the tratamiento to set
     */
    public void setTratamiento(int tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String linkTratamientos() {
        return "tratamientos";
    }
}

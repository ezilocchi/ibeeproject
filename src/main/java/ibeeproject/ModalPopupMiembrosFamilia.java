/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.icesoft.faces.component.jsfcl.data.PopupBean;
import com.icesoft.faces.component.panelpopup.PanelPopup;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.familia.Familia;
import ibeeproject.model.familia.MiembroFamilia;
import ibeeproject.model.soporte.UtilMiembroFamilia;
import java.util.ArrayList;
import javax.faces.component.UIParameter;
import javax.faces.FacesException;

public class ModalPopupMiembrosFamilia extends AbstractFragmentBean {

    private String title;
    private UIParameter parametro;
    private PopupBean panelPopup1Bean = new PopupBean();
    private PanelPopup panelPopup1 = new PanelPopup();
    private ArrayList<UtilMiembroFamilia> utilMiembrosFamilia;
    //Esta es la familia que tengo en el modificar, las uso para mostrar los MiembrosFamilia
    // que no son de esta familia, COOL
    private Familia familia;

    private void _init() throws Exception {
    }

    public ModalPopupMiembrosFamilia() {
    }

    @Override
    public void init() {
// Perform initializations inherited from our superclass
        super.init();

        this.panelPopup1Bean.setShowModalPanel(false);
        this.panelPopup1.setRendered(false);
        this.panelPopup1.setVisible(false);

        try {
            _init();
        } catch (Exception e) {
            log("Page1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
    }

    @Override
    public void destroy() {
    }

    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
    }

    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
    }

    public UIParameter getParametro() {
        return parametro;
    }

    public void setParametro(UIParameter parametro) {
        this.parametro = parametro;
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

    /**
     * @return the panelPopup1Bean
     */
    public PopupBean getPanelPopup1Bean() {
        return panelPopup1Bean;
    }

    /**
     * @param panelPopup1Bean the panelPopup1Bean to set
     */
    public void setPanelPopup1Bean(PopupBean panelPopup1Bean) {
        this.panelPopup1Bean = panelPopup1Bean;
    }

    /**
     * @return the panelPopup1
     */
    public PanelPopup getPanelPopup1() {
        return panelPopup1;
    }

    /**
     * @param panelPopup1 the panelPopup1 to set
     */
    public void setPanelPopup1(PanelPopup panelPopup1) {
        this.panelPopup1 = panelPopup1;
    }

    /**
     * @return the familia
     */
    public Familia getFamilia() {
        return familia;
    }

    /**
     * @param familia the familia to set
     */
    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    /**
     * @return the utilMiembrosFamilia
     */
    public ArrayList<UtilMiembroFamilia> getUtilMiembrosFamilia() {
        return utilMiembrosFamilia;
    }

    /**
     * @param utilMiembrosFamilia the utilMiembrosFamilia to set
     */
    public void setUtilMiembrosFamilia(ArrayList<UtilMiembroFamilia> utilMiembrosFamilia) {
        this.utilMiembrosFamilia = utilMiembrosFamilia;
    }

    public void setearTamaño(int ancho, int alto) {
        String style = " width: " + ancho + "px; height: " + alto + "px;";
        this.getPanelPopup1().setStyle(style);
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Miembros de Familia");
    }

//    public void agregarMiembro(ValueChangeEvent event) {
//        if (this.getUtilMiembrosFamilia().contains((MiembroFamilia) this.getParametro().getValue())) {
//            this.getUtilMiembrosFamilia().remove((MiembroFamilia) this.getParametro().getValue());
//        } else {
//            this.getUtilMiembrosFamilia().add((UtilMiembroFamilia) this.getParametro().getValue());
//        }
//    }
    public String cerrarPopup() {
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
        this.getUtilMiembrosFamilia().clear();
        return null;
    }

    public String cancela() {
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.getPanelPopup1().setRendered(false);
        this.getUtilMiembrosFamilia().clear();
        return null;
    }

    public String acepta() {
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);

        //limpio el miembrosFamilia y lo cargo de vuelta
        getFamilia().getMiembrosFamilia().clear();
        //recorro todo el array para buscar los seleccionado=true
        for (int i = 0; i < getUtilMiembrosFamilia().size(); i++) {
            if (getUtilMiembrosFamilia().get(i).isSeleccionado()) {
                getFamilia().getMiembrosFamilia().add(getUtilMiembrosFamilia().get(i).getMiembroFamilia());
            }
        }
        return null;
    }

    public String nuevoMiembro() {
        setCabecera("Home &raquo; Miembro de Familia &raquo; Agregar");

        MiembrosFamilia m = (MiembrosFamilia) getBean("MiembrosFamilia");
        m.setAgregar(true);

        agregarMiembroFamilia bean = (agregarMiembroFamilia) getBean("agregarMiembroFamilia");
        bean.setMiembroFamilia(new MiembroFamilia());
        cerrarPopup();
        return "miembrosFamilia";
    }

    public String modif_action() {
        setCabecera("Home &raquo; Miembro de Familia &raquo; Modificar");

        MiembrosFamilia m = (MiembrosFamilia) getBean("MiembrosFamilia");
        m.setModificar(true);

        UtilMiembroFamilia aux = (UtilMiembroFamilia) this.getParametro().getValue();
        MiembroFamilia miembroFamilia = aux.getMiembroFamilia();

        modificarMiembroFamilia bean = (modificarMiembroFamilia) getBean("modificarMiembroFamilia");
        bean.setMiembroFamilia(miembroFamilia);
        cerrarPopup();
        return "miembrosFamilia";
    }

    public String delete_action() {
        setCabecera("Home &raquo; Miembro de Familia &raquo; Eliminar");

        MiembrosFamilia m = (MiembrosFamilia) getBean("MiembrosFamilia");
        m.setEliminar(true);

        UtilMiembroFamilia aux = (UtilMiembroFamilia) this.getParametro().getValue();
        MiembroFamilia miembroFamilia = aux.getMiembroFamilia();

        eliminarMiembroFamilia bean = (eliminarMiembroFamilia) getBean("eliminarMiembroFamilia");
        bean.setMiembroFamilia(miembroFamilia);
        cerrarPopup();
        return "miembrosFamilia";
    }

    public String query_action() {
        setCabecera("Home &raquo; Miembro de Familia &raquo; Consultar");

        MiembrosFamilia m = (MiembrosFamilia) getBean("MiembrosFamilia");
        m.setConsultar(true);

        UtilMiembroFamilia aux = (UtilMiembroFamilia) this.getParametro().getValue();
        MiembroFamilia miembroFamilia = aux.getMiembroFamilia();

        consultarMiembroFamilia bean = (consultarMiembroFamilia) getBean("consultarMiembroFamilia");
        bean.setMiembroFamilia(miembroFamilia);
        cerrarPopup();
        return "miembrosFamilia";
    }
}

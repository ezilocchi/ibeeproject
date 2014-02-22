/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.cajon.Cajon;
import ibeeproject.persistencia.GestorCajon;
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
 * @version consultarCajones.java
 * @version Created on 26-ago-2009, 18:40:06
 * @author farias.facundo
 */
public class consultarCajones extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private String campoBusq;
    private ArrayList cajones = new ArrayList();
//    private GestorCajon gestor = new GestorCajon();
    private UIParameter parametro;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarCajones() {
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

    public String add_action() {
        setCabecera("Home &raquo; Cajones &raquo; Agregar");
        Cajones c = (Cajones) getBean("Cajones");

        c.setAgregar(true);

        agregarCajon agregar = (agregarCajon) getBean("agregarCajon");
        agregar.setCajon(new Cajon());
        return null;
    }

    public String modif_action() {
        setCabecera("Home &raquo; Cajones &raquo; Modificar");
        Cajones c = (Cajones) getBean("Cajones");

        c.setModificar(true);
        Cajon cajon = (Cajon) this.parametro.getValue();
        modificarCajon modificar = (modificarCajon) getBean("modificarCajon");
        modificar.setCajon(cajon);
        return null;
    }

    public String delete_action() {
        setCabecera("Home &raquo; Cajones &raquo; Eliminar");
        Cajones c = (Cajones) getBean("Cajones");

        c.setEliminar(true);
        Cajon cajon = (Cajon) this.parametro.getValue();
        eliminarCajon eliminar = (eliminarCajon) getBean("eliminarCajon");
        eliminar.setCajon(cajon);
        return null;
    }

    public String query_action() {
        setCabecera("Home &raquo; Cajones &raquo; Consultar");
        Cajones c = (Cajones) getBean("Cajones");

        c.setConsultar(true);
        Cajon cajon = (Cajon) this.parametro.getValue();
        consultarCajon consultar = (consultarCajon) getBean("consultarCajon");
        consultar.setCajon(cajon);
        return null;
    }

    public String queryAll_action() {
        setCabecera();
        Cajones c = (Cajones) getBean("Cajones");
        c.setConsultarAll(true);

        return null;
    }

    /**
     * @return the cajones
     */
    public ArrayList getCajones() {
        return cajones;
    }

    /**
     * @param cajones the cajones to set
     */
    public void setCajones(ArrayList cajones) {
        this.cajones = cajones;
    }

    public void updateTable() {
        this.getCajones().clear();
        GestorCajon gestor = new GestorCajon();
        setCajones(gestor.getTodos());
    }

//    /**
//     * @return the gestor
//     */
//    public GestorCajon getGestor() {
//        return gestor;
//    }
//
//    /**
//     * @param gestor the gestor to set
//     */
//    public void setGestor(GestorCajon gestor) {
//        this.gestor = gestor;
//    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Cajones");
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
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.enfermedad.Enfermedad;
import ibeeproject.persistencia.GestorEnfermedad;
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
 * @version consultarEnfermedades.java
 * @version Created on 01-jul-2009, 20:02:29
 * @author farias.facundo 
 */
public class consultarEnfermedades extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private String campoBusq;
    private ArrayList enfermedades = new ArrayList();
    private GestorEnfermedad gestor = new GestorEnfermedad();
    private UIParameter parametro;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }

    public consultarEnfermedades() {
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
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
    }

    public String add_action() {
        this.setCabecera("Home &raquo; Enfermedades &raquo; Agregar");
        Enfermedades e = (Enfermedades) getBean("Enfermedades");
        e.setAgregar(true);
        agregarEnfermedad agregar = (agregarEnfermedad) getBean("agregarEnfermedad");
        agregar.setEnfermedad(new Enfermedad());
        return null;
    }

    public String modif_action() {

        this.setCabecera("Home &raquo; Enfermedades &raquo; Modificar");
        Enfermedades e = (Enfermedades) getBean("Enfermedades");
        e.setModificar(true);
        Enfermedad enfermedad = (Enfermedad) this.parametro.getValue();
        modificarEnfermedad modificar = (modificarEnfermedad) getBean("modificarEnfermedad");
        modificar.setEnfermedad(enfermedad);
        return null;
    }

    public String delete_action() {
        this.setCabecera("Home &raquo; Enfermedades &raquo; Eliminar");
        Enfermedades e = (Enfermedades) getBean("Enfermedades");
        e.setEliminar(true);
        Enfermedad enfermedad = (Enfermedad) this.parametro.getValue();
        eliminarEnfermedad eliminar = (eliminarEnfermedad) getBean("eliminarEnfermedad");
        eliminar.setEnfermedad(enfermedad);
        return null;
    }

    public String query_action() {
        this.setCabecera("Home &raquo; Enfermedades &raquo; Consultar");
        Enfermedades e = (Enfermedades) getBean("Enfermedades");
        e.setConsultar(true);
        Enfermedad enfermedad = (Enfermedad) this.parametro.getValue();
        consultarEnfermedad consultar = (consultarEnfermedad) getBean("consultarEnfermedad");
        consultar.setEnfermedad(enfermedad);
        return null;
    }

    public String queryAll_action() {
        this.setCabecera();
        Enfermedades e = (Enfermedades) getBean("Enfermedades");
        e.setConsultarAll(true);
        return null;
    }

    /**
     * @return the enfermedades
     */
    public ArrayList getEnfermedades() {
        return enfermedades;
    }

    /**
     * @param enfermedades the enfermedades to set
     */
    public void setEnfermedades(ArrayList enfermedades) {
        this.enfermedades = enfermedades;
    }

    public void updateTable() {
        this.getEnfermedades().clear();
        setEnfermedades(gestor.getTodos());
    }

   public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Enfermedades");
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

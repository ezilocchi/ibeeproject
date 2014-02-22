/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.apiar.Apiar;
import ibeeproject.persistencia.GestorApiar;
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
 * @version consultarApiar.java
 * @version Created on 27-jun-2009, 17:53:57
 * @author farias.facundo 
 */
public class consultarApiares extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private ArrayList apiares = new ArrayList();
    private GestorApiar gestor = new GestorApiar();
    private UIParameter parametro;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarApiares() {
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

    public void cleanTodo() {
        this.apiares.clear();
        this.apiares = this.gestor.getTodos();
        Apiares apiaresJSP = (Apiares) getBean("Apiares");
        apiaresJSP.cargarColmenas();
    }

    public String add_action() {
        setCabecera("Home &raquo; Apiares &raquo; Agregar");
        Apiares e = (Apiares) getBean("Apiares");
        e.setAgregar(true);
        agregarApiar agregar = (agregarApiar) getBean("agregarApiar");
        agregar.setApiar(new Apiar());
        Apiar ultimoApiar = (Apiar) this.gestor.getUltimo();
        agregar.getApiar().setIdApiar(ultimoApiar.getIdApiar() + 1);
        return null;
    }

    public String modif_action() {
        setCabecera("Home &raquo; Apiares &raquo; Modificar");
        Apiares e = (Apiares) getBean("Apiares");
        e.setModificar(true);
        e.cargarColmenas();
        e.cargarLayouts();
        e.cargarZonas();
        Apiar apiar = (Apiar) this.parametro.getValue();
        modificarApiar modificar = (modificarApiar) getBean("modificarApiar");
        modificar.setApiar(apiar);
        return null;
    }

    public String delete_action() {
        setCabecera("Home &raquo; Apiares &raquo; Eliminar");
        Apiares e = (Apiares) getBean("Apiares");
        e.setEliminar(true);
        Apiar apiar = (Apiar) this.parametro.getValue();
        eliminarApiar eliminar = (eliminarApiar) getBean("eliminarApiar");
        eliminar.setApiar(apiar);
        return null;
    }

    public String query_action() {
        setCabecera("Home &raquo; Apiares &raquo; Consultar");
        Apiares e = (Apiares) getBean("Apiares");
        e.setConsultar(true);
        Apiar apiar = (Apiar) this.parametro.getValue();
        consultarApiar consulta = (consultarApiar) getBean("consultarApiar");
        //Apiar apiar = (Apiar)this.getIdFilaSeleccionada();
        consulta.setApiar(apiar);
        return null;
    }

    public String queryAll_action() {
        setCabecera();
        Apiares e = (Apiares) getBean("Apiares");
        e.setConsultarAll(true);
        return "apiares";
    }

    /**
     * @return the enfermedades
     */
    public ArrayList getApiares() {
        return apiares;
    }

    /**
     * @param enfermedades the enfermedades to set
     */
    public void setApiares(ArrayList enfermedades) {
        this.apiares = enfermedades;
    }

    public void updateTable() {
        this.getApiares().clear();
        setApiares(gestor.getTodos());
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Apiares");
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

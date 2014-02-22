/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.enfermedad.Tratamiento;
import ibeeproject.persistencia.GestorTratamiento;
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
 * @version consultarTratamientos.java
 * @version Created on 23-ago-2009, 13:39:27
 * @author carranza.matias
 */

public class consultarTratamientos extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private String campoBusq;
    private ArrayList tratamientos = new ArrayList();
    private GestorTratamiento gestor = new GestorTratamiento();

    private UIParameter parametro;
    /**
    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarTratamientos() {
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
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
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
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
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
     * @return the tratamientos
     */
    public ArrayList getTratamientos() {
        return tratamientos;
    }

    /**
     * @param tratamientos the tratamientos to set
     */
    public void setTratamientos(ArrayList tratamientos) {
        this.tratamientos = tratamientos;
    }

    /**
     * @return the gestor
     */
    public GestorTratamiento getGestor() {
        return gestor;
    }

    /**
     * @param gestor the gestor to set
     */
    public void setGestor(GestorTratamiento gestor) {
        this.gestor = gestor;
    }

    public String add_action() {
        setCabecera("Home &raquo; Tratamientos &raquo; Agregar");
        Tratamientos t = (Tratamientos) getBean("Tratamientos");
        t.setAgregar(true);
        agregarTratamiento bean = (agregarTratamiento) getBean("agregarTratamiento");
        bean.setTratamiento(new Tratamiento());
        return null;
    }

    public String modif_action() {
        setCabecera("Home &raquo; Tratamientos &raquo; Modificar");
        Tratamientos t = (Tratamientos) getBean("Tratamientos");
        t.setModificar(true);
        Tratamiento tratamiento = (Tratamiento) this.parametro.getValue();
        modificarTratamiento bean = (modificarTratamiento) getBean("modificarTratamiento");
        bean.setTratamiento(tratamiento);
        return null;
    }

    public String delete_action() {
        setCabecera("Home &raquo; Tratamientos &raquo; Eliminar");
        Tratamientos t = (Tratamientos) getBean("Tratamientos");
        t.setEliminar(true);
        Tratamiento tratamiento = (Tratamiento) this.parametro.getValue();
        eliminarTratamiento bean = (eliminarTratamiento) getBean("eliminarTratamiento");
        bean.setTratamiento(tratamiento);
        return null;
    }

    public String query_action() {
        setCabecera("Home &raquo; Tratamientos &raquo; Consultar");
        Tratamientos t = (Tratamientos) getBean("Tratamientos");
        t.setConsultar(true);
        Tratamiento tratamiento = (Tratamiento) this.parametro.getValue();
        consultarTratamiento consultar = (consultarTratamiento) getBean("consultarTratamiento");
        consultar.setTratamiento(tratamiento);
        return null;
    }

    public String queryAll_action() {
        setCabecera();
        Tratamientos t = (Tratamientos) getBean("Tratamientos");
        t.setConsultarAll(true);

        return null;
    }

    public void updateTable()
    {
        this.getTratamientos().clear();
        setTratamientos(gestor.getTodos(0));
    }


    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Tratamientos");
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

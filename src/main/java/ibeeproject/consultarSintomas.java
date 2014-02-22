/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.enfermedad.Sintoma;
import ibeeproject.persistencia.GestorSintoma;
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
 * @version consultarSintomas.java
 * @version Created on 22-ago-2009, 17:43:04
 * @author carranza.matias
 */

public class consultarSintomas extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private String campoBusq;
    private ArrayList sintomas = new ArrayList();
    private GestorSintoma gestor = new GestorSintoma();

    private UIParameter parametro;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarSintomas() {
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
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
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
        setCabecera("Home &raquo; Síntomas &raquo; Agregar");
        Sintomas s = (Sintomas) getBean("Sintomas");
        s.setAgregar(true);
        agregarSintoma bean = (agregarSintoma) getBean("agregarSintoma");
        bean.setSintoma(new Sintoma());
        return null;
    }

    public String modif_action() {
        setCabecera("Home &raquo; Síntomas &raquo; Modificar");
        Sintomas s = (Sintomas) getBean("Sintomas");
        s.setModificar(true);
        Sintoma sintoma = (Sintoma) this.parametro.getValue();
        modificarSintoma bean = (modificarSintoma) getBean("modificarSintoma");
        bean.setSintoma(sintoma);
        return null;
    }

    public String delete_action() {
        setCabecera("Home &raquo; Síntomas &raquo; Eliminar");
        Sintomas s = (Sintomas) getBean("Sintomas");
        s.setEliminar(true);
        Sintoma sintoma = (Sintoma) this.parametro.getValue();
        eliminarSintoma bean = (eliminarSintoma) getBean("eliminarSintoma");
        bean.setSintoma(sintoma);
        return null;
    }

    public String query_action() {
        setCabecera("Home &raquo; Síntomas &raquo; Consultar");
        Sintomas s = (Sintomas) getBean("Sintomas");
        s.setConsultar(true);
        Sintoma sintoma = (Sintoma) this.parametro.getValue();
        consultarSintoma consultar = (consultarSintoma) getBean("consultarSintoma");
        consultar.setSintoma(sintoma);
        return null;
    }

    public String queryAll_action() {
        setCabecera();
        Sintomas s = (Sintomas) getBean("Sintomas");
        s.setConsultarAll(true);
        return null;
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
     * @return the sintomas
     */
    public ArrayList getSintomas() {
        return sintomas;
    }

    /**
     * @param sintomas the sintomas to set
     */
    public void setSintomas(ArrayList sintomas) {
        this.sintomas = sintomas;
    }

    /*selection listener is used when a row is selected on the data table component
     *@param e RowSelectorEvent */

    public void updateTable()
    {
        this.getSintomas().clear();
        setSintomas(gestor.getTodos(0));
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Síntomas");
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

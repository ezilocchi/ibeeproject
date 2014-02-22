/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.familia.Familia;
import ibeeproject.persistencia.GestorFamilia;
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
 * @version consultarFamilia.java
 * @version Created on 22-ago-2009, 15:40:07
 * @author burni.matias
 */
public class consultarFamilia extends AbstractFragmentBean {

    private String campoBusq;
    private ArrayList familias = new ArrayList();
    private GestorFamilia gestor = new GestorFamilia();
    private UIParameter parametro;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarFamilia() {
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

        try {
            _init();
        } catch (Exception e) {
            log("Page1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        this.updateTable();
        setCabecera();
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

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
    }

    /**
     * @return the familias
     */
    public ArrayList getFamilias() {
        return familias;
    }

    /**
     * @param familias the familias to set
     */
    public void setFamilias(ArrayList familias) {
        this.familias = familias;
    }

    /**
     * @return the gestor
     */
    public GestorFamilia getGestor() {
        return gestor;
    }

    /**
     * @param gestor the gestor to set
     */
    public void setGestor(GestorFamilia gestor) {
        this.gestor = gestor;
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

    public String queryAll_action() {
        setCabecera();

        Familias f = (Familias) getBean("Familias");
        f.setConsultarAll(true);
        f.setAgregar(false);
        f.setEliminar(false);
        f.setModificar(false);
        f.setConsultar(false);

        return null;
    }

    public String add_action() {
        setCabecera("Home &raquo; Familias &raquo; Agregar");
        Familias f = (Familias) getBean("Familias");
        f.setConsultar(false);
        f.setEliminar(false);
        f.setModificar(false);
        f.setConsultarAll(false);
        f.setAgregar(true);

        agregarFamilia agregar = (agregarFamilia) getBean("agregarFamilia");
        agregar.setFamilia(new Familia());
        return null;
    }

    public String modif_action() throws Exception {
        setCabecera("Home &raquo; Familias &raquo; Modificar");
        Familias f = (Familias) getBean("Familias");
        f.setAgregar(false);
        f.setConsultar(false);
        f.setEliminar(false);
        f.setConsultarAll(false);
        f.setModificar(true);

        this.updateTable();

        Familia familia = (Familia) this.getParametro().getValue();
        modificarFamilia modificar = (modificarFamilia) getBean("modificarFamilia");
        modificar.setFamilia(familia);
        return null;
    }

    public String delete_action() throws Exception {
        setCabecera("Home &raquo; Familias &raquo; Eliminar");
        Familias f = (Familias) getBean("Familias");
        f.setConsultarAll(false);
        f.setAgregar(false);
        f.setConsultar(false);
        f.setModificar(false);
        f.setEliminar(true);

        Familia familia = (Familia) this.getParametro().getValue();
        eliminarFamilia eliminar = (eliminarFamilia) getBean("eliminarFamilia");
        eliminar.setFamilia(familia);
        return null;
    }

    public String query_action() throws Exception {
        setCabecera("Home &raquo; Familias &raquo; Consultar");
        Familias f = (Familias) getBean("Familias");
        f.setAgregar(false);
        f.setEliminar(false);
        f.setModificar(false);
        f.setConsultarAll(false);
        f.setConsultar(true);

        Familia familia = (Familia) this.getParametro().getValue();
        consultarFamiliaUna consultar = (consultarFamiliaUna) getBean("consultarFamiliaUna");
        consultar.setFamilia(familia);
        return null;
    }

    public void updateTable() {
        this.getFamilias().clear();
        setFamilias(gestor.getTodos());
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Familias");
    }
}

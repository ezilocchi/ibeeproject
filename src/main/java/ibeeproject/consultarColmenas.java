/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.apiar.Colmena;
import ibeeproject.persistencia.GestorColmena;
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
 * @version consultarColmenas.java
 * @version Created on 30-ago-2009, 17:10:38
 * @author carranza.matias
 */

public class consultarColmenas extends AbstractFragmentBean {
    private ArrayList<Colmena> colmenas = new ArrayList();
//    private GestorColmena gestor = new GestorColmena();
    private UIParameter parametro;

    private String campoBusq;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarColmenas() {
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
     * @return the colmenas
     */
    public ArrayList getColmenas() {
        return colmenas;
    }

    /**
     * @param colmenas the colmenas to set
     */
    public void setColmenas(ArrayList colmenas) {
        this.colmenas = colmenas;
    }

//    /**
//     * @return the gestor
//     */
//    public GestorColmena getGestor() {
//        return gestor;
//    }
//
//    /**
//     * @param gestor the gestor to set
//     */
//    public void setGestor(GestorColmena gestor) {
//        this.gestor = gestor;
//    }

    public void updateTable() {
        this.getColmenas().clear();
        GestorColmena gestor = new GestorColmena();
        setColmenas(gestor.getTodos());
    }

    public String add_action() {
        setCabecera("Home &raquo; Colmenas &raquo; Agregar");
        Colmenas c = (Colmenas) getBean("Colmenas");
        c.setAgregar(true);
        agregarColmena agregar = (agregarColmena) getBean("agregarColmena");
        agregar.setColmena(new Colmena());
        return null;
    }

    public String modif_action() {
        setCabecera("Home &raquo; Colmenas &raquo; Modificar");
        Colmenas c = (Colmenas) getBean("Colmenas");

        c.setModificar(true);
        Colmena colmena = (Colmena) this.parametro.getValue();
        modificarColmena modificar = (modificarColmena) getBean("modificarColmena");
        modificar.setCambiarCajon(false);
        modificar.setNombreCambiarCajon("Cambiar Cajón");
        modificar.setCambiarFamilia(false);
        modificar.setNombreCambiarFamilia("Cambiar Familia");
        modificar.setColmena(new Colmena());
        modificar.setColmena(colmena);
        
        return null;
    }

    public String delete_action() {
        setCabecera("Home &raquo; Colmenas &raquo; Eliminar");
        Colmenas c = (Colmenas) getBean("Colmenas");

        c.setEliminar(true);
        Colmena colmena = (Colmena) this.parametro.getValue();
        eliminarColmena eliminar = (eliminarColmena) getBean("eliminarColmena");
        eliminar.setColmena(new Colmena());
        eliminar.setColmena(colmena);
        return null;
    }

    public String query_action() {
        setCabecera("Home &raquo; Colmenas &raquo; Consultar");
        Colmenas c = (Colmenas) getBean("Colmenas");
        c.setConsultar(true);

        Colmena colmena = (Colmena) this.parametro.getValue();

        consultarColmena consultar = (consultarColmena) getBean("consultarColmena");
        consultar.setColmena(new Colmena());
        consultar.setColmena(colmena);
        return null;
    }

    public String queryAll_action() {
        setCabecera();
        Colmenas c = (Colmenas) getBean("Colmenas");
        c.setConsultarAll(true);
        
        return null;
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Colmenas");
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.zona.Alarma;
import ibeeproject.persistencia.GestorAlarma;
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
 * @version consultarAlarmas.java
 * @version Created on 12-ene-2010, 21:35:18
 * @author carranza.matias
 */

public class consultarAlarmas extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private String campoBusq;
    private ArrayList alarmas = new ArrayList();
    private GestorAlarma gestor = new GestorAlarma();

    private UIParameter parametro;
    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarAlarmas() {
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
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
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
     * @return the alarmas
     */
    public ArrayList getAlarmas() {
        return alarmas;
    }

    /**
     * @param alarmas the alarmas to set
     */
    public void setAlarmas(ArrayList alarmas) {
        this.alarmas = alarmas;
    }

    /**
     * @return the gestor
     */
    public GestorAlarma getGestor() {
        return gestor;
    }

    /**
     * @param gestor the gestor to set
     */
    public void setGestor(GestorAlarma gestor) {
        this.gestor = gestor;
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

    public String add_action() {
        setCabecera("Home &raquo; Alarmas &raquo; Agregar");
        Alarmas a = (Alarmas) getBean("Alarmas");
        a.setAgregar(true);
        agregarAlarma bean = (agregarAlarma) getBean("agregarAlarma");
        bean.setAlarma(new Alarma());
        return null;
    }

    public String modif_action() {
        setCabecera("Home &raquo; Alarmas &raquo; Modificar");
        Alarmas a = (Alarmas) getBean("Alarmas");
        a.setModificar(true);
        Alarma alarma = (Alarma) this.parametro.getValue();
        modificarAlarma bean = (modificarAlarma) getBean("modificarAlarma");
        bean.setAlarma(alarma);
        return null;
    }

    public String delete_action() {
//        setCabecera("Home &raquo; Alarmas &raquo; Desactivar");
//        Alarmas a = (Alarmas) getBean("Alarmas");
//        a.setEliminar(true);
//        Alarma alarma = (Alarma) this.parametro.getValue();
//        eliminarAlarma bean = (eliminarAlarma) getBean("eliminarAlarma");
//        bean.setAlarma(alarma);
        return null;
    }

    public String desactivar_action() {
        GestorAlarma gestorAlarma = new GestorAlarma();
        gestorAlarma.desactivarUno((Alarma) this.parametro.getValue());

        consultarAlarmas cons = (consultarAlarmas) getBean("consultarAlarmas");
        cons.updateTable();
        cons.queryAll_action();
        return null;
    }

    public String activar_action() {
        GestorAlarma gestorAlarma = new GestorAlarma();
        gestorAlarma.activarUno((Alarma) this.parametro.getValue());

        consultarAlarmas cons = (consultarAlarmas) getBean("consultarAlarmas");
        cons.updateTable();
        cons.queryAll_action();
        return null;
    }

    public String query_action() {
        setCabecera("Home &raquo; Alarmas &raquo; Consultar");
        Alarmas a = (Alarmas) getBean("Alarmas");
        a.setConsultar(true);
        Alarma alarma = (Alarma) this.parametro.getValue();
        consultarAlarma consultar = (consultarAlarma) getBean("consultarAlarma");
        consultar.setAlarma(alarma);
        return null;
    }

    public String queryAll_action() {
        setCabecera();
        Alarmas a = (Alarmas) getBean("Alarmas");
        a.setConsultarAll(true);
        return null;
    }

    public void updateTable()
    {
        this.getAlarmas().clear();
        setAlarmas(gestor.getTodos());
    }
    
    public String activos_action() {
        this.getAlarmas().clear();
        setAlarmas(gestor.getActivos(1));
        return null;
    }

    public String desactivos_action() {
        this.getAlarmas().clear();
        setAlarmas(gestor.getActivos(0));
        return null;
    }

    public String todos_action() {
        this.getAlarmas().clear();
        setAlarmas(gestor.getTodos());
        return null;
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Alarmas");
    }

}

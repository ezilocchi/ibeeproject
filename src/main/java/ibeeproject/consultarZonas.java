/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.zona.Zona;
import ibeeproject.persistencia.GestorZona;
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
 * @version consultarZonas.java
 * @version Created on 13/03/2010, 10:36:20
 * @author CyberShark
 */
public class consultarZonas extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private ArrayList zonas = new ArrayList();
    private GestorZona gestor = new GestorZona();
    private UIParameter parametro;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarZonas() {
    }

    /**
     * <p>Callback method that is called whenever a page containing
     * this page fragment is navigated to, either directly via a URL,
     * or indirectly via page navigation.  Override this method to acquire
     * resources that will be needed for event handlers and lifecycle methods.</p>
     * 
     * <p>The default implementation does nothing.</p>
     */
    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Zonas");
    }

    public void updateTable() {
        this.getZonas().clear();
        this.setZonas(gestor.getTodos());
    }

    public UIParameter getParametro() {
        return parametro;
    }

    public void setParametro(UIParameter parametro) {
        this.parametro = parametro;
    }

    public ArrayList getZonas() {
        return zonas;
    }

    public void setZonas(ArrayList zonas) {
        this.zonas = zonas;
    }

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
        this.zonas.clear();
        this.zonas = new ArrayList();
        gestor = new GestorZona();
        this.zonas = gestor.getTodos();
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

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

     public void cleanTodo()
        {
        this.zonas.clear();
        this.zonas = new ArrayList();
        gestor = new GestorZona();
        this.zonas = gestor.getTodos();
        Zonas e = (Zonas) getBean("Zonas");
        e.cargarTipoAgroquimico();
        e.cargarTipoFlora();
        }

    public String add_action() {
        setCabecera("Home &raquo; Zonas &raquo; Agregar");
        Zonas e = (Zonas) getBean("Zonas");
        e.setAgregar(true);
        agregarZona agregar = (agregarZona) getBean("agregarZona");
        gestor = new GestorZona();
        Zona zona = (Zona) this.gestor.getUltimo();
        Zona nuevaZona = new Zona();
        nuevaZona.setIdZona(zona.getIdZona()+1);
        agregar.setZona(nuevaZona);
        mostrarZona mostrar = (mostrarZona) getBean("mostrarZona");
        mostrar.setDibujar(false);
        mostrar.getPuntos().clear();
        mostrar.getMapa().getChildren().clear();
        mostrar.getMark().setRendered(true);
        mostrar.getMapa().getChildren().add(mostrar.getMark());
        mostrar.setAgregar(true);
        mostrar.setModificar(false);
        return "agregarZona";
    }

    public String modif_action() {
        setCabecera("Home &raquo; Zonas &raquo; Modificar");
        Zonas e = (Zonas) getBean("Zonas");
        e.setModificar(true);
        e.cargarTipoAgroquimico();
        e.cargarTipoFlora();
        Zona zona = (Zona) this.parametro.getValue();
        mostrarZona mostrar = (mostrarZona) getBean("mostrarZona");
        mostrar.setDibujar(false);
        mostrar.dibujarPoligono(zona.getIdZona());
        mostrar.setAgregar(false);
        mostrar.setModificar(true);
        modificarZona modificar = (modificarZona) getBean("modificarZona");
        if(zona.getTipoAgroquimico().getIdTipoAgroquimico()!=-1)
        modificar.setIdtipoZona(zona.getTipoAgroquimico().getIdTipoAgroquimico());
        else if(zona.getTipoAgroquimico().getIdTipoAgroquimico()!=-1)
        modificar.setIdtipoZona(zona.getTipoFlora().getIdTipoFlora());

        modificar.setZona(zona);
        return null;
    }


    public String delete_action() {
        setCabecera("Home &raquo; Zonas &raquo; Eliminar");
        Zonas e = (Zonas) getBean("Zonas");
        e.setEliminar(true);
        Zona zona = (Zona) this.parametro.getValue();
        mostrarZona mostrar = (mostrarZona) getBean("mostrarZona");
        mostrar.setDibujar(false);
        mostrar.dibujarPoligono(zona.getIdZona());
        eliminarZona eliminar = (eliminarZona) getBean("eliminarZona");
        eliminar.setZona(zona);
        return null;
    }

    public String query_action() {
        setCabecera("Home &raquo; Zonas &raquo; Consultar");
        Zonas e = (Zonas) getBean("Zonas");
        e.setConsultar(true);
        Zona zona = (Zona) this.parametro.getValue();
        mostrarZona mostrar = (mostrarZona) getBean("mostrarZona");
        mostrar.setDibujar(false);
        mostrar.dibujarPoligono(zona.getIdZona());
        consultarZona consulta = (consultarZona) getBean("consultarZona");
        consulta.setZona(zona);

        return null;
    }

    public String queryAll_action() {
        setCabecera();
        Zonas e = (Zonas) getBean("Zonas");
        e.setConsultarAll(true);
        return "zonas";
    }
}

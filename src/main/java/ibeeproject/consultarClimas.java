/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.zona.Clima;
import ibeeproject.model.zona.Zona;
import ibeeproject.persistencia.GestorClima;
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
 * @version consultarClimas.java
 * @version Created on 19/03/2010, 17:08:07
 * @author CyberShark
 */
public class consultarClimas extends AbstractFragmentBean {

    private ArrayList Climas = new ArrayList();
    private GestorClima gestor = new GestorClima();
    private GestorZona gestZ = new GestorZona();
    private UIParameter parametro;
    private ArrayList climasFiltrados = new ArrayList();
    private int selected = 1;
    private boolean cargando = true;

    public ArrayList getClimasFiltrados() {
        return climasFiltrados;
    }

    public void setClimasFiltrados(ArrayList climasFiltrados) {
        this.climasFiltrados = climasFiltrados;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public String filtarTabla() {
        System.out.println("Agrego Zona filtrada: ");
        this.climasFiltrados = new ArrayList();
        Zona zonaElegida = (Zona) this.gestZ.getUno(this.selected);
        System.out.println("Filtrao para la zona: "+ zonaElegida.getIdZona()+ " Denominación" + zonaElegida.getZona());
        for (int i = 0; i < this.Climas.size(); i++) {
            Clima clima = (Clima) this.Climas.get(i);
            if (clima.getIdZona() == zonaElegida.getIdZona()) {
                this.climasFiltrados.add(clima);
                System.out.println("Agrego Zona filtrada: " + clima.getIdClima());
            }
        }
        return null;
    }

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarClimas() {
        this.Climas = new ArrayList();

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
        ca.setPath("Home &raquo; Climas");
    }

    public void updateTable() {
        this.getClimas().clear();
        this.setClimas(gestor.getTodos());
    }

    public UIParameter getParametro() {
        return parametro;
    }

    public void setParametro(UIParameter parametro) {
        this.parametro = parametro;
    }

    public ArrayList getClimas() {
        return Climas;
    }

    public void setClimas(ArrayList Climas) {
        this.Climas = Climas;
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
        this.Climas.clear();
        this.Climas = new ArrayList();
        gestor = new GestorClima();
        this.Climas = gestor.getTodos();
        this.climasFiltrados = this.Climas;
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

    public void cleanTodo() {
        this.Climas.clear();
        this.Climas = new ArrayList();
        gestor = new GestorClima();
        this.Climas = gestor.getTodos();
        Climas e = (Climas) getBean("Climas");

    }

    public String add_action() {
        setCabecera("Home &raquo; Zona &raquo; Agregar");
        Climas e = (Climas) getBean("Climas");
        e.setAgregar(true);
        agregarZona agregar = (agregarZona) getBean("agregarZona");
//        gestor = new GestorClimas();
//        Zona zona = (Zona) this.gestor.getUltimo();
//        Zona nuevaZona = new Zona();
//        nuevaZona.setIdZona(zona.getIdZona()+1);
//        agregar.setZona(nuevaZona);
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
        setCabecera("Home &raquo; Zona &raquo; Modificar");
        Climas e = (Climas) getBean("Climas");
//        e.setModificar(true);
//        e.cargarTipoAgroquimico();
//        e.cargarTipoFlora();
//        Zona zona = (Zona) this.parametro.getValue();
        mostrarZona mostrar = (mostrarZona) getBean("mostrarZona");
        mostrar.setDibujar(false);
        //mostrar.dibujarPoligono(zona.getIdZona());
        mostrar.setAgregar(false);
        mostrar.setModificar(true);
        modificarZona modificar = (modificarZona) getBean("modificarZona");
//        if(zona.getTipoAgroquimico().getIdTipoAgroquimico()!=-1)
//        modificar.setIdtipoZona(zona.getTipoAgroquimico().getIdTipoAgroquimico());
//        else if(zona.getTipoAgroquimico().getIdTipoAgroquimico()!=-1)
//        modificar.setIdtipoZona(zona.getTipoFlora().getIdTipoFlora());
//
//        modificar.setZona(zona);
        return null;
    }

    public String delete_action() {
        setCabecera("Home &raquo; Zona &raquo; Eliminar");
        Climas e = (Climas) getBean("Climas");
//        e.setEliminar(true);
//        Zona zona = (Zona) this.parametro.getValue();
        mostrarZona mostrar = (mostrarZona) getBean("mostrarZona");
        mostrar.setDibujar(false);
//        mostrar.dibujarPoligono(zona.getIdZona());
        eliminarZona eliminar = (eliminarZona) getBean("eliminarZona");
//        eliminar.setZona(zona);
        return null;
    }

    public String query_action() {
        setCabecera("Home &raquo; Zona &raquo; Consultar");
        Climas e = (Climas) getBean("Climas");
        //e.setConsultar(true);
        //Zona zona = (Zona) this.parametro.getValue();
        mostrarZona mostrar = (mostrarZona) getBean("mostrarZona");
        mostrar.setDibujar(false);
        // mostrar.dibujarPoligono(zona.getIdZona());
        consultarZona consulta = (consultarZona) getBean("consultarZona");
        //consulta.setZona(zona);

        return null;
    }

    public String queryAll_action() {
        setCabecera();
        Climas e = (Climas) getBean("Climas");
        e.setConsultarAll(true);
        return "Climas";
    }
}

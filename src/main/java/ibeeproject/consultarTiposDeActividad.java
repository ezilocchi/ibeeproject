/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.actividad.TipoActividad;
import ibeeproject.persistencia.GestorTipoActividad;
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
 * @version consultarTiposDeActividad.java
 * @version Created on 05-sep-2009, 18:01:38
 * @author farias.facundo
 */

public class consultarTiposDeActividad extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private String campoBusq;
    private ArrayList tiposDeActividad = new ArrayList();
    private GestorTipoActividad gestor = new GestorTipoActividad();
    private UIParameter parametro;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarTiposDeActividad() {
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


    public String add_action() {
        setCabecera("Home &raquo; Tipos de Actividad &raquo; Agregar");
        TiposDeActividad t = (TiposDeActividad) getBean("TiposDeActividad");
        t.setAgregar(true);
        agregarTipoDeActividad agregar = (agregarTipoDeActividad) getBean("agregarTipoDeActividad");
        agregar.setTipoActividad(new TipoActividad());
        return null;
    }

    public String modif_action() {
        setCabecera("Home &raquo; Tipos de Actividad &raquo; Modificar");
        TiposDeActividad t = (TiposDeActividad) getBean("TiposDeActividad");
        t.setModificar(true);
        TipoActividad tipoActividad = (TipoActividad) this.parametro.getValue();
        modificarTipoDeActividad modificar = (modificarTipoDeActividad) getBean("modificarTipoDeActividad");
        modificar.setTipoActividad(tipoActividad);
        return null;
    }

    public String delete_action() {
        setCabecera("Home &raquo; Tipos de Actividad &raquo; Eliminar");
        TiposDeActividad t = (TiposDeActividad) getBean("TiposDeActividad");
        t.setEliminar(true);
        TipoActividad tipoActividad = (TipoActividad) this.parametro.getValue();
        eliminarTipoDeActividad eliminar = (eliminarTipoDeActividad) getBean("eliminarTipoDeActividad");
        eliminar.setTipoDeActividad(tipoActividad);
        return null;
    }

    public String query_action() {
        setCabecera("Home &raquo; Tipos de Actividad &raquo; Consultar");
        TiposDeActividad t = (TiposDeActividad) getBean("TiposDeActividad");
        t.setConsultar(true);
        TipoActividad tipoActividad = (TipoActividad) this.parametro.getValue();
        consultarTipoDeActividad consultar = (consultarTipoDeActividad) getBean("consultarTipoDeActividad");
        consultar.setTipoDeActividad(tipoActividad);
        return null;
    }

    public String queryAll_action() {
        setCabecera();
        TiposDeActividad t = (TiposDeActividad) getBean("TiposDeActividad");
        t.setConsultarAll(true);
        return null;
    }

    public void updateTable()
    {
        try {
            this.getTiposDeActividad().clear();
            this.setTiposDeActividad(gestor.getTodos());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: consultarTiposDeActividad! (updateTable)");
        }
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Tipos de Actividad");
    }

    /**
     * @return the tiposDeActividad
     */
    public ArrayList getTiposDeActividad() {
        return tiposDeActividad;
    }

    /**
     * @param tiposDeActividad the tiposDeActividad to set
     */
    public void setTiposDeActividad(ArrayList tiposDeActividad) {
        this.tiposDeActividad = tiposDeActividad;
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

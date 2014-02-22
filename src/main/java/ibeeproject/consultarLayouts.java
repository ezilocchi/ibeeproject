/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlDataTable;
import com.icesoft.faces.component.jsfcl.data.DefaultTableDataModel;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.apiar.Layout;
import javax.faces.FacesException;
import javax.faces.component.UIParameter;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version consultarLayouts.java
 * @version Created on 19-sep-2009, 19:44:12
 * @author Administrador
 */
public class consultarLayouts extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private Object filaSeleccionada = -1;

    // Botones de acción
    private HtmlCommandButton btn_add = new HtmlCommandButton();
    private HtmlCommandButton btn_modif = new HtmlCommandButton();
    private HtmlCommandButton btn_elim = new HtmlCommandButton();
    private HtmlCommandButton btn_ver = new HtmlCommandButton();

    // Tabla
    private HtmlDataTable dataTable1 = new HtmlDataTable();
    private UIParameter parametro;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    private DefaultTableDataModel dataTable1Model = new DefaultTableDataModel();

    public DefaultTableDataModel getDataTable1Model() {
        return dataTable1Model;
    }

    public void setDataTable1Model(DefaultTableDataModel dtdm) {
        this.dataTable1Model = dtdm;
    }
    // </editor-fold>

    public consultarLayouts() {
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
    }

    public String add_action() {
        setCabecera("Home &raquo; Layouts &raquo; Agregar");
        Layouts e = (Layouts) getBean("Layouts");
        e.setConsultarAll(false);
        e.setConsultar(false);
        e.setEliminar(false);
        e.setModificar(false);
        e.setAgregar(true);
        if (this.getBtn_add().getValue() == null) {
            // Valor por defecto cuando no tiene nada seleccionado
            this.setIdFilaSeleccionada(-1);
        } else {
            // Almaceno el valor del indice seleccionado
            this.setIdFilaSeleccionada(this.parametro.getValue());
        }
        agregarLayout agregar = (agregarLayout) getBean("agregarLayout");
        return null;
    }

    public String modif_action() {
        setCabecera("Home &raquo; Layouts &raquo; Modificar");
        Layouts e = (Layouts) getBean("Layouts");
        e.setConsultarAll(false);
        e.setAgregar(false);
        e.setConsultar(false);
        e.setEliminar(false);
        e.setModificar(true);
        if (this.getBtn_modif().getValue() == null) {
            // Valor por defecto cuando no tiene nada seleccionado
            this.setIdFilaSeleccionada(-1);
        } else {
            // Almaceno el valor del indice seleccionado
            this.setIdFilaSeleccionada(this.getBtn_modif().getValue());
        }
        modificarLayout modificar = (modificarLayout) getBean("modificarLayout");
        Layout laySel = (Layout) this.getParametro().getValue();
        modificar.setLayout(laySel);
        return null;
    }

    public String delete_action() {
        setCabecera("Home &raquo; Layouts &raquo; Eliminar");
        Layouts e = (Layouts) getBean("Layouts");
        e.setConsultarAll(false);
        e.setAgregar(false);
        e.setConsultar(false);
        e.setModificar(false);
        e.setEliminar(true);
        if (this.getBtn_elim().getValue() == null) {
            // Valor por defecto cuando no tiene nada seleccionado
            this.setIdFilaSeleccionada(-1);
        } else {
            // Almaceno el valor del indice seleccionado
            this.setIdFilaSeleccionada(this.parametro.getValue());
        }
        eliminarLayout eliminar = (eliminarLayout) getBean("eliminarLayout");
        Layout laySel = (Layout) this.getParametro().getValue();
        eliminar.setLayout(laySel);
        return null;
    }

    public String query_action() {
        setCabecera("Home &raquo; Layouts &raquo; Consultar");
        Layouts e = (Layouts) getBean("Layouts");
        e.setConsultarAll(false);
        e.setAgregar(false);
        e.setEliminar(false);
        e.setModificar(false);
        e.setConsultar(true);
        if (this.getBtn_ver().getValue() == null) {
            // Valor por defecto cuando no tiene nada seleccionado
            this.setIdFilaSeleccionada(-1);
        } else {
            // Almaceno el valor del indice seleccionado
            this.setIdFilaSeleccionada(this.parametro.getValue());
        }
        consultarLayout consulta = (consultarLayout) getBean("consultarLayout");
        consulta.setLayout((Layout) this.parametro.getValue());
        return null;
    }

    public String queryAll_action() {
        setCabecera("Home &raquo; Layouts");
        Layouts e = (Layouts) getBean("Layouts");
        e.setConsultarAll(true);
        e.setAgregar(false);
        e.setEliminar(false);
        e.setModificar(false);
        e.setConsultar(false);
        if (this.getBtn_ver().getValue() == null) {
            // Valor por defecto cuando no tiene nada seleccionado
            this.setIdFilaSeleccionada(-1);
        } else {
            // Almaceno el valor del indice seleccionado
            this.setIdFilaSeleccionada(this.parametro.getValue());
        }
        return null;
    }

    /**
     * @return the btn_add
     */
    public HtmlCommandButton getBtn_add() {
        return btn_add;
    }

    /**
     * @param btn_add the btn_add to set
     */
    public void setBtn_add(HtmlCommandButton btn_add) {
        this.btn_add = btn_add;
    }

    /**
     * @return the btn_modif
     */
    public HtmlCommandButton getBtn_modif() {
        return btn_modif;
    }

    /**
     * @param btn_modif the btn_modif to set
     */
    public void setBtn_modif(HtmlCommandButton btn_modif) {
        this.btn_modif = btn_modif;
    }

    /**
     * @return the btn_elim
     */
    public HtmlCommandButton getBtn_elim() {
        return btn_elim;
    }

    /**
     * @param btn_elim the btn_elim to set
     */
    public void setBtn_elim(HtmlCommandButton btn_elim) {
        this.btn_elim = btn_elim;
    }

    /**
     * @return the btn_ver
     */
    public HtmlCommandButton getBtn_ver() {
        return btn_ver;
    }

    /**
     * @param btn_ver the btn_ver to set
     */
    public void setBtn_ver(HtmlCommandButton btn_ver) {
        this.btn_ver = btn_ver;
    }

    /**
     * @return the dataTable1
     */
    public HtmlDataTable getDataTable1() {
        return dataTable1;
    }

    /**
     * @param dataTable1 the dataTable1 to set
     */
    public void setDataTable1(HtmlDataTable dataTable1) {
        this.dataTable1 = dataTable1;
    }

    /**
     * @return the idFilaSeleccionada
     */
    public Object getIdFilaSeleccionada() {
        return filaSeleccionada;
    }

    /**
     * @param idFilaSeleccionada the idFilaSeleccionada to set
     */
    public void setIdFilaSeleccionada(Object idFilaSeleccionada) {
        this.filaSeleccionada = idFilaSeleccionada;
    }

    public void updateTable() {
        Layouts lay = (Layouts) getBean("Layouts");
        lay.cargarLayouts();
        ;
    }

    public void setCabecera(String ruta) {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath(ruta);
    }

    public void setCabecera() {
        cabecera ca = (cabecera) getBean("cabecera");
        ca.setPath("Home &raquo; Layouts");
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

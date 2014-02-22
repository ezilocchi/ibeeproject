/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlDataTable;
import com.icesoft.faces.component.ext.HtmlPanelGrid;
import com.icesoft.faces.component.ext.RowSelectorEvent;
import com.icesoft.faces.component.panellayout.PanelLayout;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.apiar.Apiar;
import ibeeproject.model.apiar.Colmena;
import ibeeproject.persistencia.GestorColmena;
import ibeeproject.persistencia.GestorHistorialEstadoApiar;
import ibeeproject.persistencia.GestorPosicion;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.FacesException;

/**
 *
 * @author Administrador
 */
public class consultarApiar extends AbstractFragmentBean {

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private Apiar apiar;
    private String idApiar;
    private Date fechaCreacion;
    private ArrayList layouts = new ArrayList();
    private int layout;
    private int cantidad_max;
    private String longitud;
    private String latitud;
    private HtmlCommandButton btn_elim1;
    private ArrayList posicionesLayout = new ArrayList();
    private ArrayList colmenasAsignadas = new ArrayList();
    private GestorPosicion gestPos;
    private GestorColmena gestC;
    private Colmena idFilaSeleccionada;
    private HtmlCommandButton btn_add = new HtmlCommandButton();
    private HtmlCommandButton btn_modif = new HtmlCommandButton();
    private HtmlCommandButton btn_elim = new HtmlCommandButton();
    private HtmlCommandButton btn_ver = new HtmlCommandButton();
    private HtmlDataTable dataTable1 = new HtmlDataTable();
    private HtmlDataTable dataTable2 = new HtmlDataTable();
    private HtmlDataTable dataTable3 = new HtmlDataTable();
    private PanelLayout panel;
    private HtmlPanelGrid panelGrid;
    private boolean confirmacion;

    public Apiar getApiar() {
        return apiar;
    }

    public void setApiar(Apiar apiar) {
        this.apiar = apiar;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(boolean confirmacion) {
        this.confirmacion = confirmacion;
    }

    public PanelLayout getPanel() {
        return panel;
    }

    public HtmlPanelGrid getPanelGrid() {
        return panelGrid;
    }

    public void setPanelGrid(HtmlPanelGrid panelGrid) {
        this.panelGrid = panelGrid;
    }

    public void setPanel(PanelLayout panel) {
        this.panel = panel;
    }

    public HtmlDataTable getDataTable3() {
        return dataTable3;
    }

    public void setDataTable3(HtmlDataTable dataTable3) {
        this.dataTable3 = dataTable3;
    }

    public ArrayList getPosicionesLayout() {
        return posicionesLayout;
    }

    public void setPosicionesLayout(ArrayList posicionesLayout) {
        this.posicionesLayout = new ArrayList();
        this.posicionesLayout = posicionesLayout;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public HtmlDataTable getDataTable2() {
        return dataTable2;
    }

    public void setDataTable2(HtmlDataTable dataTable2) {
        this.dataTable2 = dataTable2;
    }

    public int getCantidad_asignada() {
        return this.apiar.getColmenasAsignadas().size();
    }

    public void setCantidad_asignada(int cantidad_asignada) {
        this.cantidad_asignada = cantidad_asignada;
    }

    public int getCantidad_max() {
        return cantidad_max;
    }

    public void setCantidad_max(int cantidad_max) {
        this.cantidad_max = cantidad_max;
    }
    private int cantidad_asignada;

    public ArrayList getColmenasAsignadas() {
        return colmenasAsignadas;
    }

    public void setColmenasAsignadas(ArrayList colmenasAsignadas) {
        this.colmenasAsignadas = colmenasAsignadas;
    }

    public String getIdApiar() {
        return idApiar;
    }

    public void setIdApiar(String IDApiar) {
        this.idApiar = IDApiar;
    }

    public ArrayList getLayouts() {
        return layouts;
    }

    public void setLayouts(ArrayList layouts) {
        this.layouts = layouts;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int lay) {
        this.layout = lay;
        this.cargarPosiciones();
    }

    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarApiar() {
        this.cargarPosiciones();
    }

    public void cargarPosiciones() {
        gestPos = new GestorPosicion();
        this.posicionesLayout = gestPos.getUnoLayout(this.getLayout());
        this.setCantidad_max(this.getPosicionesLayout().size());
    }

    public void setearCampos(Apiar apiar) {
        this.cargarPosiciones();
        gestC = new GestorColmena();
        this.colmenasAsignadas = gestC.getAsignadas(apiar.getIdApiar());
    }

    public String mostrarMensaje() {
        // Hago visible la ventana
        ModalPopupUbicacion pop = (ModalPopupUbicacion) getBean("ModalPopUpConfirmacion");
        pop.getPanelPopup1Bean().setShowModalPanel(true);
        pop.getPanelPopup1().setVisible(true);
        pop.getPanelPopup1().setRendered(true);

        // Configuro el Popup
        pop.setTitle("Resultado de la transaccion");
        pop.setearTamaño(225, 250);


        return null;
    }

    public String actionCancelar() {
        // Cancela
        consultarApiares consulta = (consultarApiares) getBean("consultarApiares");
        consulta.updateTable();
        consulta.queryAll_action();
        return "apiares";
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
        this.cargarPosiciones();
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

    public String confirmar() {
        this.setConfirmacion(true);
        return null;
    }

    public int getRowByID(ArrayList array, Integer ID) {
        for (int i = 0; i < array.size(); i++) {
            Colmena colmena = (Colmena) array.get(i);
            if (colmena.getIdColmena() == ID.intValue()) {
                return i;
            }
        }
        return 0;
    }

    /*selection listener is used when a row is selected on the data table component
     *@param e RowSelectorEvent */
    public void rowSelector1_processAction(RowSelectorEvent rse) {
        int selectedRowIndex = rse.getRow();
        System.out.print(selectedRowIndex);
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

    public HtmlCommandButton getBtn_elim1() {
        return btn_elim1;
    }

    /**
     * @param btn_elim the btn_elim to set
     */
    public void setBtn_elim(HtmlCommandButton btn_elim) {
        this.btn_elim = btn_elim;
    }

    /**
     * @param btn_elim the btn_elim to set
     */
    public void setBtn_elim1(HtmlCommandButton btn_elim1) {
        this.btn_elim = btn_elim1;
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
    public Colmena getIdFilaSeleccionada() {
        return idFilaSeleccionada;
    }

    /**
     * @param idFilaSeleccionada the idFilaSeleccionada to set
     */
    public void setIdFilaSeleccionada(Colmena idFilaSeleccionada) {
        this.idFilaSeleccionada = idFilaSeleccionada;
    }

    public void updateTable() {
        Apiares apiares = (Apiares) getBean("Apiares");
        this.getColmenasAsignadas().clear();
        apiares.getColmenasSinAsignar();
    }

    public String agregarPuntos() {
        // Hago visible la ventana
        ModalPopupUbicacion pop = (ModalPopupUbicacion) getBean("ModalPopupUbicacion");
        pop.setWindow("modificarApiar");
        pop.getPanelPopup1Bean().setShowModalPanel(true);
        pop.getPanelPopup1().setVisible(true);
        pop.getPanelPopup1().setRendered(true);

        // Configuro el Popup
        pop.setTitle("Seleccionar la ubicacion deseada...");
        pop.setearTamaño(525, 550);

        return null;
    }

    public String verHistoriales() {
        //Obtengo los datos
        GestorHistorialEstadoApiar gestorHistorialEstadoApiar = new GestorHistorialEstadoApiar();
        ArrayList estados = gestorHistorialEstadoApiar.getTodos(this.apiar.getIdApiar());

        // Hago visible la ventana
        ModalPopupHistorialApiar pop = (ModalPopupHistorialApiar) getBean("ModalPopupHistorialApiar");
        pop.setHistorialEstadoApiar(estados);
        pop.getPanelPopup1Bean().setShowModalPanel(true);
        pop.getPanelPopup1().setVisible(true);
        pop.getPanelPopup1().setRendered(true);

        // Configuro el Popup
        pop.setTitle("Historial de Estados");
        pop.setearTamaño(300, 200);
        return null;
    }
}

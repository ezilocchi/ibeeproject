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
import ibeeproject.model.apiar.EstadoApiar;
import ibeeproject.model.apiar.Layout;
import ibeeproject.model.apiar.Posicion;
import ibeeproject.model.apiar.Ubicacion;
import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.persistencia.GestorApiar;
import ibeeproject.persistencia.GestorHistorialEstadoApiar;
import ibeeproject.persistencia.GestorLayout;
import ibeeproject.persistencia.GestorPosicion;
import ibeeproject.persistencia.GestorUbicacion;
import ibeeproject.persistencia.GestorZona;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.FacesException;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version modificarApiar.java
 * @version Created on 30-ago-2009, 13:18:30
 * @author Administrador
 */
public class modificarApiar extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private Apiar apiar;
    private String idApiar;
    private String denominacion;
    private Date fechaAlta;
    private Date fechaCreacion;
    private ArrayList layouts = new ArrayList();
    private int layout;
    private int cantidad_max;
    private String longitud;
    private String latitud;
    private HtmlCommandButton btn_elim1;
    private ArrayList posicionesLayout = new ArrayList();
    //   private ArrayList colmenasAsignadas = new ArrayList();
    private GestorLayout gestLay;
    private GestorPosicion gestPos;
    private GestorApiar gestA;
    private GestorZona gestZ;
    private Colmena idFilaSeleccionada;
    private ArrayList<SelectItem> zonas = new ArrayList();
    private int zona;
    private HtmlDataTable dataTable1 = new HtmlDataTable();
    private HtmlDataTable dataTable2 = new HtmlDataTable();
    private HtmlDataTable dataTable3 = new HtmlDataTable();
    private PanelLayout panel;
    private HtmlPanelGrid panelGrid;
    private boolean confirmacion;
    private int colmenaPosicionada = 1;
    private ArrayList colmenasAsignadasCombo = new ArrayList();
    private UIParameter parametro;

    public Apiar getApiar() {
        return apiar;
    }

    public void setApiar(Apiar apiar) {
        this.apiar = apiar;
    }

    public int getColmenaPosicionada() {
        return colmenaPosicionada;
    }

    public void setColmenaPosicionada(int colmenaPosicionada) {
        this.colmenaPosicionada = colmenaPosicionada;
    }

    public ArrayList getColmenasAsignadasCombo() {
        return colmenasAsignadasCombo;
    }

    public void setColmenasAsignadasCombo(ArrayList colmenasAsignadasCombo) {
        this.colmenasAsignadasCombo = colmenasAsignadasCombo;
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

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public void setZonas(ArrayList zonas) {
        this.zonas = zonas;
    }

    public ArrayList<SelectItem> getZonas() {
        return zonas;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
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
        return cantidad_asignada;
    }

    public void setCantidad_asignada(int cantidad_asignada) {
        this.cantidad_asignada = cantidad_asignada;
        this.cantidad_asignada = this.apiar.getColmenasAsignadas().size();
    }

    public int getCantidad_max() {
        return cantidad_max;
    }

    public void setCantidad_max(int cantidad_max) {
        this.cantidad_max = cantidad_max;
    }
    private int cantidad_asignada;

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
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
    }

    private void _init() throws Exception {
    }
    // </editor-fold>

    public modificarApiar() {
    }

    public void cargarLayouts() {
        //Busco todos los layout con sus respectivas posiciones
        gestLay = new GestorLayout();
        ArrayList layouts = new ArrayList();
        layouts = gestLay.getTodos();
        for (int i = 0; i < layouts.size(); i++) {
            Layout lay = (Layout) layouts.get(i);
            this.getLayouts().add(new SelectItem(lay.getIdLayout(), lay.getDenominacion()));
        }
    }

    public String cargarPosiciones() {
        gestPos = new GestorPosicion();
        gestLay = new GestorLayout();
        this.posicionesLayout.clear();
        this.posicionesLayout = gestPos.getUnoLayout(this.apiar.getLayout().getIdLayout());
        this.setCantidad_max(this.getPosicionesLayout().size());
        return null;
    }

    public void cargarColmenasAsignadas() {
        //Busco todos los layout con sus respectivas posiciones
        this.getColmenasAsignadasCombo().clear();
        for (int i = 0; i < this.apiar.getColmenasAsignadas().size(); i++) {
            Colmena col = (Colmena) this.apiar.getColmenasAsignadas().get(i);
            this.getColmenasAsignadasCombo().add(new SelectItem(col.getIdColmena(), col.getDenominacion()));
        }
    }

    public void setearCampos(Apiar apiar) {

        this.setIdApiar(String.valueOf(apiar.getIdApiar()));
        this.setDenominacion(apiar.getDenominacion());
        this.setFechaAlta(apiar.getFechaAlta());
        this.setFechaCreacion(apiar.getFechaCreacion());
        this.setLatitud(String.valueOf(apiar.getUbicacion().getLatitud()));
        this.setLongitud(String.valueOf(apiar.getUbicacion().getLongitud()));
        this.setZona(apiar.getZona().getIdZona());
        this.setLayout(apiar.getLayout().getIdLayout());
        this.setCantidad_asignada(this.apiar.getColmenasAsignadas().size());
    }

    public void updateCampos() {
//        this.gestA = new GestorApiar();
//        consultarApiares bean = (consultarApiares) getBean("consultarApiares");
//        System.out.println("Id de APiar?: "+ bean.getIdFilaSeleccionada());
//        Apiar apiar = (Apiar) this.gestA.getUno(bean.getIdFilaSeleccionada());
//        this.setearCampos(apiar);
        }

    public void clean() {
        this.zonas = new ArrayList();
        this.layouts = new ArrayList();
        this.denominacion = "";
        this.latitud = "";
        this.longitud = "";
        this.fechaAlta = new Date();
    }

    public String action_modificar() {
        Apiares e = (Apiares) getBean("Apiares");
        e.setConsultarAll(true);
        //apiar.setFechaCreacion(UtilFecha.convertiFecha(new Date()));
        try {
            this.comprobarFecha();
            GestorUbicacion gestU = new GestorUbicacion();
            gestU.insertUno((Object) apiar.getUbicacion());
            Ubicacion ultimaUbicacion = (Ubicacion) gestU.getUltimo();
            apiar.getUbicacion().setIdUbicacion(ultimaUbicacion.getIdUbicacion());
            gestA = new GestorApiar();
            gestA.updateUno(apiar);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.println("Erro al guardar un nuevo Apiar");
        }

        googleMap mapa = (googleMap) getBean("googleMap");
        mapa.setRender(false);
        //mapa.initMap();
        mapa.setRender(true);
        consultarApiares consulta = (consultarApiares) getBean("consultarApiares");
        consulta.cleanTodo();
        consulta.queryAll_action();
        return "apiares";
    //mostrarMensaje();
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

    public String add_action() {
        agregarApiar e = (agregarApiar) getBean("agregarApiar");
        if (this.getParametro().getValue() == null) {
            // Valor por defecto cuando no tiene nada seleccionado
            this.setIdFilaSeleccionada(null);
        } else {
            // Almaceno el valor del indice seleccionado
            this.setIdFilaSeleccionada((Colmena) this.parametro.getValue());
            Apiares apiares = (Apiares) getBean("Apiares");
            Colmena aux = (Colmena) apiares.getColmenasSinAsignar().get(this.getRowByID(apiares.getColmenasSinAsignar(), this.getIdFilaSeleccionada().getIdColmena()));
            this.apiar.getColmenasAsignadas().add(aux);
            apiares.getColmenasSinAsignar().remove(this.getRowByID(apiares.getColmenasSinAsignar(), this.getIdFilaSeleccionada().getIdColmena()));
        }
        return null;
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

    public String delete_action() {
        agregarApiar e = (agregarApiar) getBean("agregarApiar");
        if (this.getParametro().getValue() == null) {
            // Valor por defecto cuando no tiene nada seleccionado
            this.setIdFilaSeleccionada(null);
        } else {
            // Almaceno el valor del indice seleccionado
            this.setIdFilaSeleccionada((Colmena) this.parametro.getValue());
            Apiares apiares = (Apiares) getBean("Apiares");
            apiares.getColmenasSinAsignar().add(this.apiar.getColmenasAsignadas().get(getRowByID(this.apiar.getColmenasAsignadas(), this.getIdFilaSeleccionada().getIdColmena())));
            this.apiar.getColmenasAsignadas().remove(getRowByID(this.apiar.getColmenasAsignadas(), this.getIdFilaSeleccionada().getIdColmena()));
        //this.colmenasAsignadas.remove(this.getIdFilaSeleccionada()-1);
        }
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

    public HtmlCommandButton getBtn_elim1() {
        return btn_elim1;
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

    public String agregarPuntos() {
        // Hago visible la ventana
        ModalPopupUbicacion pop = (ModalPopupUbicacion) getBean("ModalPopupUbicacion");
        pop.setWindow("modificarApiar");
        pop.getPanelPopup1Bean().setShowModalPanel(true);
        pop.getPanelPopup1().setVisible(true);
        pop.getPanelPopup1().setRendered(true);


        // Configuro el Popup
        pop.setTitle("Seleccionar la ubicacion deseada...");
        pop.setearTamaño(525, 575);

        return null;
    }
    ArrayList botones;
    ArrayList posiciones;

    public String setearPosicion() {
        // Hago visible la ventana
        ModalPopupPosicionMod pop = (ModalPopupPosicionMod) getBean("ModalPopupPosicionMod");
        this.cargarColmenasAsignadas();
        try {
            this.gestLay = new GestorLayout();
            Layout layout = (Layout) this.gestLay.getUno(this.apiar.getLayout().getIdLayout());
            this.cargarPosiciones();
            botones = new ArrayList();
            int count = 0;
            for (int i = 1; i < layout.getLargo() + 1; i++) {
                for (int j = 1; j < layout.getAncho() + 1; j++) {
                    count++;
                    HtmlCommandButton boton = new HtmlCommandButton();
                    boton.setId("a" + count);
                    boton.setValue(i + ":" + j);
                    if (this.buscarPosicionValida(j, i, this.posicionesLayout)) {
                        boton.setImage("/resources/icons/posicion_vacia.png");
                        boton.setDisabled(false);
                        //Does use the convenience method to create the expression

                        boton.addActionListener(new javax.faces.event.ActionListener() {

                            public void processAction(ActionEvent event) {
                                for (int k = 0; k < modificarApiar.this.apiar.getColmenasAsignadas().size(); k++) {

                                    Colmena col = (Colmena) modificarApiar.this.apiar.getColmenasAsignadas().get(k);

                                    if ((col.getIdColmena()) == (modificarApiar.this.getColmenaPosicionada())) {
                                        HtmlCommandButton boton = (HtmlCommandButton) event.getSource();
                                        String pos = (String) boton.getValue();
                                        String[] posicion = pos.split(" ");
                                        col.setPosicion(modificarApiar.this.buscarPosicion(Integer.parseInt(posicion[0]), Integer.parseInt(posicion[1]), modificarApiar.this.posicionesLayout));
                                        modificarApiar.this.apiar.getColmenasAsignadas().set(k, col);
                                        modificarApiar.this.getColmenasAsignadasCombo().remove(modificarApiar.this.buscarIntColmenaPosicionada());
                                        ModalPopupPosicionMod pop = (ModalPopupPosicionMod) getBean("ModalPopupPosicionMod");
                                        boton.setImage("/resources/icons/posicion_asignada.png");
                                        if (modificarApiar.this.getColmenasAsignadasCombo().size() == 0) {
                                            modificarApiar.this.getColmenasAsignadasCombo().add(new SelectItem(0, "no disponible"));
                                        }
                                        int cant_max = modificarApiar.this.getCantidad_max();
                                        int cant_asignada = modificarApiar.this.apiar.getColmenasAsignadas().size();
                                        int posicionada = modificarApiar.this.getColmenasAsignadasCombo().size();
                                        int ocupadas = cant_asignada - posicionada - 1;
                                        int subtotal = (ocupadas - posicionada);
                                        int total = cant_max + subtotal;
                                        modificarApiar.this.setCantidad_asignada(total);
                                        boton.setDisabled(true);
                                        break;
                                    }
                                }
                            }
                        });
                    } else {
                        boton.setImage("/resources/icons/posicion_invalida.png");
                        boton.setDisabled(true);
                    }
                    boton.setVisible(true);
                    boton.setValue(i + " " + j);
                    botones.add(boton);
                    pop.getPanelGrid().setColumns(j);
                    pop.getPanelGrid().getChildren().add(boton);
                }
            }
            pop.getPanelPopup1Bean().setShowModalPanel(true);
            pop.getPanelPopup1().setVisible(true);
            pop.getPanelPopup1().setRendered(true);
            // Configuro el Popup
            pop.setTitle("Ubique Colmenas en Layout");
            if ((layout.getAncho() * 40) < 200 || (layout.getLargo() * 75) < 320) {
                pop.setearTamaño((200), (400));
            } else {
                pop.setearTamaño((layout.getAncho() * 50), (layout.getLargo() * 75));
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return null;
    }

    public boolean buscarPosicionValida(int x, int y, ArrayList posiciones) {
        for (int i = 0; i < posiciones.size(); i++) {
            Posicion pos = (Posicion) posiciones.get(i);
            int posX = Integer.parseInt(pos.getPosHorizontal());
            int posY = Integer.parseInt(pos.getPosVertical());
            if (posX == x && posY == y) {
                return true;
            }
        }
        return false;
    }

    public Posicion buscarPosicion(int x, int y, ArrayList posiciones) {
        for (int i = 0; i < posiciones.size(); i++) {
            Posicion pos = (Posicion) posiciones.get(i);
            int posY = Integer.parseInt(pos.getPosHorizontal());
            int posX = Integer.parseInt(pos.getPosVertical());
            if (posX == x && posY == y) {
                return pos;
            }
        }
        return null;
    }

    public int buscarIntColmenaPosicionada() {
        for (int i = 0; i < this.colmenasAsignadasCombo.size() + 1; i++) {
            SelectItem sel = (SelectItem) this.colmenasAsignadasCombo.get(i);
            if (Integer.valueOf(String.valueOf(sel.getValue())) == (this.getColmenaPosicionada()));
            return i;
        }
        return 1;
    }

    public void comprobarFecha() {
        Date hoy = new Date();
        if (UtilFecha.fechasDiferenciaEnDias(this.apiar.getFechaAlta(), hoy) > 0) {
            EstadoApiar estado = new EstadoApiar(1);
            apiar.setEstado(estado);
        } else {
            EstadoApiar estado = new EstadoApiar(2);
            apiar.setEstado(estado);
        }

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

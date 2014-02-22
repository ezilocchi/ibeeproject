/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.icesoft.faces.component.panellayout.PanelLayout;
import com.icesoft.faces.component.ext.*;
import com.icesoft.faces.component.ext.RowSelectorEvent;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.apiar.Apiar;
import ibeeproject.persistencia.GestorApiar;
import ibeeproject.persistencia.GestorLayout;
import ibeeproject.persistencia.GestorPosicion;
import java.util.ArrayList;
import javax.faces.FacesException;
import ibeeproject.model.apiar.Colmena;
import ibeeproject.model.apiar.Layout;
import ibeeproject.model.apiar.Posicion;
import ibeeproject.model.apiar.Ubicacion;
import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.model.apiar.EstadoApiar;
import ibeeproject.persistencia.GestorUbicacion;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
// Use this code clip in a visual web project.
// Place this code in the prerender() method.

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version agregarApiar.java
 * @version Created on 22-ago-2009, 14:12:46
 * @author Adriana
 */
public class agregarApiar extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private Apiar apiar;
    private int layout;
    private int cantidad_max;
    private String longitud;
    private String latitud;
    private boolean ubicacion = false;
    private HtmlCommandButton btn_elim1;
    private ArrayList posicionesLayout = new ArrayList();
    private ArrayList colmenasAsignadas = new ArrayList();
    private ArrayList colmenasAsignadasCombo = new ArrayList();
    private GestorLayout gestLay;
    private GestorPosicion gestPos;
    private GestorApiar gestA;
    private Colmena idFilaSeleccionada;
    private int colmenaPosicionada = 1;
    private PanelLayout panel;
    private HtmlPanelGrid panelGrid;
    private boolean confirmacion;
    private UIParameter parametro;

    public boolean isUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(boolean ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Apiar getApiar() {
        return apiar;
    }

    public void setApiar(Apiar apiar) {
        this.apiar = apiar;
    }

    public ArrayList getColmenasAsignadas() {
        return this.colmenasAsignadas;
    }

    public void setColmenasAsignadas(ArrayList asignadas) {
        this.colmenasAsignadas = asignadas;
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

    private void _init() throws Exception {
    }
    // </editor-fold>

    public agregarApiar() {
    }

    public void cargarApiar() {
        //Busco el último apiar y cargo el Id del mismo más 1
        gestA = new GestorApiar();
        Apiar ultimoApiar = (Apiar) gestA.getUltimo();
        this.apiar.setIdApiar(ultimoApiar.getIdApiar() + 1);
        this.apiar.setFechaAlta(new Date());
    }

    public String cargarPosiciones(javax.faces.event.ValueChangeEvent a) {
        gestPos = new GestorPosicion();
        gestLay = new GestorLayout();
        this.posicionesLayout = new ArrayList();
        this.posicionesLayout = gestPos.getUnoLayout(this.apiar.getLayout().getIdLayout());
        this.setCantidad_max(this.getPosicionesLayout().size());
        return null;
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

    public void clean() {
        this.colmenasAsignadas.clear();
        this.latitud = " ";
        this.longitud = " ";
    }

    public String action_guardar() {
        /////////////////////////////////// Validaciones

        if (this.apiar.getUbicacion().getLatitud() == 0.0 || this.apiar.getUbicacion().getLongitud() == 0.0) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Debe cargarse las coordenadas");
            message.setDetail("Debe cargarse las coordenadas");
            context.addMessage("agregar:agregarApiar:latitud", message);
            return null;
        } else if (this.apiar.getColmenasAsignadas().size() == 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Debe asignarse por lo menos una colmena");
            message.setDetail("Debe asignarse por lo menos una colmena");
            context.addMessage("agregar:agregarApiar:dataTable2", message);
            return null;
        } else if (!this.verificacionUbicacion()) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Deben posicionarse todas las colmenas asignadas");
            message.setDetail("Deben posicionarse todas las colmenas asignadas");
            context.addMessage("agregar:agregarApiar:dataTable3", message);
            return null;
        } else {
            ////////////////////////////////////////// Fin Validaciones
            Apiares e = (Apiares) getBean("Apiares");
            e.setConsultarAll(true);
            apiar.setFechaCreacion(UtilFecha.convertiFecha(new Date()));
            try {
                this.comprobarFecha();
                GestorUbicacion gestU = new GestorUbicacion();
                gestU.insertUno((Object) apiar.getUbicacion());
                Ubicacion ultimaUbicacion = (Ubicacion) gestU.getUltimo();
                apiar.getUbicacion().setIdUbicacion(ultimaUbicacion.getIdUbicacion());
                gestA = new GestorApiar();
                gestA.insertUno(apiar);
            } catch (Exception a) {
                a.printStackTrace();
                System.out.println("Erro al guardar un nuevo Apiar");
            }

            googleMap mapa = (googleMap) getBean("googleMap");
            mapa.initMap();
//            mapa.setRender(false);
            mapa.initMap();
//            mapa.setRender(true);
            consultarApiares consulta = (consultarApiares) getBean("consultarApiares");
            consulta.cleanTodo();
            consulta.queryAll_action();
            return "apiares";
        //mostrarMensaje();
        }
    }

    public boolean verificacionUbicacion() {
        for (int i = 0; i < this.apiar.getColmenasAsignadas().size(); i++) {
            Colmena col = (Colmena) this.apiar.getColmenasAsignadas().get(i);
            
            if ((col.getPosicion().getPosHorizontal().isEmpty())) {
                return false;
            }
        }
        return true;
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
        this.clean();
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
        if (this.parametro.getValue() == null) {
            // Valor por defecto cuando no tiene nada seleccionado
            this.setIdFilaSeleccionada(null);
        } else {
            // Almaceno el valor del indice seleccionado
            this.setIdFilaSeleccionada((Colmena) e.parametro.getValue());
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
        if (this.parametro.getValue() == null) {
            // Valor por defecto cuando no tiene nada seleccionado
            this.setIdFilaSeleccionada(null);
        } else {
            // Almaceno el valor del indice seleccionado
            this.setIdFilaSeleccionada((Colmena) this.parametro.getValue());
            Apiares aux = (Apiares) getBean("Apiares");
            aux.getColmenasSinAsignar().add(this.apiar.getColmenasAsignadas().get(getRowByID(this.getColmenasAsignadas(), this.getIdFilaSeleccionada().getIdColmena())));
            this.apiar.getColmenasAsignadas().remove(getRowByID(this.getColmenasAsignadas(), this.getIdFilaSeleccionada().getIdColmena()));
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
        Apiares e = (Apiares) getBean("Apiares");
        e.getColmenasSinAsignar();
    }

    public String agregarPuntos() {
        // Hago visible la ventana
        ModalPopupUbicacion pop = (ModalPopupUbicacion) getBean("ModalPopupUbicacion");
        pop.setWindow("agregarApiar");
        pop.getPanelPopup1Bean().setShowModalPanel(true);
        pop.getPanelPopup1().setVisible(true);
        pop.getPanelPopup1().setRendered(true);

        // Configuro el Popup
        pop.setTitle("Seleccionar la ubicacion deseada...");
        pop.setearTamaño(525, 550);

        return null;
    }
    ArrayList botones;
    ArrayList posiciones;

    public String setearPosicion() {
        // Hago visible la ventana
        ModalPopupPosicion pop = (ModalPopupPosicion) getBean("ModalPopupPosicion");
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
                                for (int k = 0; k < agregarApiar.this.apiar.getColmenasAsignadas().size(); k++) {

                                    Colmena col = (Colmena) agregarApiar.this.apiar.getColmenasAsignadas().get(k);

                                    if ((col.getIdColmena()) == (agregarApiar.this.getColmenaPosicionada())) {
                                        HtmlCommandButton boton = (HtmlCommandButton) event.getSource();
                                        String pos = (String) boton.getValue();
                                        String[] posicion = pos.split(" ");
                                        col.setPosicion(agregarApiar.this.buscarPosicion(Integer.parseInt(posicion[0]), Integer.parseInt(posicion[1]), agregarApiar.this.posicionesLayout));
                                        agregarApiar.this.apiar.getColmenasAsignadas().set(k, col);
                                        agregarApiar.this.getColmenasAsignadasCombo().remove(agregarApiar.this.buscarIntColmenaPosicionada());
                                        ModalPopupPosicion pop = (ModalPopupPosicion) getBean("ModalPopupPosicion");
                                        boton.setImage("/resources/icons/posicion_asignada.png");
                                        if (agregarApiar.this.getColmenasAsignadasCombo().size() == 0) {
                                            agregarApiar.this.getColmenasAsignadasCombo().add(new SelectItem(0, "no disponible"));
                                        }
                                        int cant_max = agregarApiar.this.getCantidad_max();
                                        int cant_asignada = agregarApiar.this.apiar.getColmenasAsignadas().size();
                                        int posicionada = agregarApiar.this.getColmenasAsignadasCombo().size();
                                        int ocupadas = cant_asignada - posicionada - 1;
                                        int subtotal = (ocupadas - posicionada);
                                        int total = cant_max + subtotal;
                                        agregarApiar.this.setCantidad_asignada(total);
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
                    pop.getPanelPopup2().setColumns(j);
                    pop.getPanelPopup2().getChildren().add(boton);
                }
            }
            pop.getPanelPopup1Bean().setShowModalPanel(true);
            pop.getPanelPopup1().setVisible(true);
            pop.getPanelPopup1().setRendered(true);
            // Configuro el Popup
            pop.setTitle("Ubique Colmenas en Layout");
            
            //verifico que el layout este bien cargado
//            if (layout.getAncho() != 0 || layout.getLargo() != 0) {
//                System.out.println("---ERROR en el setteo del tamanio del POPUP ModalPopupPosicion---");
//                return null;
//            }

            // las imagenes de las casitas son de tamanio 32x32 pixeles
            if (layout.getAncho() < 3 || layout.getLargo() < 3) {
                pop.setearTamaño(200, 250);
            } else {
                pop.setearTamaño((layout.getAncho() * 32) + 85, (layout.getLargo() * 32) + 210);
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

    public String agregarLayout() {
        consultarLayouts layout = (consultarLayouts) getBean("consultarLayouts");
        layout.add_action();

        agregarLayout agregarlayout = (agregarLayout) getBean("agregarLayout");
        agregarlayout.setVengoDeApiar(true);
        return "agregarlayout";
    }
}

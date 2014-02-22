/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlPanelGrid;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.Apiares;
import ibeeproject.model.apiar.Layout;
import ibeeproject.model.apiar.Posicion;
import ibeeproject.persistencia.GestorLayout;
import ibeeproject.persistencia.GestorPosicion;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.event.ActionEvent;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version agregarLayout.java
 * @version Created on 20-sep-2009, 21:19:12
 * @author Administrador
 */

public class agregarLayout extends AbstractFragmentBean {

    private Layout layout;
    private GestorPosicion gestPos;
    private ArrayList posiciones = new ArrayList();
    private ArrayList nvasPosiciones = new ArrayList();
    private HtmlPanelGrid panelGrid;
    private int cantidad_max;
    private boolean nuevasPosiciones=false;
    private boolean vengoDeApiar = false;

    public boolean isVengoDeApiar() {
        return vengoDeApiar;
    }

    public void setVengoDeApiar(boolean vengoDeApiar) {
        this.vengoDeApiar = vengoDeApiar;
    }

    public boolean isNuevasPosiciones() {
        return nuevasPosiciones;
    }

    public void setNuevasPosiciones(boolean nuevasPosiciones) {
        this.nuevasPosiciones = nuevasPosiciones;
    }


    public ArrayList getNvasPosiciones() {
        return nvasPosiciones;
    }

    public int getCantidad_max() {
        return cantidad_max;
    }

    public void setCantidad_max(int cantidad_max) {
        this.cantidad_max = cantidad_max;
    }

    public void setNvasPosiciones(ArrayList nvasPosiciones) {
        this.nvasPosiciones = nvasPosiciones;
    }

    public HtmlPanelGrid getPanelGrid() {
        return panelGrid;
    }

    public void setPanelGrid(HtmlPanelGrid panelGrid) {
        this.panelGrid = panelGrid;
    }

    public ArrayList getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(ArrayList posiciones) {
        this.posiciones = posiciones;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layaout) {
        this.layout = layaout;
        Layouts lay = (Layouts) getBean("Layouts");
        gestPos = new GestorPosicion();
        posiciones = new ArrayList();
    }

    private void _init() throws Exception {
    }
    // </editor-fold>

    public agregarLayout() {
                this.setLayout(new Layout());
    }

    public String actionCancelar() {
        // Cancela
        consultarLayouts consulta = (consultarLayouts) getBean("consultarLayouts");
        consulta.cleanTodo();
        consulta.queryAll_action();
        if(this.vengoDeApiar) {
            consultarApiares consultarapiares = (consultarApiares) getBean("consultarApiares");
            consultarapiares.add_action();
            return "vuelvoApiares";
        }
        else
            return "layouts";
    }

    public String action_guardar() {
        Layouts e = (Layouts) getBean("Layouts");
        e.setConsultarAll(true);
        e.setConsultar(false);
        e.setEliminar(false);
        e.setModificar(false);
        e.setAgregar(false);
        GestorLayout gestLay = new GestorLayout();
        gestLay.insertUno(this.layout);
            for (int i = 0; i < this.getNvasPosiciones().size(); i++) {
                  gestPos.insertUno(this.getNvasPosiciones().get(i));
               }
        if(this.vengoDeApiar) {
            consultarApiares consultarapiares = (consultarApiares) getBean("consultarApiares");
            consultarapiares.add_action();
            Apiares apiares = (Apiares) getBean("Apiares");
            apiares.cargarLayouts();
            return "vuelvoApiares";
        }
        else
            return "layouts";
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
        Layouts lay = (Layouts) getBean("Layouts");
        this.getLayout().setIdLayout(lay.getUltimoLay().getIdLayout()+1);
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

    public String setearPosicion() {
        // Hago visible la ventana
        GestorLayout gestLay = new GestorLayout();
        this.panelGrid.setRendered(true);
        if (this.layout.getAncho() > 0 && this.layout.getLargo() > 0) {
            ArrayList botones = new ArrayList();
            this.getPanelGrid().getChildren().clear();
            try {
                int count = 0;
                for (int i = 1; i < layout.getLargo() + 1; i++) {
                    for (int j = 1; j < layout.getAncho() + 1; j++) {
                        count++;
                        HtmlCommandButton boton = new HtmlCommandButton();
                        boton.setId("a" + count);
                        boton.setValue(i + ":" + j);
                        boton.setImage("/resources/icons/posicion_invalida.png");
                        boton.setDisabled(false);
                        boton.setVisible(true);
                        botones.add(boton);
                        this.panelGrid.setColumns(j);
                        this.panelGrid.getChildren().add(boton);
                        //Does use the convenience method to create the expression

                        boton.addActionListener(new javax.faces.event.ActionListener() {

                            public void processAction(ActionEvent event) {
                                    HtmlCommandButton boton = (HtmlCommandButton) event.getSource();
                                    String pos = (String) boton.getValue();
                                    String[] posicion = pos.split(":");
                                    Posicion posici = new Posicion();
                                    posici.setLayout(agregarLayout.this.getLayout());
                                    posici.setIdPosicion(agregarLayout.this.nvasPosiciones.size());
                                    posici.setPosHorizontal(posicion[1]);
                                    posici.setPosVertical(posicion[0]);
                                    agregarLayout.this.nvasPosiciones.add(posici);
                                    boton.setImage("/resources/icons/posicion_vacia.png");
                                    agregarLayout.this.setCantidad_max(agregarLayout.this.nvasPosiciones.size());
                                    boton.setDisabled(true);
                            }
                        });
                    }
                }
                this.panelGrid.setStyle(" height: " + (layout.getLargo() * 75) + " px; width: " + (layout.getAncho() * 40) +" px; ");
            } catch (Exception a) {
                a.printStackTrace();
            }
        }
                    return null;
    }

        public String mostrarPosicion()
        {
        // Hago visible la ventana
        try{
        ArrayList botones =  new ArrayList();
        int count=0;
        for (int i = 1; i < layout.getLargo()+1; i++) {
            for (int j = 1; j < layout.getAncho()+1; j++) {
                count++;
                HtmlCommandButton boton = new HtmlCommandButton();
                boton.setId("a"+count);
                boton.setValue(i+":"+j);
                if(this.buscarPosicionValida(j, i, this.posiciones)){
                    boton.setImage("/resources/icons/posicion_vacia.png");
                    boton.setDisabled(false);
                }
            else{
                boton.setImage("/resources/icons/posicion_invalida.png");
                boton.setDisabled(true);
                }
                boton.setVisible(true);
                boton.setValue(i+" "+j);
                botones.add(boton);
                this.panelGrid.setColumns(j);
                this.panelGrid.getChildren().add(boton);
            }
        }
        // Configuro el Popup
         this.panelGrid.setStyle(" height: " + (layout.getLargo() * 75) + " px; width: " + (layout.getAncho() * 40) +" px; ");
        }
        catch(Exception a)
            {
            a.printStackTrace();
            }
        return null;
        }
            public boolean buscarPosicionValida(int x, int y,ArrayList posiciones)
           {
               for (int i = 0; i < posiciones.size(); i++) {
                   Posicion pos = (Posicion) posiciones.get(i);
                   int posX = Integer.parseInt(pos.getPosHorizontal());
                   int posY = Integer.parseInt(pos.getPosVertical());
                   if(posX==x && posY==y)
                       return true;
                 }
            return false;
           }

        public Posicion buscarPosicion(int x, int y,ArrayList posiciones)
           {
               for (int i = 0; i < posiciones.size(); i++) {
                   Posicion pos = (Posicion) posiciones.get(i);
                   int posY = Integer.parseInt(pos.getPosHorizontal());
                   int posX = Integer.parseInt(pos.getPosVertical());
                   if(posX==x && posY==y)
                       return pos;
                 }
            return null;
           }

}

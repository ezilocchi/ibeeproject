/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.googlecode.gmaps4jsf.component.mapcontrol.MapControl;
import com.googlecode.gmaps4jsf.component.marker.Marker;
import com.googlecode.gmaps4jsf.component.polygon.Polygon;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.soporte.UtilZonas;
import ibeeproject.model.zona.EstadoZona;
import ibeeproject.model.zona.TipoAgroquimico;
import ibeeproject.model.zona.TipoFlora;
import ibeeproject.model.zona.Zona;
import ibeeproject.persistencia.GestorZona;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.model.SelectItem;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version modificarZona.java
 * @version Created on 14/03/2010, 16:58:25
 * @author CyberShark
 */
public class modificarZona extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private int idtipoZona;
    private ArrayList<SelectItem> opciones = new ArrayList();
    private int selected = 1;
    private boolean agro;
    private boolean flor;
    public Zona zona;
    private boolean redibujado = false;
    private GestorZona gestZ = new GestorZona();

    public boolean isRedibujado() {
        return redibujado;
    }

    public void setRedibujado(boolean redibujado) {
        this.redibujado = redibujado;
    }

    public boolean isAgro() {
        return agro;
    }

    public void setAgro() {

        this.flor = false;
        this.agro = true;
    }

    public boolean isFlor() {
        return flor;
    }

    public void setFlor() {

        this.flor = true;
        this.agro = false;
    }

    public int getSelected() {
        return selected;
    }

    public String getTipo() {
        if (selected == 1) { // Flora
            return "Flora";
        }
        if (selected == 2) { // Agroquímico
            return "Agroquímico";
        }
        return null;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(int selected) {
        this.selected = selected;
        if (selected == 1) { // Flora
            this.setFlor();
        }
        if (selected == 2) { // Agroquímico
            this.setAgro();
        }
    }

    public void cargarRadio() {
        this.getOpciones().add(new SelectItem(1, "Flora"));
        this.getOpciones().add(new SelectItem(2, "Agroquimicos"));
    }

    /**
     * @return the opciones
     */
    public ArrayList<SelectItem> getOpciones() {
        return opciones;
    }

    /**
     * @param opciones the opciones to set
     */
    public void setOpciones(ArrayList<SelectItem> opciones) {
        this.opciones = opciones;
    }

    public int getIdtipoZona() {
        return idtipoZona;
    }

    public void setIdtipoZona(int idtipoZona) {
        this.idtipoZona = idtipoZona;
    }

    public Zona getZona() {
        mostrarZona mZ = (mostrarZona) getBean("mostrarZona");
        if (mZ.lat != 0.0 && mZ.longi != 0.0) {
            this.zona.setLatitud(mZ.lat);
            this.zona.setLongitud(mZ.longi);
        }
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
        this.zona = (Zona) this.gestZ.getUno(this.zona.getIdZona());
        Zonas zs = (Zonas) getBean("Zonas");
        if (this.zona.getTipoAgroquimico().getIdTipoAgroquimico() != -1) {
            this.setSelected(2);
            if (this.zona.getTipoAgroquimico().getIdTipoAgroquimico() > 0) {
                this.setIdtipoZona(this.zona.getTipoAgroquimico().getIdTipoAgroquimico());
            } else {
                this.setIdtipoZona(this.zona.getTipoAgroquimico().getIdTipoAgroquimico());
            }
        } else {
            this.setSelected(1);
            if (this.zona.getTipoFlora().getIdTipoFlora() > 0) {
                this.setIdtipoZona(this.zona.getTipoFlora().getIdTipoFlora() );
            } else {
                this.setIdtipoZona(this.zona.getTipoFlora().getIdTipoFlora());
            }
        }

    }

    public String guardar() {
        Zonas zone = (Zonas) getBean("Zonas");
        if (this.getSelected() == 1) {
            this.zona.setTipoAgroquimico(new TipoAgroquimico());
            if (this.getIdtipoZona() != 0) {
                this.zona.setTipoFlora((TipoFlora) zone.getTipoFlora().get(this.getIdtipoZona() - 1));
            } else {
                this.zona.setTipoFlora((TipoFlora) zone.getTipoFlora().get(this.getIdtipoZona()));
            }
        } else {
            this.zona.setTipoFlora(new TipoFlora());
            if (this.getIdtipoZona() != 0) {
                this.zona.setTipoAgroquimico((TipoAgroquimico) zone.getTipoAgroquimicos().get(this.getIdtipoZona() - 1));
            } else {
                this.zona.setTipoAgroquimico((TipoAgroquimico) zone.getTipoAgroquimicos().get(this.getIdtipoZona()));
            }
        }
        this.zona.setEstado(new EstadoZona());
        mostrarZona mZ = (mostrarZona) getBean("mostrarZona");
        if (this.isRedibujado()) {
            this.zona.setUbicacion(mZ.getPuntos());
        }
        try {
            this.gestZ.updateUno(this.zona);
        } catch (Exception a) {
            a.printStackTrace();
        }
        this.zona = new Zona();
        mZ.getPuntos().clear();
        mZ.getMark().setRendered(false);
        consultarZonas cons = (consultarZonas) getBean("consultarZonas");
        cons.queryAll_action();
        return "zonas";
    }

    private void _init() throws Exception {
    }
    // </editor-fold>

    public modificarZona() {
        this.cargarRadio();
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

    public String drawPoly() {
        double sumaX = 0, sumaY = 0;
        //trato que el mapa siga al marcador todavía NO LO LOGRO! JA
        mostrarZona mostrar = (mostrarZona) getBean("mostrarZona");
        mostrar.getMapa().setLatitude(String.valueOf(mostrar.getPuntos().get(mostrar.getPuntos().size() - 1).getLatitud()));
        mostrar.getMapa().setLongitude(String.valueOf(mostrar.getPuntos().get(mostrar.getPuntos().size() - 1).getLongitud()));

        //////////////////////////////////////

        mostrar.getMapa().getChildren().clear();
        UtilZonas zona = new UtilZonas();
        zona.setPuntos(mostrar.getPuntos());
        zona.setDenominacion(this.getZona().getZona());
        Polygon poly = new Polygon();
        //poly.getChildren().clear();

        if (this.getSelected() == 1) {
            poly.setHexStrokeColor("00FF00");
            poly.setHexFillColor("00FF00");
        } else {
            poly.setHexStrokeColor("FF0000");
            poly.setHexFillColor("FF0000");
        }
        poly.setFillOpacity("0.2");
        poly.setLineWidth("1");
        poly.setRendered(true);
        for (int i = 0; i < mostrar.getPuntos().size(); i++) {
            poly.getChildren().add(mostrar.getPuntos().get(i).getPoint());
        }
        //agrego el nuevo marcador en la última coordenada cargada
        Marker p = new Marker();
        p.setLatitude(String.valueOf(this.zona.getLatitud()));
        p.setLongitude(String.valueOf(this.zona.getLongitud()));
        mostrar.getMapa().getChildren().add(mostrar.getMark());

        mostrar.getMapa().getChildren().add(poly);
        // seteo los controles del mapa
        //controlS = new MapControl();

        MapControl controlZ = new MapControl();
        MapControl controlS = new MapControl();
        MapControl control = new MapControl();
        controlS.setId("control1");
        controlS.setName("GLargeMapControl");
        mostrar.getMapa().getChildren().add(controlS);

        //control = new MapControl();
        control.setId("control1");
        control.setName("GMapTypeControl");
        control.setOffsetWidth("300");
        mostrar.getMapa().getChildren().add(control);
        mostrar.getMapa().setRendered(true);
        return null;
    }

    public String redibujar() {
        this.setRedibujado(true);
        modificarZona agre = (modificarZona) getBean("modificarZona");
        agre.getZona().setLatitud(0.0);
        agre.getZona().setLongitud(0.0);
        mostrarZona mostrar = (mostrarZona) getBean("mostrarZona");
        mostrar.getPuntos().clear();
        mostrar.getMapa().getChildren().clear();
        mostrar.getMark().setRendered(true);
        mostrar.getMapa().getChildren().add(mostrar.getMark());
        return null;
    }

    public void calcularCentro() {
        double lat = 0;
        double longit = 0;
        mostrarZona mostrar = (mostrarZona) getBean("mostrarZona");
        for (int i = 0; i < mostrar.getPuntos().size(); i++) {
            lat = (lat) + (mostrar.getPuntos().get(i).getLatitud());
            longit = (longit) + (mostrar.getPuntos().get(i).getLongitud());
        }
        System.out.println("Latitud: " + lat / mostrar.getPuntos().size() + "  Longitud: " + longit / mostrar.getPuntos().size());
        this.zona.setLatitud(lat / mostrar.getPuntos().size());
        this.zona.setLongitud(longit / mostrar.getPuntos().size());
    }

    public String actionCancelar() {
        // Cancela
        mostrarZona mostrar = (mostrarZona) getBean("mostrarZona");
        mostrar.setDibujar(true);
        consultarZonas consulta = (consultarZonas) getBean("consultarZonas");
        consulta.cleanTodo();
        consulta.queryAll_action();
        return "zonas";
    }
}

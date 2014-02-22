/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.googlecode.gmaps4jsf.component.map.Map;
import com.googlecode.gmaps4jsf.component.mapcontrol.MapControl;
import com.googlecode.gmaps4jsf.component.marker.Marker;
import com.googlecode.gmaps4jsf.component.polygon.Polygon;
import com.googlecode.gmaps4jsf.component.point.Point;
import javax.faces.component.html.HtmlPanelGrid;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.apiar.Ubicacion;
import ibeeproject.model.soporte.UtilPolygon;
import ibeeproject.model.soporte.UtilZonas;
import java.util.ArrayList;
import java.util.List;
import javax.faces.FacesException;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version mostrarZona.java
 * @version Created on 30/01/2010, 15:38:51
 * @author CyberShark
 */
public class mostrarZona extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private String latitud = "-31.5";
    private String longitud = "-64.92";
    private HtmlPanelGrid panel;
    private Map mapa;// = new Map();
    private MapControl controlZ = new MapControl();
    private MapControl controlS = new MapControl();
    private MapControl control = new MapControl();
    private Marker mark;// = new Marker();
    private UtilZonas zona;
    private ArrayList<Ubicacion> puntos = new ArrayList<Ubicacion>();
    public double lat;
    public double longi;
    private Polygon poly = new Polygon();
    private Point cir = new Point();
    private boolean dibujar = true;
    private boolean modificar = false;
    private boolean agregar = false;

    public boolean isAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }

    public boolean isModificar() {
        return modificar;
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

    public boolean isDibujar() {
        return dibujar;
    }

    public void setDibujar(boolean dibujar) {
        this.dibujar = dibujar;
    }

    public Marker getMark() {
        return mark;
    }

    public void setMark(Marker mark) {
        this.mark = mark;
    }

    public ArrayList<Ubicacion> getPuntos() {
        return puntos;
    }

    public void setPuntos(ArrayList<Ubicacion> puntos) {
        this.puntos = puntos;
    }

    public Map getMapa() {
        return mapa;
    }

    public void setMapa(Map mapa) {
        this.mapa = mapa;
        if (dibujar) {
            this.dibujarPoligonos();
        }

    }

    public void dibujarPoligonos() {
        UtilPolygon utilP = new UtilPolygon();
        this.mapa.getChildren().clear();
        ArrayList<Polygon> poligonos = utilP.dibujarPoly();
        for (int i = 0; i < poligonos.size(); i++) {
            this.mapa.getChildren().add(poligonos.get(i));
        }
    }

    public void dibujarPoligono(int idZona) {
        UtilPolygon utilP = new UtilPolygon();
        this.mapa.getChildren().clear();
        Polygon poligono = utilP.dibujarPoly(idZona);
        this.mapa.getChildren().add(poligono);
    }

    public void calcularCentro() {
        double lat = 0;
        double longit = 0;
        for (int i = 0; i < puntos.size(); i++) {
            lat = (lat) + (puntos.get(i).getLatitud());
            longit = (longit) + (puntos.get(i).getLongitud());
        }
        System.out.println("Latitud: " + lat / puntos.size() + "  Longitud: " + longit / puntos.size());
        this.lat = lat / puntos.size();
        this.longi = longit / puntos.size();
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
        if (this.latitud != null && this.longitud != null) {
            //verificar que no se repite el último punto
            if (puntos.size() > 0) {
                if ((puntos.get(puntos.size() - 1).getLatitud() != Double.valueOf(this.latitud)) && (puntos.get(puntos.size() - 1).getLongitud() != Double.valueOf(this.longitud))) {
                    // agrego la coordenada al array de puntos
                    Ubicacion ubi = new Ubicacion();
                    ubi.setIdUbicacion(puntos.size() + 1);
                    ubi.setLatitud(Double.parseDouble(this.latitud));
                    ubi.setLongitud(Double.parseDouble(this.longitud));
                    puntos.add(ubi);
                }
            } else {
                // agrego la coordenada al array de puntos
                Ubicacion ubi = new Ubicacion();
                ubi.setIdUbicacion(puntos.size() + 1);
                ubi.setLatitud(Double.parseDouble(this.latitud));
                ubi.setLongitud(Double.parseDouble(this.longitud));
                puntos.add(ubi);
            }
        }
        this.drawPoly();
        this.calcularCentro();
    }

    public String drawPoly() {
        double sumaX = 0, sumaY = 0;
        //trato que el mapa siga al marcador todavía NO LO LOGRO! JA

        this.getMapa().setLatitude(String.valueOf(this.puntos.get(this.puntos.size() - 1).getLatitud()));
        this.getMapa().setLongitude(String.valueOf(this.puntos.get(this.puntos.size() - 1).getLongitud()));

        //////////////////////////////////////

        this.getMapa().getChildren().clear();
        zona = new UtilZonas();
        zona.setPuntos(this.puntos);
        agregarZona agr = null;
        modificarZona modif;
        int tipo = 0;
        this.poly = new Polygon();
        if (agregar) {
            agr = (agregarZona) getBean("agregarZona");
            zona.setDenominacion(agr.getZona().getZona());
            if (agr.getSelected() == 1) {
                this.poly.setHexStrokeColor("00FF00");
                this.poly.setHexFillColor("00FF00");
            } else {
                this.poly.setHexStrokeColor("FF0000");
                this.poly.setHexFillColor("FF0000");
            }
        } else {
            modif = (modificarZona) getBean("modificarZona");
            zona.setDenominacion(modif.getZona().getZona());
            if (modif.getSelected() == 1) {
                this.poly.setHexStrokeColor("00FF00");
                this.poly.setHexFillColor("00FF00");
            } else {
                this.poly.setHexStrokeColor("FF0000");
                this.poly.setHexFillColor("FF0000");
            }
        }




        //poly.getChildren().clear();
        this.poly.setFillOpacity("0.2");
        this.poly.setLineWidth("1");
        this.poly.setRendered(true);
        for (int i = 0; i < puntos.size(); i++) {
            this.poly.getChildren().add(puntos.get(i).getPoint());
        }
        //agrego el nuevo marcador en la última coordenada cargada
        Marker p = new Marker();
        p.setLatitude(this.latitud);
        p.setLongitude(this.longitud);
        this.getMapa().getChildren().add(this.getMark());

        this.getMapa().getChildren().add(this.poly);
        // seteo los controles del mapa
        //controlS = new MapControl();
        controlS.setId("control1");
        controlS.setName("GLargeMapControl");
        mapa.getChildren().add(controlS);

        //control = new MapControl();
        control.setId("control1");
        control.setName("GMapTypeControl");
        control.setOffsetWidth("300");
        mapa.getChildren().add(control);
        mapa.setRendered(true);
        return null;
    }

    public String redibujar() {
        agregarZona agre = (agregarZona) getBean("agregarZona");
        agre.getZona().setLatitud(0.0);
        agre.getZona().setLongitud(0.0);
        this.puntos.clear();
        return null;
    }

    public String drawCircle() {
        zona = new UtilZonas();
        zona.setPuntos(this.puntos);
        zona.setDenominacion("Flora");
        poly.getChildren().clear();
        for (int i = 0; i < puntos.size(); i++) {
            poly.getChildren().add(puntos.get(i).getPoint());
        }
        this.mapa.getChildren().clear();
        poly.setFillOpacity("0.2");
        poly.setHexFillColor("0x0000FF");
        poly.setLineWidth("1");
        this.mapa.getChildren().add(poly);

        //defino los controles del mapa
        controlZ = new MapControl();
        controlZ.setId("control1");
        controlZ.setName("GScaleControl");
        controlZ.setOffsetHeight("430");
        mapa.getChildren().add(controlZ);

        controlS = new MapControl();
        controlS.setId("control1");
        controlS.setName("GLargeMapControl");
        mapa.getChildren().add(controlS);

        control = new MapControl();
        control.setId("control1");
        control.setName("GMapTypeControl");
        control.setOffsetWidth("300");
        mapa.getChildren().add(control);

        this.mapa.setRendered(true);
        return null;
    }

    public HtmlPanelGrid getPanel() {
        return panel;
    }

    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }

    private void _init() throws Exception {
        if (this.mapa != null) {
            this.mapa.setLatitude("-39");
            this.mapa.setLongitude("-64");
        }
    }
    // </editor-fold>

    public mostrarZona() {
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

//        //creo el mapa
//        mapa = new Map();
//        mapa.setId("map1");
//        mapa.setLatitude("-31.4");
//        mapa.setLongitude("-64.8");
//        mapa.setZoom("9");
//        mapa.setAutoReshape("false");
//        mapa.setType("G_NORMAL_MAP");
//        //mapa.setRendered(true);
//        mapa.setRenderOnWindowLoad("false");
//
//        //defino los controles del mapa
//        controlZ = new MapControl();
//        controlZ.setId("control1");
//        controlZ.setName("GScaleControl");
//        controlZ.setOffsetHeight("430");
//        mapa.getChildren().add(controlZ);
//
//        controlS = new MapControl();
//        controlS.setId("control1");
//        controlS.setName("GLargeMapControl");
//        mapa.getChildren().add(controlS);
//
//        control = new MapControl();
//        control.setId("control1");
//        control.setName("GMapTypeControl");
//        control.setOffsetWidth("300");
//        mapa.getChildren().add(control);
//
//        this.panel = new HtmlPanelGrid();
//        this.panel.setId("panel2");
//        this.panel.getChildren().add(mapa);
//
//        Point p1 = new Point();
//        p1.setLatitude("-31.5");
//        p1.setLongitude("-64.9");
//
//        Point p2 = new Point();
//        p2.setLatitude("-31.5");
//        p2.setLongitude("-64.92");
//
//        Point p3 = new Point();
//        p3.setLatitude("-31.51");
//        p3.setLongitude("-64.9");
//
//        Point p4 = new Point();
//        p4.setLatitude("-31.51");
//        p4.setLongitude("-64.89");
//
//        Polygon poly = new Polygon();
//        poly.setLineWidth("3");
//        poly.setFillOpacity("0.5");
//        poly.getChildren().add(p1);
//        poly.getChildren().add(p2);
//        poly.getChildren().add(p3);
//        poly.getChildren().add(p4);
//
//        mapa.getChildren().add(poly);
//
//        mark.setDraggable("true");
//        mark.setLatitude(this.getLatitud());
//        mark.setLongitude(this.getLongitud());
//        mark.setId("mark1");
//        mark.setJsVariable("marker1");
//        EventListener event = new EventListener();
//        event.setEventName("dragend");
//        event.setJsFunction("marker1DragHandler");
//        mark.getChildren().add(event);
//        this.mapa.getChildren().add(mark);

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

    public String makeMarker() {
        Marker mark = new Marker();
        mark.setDraggable("true");
        mark.setLatitude("-31.5");
        mark.setLongitude("-64.92");
        mark.setId("mark1");
        mark.setRendered(true);
        this.mapa.getChildren().add(mark);
        return null;
    }
}

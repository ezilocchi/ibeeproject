/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.googlecode.gmaps4jsf.component.groundoverlay.GroundOverlay;
import com.googlecode.gmaps4jsf.component.htmlInformationWindow.HTMLInformationWindow;
import com.googlecode.gmaps4jsf.component.icon.Icon;
import com.googlecode.gmaps4jsf.component.marker.Marker;
import com.googlecode.gmaps4jsf.component.point.Point;
import com.googlecode.gmaps4jsf.component.polygon.Polygon;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.apiar.Ubicacion;
import ibeeproject.model.soporte.UtilMapas;
import ibeeproject.model.soporte.UtilPolygon;
import ibeeproject.model.soporte.UtilZonas;
import ibeeproject.model.zona.Zona;
import ibeeproject.persistencia.GestorPuntosGMap;
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
 * @version parametrosMapa.java
 * @version Created on 23-ago-2009, 21:51:42
 * @author farias.facundo
 */
public class parametrosMapa extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private ArrayList<SelectItem> opciones = new ArrayList();
    private int selected = 0;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public parametrosMapa() {
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
     * @return the selected
     */
    public int getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(int selected) {
        this.selected = selected;
        if (selected == 1) { // Apiares
            this.showApiares();
        }
        if (selected == 2) { // Climas
            this.showClimas();
        }
        if (selected == 3) { // Flora
            this.showZonas();
        }
        if (selected == 4) { // Agroquimicos
            this.showAgroquimicos();
        }
        if (selected == 5) { // Apiares y Agroquímicos
            this.showApiaresAgroquímicos();
        }
        if (selected == 6) { // Apiares y Flora
            this.showApiaresFlora();
        }
    }

    public void cargarRadio() {
        this.getOpciones().add(new SelectItem(1, "Apiares"));
        this.getOpciones().add(new SelectItem(2, "Clima"));
        this.getOpciones().add(new SelectItem(3, "Flora"));
        this.getOpciones().add(new SelectItem(4, "Agroquimicos"));

        //Opciones Avanzadas
        this.getOpciones().add(new SelectItem(5, "Apiares y Agroquímicos"));
        this.getOpciones().add(new SelectItem(6, "Apiares y Flora"));

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

    public void showApiares() {
        Mapas mapas = (Mapas) getBean("Mapas");
        mapas.getMyMap().getChildren().clear();
        GestorPuntosGMap gestorPuntos = new GestorPuntosGMap();
        ArrayList apiares = gestorPuntos.getApiares();
        for (int i = 0; i < apiares.size(); i++) {
            // Imagen de Apiares
            UtilMapas util = (UtilMapas) apiares.get(i);
            Marker mark = new Marker();
            mark.setLatitude(String.valueOf(util.getLatitud()));
            mark.setLongitude(String.valueOf(util.getLongitud()));

            Icon icon = new Icon();
            icon.setImageURL(util.getIcon());
            icon.setWidth("30");
            icon.setHeight("30");

            // Seteo la ventanita chetaza
            HTMLInformationWindow window = new HTMLInformationWindow();
            window.setLatitude(String.valueOf(util.getLatitud()));
            window.setLongitude(String.valueOf(util.getLongitud()));
            String html = "El Apiar es: " + util.getTitle() + "</br>" +
                    " Su Latitud es " + util.getLatitud() + "</br>" +
                    " Su Longitud es " + util.getLongitud() + "</br>";
            window.setHtmlText(html);

            mark.getChildren().add(icon);
            mark.getChildren().add(window);
            mapas.getMyMap().getChildren().add(mark);
        }
    }

    public void showClimas() {
        Mapas mapas = (Mapas) getBean("Mapas");
        mapas.getMyMap().getChildren().clear();
        GestorPuntosGMap gestorPuntos = new GestorPuntosGMap();
        ArrayList climas = gestorPuntos.getClimas();
        for (int i = 0; i < climas.size(); i++) {

            // Imagen de los Climas
            UtilMapas util = (UtilMapas) climas.get(i);
            Marker mark = new Marker();
            mark.setLatitude(String.valueOf(util.getLatitud()));
            mark.setLongitude(String.valueOf(util.getLongitud()));

            Icon icon = new Icon();
            icon.setImageURL(util.getIcon());
            icon.setWidth("30");
            icon.setHeight("30");

            // Seteo la ventanita chetaza
            HTMLInformationWindow window = new HTMLInformationWindow();
            window.setLatitude(String.valueOf(util.getLatitud()));
            window.setLongitude(String.valueOf(util.getLongitud()));
            String html = "El Apiar es: " + util.getTitle() + "</br>" +
                    " Su Latitud es " + util.getLatitud() + "</br>" +
                    " Su Longitud es " + util.getLongitud() + "</br>" +
                    util.getDetail();
            window.setHtmlText(html);

            mark.getChildren().add(icon);
            mark.getChildren().add(window);
            mapas.getMyMap().getChildren().add(mark);
        }
    }

    public void showZonas() {

        UtilPolygon util = new UtilPolygon();

        Mapas mapas = (Mapas) getBean("Mapas");
        mapas.getMyMap().getChildren().clear();

        ArrayList poligonos = util.dibujarFlora();
        for (int i = 0; i < poligonos.size(); i++) {
            mapas.getMyMap().getChildren().add((Polygon) poligonos.get(i));
        }
    }

    private void showAgroquimicos() {
        UtilPolygon util = new UtilPolygon();

        Mapas mapas = (Mapas) getBean("Mapas");
        mapas.getMyMap().getChildren().clear();

        ArrayList poligonos = util.dibujarAgroquimico();
        for (int i = 0; i < poligonos.size(); i++) {
            mapas.getMyMap().getChildren().add((Polygon) poligonos.get(i));
        }
    }

    private void showApiaresAgroquímicos() {

        //Dibujo Apiares
        Mapas mapas = (Mapas) getBean("Mapas");
        mapas.getMyMap().getChildren().clear();
        GestorPuntosGMap gestorPuntos = new GestorPuntosGMap();
        ArrayList apiares = gestorPuntos.getApiares();
        for (int i = 0; i < apiares.size(); i++) {
            // Imagen de Apiares
            UtilMapas util = (UtilMapas) apiares.get(i);
            Marker mark = new Marker();
            mark.setLatitude(String.valueOf(util.getLatitud()));
            mark.setLongitude(String.valueOf(util.getLongitud()));

            Icon icon = new Icon();
            icon.setImageURL(util.getIcon());
            icon.setWidth("30");
            icon.setHeight("30");

            // Seteo la ventanita chetaza
            HTMLInformationWindow window = new HTMLInformationWindow();
            window.setLatitude(String.valueOf(util.getLatitud()));
            window.setLongitude(String.valueOf(util.getLongitud()));
            String html = "El Apiar es: " + util.getTitle() + "</br>" +
                    " Su Latitud es " + util.getLatitud() + "</br>" +
                    " Su Longitud es " + util.getLongitud() + "</br>";
            window.setHtmlText(html);

            mark.getChildren().add(icon);
            mark.getChildren().add(window);
            mapas.getMyMap().getChildren().add(mark);
        }
        // Fin dibujar apiares
        //Dibujo Agroquímicos
        UtilPolygon util = new UtilPolygon();
        ArrayList poligonos = util.dibujarAgroquimico();
        for (int i = 0; i < poligonos.size(); i++) {
            mapas.getMyMap().getChildren().add((Polygon) poligonos.get(i));
        }
    //Fin dibujo de agroquímicos
    }

    private void showApiaresFlora() {
        //Dibujo Apiares
        Mapas mapas = (Mapas) getBean("Mapas");
        mapas.getMyMap().getChildren().clear();
        GestorPuntosGMap gestorPuntos = new GestorPuntosGMap();
        ArrayList apiares = gestorPuntos.getApiares();
        for (int i = 0; i < apiares.size(); i++) {
            // Imagen de Apiares
            UtilMapas util = (UtilMapas) apiares.get(i);
            Marker mark = new Marker();
            mark.setLatitude(String.valueOf(util.getLatitud()));
            mark.setLongitude(String.valueOf(util.getLongitud()));

            Icon icon = new Icon();
            icon.setImageURL(util.getIcon());
            icon.setWidth("30");
            icon.setHeight("30");

            // Seteo la ventanita chetaza
            HTMLInformationWindow window = new HTMLInformationWindow();
            window.setLatitude(String.valueOf(util.getLatitud()));
            window.setLongitude(String.valueOf(util.getLongitud()));
            String html = "El Apiar es: " + util.getTitle() + "</br>" +
                    " Su Latitud es " + util.getLatitud() + "</br>" +
                    " Su Longitud es " + util.getLongitud() + "</br>";
            window.setHtmlText(html);

            mark.getChildren().add(icon);
            mark.getChildren().add(window);
            mapas.getMyMap().getChildren().add(mark);
        }
        // Fin dibujar apiares
        // Dibujo Flora
        UtilPolygon util = new UtilPolygon();

        ArrayList poligonos = util.dibujarFlora();
        for (int i = 0; i < poligonos.size(); i++) {
            mapas.getMyMap().getChildren().add((Polygon) poligonos.get(i));
        }
    //Fin dibujo Flora

    }

    private void showClimasApiares() {
 //Dibujo Apiares
        Mapas mapas = (Mapas) getBean("Mapas");
        mapas.getMyMap().getChildren().clear();
        GestorPuntosGMap gestorPuntos = new GestorPuntosGMap();
        ArrayList apiares = gestorPuntos.getApiares();
        for (int i = 0; i < apiares.size(); i++) {
            // Imagen de Apiares
            UtilMapas util = (UtilMapas) apiares.get(i);
            Marker mark = new Marker();
            mark.setLatitude(String.valueOf(util.getLatitud()));
            mark.setLongitude(String.valueOf(util.getLongitud()));

            Icon icon = new Icon();
            icon.setImageURL(util.getIcon());
            icon.setWidth("30");
            icon.setHeight("30");

            // Seteo la ventanita chetaza
            HTMLInformationWindow window = new HTMLInformationWindow();
            window.setLatitude(String.valueOf(util.getLatitud()));
            window.setLongitude(String.valueOf(util.getLongitud()));
            String html = "El Apiar es: " + util.getTitle() + "</br>" +
                    " Su Latitud es " + util.getLatitud() + "</br>" +
                    " Su Longitud es " + util.getLongitud() + "</br>";
            window.setHtmlText(html);

            mark.getChildren().add(icon);
            mark.getChildren().add(window);
            mapas.getMyMap().getChildren().add(mark);
        }
        // Fin dibujar apiares
        // Dibujo Clima
        mapas.getMyMap().getChildren().clear();
        GestorPuntosGMap gestorPuntos2 = new GestorPuntosGMap();
        ArrayList climas = gestorPuntos2.getClimas();
        for (int i = 0; i < climas.size(); i++) {

            // Imagen de los Climas
            UtilMapas util = (UtilMapas) climas.get(i);
            Marker mark = new Marker();
            mark.setLatitude(String.valueOf(util.getLatitud()));
            mark.setLongitude(String.valueOf(util.getLongitud()));

            Icon icon = new Icon();
            icon.setImageURL(util.getIcon());
            icon.setWidth("30");
            icon.setHeight("30");

            // Seteo la ventanita chetaza
            HTMLInformationWindow window = new HTMLInformationWindow();
            window.setLatitude(String.valueOf(util.getLatitud()));
            window.setLongitude(String.valueOf(util.getLongitud()));
            String html = "El Apiar es: " + util.getTitle() + "</br>" +
                    " Su Latitud es " + util.getLatitud() + "</br>" +
                    " Su Longitud es " + util.getLongitud() + "</br>" +
                    util.getDetail();
            window.setHtmlText(html);

            mark.getChildren().add(icon);
            mark.getChildren().add(window);
            mapas.getMyMap().getChildren().add(mark);
        }
        //Fin Dibujo Clima
    }
}

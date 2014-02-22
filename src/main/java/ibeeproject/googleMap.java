/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.googlecode.gmaps4jsf.component.htmlInformationWindow.HTMLInformationWindow;
import com.googlecode.gmaps4jsf.component.icon.Icon;
import com.googlecode.gmaps4jsf.component.map.Map;
import com.googlecode.gmaps4jsf.component.marker.Marker;
import com.googlecode.gmaps4jsf.component.polygon.Polygon;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.soporte.PuntosGMap;
import ibeeproject.model.soporte.UtilPolygon;
import ibeeproject.persistencia.GestorPuntosGMap;
import java.util.ArrayList;
import javax.faces.FacesException;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version googleMap.java
 * @version Created on 02-jun-2009, 9:27:10
 * @author Sharky
 */
public class googleMap extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private Map myMap;
    private GestorPuntosGMap gestorMap;
    private PuntosGMap gmap;
    private ArrayList list;
    private boolean dibujarPoligonos = true;
    private boolean showControls = true;
    private boolean showMarkers = true;
    private boolean render=true;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public googleMap() {
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

    public boolean isDibujarPoligonos() {
        return dibujarPoligonos;
    }

    public void setDibujarPoligonos(boolean dibujarPoligonos) {
        this.dibujarPoligonos = dibujarPoligonos;
    }

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }



    /**
     * @return the myMap
     */
    public Map getMyMap() {
        return myMap;
    }

    /**
     * @param myMap the myMap to set
     */
    public void setMyMap(Map myMap) {
        this.myMap = myMap;
        this.initMap();
    }

    public void initMap() {
        
        // Seteo la localización de mi Mapa
        this.getMyMap().setLatitude("-31.38");
        this.getMyMap().setLongitude("-64.18");

        // Obtengo mis puntos desde la BD
        gestorMap = new GestorPuntosGMap();
        list = new ArrayList();
        gmap = new PuntosGMap();
        list = gestorMap.getTodos();

        // Objetos a setear en el Mapa

        Marker mark;
        HTMLInformationWindow window;
        Icon icon;

        //Seteo los Marcadores
        for (int i = 0; i < list.size(); i++) {
            // Regenero el punto
            gmap = (PuntosGMap) list.get(i);

            // Seteo el marcador
            mark = new Marker();
            mark.setLatitude(String.valueOf(gmap.getLatitud()));
            mark.setLongitude(String.valueOf(gmap.getLongitud()));

            // Seteo la ventanita chetaza
            window = new HTMLInformationWindow();
            window.setLatitude(String.valueOf(gmap.getLatitud()));
            window.setLongitude(String.valueOf(gmap.getLongitud()));
            String html = "El Apiar es: " + gmap.getDenominacion() + "</br>" +
                    " Su estado es " + gmap.getEstado() + "</br>" +
                    " Su Latitud es " + gmap.getLatitud() + "</br>" +
                    " Su Longitud es " + gmap.getLongitud() + "</br>" ;
            
            window.setHtmlText(html);

            //Añado al mapa
            //mark.getChildren().add(icon);
            mark.getChildren().add(window);
            this.getMyMap().getChildren().add(mark);
        //this.getMyMap().getChildren().add(window);
        }

    }

    /**
     * @return the showControls
     */
    public boolean isShowControls() {
        return showControls;
    }

    /**
     * @param showControls the showControls to set
     */
    public void setShowControls(boolean showControls) {
        this.showControls = showControls;
    }

    /**
     * @return the showMarkers
     */
    public boolean isShowMarkers() {
        return showMarkers;
    }

    /**
     * @param showMarkers the showMarkers to set
     */
    public void setShowMarkers(boolean showMarkers) {
        this.showMarkers = showMarkers;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject;

/**
 *
 * @author farias.facundo 
 */
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.gmap.GMapLatLng;
import ibeeproject.model.soporte.PuntosGMap;
import ibeeproject.persistencia.GestorPuntosGMap;

/**
 * <p>The GmapBean is responsible for setting up default map markers,
 * as well as the selecting of cities, searching for addresses, and toggling
 * the visibility of the map ui controls.</p>
 *
 * @since 1.7
 */
public class GmapBean  implements Serializable {

    private List points = new ArrayList();
    private boolean showControls = true;
    private boolean showMarkers = true;
    private GestorPuntosGMap gestorMap;
    private PuntosGMap gmap;
    private ArrayList list;

    public GmapBean() {
        // Generate a set of default map marker locations
        //points.add(new GMapLatLng("37.379434", "-121.92293"));
        //points.add(new GMapLatLng("33.845449", "-84.368682"));
        //points.add(new GMapLatLng("34.05333", "-118.24499"));
        //points.add(new GMapLatLng("33.072694", "-97.06234"));
        //points.add(new GMapLatLng("37.391278", "-121.952451"));
        this.getCoord();
    }

    public List getPoints() {
        return points;
    }

    public void setPoints(List points) {
        this.points = points;
    }

    public boolean isShowControls() {
        return showControls;
    }

    public void setShowControls(boolean showControls) {
        this.showControls = showControls;
    }

    public boolean isShowMarkers() {
        return showMarkers;
    }

    public void setShowMarkers(boolean showMarkers) {
        this.showMarkers = showMarkers;
    }

        public void getCoord()
    {
        gestorMap = new GestorPuntosGMap();
        list = new ArrayList();
        gmap = new PuntosGMap();
        list = gestorMap.getTodos();
        //selectItems = new ArrayList<SelectItem>();
        for(int i=0; i<list.size();i++)
        {
            gmap = (PuntosGMap) list.get(i);
            points.add(new GMapLatLng(String.valueOf(gmap.getLatitud()),String.valueOf(gmap.getLongitud())));
        }
    }
}

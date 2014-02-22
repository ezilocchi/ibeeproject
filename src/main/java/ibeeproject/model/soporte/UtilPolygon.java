/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.model.soporte;

import com.googlecode.gmaps4jsf.component.htmlInformationWindow.HTMLInformationWindow;
import com.googlecode.gmaps4jsf.component.eventlistener.EventListener;
import com.googlecode.gmaps4jsf.component.icon.Icon;
import com.googlecode.gmaps4jsf.component.marker.Marker;
import com.googlecode.gmaps4jsf.component.polygon.Polygon;
import ibeeproject.model.zona.Zona;
import ibeeproject.persistencia.GestorZona;
import java.util.ArrayList;

/**
 *
 * @author CyberShark
 */
public class UtilPolygon {

    private UtilZonas zona;
    private GestorZona gestZ;
    private ArrayList<Zona> zonas;
    private ArrayList<Polygon> poligonos;
    private Zona zone;

    public ArrayList<Polygon> dibujarPoly() {
        gestZ = new GestorZona();
        zona = new UtilZonas();
        zone = new Zona();
        zonas = gestZ.getTodos();
        poligonos = new ArrayList<Polygon>();

        for (int i = 0; i < zonas.size(); i++) {

            zone = (Zona) zonas.get(i);

            if (validarPuntos(zone)) { // valido que las ubicaciones tengan valores de longitud y latitud válidos

                Polygon poly = new Polygon();

                if (zone.getTipoFlora().getIdTipoFlora() != -1) {
                    poly.setHexStrokeColor("00FF00");
                    poly.setHexFillColor("00FF00");
                    //Info
                    HTMLInformationWindow window = new HTMLInformationWindow();
                    window.setLatitude(String.valueOf(zone.getLatitud()));
                    window.setLongitude(String.valueOf(zone.getLongitud()));
                    String html = "Zona: " + zone.getZona() + "</br>" +
                            " Tipo de Flora: " + zone.getTipoFlora().getDenonimacion() + "</br>" +
                            " Observaciones: " + zone.getTipoFlora().getDescripcion() + "</br>";
                    window.setHtmlText(html);

                    //Seteo el ícono para el polígono
                    Icon ico = new Icon();
                    ico.setImageURL("http://localhost:8080/iBeeProject/resources/flor2.gif");
                    ico.setShadowImageURL("http://localhost:8080/iBeeProject/resources/transparente.gif");
                    Marker mark = new Marker();
                    mark.setLatitude(String.valueOf(zone.getLatitud()));
                    mark.setLongitude(String.valueOf(zone.getLongitud()));
                    mark.getChildren().add(ico);
                    mark.getChildren().add(window);
                    poly.getChildren().add(mark);
                } else {
                    poly.setHexStrokeColor("FF0000");
                    poly.setHexFillColor("FF0000");
                    HTMLInformationWindow window = new HTMLInformationWindow();
                    window.setLatitude(String.valueOf(zone.getLatitud()));
                    window.setLongitude(String.valueOf(zone.getLongitud()));
                    String html = "Zona: " + zone.getZona() + "</br>" +
                            " Tipo de Agroquímico: " + zone.getTipoAgroquimico().getDenominacion() + "</br>" +
                            " Observaciones: " + zone.getTipoAgroquimico().getDescripcion() + "</br>";
                    window.setHtmlText(html);

                    //Seteo el ícono para el polígono
                    Icon ico = new Icon();
                    ico.setImageURL("http://localhost:8080/iBeeProject/resources/veneno2.gif");
                    ico.setShadowImageURL("http://localhost:8080/iBeeProject/resources/transparente.gif");
                    Marker mark = new Marker();
                    mark.setLatitude(String.valueOf(zone.getLatitud()));
                    mark.setLongitude(String.valueOf(zone.getLongitud()));
                    mark.getChildren().add(ico);
                    mark.getChildren().add(window);
                    poly.getChildren().add(mark);
                }
                poly.setFillOpacity("0.2");
                poly.setLineWidth("1");
                poly.setRendered(true);
                for (int j = 0; j < zone.getUbicacion().size(); j++) {
                    poly.getChildren().add(zone.getUbicacion().get(j).getPoint());
                }
                poligonos.add(poly);
            }
        }
        return poligonos;
    }

    public Polygon dibujarPoly(int idZona) {
        gestZ = new GestorZona();
        zona = new UtilZonas();
        //zone = new Zona();
        Polygon poly = new Polygon();
        zone = (Zona) gestZ.getUno(idZona);

        if (validarPuntos(zone)) { // valido que las ubicaciones tengan valores de longitud y latitud válidos

            if (zone.getTipoFlora().getIdTipoFlora() != -1) {
                poly.setHexStrokeColor("00FF00");
                poly.setHexFillColor("00FF00");
                //Info
                HTMLInformationWindow window = new HTMLInformationWindow();
                window.setLatitude(String.valueOf(zone.getLatitud()));
                window.setLongitude(String.valueOf(zone.getLongitud()));
                String html = "Zona: " + zone.getZona() + "</br>" +
                        " Tipo de Flora: " + zone.getTipoFlora().getDenonimacion() + "</br>" +
                        " Observaciones: " + zone.getTipoFlora().getDescripcion() + "</br>";
                window.setHtmlText(html);

                //Seteo el ícono para el polígono
                Icon ico = new Icon();
                ico.setImageURL("http://localhost:8080/iBeeProject/resources/flor2.gif");
                ico.setShadowImageURL("http://localhost:8080/iBeeProject/resources/transparente.gif");
                Marker mark = new Marker();
                mark.setLatitude(String.valueOf(zone.getLatitud()));
                mark.setLongitude(String.valueOf(zone.getLongitud()));
                mark.getChildren().add(ico);
                mark.getChildren().add(window);
                poly.getChildren().add(mark);
            } else {
                poly.setHexStrokeColor("FF0000");
                poly.setHexFillColor("FF0000");
                HTMLInformationWindow window = new HTMLInformationWindow();
                window.setLatitude(String.valueOf(zone.getLatitud()));
                window.setLongitude(String.valueOf(zone.getLongitud()));
                String html = "Zona: " + zone.getZona() + "</br>" +
                        " Tipo de Agroquímico: " + zone.getTipoAgroquimico().getDenominacion() + "</br>" +
                        " Observaciones: " + zone.getTipoAgroquimico().getDescripcion() + "</br>";
                window.setHtmlText(html);

                //Seteo el ícono para el polígono
                Icon ico = new Icon();
                ico.setImageURL("http://localhost:8080/iBeeProject/resources/veneno2.gif");
                ico.setShadowImageURL("http://localhost:8080/iBeeProject/resources/transparente.gif");
                Marker mark = new Marker();
                mark.setLatitude(String.valueOf(zone.getLatitud()));
                mark.setLongitude(String.valueOf(zone.getLongitud()));
                mark.getChildren().add(ico);
                mark.getChildren().add(window);
                poly.getChildren().add(mark);
            }
            poly.setFillOpacity("0.2");
            poly.setLineWidth("1");
            poly.setRendered(true);

            for (int j = 0; j < zone.getUbicacion().size(); j++) {
                poly.getChildren().add(zone.getUbicacion().get(j).getPoint());
            }
        }

        return poly;
    }

    public ArrayList<Polygon> dibujarFlora() {
        gestZ = new GestorZona();
        zona = new UtilZonas();
        zone = new Zona();
        zonas = gestZ.getTodos();
        poligonos = new ArrayList<Polygon>();

        for (int i = 0; i < zonas.size(); i++) {

            zone = (Zona) zonas.get(i);

            if (validarPuntos(zone)) { // valido que las ubicaciones tengan valores de longitud y latitud válidos

                Polygon poly = new Polygon();

                if (zone.getTipoFlora().getIdTipoFlora() != -1) {
                    poly.setHexStrokeColor("00FF00");
                    poly.setHexFillColor("00FF00");

                    poly.setFillOpacity("0.2");
                    poly.setLineWidth("1");
                    poly.setRendered(true);
                    //Info
                    HTMLInformationWindow window = new HTMLInformationWindow();
                    window.setLatitude(String.valueOf(zone.getLatitud()));
                    window.setLongitude(String.valueOf(zone.getLongitud()));
                    String html = "Zona: " + zone.getZona() + "</br>" +
                            " Tipo de Flora: " + zone.getTipoFlora().getDenonimacion() + "</br>" +
                            " Observaciones: " + zone.getTipoFlora().getDescripcion() + "</br>";
                    window.setHtmlText(html);

                    //Seteo el ícono para el polígono
                    Icon ico = new Icon();
                    ico.setImageURL("http://localhost:8080/iBeeProject/resources/flor2.gif");
                    ico.setShadowImageURL("http://localhost:8080/iBeeProject/resources/transparente.gif");
                    Marker mark = new Marker();
                    mark.setLatitude(String.valueOf(zone.getLatitud()));
                    mark.setLongitude(String.valueOf(zone.getLongitud()));
                    mark.getChildren().add(ico);
                    mark.getChildren().add(window);
                    poly.getChildren().add(mark);
                    for (int j = 0; j < zone.getUbicacion().size(); j++) {
                        poly.getChildren().add(zone.getUbicacion().get(j).getPoint());
                    }
                    poligonos.add(poly);
                }
            }
        }
        return poligonos;
    }

    public ArrayList<Polygon> dibujarAgroquimico() {
        gestZ = new GestorZona();
        zona = new UtilZonas();
        zone = new Zona();
        zonas = gestZ.getTodos();
        poligonos = new ArrayList<Polygon>();

        for (int i = 0; i < zonas.size(); i++) {

            zone = (Zona) zonas.get(i);

            if (validarPuntos(zone)) { // valido que las ubicaciones tengan valores de longitud y latitud válidos

                Polygon poly = new Polygon();

                if (zone.getTipoFlora().getIdTipoFlora() == -1) {
                    poly.setHexStrokeColor("FF0000");
                    poly.setHexFillColor("FF0000");

                    poly.setFillOpacity("0.2");
                    poly.setLineWidth("1");
                    poly.setRendered(true);

                    HTMLInformationWindow window = new HTMLInformationWindow();
                    window.setLatitude(String.valueOf(zone.getLatitud()));
                    window.setLongitude(String.valueOf(zone.getLongitud()));
                    String html = "Zona: " + zone.getZona() + "</br>" +
                            " Tipo de Agroquímico: " + zone.getTipoAgroquimico().getDenominacion() + "</br>" +
                            " Observaciones: " + zone.getTipoAgroquimico().getDescripcion() + "</br>";
                    window.setHtmlText(html);

                    //Seteo el ícono para el polígono
                    Icon ico = new Icon();
                    ico.setImageURL("http://localhost:8080/iBeeProject/resources/veneno2.gif");
                    ico.setShadowImageURL("http://localhost:8080/iBeeProject/resources/transparente.gif");
                    Marker mark = new Marker();
                    mark.setLatitude(String.valueOf(zone.getLatitud()));
                    mark.setLongitude(String.valueOf(zone.getLongitud()));
                    mark.getChildren().add(ico);
                    mark.getChildren().add(window);
                    poly.getChildren().add(mark);

                    for (int j = 0; j < zone.getUbicacion().size(); j++) {
                        poly.getChildren().add(zone.getUbicacion().get(j).getPoint());
                    }
                    poligonos.add(poly);
                }
            }
        }
        return poligonos;
    }

    private boolean validarPuntos(Zona zona) {
        if (zona.getUbicacion().size() > 0) {
            for (int j = 0; j < zona.getUbicacion().size(); j++) {
                if (zona.getUbicacion().get(j).getLatitud() == -1) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}

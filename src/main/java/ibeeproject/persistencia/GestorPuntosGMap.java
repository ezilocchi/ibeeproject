/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.apiar.EstadoApiar;
import ibeeproject.model.apiar.Ubicacion;
import ibeeproject.model.soporte.PuntosGMap;
import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.model.soporte.UtilGraph;
import ibeeproject.model.soporte.UtilMapas;
import ibeeproject.model.soporte.UtilZonas;
import ibeeproject.persistencia.GestorEstadoApiar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class GestorPuntosGMap implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;
    private GestorEstadoApiar gestEa;

    public GestorPuntosGMap() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
        gestEa = new GestorEstadoApiar();
    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * " +
                    " FROM apiar a, ubicacion u, estadoapiar e " +
                    " WHERE a.idUbicacion = u.idUbicacion " +
                    " AND a.idEstado = e.idEstado " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PuntosGMap gmap = new PuntosGMap();
                gmap.setNumeroApiar(rs.getInt("idApiar"));
                gmap.setDenominacion(rs.getString("denominacion"));
                EstadoApiar ea = (EstadoApiar)gestEa.getUno(rs.getInt("idEstado"));
                gmap.setEstado(ea.getEstado());
                gmap.setLatitud(rs.getDouble("latitud"));
                gmap.setLongitud(rs.getDouble("longitud"));
                list.add(gmap);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorPuntosGmap! (getTodos)");
        }
        return list;
    }

    public Object getUltimo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getAsignado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int insertUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deleteUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int updateUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getApiares() {
        ArrayList<UtilMapas> apiares = new ArrayList();
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * " +
                    " FROM apiar a, ubicacion u " +
                    " WHERE a.idUbicacion = u.idUbicacion " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UtilMapas util = new UtilMapas();
                util.setTitle(rs.getInt("idApiar") + " " + rs.getString("denominacion"));
                util.setIcon("http://localhost:8080/iBeeProject/resources/icono.gif");
                //util.setIcon("http://localhost:8080/iBeeProject/resources/icons/bee_icon_2.gif");
                util.setLatitud(rs.getDouble("latitud"));
                util.setLongitud(rs.getDouble("longitud"));
                apiares.add(util);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorPuntosGmap! (getApiares)");
        }
        return apiares;
    }

    public ArrayList getClimas() {
        ArrayList<UtilMapas> apiares = new ArrayList();
        try {
            conn = connPool.getConnection();
                String sql = " SELECT * " +
                            " FROM apiar a, ubicacion u, zona z, localidad l " +
                            " WHERE a.idUbicacion = u.idUbicacion " +
                            " AND a.idZona = z.idZona " +
                            " AND z.idLocalidad = l.idLocalidad ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UtilMapas util = new UtilMapas();
                util.setTitle(rs.getInt("idApiar") + " " + rs.getString("denominacion"));
                util.setDetail(" Ubicado en " + rs.getString("l.nombre") + " Con temperatura " +rs.getDouble("temperatura") );
                util.setIcon("http://localhost:8080/iBeeProject/resources/icons/weather/" + rs.getString("iconoTemperatura") + ".png");
                util.setLatitud(rs.getDouble("u.latitud"));
                util.setLongitud(rs.getDouble("u.longitud"));
                apiares.add(util);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorPuntosGmap! (getClimas)");
        }
        return apiares;
    }

    public UtilGraph getClimas(int idApiar1, int idApiar2, int idApiar3, String var) {
        UtilGraph util = new UtilGraph();
        double [][] dataD = new double[14][3];
        ArrayList labels = new ArrayList();
        try {
            conn = connPool.getConnection();
                    String sql = "SELECT c.fechaHora fecha, " +
                        " AVG((select IFNULL(c."+ var +",0)" +
                        "  FROM apiar a1, zona z1, clima c1 " +
                        "  WHERE a1.idZona = z1.idZona " +
                        "  AND z1.idZona = c1.idZona " +
                        "  AND a1.idApiar = a.idApiar " +
                        "  AND c1.idClima = c.idClima " +
                        "  AND z1.idZona = z.idZona " +
                        "  AND a1.idApiar = ? )) as valor1, " +
                       " AVG((select IFNULL(c."+ var +",0)" +
                       "   FROM apiar a1, zona z1, clima c1 " +
                       "   WHERE a1.idZona = z1.idZona " +
                       "   AND z1.idZona = c1.idZona " +
                       "   AND a1.idApiar = a.idApiar " +
                       "   AND c1.idClima = c.idClima " +
                       "   AND z1.idZona = z.idZona " +
                       "   AND a1.idApiar = ? )) as valor2, " +
                       " AVG((select IFNULL(c."+ var +",0)" +
                       "   FROM apiar a1, zona z1, clima c1 " +
                       "   WHERE a1.idZona = z1.idZona " +
                       "   AND z1.idZona = c1.idZona " +
                       "  AND a1.idApiar = a.idApiar " +
                       "   AND c1.idClima = c.idClima " +
                       "   AND z1.idZona = z.idZona " +
                       "   AND a1.idApiar = ? )) as valor3 " +
             "   FROM apiar a, zona z, clima c " +
             "   WHERE a.idZona = z.idZona  " +
             "   AND z.idZona = c.idZona  " +
             "   AND (a.idApiar = ?  " +
             "   OR a.idApiar = ?  " +
             "   OR a.idApiar = ?) " +
             "   group by 1 LIMIT 14 " ;
            System.out.println(sql);
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idApiar1);
            ps.setInt(2, idApiar2);
            ps.setInt(3, idApiar3);
            ps.setInt(4, idApiar1);
            ps.setInt(5, idApiar2);
            ps.setInt(6, idApiar3);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                Date fecha = UtilFecha.convertiFecha(rs.getDate("fecha"));
                String label = DateFormat.getDateInstance(DateFormat.SHORT).format(fecha).toString();
                labels.add(label);
                dataD[i][0] = (rs.getDouble("valor1"));
                dataD[i][1] = (rs.getDouble("valor2"));
                dataD[i][2] = (rs.getDouble("valor3"));
                i = i + 1;
            }
            util.setData(new ArrayList(Arrays.asList(dataD)));
            util.setLabels(labels);
            util.setLegendLabels(new ArrayList(Arrays.asList(new String[]{"Apiar "+idApiar1, "Apiar "+idApiar2, "Apiar "+idApiar3})));
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorPuntosGmap! (getClimas(2)");
        }
        return util;
    }
public ArrayList getZonas() {
        ArrayList<UtilZonas> zonas = new ArrayList();
        try {
            conn = connPool.getConnection();
                String sql = " SELECT * " +
                            " FROM zona z, ubicaciondezona uz, ubicacion u " +
                            " WHERE z.idZona = uz.idZona " +
                            " AND uz.idUbicacion = u.idUbicacion " +
                            " Group by uz.idZona";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UtilZonas util = new UtilZonas();
                util.setDenominacion(rs.getInt("z.idZona") + " " + rs.getString("z.zona"));
                String sql2 =   " Select * " +
                                " from ubicacion u, ubicaciondezona uz " +
                                " where u.idUbicacion=uz.idUbicacion" +
                                " and uz.idZona= ? " ;
                PreparedStatement ps2;
                //System.out.println("idZona=:" +rs.getInt("z.idZona"));
                ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1, rs.getInt("z.idZona"));
                ResultSet rs2 = ps2.executeQuery();
                        while(rs2.next()){
                            Ubicacion ubicacion = new Ubicacion();
                            ubicacion.setLatitud(rs2.getDouble("latitud"));
                            ubicacion.setLongitud(rs2.getDouble("longitud"));
                            util.getPuntos().add(ubicacion);
                        }
                ps2.close();
                zonas.add(util);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorPuntosGmap! (getClimas)");
        }
        return zonas;
    }

}

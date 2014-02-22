/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.apiar.EstadoApiar;
import ibeeproject.model.apiar.HistorialEstadoApiar;
import ibeeproject.model.soporte.UtilFecha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorHistorialEstadoApiar implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorHistorialEstadoApiar() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM HistorialEstadoApiar ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistorialEstadoApiar historialEstadoApiar = new HistorialEstadoApiar();
                historialEstadoApiar.setIdApiar(rs.getInt("idApiar"));

                //Regenero el Estado
                GestorEstadoApiar gestorEstadoApiar = new GestorEstadoApiar();
                EstadoApiar estadoApiar = (EstadoApiar) gestorEstadoApiar.getUno(rs.getInt("idEstado"));

                historialEstadoApiar.setEstado(estadoApiar);
                historialEstadoApiar.setFecha(UtilFecha.convertiFecha(rs.getDate("fecha")));

                list.add(historialEstadoApiar);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorHistorialEstadoApiar! (getTodos)");
        }
        return list;
    }

    public ArrayList getTodos(int idApiar) {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM HistorialEstadoApiar " +
                    " WHERE idApiar = ? " +
                    " ORDER BY fecha DESC LIMIT 10";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idApiar);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistorialEstadoApiar historialEstadoApiar = new HistorialEstadoApiar();
                historialEstadoApiar.setIdApiar(rs.getInt("idApiar"));

                //Regenero el Estado
                GestorEstadoApiar gestorEstadoApiar = new GestorEstadoApiar();
                EstadoApiar estadoApiar = (EstadoApiar) gestorEstadoApiar.getUno(rs.getInt("idEstado"));

                historialEstadoApiar.setEstado(estadoApiar);
                historialEstadoApiar.setFecha(UtilFecha.convertiFecha(rs.getDate("fecha")));

                list.add(historialEstadoApiar);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorHistorialAlarma! (getTodos(int))");
        }
        return list;
    }

    public Object getUltimo() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) {
throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto, int idAlarma) {
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
}
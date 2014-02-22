/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.apiar.HistorialEstadoFamilia;
import ibeeproject.model.familia.EstadoFamilia;
import ibeeproject.model.soporte.UtilFecha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorHistorialEstadoFamilia implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorHistorialEstadoFamilia() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM HistorialEstadoFamilia ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistorialEstadoFamilia historialEstadoFamilia = new HistorialEstadoFamilia();
                historialEstadoFamilia.setNumeroFamilia(rs.getInt("numeroFamilia"));

                //Regenero el Estado
                GestorEstadoFamilia gestorEstadoFamilia = new GestorEstadoFamilia();
                EstadoFamilia estadoFamilia = (EstadoFamilia) gestorEstadoFamilia.getUno(rs.getInt("idEstadoFamilia"));

                historialEstadoFamilia.setEstado(estadoFamilia);
                historialEstadoFamilia.setFecha(UtilFecha.convertiFecha(rs.getDate("fecha")));

                list.add(historialEstadoFamilia);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorHistorialEstadoFamilia! (getTodos)");
        }
        return list;
    }

    public ArrayList getTodos(int numeroFamilia) {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM HistorialEstadoFamilia " +
                    " WHERE numeroFamilia = ? " +
                    " ORDER BY fecha DESC LIMIT 10";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, numeroFamilia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistorialEstadoFamilia historialEstadoFamilia = new HistorialEstadoFamilia();
                historialEstadoFamilia.setNumeroFamilia(rs.getInt("numeroFamilia"));

                //Regenero el Estado
                GestorEstadoFamilia gestorEstadoFamilia = new GestorEstadoFamilia();
                EstadoFamilia estadoFamilia = (EstadoFamilia) gestorEstadoFamilia.getUno(rs.getInt("idEstadoFamilia"));

                historialEstadoFamilia.setEstado(estadoFamilia);
                historialEstadoFamilia.setFecha(UtilFecha.convertiFecha(rs.getDate("fecha")));

                list.add(historialEstadoFamilia);
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

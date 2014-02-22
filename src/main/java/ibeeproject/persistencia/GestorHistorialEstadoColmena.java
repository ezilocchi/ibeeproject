/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import ibeeproject.model.apiar.EstadoColmena;
import ibeeproject.model.apiar.HistorialEstadoColmena;
import ibeeproject.model.soporte.UtilFecha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorHistorialEstadoColmena  implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorHistorialEstadoColmena() {
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
                HistorialEstadoColmena historialEstadoColmena = new HistorialEstadoColmena();
                historialEstadoColmena.setNumeroColmena(rs.getInt("numeroColmena"));

                //Regenero el Estado
                GestorEstadoColmena gestorEstadoColmena = new GestorEstadoColmena();
                EstadoColmena estadoColmena = (EstadoColmena) gestorEstadoColmena.getUno(rs.getInt("idEstadoColmena"));

                historialEstadoColmena.setEstado(estadoColmena);
                historialEstadoColmena.setFecha(UtilFecha.convertiFecha(rs.getDate("fecha")));

                list.add(historialEstadoColmena);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorHistorialEstadoColmena! (getTodos)");
        }
        return list;
    }

    public ArrayList getTodos(int numeroColmena) {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM HistorialEstadoColmena " +
                    " WHERE numeroColmena = ? " +
                    " ORDER BY fecha DESC LIMIT 10";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, numeroColmena);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistorialEstadoColmena historialEstadoColmena = new HistorialEstadoColmena();
                historialEstadoColmena.setNumeroColmena(rs.getInt("numeroColmena"));

                //Regenero el Estado
                GestorEstadoColmena gestorEstadoColmena = new GestorEstadoColmena();
                EstadoColmena estadoColmena = (EstadoColmena) gestorEstadoColmena.getUno(rs.getInt("idEstadoColmena"));

                historialEstadoColmena.setEstado(estadoColmena);
                historialEstadoColmena.setFecha(UtilFecha.convertiFecha(rs.getDate("fecha")));

                list.add(historialEstadoColmena);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorHistorialColmena! (getTodos(int))");
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

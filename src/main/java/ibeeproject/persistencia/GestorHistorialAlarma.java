/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import ibeeproject.model.zona.Criticidad;
import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.model.zona.HistorialAlarma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author carranza.matias
 */
public class GestorHistorialAlarma implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorHistorialAlarma() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM HistorialAlarma ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistorialAlarma historialAlarma = new HistorialAlarma();
                historialAlarma.setNumero(rs.getInt("idHistorialAlarma"));
                historialAlarma.setIdAlarma(rs.getInt("idAlarma"));
                historialAlarma.setFecha(UtilFecha.convertiFecha(rs.getDate("fecha")));
                historialAlarma.setValor(rs.getFloat("valor"));
                historialAlarma.setAcciones(rs.getBoolean("acciones"));

                //Regenero la Criticidad
                GestorCriticidadAlarma gestorCriticidad = new GestorCriticidadAlarma();
                Criticidad criticidad = (Criticidad) gestorCriticidad.getUno(rs.getInt("idCriticidad"));
                historialAlarma.setCriticidad(criticidad);

                list.add(historialAlarma);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorHistorialAlarma! (getTodos)");
        }
        return list;
    }

    public ArrayList getTodos(int idAlarma) {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM HistorialAlarma where idAlarma = ? ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idAlarma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistorialAlarma historialAlarma = new HistorialAlarma();
                historialAlarma.setNumero(rs.getInt("idHistorialAlarma"));
                historialAlarma.setIdAlarma(rs.getInt("idAlarma"));
                historialAlarma.setFecha(UtilFecha.convertiFecha(rs.getDate("fecha")));
                historialAlarma.setValor(rs.getFloat("valor"));
                historialAlarma.setAcciones(rs.getBoolean("acciones"));

                //Regenero la Criticidad
                GestorCriticidadAlarma gestorCriticidad = new GestorCriticidadAlarma();
                Criticidad criticidad = (Criticidad) gestorCriticidad.getUno(rs.getInt("idCriticidad"));
                historialAlarma.setCriticidad(criticidad);

                list.add(historialAlarma);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorHistorialAlarma! (getTodos)");
        }
        return list;
    }

    public Object getUltimo() throws Exception {
        HistorialAlarma historialAlarma = new HistorialAlarma();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM HistorialAlarma WHERE idHistorialAlarma = (select MAX(idHistorialAlarma) from HistorialAlarma)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                historialAlarma.setNumero(rs.getInt("idHistorialAlarma"));
                historialAlarma.setIdAlarma(rs.getInt("idAlarma"));

                historialAlarma.setFecha(UtilFecha.convertiFecha(rs.getDate("fecha")));
                historialAlarma.setValor(rs.getFloat("valor"));
                historialAlarma.setAcciones(rs.getBoolean("acciones"));

                //Regenero la Criticidad
                GestorCriticidadAlarma gestorCriticidad = new GestorCriticidadAlarma();
                Criticidad criticidad = (Criticidad) gestorCriticidad.getUno(rs.getInt("idCriticidad"));
                historialAlarma.setCriticidad(criticidad);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorHistorialAlarma !!! (getUltimo)");
        }
        return historialAlarma;
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) {
        HistorialAlarma historialAlarma = new HistorialAlarma();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM HistorialAlarma where idHistorialAlarma = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                historialAlarma.setNumero(rs.getInt("idHistorialAlarma"));
                historialAlarma.setIdAlarma(rs.getInt("idAlarma"));
                historialAlarma.setFecha(UtilFecha.convertiFecha(rs.getDate("fecha")));
                historialAlarma.setValor(rs.getFloat("valor"));
                historialAlarma.setAcciones(rs.getBoolean("acciones"));

                //Regenero la Criticidad
                GestorCriticidadAlarma gestorCriticidad = new GestorCriticidadAlarma();
                Criticidad criticidad = (Criticidad) gestorCriticidad.getUno(rs.getInt("idCriticidad"));
                historialAlarma.setCriticidad(criticidad);

                list.add(historialAlarma);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorHistorialAlarma! (getUno)");
        }
        return historialAlarma;
    }

    public Object getUno(int idObjeto, int idAlarma) {
        HistorialAlarma historialAlarma = new HistorialAlarma();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM HistorialAlarma where idHistorialAlarma = ? and idAlarma = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ps.setInt(2, idAlarma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                historialAlarma.setNumero(rs.getInt("idHistorialAlarma"));
                historialAlarma.setIdAlarma(rs.getInt("idAlarma"));
                historialAlarma.setFecha(UtilFecha.convertiFecha(rs.getDate("fecha")));
                historialAlarma.setValor(rs.getFloat("valor"));
                historialAlarma.setAcciones(rs.getBoolean("acciones"));

                //Regenero el tipo alarma
                GestorCriticidadAlarma gestorCriticidad = new GestorCriticidadAlarma();
                Criticidad criticidad = (Criticidad) gestorCriticidad.getUno(rs.getInt("idCriticidad"));
                historialAlarma.setCriticidad(criticidad);

                list.add(historialAlarma);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorHistorialAlarma! (getUno)");
        }
        return historialAlarma;
    }

    public int insertUno(Object object) throws Exception {
        HistorialAlarma historialAlarma = (HistorialAlarma) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = "INSERT INTO HistorialAlarma (idHistorialAlarma, idAlarma, " +
                    " fecha, valor, idCriticidad, acciones" +
                    " values (?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            HistorialAlarma ultimo = (HistorialAlarma) getUltimo();
            ps.setInt(1, ultimo.getNumero() + 1);
            ps.setInt(2, historialAlarma.getIdAlarma());
            ps.setDate(3, UtilFecha.convertiFecha(historialAlarma.getFecha()));
            ps.setFloat(4, historialAlarma.getValor());
            ps.setInt(5, historialAlarma.getCriticidad().getNumero());
            ps.setBoolean(6, historialAlarma.isAcciones());

            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorHistorialAlarma !!! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int updateUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}


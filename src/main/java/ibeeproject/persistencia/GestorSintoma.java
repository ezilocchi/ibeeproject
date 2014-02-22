/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.enfermedad.Sintoma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class GestorSintoma implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorSintoma() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos(int numeroSintoma) {
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;
            // Si recibe un cero devuelve todos los sintomas
            if (numeroSintoma == 0) {
                String sql = "SELECT * FROM Sintoma ORDER BY 1";
                ps = conn.prepareStatement(sql);
            } else {
                String sql = "SELECT s.* FROM Sintoma s" +
                        " WHERE s.idsintoma = ? " +
                        " ORDER BY 1";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, numeroSintoma);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sintoma sintoma = new Sintoma();
                sintoma.setNumero(rs.getInt("idSintoma"));
                sintoma.setDenominacion(rs.getString("denominacion"));
                sintoma.setDescripcion(rs.getString("descripcion"));
                sintoma.setObservaciones(rs.getString("observaciones"));
                list.add(sintoma);
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorSintoma! (getTodos)");
        }
        return list;
    }

    public ArrayList getTodosPorEnfermedad(int numeroEnfermedad) {
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;
            // Si recibe un cero devuelve todos los sintomas
            if (numeroEnfermedad == 0) {
                String sql = "SELECT * FROM Sintoma ORDER BY 1";
                ps = conn.prepareStatement(sql);
            } else {
                String sql = "SELECT DISTINCT s.* FROM Sintoma s, sintomaporenfermedad spe " +
                        " WHERE s.idsintoma = spe.idsintoma and idEnfermedad = ? " +
                        " ORDER BY 1 ";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, numeroEnfermedad);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sintoma sintoma = new Sintoma();
                sintoma.setNumero(rs.getInt("idSintoma"));
                sintoma.setDenominacion(rs.getString("denominacion"));
                sintoma.setDescripcion(rs.getString("descripcion"));
                sintoma.setObservaciones(rs.getString("observaciones"));
                list.add(sintoma);
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorSintoma! (getTodos(int))");
        }
        return list;
    }

    public int deleteUnoPorEnfermedad(int idEnfermedad, int idSintoma) throws Exception {
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " delete from sintomaporenfermedad " +
                    "where idenfermedad = ? and idSintoma = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idEnfermedad);
            ps.setInt(2, idSintoma);
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorSintoma! (deleteUnoPorEnfermedad)");
        }
        return resultado;
    }

    public Object getUltimo() {
        Sintoma sintoma = new Sintoma();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Sintoma WHERE idSintoma = (select MAX(idSintoma) from Sintoma)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sintoma.setNumero(rs.getInt("idSintoma"));
                sintoma.setDenominacion(rs.getString("denominacion"));
                sintoma.setDescripcion(rs.getString("descripcion"));
                sintoma.setObservaciones(rs.getString("observaciones"));
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorSintoma! (getUltimo)");
        }
        return sintoma;
    }

    public ArrayList getAsignado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;
            String sql = "SELECT * FROM Sintoma ORDER BY 1";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sintoma sintoma = new Sintoma();
                sintoma.setNumero(rs.getInt("idSintoma"));
                sintoma.setDenominacion(rs.getString("denominacion"));
                sintoma.setDescripcion(rs.getString("descripcion"));
                sintoma.setObservaciones(rs.getString("observaciones"));
                list.add(sintoma);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorSintoma! (getTodos)");
        }
        return list;
    }

    public Object getUno(int idObjeto) {
        Sintoma sintoma = new Sintoma();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Sintoma WHERE idSintoma = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sintoma.setNumero(rs.getInt("idSintoma"));
                sintoma.setDenominacion(rs.getString("denominacion"));
                sintoma.setDescripcion(rs.getString("descripcion"));
                sintoma.setObservaciones(rs.getString("observaciones"));

            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorSintoma! (getUno)");
        }
        return sintoma;
    }

    public int insertUno(Object object) throws Exception {
        Sintoma sintoma = (Sintoma) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = "insert into Sintoma(idSintoma, denominacion, descripcion, observaciones) " +
                    " values (?,?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sintoma.getNumero());
            //ps.setInt(2, sintoma.getEnfermedad().getNumero());
            ps.setString(2, sintoma.getDenominacion());
            ps.setString(3, sintoma.getDescripcion());
            ps.setString(4, sintoma.getObservaciones());

            resultado = ps.executeUpdate();
            conn.close();
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorSintoma! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception, SQLException {
        Sintoma sintoma = (Sintoma) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " delete from sintoma where idSintoma = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sintoma.getNumero());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                throw e;
            }
            e.printStackTrace();
            System.out.print("Error SQLException: GestorSintoma !!! (deleteUno)");
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorSintoma! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        Sintoma sintoma = (Sintoma) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update Sintoma set " +
                    " denominacion = ? , " +
                    " descripcion = ? , " +
                    " observaciones = ? " +
                    " WHERE idSintoma = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setString(1, sintoma.getDenominacion());
            ps.setString(2, sintoma.getDescripcion());
            ps.setString(3, sintoma.getObservaciones());
            //ps.setInt(4, sintoma.getEnfermedad().getNumero());

            // Sintoma a actualizar
            ps.setInt(4, sintoma.getNumero());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorSintoma! (updateUno)");
        }
        return resultado;
    }
}

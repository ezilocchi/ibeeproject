/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.enfermedad.Tratamiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class GestorTratamiento implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorTratamiento() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos(int numeroTratamiento) {
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;
            // Si recibe un cero devuelve todos los tratamientos
            if (numeroTratamiento == 0) {
                String sql = "SELECT * FROM Tratamiento ORDER BY 1";
                ps = conn.prepareStatement(sql);
            } else {
                String sql = " SELECT t.* FROM Tratamiento t" +
                        " WHERE t.idtratamiento = ? " +
                        " ORDER BY 1";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, numeroTratamiento);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tratamiento tratamiento = new Tratamiento();
                tratamiento.setNumero(rs.getInt("idTratamiento"));
                tratamiento.setDenominacion(rs.getString("denominacion"));
                tratamiento.setDescripcion(rs.getString("descripcion"));
                tratamiento.setCosteo(rs.getFloat("costeo"));
                list.add(tratamiento);
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTratamiento !!!");
        }
        return list;
    }

    public ArrayList getTodosPorEnfermedad(int numeroEnfermedad) {

        try {
            conn = connPool.getConnection();
            PreparedStatement ps;
            // Si recibe un cero devuelve todos los tratamientos
            if (numeroEnfermedad == 0) {
                String sql = "SELECT * FROM Tratamiento ORDER BY 1";
                ps = conn.prepareStatement(sql);
            } else {
                String sql = " SELECT DISTINCT t.* FROM Tratamiento t, tratamientosporenfermedad tpe " +
                        " WHERE t.idtratamiento = tpe.idtratamiento and idEnfermedad = ? " +
                        " ORDER BY 1 ";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, numeroEnfermedad);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tratamiento tratamiento = new Tratamiento();
                tratamiento.setNumero(rs.getInt("idTratamiento"));
                tratamiento.setDenominacion(rs.getString("denominacion"));
                tratamiento.setDescripcion(rs.getString("descripcion"));
                tratamiento.setCosteo(rs.getFloat("costeo"));
                list.add(tratamiento);
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTratamiento! (getTodos(int))");
        }
        return list;

    }

    public int deleteUnoPorEnfermedad(int idEnfermedad, int idTratamiento) throws Exception {
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " delete from tratamientosporenfermedad " +
                    "where idenfermedad = ? and idTratamiento = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idEnfermedad);
            ps.setInt(2, idTratamiento);
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTratamiento! (deleteUnoPorEnfermedad)");
        }
        return resultado;
    }

    public Object getUltimo() {
        Tratamiento tratamiento = new Tratamiento();

        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Tratamiento WHERE idTratamiento = (select MAX(idTratamiento) from Tratamiento)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tratamiento.setNumero(rs.getInt("idTratamiento"));
                tratamiento.setDenominacion(rs.getString("denominacion"));
                tratamiento.setDescripcion(rs.getString("descripcion"));
                tratamiento.setCosteo(rs.getFloat("costeo"));
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTratamiento !!! (getUltimo)");
        }
        return tratamiento;
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
                String sql = "SELECT * FROM Tratamiento ORDER BY 1";
                ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tratamiento tratamiento = new Tratamiento();
                tratamiento.setNumero(rs.getInt("idTratamiento"));
                tratamiento.setDenominacion(rs.getString("denominacion"));
                tratamiento.setDescripcion(rs.getString("descripcion"));
                tratamiento.setCosteo(rs.getFloat("costeo"));
                list.add(tratamiento);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTratamiento !!!");
        }
        return list;
    }

    public Object getUno(int idObjeto) {
        Tratamiento tratamiento = new Tratamiento();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Tratamiento WHERE idTratamiento = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tratamiento.setNumero(rs.getInt("idTratamiento"));
                tratamiento.setDenominacion(rs.getString("denominacion"));
                tratamiento.setDescripcion(rs.getString("descripcion"));
                tratamiento.setCosteo(rs.getFloat("costeo"));

            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTratamiento!!! (getUno)");
        }
        return tratamiento;
    }

    public int insertUno(Object object) throws Exception {
        Tratamiento tratamiento = (Tratamiento) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = "insert into Tratamiento(idTratamiento, denominacion, descripcion, costeo) " +
                    " values (?,?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tratamiento.getNumero());
            ps.setString(2, tratamiento.getDenominacion());
            ps.setString(3, tratamiento.getDescripcion());
            ps.setString(4, Double.toString(tratamiento.getCosteo()));

            resultado = ps.executeUpdate();
            conn.close();
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTratamiento !!! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception, SQLException {
        Tratamiento tratamiento = (Tratamiento) object;
        int resultado = 0;
        boolean res = false;
        try {
            conn = connPool.getConnection();
            String sql = "delete from Tratamiento WHERE idTratamiento = ? ";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            //System.out.println(tratamiento.getNumero());
            ps.setInt(1, tratamiento.getNumero());
            //System.out.println(sql);
            res = ps.execute();
            if (res) {
                resultado = 1;
            }
            //resultado = ps.executeUpdate();


            ps.close();
            connPool.closeConnection(conn);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                throw e;
            }
            e.printStackTrace();
            System.out.print("Error SQLException: GestorTratamiento !!! (deleteUno)");
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTratamiento !!! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        Tratamiento tratamiento = (Tratamiento) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update Tratamiento set " +
                    " denominacion = ? , " +
                    " descripcion = ? , " +
                    " costeo = ? " +
                    " WHERE idTratamiento = ? ";
            //System.out.println(sql);
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setString(1, tratamiento.getDenominacion());
            ps.setString(2, tratamiento.getDescripcion());
            ps.setFloat(3, Float.parseFloat(Double.toString(tratamiento.getCosteo())));

            // Tratamiento a actualizar
            ps.setInt(4, tratamiento.getNumero());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTratamiento !!! (updateUno)");
        }
        return resultado;
    }
}

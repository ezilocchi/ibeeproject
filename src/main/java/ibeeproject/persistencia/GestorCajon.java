/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.cajon.Cajon;
import ibeeproject.model.cajon.EstadoCajon;
import ibeeproject.model.cajon.TipoCajon;
import ibeeproject.model.persona.Empleado;
import ibeeproject.model.soporte.UtilFecha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Fede 
 */
public class GestorCajon implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorCajon() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

//    public void insertar(Cajon cajon) throws Exception {
//        try {
//            conn = connPool.getConnection();
//            String sql = " INSERT INTO cajon VALUES ((?),(?),(?),(?),(?),(?),(?),(?),(?))";
//            PreparedStatement ps;
//
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, cajon.getNroCajon());
//            ps.setString(2, cajon.getDescripcion());
//            ps.setInt(3, 1);
//            ps.setString(4, cajon.getEmpleado().getNombre());
//            ps.setDate(5, UtilFecha.convertiFecha(cajon.getFechaFabricacion()));
//            ps.setString(6, cajon.getObservaciones());
//            ps.setInt(7, cajon.getTipoCajon().getIdTipoCajon());
//            ps.setInt(8, cajon.getCantidadAlzas());
//            ps.setInt(9, cajon.getMarcosPorAlza());
//            ps.executeUpdate();
//
//            ps.close();
//            connPool.closeConnection(conn);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.print("Error en conexion BD: GestorCajon !!! (insertar)");
//        }
//    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * FROM cajon ORDER BY 1 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Cajon
                Cajon cajon = new Cajon();
                cajon.setNroCajon(rs.getInt("numeroCajon"));
                cajon.setDescripcion(rs.getString("descripcion"));

                // Regenero el EstadoCajon
                GestorEstadoCajon gestorEstadoCajon = new GestorEstadoCajon();
                EstadoCajon estadoCajon = (EstadoCajon) gestorEstadoCajon.getUno(rs.getInt("idEstadoCajon"));
                cajon.setEstado(estadoCajon);

                // Regenero el TipoCajon
                GestorTipoCajon gestorTipoCajon = new GestorTipoCajon();
                TipoCajon tipoCajon = (TipoCajon) gestorTipoCajon.getUno(rs.getInt("idTipoCajon"));
                cajon.setTipoCajon(tipoCajon);

                // Regenero el Empleado
                GestorEmpleado gestorEmpleado = new GestorEmpleado();
                Empleado empleado = (Empleado) gestorEmpleado.getUno(rs.getInt("LegajoUsuFab"));
                cajon.setEmpleado(empleado);

                cajon.setFechaFabricacion(UtilFecha.convertiFecha(rs.getDate("fechafabricacion")));
                cajon.setObservaciones(rs.getString("observaciones"));
                cajon.setCantidadAlzas(rs.getInt("cantidadAlzas"));
                cajon.setMarcosPorAlza(rs.getInt("marcosPorAlza"));
                list.add(cajon);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCajon !!! (getTodos)");
        }
        return list;
    }

    public Object getUltimo() {
        Cajon cajon = new Cajon();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Cajon WHERE numeroCajon = (select MAX(numeroCajon) from Cajon)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cajon.setNroCajon(rs.getInt("numeroCajon"));
                cajon.setDescripcion(rs.getString("descripcion"));

                // Regenero el EstadoCajon
                GestorEstadoCajon gestorEstadoCajon = new GestorEstadoCajon();
                EstadoCajon estadoCajon = (EstadoCajon) gestorEstadoCajon.getUno(rs.getInt("idEstadoCajon"));
                cajon.setEstado(estadoCajon);

                // Regenero el TipoCajon
                GestorTipoCajon gestorTipoCajon = new GestorTipoCajon();
                TipoCajon tipoCajon = (TipoCajon) gestorTipoCajon.getUno(rs.getInt("idTipoCajon"));
                cajon.setTipoCajon(tipoCajon);

                // Regenero el Empleado
                GestorEmpleado gestorEmpleado = new GestorEmpleado();
                Empleado empleado = (Empleado) gestorEmpleado.getUno(rs.getInt("LegajoUsuFab"));
                cajon.setEmpleado(empleado);

                cajon.setFechaFabricacion(UtilFecha.convertiFecha(rs.getDate("fechafabricacion")));
                cajon.setObservaciones(rs.getString("observaciones"));
                cajon.setCantidadAlzas(rs.getInt("cantidadAlzas"));
                cajon.setMarcosPorAlza(rs.getInt("marcosPorAlza"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorSintoma !!! (getUltimo)");
        }
        return cajon;
    }

    public ArrayList getAsignado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() {
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * FROM cajon c " +
                    "WHERE c.idEstadoCajon = 1 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Cajon
                Cajon cajon = new Cajon();
                cajon.setNroCajon(rs.getInt("numeroCajon"));
                cajon.setDescripcion(rs.getString("descripcion"));

                // Regenero el EstadoCajon
                GestorEstadoCajon gestorEstadoCajon = new GestorEstadoCajon();
                EstadoCajon estadoCajon = (EstadoCajon) gestorEstadoCajon.getUno(rs.getInt("idEstadoCajon"));
                cajon.setEstado(estadoCajon);

                // Regenero el TipoCajon
                GestorTipoCajon gestorTipoCajon = new GestorTipoCajon();
                TipoCajon tipoCajon = (TipoCajon) gestorTipoCajon.getUno(rs.getInt("idTipoCajon"));
                cajon.setTipoCajon(tipoCajon);

                // Regenero el Empleado
                GestorEmpleado gestorEmpleado = new GestorEmpleado();
                Empleado empleado = (Empleado) gestorEmpleado.getUno(rs.getInt("LegajoUsuFab"));
                cajon.setEmpleado(empleado);

                cajon.setFechaFabricacion(UtilFecha.convertiFecha(rs.getDate("fechafabricacion")));
                cajon.setObservaciones(rs.getString("observaciones"));
                cajon.setCantidadAlzas(rs.getInt("cantidadAlzas"));
                cajon.setMarcosPorAlza(rs.getInt("marcosPorAlza"));

                list.add(cajon);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCajon !!! (getSinAsignar)");
        }
        return list;
    }

    public Object getUno(int idObjeto) {
        Cajon cajon = new Cajon();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM cajon " +
                    " WHERE numeroCajon = ? " +
                    " ORDER BY 1 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Cajon
                cajon.setNroCajon(rs.getInt("numeroCajon"));
                cajon.setDescripcion(rs.getString("descripcion"));

                // Regenero el EstadoCajon
                GestorEstadoCajon gestorEstadoCajon = new GestorEstadoCajon();
                EstadoCajon estadoCajon = (EstadoCajon) gestorEstadoCajon.getUno(rs.getInt("idEstadoCajon"));
                cajon.setEstado(estadoCajon);

                // Regenero el TipoCajon
                GestorTipoCajon gestorTipoCajon = new GestorTipoCajon();
                TipoCajon tipoCajon = (TipoCajon) gestorTipoCajon.getUno(rs.getInt("idTipoCajon"));
                cajon.setTipoCajon(tipoCajon);

                // Regenero el Empleado
                GestorEmpleado gestorEmpleado = new GestorEmpleado();
                Empleado empleado = (Empleado) gestorEmpleado.getUno(rs.getInt("LegajoUsuFab"));
                cajon.setEmpleado(empleado);

                cajon.setFechaFabricacion(UtilFecha.convertiFecha(rs.getDate("fechafabricacion")));
                cajon.setObservaciones(rs.getString("observaciones"));
                cajon.setCantidadAlzas(rs.getInt("cantidadAlzas"));
                cajon.setMarcosPorAlza(rs.getInt("marcosPorAlza"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCajon !!! (getUno)");
        }
        return cajon;
    }

    public int insertUno(Object object) throws Exception {
        Cajon cajon = (Cajon) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " insert into cajon (numeroCajon, descripcion, " +
                    " idEstadoCajon, idTipoCajon, legajoUsuFab, fechafabricacion," +
                    " observaciones, cantidadAlzas, marcosPorAlza ) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cajon.getNroCajon());
            ps.setString(2, cajon.getDescripcion());
            ps.setInt(3, 1);
            ps.setInt(4, cajon.getTipoCajon().getIdTipoCajon());
            ps.setInt(5, (int) cajon.getEmpleado().getLegajo());
            ps.setDate(6, UtilFecha.convertiFecha(cajon.getFechaFabricacion()));
            ps.setString(7, cajon.getObservaciones());
            ps.setInt(8, cajon.getCantidadAlzas());
            ps.setInt(9, cajon.getMarcosPorAlza());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCajon !!! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws SQLException, Exception {
        Cajon cajon = (Cajon) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " delete from cajon where numeroCajon = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cajon.getNroCajon());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                throw e;
            }
            e.printStackTrace();
            System.out.print("Error SQLException: GestorCajon !!! (deleteUno)");

        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCajon !!! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        Cajon cajon = (Cajon) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update cajon set " +
                    " numeroCajon = ? , " +
                    " descripcion = ? , " +
                    " idTipoCajon = ? , " +
                    " legajoUsuFab = ? ," +
                    " fechafabricacion = ? ," +
                    " observaciones = ?, " +
                    " cantidadAlzas = ?, " +
                    " marcosPorAlza = ? " +
                    " WHERE numeroCajon = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setInt(1, cajon.getNroCajon());
            ps.setString(2, cajon.getDescripcion());
            ps.setInt(3, cajon.getTipoCajon().getIdTipoCajon());
            ps.setInt(4, (int) cajon.getEmpleado().getLegajo());
            ps.setDate(5, UtilFecha.convertiFecha(cajon.getFechaFabricacion()));
            ps.setString(6, cajon.getObservaciones());
            ps.setInt(7, cajon.getCantidadAlzas());
            ps.setInt(8, cajon.getMarcosPorAlza());

            // Cajon a actualizar
            ps.setInt(9, cajon.getNroCajon());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCajon !!! (updateUno)");
        }
        return resultado;
    }

    public int cambiarEstado(Object object) throws Exception {
        Cajon cajon = (Cajon) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update cajon set " +
                    " idEstadoCajon = ?  " +
                    " WHERE numeroCajon = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setInt(1, cajon.getEstado().getNumero());

            // Cajon a actualizar
            ps.setInt(2, cajon.getNroCajon());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCajon !!! (cambiarEstado)");
        }
        return resultado;
    }
}

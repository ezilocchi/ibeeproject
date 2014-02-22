/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.persona.Cargo;
import ibeeproject.model.persona.Empleado;
import ibeeproject.model.persona.EstadoEmpleado;
import ibeeproject.model.soporte.UtilFecha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author carranza.matias
 */
public class GestorEmpleado implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorEmpleado() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() throws Exception {
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * FROM Empleado where fechaBaja is null ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setLegajo(rs.getInt("legajo"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));

                GestorEstadoEmpleado gestorEstadoEmpleado = new GestorEstadoEmpleado();
                EstadoEmpleado e = (EstadoEmpleado) gestorEstadoEmpleado.getUno(rs.getInt("idEstadoEmpleado"));
                empleado.setEstado(e);

                GestorCargo gestorCargo = new GestorCargo();
                Cargo c = (Cargo) gestorCargo.getUno(rs.getInt("idCargo"));
                empleado.setCargo(c);

                empleado.setObservaciones(rs.getString("observaciones"));
                empleado.setUsuario(rs.getString("usuario"));
                
                empleado.setPassword(rs.getString("password"));

                empleado.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                empleado.setFechaBaja(UtilFecha.convertiFecha(rs.getDate("fechaBaja")));
                empleado.setEmail(rs.getString("email"));
                list.add(empleado);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);

        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEmpleado !!! (getUno)");
        }
        return list;
    }

    public Object getUltimo() throws Exception {
        Empleado empleado = new Empleado();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM empleado WHERE legajo = (select MAX(legajo) from empleado)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                empleado.setLegajo(rs.getInt("legajo"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));

                GestorEstadoEmpleado gestorEstadoEmpleado = new GestorEstadoEmpleado();
                EstadoEmpleado e = (EstadoEmpleado) gestorEstadoEmpleado.getUno(rs.getInt("idEstadoEmpleado"));
                empleado.setEstado(e);

                GestorCargo gestorCargo = new GestorCargo();
                Cargo c = (Cargo) gestorCargo.getUno(rs.getInt("idCargo"));
                empleado.setCargo(c);

                empleado.setObservaciones(rs.getString("observaciones"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setPassword(rs.getString("password"));
                empleado.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                empleado.setFechaBaja(UtilFecha.convertiFecha(rs.getDate("fechaBaja")));
                empleado.setEmail(rs.getString("email"));
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEmpleado.getUltimo() !!!");
        }
        return empleado;
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) throws Exception {
        Empleado empleado = new Empleado();
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * FROM Empleado where fechaBaja is null and legajo = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                empleado.setLegajo(rs.getInt("legajo"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));

                GestorEstadoEmpleado gestorEstadoEmpleado = new GestorEstadoEmpleado();
                EstadoEmpleado e = (EstadoEmpleado) gestorEstadoEmpleado.getUno(rs.getInt("idEstadoEmpleado"));
                empleado.setEstado(e);

                GestorCargo gestorCargo = new GestorCargo();
                Cargo c = (Cargo) gestorCargo.getUno(rs.getInt("idCargo"));
                empleado.setCargo(c);

                empleado.setObservaciones(rs.getString("observaciones"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setPassword(rs.getString("password"));
                empleado.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                empleado.setFechaBaja(UtilFecha.convertiFecha(rs.getDate("fechaBaja")));
                empleado.setEmail(rs.getString("email"));
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEmpleado !!! (getUno)");
        }
        return empleado;
    }

    public int insertUno(Object object) throws Exception {
        Empleado empleado = (Empleado) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = "insert into empleado(legajo, nombre, apellido, idEstadoEmpleado, idCargo, observaciones, usuario, password, fechaAlta, email) " +
                    " values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ps.setInt(1, empleado.getLegajo());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellido());
            ps.setInt(4, empleado.getEstado().getNumero());
            ps.setInt(5, empleado.getCargo().getIdCargo());
            ps.setString(6, empleado.getObservaciones());
            ps.setString(7, empleado.getUsuario());
            ps.setString(8, empleado.getPassword());
            ps.setDate(9, UtilFecha.convertiFecha(empleado.getFechaAlta()));
            ps.setString(10, empleado.getEmail());

            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEmpleado.insertUno() ");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {

        Empleado empleado = (Empleado) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update empleado set " +
                    " fechaBaja = ?  " +
                    " WHERE legajo = ? ";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            long fecha = System.currentTimeMillis();
            Date hoy = new Date(fecha);
            ps.setDate(1, UtilFecha.convertiFecha(hoy));
            ps.setInt(2, empleado.getLegajo());

            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEmpleado !!! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        Empleado empleado = (Empleado) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update empleado set " +
                    " nombre = ? , " +
                    " apellido = ? , " +
                    " idEstadoEmpleado = ? , " +
                    " idCargo = ? , " +
                    " observaciones = ? ," +
                    " usuario = ? ," +
                    " fechaAlta = ? ," +
                    " email = ? , " +
                    " password = ? " +
                    " WHERE legajo = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setInt(3, empleado.getEstado().getNumero());
            ps.setInt(4, empleado.getCargo().getIdCargo());
            ps.setString(5, empleado.getObservaciones());
            ps.setString(6, empleado.getUsuario());
            ps.setDate(7, UtilFecha.convertiFecha(empleado.getFechaAlta()));
            ps.setString(8, empleado.getEmail());
            ps.setInt(9, empleado.getLegajo());
            ps.setString(10, empleado.getPassword());

            // Empleado a actualizar
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEmpleado.updateUno !!! (updateUno)");
        }
        return resultado;
    }

    public Object getUno(String usuario) {
        Empleado empleado=null;
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * FROM Empleado where usuario = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                empleado = new Empleado();
                
                empleado.setLegajo(rs.getInt("legajo"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));

                GestorEstadoEmpleado gestorEstadoEmpleado = new GestorEstadoEmpleado();
                EstadoEmpleado e = (EstadoEmpleado) gestorEstadoEmpleado.getUno(rs.getInt("idEstadoEmpleado"));
                empleado.setEstado(e);

                GestorCargo gestorCargo = new GestorCargo();
                Cargo c = (Cargo) gestorCargo.getUno(rs.getInt("idCargo"));
                empleado.setCargo(c);

                empleado.setObservaciones(rs.getString("observaciones"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setPassword(rs.getString("password"));
                empleado.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                empleado.setFechaBaja(UtilFecha.convertiFecha(rs.getDate("fechaBaja")));
                empleado.setEmail(rs.getString("email"));
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEmpleado !!! (getUno)");
        } finally {
            connPool.closeConnection(conn);
        }
        return empleado;
    }
}

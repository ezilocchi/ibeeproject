/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.persona.Cargo;
import ibeeproject.model.persona.Empleado;
import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.model.zona.Alarma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorAlarma implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorAlarma() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;

            String sql = "SELECT * FROM alarma ORDER BY 1";
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Alarma alarma = new Alarma();
                alarma.setNumero(rs.getInt("idAlarma"));
                alarma.setIdZona(rs.getInt("idZona"));
                alarma.setDenominacion(rs.getString("denominacion"));
                alarma.setDescripcion(rs.getString("descripcion"));
                alarma.setOrigen(rs.getString("origen"));
                alarma.setFechaCreacion(UtilFecha.convertiFecha(rs.getDate("fechaCreacion")));
                alarma.setFechaInicio(UtilFecha.convertiFecha(rs.getDate("fechaInicio")));
                alarma.setFechaFin(UtilFecha.convertiFecha(rs.getDate("fechaFin")));
                alarma.setActivado(rs.getBoolean("activado"));
                alarma.setRangoDesde(rs.getDouble("rangoDesde"));
                alarma.setRangoHasta(rs.getDouble("rangoHasta"));

//                //Regenero el tipo alarma
//                GestorTipoAlarma gestorTipoAlarma = new GestorTipoAlarma();
//                TipoAlarma tipoAlarma = (TipoAlarma) gestorTipoAlarma.getUno(rs.getInt("idTipoAlarma"));
//                alarma.setTipo(tipoAlarma);

//                //Regenero el estado alarma
//                GestorEstadoAlarma gestorEstadoAlarma = new GestorEstadoAlarma();
//                EstadoAlarma estadoAlarma = (EstadoAlarma) gestorEstadoAlarma.getUno(rs.getInt("idEstado"));
//                alarma.setEstado(estadoAlarma);

//                //Regenero la periodicidad
//                GestorPeriodicidad gestorPeriodicidad = new GestorPeriodicidad();
//                Periodicidad periodicidad = (Periodicidad) gestorPeriodicidad.getUno(rs.getInt("idPeriodicidad"));
//                alarma.setPeriodicidad(periodicidad);

                //Regenero el cargo
                GestorCargo gestorCargo = new GestorCargo();
                Cargo cargo = (Cargo) gestorCargo.getUno(rs.getInt("idCargo"));
                alarma.setCargo(cargo);

                //Regenero el historialAlarma
                GestorHistorialAlarma gestorHistorialAlarma = new GestorHistorialAlarma();
                alarma.setHistorialAlarma(gestorHistorialAlarma.getTodos(rs.getInt("idAlarma")));


                list.add(alarma);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAlarma!!!");
        }
        return list;
    }

    public ArrayList getActivos(int estado) {
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;

            String sql = "SELECT * FROM alarma WHERE activado = ? ORDER BY 1";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, estado);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Alarma alarma = new Alarma();
                alarma.setNumero(rs.getInt("idAlarma"));
                alarma.setIdZona(rs.getInt("idZona"));
                alarma.setDenominacion(rs.getString("denominacion"));
                alarma.setDescripcion(rs.getString("descripcion"));
                alarma.setOrigen(rs.getString("origen"));
                alarma.setFechaCreacion(UtilFecha.convertiFecha(rs.getDate("fechaCreacion")));
                alarma.setFechaInicio(UtilFecha.convertiFecha(rs.getDate("fechaInicio")));
                alarma.setFechaFin(UtilFecha.convertiFecha(rs.getDate("fechaFin")));
                alarma.setActivado(rs.getBoolean("activado"));
                alarma.setRangoDesde(rs.getDouble("rangoDesde"));
                alarma.setRangoHasta(rs.getDouble("rangoHasta"));

//                //Regenero el tipo alarma
//                GestorTipoAlarma gestorTipoAlarma = new GestorTipoAlarma();
//                TipoAlarma tipoAlarma = (TipoAlarma) gestorTipoAlarma.getUno(rs.getInt("idTipoAlarma"));
//                alarma.setTipo(tipoAlarma);
//
//                //Regenero el estado alarma
//                GestorEstadoAlarma gestorEstadoAlarma = new GestorEstadoAlarma();
//                EstadoAlarma estadoAlarma = (EstadoAlarma) gestorEstadoAlarma.getUno(rs.getInt("idEstado"));
//                alarma.setEstado(estadoAlarma);
//
//                //Regenero la periodicidad
//                GestorPeriodicidad gestorPeriodicidad = new GestorPeriodicidad();
//                Periodicidad periodicidad = (Periodicidad) gestorPeriodicidad.getUno(rs.getInt("idPeriodicidad"));
//                alarma.setPeriodicidad(periodicidad);

                //Regenero el historialAlarma
                GestorHistorialAlarma gestorHistorialAlarma = new GestorHistorialAlarma();
                alarma.setHistorialAlarma(gestorHistorialAlarma.getTodos(rs.getInt("idAlarma")));

                //Regenero el cargo
                GestorCargo gestorCargo = new GestorCargo();
                Cargo cargo = (Cargo) gestorCargo.getUno(rs.getInt("idCargo"));
                alarma.setCargo(cargo);


                list.add(alarma);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAlarma!!! (getActivos(" + estado + ")");
        }
        return list;
    }

    public Object getUltimo() {
        Alarma alarma = new Alarma();
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;

            String sql = "SELECT * FROM alarma WHERE idAlarma = (select MAX(idAlarma) from Alarma)";
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                alarma.setNumero(rs.getInt("idAlarma"));
                alarma.setIdZona(rs.getInt("idZona"));
                alarma.setDenominacion(rs.getString("denominacion"));
                alarma.setDescripcion(rs.getString("descripcion"));
                alarma.setOrigen(rs.getString("origen"));
                alarma.setFechaCreacion(UtilFecha.convertiFecha(rs.getDate("fechaCreacion")));
                alarma.setFechaInicio(UtilFecha.convertiFecha(rs.getDate("fechaInicio")));
                alarma.setFechaFin(UtilFecha.convertiFecha(rs.getDate("fechaFin")));
                alarma.setActivado(rs.getBoolean("activado"));
                alarma.setRangoDesde(rs.getDouble("rangoDesde"));
                alarma.setRangoHasta(rs.getDouble("rangoHasta"));

//                //Regenero el tipo alarma
//                GestorTipoAlarma gestorTipoAlarma = new GestorTipoAlarma();
//                TipoAlarma tipoAlarma = (TipoAlarma) gestorTipoAlarma.getUno(rs.getInt("idTipoAlarma"));
//                alarma.setTipo(tipoAlarma);
//
//                //Regenero el estado alarma
//                GestorEstadoAlarma gestorEstadoAlarma = new GestorEstadoAlarma();
//                EstadoAlarma estadoAlarma = (EstadoAlarma) gestorEstadoAlarma.getUno(rs.getInt("idEstado"));
//                alarma.setEstado(estadoAlarma);
//
//                //Regenero la periodicidad
//                GestorPeriodicidad gestorPeriodicidad = new GestorPeriodicidad();
//                Periodicidad periodicidad = (Periodicidad) gestorPeriodicidad.getUno(rs.getInt("idPeriodicidad"));
//                alarma.setPeriodicidad(periodicidad);

                //Regenero el historialAlarma
                GestorHistorialAlarma gestorHistorialAlarma = new GestorHistorialAlarma();
                alarma.setHistorialAlarma(gestorHistorialAlarma.getTodos(rs.getInt("idAlarma")));

                //Regenero el cargo
                GestorCargo gestorCargo = new GestorCargo();
                Cargo cargo = (Cargo) gestorCargo.getUno(rs.getInt("idCargo"));
                alarma.setCargo(cargo);


            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAlarma!!! (getUltimo)");
        }
        return alarma;
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) throws Exception {
        Alarma alarma = new Alarma();
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;

            String sql = "SELECT * FROM alarma " +
                    " WHERE idAlarma = ? " +
                    " ORDER BY 1";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                alarma.setNumero(rs.getInt("idAlarma"));
                alarma.setIdZona(rs.getInt("idZona"));
                alarma.setDenominacion(rs.getString("denominacion"));
                alarma.setDescripcion(rs.getString("descripcion"));
                alarma.setOrigen(rs.getString("origen"));
                alarma.setFechaCreacion(UtilFecha.convertiFecha(rs.getDate("fechaCreacion")));
                alarma.setFechaInicio(UtilFecha.convertiFecha(rs.getDate("fechaInicio")));
                alarma.setFechaFin(UtilFecha.convertiFecha(rs.getDate("fechaFin")));
                alarma.setActivado(rs.getBoolean("activado"));
                alarma.setRangoDesde(rs.getDouble("rangoDesde"));
                alarma.setRangoHasta(rs.getDouble("rangoHasta"));

                //Regenero el cargo
                GestorCargo gestorCargo = new GestorCargo();
                Cargo cargo = (Cargo) gestorCargo.getUno(rs.getInt("idCargo"));
                alarma.setCargo(cargo);

                //Regenero el historialAlarma
                GestorHistorialAlarma gestorHistorialAlarma = new GestorHistorialAlarma();
                alarma.setHistorialAlarma(gestorHistorialAlarma.getTodos(rs.getInt("idAlarma")));
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAlarma.getUno ");
        }
        return alarma;
    }

    public int insertUno(Object object) throws Exception {
        Alarma alarma = (Alarma) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
//            String sql = " insert into alarma (idAlarma, idZona, denominacion, descripcion, " +
//                    " origen, fechaCreacion, fechaInicio, fechaFin, idPeriodicidad," +
//                    " idTipoAlarma, idEstado, activado, rangoDesde, rangoHasta, idCargo ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String sql = " insert into alarma (idAlarma, idZona, denominacion, descripcion, " +
                    " origen, fechaCreacion, fechaInicio, fechaFin, " +
                    " activado, rangoDesde, rangoHasta, idCargo ) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, alarma.getNumero());
            ps.setInt(2, alarma.getIdZona());
            ps.setString(3, alarma.getDenominacion());
            ps.setString(4, alarma.getDescripcion());
            ps.setString(5, alarma.getOrigen());
            ps.setDate(6, UtilFecha.convertiFecha(alarma.getFechaCreacion()));
            ps.setDate(7, UtilFecha.convertiFecha(alarma.getFechaInicio()));
            ps.setDate(8, UtilFecha.convertiFecha(alarma.getFechaFin()));
//            ps.setInt(9, alarma.getPeriodicidad().getNumero());
//            ps.setInt(10, alarma.getTipo().getNumero());
//            ps.setInt(11, alarma.getEstado().getNumero());
            ps.setBoolean(9, alarma.isActivado());
            ps.setDouble(10, alarma.getRangoDesde());
            ps.setDouble(11, alarma.getRangoHasta());
            ps.setInt(12, alarma.getCargo().getIdCargo());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAlarma !!! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int updateUno(Object object) throws Exception {
        Alarma alarma = (Alarma) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update Alarma set " +
                    " denominacion = ? , " +
                    " descripcion = ? , " +
                    " fechaFin = ? , " +
                    //                    " idTipoAlarma = ? , " +
                    //                    " idPeriodicidad = ? , " +
                    " rangoDesde = ? , " +
                    " rangoHasta = ? , " +
                    " idCargo = ? " +
                    " WHERE idAlarma = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setString(1, alarma.getDenominacion());
            ps.setString(2, alarma.getDescripcion());
            ps.setDate(3, UtilFecha.convertiFecha(alarma.getFechaFin()));
//            ps.setInt(4, alarma.getTipo().getNumero());
//            ps.setInt(5, alarma.getPeriodicidad().getNumero());
            ps.setDouble(4, alarma.getRangoDesde());
            ps.setDouble(5, alarma.getRangoHasta());
            ps.setInt(6, alarma.getCargo().getIdCargo());

            // Alarma a actualizar
            ps.setInt(7, alarma.getNumero());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAlarma! (updateUno)");
        }
        return resultado;
    }

    public int desactivarUno(Object object) {
        Alarma alarma = (Alarma) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update Alarma set " +
                    " activado = 0 " +
                    " WHERE idAlarma = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Alarma a actualizar
            ps.setInt(1, alarma.getNumero());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAlarma! (desactivarUno)");
        }
        return resultado;
    }

    public int activarUno(Object object) {
        Alarma alarma = (Alarma) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update Alarma set " +
                    " activado = 1 " +
                    " WHERE idAlarma = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Alarma a actualizar
            ps.setInt(1, alarma.getNumero());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAlarma! (activarUno)");
        }
        return resultado;
    }

    public ArrayList getTopTenPorEmpleado(Empleado empleado) {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT a.* " +
                        "FROM alarma a " +
                        "WHERE a.idCargo = ? AND a.activado = 1 " +
                        "ORDER BY idAlarma DESC LIMIT 5";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, empleado.getCargo().getIdCargo());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto enfermedad
                Alarma alarma = new Alarma();
                alarma.setNumero(rs.getInt("idAlarma"));
                alarma.setIdZona(rs.getInt("idZona"));
                alarma.setDenominacion(rs.getString("denominacion"));
                alarma.setDescripcion(rs.getString("descripcion"));
                alarma.setOrigen(rs.getString("origen"));
                alarma.setFechaCreacion(UtilFecha.convertiFecha(rs.getDate("fechaCreacion")));
                alarma.setFechaInicio(UtilFecha.convertiFecha(rs.getDate("fechaInicio")));
                //alarma.setFechaFin(UtilFecha.convertiFecha(rs.getDate("fechaFin")));

                // Seteo los campos en NULL porque no los necesito para la interfaz
                list.add(alarma);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAlarma! (getTopFive)");
        }
        return list;
    }

    public ArrayList getAlarmasMedicion(int idZona, int medicion) {
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;

            String sql = "SELECT * " +
                    "FROM alarma a " +
                    "WHERE fechaInicio <= NOW() AND fechaFin >= NOW() AND activado = 1 " +
                    "AND idZona = ? " +
                    "AND ? NOT BETWEEN rangoDesde AND rangoHasta";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idZona);
            ps.setInt(2, medicion);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Alarma alarma = new Alarma();
                alarma.setNumero(rs.getInt("idAlarma"));
                alarma.setIdZona(rs.getInt("idZona"));
                alarma.setDenominacion(rs.getString("denominacion"));
                alarma.setDescripcion(rs.getString("descripcion"));
                alarma.setOrigen(rs.getString("origen"));
                alarma.setFechaCreacion(UtilFecha.convertiFecha(rs.getDate("fechaCreacion")));
                alarma.setFechaInicio(UtilFecha.convertiFecha(rs.getDate("fechaInicio")));
                alarma.setFechaFin(UtilFecha.convertiFecha(rs.getDate("fechaFin")));
                alarma.setActivado(rs.getBoolean("activado"));
                alarma.setRangoDesde(rs.getDouble("rangoDesde"));
                alarma.setRangoHasta(rs.getDouble("rangoHasta"));

//                //Regenero el tipo alarma
//                GestorTipoAlarma gestorTipoAlarma = new GestorTipoAlarma();
//                TipoAlarma tipoAlarma = (TipoAlarma) gestorTipoAlarma.getUno(rs.getInt("idTipoAlarma"));
//                alarma.setTipo(tipoAlarma);
//
//                //Regenero el estado alarma
//                GestorEstadoAlarma gestorEstadoAlarma = new GestorEstadoAlarma();
//                EstadoAlarma estadoAlarma = (EstadoAlarma) gestorEstadoAlarma.getUno(rs.getInt("idEstado"));
//                alarma.setEstado(estadoAlarma);
//
//                //Regenero la periodicidad
//                GestorPeriodicidad gestorPeriodicidad = new GestorPeriodicidad();
//                Periodicidad periodicidad = (Periodicidad) gestorPeriodicidad.getUno(rs.getInt("idPeriodicidad"));
//                alarma.setPeriodicidad(periodicidad);

                //Regenero el cargo
                GestorCargo gestorCargo = new GestorCargo();
                Cargo cargo = (Cargo) gestorCargo.getUno(rs.getInt("idCargo"));
                alarma.setCargo(cargo);

                //Regenero el historialAlarma
                GestorHistorialAlarma gestorHistorialAlarma = new GestorHistorialAlarma();
                alarma.setHistorialAlarma(gestorHistorialAlarma.getTodos(rs.getInt("idAlarma")));


                list.add(alarma);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAlarma!!! (getAlarmasMedicion");
        }
        return list;
    }
}

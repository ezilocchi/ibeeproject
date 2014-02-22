/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.actividad.Actividad;
import ibeeproject.model.actividad.EstadoActividad;
import ibeeproject.model.actividad.Tarea;
import ibeeproject.model.actividad.TipoActividad;
import ibeeproject.model.apiar.Apiar;
import ibeeproject.model.soporte.UtilFecha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorActividad implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorActividad() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() throws Exception {
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * FROM Actividad ORDER BY 1 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setNumero(rs.getInt("idActividad"));
                actividad.setDenominacion(rs.getString("denominacion"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setFechaCreacion(UtilFecha.convertiFecha(rs.getDate("fechaCreacion")));
                actividad.setFechaInicio(UtilFecha.convertiFecha(rs.getDate("fechaInicio")));
                actividad.setFechaFin(UtilFecha.convertiFecha(rs.getDate("fechaFin")));
                actividad.setFechaEsperadaDeCierre(UtilFecha.convertiFecha(rs.getDate("fechaEsperadaDeCierre")));
                actividad.setObservaciones(rs.getString("observaciones"));

                // Reconstruyo el Tipo de Actividad
                GestorTipoActividad gestorTipoActividad = new GestorTipoActividad();
                TipoActividad tipoDeActividad = (TipoActividad) gestorTipoActividad.getUno(rs.getString("codTipoActividad"));
                actividad.setTipoActividad(tipoDeActividad);

                // Reconstruyo es Estado de Actividad
                GestorEstadoActividad gestorEstadoActividad = new GestorEstadoActividad();
                EstadoActividad estadoActividad = (EstadoActividad) gestorEstadoActividad.getUno(rs.getInt("idEstado"));
                actividad.setEstado(estadoActividad);

                // Reconstruyo las Tareas asociadas
                GestorTarea gestorTarea = new GestorTarea();
                ArrayList tareas = gestorTarea.getTodos(actividad.getNumero());
                actividad.setTareas(tareas);

                list.add(actividad);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorActividad! (getTodos)");
        }
        return list;
    }

    public ArrayList getTodosConFiltro(String sqlWhere) throws Exception {
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * FROM Actividad " +
                    sqlWhere +
                    "ORDER BY 1 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setNumero(rs.getInt("idActividad"));
                actividad.setDenominacion(rs.getString("denominacion"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setFechaCreacion(UtilFecha.convertiFecha(rs.getDate("fechaCreacion")));
                actividad.setFechaInicio(UtilFecha.convertiFecha(rs.getDate("fechaInicio")));
                actividad.setFechaFin(UtilFecha.convertiFecha(rs.getDate("fechaFin")));
                actividad.setFechaEsperadaDeCierre(UtilFecha.convertiFecha(rs.getDate("fechaEsperadaDeCierre")));
                actividad.setObservaciones(rs.getString("observaciones"));

                // Reconstruyo el Tipo de Actividad
                GestorTipoActividad gestorTipoActividad = new GestorTipoActividad();
                TipoActividad tipoDeActividad = (TipoActividad) gestorTipoActividad.getUno(rs.getString("codTipoActividad"));
                actividad.setTipoActividad(tipoDeActividad);

                // Reconstruyo es Estado de Actividad
                GestorEstadoActividad gestorEstadoActividad = new GestorEstadoActividad();
                EstadoActividad estadoActividad = (EstadoActividad) gestorEstadoActividad.getUno(rs.getInt("idEstado"));
                actividad.setEstado(estadoActividad);

                // Reconstruyo las Tareas asociadas
                GestorTarea gestorTarea = new GestorTarea();
                ArrayList tareas = gestorTarea.getTodos(actividad.getNumero());
                actividad.setTareas(tareas);

                list.add(actividad);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorActividad! (getTodosConFiltro)");
        }
        return list;
    }

    public ArrayList getTopFive() {
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * FROM actividad " +
                    " WHERE idEstado IN (1,2) " +
                    " ORDER BY fechaEsperadaDeCierre desc LIMIT 5 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setNumero(rs.getInt("idActividad"));
                actividad.setDenominacion(rs.getString("denominacion"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setFechaCreacion(UtilFecha.convertiFecha(rs.getDate("fechaCreacion")));
                actividad.setFechaInicio(UtilFecha.convertiFecha(rs.getDate("fechaInicio")));
                actividad.setFechaFin(UtilFecha.convertiFecha(rs.getDate("fechaFin")));
                actividad.setObservaciones(rs.getString("observaciones"));

                // Seteo solo algunos atributos porque son los visibles en interfaz
                list.add(actividad);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorActividad! (getTopFive)");
        } finally {
            connPool.closeConnection(conn);
        }
        return list;
    }

    public Object getUltimo() throws Exception {
        Actividad actividad = new Actividad();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Actividad WHERE idActividad = " +
                    " (select MAX(idActividad) from Actividad)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto enfermedad
                actividad.setNumero(rs.getInt("idActividad"));
                actividad.setDenominacion(rs.getString("denominacion"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setFechaCreacion(UtilFecha.convertiFecha(rs.getDate("fechaCreacion")));
                actividad.setFechaInicio(UtilFecha.convertiFecha(rs.getDate("fechaInicio")));
                actividad.setFechaFin(UtilFecha.convertiFecha(rs.getDate("fechaFin")));
                actividad.setObservaciones(rs.getString("observaciones"));

            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorActividad!!! (getUno)");
        }
        return actividad;
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) throws Exception {
        Actividad actividad = new Actividad();
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * FROM Actividad " +
                    " WHERE idActividad =  ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto enfermedad
                actividad.setNumero(rs.getInt("idActividad"));
                actividad.setDenominacion(rs.getString("denominacion"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setFechaCreacion(UtilFecha.convertiFecha(rs.getDate("fechaCreacion")));
                actividad.setFechaInicio(UtilFecha.convertiFecha(rs.getDate("fechaInicio")));
                actividad.setFechaFin(UtilFecha.convertiFecha(rs.getDate("fechaFin")));
                actividad.setObservaciones(rs.getString("observaciones"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorActividad !!! (getUno)");
        }
        return actividad;
    }

    public int insertUno(Object object) throws Exception {
        Actividad actividad = (Actividad) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " insert into actividad (idActividad, codTipoActividad, " +
                    " denominacion, descripcion, fechaCreacion, fechaEsperadaDeCierre, " +
                    " fechaInicio, fechaFin,observaciones, idEstado) " +
                    " values (?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, actividad.getNumero());
            ps.setString(2, actividad.getTipoActividad().getCodigo());
            ps.setString(3, actividad.getDenominacion());
            ps.setString(4, actividad.getDescripcion());
            ps.setDate(5, UtilFecha.convertiFecha(actividad.getFechaCreacion()));
            ps.setDate(6, UtilFecha.convertiFecha(actividad.getFechaEsperadaDeCierre()));
            ps.setDate(7, UtilFecha.convertiFecha(actividad.getFechaInicio()));
            ps.setDate(8, UtilFecha.convertiFecha(actividad.getFechaFin()));
            ps.setString(9, actividad.getObservaciones());
            ps.setInt(10, actividad.getEstado().getNumero());
            resultado = ps.executeUpdate();
            // Inserto las tareas

            for (int i = 0; i < actividad.getTareas().size(); i++) {
                GestorTarea gestorTarea = new GestorTarea();
                Tarea tarea = (Tarea) actividad.getTareas().get(i);
                tarea.setIdActividad(actividad.getNumero());
                gestorTarea.insertUno(tarea);
            }
            ps.close();
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoActividad! (insertUno)");
        } finally {
            connPool.closeConnection(conn);
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        Actividad actividad = (Actividad) object;
        int resultado = 0;
        try {
            // Primero actualizo el estado de las tareas relacionadas a Anulado
            GestorTarea gestorTarea = new GestorTarea();
            gestorTarea.deleteUno(actividad.getNumero());

            // Ahora actualizo el estado de la actividad
            conn = connPool.getConnection();
            String sql = " update Actividad set " +
                    " idEstado = ?, " +
                    " fechaAnulacion = ? " +
                    " where idActividad = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, actividad.getEstado().getNumero());
            ps.setDate(2, UtilFecha.convertiFecha(actividad.getFechaAnulacion()));
            ps.setInt(3, actividad.getNumero());
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorActividad! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        Actividad actividad = (Actividad) object;
        int resultado = 0;
        try {
            // Primero actualizo las tareas relacionadas
            GestorTarea gestorTarea = new GestorTarea();
            gestorTarea.updateUno(actividad.getTareas());

            // Ahora actualizo la actividad
            conn = connPool.getConnection();
            String sql = " update actividad set     " +
                    "  idEstado = ?,           " +
                    "  fechaInicio = ?,        " +
                    "  fechaFin = ?            " +
                    "  where idActividad = ?   ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setInt(1, actividad.getEstado().getNumero());
            if (actividad.getFechaInicio() == null) {
                ps.setNull(2, java.sql.Types.DATE);
            } else {
                ps.setDate(2, UtilFecha.convertiFecha(actividad.getFechaInicio()));
            }

            if (actividad.getFechaFin() == null) {
                ps.setNull(3, java.sql.Types.DATE);
            } else {
                ps.setDate(3, UtilFecha.convertiFecha(actividad.getFechaFin()));
            }

            // Enfermedad a actualizar
            ps.setInt(4, actividad.getNumero());
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorActividad! (updateUno)");
        }
        return resultado;
    }
}

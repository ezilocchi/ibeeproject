/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.actividad.TipoActividad;
import ibeeproject.model.persona.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorTipoActividad implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorTipoActividad() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() throws Exception {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM TipoActividad ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoActividad tipoActividad = new TipoActividad();
                tipoActividad.setCodigo(rs.getString("codTipoActividad"));
                tipoActividad.setDenominacion(rs.getString("denominacion"));
                tipoActividad.setDescripcion(rs.getString("descripcion"));

                // Reconstruyo el Cargo
                GestorCargo gestorCargo = new GestorCargo();
                Cargo cargo = (Cargo) gestorCargo.getUno(rs.getInt("idCargo"));
                tipoActividad.setCargo(cargo);

                // Reconstruyo los Tipos de Tarea
                GestorTipoTarea gestorTpoTar = new GestorTipoTarea();
                ArrayList tpoTars = gestorTpoTar.getTodos(tipoActividad.getCodigo());
                tipoActividad.setTipoTareas(tpoTars);

                list.add(tipoActividad);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoActividad! (getTodos)");
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

    public Object getUno(String idObjeto) {
        TipoActividad tipoActividad = new TipoActividad();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM TipoActividad where codTipoActividad = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipoActividad.setCodigo(rs.getString("codTipoActividad"));
                tipoActividad.setDenominacion(rs.getString("denominacion"));
                tipoActividad.setDescripcion(rs.getString("descripcion"));

                list.add(tipoActividad);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoActividad! (getUno)");
        }
        return tipoActividad;
    }

    public int insertUno(Object object) throws Exception {
        TipoActividad tipoActividad = (TipoActividad) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = "insert into tipoactividad(codtipoactividad, denominacion, " +
                    "descripcion, idCargo) values (?,?,?,?) ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, tipoActividad.getCodigo());
            ps.setString(2, tipoActividad.getDenominacion());
            ps.setString(3, tipoActividad.getDescripcion());
            ps.setInt(4, tipoActividad.getCargo().getIdCargo());
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);

        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoActividad! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        TipoActividad tipoActividad = (TipoActividad) object;
        int resultado = 0;
        try {
            // Primero elimino los datos de tipos de tarea relacionados
            GestorTipoTarea gestorTpoTar = new GestorTipoTarea();
            gestorTpoTar.deleteUno(tipoActividad.getCodigo());

            // Ahora elimino los tipos de actividad
            conn = connPool.getConnection();
            String sql = " delete from tipoactividad where codTipoActividad = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, tipoActividad.getCodigo());
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoActividad! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        TipoActividad tipoActividad = (TipoActividad) object;
        int resultado = 0;
        try {
            // Primero actualizo los datos de tipos de tarea relacionados
            GestorTipoTarea gestorTpoTar = new GestorTipoTarea();
            gestorTpoTar.updateUno(tipoActividad.getTipoTareas());

            // Ahora actualizo el tipos de actividad
            conn = connPool.getConnection();
            String sql = " update tipoactividad set     " +
                         "  denominacion = ?,           " +
                         "  descripcion = ?,            " +
                         "  idCargo = ?                 " +
                         "  where codtipoactividad = ?  " ;
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setString(1, tipoActividad.getDenominacion());
            ps.setString(2, tipoActividad.getDescripcion());
            ps.setInt(3, tipoActividad.getCargo().getIdCargo());

            // Enfermedad a actualizar
            ps.setString(4, tipoActividad.getCodigo());
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoActividad! (updateUno)");
        }
        return resultado;
    }

    public Object getUno(int idObjeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

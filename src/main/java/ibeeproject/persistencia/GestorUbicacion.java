/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.apiar.Apiar;
import ibeeproject.model.apiar.Ubicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class GestorUbicacion implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorUbicacion() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUltimo() throws Exception {
        Ubicacion ubicacion = new Ubicacion();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT max(idUbicacion), latitud, longitud FROM ubicacion ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ubicacion.setIdUbicacion(rs.getInt("max(idUbicacion)"));
                ubicacion.setLatitud(rs.getDouble("latitud"));
                ubicacion.setLongitud(rs.getDouble("longitud"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorUbicacion !!! (getUltimo)");
        }
        return ubicacion;
    }

    public ArrayList<Ubicacion> getAsignado(int id) throws Exception {
        this.list = new ArrayList();
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct u.idUbicacion, u.latitud, u.longitud, u.observaciones FROM ubicacion as u, ubicaciondezona as uz " +
                    " where u.idUbicacion= uz.idUbicacion" +
                    " and uz.idZona = ? " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Ubicacion
                Ubicacion ubicacion = new Ubicacion();
                ubicacion.setIdUbicacion(rs.getInt("u.idUbicacion"));
                ubicacion.setLatitud(rs.getDouble("u.latitud"));
                ubicacion.setLongitud(rs.getDouble("u.longitud"));
                ubicacion.setObservaciones(rs.getString("u.observaciones"));
                list.add(ubicacion);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorUbicacion !!!");
        }
        return list;
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) throws Exception {
        Ubicacion ubicacion = new Ubicacion();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM ubicacion " +
                    " where idUbicacion= ? " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto familia
                ubicacion.setLatitud(rs.getDouble("latitud"));
                ubicacion.setLongitud(rs.getDouble("longitud"));
                ubicacion.setObservaciones(rs.getString("observaciones"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorUbicacion !!!");
        }
        return ubicacion;
    }

    public int insertUno(Object object) throws Exception {
        Ubicacion ubicacion = (Ubicacion) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " insert into ubicacion( latitud, longitud) " +
                    " values (?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            // Atributos a actualizar
            ps.setDouble(1, ubicacion.getLatitud());
            ps.setDouble(2, ubicacion.getLongitud());
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorUbicacion !!! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int updateUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

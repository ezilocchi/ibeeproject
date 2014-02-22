/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.actividad.EstadoTarea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorEstadoTarea implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorEstadoTarea() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM EstadoTarea ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EstadoTarea estado = new EstadoTarea();
                estado.setNumero(rs.getInt("idEstadoTarea"));
                estado.setDescripcion(rs.getString("descripcion"));
                list.add(estado);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEstadoTarea! (getTodos)");
        }
        return list;
    }

    public ArrayList getTodosParaResolucion() {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM EstadoTarea " +
                    " WHERE idEstadoTarea IN (3,4,5) " +
                    " ORDER BY 1 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EstadoTarea estado = new EstadoTarea();
                estado.setNumero(rs.getInt("idEstadoTarea"));
                estado.setDescripcion(rs.getString("descripcion"));
                list.add(estado);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEstadoTarea! (getTodos)");
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
        EstadoTarea estado = new EstadoTarea();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM EstadoTarea where idEstadoTarea = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                estado.setNumero(rs.getInt("idEstadoTarea"));
                estado.setDescripcion(rs.getString("descripcion"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEstadoTarea! (getUno)");
        }
        return estado;
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

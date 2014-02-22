/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import ibeeproject.model.actividad.TipoActividad;
import ibeeproject.model.zona.TipoAlarma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorTipoAlarma implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorTipoAlarma() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM TipoAlarma ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoAlarma tipoAlarma = new TipoAlarma();
                tipoAlarma.setNumero(rs.getInt("idTipoAlarma"));
                tipoAlarma.setDenominacion(rs.getString("denominacion"));
                tipoAlarma.setDescripcion(rs.getString("descripcion"));
                list.add(tipoAlarma);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoAlarma! (getTodos)");
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
        TipoAlarma tipoAlarma = new TipoAlarma();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM TipoAlarma where idTipoAlarma = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipoAlarma.setNumero(rs.getInt("idTipoAlarma"));
                tipoAlarma.setDenominacion(rs.getString("denominacion"));
                tipoAlarma.setDescripcion(rs.getString("descripcion"));
                list.add(tipoAlarma);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoAlarma! (getUno)");
        }
        return tipoAlarma;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.zona.Flora;
import java.sql.Connection;
import java.util.ArrayList;
import ibeeproject.model.zona.TipoFlora;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Erro Gonzalo
 */
public class GestorTipoFlora implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;
    private TipoFlora tipoZona;

    //private Gestor
    public GestorTipoFlora() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        list = new ArrayList();

        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * FROM tipoFlora " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoFlora tipoFlora = new TipoFlora();
                tipoFlora.setIdTipoFlora(rs.getInt("idTipoFlora"));
                tipoFlora.setDenonimacion(rs.getString("denominacion"));
                tipoFlora.setDescripcion(rs.getString("descripcion"));
                list.add(tipoFlora);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoFlora !!!");
        }
        return list;
    }

    public Object getUltimo() {
        int indice = 0;
        TipoFlora tipoFlora = new TipoFlora();
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct max(idTipoFlora) FROM tipoflora " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            // Regenero el objeto TipodeFlora
            // PROXIMAMENTE!!
            tipoFlora.setIdTipoFlora(rs.getInt("idTipoFlora"));
            tipoFlora.setDenonimacion(rs.getString("denominacion"));
            tipoFlora.setDescripcion(rs.getString("descripcion"));
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoFlora !!!");
        }
        return tipoFlora;
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) throws Exception {
        TipoFlora tipoFlora = new TipoFlora();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct tf.idTipoFlora, tf.denominacion, tf.descripcion  FROM tipoflora as tf, flora f" +
                    " where tf.idTipoFlora = f.idTipoFlora" +
                    " and f.idZona = ? " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Zona
                // PROXIMAMEN
                tipoFlora.setIdTipoFlora(rs.getInt("idTipoFlora"));
                tipoFlora.setDenonimacion(rs.getString("denominacion"));
                tipoFlora.setDescripcion(rs.getString("descripcion"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoFlora !!!");
        }
        return tipoFlora;
    }

    public int insertUno(Object idObjeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deleteUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int updateUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

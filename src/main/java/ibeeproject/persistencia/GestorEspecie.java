/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import ibeeproject.model.familia.Especie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author burni.matias
 */
public class GestorEspecie implements Manejable{

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorEspecie()
        {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
        }
    public ArrayList getTodos() throws Exception {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM especie ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto familia
                Especie especie = new Especie();

                especie.setDenominacion(rs.getString("denominacion"));
                especie.setDescripcion(rs.getString("descripcion"));
                especie.setIdEspecie(rs.getInt("idEspecie"));

                list.add(especie);
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEspecie.getTodos !!!");
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

    public Object getUno(int idObjeto) throws Exception {
        Especie especie = new Especie();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM especie WHERE idEspecie = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                especie.setIdEspecie(rs.getInt("idEspecie"));
                especie.setDenominacion(rs.getString("denominacion"));
                especie.setDescripcion(rs.getString("descripcion"));
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEspecie !!!");
        }
        return especie;
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

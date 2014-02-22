/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.apiar.TipoMiel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author carranza.matias
 */
public class GestorTipoMiel {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorTipoMiel() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM TipoMiel ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoMiel tipoMiel = new TipoMiel();
                tipoMiel.setIdTipoMiel(rs.getInt("idTipoMiel"));
                tipoMiel.setDenominacion(rs.getString("denominacion"));
                tipoMiel.setDescripcion(rs.getString("descripcion"));
                list.add(tipoMiel);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoMiel !!! (getTodos)");
        }
        return list;
    }

    public Object getUltimo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getAsignado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) {
        TipoMiel tipoMiel = new TipoMiel();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM TipoMiel where idTipoMiel = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipoMiel.setIdTipoMiel(rs.getInt("idTipoMiel"));
                tipoMiel.setDenominacion(rs.getString("denominacion"));
                tipoMiel.setDescripcion(rs.getString("descripcion"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoMiel !!! (getUno)");
        }
        return tipoMiel;
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
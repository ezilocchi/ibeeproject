/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.familia.TipoAbeja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author burni.matias
 */
public class GestorTipoAbeja implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorTipoAbeja() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() throws Exception {
        ArrayList list1 = new ArrayList();
        try {
            list1.clear();
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM tipoabeja ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoAbeja tipoAbeja = new TipoAbeja();

                tipoAbeja.setIdTipoAbeja(rs.getInt("idTipoAbeja"));
                tipoAbeja.setDenominacion(rs.getString("denominacion"));
                tipoAbeja.setDescripcion(rs.getString("descripcion"));
                tipoAbeja.setCuidados(rs.getString("cuidados"));
                tipoAbeja.setTiempoRecomendado(rs.getString("tiempoRecomendado"));

                list1.add(tipoAbeja);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoAbeja.getTodos !!!");
        }
        return list1;
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
        TipoAbeja tipoAbeja = new TipoAbeja();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM tipoabeja WHERE idTipoAbeja = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipoAbeja.setDenominacion(rs.getString("denominacion"));
                tipoAbeja.setDescripcion(rs.getString("descripcion"));
                tipoAbeja.setCuidados(rs.getString("cuidados"));
                tipoAbeja.setTiempoRecomendado(rs.getString("tiempoRecomendado"));
                tipoAbeja.setIdTipoAbeja(idObjeto);
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoAbeja.getUno !!!");
        }
        return tipoAbeja;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.cajon.TipoCajon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author carranza.matias
 */
public class GestorTipoCajon {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * FROM tipoCajon ORDER BY 1 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto TipoCajon
                TipoCajon tipoCajon = new TipoCajon();
                tipoCajon.setIdTipoCajon(rs.getInt("idtipocajon"));
                tipoCajon.setDenominacion(rs.getString("denominacion"));
                tipoCajon.setTamanoPrimeraAlza(rs.getFloat("tamanoPrimeraAlza"));
                tipoCajon.setTamanoAlza(rs.getFloat("tamanoAlza"));
                tipoCajon.setObservaciones(rs.getString("observaciones"));

                list.add(tipoCajon);
            }
            rs.close();
            ps.close();
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoCajon !!! (getTodos)");
        } finally {
            connPool.closeConnection(conn);
        }
        return list;
    }

    public Object getUno(int tipo) {
        TipoCajon tipoCajon = new TipoCajon();
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * FROM tipoCajon WHERE idTipoCajon = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tipo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Regenero el objeto TipoCajon

                tipoCajon.setIdTipoCajon(rs.getInt("idtipocajon"));
                tipoCajon.setDenominacion(rs.getString("denominacion"));
                tipoCajon.setTamanoPrimeraAlza(rs.getFloat("tamanoPrimeraAlza"));
                tipoCajon.setTamanoAlza(rs.getFloat("tamanoAlza"));
                tipoCajon.setObservaciones(rs.getString("observaciones"));
            }
            rs.close();
            ps.close();

        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoCajon !!! (getUno)");
        } finally {
            connPool.closeConnection(conn);
        }
        return tipoCajon;
    }

    public GestorTipoCajon() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    /**
     * @return the connPool
     */
    public ConexionPoolBD getConnPool() {
        return connPool;
    }

    /**
     * @param connPool the connPool to set
     */
    public void setConnPool(ConexionPoolBD connPool) {
        this.connPool = connPool;
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * @return the list
     */
    public ArrayList getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(ArrayList list) {
        this.list = list;
    }
}

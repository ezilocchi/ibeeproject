/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import java.sql.Connection;
import java.util.ArrayList;
import ibeeproject.model.zona.Flora;
import ibeeproject.model.zona.TipoFlora;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Erro Gonzalo
 */
public class GestorFlora implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;
    private GestorTipoFlora gestTF = new GestorTipoFlora();
    //private Gestor

    public GestorFlora() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        list = new ArrayList();

        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * FROM flora " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Zona
                // PROXIMAMENTE!!
                Flora flora = new Flora();
                flora.setIdFlora(rs.getInt("idFlora"));
                flora.setTipoFlora((TipoFlora) this.gestTF.getUno(rs.getInt("idTipoFlora")));
                flora.setObservaciones(rs.getString("observaciones"));
                flora.setDenominacion(rs.getString("denominacion"));
                flora.setDescripcion(rs.getString("descripcion"));
                list.add(flora);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFlora !!!");
        }
        return list;
    }

    public Object getUltimo() {
        int indice = 0;
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct max(idFlora) FROM flora " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            // Regenero el objeto Zona
            // PROXIMAMENTE!!
            Flora flora = new Flora();
            flora.setIdFlora(rs.getInt("idFlora"));
            flora.setTipoFlora((TipoFlora) this.gestTF.getUno(rs.getInt("idTipoFlora")));
            flora.setObservaciones(rs.getString("observaciones"));
            flora.setDenominacion(rs.getString("denominacion"));
            flora.setDescripcion(rs.getString("descripcion"));
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFlora !!!");
        }
        return list;
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) throws Exception {

        Flora flora = new Flora();

        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM flora " +
                    " where idFlora=" + idObjeto +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Zona
                // PROXIMAMENTE!!

                flora.setIdFlora(rs.getInt("idFlora"));
                flora.setTipoFlora((TipoFlora) this.gestTF.getUno(rs.getInt("idTipoFlora")));
                flora.setObservaciones(rs.getString("observaciones"));
                flora.setDenominacion(rs.getString("denominacion"));
                flora.setDescripcion(rs.getString("descripcion"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFamilia !!!");
        }
        return flora;
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


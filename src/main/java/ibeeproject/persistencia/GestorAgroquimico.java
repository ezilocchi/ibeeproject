/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ibeeproject.model.zona.Agroquimico;
import java.sql.Connection;
import java.util.ArrayList;
import ibeeproject.model.zona.Flora;
import ibeeproject.model.zona.TipoAgroquimico;
import ibeeproject.model.zona.TipoFlora;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Erro Gonzalo
 */
public class GestorAgroquimico implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;
    private GestorTipoFlora gestTF = new GestorTipoFlora();
    private GestorTipoAgroquimico gestTA = new GestorTipoAgroquimico();
    //private Gestor

    public GestorAgroquimico() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        list = new ArrayList();

        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * FROM agroquimico " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Zona
                // PROXIMAMENTE!!
                Agroquimico agroquimico = new Agroquimico();
                agroquimico.setIdAgroquimico(rs.getInt("idAgroquimico"));
                agroquimico.setDenominacion(rs.getString("denominacion"));
                agroquimico.setDescripcion(rs.getString("descripcion"));
                agroquimico.setTipoAgroquimico((TipoAgroquimico) gestTA.getUno(rs.getInt("idTipoAgroquimico")));
                list.add(agroquimico);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAgroquimico!!! (getTodos)");
        }
        return list;
    }

    public Object getUltimo() {
        int indice = 0;
        Agroquimico agro = new Agroquimico();
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct max(idAgroquimico) agroquimico FROM agroquimico " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            // Regenero el objeto Zona
            // PROXIMAMENTE!!
            agro.setIdAgroquimico(rs.getInt("agroquimico"));
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAgroquimico!!! (getUltimo)");
        }
        return agro;
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) throws Exception {

        Agroquimico agroquimico = new Agroquimico();
        list.clear();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM agroquimico " +
                    " where idAgroquimico=" + idObjeto +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Zona
                // PROXIMAMENTE!!
                agroquimico.setIdAgroquimico(rs.getInt("idAgroquimico"));
                agroquimico.setTipoAgroquimico((TipoAgroquimico) this.gestTA.getUno(rs.getInt("idTipoAgroquimico")));
                agroquimico.setObservaciones(rs.getString("observaciones"));
                agroquimico.setDenominacion(rs.getString("denominacion"));
                agroquimico.setDescripcion(rs.getString("descripcion"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorAgroquimico!!! (getUno)");
        }
        return agroquimico;
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

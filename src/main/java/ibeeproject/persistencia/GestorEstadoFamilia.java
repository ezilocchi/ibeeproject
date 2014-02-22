/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import ibeeproject.model.familia.EstadoFamilia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author burni.matias
 */
public class GestorEstadoFamilia implements Manejable{

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorEstadoFamilia()
        {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
        }
    public ArrayList getTodos() throws Exception {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM estadofamilia ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto familia
                EstadoFamilia estadoFamilia = new EstadoFamilia();

                estadoFamilia.setDenominacion(rs.getString("denominacion"));
                estadoFamilia.setDescripcion(rs.getString("descripcion"));
                estadoFamilia.setIdEstadoFamilia(rs.getInt("idEstadoFamilia"));

                list.add(estadoFamilia);
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEstadoFamilia.getTodos() !!!");
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
        EstadoFamilia estadoFamilia = new EstadoFamilia();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM estadofamilia WHERE idEstadoFamilia = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                estadoFamilia.setIdEstadoFamilia(rs.getInt("idEstadoFamilia"));
                estadoFamilia.setDenominacion(rs.getString("denominacion"));
                estadoFamilia.setDescripcion(rs.getString("descripcion"));   
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoAbeja.getUno !!!");
        }
        return estadoFamilia;
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

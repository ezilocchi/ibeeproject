/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import ibeeproject.model.actividad.TipoDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorTipoDato implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorTipoDato() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() throws Exception {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM TipoDato ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoDato tipoDato = new TipoDato();
                tipoDato.setNumero(rs.getInt("idTipoDato"));
                tipoDato.setDescripcion(rs.getString("descripcion"));
                list.add(tipoDato);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoDato! (getTodos)");
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
       TipoDato tipoDato = new TipoDato();
        try{
            conn = connPool.getConnection();
            String sql = "SELECT * FROM TipoDato where idTipoDato = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
           while (rs.next())
           {
               tipoDato.setNumero(rs.getInt("idTipoDato"));
               tipoDato.setDescripcion(rs.getString("descripcion"));
           }
            ps.close();
            connPool.closeConnection(conn);
        }catch(Exception a)
            {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoDato! (getUno)");
            }
        return tipoDato;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import ibeeproject.model.enfermedad.Criticidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class GestorCriticidad implements Manejable{
    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorCriticidad()
    {
            connPool = ConexionPoolBD.getInstance();
            list = new ArrayList();
    }

    public ArrayList getTodos() {
    try{
        conn = connPool.getConnection();
        String sql = "SELECT * FROM Criticidad ORDER BY 1";
        PreparedStatement ps;
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
       while (rs.next())
       {
           Criticidad criticidad = new Criticidad();
           criticidad.setNumero(rs.getInt("idCriticidad"));
           criticidad.setDenominacion(rs.getString("denominacion"));
           criticidad.setDescripcion(rs.getString("descripcion"));
           list.add(criticidad);
       }
        connPool.closeConnection(conn);
        }catch(Exception a)
            {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCriticidad !!!");
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
        
        Criticidad criticidad = new Criticidad();
        try{
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Criticidad where idCriticidad = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
           while (rs.next())
           {
               criticidad.setNumero(rs.getInt("idCriticidad"));
               criticidad.setDenominacion(rs.getString("denominacion"));
               criticidad.setDescripcion(rs.getString("descripcion"));
           }
            connPool.closeConnection(conn);
        }catch(Exception a)
            {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCriticidad !!!");
            }
        return criticidad;
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

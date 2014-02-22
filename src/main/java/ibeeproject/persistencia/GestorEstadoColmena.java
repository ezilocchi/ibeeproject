/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import ibeeproject.model.apiar.EstadoColmena;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author carranza.matias
 */
public class GestorEstadoColmena implements Manejable{
private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorEstadoColmena()
    {
            connPool = ConexionPoolBD.getInstance();
            list = new ArrayList();
    }

    public ArrayList getTodos() {
    try{
        conn = connPool.getConnection();
        String sql = "SELECT * FROM EstadoColmena ORDER BY 1";
        PreparedStatement ps;
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
       while (rs.next())
       {
           EstadoColmena estado = new EstadoColmena();
           estado.setNumero(rs.getInt("idEstadoColmena"));
           estado.setDenominacion(rs.getString("denominacion"));
           estado.setDescripcion(rs.getString("descripcion"));
           list.add(estado);
       }
        rs.close();
        ps.close();
        connPool.closeConnection(conn);
        }catch(Exception a)
            {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEstadoColmena !!! (getTodos)");
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
        EstadoColmena estado = new EstadoColmena();
        try{
            conn = connPool.getConnection();
            String sql = "SELECT * FROM EstadoColmena where idEstadoColmena = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
           while (rs.next())
           {
               estado.setNumero(rs.getInt("idEstadoColmena"));
               estado.setDenominacion(rs.getString("denominacion"));
               estado.setDescripcion(rs.getString("descripcion"));
           }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        }catch(Exception a)
            {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEstadoColmena !!! (getUno)");
            }
        return estado;
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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.apiar.Layout;
import java.sql.Connection;
import java.util.ArrayList;
import ibeeproject.model.apiar.Posicion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author carranza.matias
 */
public class GestorPosicion implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorPosicion() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        list = new ArrayList();
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * FROM posicion " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero las posiciones de un layout determinado
                Posicion posicion = new Posicion();
                posicion.setIdPosicion(rs.getInt("idPosicion"));
                posicion.setPosHorizontal(rs.getString("posHorizontal"));
                posicion.setPosVertical(rs.getString("posVertical"));
                list.add(posicion);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorPosicion !!!");
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

    public ArrayList getUnoLayout(int idObjeto) {
        list = new ArrayList();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM posicion " +
                    " where idLayout=" + idObjeto +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero las posiciones de un layout determinado
                Posicion posicion = new Posicion();
                posicion.setIdPosicion(rs.getInt("idPosicion"));
                posicion.setPosHorizontal(rs.getString("posHorizontal"));
                posicion.setPosVertical(rs.getString("posVertical"));
                list.add(posicion);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorPosicion !!!");
        }
        return list;
    }

    public int insertUno(Object object) {
        Posicion posicion = (Posicion) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " insert into posicion (idPosicion, posHorizontal, posVertical,idLayout) " +
                    " values (?,?,?,?)";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ps.setInt(1, posicion.getIdPosicion());
            ps.setString(2, posicion.getPosHorizontal());
            ps.setString(3, posicion.getPosVertical());
            ps.setInt(4, posicion.getLayout().getIdLayout());
            //FAlta dar de alta al usuario q´carga este apiar....podría modificarse la base de datos y poner el campo usuairoAlta mirando a empleado
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEnfermedad !!! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) {
        Layout layout = (Layout) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " delete from posicion " +
                    " where idLayout=" + layout.getIdLayout();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            //FAlta dar de alta al usuario q´carga este apiar....podría modificarse la base de datos y poner el campo usuairoAlta mirando a empleado
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorPosicino!!! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) {
        Posicion posicion = (Posicion) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update posicion set " +
                    " posHorizontal = ? , " +
                    " posVertical = ?  " +
                    " WHERE idLayout = ? " +
                    " and idPosicion = ?";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ps.setString(1, posicion.getPosHorizontal());
            ps.setString(2, posicion.getPosVertical());
            ps.setInt(3, posicion.getLayout().getIdLayout());
            ps.setInt(4, posicion.getIdPosicion());
            //FAlta dar de alta al usuario q´carga este apiar....podría modificarse la base de datos y poner el campo usuairoAlta mirando a empleado

            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEnfermedad !!! (insertUno)");
        }
        return resultado;
    }

    public Object getUno(int idColmena) throws Exception {
        Posicion posicion = new Posicion();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct p.idPosicion, p.posHorizontal, p.posVertical FROM posicion p, colmena c, asignacioncolmena a, apiar ap" +
                    " where c.numeroColmena=a.numeroColmena" +
                    " and c.numeroColmena=" + idColmena +
                    " and a.idApiar=ap.idApiar" +
                    " and ap.idLayout=p.idLayout " +
                    " Group by c.numeroColmena";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero las posiciones de un layout determinado
                posicion.setIdPosicion(rs.getInt("idPosicion"));
                posicion.setPosHorizontal(rs.getString("posHorizontal"));
                posicion.setPosVertical(rs.getString("posVertical"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorPosicion !!!");
        }
        return posicion;
    }
}

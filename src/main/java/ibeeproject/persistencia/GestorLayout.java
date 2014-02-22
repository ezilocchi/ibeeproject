/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.apiar.EstadoLayout;
import ibeeproject.model.apiar.Layout;
import ibeeproject.model.apiar.Posicion;
import ibeeproject.model.soporte.UtilFecha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author carranza.matias
 */
public class GestorLayout implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorLayout() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {

        list = new ArrayList();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM layout group by idLayout ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Layout
                Layout layout = new Layout();
                layout.setIdLayout(rs.getInt("idLayout"));
                layout.setDenominacion(rs.getString("denominacion"));
                layout.setDisenio(rs.getString("disenio"));
                layout.setFechaCreacion(rs.getDate("fechaCreacion"));
                layout.setObservaciones(rs.getString("observaciones"));
                layout.setVentajas(rs.getString("ventaja"));
                layout.setAncho(rs.getInt("ancho"));
                layout.setLargo(rs.getInt("largo"));
                if (this.getAsignado(layout) != 0) {
                    layout.setAsignado(new EstadoLayout(1)); //true
                } else {
                    layout.setAsignado(new EstadoLayout(2));
                }
                list.add(layout);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFamilia !!!");
        }
        return list;
    }

    public Object getUltimo() {
        Layout layout = new Layout();
        list = new ArrayList();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM layout where idLayout = ( select max(idLayout) from layout ) group by idLayout ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Layout

                layout.setIdLayout(rs.getInt("idLayout"));
                layout.setDenominacion(rs.getString("denominacion"));
                layout.setDisenio(rs.getString("disenio"));
                layout.setFechaCreacion(rs.getDate("fechaCreacion"));
                layout.setObservaciones(rs.getString("observaciones"));
                layout.setVentajas(rs.getString("ventaja"));
                layout.setAncho(rs.getInt("ancho"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFamilia !!!");
        }
        return layout;
    }

    public ArrayList getAsignado() throws Exception {

        list = new ArrayList();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM layout where idLayout in ( select idLayout from apiar ) group by idLayout ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Layout
                Layout layout = new Layout();
                layout.setIdLayout(rs.getInt("idLayout"));
                layout.setDenominacion(rs.getString("denominacion"));
                layout.setDisenio(rs.getString("disenio"));
                layout.setFechaCreacion(rs.getDate("fechaCreacion"));
                layout.setObservaciones(rs.getString("observaciones"));
                layout.setVentajas(rs.getString("ventaja"));
                layout.setAncho(rs.getInt("ancho"));
                list.add(layout);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFamilia !!!");
        }
        return list;
    }

    public int getAsignado(Object lay) {
        Layout layout = (Layout) lay;
        int cantidad = 0;

        try {
            conn = connPool.getConnection();
            String sql = "SELECT count(*) 'cantidad' FROM apiar where idLayout=" + layout.getIdLayout();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            cantidad = rs.getInt("cantidad");

            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorLayout !!!");
        }
        return cantidad;
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) throws Exception {
        Layout layout = new Layout();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM layout " +
                    " where idLayout=" + idObjeto +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto familia
                layout.setIdLayout(rs.getInt("idLayout"));
                layout.setDenominacion(rs.getString("denominacion"));
                layout.setDisenio(rs.getString("disenio"));
                layout.setFechaCreacion(rs.getDate("fechaCreacion"));
                layout.setObservaciones(rs.getString("observaciones"));
                layout.setVentajas(rs.getString("ventaja"));
                layout.setAncho(rs.getInt("ancho"));
                layout.setLargo(rs.getInt("largo"));
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorLayout !!!");
        }
        return layout;
    }

    public int insertUno(Object object) {
        Layout layout = (Layout) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " insert into layout(idLayout,denominacion,disenio,ventaja,observaciones, fechaCreacion, ancho, largo)" +
                    "   values(?,?,?,?,?,?,?,?)  ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, layout.getIdLayout());
            ps.setString(2, layout.getDenominacion());
            ps.setString(3, layout.getDisenio());
            ps.setString(4, layout.getVentajas());
            ps.setString(5, layout.getObservaciones());
            ps.setDate(6, UtilFecha.convertiFecha(layout.getFechaCreacion()));
            ps.setString(7, String.valueOf(layout.getAncho()));
            ps.setString(8, String.valueOf(layout.getLargo()));

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
            String sql = " delete from layout where idLayout=" + layout.getIdLayout();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorLayout !!! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) {
        Layout layout = (Layout) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update layout set " +
                    " denominacion = ? , " +
                    " disenio = ? , " +
                    " ventaja = ? , " +
                    " observaciones = ? , " +
                    " fechaCreacion = ? ," +
                    " ancho = ? ," +
                    " largo = ? " +
                    " where idLayout =" + layout.getIdLayout();

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ps.setString(1, layout.getDenominacion());
            ps.setString(2, layout.getDisenio());
            ps.setString(3, layout.getVentajas());
            ps.setString(4, layout.getObservaciones());
            ps.setDate(5, UtilFecha.convertiFecha(layout.getFechaCreacion()));
            ps.setString(6, String.valueOf(layout.getAncho()));
            ps.setString(7, String.valueOf(layout.getLargo()));

            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEnfermedad !!! (insertUno)");
        }
        return resultado;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.persona.Cargo;
import ibeeproject.model.persona.Recurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorCargo implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorCargo() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Cargo";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setDenominacion(rs.getString("denominacion"));
                cargo.setDescripcion(rs.getString("descripcion"));
                // busco los recursos del Cargo y los cargo en un arrayList
                GestorRecurso gestorRecurso = new GestorRecurso();
                ArrayList recursos = gestorRecurso.getTodosHabilitados(cargo.getIdCargo());
                cargo.setRecursos(recursos);

                list.add(cargo);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCargo! (getTodos)");
        }
        return list;
    }

    public Object getUltimo() throws Exception {
        Cargo cargo = new Cargo();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM cargo WHERE idCargo = (select MAX(idCargo) from cargo)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setDenominacion(rs.getString("denominacion"));
                cargo.setDescripcion(rs.getString("descripcion"));
                // busco los recursos del Cargo y los cargo en un arrayList
                GestorRecurso gestorRecurso = new GestorRecurso();
                ArrayList recursos = gestorRecurso.getTodosHabilitados(cargo.getIdCargo());
                cargo.setRecursos(recursos);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCargo.getUltimo() !!!");
        }
        return cargo;
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idCargo) {
        Cargo cargo = new Cargo();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Cargo where idCargo = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCargo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setDenominacion(rs.getString("denominacion"));
                cargo.setDescripcion(rs.getString("descripcion"));

                GestorRecurso gestorRecurso = new GestorRecurso();
                ArrayList recursos = gestorRecurso.getTodosHabilitados(idCargo);
                cargo.setRecursos(recursos);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCargo! (getUno)");
        }
        return cargo;
    }

    public int insertUno(Object object) throws Exception {

        Cargo cargo = (Cargo) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = "insert into Cargo(idCargo, denominacion, descripcion) " +
                    " values (?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cargo.getIdCargo());
            ps.setString(2, cargo.getDenominacion());
            ps.setString(3, cargo.getDescripcion());

            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCargo! (insertUno)");
        }

        for (int i = 0; i < cargo.getRecursos().size(); i++) {
            GestorRecursoXCargo gestorRecursoXCargo = new GestorRecursoXCargo();
            Recurso recurso = (Recurso) cargo.getRecursos().get(i);
            gestorRecursoXCargo.insertUno(cargo.getIdCargo(), recurso.getIdRecursos());
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        Cargo cargo = (Cargo) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = "select COUNT(legajo) as cantidad from empleado where idCargo=? and fechaBaja is null";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cargo.getIdCargo());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("cantidad") != 0) {
                    return -1;
                }
            }
            //Luego debo eliminar todos sus recursos asociados
            //en la tabla recursosXcargo
            GestorRecursoXCargo gestorRecursoXCargo = new GestorRecursoXCargo();
            gestorRecursoXCargo.deleteUno(cargo);

            //Por ultimo elimino el Cargo si cumple con lo previo
            sql = "delete from cargo where idCargo = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cargo.getIdCargo());
            resultado = ps.executeUpdate();
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCargo.deleteUno ");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        Cargo cargo = (Cargo) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update cargo set " +
                    " denominacion = ? , " +
                    " descripcion = ? " +
                    " WHERE idCargo = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setString(1, cargo.getDenominacion());
            ps.setString(2, cargo.getDescripcion());
            ps.setInt(3, cargo.getIdCargo());

            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);

            // Aqui actulizo la lista de los recursos que tenia asignado el Cargo
            GestorRecursoXCargo gestorRecursoXCargo = new GestorRecursoXCargo();
            gestorRecursoXCargo.updateUno(cargo);


        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCargo.updateUno !!! (updateUno)");
        }
        return resultado;
    }

    public Object getUno(String denom) {
        Cargo cargo = null;
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Cargo where denominacion = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, denom);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setDenominacion(rs.getString("denominacion"));
                cargo.setDescripcion(rs.getString("descripcion"));

            //No seteo los recursos porque NO los necesito
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCargo! (getUno)");
        }
        return cargo;
    }
}

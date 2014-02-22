/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.persona.Cargo;
import ibeeproject.model.persona.Recurso;
import ibeeproject.model.soporte.UtilRecursoXCargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author carranza.matias
 */
public class GestorRecursoXCargo implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorRecursoXCargo() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
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

    /*
     * me da un Array con todos los recursos(MENUES) que puede acceder el cargo
     */
    public Object getTodosHabilitados(int idCargo) {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM recursoxCargo WHERE idCargo = ? ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCargo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UtilRecursoXCargo utilRecursoXCargo = new UtilRecursoXCargo();

                GestorRecurso gestorRecurso = new GestorRecurso();
                utilRecursoXCargo.setRecurso((Recurso) gestorRecurso.getUno(rs.getInt("idRecurso")));
                utilRecursoXCargo.setHabilitado(rs.getBoolean("true"));

                list.add(utilRecursoXCargo);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorRecurso.getRecursos !!! ");
        }
        return list;
    }

    public Object getTodosHabilitadosHash(int idCargo) {
        Hashtable recursoXcargo = new Hashtable();
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;
            String sql = "SELECT * FROM recursoXcargo where idCargo = ? order by 2 ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCargo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GestorRecurso gestorRecurso = new GestorRecurso();
                Recurso recurso = new Recurso();
                recurso = (Recurso) gestorRecurso.getUno(rs.getInt("idRecurso"));
                recursoXcargo.put(recurso.getNombre(), true);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorMenu!!! (getUno)");
        }
        return recursoXcargo;
    }

    public int insertUno(int idCargo, int idRecurso) throws Exception {
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = "insert into recursoxcargo(idCargo, idRecurso) values (?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCargo);
            ps.setInt(2, idRecurso);

            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestoRecursoXCargo! (insertUno)");
        }
        return resultado;
    }

    /*
     * Borra TODOS los recursos para ese Cargo
     */
    public int deleteUno(Object object) throws Exception {
        Cargo cargo = (Cargo) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " delete from recursoxcargo where idCargo = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cargo.getIdCargo());

            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);

        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorRecursoXCargo.DeleteUno");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        Cargo cargo = (Cargo) object;
        int resultado = 0;

        //Primero debo borrar todos y luego volver a insertarlos
        // El UPDATE es un falso UPDATE, lo hago a mano ya que es una tabla intermedia
        this.deleteUno(cargo);

        try {
            conn = connPool.getConnection();

            for (int i = 0; i < cargo.getRecursos().size(); i++) {
                Recurso aux = (Recurso) cargo.getRecursos().get(i);
                this.insertUno(cargo.getIdCargo(), aux.getIdRecursos());
            }
            connPool.closeConnection(conn);

        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorCargo.updateUno !!! (updateUno)");
        }
        return resultado;
    }

    public Object getUno(int idObjeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int insertUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

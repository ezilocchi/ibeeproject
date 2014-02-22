package ibeeproject.persistencia;

import ibeeproject.model.persona.Recurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author burni.matias
 */
public class GestorRecurso implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorRecurso() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() throws Exception {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Recurso";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recurso recurso = new Recurso();
                recurso.setIdRecursos(rs.getInt("idRecurso"));
                recurso.setNombre(rs.getString("nombre"));

                list.add(recurso);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorRecurso! (getTodos)");
        } 
        return list;
    }

    public ArrayList getTodosHabilitados(int idCargo) throws Exception {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM RecursoxCargo WHERE idCargo = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCargo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recurso recurso = new Recurso();
                recurso.setIdRecursos(rs.getInt("idRecurso"));

                Recurso aux=(Recurso) this.getUno(rs.getInt("idRecurso"));
                recurso.setNombre(aux.getNombre());

                list.add(recurso);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorRecurso! (getTodos)");
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

    public Object getUno(int idRecurso) throws Exception {
        Recurso recurso = new Recurso();
        try {
            Connection c = connPool.getConnection();
            String sql = "SELECT * FROM recurso WHERE idRecurso = ?";
            PreparedStatement ps;
            ps = c.prepareStatement(sql);
            ps.setInt(1, idRecurso);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                recurso.setIdRecursos(rs.getInt("idRecurso"));
                recurso.setNombre(rs.getString("nombre"));
            }
            rs.close();
            ps.close();
            connPool.closeConnection(c);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorRecurso.getUno() !!!");
        }
        return recurso;
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

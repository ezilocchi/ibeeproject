/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.zona.Alarma;
import ibeeproject.model.persona.Empleado;
import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.model.zona.Mensaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorMensaje implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorMensaje() {
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

    public Object getUno(int idObjeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
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

    public ArrayList getTopTenParaEmpleado(Empleado empleado) {
        try {
            Mensaje mensaje;
            conn = connPool.getConnection();
            String sql = "SELECT m.* " +
                        "FROM mensajes m, alarma a " +
                        "WHERE m.idAlarma = a.idAlarma AND a.idCargo = ? " +
                        "ORDER by idMensaje desc LIMIT 10 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, empleado.getCargo().getIdCargo());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mensaje = new Mensaje();
                // Regenero el objeto MENSAJES @TODO
                mensaje.setIdMensaje(rs.getInt("idMensaje"));
                mensaje.setTexto(rs.getString("texto"));
                mensaje.setFecha(UtilFecha.convertiFecha(rs.getDate("fecha")));

                // Regenero la Alarma
                GestorAlarma gestorAlarma = new GestorAlarma();
                Alarma alarma = (Alarma) gestorAlarma.getUno(rs.getInt("idAlarma"));

                mensaje.setAlarma(alarma);

                list.add(mensaje);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorMensajes! (getTopFive)");
        }
        return list;
    }
}

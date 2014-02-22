/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.model.zona.Clima;
import ibeeproject.model.zona.Viento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorClima implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorClima() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos()  {
         list = new ArrayList();

        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * FROM clima " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Clima WORK IN PROGESS
                Clima clima = new Clima();
                clima.setIdClima(rs.getInt("idClima"));
                clima.setIdZona(rs.getInt("idZona"));
                clima.setFecha(UtilFecha.convertiFecha(rs.getDate("fechahora")));
                clima.setTemperatura(rs.getDouble("temperatura"));
                clima.setHumedad(rs.getDouble("humedad"));
                clima.setIndiceUV(rs.getDouble("humedad"));
                clima.setPresion(rs.getDouble("presion"));
                Viento viento = new Viento();
                viento.setVelocidad(rs.getDouble("velocidadDelViento"));
                viento.setDireccion(rs.getString("direccionDelViento"));
                clima.setViento(viento);
                list.add(clima);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorClima !!!");
        }
        return list;
    }

    public ArrayList getAsignado(int idZona) {
               list = new ArrayList();

        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * FROM clima " +
                    " where idZona = ? " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idZona);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Clima WORK IN PROGESS
                Clima clima = new Clima();
                clima.setIdClima(rs.getInt("idClima"));
                clima.setIdZona(rs.getInt("idZona"));
                clima.setFecha(UtilFecha.convertiFecha(rs.getDate("fechahora")));
                clima.setTemperatura(rs.getDouble("temperatura"));
                clima.setHumedad(rs.getDouble("humedad"));
                clima.setIndiceUV(rs.getDouble("humedad"));
                clima.setPresion(rs.getDouble("presion"));
                Viento viento = new Viento();
                viento.setVelocidad(rs.getDouble("velocidadDelViento"));
                viento.setDireccion(rs.getString("direccionDelViento"));
                clima.setViento(viento);
                list.add(clima);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorClima !!!");
        }
        return list;
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

    public ArrayList getTopFive() {
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct l.nombre, c.fechahora, c.temperatura, " +
                          "   c.humedad, c.indiceUV, c.presion " +
                          " FROM zona z, localidad l, clima c " +
                          " WHERE c.idzona = z.idzona " +
                          " and z.idlocalidad = l.idlocalidad " +
                          " ORDER by c.fechahora desc  LIMIT 10 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto enfermedad
                Clima clima = new Clima();
                clima.setLocalidad(rs.getString("nombre"));
                clima.setFecha(UtilFecha.convertiFecha(rs.getDate("fechahora")));
                clima.setTemperatura(rs.getDouble("temperatura"));
                clima.setHumedad(rs.getDouble("humedad"));
                clima.setIndiceUV(rs.getDouble("humedad"));
                clima.setPresion(rs.getDouble("presion"));
                list.add(clima);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorClima! (getTopFive)");
        }
        return list;
    }

    public Object getUltimo() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

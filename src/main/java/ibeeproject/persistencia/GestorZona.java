/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.apiar.Ubicacion;
import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.model.zona.EstadoZona;
import ibeeproject.model.zona.TipoAgroquimico;
import ibeeproject.model.zona.TipoFlora;
import java.sql.Connection;
import java.util.ArrayList;
import ibeeproject.model.zona.Zona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Administrador
 */
public class GestorZona implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    //private Gestor

    public GestorZona() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        list = new ArrayList();

        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * FROM zona " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Zona
                Zona zona = new Zona();
                zona.setIdZona(rs.getInt("idZona"));
                zona.setZona(rs.getString("zona"));
                zona.setObservaciones(rs.getString("observaciones"));
                zona.setFechaAlta(rs.getDate("fechaAlta"));
                zona.setFechaBaja(rs.getDate("fechaBaja"));

                GestorTipoFlora gestTF = new GestorTipoFlora();
                zona.setTipoFlora((TipoFlora) gestTF.getUno(rs.getInt("idZona")));

                GestorTipoAgroquimico gestTA = new GestorTipoAgroquimico();
                zona.setTipoAgroquimico((TipoAgroquimico) gestTA.getUno(rs.getInt("idZona")));
                zona.setLatitud(rs.getDouble("latitud"));
                zona.setLongitud(rs.getDouble("longitud"));

                GestorEstadoZona gestEZ = new GestorEstadoZona();
                zona.setEstado((EstadoZona) gestEZ.getUno(rs.getInt("estado")));

                GestorUbicacion gestU = new GestorUbicacion();
                zona.setUbicacion(gestU.getAsignado(rs.getInt("idZona")));
                list.add(zona);
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorZona !!!");
        }
        return list;
    }

    public Object getUltimo() {
        int indice = 0;
        Zona zona = new Zona();
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct max(idZona) idZona FROM zona " +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            // Regenero el objeto Zona
            // PROXIMAMENTE!!

            zona.setIdZona(rs.getInt("idZona"));
//                zona.setZona(rs.getString("zona"));
//                zona.setObservaciones(rs.getString("observaciones"));
//                zona.setFechaAlta(rs.getDate("fechaAlta"));
//                zona.setFechaBaja(rs.getDate("fechaBaja"));
//                zona.setFlora((Flora)gestF.getUno(rs.getInt("idFlora")));
//                //zona.setClima((Clima)gestC.getUno(rs.getInt("idClima")));
//                zona.setAgroquimico((Agroquimico)gestA.getUno(rs.getInt("idAgroquimico")));
//                zona.setLatitud(indice);
//                zona.setLongitud(indice);


            //zona.setLatitud(latitud);
            //zona.setLongitud(longitud);
            //zona.setEstado(sql);
            //zona.setClima(clima);
            //zona.getAgroquimico();
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorZona!!!");
        }
        return zona;
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) {
        int idzona = Integer.parseInt(String.valueOf(idObjeto));
        Zona zona = new Zona();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM zona " +
                    " where idZona=" + idObjeto +
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Zona
                // PROXIMAMENTE!!
                zona.setIdZona(rs.getInt("idZona"));
                zona.setZona(rs.getString("zona"));
                zona.setObservaciones(rs.getString("observaciones"));
                zona.setFechaAlta(rs.getDate("fechaAlta"));
                zona.setFechaBaja(rs.getDate("fechaBaja"));
                
                GestorTipoFlora gestTF = new GestorTipoFlora();
                zona.setTipoFlora((TipoFlora) gestTF.getUno(rs.getInt("idZona")));

                GestorTipoAgroquimico gestTA = new GestorTipoAgroquimico();
                zona.setTipoAgroquimico((TipoAgroquimico) gestTA.getUno(rs.getInt("idZona")));
                zona.setLatitud(rs.getDouble("latitud"));
                zona.setLongitud(rs.getDouble("longitud"));

                GestorEstadoZona gestEZ = new GestorEstadoZona();
                zona.setEstado((EstadoZona) gestEZ.getUno(rs.getInt("estado")));

                GestorUbicacion gestU = new GestorUbicacion();
                zona.setUbicacion(gestU.getAsignado(rs.getInt("idZona")));
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorZona !!!");
        }
        return zona;
    }

    public int insertUno(Object object, int sel) {
        Zona zona = (Zona) object;
        int id = 0;
        int resultado = 0;
        try {
            conn = connPool.getConnection();

            String sql = "insert into Zona(idZona, zona, " +
                    "fechaAlta, fechaBaja, observaciones, latitud, longitud, estado) " +
                    " values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, zona.getIdZona());
            ps.setString(2, zona.getZona());
            ps.setDate(3, UtilFecha.convertiFecha(zona.getFechaAlta()));
            ps.setDate(4, UtilFecha.convertiFecha(zona.getFechaBaja()));
            ps.setString(5, zona.getObservaciones());
            ps.setDouble(6, zona.getLatitud());
            ps.setDouble(7, zona.getLongitud());
            ps.setInt(8, zona.getEstado().getNumero());
            resultado = ps.executeUpdate();

            ps.close();

            ///////////////////Cargo Agroquimico///////////////
            if (sel == 2) {
                sql = "insert into agroquimico(idZona, " +
                        "idTipoAgroquimico) " +
                        " values (?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, zona.getIdZona());
                ps.setInt(2, zona.getTipoAgroquimico().getIdTipoAgroquimico());
                resultado = ps.executeUpdate();
                ps.close();
            } else {
                ///////////////////Cargo Flora////////////////////
                sql = "insert into flora(idZona, " +
                        "idTipoFlora) " +
                        " values (?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, zona.getIdZona());
                ps.setInt(2, zona.getTipoFlora().getIdTipoFlora());
                resultado = ps.executeUpdate();
                ps.close();
            }
            //////////////////////Ubicacion y de Ubicacion de Zona////////////////////
            GestorUbicacion gestU = new GestorUbicacion();
            Ubicacion ultimo;
            for (int i = 0; i < zona.getUbicacion().size(); i++) {
                gestU.insertUno(zona.getUbicacion().get(i));
                ultimo = (Ubicacion) gestU.getUltimo();
                sql = "insert into ubicaciondezona(idZona, " +
                        "idUbicacion) " +
                        " values (?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, zona.getIdZona());
                ps.setInt(2, ultimo.getIdUbicacion());
                resultado = ps.executeUpdate();
                ps.close();
            }

            /////////////////////// Fin ////////////////////////////
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorZona! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) {
        Zona zona = (Zona) object;
        int id = 0;
        int resultado = 0;
        ArrayList coordenadas = new ArrayList();

        try {
            conn = connPool.getConnection();

            //////////////////Guardo la lista de coordenadas////////////////

            String sql = " select distinct idUbicacion from ubicaciondezona " +
                    " where idZona = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, zona.getIdZona());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                coordenadas.add(rs.getInt("idubicacion"));
            }
            ps.close();

            //////////////////Borro las coordenadas asignadas para esta zona/
            sql = " delete from ubicaciondezona " +
                    " where idZona = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, zona.getIdZona());
            resultado = ps.executeUpdate();
            ps.close();

            //////////////////Borro las coordenadas de ubicacion//////////////
            for (int i = 0; i < coordenadas.size(); i++) {
                sql = " delete from ubicacion " +
                        " where idUbicacion = ? ";
                ps = conn.prepareStatement(sql);
                int idU = Integer.parseInt(String.valueOf(coordenadas.get(i)));
                ps.setInt(1, idU);
                resultado = ps.executeUpdate();
                ps.close();
            }

            ///////////////////Borro Agroquimico///////////////
            if (zona.getTipoAgroquimico().getIdTipoAgroquimico() != -1) {
                sql = "delete from agroquimico" +
                        " where idZona = ? ";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, zona.getIdZona());
                resultado = ps.executeUpdate();
                ps.close();
            } else {
                ///////////////////Borro Flora////////////////////
                sql = "delete from flora" +
                        " where idZona = ? ";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, zona.getIdZona());
                resultado = ps.executeUpdate();
                ps.close();
            }
            //////////////////////Borro Zona////////////////////
            sql = "delete from Zona  where idZona = ? " ;

            ps = conn.prepareStatement(sql);
            ps.setInt(1, zona.getIdZona());
            resultado = ps.executeUpdate();
            ps.close();

            /////////////////////// Fin ////////////////////////////

            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorZona! (BorraUno)");
            return resultado;
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        Zona zona = (Zona) object;
        int id = 0;
        int resultado = 0;
        ArrayList coordenadas = new ArrayList();

        try {
            conn = connPool.getConnection();

            //////////////////Borro las coordenadas////////////////

            String sql = " select distinct idUbicacion from ubicaciondezona " +
                    " where idZona = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, zona.getIdZona());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                coordenadas.add(rs.getInt("idubicacion"));
            }
            ps.close();

            //////////////////Borro las coordenadas asignadas para esta zona/
            sql = " delete from ubicaciondezona " +
                    " where idZona = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, zona.getIdZona());
            resultado = ps.executeUpdate();
            ps.close();

            //////////////////Borro las coordenadas de ubicacion//////////////
            for (int i = 0; i < coordenadas.size(); i++) {
                sql = " delete from ubicacion " +
                        " where idUbicacion = ? ";
                ps = conn.prepareStatement(sql);
                int idU = Integer.parseInt(String.valueOf(coordenadas.get(i)));
                ps.setInt(1, idU);
                resultado = ps.executeUpdate();
                ps.close();
            }
            ///////////////////Borro Agroquimico///////////////

                sql = "delete from agroquimico" +
                        " where idZona = ? ";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, zona.getIdZona());
                resultado = ps.executeUpdate();
                ps.close();

                ///////////////////Borro Flora////////////////////
                sql = "delete from flora" +
                        " where idZona = ? ";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, zona.getIdZona());
                resultado = ps.executeUpdate();
                ps.close();

            //////////////////////Actualizo Zona////////////////////
            sql = "update Zona set " +
                    " zona = ? ," +
                    " fechaAlta = ? ," +
                    " fechaBaja = ? ," +
                    " observaciones = ? ," +
                    " latitud = ? ," +
                    " longitud = ? ," +
                    " estado = ? " +
                    " where idZona = ?" ;
            ps = conn.prepareStatement(sql);

            ps.setString(1, zona.getZona());
            ps.setDate(2, UtilFecha.convertiFecha(zona.getFechaAlta()));
            ps.setDate(3, UtilFecha.convertiFecha(zona.getFechaBaja()));
            ps.setString(4, zona.getObservaciones());
            ps.setDouble(5, zona.getLatitud());
            ps.setDouble(6, zona.getLongitud());
            ps.setInt(7, zona.getEstado().getNumero());
            ps.setInt(8, zona.getIdZona());
            resultado = ps.executeUpdate();
            ps.close();

            ///////////////////Cargo Agroquimico///////////////
            if (zona.getTipoAgroquimico().getIdTipoAgroquimico() != -1) {
                sql = "insert into agroquimico (idZona, idTipoAgroquimico)" +
                        " values (? , ?) " ;
                ps = conn.prepareStatement(sql);
                ps.setInt(1, zona.getIdZona());
                ps.setInt(2, zona.getTipoAgroquimico().getIdTipoAgroquimico());
                resultado = ps.executeUpdate();
                ps.close();
            } else if(zona.getTipoFlora().getIdTipoFlora() != -1 ){
                ///////////////////Cargo Flora////////////////////
                sql = "insert into flora (idZona, idTipoFlora)" +
                        " values ( ? , ? )" ;
                ps = conn.prepareStatement(sql);
                ps.setInt(1, zona.getIdZona());
                ps.setInt(2, zona.getTipoFlora().getIdTipoFlora());
                resultado = ps.executeUpdate();
                ps.close();
            }
            //////////////////////Ubicacion y de Ubicacion de Zona////////////////////
            GestorUbicacion gestU = new GestorUbicacion();
            Ubicacion ultimo;
            for (int i = 0; i < zona.getUbicacion().size(); i++) {
                gestU.insertUno(zona.getUbicacion().get(i));
                ultimo = (Ubicacion) gestU.getUltimo();
                sql = "insert into ubicaciondezona(idZona, " +
                        "idUbicacion) " +
                        " values (?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, zona.getIdZona());
                ps.setInt(2, ultimo.getIdUbicacion());
                resultado = ps.executeUpdate();
                ps.close();
            }

            /////////////////////// Fin ////////////////////////////
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorZona! (BorraUno)");
            return resultado;
        }
        return resultado;
    }

    public int insertUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

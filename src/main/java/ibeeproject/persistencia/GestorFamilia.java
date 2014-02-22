/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.familia.Especie;
import ibeeproject.model.familia.EstadoFamilia;
import ibeeproject.model.familia.Familia;
import ibeeproject.model.soporte.UtilFecha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class GestorFamilia implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorFamilia() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * FROM familia " +
                    " WHERE fechaBaja is null " +
                    " ORDER BY 1 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto familia
                Familia familia = new Familia();

                familia.setCaracteristicas(rs.getString("caracteristicas"));
                familia.setDenominacion(rs.getString("denominacion"));
                familia.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                familia.setNroGrupo(rs.getInt("numeroFamilia"));
                familia.setObservaciones(rs.getString("observaciones"));

                // busco los miembros de la familia y los cargo en un arrayList
                GestorMiembroFamilia gestorMiembroFamilia = new GestorMiembroFamilia();
                ArrayList miembrosFamilia = gestorMiembroFamilia.getMiembrosFamilia(familia.getNroFamilia());
                familia.setMiembrosFamilia(miembrosFamilia);

                GestorEspecie gestorEspecie = new GestorEspecie();
                Especie especie = (Especie) gestorEspecie.getUno(rs.getInt("idEspecie"));
                familia.setEspecie(especie);

                GestorEstadoFamilia gestorEstadoFamilia = new GestorEstadoFamilia();
                EstadoFamilia estadoFamilia = (EstadoFamilia) gestorEstadoFamilia.getUno(rs.getInt("idEstadoFamilia"));
                familia.setEstado(estadoFamilia);

//                //No lo cargo porque no se que es!
//                //@TODO: hacer el metodo GestorHistorialEstadoGrupo.getTodos()
//                GestorHistorialEstadoGrupo gestorHistorialEstadoGrupo = new GestorHistorialEstadoGrupo();
//                ArrayList historialEstadoGrupo = (ArrayList) gestorHistorialEstadoGrupo.getTodos();
//                familia.setHistorialEstadoGrupo(historialEstadoGrupo);

                list.add(familia);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFamilia !!!");
        }
        return list;
    }

    public Object getUltimo() throws Exception {
        Familia familia = new Familia();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM familia WHERE numeroFamilia = (select MAX(numeroFamilia) from familia)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                familia.setCaracteristicas(rs.getString("caracteristicas"));
                familia.setDenominacion(rs.getString("denominacion"));
                familia.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                familia.setNroGrupo(rs.getInt("numeroFamilia"));
                familia.setObservaciones(rs.getString("observaciones"));

                // busco los miembros de la familia y los cargo en un arrayList
                GestorMiembroFamilia gestorMiembroFamilia = new GestorMiembroFamilia();
                ArrayList miembrosFamilia = (ArrayList) gestorMiembroFamilia.getMiembrosFamilia(familia.getNroFamilia());
                familia.setMiembrosFamilia(miembrosFamilia);

                GestorEspecie gestorEspecie = new GestorEspecie();
                Especie especie = (Especie) gestorEspecie.getUno(rs.getInt("idEspecie"));
                familia.setEspecie(especie);

                GestorEstadoFamilia gestorEstadoFamilia = new GestorEstadoFamilia();
                EstadoFamilia estadoFamilia = (EstadoFamilia) gestorEstadoFamilia.getUno(familia.getNroFamilia());
                familia.setEstado(estadoFamilia);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFamilia.getUltimo() !!!");
        }
        return familia;
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() throws Exception {
        //copio del dame todos pero tiene un TODO
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * " +
                    "FROM familia f " +
                    "WHERE f.numeroFamilia NOT IN " +
                    "(SELECT c.numeroFamilia FROM colmena c where c.numeroFamilia IS NOT NULL)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto familia
                Familia familia = new Familia();

                familia.setCaracteristicas(rs.getString("caracteristicas"));
                familia.setDenominacion(rs.getString("denominacion"));


                familia.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                familia.setNroGrupo(rs.getInt("numeroFamilia"));
                familia.setObservaciones(rs.getString("observaciones"));

                // busco los miembros de la familia y los cargo en un arrayList
                GestorMiembroFamilia gestorMiembroFamilia = new GestorMiembroFamilia();
                ArrayList miembrosFamilia = (ArrayList) gestorMiembroFamilia.getMiembrosFamilia(familia.getNroFamilia());
                familia.setMiembrosFamilia(miembrosFamilia);

                GestorEspecie gestorEspecie = new GestorEspecie();
                Especie especie = (Especie) gestorEspecie.getUno(rs.getInt("idEspecie"));
                familia.setEspecie(especie);

                GestorEstadoFamilia gestorEstadoFamilia = new GestorEstadoFamilia();
                EstadoFamilia estadoFamilia = (EstadoFamilia) gestorEstadoFamilia.getUno(rs.getInt("idEstadoFamilia"));
                familia.setEstado(estadoFamilia);

//                //No lo cargo porque no se que es!
//                //@TODO: hacer el metodo GestorHistorialEstadoGrupo.getTodos()
//                GestorHistorialEstadoGrupo gestorHistorialEstadoGrupo = new GestorHistorialEstadoGrupo();
//                ArrayList historialEstadoGrupo = (ArrayList) gestorHistorialEstadoGrupo.getTodos();
//                familia.setHistorialEstadoGrupo(historialEstadoGrupo);

                list.add(familia);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFamilia (getSinAsignar())!!!");
        }
        return list;
    }

    public Object getUno(int idObjeto) throws Exception {
        Familia familia = new Familia();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM familia " +
                    " where numeroFamilia = ? " +
                    " ORDER BY 1 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto familia
                familia.setCaracteristicas(rs.getString("caracteristicas"));
                familia.setDenominacion(rs.getString("denominacion"));
                familia.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                familia.setNroGrupo(rs.getInt("numeroFamilia"));
                familia.setObservaciones(rs.getString("observaciones"));

                // busco los miembros de la familia y los cargo en un arrayList
                GestorMiembroFamilia gestorMiembroFamilia = new GestorMiembroFamilia();
                ArrayList miembrosFamilia = (ArrayList) gestorMiembroFamilia.getMiembrosFamilia(familia.getNroFamilia());
                familia.setMiembrosFamilia(miembrosFamilia);

                GestorEspecie gestorEspecie = new GestorEspecie();
                Especie especie = (Especie) gestorEspecie.getUno(rs.getInt("idEspecie"));
                familia.setEspecie(especie);

                GestorEstadoFamilia gestorEstadoFamilia = new GestorEstadoFamilia();
                EstadoFamilia estadoFamilia = (EstadoFamilia) gestorEstadoFamilia.getUno(rs.getInt("idEstadoFamilia"));
                familia.setEstado(estadoFamilia);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFamilia !!!");
        }
        return familia;
    }

    public int insertUno(Object object) throws Exception {
        Familia familia = (Familia) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = "insert into familia(numeroFamilia, caracteristicas, fechaAlta, " +
                    " idEspecie, observaciones, denominacion, idEstadoFamilia) " +
                    " values (?,?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, familia.getNroFamilia());
            ps.setString(2, familia.getCaracteristicas());
            ps.setDate(3, UtilFecha.convertiFecha(familia.getFechaAlta()));
            ps.setInt(4, familia.getEspecie().getIdEspecie());
            ps.setString(5, familia.getObservaciones());
            ps.setString(6, familia.getDenominacion());
            ps.setInt(7, familia.getEstado().getIdEstadoFamilia());

            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);

            //Logueo la ALTA
            this.insertHistorialEstado(familia.getNroFamilia(), familia.getEstado().getIdEstadoFamilia());
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFamilia.insertUno() ");
        }

        //agrego cada uno de los miembros familia
        //lo hago aca, porque PRIMERO debo crear la familia y luego insertarle sus MiembrosFamilia
        for (int i = 0; i < familia.getMiembrosFamilia().size(); i++) {
            GestorMiembroFamilia gestorMiembroFamilia = new GestorMiembroFamilia();
            gestorMiembroFamilia.updateUno(familia.getMiembrosFamilia().get(i), familia.getNroFamilia());
        }

        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        Familia familia = (Familia) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            //old: String sql = " delete from familia where numeroFamilia = ? ";
            String sql = " update familia set fechabaja = ? where numeroFamilia = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setDate(1, UtilFecha.convertiFecha(new Date()));
            ps.setInt(2, familia.getNroFamilia());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);

            //Logueo la BAJA
            this.insertHistorialEstado(familia.getNroFamilia(), 2);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFamilia.deleteUno ");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        Familia familia = (Familia) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update familia set " +
                    " caracteristicas = ? , " +
                    " fechaAlta = ? , " +
                    " idEspecie = ? , " +
                    " observaciones = ? , " +
                    " denominacion = ? ," +
                    " idEstadoFamilia = ? " +
                    " WHERE numeroFamilia = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setString(1, familia.getCaracteristicas());
            ps.setDate(2, UtilFecha.convertiFecha(familia.getFechaAlta()));
            ps.setInt(3, familia.getEspecie().getIdEspecie());
            ps.setString(4, familia.getObservaciones());
            ps.setString(5, familia.getDenominacion());
            ps.setInt(6, familia.getEstado().getIdEstadoFamilia());
            ps.setInt(7, familia.getNroFamilia());

            //agrego cada uno de los miembros familia
            for (int i = 0; i < familia.getMiembrosFamilia().size(); i++) {
                GestorMiembroFamilia gestorMiembroFamilia = new GestorMiembroFamilia();
                gestorMiembroFamilia.updateUno(familia.getMiembrosFamilia().get(i), familia.getNroFamilia());
            }

            // Familia a actualizar
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);

            //Logueo la MODIFICACION
            this.insertHistorialEstado(familia.getNroFamilia(), familia.getEstado().getIdEstadoFamilia());
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFamilia.updateUno !!! (updateUno)");
        }
        return resultado;
    }

    public int insertHistorialEstado(int numeroFamilia, int idEstadoFamilia) throws Exception {
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " insert into historialestadofamilia (numeroFamilia, idEstadoFamilia, fecha ) " +
                    " values (?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, numeroFamilia);
            ps.setInt(2, idEstadoFamilia);
            ps.setDate(3, UtilFecha.convertiFecha(new Date()));
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorFamilia !!! (insertHistorialEstado)");
        }
        return resultado;
    }
}

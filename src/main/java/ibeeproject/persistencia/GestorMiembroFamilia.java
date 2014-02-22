/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.familia.MiembroFamilia;
import ibeeproject.model.familia.TipoAbeja;
import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.model.soporte.UtilMiembroFamilia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author burni.matias
 */
public class GestorMiembroFamilia implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorMiembroFamilia() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        ArrayList list1 = new ArrayList();
        try {
            list1.clear();
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM miembrofamilia ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MiembroFamilia miembroFamilia = new MiembroFamilia();
                miembroFamilia.setCantidad(rs.getInt("cantidad"));
                miembroFamilia.setDenominacion(rs.getString("denominacion"));
                miembroFamilia.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                miembroFamilia.setFechaBaja(UtilFecha.convertiFecha(rs.getDate("fechaBaja")));
                miembroFamilia.setFechaNacimiento(UtilFecha.convertiFecha(rs.getDate("fechaNac")));
                miembroFamilia.setIdMiembroFamilia(rs.getInt("idMiembroFamilia"));

                // busco los miembros de la familia y los cargo en un arrayList
                GestorTipoAbeja gestorTipoAbeja = new GestorTipoAbeja();
                TipoAbeja tipoAbeja = (TipoAbeja) gestorTipoAbeja.getUno(rs.getInt("idTipoAbeja"));
                miembroFamilia.setTipoAbeja(tipoAbeja);

                list1.add(miembroFamilia);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorMiembroFamilia.getTodos !!!");
        }
        return list1;
    }

    /**
     * Busca los miembros de Familia y retorna el objeto + la familia a la que esta asignada
     * Para realizar esto devuelvo un ArrayList con UtilMiembroFamilia dentro
     */
    public ArrayList getFamiliaAsignada() {
        ArrayList list1 = new ArrayList();
        try {
            list1.clear();
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM miembrofamilia ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UtilMiembroFamilia utilMiembroFamilia = new UtilMiembroFamilia();
                utilMiembroFamilia.getMiembroFamilia().setCantidad(rs.getInt("cantidad"));
                utilMiembroFamilia.getMiembroFamilia().setDenominacion(rs.getString("denominacion"));
                utilMiembroFamilia.getMiembroFamilia().setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                utilMiembroFamilia.getMiembroFamilia().setFechaBaja(UtilFecha.convertiFecha(rs.getDate("fechaBaja")));
                utilMiembroFamilia.getMiembroFamilia().setFechaNacimiento(UtilFecha.convertiFecha(rs.getDate("fechaNac")));
                utilMiembroFamilia.getMiembroFamilia().setIdMiembroFamilia(rs.getInt("idMiembroFamilia"));
                utilMiembroFamilia.setNumeroFamilia(rs.getInt("numeroFamilia"));

                GestorTipoAbeja gestorTipoAbeja = new GestorTipoAbeja();
                TipoAbeja tipoAbeja = (TipoAbeja) gestorTipoAbeja.getUno(rs.getInt("idTipoAbeja"));
                utilMiembroFamilia.getMiembroFamilia().setTipoAbeja(tipoAbeja);

                list1.add(utilMiembroFamilia);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorMiembroFamilia.getFamiliaAsignada !!!");
        }
        return list1;
    }

    public Object getUltimo() {

        MiembroFamilia miembroFamilia = new MiembroFamilia();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM miembrofamilia WHERE idMiembroFamilia = (select MAX(idMiembroFamilia) from miembrofamilia)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                miembroFamilia.setCantidad(rs.getInt("cantidad"));
                miembroFamilia.setDenominacion(rs.getString("denominacion"));
                miembroFamilia.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                miembroFamilia.setFechaBaja(UtilFecha.convertiFecha(rs.getDate("fechaBaja")));
                miembroFamilia.setFechaNacimiento(UtilFecha.convertiFecha(rs.getDate("fechaNac")));
                miembroFamilia.setIdMiembroFamilia(rs.getInt("idMiembroFamilia"));

                // busco los miembros de la familia y los cargo en un arrayList
                GestorTipoAbeja gestorTipoAbeja = new GestorTipoAbeja();
                TipoAbeja tipoAbeja = (TipoAbeja) gestorTipoAbeja.getUno(rs.getInt("idTipoAbeja"));
                miembroFamilia.setTipoAbeja(tipoAbeja);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorMiembroFamilia! (getUltimo)");
        }
        return miembroFamilia;

    }

    public ArrayList getAsignado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) {
        MiembroFamilia miembroFamilia = new MiembroFamilia();
        try {
            this.list.clear();
            conn = connPool.getConnection();
            String sql = "SELECT * FROM miembrofamilia WHERE idMiembroFamilia = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                miembroFamilia.setIdMiembroFamilia(rs.getInt("idMiembroFamilia"));
                miembroFamilia.setFechaNacimiento(UtilFecha.convertiFecha(rs.getDate("fechaNac")));
                miembroFamilia.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                miembroFamilia.setFechaBaja(UtilFecha.convertiFecha(rs.getDate("fechaBaja")));

                GestorTipoAbeja gestorTipoAbeja = new GestorTipoAbeja();
                TipoAbeja tipoAbeja = (TipoAbeja) gestorTipoAbeja.getUno(rs.getInt("idTipoAbeja"));
                miembroFamilia.setTipoAbeja(tipoAbeja);

                miembroFamilia.setCantidad(rs.getInt("cantidad"));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorMiembroFamilia.getUno() !!!");
        }
        return miembroFamilia;
    }

    /**
     * devuelve un arrayList con los miembros de la familia de ese nroFamilia
     **/
    public ArrayList getMiembrosFamilia(int nroFamilia) {
        try {
            this.list.clear();
//            this.list=null;
//            this.list=new ArrayList();
            conn = connPool.getConnection();
            String sql = "SELECT * FROM miembrofamilia WHERE numeroFamilia = ? ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, nroFamilia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MiembroFamilia miembroFamilia = new MiembroFamilia();
                miembroFamilia.setDenominacion(rs.getString("denominacion"));
                miembroFamilia.setIdMiembroFamilia(rs.getInt("idMiembroFamilia"));
                miembroFamilia.setCantidad(rs.getInt("cantidad"));
                miembroFamilia.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                miembroFamilia.setFechaBaja(UtilFecha.convertiFecha(rs.getDate("fechaBaja")));
                miembroFamilia.setFechaNacimiento(UtilFecha.convertiFecha(rs.getDate("fechaNac")));

                GestorTipoAbeja gestorTipoAbeja = new GestorTipoAbeja();
                TipoAbeja tipoAbeja = (TipoAbeja) gestorTipoAbeja.getUno(rs.getInt("idTipoAbeja"));
                miembroFamilia.setTipoAbeja(tipoAbeja);

                list.add(miembroFamilia);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorSintoma.getMiembrosFamilia !!! (getTodos)");
        }
        return list;
    }

    /**
     * Devuelve todos los MiembrosFamilia que no son de esa Familia
     * Esto sirve por si yo quiero agregar MiembrosFamilia
     */
    public ArrayList getTodosMenosFamilia(int nroFamilia) {
        try {
            this.list.clear();
            conn = connPool.getConnection();
            PreparedStatement ps;
            String sql = "SELECT DISTINCT * FROM miembrofamilia " +
                    " WHERE numeroFamilia <> ? ORDER BY 1 ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, nroFamilia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MiembroFamilia miembroFamilia = new MiembroFamilia();
                miembroFamilia.setIdMiembroFamilia(rs.getInt("idMiembroFamilia"));
                miembroFamilia.setDenominacion(rs.getString("denominacion"));
                miembroFamilia.setCantidad(rs.getInt("cantidad"));
                miembroFamilia.setFechaAlta(UtilFecha.convertiFecha(rs.getDate("fechaAlta")));
                miembroFamilia.setFechaBaja(UtilFecha.convertiFecha(rs.getDate("fechaBaja")));
                miembroFamilia.setFechaNacimiento(UtilFecha.convertiFecha(rs.getDate("fechaNac")));
                GestorTipoAbeja gestorTipoAbeja = new GestorTipoAbeja();
                TipoAbeja tipoAbeja = (TipoAbeja) gestorTipoAbeja.getUno(rs.getInt("idTipoAbeja"));
                miembroFamilia.setTipoAbeja(tipoAbeja);

                list.add(miembroFamilia);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorMiembroFamilia.getTodosPorFamilia");
        }
        return list;
    }

    public ArrayList getTodos(int numeroFamilia) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int insertUno(Object object) throws Exception {
        MiembroFamilia miembroFamilia = (MiembroFamilia) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = "insert into miembrofamilia(idMiembroFamilia, denominacion, fechaNac, fechaAlta, idTipoAbeja, cantidad) " +
                    " values (?,?,?,?,?,?)";
            //el numero de familia no lo ingreso porque cuando lo creo, todavia no se a que flia pertenece.
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, miembroFamilia.getIdMiembroFamilia());
            ps.setString(2, miembroFamilia.getDenominacion());
            ps.setDate(3, UtilFecha.convertiFecha(miembroFamilia.getFechaNacimiento()));
            ps.setDate(4, UtilFecha.convertiFecha(miembroFamilia.getFechaAlta()));
            ps.setInt(5, miembroFamilia.getTipoAbeja().getIdTipoAbeja());
            ps.setInt(6, miembroFamilia.getCantidad());

            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorMiembroFamilia! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        MiembroFamilia miembroFamilia = (MiembroFamilia) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " delete from miembrofamilia where idMiembroFamilia = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, miembroFamilia.getIdMiembroFamilia());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorMiembroFamilia! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        MiembroFamilia miembroFamilia = (MiembroFamilia) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update miembrofamilia set " +
                    " denominacion = ? , " +
                    " fechaNac = ? , " +
                    " fechaAlta = ? , " +
                    " idTipoAbeja = ? , " +
                    " cantidad = ? " +
                    " WHERE idMiembroFamilia = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setString(1, miembroFamilia.getDenominacion());
            ps.setDate(2, UtilFecha.convertiFecha(miembroFamilia.getFechaNacimiento()));
            ps.setDate(3, UtilFecha.convertiFecha(miembroFamilia.getFechaAlta()));
            ps.setInt(4, miembroFamilia.getTipoAbeja().getIdTipoAbeja());
            ps.setInt(5, miembroFamilia.getCantidad());

            ps.setInt(6, miembroFamilia.getIdMiembroFamilia());

            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorMiembroFamila! (updateUno)");
        }
        return resultado;
    }

    /**
     * Es para cambiar la asignacion del miembro a la familia
     * O sea, que este miembro ahora pertenece a otra familia!!!
     */
    public int updateUno(MiembroFamilia miembroFamilia, int numeroFamilia) throws Exception {
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update miembrofamilia set " +
                    " denominacion = ? , " +
                    " fechaNac = ? , " +
                    " fechaAlta = ? , " +
                    " fechaBaja = ? , " +
                    " idTipoAbeja = ? , " +
                    " cantidad = ? , " +
                    " numeroFamilia = ? " +
                    " WHERE idMiembroFamilia = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setString(1, miembroFamilia.getDenominacion());
            ps.setDate(2, UtilFecha.convertiFecha(miembroFamilia.getFechaNacimiento()));
            ps.setDate(3, UtilFecha.convertiFecha(miembroFamilia.getFechaAlta()));
            ps.setDate(4, UtilFecha.convertiFecha(miembroFamilia.getFechaBaja()));
            ps.setInt(5, miembroFamilia.getTipoAbeja().getIdTipoAbeja());
            ps.setInt(6, miembroFamilia.getCantidad());
            ps.setInt(7, numeroFamilia);
            ps.setInt(8, miembroFamilia.getIdMiembroFamilia());

            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorMiembroFamilia !!! (updateUno)");
        }
        return resultado;
    }
}

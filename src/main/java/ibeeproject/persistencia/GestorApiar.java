/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.apiar.EstadoApiar;
import ibeeproject.model.apiar.Apiar;
import ibeeproject.model.apiar.Colmena;
import ibeeproject.model.apiar.EstadoColmena;
import ibeeproject.model.apiar.Layout;
import ibeeproject.model.apiar.Ubicacion;
import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.model.zona.Zona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class GestorApiar implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorApiar() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        list.clear();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM apiar where fechaBaja IS NULL";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Apiar apiar = new Apiar();
                apiar.setIdApiar(rs.getInt("idApiar"));
                apiar.setFechaAlta(rs.getDate("fechaAlta"));
                apiar.setFechaBaja(rs.getDate("fechaBaja"));
                apiar.setDenominacion(rs.getString("denominacion"));
                apiar.setFechaCreacion(rs.getDate("fechaCreacion"));

                GestorEstadoApiar gestEstApi = new GestorEstadoApiar();
                apiar.setEstado((EstadoApiar) gestEstApi.getUno(rs.getInt("idEstado")));

                GestorLayout gestLay = new GestorLayout();
                apiar.setLayout((Layout) gestLay.getUno(rs.getInt("idLayout")));

                GestorUbicacion gestUbi = new GestorUbicacion();
                apiar.setUbicacion((Ubicacion) gestUbi.getUno(rs.getInt("idUbicacion")));

                GestorZona gestZo = new GestorZona();
                apiar.setZona((Zona) gestZo.getUno(rs.getInt("idZona")));

                //Regenero las Colmenas Asignadas
                sql = " SELECT numeroColmena FROM asignacioncolmena WHERE idApiar = ? " +
                        " AND fechaHasta IS NULL ";
                PreparedStatement ps1 = conn.prepareStatement(sql);
                ps1.setInt(1, apiar.getIdApiar());
                ResultSet rs1 = ps1.executeQuery();
                GestorColmena gestorColmena = new GestorColmena();
                while (rs1.next()) {
                    Colmena colmena = (Colmena) gestorColmena.getUno(rs1.getInt("numeroColmena"));
                    if (colmena != null) {
                        apiar.getColmenasAsignadas().add(colmena);
                    }
                }
                list.add(apiar);
                rs1.close();
                ps1.close();
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorApiar! (getTodos)");
        }
        return list;
    }

    public Object getUltimo() {
        Apiar apiar = new Apiar();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM apiar WHERE idApiar = (select MAX(idApiar) from apiar)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                apiar.setIdApiar(rs.getInt("idApiar"));
                apiar.setFechaAlta(rs.getDate("fechaAlta"));
                apiar.setFechaBaja(rs.getDate("fechaBaja"));
                apiar.setDenominacion(rs.getString("denominacion"));
                apiar.setFechaCreacion(rs.getDate("fechaCreacion"));

                GestorEstadoApiar gestEstApi = new GestorEstadoApiar();
                apiar.setEstado((EstadoApiar) gestEstApi.getUno(rs.getInt("idEstado")));

                GestorLayout gestLay = new GestorLayout();
                apiar.setLayout((Layout) gestLay.getUno(rs.getInt("idLayout")));

                GestorUbicacion gestUbi = new GestorUbicacion();
                apiar.setUbicacion((Ubicacion) gestUbi.getUno(rs.getInt("idUbicacion")));

                GestorZona gestZo = new GestorZona();
                apiar.setZona((Zona) gestZo.getUno(rs.getInt("idZona")));

                //Regenero las Colmenas Asignadas
                sql = " SELECT numeroColmena FROM asignacioncolmena WHERE idApiar = ? " +
                        " AND fechaHasta IS NULL ";
                PreparedStatement ps1 = conn.prepareStatement(sql);
                ps1.setInt(1, apiar.getIdApiar());
                ResultSet rs1 = ps1.executeQuery();
                GestorColmena gestorColmena = new GestorColmena();
                while (rs1.next()) {
                    Colmena colmena = (Colmena) gestorColmena.getUno(rs1.getInt("numeroColmena"));
                    if (colmena != null) {
                        apiar.getColmenasAsignadas().add(colmena);
                    }
                }
                rs1.close();
                ps1.close();

            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorApiar !!! (getUltimo)");
        }
        return apiar;
    }

    public ArrayList getAsignado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) {
        Apiar apiar = new Apiar();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM apiar WHERE idApiar = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                apiar.setIdApiar(rs.getInt("idApiar"));
                apiar.setFechaAlta(rs.getDate("fechaAlta"));
                apiar.setFechaBaja(rs.getDate("fechaBaja"));
                apiar.setDenominacion(rs.getString("denominacion"));
                apiar.setFechaCreacion(rs.getDate("fechaCreacion"));

                GestorEstadoApiar gestEstApi = new GestorEstadoApiar();
                apiar.setEstado((EstadoApiar) gestEstApi.getUno(rs.getInt("idEstado")));

                GestorLayout gestLay = new GestorLayout();
                apiar.setLayout((Layout) gestLay.getUno(rs.getInt("idLayout")));

                GestorUbicacion gestUbi = new GestorUbicacion();
                apiar.setUbicacion((Ubicacion) gestUbi.getUno(rs.getInt("idUbicacion")));

                GestorZona gestZo = new GestorZona();
                apiar.setZona((Zona) gestZo.getUno(rs.getInt("idZona")));

                //Regenero las Colmenas Asignadas
                sql = " SELECT numeroColmena FROM asignacioncolmena WHERE idApiar = ? " +
                        " AND fechaHasta IS NULL ";
                PreparedStatement ps1 = conn.prepareStatement(sql);
                ps1.setInt(1, apiar.getIdApiar());
                ResultSet rs1 = ps1.executeQuery();
                GestorColmena gestorColmena = new GestorColmena();
                while (rs1.next()) {
                    Colmena colmena = (Colmena) gestorColmena.getUno(rs1.getInt("numeroColmena"));
                    if (colmena != null) {
                        apiar.getColmenasAsignadas().add(colmena);
                    }
                }
                list.add(apiar);
                rs1.close();
                ps1.close();
            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorApiar! (getUno)");
        }
        return apiar;
    }

    public int insertUno(Object object) throws Exception {
        Apiar apiar = (Apiar) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = "insert into apiar(idApiar, denominacion, " +
                    "fechaAlta, fechaCreacion, idLayout, idZona , idEstado, idUbicacion) " +
                    " values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, apiar.getIdApiar());
            ps.setString(2, apiar.getDenominacion());
            ps.setDate(3, UtilFecha.convertiFecha(apiar.getFechaAlta()));
            ps.setDate(4, apiar.getFechaCreacion());
            ps.setInt(5, apiar.getLayout().getIdLayout());
            ps.setInt(6, apiar.getZona().getIdZona());
            ps.setInt(7, apiar.getEstado().getNumero());
            ps.setInt(8, apiar.getUbicacion().getIdUbicacion());
            resultado = ps.executeUpdate();

            // Inserto las colmenas asignadas
            sql = " insert into asignacioncolmena (idApiar, numeroColmena, fechaDesde) values (?,?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, apiar.getIdApiar());
            for (int i = 0; i < apiar.getColmenasAsignadas().size(); i++) {
                ps.setInt(2, apiar.getColmenasAsignadas().get(i).getIdColmena());
                ps.setDate(3, UtilFecha.convertiFecha(apiar.getColmenasAsignadas().get(i).getFechaAlta()));
                resultado = ps.executeUpdate();
                // REcupero el estado de la colmena y lo seteo como En producción
                Colmena colmena = apiar.getColmenasAsignadas().get(i);
                GestorEstadoColmena gestEC = new GestorEstadoColmena();
                EstadoColmena estado = (EstadoColmena) gestEC.getUno(2);
                colmena.setEstado(estado);
                GestorColmena gestC = new GestorColmena();
                gestC.updateUnoAsignado(colmena);
            }
            ps.close();
            connPool.closeConnection(conn);

            //Logueo la ALTA
            this.insertHistorialEstado(apiar.getIdApiar(), apiar.getEstado().getNumero());

        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorApiar! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        Apiar apiar = (Apiar) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();

            // Desasignación de Colmenas
            String sql = " UPDATE asignacionColmena SET fechaBaja = ? where idApiar = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, apiar.getIdApiar());
            ps.setDate(3, UtilFecha.convertiFecha(new Date()));
            resultado = ps.executeUpdate();

            // Baja Lógica de Apiar
            sql = " UPDATE apiar SET fechaBaja = ? AND idEstado = 3 where idApiar = ? ";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, UtilFecha.convertiFecha(new Date()));
            ps.setInt(2, apiar.getIdApiar());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);

            //Logueo la BAJA
            this.insertHistorialEstado(apiar.getIdApiar(), 3);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorApiar! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        Apiar apiar = (Apiar) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();

            // Actualizo la fecha de Baja para aquellos registros que no esten en el arraylist colmenasAsignadas
            String sql = " SELECT numeroColmena from asignacionColmena WHERE idApiar = ? " +
                    "AND fechaHasta IS NULL";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, apiar.getIdApiar());
            ResultSet rs = ps.executeQuery();
            Colmena col = new Colmena();
            while (rs.next()) {
                boolean existe = false;
                for (int i = 0; i < apiar.getColmenasAsignadas().size(); i++) {
                    if (apiar.getColmenasAsignadas().get(i).getIdColmena() == rs.getInt("numeroColmena")) {
                        existe = true;
                        col = apiar.getColmenasAsignadas().get(i);
                    }
                }
                // Si no existe le actualizo la fechaHasta
                if (existe == true) {
                    String sql2 = " UPDATE asignacionColmena SET fechaHasta = ? " +
                            " WHERE idApiar = ? AND numeroColmena = ? ";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setDate(1, UtilFecha.convertiFecha(new Date()));
                    ps2.setInt(2, apiar.getIdApiar());
                    ps2.setInt(3, rs.getInt("numeroColmena"));
                    resultado = ps2.executeUpdate();
                    // REcupero el estado de la colmena y lo seteo como no asignada
                    GestorEstadoColmena gestEC = new GestorEstadoColmena();
                    EstadoColmena estado = (EstadoColmena) gestEC.getUno(1);
                    col.setEstado( estado );

                    GestorColmena gestC = new GestorColmena();
                    gestC.updateUnoAsignado(col);

                    ps2.close();
                }
            }

            // Verifico las Colmenas, e inserto el registro que no existe
            sql = "SELECT count(*) FROM asignacioncolmena" +
                    " WHERE idApiar = ?  " +
                    " AND numeroColmena = ? " +
                    " AND fechaHasta IS NOT NULL ";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, apiar.getIdApiar());
            for (int i = 0; i < apiar.getColmenasAsignadas().size(); i++) {
                ps.setInt(2, apiar.getColmenasAsignadas().get(i).getIdColmena());
                rs = ps.executeQuery();
                int result = 0;
                if (rs.next()) {
                    result = rs.getInt(1);
                    if (result == 0) { // No Existe
                        String sql1 = " insert into asignacioncolmena (idApiar, numeroColmena, fechaDesde) values (?,?,?) ";
                        PreparedStatement ps1 = conn.prepareStatement(sql1);
                        ps1.setInt(1, apiar.getIdApiar());
                        ps1.setInt(2, apiar.getColmenasAsignadas().get(i).getIdColmena());
                        ps1.setDate(3, UtilFecha.convertiFecha(new Date()));
                        resultado = ps1.executeUpdate();
                        Colmena colmena = (Colmena)apiar.getColmenasAsignadas().get(i);
                        // REcupero el estado de la colmena y lo seteo como En producción
                        GestorEstadoColmena gestEC = new GestorEstadoColmena();
                        EstadoColmena estado = (EstadoColmena) gestEC.getUno(2);
                        colmena.setEstado( estado );

                        GestorColmena gestC = new GestorColmena();
                        gestC.updateUnoAsignado(colmena);
                        ps1.close();
                    }
                }
            }

            sql = " update apiar set " +
                    " denominacion = ? , " +
                    " fechaAlta = ? , " +
                    " fechaCreacion = ? , " +
                    " fechaBaja = ? , " +
                    " idLayout = ? , " +
                    " idZona = ? , " +
                    " idEstado = ? " +
                    " WHERE idApiar = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, apiar.getDenominacion());
            ps.setDate(2, UtilFecha.convertiFecha(apiar.getFechaAlta()));
            ps.setDate(3, apiar.getFechaCreacion());
            ps.setDate(4, apiar.getFechaBaja());
            ps.setInt(5, apiar.getLayout().getIdLayout());
            ps.setInt(6, apiar.getZona().getIdZona());
            ps.setInt(7, apiar.getEstado().getNumero());
            ps.setInt(8, apiar.getIdApiar());
            resultado = ps.executeUpdate();
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
            
            //Logueo la MODIFICACION
            this.insertHistorialEstado(apiar.getIdApiar(), apiar.getEstado().getNumero());
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorApiar! (updateUno)");
        }
        return resultado;
    }

        public int insertHistorialEstado(int idApiar, int idEstadoApiar) throws Exception {
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " insert into historialestadoapiar (idApiar, idEstado, fecha ) " +
                    " values (?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idApiar);
            ps.setInt(2, idEstadoApiar);
            ps.setDate(3, UtilFecha.convertiFecha(new Date()));
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorApiar! (insertHistorialEstado)");
        }
        return resultado;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.apiar.Apiar;
import ibeeproject.model.apiar.Colmena;
import ibeeproject.model.apiar.EstadoColmena;
import ibeeproject.model.apiar.Posicion;
import ibeeproject.model.apiar.TipoMiel;
import ibeeproject.model.cajon.Cajon;
import ibeeproject.model.familia.Familia;
import ibeeproject.model.soporte.UtilFecha;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class GestorColmena implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorColmena() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();

    }

    public boolean registrarColmena(int nroColmena, int abejasPromedio, int cajon, int familia) {
        try {
            conn = connPool.getConnection();
            String sql = "insert into colmena(numeroColmena, fechaAlta, numeroFamilia, numeroCajon) " +
                    " values (" + nroColmena + ",NOW()," + familia + "," + cajon + ")";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            boolean rs = ps.execute();
            ps.close();
            connPool.closeConnection(conn);
            return rs;
        } catch (Exception a) {
            a.printStackTrace();
        }
        return false;
    }

    public ArrayList getTodos() {
        Posicion pos = new Posicion();
        Cajon cajon = new Cajon();
        Familia familia = new Familia();
        Colmena colmena = new Colmena();
        TipoMiel tipoMiel = new TipoMiel();
        EstadoColmena estadoColmena = new EstadoColmena();
        ArrayList colmenas = new ArrayList();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM colmena c where fechaBaja is null ORDER BY numeroColmena";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // armo el cajon
                GestorCajon gestorCajon = new GestorCajon();
                cajon = (Cajon) gestorCajon.getUno(rs.getInt("numeroCajon"));
                colmena.setCajon(cajon);

                // armo la familia
                GestorFamilia gestorFamilia = new GestorFamilia();
                familia = (Familia) gestorFamilia.getUno(rs.getInt("numeroFamilia"));
                colmena.setFamilia(familia);

                colmena.setDenominacion(rs.getString("denominacion"));
                colmena.setFechaAlta(rs.getDate("fechaAlta"));
                // armo el estado
                GestorEstadoColmena gestorEstado = new GestorEstadoColmena();
                estadoColmena = (EstadoColmena) gestorEstado.getUno(rs.getInt("idEstadoColmena"));
                colmena.setEstado(estadoColmena);

                // armo el tipoMiel
                GestorTipoMiel gestorTipoMiel = new GestorTipoMiel();
                tipoMiel = (TipoMiel) gestorTipoMiel.getUno(rs.getInt("idTipoMiel"));
                colmena.setTipoMiel(tipoMiel);

                colmena.setFechaAlta(rs.getDate("fechaAlta"));
                colmena.setIdColmena(rs.getInt("numeroColmena"));

                colmenas.add(colmena);
                cajon = new Cajon();
                familia = new Familia();
                estadoColmena = new EstadoColmena();
                tipoMiel = new TipoMiel();
                colmena = new Colmena();
            }
            ps.close();
            connPool.closeConnection(conn);
            return colmenas;
        } catch (Exception a) {
            a.printStackTrace();
        }
        return null;
    }

    public Object getUltimo() {
        Posicion pos = new Posicion();
        Cajon cajon = new Cajon();
        Familia familia = new Familia();
        Colmena colmena = new Colmena();
        TipoMiel tipoMiel = new TipoMiel();
        EstadoColmena estadoColmena = new EstadoColmena();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM colmena WHERE numeroColmena = (select MAX(numeroColmena) from colmena)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs == null) {
                System.out.println("RS es null");
            } else {
                // armo el cajon
                GestorCajon gestorCajon = new GestorCajon();
                cajon = (Cajon) gestorCajon.getUno(rs.getInt("numeroCajon"));
                colmena.setCajon(cajon);

                // armo la familia
                GestorFamilia gestorFamilia = new GestorFamilia();
                try {
                    familia = (Familia) gestorFamilia.getUno(rs.getInt("numeroFamilia"));
                } catch (Exception ex) {
                    Logger.getLogger(GestorColmena.class.getName()).log(Level.SEVERE, null, ex);
                }
                colmena.setFamilia(familia);

                colmena.setDenominacion(rs.getString("denominacion"));
                colmena.setFechaAlta(rs.getDate("fechaAlta"));
                // armo el estado
                GestorEstadoColmena gestorEstado = new GestorEstadoColmena();
                try {
                    estadoColmena = (EstadoColmena) gestorEstado.getUno(rs.getInt("idEstadoColmena"));
                } catch (Exception ex) {
                    Logger.getLogger(GestorColmena.class.getName()).log(Level.SEVERE, null, ex);
                }
                colmena.setEstado(estadoColmena);

                // armo el tipoMiel
                GestorTipoMiel gestorTipoMiel = new GestorTipoMiel();
                try {
                    tipoMiel = (TipoMiel) gestorTipoMiel.getUno(rs.getInt("idTipoMiel"));
                } catch (Exception ex) {
                    Logger.getLogger(GestorColmena.class.getName()).log(Level.SEVERE, null, ex);
                }
                colmena.setTipoMiel(tipoMiel);

                colmena.setFechaAlta(rs.getDate("fechaAlta"));
                colmena.setIdColmena(rs.getInt("numeroColmena"));
                return colmena;
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (SQLException a) {
            a.printStackTrace();
        }

        System.out.println("Get ultijmo tengo conexión?: " + conn.toString());
        return 1;
    }

    public ArrayList getAsignado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() {
        list = new ArrayList();
        try {
            conn = connPool.getConnection();
            String sql = " SELECT c.numeroColmena, c.denominacion, c.fechaAlta, c.numeroFamilia, f.observaciones, c.numeroCajon " +
                    " FROM colmena c, familia f " +
                    " where c.numeroFamilia = f.numeroFamilia and " +
                    " c.idEstadoColmena=1 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Colmena colmena = new Colmena();
                colmena.setIdColmena(rs.getInt("c.numeroColmena"));
                colmena.setFechaAlta(rs.getDate("c.fechaAlta"));
                colmena.setDenominacion(rs.getString("c.denominacion"));

                GestorCajon gestC = new GestorCajon();
                colmena.setCajon((Cajon) gestC.getUno(rs.getInt("c.numeroCajon")));
                GestorFamilia gestF = new GestorFamilia();
                colmena.setFamilia((Familia) gestF.getUno(rs.getInt("c.numeroFamilia")));
                list.add(colmena);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorApiar !!! (getTodos)");
        }
        return list;
    }

    public ArrayList getAsignadas(Object objeto) {
        list.clear();
        int idApiar = Integer.valueOf(String.valueOf(objeto));
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * " +
                    " from asignacioncolmena a , colmena c, apiar ap, posicion p " +
                    " where c.numeroColmena=a.numeroColmena" +
                    " and ap.idApiar=a.idApiar" +
                    " and p.idLayout=ap.idLayout " +
                    " and p.idPosicion=c.idPosicion" +
                    " and ap.idApiar=" + idApiar;
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Colmena colmena = new Colmena();
                colmena.setIdColmena(rs.getInt("c.numeroColmena"));
                colmena.setFechaAlta(rs.getDate("c.fechaAlta"));
                colmena.setDenominacion(rs.getString("c.denominacion"));

                GestorCajon gestC = new GestorCajon();
                colmena.setCajon((Cajon) gestC.getUno(rs.getInt("c.numeroCajon")));
                GestorPosicion gestP = new GestorPosicion();
                Posicion pos = (Posicion) gestP.getUno(rs.getInt("c.numeroColmena"));
                colmena.setPosicion(pos);
                GestorFamilia gestF = new GestorFamilia();
                colmena.setFamilia((Familia) gestF.getUno(rs.getInt("c.numeroFamilia")));
                list.add(colmena);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorApiar !!! (getTodos)");
        }
        return list;
    }

    public int isAsignada(Colmena colmena) {
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " SELECT count(idApiar) as cantidad FROM asignacioncolmena a where numeroColmena = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, colmena.getIdColmena());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado = rs.getInt("cantidad");
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorColmena !!! (isAsignada)");
        }
        return resultado;
    }

    public Object getUno(int idObjeto) {
        Colmena colmena = new Colmena();
        Posicion pos = new Posicion();
        Cajon cajon = new Cajon();
        Familia familia = new Familia();
        TipoMiel tipoMiel = new TipoMiel();
        EstadoColmena estadoColmena = new EstadoColmena();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM colmena " +
                    " WHERE numeroColmena = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // armo el cajon
                GestorCajon gestorCajon = new GestorCajon();
                cajon = (Cajon) gestorCajon.getUno(rs.getInt("numeroCajon"));
                colmena.setCajon(cajon);

                // armo la familia
                GestorFamilia gestorFamilia = new GestorFamilia();
                familia = (Familia) gestorFamilia.getUno(rs.getInt("numeroFamilia"));
                colmena.setFamilia(familia);

                colmena.setDenominacion(rs.getString("denominacion"));
                colmena.setFechaAlta(rs.getDate("fechaAlta"));
                // armo el estado
                GestorEstadoColmena gestorEstado = new GestorEstadoColmena();
                estadoColmena = (EstadoColmena) gestorEstado.getUno(rs.getInt("idEstadoColmena"));
                colmena.setEstado(estadoColmena);

                // armo el tipoMiel
                GestorTipoMiel gestorTipoMiel = new GestorTipoMiel();
                tipoMiel = (TipoMiel) gestorTipoMiel.getUno(rs.getInt("idTipoMiel"));
                colmena.setTipoMiel(tipoMiel);

                colmena.setFechaAlta(rs.getDate("fechaAlta"));
                colmena.setIdColmena(rs.getInt("numeroColmena"));

                GestorPosicion gestP = new GestorPosicion();
                colmena.setPosicion((Posicion) gestP.getUno(rs.getInt("numeroColmena")));

            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorColmena !!! (getUno)");
        }
        return colmena;
    }

    public int insertUno(Object object, Object object2) throws Exception {
        Apiar apiar = (Apiar) object;
        int numeroColmena = (int) Integer.valueOf(String.valueOf(object2));
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " insert into asignacioncolmena(idApiar, numeroColmena , fechaDesde) " +
                    " values (?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            // Atributos a actualizar
            ps.setInt(1, apiar.getIdApiar());
            ps.setInt(2, numeroColmena);
            ps.setDate(3, UtilFecha.convertiFecha(apiar.getFechaAlta()));
            resultado = ps.executeUpdate();

            sql = " update colmena set idestadocolmena = 2 " +
                    " where idcolmena = ? ";
            ps.setInt(1, numeroColmena);
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);

            //Logueo la ASIGNACION
            this.insertHistorialEstado(numeroColmena, 2);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorColmena !!! (updateUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        Apiar apiar = (Apiar) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " delete from asignacioncolmena where idApiar = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, apiar.getIdApiar());
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEnfermedad !!! (deleteUno)");
        }
        return resultado;
    }

    public int deleteUno(Colmena colmena) throws Exception {
        int resultado = 0;
        try {
            conn = connPool.getConnection();

            String sql = " update colmena set fechaBaja = ? , set idEstadoColmena = ? " +
                    " where numeroColmena = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setDate(1, UtilFecha.convertiFecha(colmena.getFechaBaja()));
            ps.setInt(2, 4);
            ps.setInt(3, colmena.getIdColmena());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);

            //Logueo la BAJA
            this.insertHistorialEstado(colmena.getIdColmena(), 4);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorColmena !!! (deleteUno(Colmena))");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        Colmena colmena = (Colmena) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update colmena set " +
                    " denominacion = ?, " +
                    " fechaAlta = ?, " +
                    " numeroFamilia = ?, " +
                    // " idTipoMiel = ?, " +
                    " numeroCajon = ? ," +
                    " idPosicion = ? ," +
                    " idEstadoColmena = ?" +
                    " WHERE numeroColmena = ? ";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ps.setString(1, colmena.getDenominacion());
            ps.setDate(2, UtilFecha.convertiFecha(colmena.getFechaAlta()));
            ps.setInt(3, colmena.getFamilia().getNroFamilia());
            //ps.setInt(4, colmena.getTipoMiel().getIdTipoMiel());
            ps.setInt(4, colmena.getCajon().getNroCajon());
            ps.setInt(5, colmena.getPosicion().getIdPosicion());
            ps.setInt(6, colmena.getEstado().getNumero());
            ps.setInt(7, colmena.getIdColmena());
            //FAlta dar de alta al usuario q´carga este apiar....podría modificarse la base de datos y poner el campo usuairoAlta mirando a empleado

            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);

            //Logueo la MODIFICACION
            this.insertHistorialEstado(colmena.getIdColmena(), colmena.getEstado().getNumero());
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorColmena !!! (updateUno)");
        }
        return resultado;
    }

    public int updateUnoMatias(Object object) throws Exception {
        Colmena colmena = (Colmena) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update colmena set " +
                    " denominacion = ?, " +
                    " fechaAlta = ?, " +
                    " numeroFamilia = ?, " +
                    // " idTipoMiel = ?, " +
                    " numeroCajon = ? ," +
                    //                        " idPosicion = ? ," +
                    " idEstadoColmena = ?" +
                    " WHERE numeroColmena = ? ";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ps.setString(1, colmena.getDenominacion());
            ps.setDate(2, UtilFecha.convertiFecha(colmena.getFechaAlta()));
            ps.setInt(3, colmena.getFamilia().getNroFamilia());
            //ps.setInt(4, colmena.getTipoMiel().getIdTipoMiel());
            ps.setInt(4, colmena.getCajon().getNroCajon());
//            ps.setInt(5, colmena.getPosicion().getIdPosicion());
            ps.setInt(5, colmena.getEstado().getNumero());
            ps.setInt(6, colmena.getIdColmena());
            //FAlta dar de alta al usuario q´carga este apiar....podría modificarse la base de datos y poner el campo usuairoAlta mirando a empleado

            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);

            //Logueo la MODIFICACION
            this.insertHistorialEstado(colmena.getIdColmena(), colmena.getEstado().getNumero());
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorColmena !!! (updateUnoMatias)");
        }
        return resultado;
    }

    public void updateUnoAsignado(Object object) throws Exception {
        Colmena colmena = (Colmena) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update colmena set " +
                    " idEstadoColmena = ? ," +
                    " idPosicion  = ? " +
                    " WHERE numeroColmena = ? ";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, colmena.getEstado().getNumero());

            GestorPosicion gestP = new GestorPosicion();
            Posicion pos = (Posicion) gestP.getUno(colmena.getIdColmena());
            ps.setInt(2, pos.getIdPosicion());
            ps.setInt(3, colmena.getIdColmena());
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);

            //Logueo la MODIFICACION
            this.insertHistorialEstado(colmena.getIdColmena(), colmena.getEstado().getNumero());
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorColmena !!! (updateUno)");
        }
    }

    public int insertUno(Object object) throws Exception {
        Colmena colmena = (Colmena) object;
        Colmena aux = (Colmena) getUltimo();
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " insert into colmena (numeroColmena, denominacion, " +
                    " fechaAlta, numeroFamilia, idTipoMiel, numeroCajon," +
                    " idEstadoColmena ) values (?,?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, aux.getIdColmena() + 1);
            ps.setString(2, colmena.getDenominacion());
            ps.setDate(3, UtilFecha.convertiFecha(colmena.getFechaAlta()));
            ps.setInt(4, colmena.getFamilia().getNroFamilia());
            ps.setInt(5, colmena.getTipoMiel().getIdTipoMiel());
            ps.setInt(6, colmena.getCajon().getNroCajon());
            ps.setInt(7, 1);
            resultado = ps.executeUpdate();

            sql = " update cajon set idEstadoCajon = 2 where numeroCajon = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, colmena.getCajon().getNroCajon());

            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);

            //Logueo la MODIFICACION
            this.insertHistorialEstado(colmena.getIdColmena() + 1, 1);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorColmena !!! (insertUno)");
        }
        return resultado;
    }

    public void updateUno(Apiar apiar, Object col) {
        Colmena colmena = (Colmena) col;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update asignacioncolmena set " +
                    " fechaDesde = ? , " +
                    " fechaHasta= ? " +
                    " WHERE idApiar = ? " +
                    " and numeroColmena= ?";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ps.setDate(1, UtilFecha.convertiFecha(apiar.getFechaAlta()));
            Date fechaBaja = new Date();
            ps.setDate(2, UtilFecha.convertiFecha(fechaBaja));
            ps.setInt(3, apiar.getIdApiar());
            ps.setInt(4, colmena.getIdColmena());
            //FAlta dar de alta al usuario q´carga este apiar....podría modificarse la base de datos y poner el campo usuairoAlta mirando a empleado

            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);

            //Logueo la MODIFICACION
            this.insertHistorialEstado(colmena.getIdColmena(), colmena.getEstado().getNumero());
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorColmena !!! (updateUno(Apiar apiar, int idColmena))");
        }

    }

    public int desasignarUno(Colmena colmena) {
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " update asignacioncolmena set " +
                    " fechaHasta = ?  " +
                    " WHERE numeroColmena = ? ";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ps.setDate(1, UtilFecha.convertiFecha(colmena.getFechaBaja()));
            ps.setInt(2, colmena.getIdColmena());

            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);

            //Logueo la MODIFICACION
            this.insertHistorialEstado(colmena.getIdColmena(), colmena.getEstado().getNumero());
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorColmena !!! (desasignarUno)");
        }
        return resultado;
    }

    public int insertHistorialEstado(int numeroColmena, int idEstadoColmena) throws Exception {
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " insert into historialestadocolmena (numeroColmena, idEstadoColmena, fecha ) " +
                    " values (?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, numeroColmena);
            ps.setInt(2, idEstadoColmena);
            ps.setDate(3, UtilFecha.convertiFecha(new Date()));
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorColmena !!! (insertHistorialEstado)");
        }
        return resultado;
    }
}

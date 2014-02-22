/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.actividad.TipoTarea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorTipoTarea implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorTipoTarea() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getTodos(String codTipoActividad) {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM TipoTarea " +
                        "WHERE (codtipoactividad = ? OR codtipoactividad = '0' ) " +
                        "ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, codTipoActividad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoTarea tipoTarea = new TipoTarea();
                tipoTarea.setCodigo(rs.getString("codTipoTarea"));
                tipoTarea.setCodigoTipoActividad(rs.getString("codTipoActividad"));
                tipoTarea.setDenominacion(rs.getString("denominacion"));
                tipoTarea.setDescripcion(rs.getString("descripcion"));
                tipoTarea.setUsaZona(rs.getBoolean("usaZona"));
                tipoTarea.setUsaLayout(rs.getBoolean("usaLayout"));
                tipoTarea.setUsaApiar(rs.getBoolean("usaApiar"));
                tipoTarea.setUsaColmena(rs.getBoolean("usaColmena"));
                tipoTarea.setUsaFamilia(rs.getBoolean("usaFamilia"));
                tipoTarea.setUsaCajon(rs.getBoolean("usaCajon"));
                tipoTarea.setUsaTratamiento(rs.getBoolean("usaTratamiento"));
                tipoTarea.setUsaEnfermedad(rs.getBoolean("usaEnfermedad"));
                tipoTarea.setUsaSintoma(rs.getBoolean("usaSintoma"));
                tipoTarea.setUsaAlarma(rs.getBoolean("usaAlarma"));

                //Reconstruyo los datos de recoleccion
                GestorDatoDeRecoleccion gestorDatRec = new GestorDatoDeRecoleccion();
                ArrayList datos = gestorDatRec.getTodos(tipoTarea.getCodigoTipoActividad(), tipoTarea.getCodigo());
                tipoTarea.setDetalleTipoTarea(datos);
                list.add(tipoTarea);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoTarea !!! (getTodos(string))");
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

    public Object getUno(int idObjeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(String codTipoActividad, String codTipoTarea) throws Exception {
        TipoTarea tipoTarea = new TipoTarea();
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * FROM TipoTarea " +
                        " WHERE codTipoActividad = ? " +
                        " AND codTipoTarea = ? " +
                        " ORDER BY 1 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, codTipoActividad);
            ps.setString(2, codTipoTarea);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipoTarea.setCodigo(rs.getString("codTipoTarea"));
                tipoTarea.setCodigoTipoActividad(rs.getString("codTipoActividad"));
                tipoTarea.setDenominacion(rs.getString("denominacion"));
                tipoTarea.setDescripcion(rs.getString("descripcion"));
                tipoTarea.setUsaZona(rs.getBoolean("usaZona"));
                tipoTarea.setUsaLayout(rs.getBoolean("usaLayout"));
                tipoTarea.setUsaApiar(rs.getBoolean("usaApiar"));
                tipoTarea.setUsaColmena(rs.getBoolean("usaColmena"));
                tipoTarea.setUsaFamilia(rs.getBoolean("usaFamilia"));
                tipoTarea.setUsaCajon(rs.getBoolean("usaCajon"));
                tipoTarea.setUsaTratamiento(rs.getBoolean("usaTratamiento"));
                tipoTarea.setUsaEnfermedad(rs.getBoolean("usaEnfermedad"));
                tipoTarea.setUsaSintoma(rs.getBoolean("usaSintoma"));
                tipoTarea.setUsaAlarma(rs.getBoolean("usaAlarma"));

                //Reconstruyo los datos de recoleccion
                GestorDatoDeRecoleccion gestorDatRec = new GestorDatoDeRecoleccion();
                ArrayList datos = gestorDatRec.getTodos(tipoTarea.getCodigoTipoActividad(), tipoTarea.getCodigo());
                tipoTarea.setDetalleTipoTarea(datos);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoTarea !!! (getUno)");
        }
        return tipoTarea;
    }

    public int insertUno(Object object) throws Exception {
        TipoTarea tipoTarea = (TipoTarea) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = "insert into tipotarea (codtipoactividad, codtipotarea, " +
                    " denominacion, descripcion, usaApiar, usaFamilia, usaCajon, " +
                    " usaTratamiento, usaEnfermedad, usaSintoma, usaAlarma, " +
                    " usaLayout, usaZona, usaColmena) " +
                    " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, tipoTarea.getCodigoTipoActividad());
            ps.setString(2, tipoTarea.getCodigo());
            ps.setString(3, tipoTarea.getDenominacion());
            ps.setString(4, tipoTarea.getDescripcion());
            ps.setBoolean(5, tipoTarea.isUsaApiar());
            ps.setBoolean(6, tipoTarea.isUsaFamilia());
            ps.setBoolean(7, tipoTarea.isUsaCajon());
            ps.setBoolean(8, tipoTarea.isUsaTratamiento());
            ps.setBoolean(9, tipoTarea.isUsaEnfermedad());
            ps.setBoolean(10, tipoTarea.isUsaSintoma());
            ps.setBoolean(11, tipoTarea.isUsaSintoma());
            ps.setBoolean(12, tipoTarea.isUsaLayout());
            ps.setBoolean(13, tipoTarea.isUsaZona());
            ps.setBoolean(14, tipoTarea.isUsaColmena());
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoTarea! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deleteUno(String codTipoActividad) throws Exception {
        int resultado = 0;
        try {
            // Primero elimino los datos de recoleccion relacionados
            GestorDatoDeRecoleccion gestorDatRec = new GestorDatoDeRecoleccion();
            gestorDatRec.deleteUno(codTipoActividad);

            // Ahora elimino los tipos de tarea relacionados
            conn = connPool.getConnection();
            String sql = " delete from tipotarea where codTipoActividad = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, codTipoActividad);
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoTarea! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        ArrayList<TipoTarea> tipoTarea = (ArrayList) object;
        int resultado = 0;
        try {
            // Primero actualizo los datos de datos de recoleccion relacionados
            GestorDatoDeRecoleccion gestorDatRec = new GestorDatoDeRecoleccion();
            for (int i = 0; i < tipoTarea.size(); i++) {
                gestorDatRec.updateUno(tipoTarea.get(i).getDetalleTipoTarea());
            }
            // Ahora actualizo los tipos de tarea
            conn = connPool.getConnection();
            PreparedStatement ps = null;
            for (int i = 0; i < tipoTarea.size(); i++) {
                String sql = " update tipotarea set     " +
                        "  denominacion = ?,       " +
                        "  descripcion = ?,        " +
                        "  usaApiar = ?,           " +
                        "  usaFamilia = ?,         " +
                        "  usaCajon = ?,           " +
                        "  usaTratamiento = ?,     " +
                        "  usaEnfermedad = ?,      " +
                        "  usaSintoma = ?,         " +
                        "  usaLayout = ?,          " +
                        "  usaColmena = ?,         " +
                        "  usaZona = ?,            " +
                        "  usaAlarma = ?           " +
                        "  where codtipotarea = ?  " +
                        "  and codtipoactividad = ?  ";

                ps = conn.prepareStatement(sql);
                // Atributos a actualizar
                ps.setString(1, tipoTarea.get(i).getDenominacion());
                ps.setString(2, tipoTarea.get(i).getDescripcion());
                ps.setBoolean(3, tipoTarea.get(i).isUsaApiar());
                ps.setBoolean(4, tipoTarea.get(i).isUsaFamilia());
                ps.setBoolean(5, tipoTarea.get(i).isUsaCajon());
                ps.setBoolean(6, tipoTarea.get(i).isUsaTratamiento());
                ps.setBoolean(7, tipoTarea.get(i).isUsaEnfermedad());
                ps.setBoolean(8, tipoTarea.get(i).isUsaSintoma());
                ps.setBoolean(9, tipoTarea.get(i).isUsaLayout());
                ps.setBoolean(10, tipoTarea.get(i).isUsaColmena());
                ps.setBoolean(11, tipoTarea.get(i).isUsaZona());
                ps.setBoolean(12, tipoTarea.get(i).isUsaAlarma());

                // tipo de tarea a actualizar
                ps.setString(13, tipoTarea.get(i).getCodigo());
                ps.setString(14, tipoTarea.get(i).getCodigoTipoActividad());
                resultado = ps.executeUpdate();
                if (i == tipoTarea.size()) {
                    ps.close();
                }
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoTarea! (updateUno)");
        }
        return resultado;
    }
}

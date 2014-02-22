/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.actividad.DatoDeRecoleccion;
import ibeeproject.model.actividad.TipoDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorDatoDeRecoleccion implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorDatoDeRecoleccion() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getTodos(String codTipoActividad, String codTipoTarea) {
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * FROM DatoDeRecoleccion " +
                    " WHERE codtipoactividad = ? " +
                    " AND codtipotarea = ? " +
                    " ORDER BY 1 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, codTipoActividad);
            ps.setString(2, codTipoTarea);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                DatoDeRecoleccion datoDeRecoleccion = new DatoDeRecoleccion();
                datoDeRecoleccion.setCodigo(rs.getString("codDatoDeRecoleccion"));
                datoDeRecoleccion.setCodigoTipoTarea(rs.getString("codTipoTarea"));
                datoDeRecoleccion.setCodigoTipoActividad(rs.getString("codTipoActividad"));
                datoDeRecoleccion.setNombreDato(rs.getString("nombreDato"));
                datoDeRecoleccion.setValoresPermitidos(rs.getString("valoresPermitidos"));
                datoDeRecoleccion.setExtension(rs.getString("extension"));

                //Reconstruyo el tipo de dato
                GestorTipoDato gestorTipoDato = new GestorTipoDato();
                TipoDato tipoDato = (TipoDato) gestorTipoDato.getUno(rs.getInt("idTipoDato"));
                datoDeRecoleccion.setTipoDato(tipoDato);

                list.add(datoDeRecoleccion);
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

    public int insertUno(Object object) throws Exception {
        DatoDeRecoleccion datoDeRecoleccion = (DatoDeRecoleccion) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " insert into datoderecoleccion " +
                    " (coddatoderecoleccion, codtipotarea,  " +
                    " codtipoactividad, nombredato, idtipodato, " +
                    " valorespermitidos) values (?,?,?,?,?,?) ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, datoDeRecoleccion.getCodigo());
            ps.setString(2, datoDeRecoleccion.getCodigoTipoTarea());
            ps.setString(3, datoDeRecoleccion.getCodigoTipoActividad());
            ps.setString(4, datoDeRecoleccion.getNombreDato());
            ps.setInt(5, datoDeRecoleccion.getTipoDato().getNumero());
            ps.setString(6, datoDeRecoleccion.getValoresPermitidos());
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorDatoDeRecoleccion! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deleteUno(String codTipoActividad) throws Exception {
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " delete from DatoDeRecoleccion where codTipoActividad = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, codTipoActividad);
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorDatoDeRecoleccion! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        ArrayList<DatoDeRecoleccion> datoDeRecoleccion = (ArrayList) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            PreparedStatement ps = null;
            for (int i = 0; i < datoDeRecoleccion.size(); i++) {
                String sql = " update datoderecoleccion set   " +
                        "  nombreDato = ?,                    " +
                        "  idtipodato = ?,                    " +
                        "  valorespermitidos = ?              " +
                        "  where coddatoderecoleccion = ?     " +
                        "  and codtipotarea = ?               " +
                        "  and codtipoactividad = ?           ";

                ps = conn.prepareStatement(sql);
                // Atributos a actualizar
                ps.setString(1, datoDeRecoleccion.get(i).getNombreDato());
                ps.setInt(2, datoDeRecoleccion.get(i).getTipoDato().getNumero());
                ps.setString(3, datoDeRecoleccion.get(i).getValoresPermitidos());

                // dato de recoleccion a actualizar
                ps.setString(4, datoDeRecoleccion.get(i).getCodigo());
                ps.setString(5, datoDeRecoleccion.get(i).getCodigoTipoTarea());
                ps.setString(6, datoDeRecoleccion.get(i).getCodigoTipoActividad());
                resultado = ps.executeUpdate();
                if (i == datoDeRecoleccion.size()) {
                    ps.close();
                }
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorDatoDeRecoleccion! (updateUno)");
        }
        return resultado;
    }
}

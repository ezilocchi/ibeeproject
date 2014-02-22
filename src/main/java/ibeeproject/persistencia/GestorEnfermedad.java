/*
 * GestorEnfermedad.java
 *
 * Created on 01-jul-2009, 19:36:04
 * Copyright farias.facundo
 */
package ibeeproject.persistencia;

import ibeeproject.model.enfermedad.Criticidad;
import ibeeproject.model.enfermedad.Enfermedad;
import ibeeproject.model.enfermedad.Sintoma;
import ibeeproject.model.enfermedad.Tratamiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class GestorEnfermedad implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorEnfermedad() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() {
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct * FROM enfermedad ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto enfermedad
                Enfermedad enfermedad = new Enfermedad();
                enfermedad.setNumero(rs.getInt("idEnfermedad"));
                enfermedad.setDenominacion(rs.getString("denominacion"));
                enfermedad.setDescripcion(rs.getString("descripcion"));
                enfermedad.setDuracionAprox(rs.getString("duracionAproximada"));
                enfermedad.setObservaciones(rs.getString("observaciones"));

                GestorCriticidad gestorCriticidad = new GestorCriticidad();
                Criticidad criticidad = (Criticidad) gestorCriticidad.getUno(rs.getInt("idCriticidad"));
                enfermedad.setCriticidad(criticidad);

                //Regenero los Sintomas
                sql = " SELECT idSintoma from sintomaporenfermedad where idEnfermedad = ?";
                PreparedStatement ps1 = conn.prepareStatement(sql);
                ps1.setInt(1, enfermedad.getNumero());
                ResultSet rs1 = ps1.executeQuery();
                GestorSintoma gestorSintoma = new GestorSintoma();
                while (rs1.next()) {
                    Sintoma sintoma = (Sintoma) gestorSintoma.getUno(rs1.getInt("idSintoma"));
                    if (sintoma != null)
                        enfermedad.getSintomas().add(sintoma);
                }

                //Regenero los Tratamientos
                sql = " SELECT idTratamiento from tratamientosporenfermedad where idEnfermedad = ?";
                ps1 = conn.prepareStatement(sql);
                ps1.setInt(1, enfermedad.getNumero());
                rs1 = ps1.executeQuery();
                GestorTratamiento gestorTratamiento = new GestorTratamiento();
                while (rs1.next()) {
                    Tratamiento tratamiento = (Tratamiento) gestorTratamiento.getUno(rs1.getInt("idTratamiento"));
                    if (tratamiento != null)
                        enfermedad.getTratamientos().add(tratamiento);
                }
                ps1.close();
                list.add(enfermedad);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEnfermedad !!! (getTodos)");
        }
        return list;
    }

    public Object getUltimo() {
        Enfermedad enfermedad = new Enfermedad();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Enfermedad WHERE idEnfermedad = " +
                    " (select MAX(idEnfermedad) from Enfermedad)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                enfermedad.setNumero(rs.getInt("idEnfermedad"));
                enfermedad.setDenominacion(rs.getString("denominacion"));
                enfermedad.setDuracionAprox(rs.getString("duracionAproximada"));
                enfermedad.setDescripcion(rs.getString("descripcion"));
                enfermedad.setObservaciones(rs.getString("observaciones"));

                // Reconstruyo el objeto criticidad
                GestorCriticidad gestorCriticidad = new GestorCriticidad();
                Criticidad criticidad = (Criticidad) gestorCriticidad.getUno(rs.getInt("idCriticidad"));
                enfermedad.setCriticidad(criticidad);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEnfermedad !!! (getUltimo)");
        }
        return enfermedad;
    }

    public ArrayList getAsignado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) {
        Enfermedad enfermedad = new Enfermedad();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT * FROM Enfermedad WHERE idEnfermedad = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                enfermedad.setNumero(rs.getInt("idEnfermedad"));
                enfermedad.setDenominacion(rs.getString("denominacion"));
                enfermedad.setDuracionAprox(rs.getString("duracionAproximada"));
                enfermedad.setDescripcion(rs.getString("descripcion"));
                enfermedad.setObservaciones(rs.getString("observaciones"));

                // Reconstruyo el objeto criticidad
                GestorCriticidad gestorCriticidad = new GestorCriticidad();
                Criticidad criticidad = (Criticidad) gestorCriticidad.getUno(rs.getInt("idCriticidad"));
                enfermedad.setCriticidad(criticidad);

                // Regenero los Sintomas
                sql = " SELECT idSintoma from sintomaporenfermedad where idEnfermedad = ?";
                PreparedStatement ps1 = conn.prepareStatement(sql);
                ps1.setInt(1, enfermedad.getNumero());
                ResultSet rs1 = ps1.executeQuery();
                GestorSintoma gestorSintoma = new GestorSintoma();
                while (rs1.next()) {
                    Sintoma sintoma = (Sintoma) gestorSintoma.getUno(rs1.getInt("idSintoma"));
                    if (sintoma != null)
                        enfermedad.getSintomas().add(sintoma);
                }

                // Regenero los Tratamientos
                sql = " SELECT idTratamiento from tratamientosporenfermedad where idEnfermedad = ?";
                ps1 = conn.prepareStatement(sql);
                ps1.setInt(1, enfermedad.getNumero());
                rs1 = ps1.executeQuery();
                GestorTratamiento gestorTratamiento = new GestorTratamiento();
                while (rs1.next()) {
                    Tratamiento tratamiento = (Tratamiento) gestorTratamiento.getUno(rs1.getInt("idTratamiento"));
                    if (tratamiento != null)
                        enfermedad.getTratamientos().add(tratamiento);
                }
                ps1.close();
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEnfermedad !!! (getUno)");
        }
        return enfermedad;
    }

    public int insertUno(Object object) throws Exception {
        Enfermedad enfermedad = (Enfermedad) object;
        int resultado = 0;
        try {
            // Inserto la enfermedad
            conn = connPool.getConnection();
            String sql = "insert into enfermedad(idEnfermedad, denominacion, " +
                    "descripcion, duracionAproximada, observaciones, idCriticidad) " +
                    " values (?,?,?,?,?,?)";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, enfermedad.getNumero());
            ps.setString(2, enfermedad.getDenominacion());
            ps.setString(3, enfermedad.getDescripcion());
            ps.setString(4, enfermedad.getDuracionAprox());
            ps.setString(5, enfermedad.getObservaciones());
            ps.setInt(6, enfermedad.getCriticidad().getNumero());
            resultado = ps.executeUpdate();

            // Inserto los sintomas
            sql = " insert into sintomaporenfermedad (idEnfermedad, idSintoma) values (?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, enfermedad.getNumero());
            for (int i = 0; i < enfermedad.getSintomas().size(); i++) {
                ps.setInt(2, enfermedad.getSintomas().get(i).getNumero());
                resultado = ps.executeUpdate();
            }

            // Inserto los tratamientos
            sql = " insert into tratamientosporenfermedad (idEnfermedad, idTratamiento) values (?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, enfermedad.getNumero());
            for (int i = 0; i < enfermedad.getTratamientos().size(); i++) {
                ps.setInt(2, enfermedad.getTratamientos().get(i).getNumero());
                resultado = ps.executeUpdate();
            }

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEnfermedad !!! (insertUno)");
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        Enfermedad enfermedad = (Enfermedad) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            // Elimino los sintomas asociados
            String sql = " delete from sintomaporenfermedad where idEnfermedad = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, enfermedad.getNumero());
            resultado = ps.executeUpdate();

            // Elimino los tratamientos asociados
            sql = " delete from tratamientosporenfermedad where idEnfermedad = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, enfermedad.getNumero());
            resultado = ps.executeUpdate();

            // Finalmente, elimino la enfermedad
            sql = " delete from enfermedad where idEnfermedad = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, enfermedad.getNumero());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEnfermedad !!! (deleteUno)");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        Enfermedad enfermedad = (Enfermedad) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            // Elimino los sintomas asociados
            String sql = " delete from sintomaporenfermedad where idEnfermedad = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, enfermedad.getNumero());
            resultado = ps.executeUpdate();

            //Inserto los sintomas
            sql = " insert into sintomaporenfermedad (idEnfermedad, idSintoma) values (?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, enfermedad.getNumero());
            for (int i = 0; i < enfermedad.getSintomas().size(); i++) {
                ps.setInt(2, enfermedad.getSintomas().get(i).getNumero());
                resultado = ps.executeUpdate();
            }

            // Elimino los tratamientos asociados
            sql = " delete from tratamientosporenfermedad where idEnfermedad = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, enfermedad.getNumero());
            resultado = ps.executeUpdate();
            //Inserto los tratamientos
            sql = " insert into tratamientosporenfermedad (idEnfermedad, idTratamiento) values (?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, enfermedad.getNumero());
            for (int i = 0; i < enfermedad.getTratamientos().size(); i++) {
                ps.setInt(2, enfermedad.getTratamientos().get(i).getNumero());
                resultado = ps.executeUpdate();
            }

            // Ahora si, actualizo la enfermedad
            sql = " update enfermedad set " +
                    " denominacion = ? , " +
                    " descripcion = ? , " +
                    " duracionAproximada = ? , " +
                    " observaciones = ? , " +
                    " idCriticidad = ? " +
                    " WHERE idEnfermedad = ? ";
            ps = conn.prepareStatement(sql);

            // Atributos a actualizar
            ps.setString(1, enfermedad.getDenominacion());
            ps.setString(2, enfermedad.getDescripcion());
            ps.setString(3, enfermedad.getDuracionAprox());
            ps.setString(4, enfermedad.getObservaciones());
            ps.setInt(5, enfermedad.getCriticidad().getNumero());

            // Enfermedad a actualizar
            ps.setInt(6, enfermedad.getNumero());
            resultado = ps.executeUpdate();

            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorEnfermedad !!! (updateUno)");
        }
        return resultado;
    }
}

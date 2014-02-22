/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.actividad.EstadoTarea;
import ibeeproject.model.actividad.Tarea;
import ibeeproject.model.actividad.TipoTarea;
import ibeeproject.model.apiar.Apiar;
import ibeeproject.model.apiar.Colmena;
import ibeeproject.model.apiar.Layout;
import ibeeproject.model.cajon.Cajon;
import ibeeproject.model.enfermedad.Enfermedad;
import ibeeproject.model.enfermedad.Sintoma;
import ibeeproject.model.enfermedad.Tratamiento;
import ibeeproject.model.familia.Familia;
import ibeeproject.model.persona.Empleado;
import ibeeproject.model.soporte.UtilFecha;
import ibeeproject.model.zona.Alarma;
import ibeeproject.model.zona.Zona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author farias.facundo
 */
public class GestorTarea implements Manejable {

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;

    public GestorTarea() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodosConFiltro(String sqlWhere, int idEmpleado) throws Exception {
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;
            String sql = " SELECT * FROM tarea " +
                    sqlWhere +
                    " AND legajoempleado = ? " +
                    " ORDER BY fechaPrevista DESC ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idEmpleado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea();

                tarea.setIdTarea(rs.getInt("idTarea"));
                tarea.setIdActividad(rs.getInt("idActividad"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setObservaciones(rs.getString("observaciones"));
                tarea.setFechaPrevista(UtilFecha.convertiFecha(rs.getDate("fechaPrevista")));
                tarea.setFechaRealizacion(UtilFecha.convertiFecha(rs.getDate("fechaRealizacion")));
                tarea.setDescripcionRealizacion(rs.getString("descripcionRealizacion"));
                tarea.setCantidadHoras(rs.getInt("cantidadHoras"));
                tarea.setCantidadMiel(rs.getDouble("cantidadMiel"));

                // Reconstruyo el Tipo de Tarea
                GestorTipoTarea gestorTipoTarea = new GestorTipoTarea();
                TipoTarea tipoTarea = (TipoTarea) gestorTipoTarea.getUno(rs.getString("codTipoActividad"), rs.getString("codTipoTarea"));
                tarea.setTipoTarea(tipoTarea);

                // Reconstruyo Estado de la Tarea
                GestorEstadoTarea gestorEstadoTarea = new GestorEstadoTarea();
                EstadoTarea estadoTarea = (EstadoTarea) gestorEstadoTarea.getUno(rs.getInt("idEstadoTarea"));
                tarea.setEstado(estadoTarea);

                // Reconstruyo el Empleado
                if (rs.getInt("legajoEmpleado") != 0) {
                    GestorEmpleado gestorEmpleado = new GestorEmpleado();
                    Empleado empleado = (Empleado) gestorEmpleado.getUno(rs.getInt("legajoEmpleado"));
                    tarea.setEmpleado(empleado);
                }

                // Reconstruyo el Cajon
                if (rs.getInt("idCajon") != 0) {
                    GestorCajon gestorCajon = new GestorCajon();
                    Cajon cajon = (Cajon) gestorCajon.getUno(rs.getInt("idCajon"));
                    tarea.setCajon(cajon);
                }

                // Reconstruyo la familia
                if (rs.getInt("idFamilia") != 0) {
                    GestorFamilia gestorFamilia = new GestorFamilia();
                    Familia familia = (Familia) gestorFamilia.getUno(rs.getInt("idFamilia"));
                    tarea.setFamilia(familia);
                }

                // Reconstruyo la colmena
                if (rs.getInt("idColmena") != 0) {
                    GestorColmena gestorColmena = new GestorColmena();
                    Colmena colmena = (Colmena) gestorColmena.getUno(rs.getInt("idColmena"));
                    tarea.setColmena(colmena);
                }

                // Reconstruyo el apiar
                if (rs.getInt("idApiar") != 0) {
                    GestorApiar gestorApiar = new GestorApiar();
                    Apiar apiar = (Apiar) gestorApiar.getUno(rs.getInt("idApiar"));
                    tarea.setApiar(apiar);
                }

                // Reconstruyo el layout
                if (rs.getInt("idLayout") != 0) {
                    GestorLayout gestorLayout = new GestorLayout();
                    Layout layout = (Layout) gestorLayout.getUno(rs.getInt("idLayout"));
                    tarea.setLayout(layout);
                }

                // Reconstruyo la zona
                if (rs.getInt("idZona") != 0) {
                    GestorZona gestorZona = new GestorZona();
                    Zona zona = (Zona) gestorZona.getUno(rs.getInt("idZona"));
                    tarea.setZona(zona);
                }

                // Reconstruyo el sintoma
                if (rs.getInt("idSintoma") != 0) {
                    GestorSintoma gestorSintoma = new GestorSintoma();
                    Sintoma sintoma = (Sintoma) gestorSintoma.getUno(rs.getInt("idSintoma"));
                    tarea.setSintoma(sintoma);
                }

                // Reconstruyo el tratamientos
                if (rs.getInt("idTratamiento") != 0) {
                    GestorTratamiento gestorTratamiento = new GestorTratamiento();
                    Tratamiento tratamiento = (Tratamiento) gestorTratamiento.getUno(rs.getInt("idTratamiento"));
                    tarea.setTratamiento(tratamiento);
                }

                // Reconstruyo la enfermedad
                if (rs.getInt("idEnfermedad") != 0) {
                    GestorEnfermedad gestorEnfermedad = new GestorEnfermedad();
                    Enfermedad enfermedad = (Enfermedad) gestorEnfermedad.getUno(rs.getInt("idEnfermedad"));
                    tarea.setEnfermedad(enfermedad);
                }

                // Reconstruyo la alarma
                if (rs.getInt("idAlarma") != 0) {
                    GestorAlarma gestorAlarma = new GestorAlarma();
                    Alarma alarma = (Alarma) gestorAlarma.getUno(rs.getInt("idAlarma"));
                    tarea.setAlarma(alarma);
                }

                list.add(tarea);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTarea! (getTodos())");
        }
        return list;
    }

    public ArrayList getTodos() throws Exception {
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;
            String sql = "SELECT * FROM tarea " +
                    " ORDER BY fechaPrevista DESC ";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea();

                tarea.setIdTarea(rs.getInt("idTarea"));
                tarea.setIdActividad(rs.getInt("idActividad"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setObservaciones(rs.getString("observaciones"));
                tarea.setFechaPrevista(UtilFecha.convertiFecha(rs.getDate("fechaPrevista")));
                tarea.setFechaRealizacion(UtilFecha.convertiFecha(rs.getDate("fechaRealizacion")));
                tarea.setDescripcionRealizacion(rs.getString("descripcionRealizacion"));
                tarea.setCantidadHoras(rs.getInt("cantidadHoras"));
                tarea.setCantidadMiel(rs.getDouble("cantidadMiel"));

                // Reconstruyo el Tipo de Tarea
                GestorTipoTarea gestorTipoTarea = new GestorTipoTarea();
                TipoTarea tipoTarea = (TipoTarea) gestorTipoTarea.getUno(rs.getString("codTipoActividad"), rs.getString("codTipoTarea"));
                tarea.setTipoTarea(tipoTarea);

                // Reconstruyo Estado de la Tarea
                GestorEstadoTarea gestorEstadoTarea = new GestorEstadoTarea();
                EstadoTarea estadoTarea = (EstadoTarea) gestorEstadoTarea.getUno(rs.getInt("idEstadoTarea"));
                tarea.setEstado(estadoTarea);

                // Reconstruyo el Empleado
                if (rs.getInt("legajoEmpleado") != 0) {
                    GestorEmpleado gestorEmpleado = new GestorEmpleado();
                    Empleado empleado = (Empleado) gestorEmpleado.getUno(rs.getInt("legajoEmpleado"));
                    tarea.setEmpleado(empleado);
                }

                // Reconstruyo el Cajon
                if (rs.getInt("idCajon") != 0) {
                    GestorCajon gestorCajon = new GestorCajon();
                    Cajon cajon = (Cajon) gestorCajon.getUno(rs.getInt("idCajon"));
                    tarea.setCajon(cajon);
                }

                // Reconstruyo la familia
                if (rs.getInt("idFamilia") != 0) {
                    GestorFamilia gestorFamilia = new GestorFamilia();
                    Familia familia = (Familia) gestorFamilia.getUno(rs.getInt("idFamilia"));
                    tarea.setFamilia(familia);
                }

                // Reconstruyo la colmena
                if (rs.getInt("idColmena") != 0) {
                    GestorColmena gestorColmena = new GestorColmena();
                    Colmena colmena = (Colmena) gestorColmena.getUno(rs.getInt("idColmena"));
                    tarea.setColmena(colmena);
                }

                // Reconstruyo el apiar
                if (rs.getInt("idApiar") != 0) {
                    GestorApiar gestorApiar = new GestorApiar();
                    Apiar apiar = (Apiar) gestorApiar.getUno(rs.getInt("idApiar"));
                    tarea.setApiar(apiar);
                }

                // Reconstruyo el layout
                if (rs.getInt("idLayout") != 0) {
                    GestorLayout gestorLayout = new GestorLayout();
                    Layout layout = (Layout) gestorLayout.getUno(rs.getInt("idLayout"));
                    tarea.setLayout(layout);
                }

                // Reconstruyo la zona
                if (rs.getInt("idZona") != 0) {
                    GestorZona gestorZona = new GestorZona();
                    Zona zona = (Zona) gestorZona.getUno(rs.getInt("idZona"));
                    tarea.setZona(zona);
                }

                // Reconstruyo el sintoma
                if (rs.getInt("idSintoma") != 0) {
                    GestorSintoma gestorSintoma = new GestorSintoma();
                    Sintoma sintoma = (Sintoma) gestorSintoma.getUno(rs.getInt("idSintoma"));
                    tarea.setSintoma(sintoma);
                }

                // Reconstruyo el tratamientos
                if (rs.getInt("idTratamiento") != 0) {
                    GestorTratamiento gestorTratamiento = new GestorTratamiento();
                    Tratamiento tratamiento = (Tratamiento) gestorTratamiento.getUno(rs.getInt("idTratamiento"));
                    tarea.setTratamiento(tratamiento);
                }

                // Reconstruyo la enfermedad
                if (rs.getInt("idEnfermedad") != 0) {
                    GestorEnfermedad gestorEnfermedad = new GestorEnfermedad();
                    Enfermedad enfermedad = (Enfermedad) gestorEnfermedad.getUno(rs.getInt("idEnfermedad"));
                    tarea.setEnfermedad(enfermedad);
                }

                // Reconstruyo la alarma
                if (rs.getInt("idAlarma") != 0) {
                    GestorAlarma gestorAlarma = new GestorAlarma();
                    Alarma alarma = (Alarma) gestorAlarma.getUno(rs.getInt("idAlarma"));
                    tarea.setAlarma(alarma);
                }

                list.add(tarea);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTarea! (getTodos())");
        }
        return list;
    }

    public ArrayList getTodosUser(int idEmpleado) throws Exception {
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;
            String sql = "SELECT * FROM tarea " +
                    " WHERE legajoempleado = ? " +
                    " ORDER BY fechaPrevista DESC ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idEmpleado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea();

                tarea.setIdTarea(rs.getInt("idTarea"));
                tarea.setIdActividad(rs.getInt("idActividad"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setObservaciones(rs.getString("observaciones"));
                tarea.setFechaPrevista(UtilFecha.convertiFecha(rs.getDate("fechaPrevista")));
                tarea.setFechaRealizacion(UtilFecha.convertiFecha(rs.getDate("fechaRealizacion")));
                tarea.setDescripcionRealizacion(rs.getString("descripcionRealizacion"));
                tarea.setCantidadHoras(rs.getInt("cantidadHoras"));
                tarea.setCantidadMiel(rs.getDouble("cantidadMiel"));

                // Reconstruyo el Tipo de Tarea
                GestorTipoTarea gestorTipoTarea = new GestorTipoTarea();
                TipoTarea tipoTarea = (TipoTarea) gestorTipoTarea.getUno(rs.getString("codTipoActividad"), rs.getString("codTipoTarea"));
                tarea.setTipoTarea(tipoTarea);

                // Reconstruyo Estado de la Tarea
                GestorEstadoTarea gestorEstadoTarea = new GestorEstadoTarea();
                EstadoTarea estadoTarea = (EstadoTarea) gestorEstadoTarea.getUno(rs.getInt("idEstadoTarea"));
                tarea.setEstado(estadoTarea);

                // Reconstruyo el Empleado
                if (rs.getInt("legajoEmpleado") != 0) {
                    GestorEmpleado gestorEmpleado = new GestorEmpleado();
                    Empleado empleado = (Empleado) gestorEmpleado.getUno(rs.getInt("legajoEmpleado"));
                    tarea.setEmpleado(empleado);
                }

                // Reconstruyo el Cajon
                if (rs.getInt("idCajon") != 0) {
                    GestorCajon gestorCajon = new GestorCajon();
                    Cajon cajon = (Cajon) gestorCajon.getUno(rs.getInt("idCajon"));
                    tarea.setCajon(cajon);
                }

                // Reconstruyo la familia
                if (rs.getInt("idFamilia") != 0) {
                    GestorFamilia gestorFamilia = new GestorFamilia();
                    Familia familia = (Familia) gestorFamilia.getUno(rs.getInt("idFamilia"));
                    tarea.setFamilia(familia);
                }

                // Reconstruyo la colmena
                if (rs.getInt("idColmena") != 0) {
                    GestorColmena gestorColmena = new GestorColmena();
                    Colmena colmena = (Colmena) gestorColmena.getUno(rs.getInt("idColmena"));
                    tarea.setColmena(colmena);
                }

                // Reconstruyo el apiar
                if (rs.getInt("idApiar") != 0) {
                    GestorApiar gestorApiar = new GestorApiar();
                    Apiar apiar = (Apiar) gestorApiar.getUno(rs.getInt("idApiar"));
                    tarea.setApiar(apiar);
                }

                // Reconstruyo el layout
                if (rs.getInt("idLayout") != 0) {
                    GestorLayout gestorLayout = new GestorLayout();
                    Layout layout = (Layout) gestorLayout.getUno(rs.getInt("idLayout"));
                    tarea.setLayout(layout);
                }

                // Reconstruyo la zona
                if (rs.getInt("idZona") != 0) {
                    GestorZona gestorZona = new GestorZona();
                    Zona zona = (Zona) gestorZona.getUno(rs.getInt("idZona"));
                    tarea.setZona(zona);
                }

                // Reconstruyo el sintoma
                if (rs.getInt("idSintoma") != 0) {
                    GestorSintoma gestorSintoma = new GestorSintoma();
                    Sintoma sintoma = (Sintoma) gestorSintoma.getUno(rs.getInt("idSintoma"));
                    tarea.setSintoma(sintoma);
                }

                // Reconstruyo el tratamientos
                if (rs.getInt("idTratamiento") != 0) {
                    GestorTratamiento gestorTratamiento = new GestorTratamiento();
                    Tratamiento tratamiento = (Tratamiento) gestorTratamiento.getUno(rs.getInt("idTratamiento"));
                    tarea.setTratamiento(tratamiento);
                }

                // Reconstruyo la enfermedad
                if (rs.getInt("idEnfermedad") != 0) {
                    GestorEnfermedad gestorEnfermedad = new GestorEnfermedad();
                    Enfermedad enfermedad = (Enfermedad) gestorEnfermedad.getUno(rs.getInt("idEnfermedad"));
                    tarea.setEnfermedad(enfermedad);
                }

                // Reconstruyo la alarma
                if (rs.getInt("idAlarma") != 0) {
                    GestorAlarma gestorAlarma = new GestorAlarma();
                    Alarma alarma = (Alarma) gestorAlarma.getUno(rs.getInt("idAlarma"));
                    tarea.setAlarma(alarma);
                }

                list.add(tarea);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTarea! (getTodos())");
        }
        return list;
    }

    public ArrayList getTodos(int idActividad) throws Exception {
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;
            String sql = "SELECT * FROM tarea " +
                    " WHERE idActividad = ? " +
                    " ORDER BY fechaPrevista DESC ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idActividad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea();

                tarea.setIdTarea(rs.getInt("idTarea"));
                tarea.setIdActividad(idActividad);
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setObservaciones(rs.getString("observaciones"));
                tarea.setFechaPrevista(UtilFecha.convertiFecha(rs.getDate("fechaPrevista")));
                tarea.setFechaRealizacion(UtilFecha.convertiFecha(rs.getDate("fechaRealizacion")));
                tarea.setDescripcionRealizacion(rs.getString("descripcionRealizacion"));
                tarea.setCantidadHoras(rs.getInt("cantidadHoras"));
                tarea.setCantidadMiel(rs.getDouble("cantidadMiel"));

                // Reconstruyo el Tipo de Tarea
                GestorTipoTarea gestorTipoTarea = new GestorTipoTarea();
                TipoTarea tipoTarea = (TipoTarea) gestorTipoTarea.getUno(rs.getString("codTipoActividad"), rs.getString("codTipoTarea"));
                tarea.setTipoTarea(tipoTarea);

                // Reconstruyo Estado de la Tarea
                GestorEstadoTarea gestorEstadoTarea = new GestorEstadoTarea();
                EstadoTarea estadoTarea = (EstadoTarea) gestorEstadoTarea.getUno(rs.getInt("idEstadoTarea"));
                tarea.setEstado(estadoTarea);

                // Reconstruyo el Empleado
                if (rs.getInt("legajoEmpleado") != 0) {
                    GestorEmpleado gestorEmpleado = new GestorEmpleado();
                    Empleado empleado = (Empleado) gestorEmpleado.getUno(rs.getInt("legajoEmpleado"));
                    tarea.setEmpleado(empleado);
                }

                // Reconstruyo el Cajon
                if (rs.getInt("idCajon") != 0) {
                    GestorCajon gestorCajon = new GestorCajon();
                    Cajon cajon = (Cajon) gestorCajon.getUno(rs.getInt("idCajon"));
                    tarea.setCajon(cajon);
                }

                // Reconstruyo la familia
                if (rs.getInt("idFamilia") != 0) {
                    GestorFamilia gestorFamilia = new GestorFamilia();
                    Familia familia = (Familia) gestorFamilia.getUno(rs.getInt("idFamilia"));
                    tarea.setFamilia(familia);
                }

                // Reconstruyo la colmena
                if (rs.getInt("idColmena") != 0) {
                    GestorColmena gestorColmena = new GestorColmena();
                    Colmena colmena = (Colmena) gestorColmena.getUno(rs.getInt("idColmena"));
                    tarea.setColmena(colmena);
                }

                // Reconstruyo el apiar
                if (rs.getInt("idApiar") != 0) {
                    GestorApiar gestorApiar = new GestorApiar();
                    Apiar apiar = (Apiar) gestorApiar.getUno(rs.getInt("idApiar"));
                    tarea.setApiar(apiar);
                }

                // Reconstruyo el layout
                if (rs.getInt("idLayout") != 0) {
                    GestorLayout gestorLayout = new GestorLayout();
                    Layout layout = (Layout) gestorLayout.getUno(rs.getInt("idLayout"));
                    tarea.setLayout(layout);
                }

                // Reconstruyo la zona
                if (rs.getInt("idZona") != 0) {
                    GestorZona gestorZona = new GestorZona();
                    Zona zona = (Zona) gestorZona.getUno(rs.getInt("idZona"));
                    tarea.setZona(zona);
                }

                // Reconstruyo el sintoma
                if (rs.getInt("idSintoma") != 0) {
                    GestorSintoma gestorSintoma = new GestorSintoma();
                    Sintoma sintoma = (Sintoma) gestorSintoma.getUno(rs.getInt("idSintoma"));
                    tarea.setSintoma(sintoma);
                }

                // Reconstruyo el tratamientos
                if (rs.getInt("idTratamiento") != 0) {
                    GestorTratamiento gestorTratamiento = new GestorTratamiento();
                    Tratamiento tratamiento = (Tratamiento) gestorTratamiento.getUno(rs.getInt("idTratamiento"));
                    tarea.setTratamiento(tratamiento);
                }

                // Reconstruyo la enfermedad
                if (rs.getInt("idEnfermedad") != 0) {
                    GestorEnfermedad gestorEnfermedad = new GestorEnfermedad();
                    Enfermedad enfermedad = (Enfermedad) gestorEnfermedad.getUno(rs.getInt("idEnfermedad"));
                    tarea.setEnfermedad(enfermedad);
                }

                // Reconstruyo la alarma
                if (rs.getInt("idAlarma") != 0) {
                    GestorAlarma gestorAlarma = new GestorAlarma();
                    Alarma alarma = (Alarma) gestorAlarma.getUno(rs.getInt("idAlarma"));
                    tarea.setAlarma(alarma);
                }

                list.add(tarea);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTarea! (getTodos(idActividad))");
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
        Tarea tarea = (Tarea) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            String sql = " insert into tarea (idActividad, idTarea, codTipoActividad, codTipoTarea, " +
                    " descripcion, idEstadoTarea, fechaPrevista, observaciones, legajoEmpleado, " +
                    " idColmena, idFamilia, idCajon, idSintoma, idTratamiento, " +
                    " idEnfermedad, idApiar, idLayout, idZona, idAlarma ) " +
                    " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tarea.getIdActividad());
            ps.setInt(2, tarea.getIdTarea());
            ps.setString(3, tarea.getTipoTarea().getCodigoTipoActividad());
            ps.setString(4, tarea.getTipoTarea().getCodigo());
            ps.setString(5, tarea.getDescripcion());
            ps.setInt(6, tarea.getEstado().getNumero());
            ps.setDate(7, UtilFecha.convertiFecha(tarea.getFechaPrevista()));
            ps.setString(8, tarea.getObservaciones());
            ps.setInt(9, (int) tarea.getEmpleado().getLegajo());

            // Colmena
            if (tarea.getTipoTarea().isUsaColmena()) {
                ps.setInt(10, tarea.getColmena().getIdColmena());
            } else {
                ps.setNull(10, java.sql.Types.NUMERIC);
            }

            // Familia
            if (tarea.getTipoTarea().isUsaFamilia()) {
                ps.setInt(11, tarea.getFamilia().getNroFamilia());
            } else {
                ps.setNull(11, java.sql.Types.NUMERIC);
            }

            // Cajon
            if (tarea.getTipoTarea().isUsaCajon()) {
                ps.setInt(12, tarea.getCajon().getNroCajon());
            } else {
                ps.setNull(12, java.sql.Types.NUMERIC);
            }

            // Sintoma
            if (tarea.getTipoTarea().isUsaSintoma()) {
                ps.setInt(13, tarea.getSintoma().getNumero());
            } else {
                ps.setNull(13, java.sql.Types.NUMERIC);
            }

            // Tratamiento
            if (tarea.getTipoTarea().isUsaTratamiento()) {
                ps.setInt(14, tarea.getTratamiento().getNumero());
            } else {
                ps.setNull(14, java.sql.Types.NUMERIC);
            }

            // Enfermedad
            if (tarea.getTipoTarea().isUsaEnfermedad()) {
                ps.setInt(15, tarea.getEnfermedad().getNumero());
            } else {
                ps.setNull(15, java.sql.Types.NUMERIC);
            }

            // Apiar
            if (tarea.getTipoTarea().isUsaApiar()) {
                ps.setInt(16, tarea.getApiar().getIdApiar());
            } else {
                ps.setNull(16, java.sql.Types.NUMERIC);
            }

            // Layout
            if (tarea.getTipoTarea().isUsaLayout()) {
                ps.setInt(17, tarea.getLayout().getIdLayout());
            } else {
                ps.setNull(17, java.sql.Types.NUMERIC);
            }

            // Zona
            if (tarea.getTipoTarea().isUsaZona()) {
                ps.setInt(18, tarea.getZona().getIdZona());
            } else {
                ps.setNull(18, java.sql.Types.NUMERIC);
            }

            // Alarma
            if (tarea.getTipoTarea().isUsaAlarma()) {
                ps.setInt(19, tarea.getAlarma().getNumero());
            } else {
                ps.setNull(19, java.sql.Types.NUMERIC);
            }

            resultado = ps.executeUpdate();
            ps.close();
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTipoTarea! (insertUno)");
        } finally {
            connPool.closeConnection(conn);
        }
        return resultado;
    }

    public int deleteUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deleteUno(int idActividad) throws Exception {
        int resultado = 0;
        try {
            // Elimino las tareas relacionadas
            conn = connPool.getConnection();
            String sql = " update tarea set idEstadoTarea = 4 " +
                    " where idActividad = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idActividad);
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTarea! (deleteUno(int))");
        }
        return resultado;
    }

    public int deleteUno(int idTarea, int idActividad) throws Exception {
        int resultado = 0;
        try {
            // Elimino la tarea seleccionada
            conn = connPool.getConnection();
            String sql = " update tarea set idEstadoTarea = 4 " +
                    " where idtarea = ? and idActividad = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idTarea);
            ps.setInt(2, idActividad);
            resultado = ps.executeUpdate();
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTarea! (deleteUno(int,int))");
        }
        return resultado;
    }

    public int updateUno(Object object) throws Exception {
        ArrayList<Tarea> tareas = (ArrayList) object;
        int resultado = 0;
        try {
            conn = connPool.getConnection();
            PreparedStatement ps = null;
            for (int i = 0; i < tareas.size(); i++) {
                String sql = " UPDATE tarea SET " +
                        " idEstadoTarea = ?, " +
                        " fechaRealizacion = ?, " +
                        " idColmena = ?, " +
                        " idFamilia = ?, " +
                        " idCajon = ?, " +
                        " idSintoma = ?, " +
                        " idTratamiento = ?, " +
                        " idEnfermedad = ?, " +
                        " idApiar = ?, " +
                        " idLayout = ?, " +
                        " idZona = ?, " +
                        " idAlarma = ?, " +
                        " descripcionRealizacion = ?, " +
                        " cantidadHoras = ?, " +
                        " cantidadMiel = ? " +
                        " WHERE idActividad = ? " +
                        " AND idtarea = ? ";

                ps = conn.prepareStatement(sql);

                // Atributos a actualizar
                ps.setInt(1, tareas.get(i).getEstado().getNumero());
                ps.setDate(2, UtilFecha.convertiFecha(tareas.get(i).getFechaRealizacion()));
                ps.setString(13, tareas.get(i).getDescripcionRealizacion());
                ps.setInt(14, tareas.get(i).getCantidadHoras());
                ps.setDouble(15, tareas.get(i).getCantidadMiel());

                // Colmena
                if (tareas.get(i).getTipoTarea().isUsaColmena()) {
                    ps.setInt(3, tareas.get(i).getColmena().getIdColmena());
                } else {
                    ps.setNull(3, java.sql.Types.NUMERIC);
                }

                // Familia
                if (tareas.get(i).getTipoTarea().isUsaFamilia()) {
                    ps.setInt(4, tareas.get(i).getFamilia().getNroFamilia());
                } else {
                    ps.setNull(4, java.sql.Types.NUMERIC);
                }

                // Cajon
                if (tareas.get(i).getTipoTarea().isUsaCajon()) {
                    ps.setInt(5, tareas.get(i).getCajon().getNroCajon());
                } else {
                    ps.setNull(5, java.sql.Types.NUMERIC);
                }

                // Sintoma
                if (tareas.get(i).getTipoTarea().isUsaSintoma()) {
                    ps.setInt(6, tareas.get(i).getSintoma().getNumero());
                } else {
                    ps.setNull(6, java.sql.Types.NUMERIC);
                }

                // Tratamiento
                if (tareas.get(i).getTipoTarea().isUsaTratamiento()) {
                    ps.setInt(7, tareas.get(i).getTratamiento().getNumero());
                } else {
                    ps.setNull(7, java.sql.Types.NUMERIC);
                }

                // Enfermedad
                if (tareas.get(i).getTipoTarea().isUsaEnfermedad()) {
                    ps.setInt(8, tareas.get(i).getEnfermedad().getNumero());
                } else {
                    ps.setNull(8, java.sql.Types.NUMERIC);
                }

                // Apiar
                if (tareas.get(i).getTipoTarea().isUsaApiar()) {
                    ps.setInt(9, tareas.get(i).getApiar().getIdApiar());
                } else {
                    ps.setNull(9, java.sql.Types.NUMERIC);
                }

                // Layout
                if (tareas.get(i).getTipoTarea().isUsaLayout()) {
                    ps.setInt(10, tareas.get(i).getLayout().getIdLayout());
                } else {
                    ps.setNull(10, java.sql.Types.NUMERIC);
                }

                // Zona
                if (tareas.get(i).getTipoTarea().isUsaZona()) {
                    ps.setInt(11, tareas.get(i).getZona().getIdZona());
                } else {
                    ps.setNull(11, java.sql.Types.NUMERIC);
                }

                // Alarma
                if (tareas.get(i).getTipoTarea().isUsaAlarma()) {
                    ps.setInt(12, tareas.get(i).getAlarma().getNumero());
                } else {
                    ps.setNull(12, java.sql.Types.NUMERIC);
                }

                // tipo de tarea a actualizar
                ps.setInt(16, tareas.get(i).getIdActividad());
                ps.setInt(17, tareas.get(i).getIdTarea());
                resultado = ps.executeUpdate();

                if (i == tareas.size()) {
                    ps.close();
                }
            }
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTarea! (updateUno)");
        }
        return resultado;
    }

    public ArrayList getTopFive(int idEmpleado) {
        try {
            conn = connPool.getConnection();
            String sql = " SELECT * FROM tarea " +
                    " WHERE idEstadoTarea IN (1,2,3) " +
                    " AND legajoempleado = ?        " +
                    " ORDER by fechaPrevista desc LIMIT 10 ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idEmpleado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setIdTarea(rs.getInt("idTarea"));
                tarea.setIdActividad(rs.getInt("idActividad"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setFechaPrevista(UtilFecha.convertiFecha(rs.getDate("fechaPrevista")));
                tarea.setObservaciones(rs.getString("observaciones"));
                // El resto de los parametros van en NULL para este metodo

                list.add(tarea);
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorTarea! (getTopFive)");
        }
        return list;
    }
}

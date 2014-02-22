/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import ibeeproject.model.soporte.UtilMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author carranza.matias
 */
public class GestorMenu implements Manejable {
    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;
    
    public GestorMenu() {
        connPool = ConexionPoolBD.getInstance();
        list = new ArrayList();
    }

    public ArrayList getTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
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

    public Object getUno(int idObjeto) {
        Hashtable permisos = new Hashtable();
        try {
            conn = connPool.getConnection();
            PreparedStatement ps;

            String sql = "SELECT * FROM permisos where idCargo = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                permisos.put(UtilMenu.ACTIVIDADES, rs.getBoolean(UtilMenu.ACTIVIDADES));
                permisos.put(UtilMenu.AGROQUIMICOS, rs.getBoolean(UtilMenu.AGROQUIMICOS));
                permisos.put(UtilMenu.ALARMAS, rs.getBoolean(UtilMenu.ALARMAS));
                permisos.put(UtilMenu.APIARES, rs.getBoolean(UtilMenu.APIARES));
                permisos.put(UtilMenu.CAJONES, rs.getBoolean(UtilMenu.CAJONES));
                permisos.put(UtilMenu.CARGOS, rs.getBoolean(UtilMenu.CARGOS));
                permisos.put(UtilMenu.CLIMAS, rs.getBoolean(UtilMenu.CLIMAS));
                permisos.put(UtilMenu.COLMENAS, rs.getBoolean(UtilMenu.COLMENAS));
                permisos.put(UtilMenu.EMPLEADOS, rs.getBoolean(UtilMenu.EMPLEADOS));
                permisos.put(UtilMenu.ENFERMEDADES, rs.getBoolean(UtilMenu.ENFERMEDADES));
                permisos.put(UtilMenu.FAMILIAS, rs.getBoolean(UtilMenu.FAMILIAS));
                permisos.put(UtilMenu.FLORAS, rs.getBoolean(UtilMenu.FLORAS));
                permisos.put(UtilMenu.HOJADERUTA, rs.getBoolean(UtilMenu.HOJADERUTA));
                permisos.put(UtilMenu.LAYOUTS, rs.getBoolean(UtilMenu.LAYOUTS));
                permisos.put(UtilMenu.MAPAS, rs.getBoolean(UtilMenu.MAPAS));
                permisos.put(UtilMenu.MONITOREO, rs.getBoolean(UtilMenu.MONITOREO));
                permisos.put(UtilMenu.RECURSOS, rs.getBoolean(UtilMenu.RECURSOS));
                permisos.put(UtilMenu.SINTOMAS, rs.getBoolean(UtilMenu.SINTOMAS));
                permisos.put(UtilMenu.TAREAS, rs.getBoolean(UtilMenu.TAREAS));
                permisos.put(UtilMenu.TIPODEACTIVIDAD, rs.getBoolean(UtilMenu.TIPODEACTIVIDAD));
                permisos.put(UtilMenu.TRATAMIENTOS, rs.getBoolean(UtilMenu.TRATAMIENTOS));
                permisos.put(UtilMenu.ZONAS, rs.getBoolean(UtilMenu.ZONAS));
                permisos.put(UtilMenu.REPORTES, rs.getBoolean(UtilMenu.REPORTES));
                permisos.put(UtilMenu.BACKUPBD, rs.getBoolean(UtilMenu.BACKUPBD));
            }
            ps.close();
            connPool.closeConnection(conn);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.print("Error en conexion BD: GestorMenu!!! (getUno)");
        }
        return permisos;
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

}

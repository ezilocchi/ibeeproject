/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.persistencia;

import ibeeproject.model.zona.Flora;
import ibeeproject.model.zona.TipoAgroquimico;
import java.sql.Connection;
import java.util.ArrayList;
import ibeeproject.model.zona.TipoFlora;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Erro Gonzalo
 */
public class GestorTipoAgroquimico implements Manejable{

    private ConexionPoolBD connPool;
    private Connection conn;
    private ArrayList list;
    private TipoAgroquimico tipoAgroquimico;

    //private Gestor

    public GestorTipoAgroquimico()
        {
           connPool = ConexionPoolBD.getInstance();
           list = new ArrayList();
        }
    public ArrayList getTodos() {
        list= new ArrayList();
        
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct * FROM tipoAgroquimico " +
                         " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipoAgroquimico = new TipoAgroquimico();
                tipoAgroquimico.setIdTipoAgroquimico(rs.getInt("idTipoAgroquimico"));
                tipoAgroquimico.setDenominacion(rs.getString("denominacion"));
                tipoAgroquimico.setDescripcion(rs.getString("descripcion"));
                list.add(tipoAgroquimico);
            }
            connPool.closeConnection(conn);
            }
            catch (Exception a)
                {
                a.printStackTrace();
                System.out.print("Error en conexion BD: GestorTipoAgroquimico !!!");
                }
       return list;
    }

    public Object getUltimo() {
        int indice=0;
        tipoAgroquimico = new TipoAgroquimico();
        try {
            conn = connPool.getConnection();
            String sql = " SELECT distinct max(idTipoAgroquimico) FROM tipoAgroquimico " +
                         " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
                // Regenero el objeto TipodeFlora
                // PROXIMAMENTE!!
                tipoAgroquimico.setIdTipoAgroquimico(rs.getInt("idTipoAgroquimico"));
                tipoAgroquimico.setDenominacion(rs.getString("denominacion"));
                tipoAgroquimico.setDescripcion(rs.getString("descripcion"));
                list.add(tipoAgroquimico);
                rs.close();
                ps.close();
                connPool.closeConnection(conn);
            }
            catch (Exception a)
                {
                a.printStackTrace();
                System.out.print("Error en conexion BD: GestorTipoAgroquimico !!!");
                }
       return tipoAgroquimico;
    }

    public ArrayList getAsignado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) throws Exception{

           tipoAgroquimico = new TipoAgroquimico();
           list.clear();
        try {
            conn = connPool.getConnection();
            String sql = "SELECT distinct ta.idTipoAgroquimico, ta.denominacion, ta.descripcion FROM tipoAgroquimico as ta, agroquimico as a" +
                    " where ta.idTipoAgroquimico = a.idTipoAgroquimico " +
                    " and  a.idZona= ? "+
                    " ORDER BY 1";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Regenero el objeto Agroquimico
                tipoAgroquimico.setIdTipoAgroquimico(rs.getInt("idTipoAgroquimico"));
                tipoAgroquimico.setDenominacion(rs.getString("denominacion"));
                tipoAgroquimico.setDescripcion(rs.getString("descripcion"));
                list.add(tipoAgroquimico);

            }
            rs.close();
            ps.close();
            connPool.closeConnection(conn);
            }
            catch (Exception a)
                {
                a.printStackTrace();
                System.out.print("Error en conexion BD: GestorTipoAgroquimico !!!");
                }
       return tipoAgroquimico;
    }

    public int insertUno(Object idObjeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deleteUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int updateUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

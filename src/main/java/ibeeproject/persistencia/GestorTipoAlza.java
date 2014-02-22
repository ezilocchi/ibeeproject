/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import ibeeproject.exception.ErrorDBException;
import ibeeproject.exception.TipoAlzaDuplicadaException;
import ibeeproject.exception.BusquedaSinResultadoException;
import ibeeproject.model.cajon.TipoAlza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Fede 
 */
public class GestorTipoAlza implements Manejable{

    private ConexionPoolBD conPool;
    private Connection con;

    public GestorTipoAlza () {
        conPool = ConexionPoolBD.getInstance();
    }

    public void insertar (TipoAlza tipoAlza) throws TipoAlzaDuplicadaException, ErrorDBException {
        con = conPool.getConnection();
        String sql="INSERT INTO tipoalza VALUES ((?),(?),(?))";
		PreparedStatement ps;
        try{
			ps=con.prepareStatement(sql);
			ps.setInt(1,tipoAlza.getIdTipoAlza());
			ps.setString(2,tipoAlza.getDenominacion());
			ps.setString(3,tipoAlza.getDescripcion());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			//System.out.println(e.getErrorCode());
			if (e.getErrorCode()==-803)
			{
				throw new TipoAlzaDuplicadaException();
			}
			if (e.getErrorCode()==-530)
			{
				//throw new LibroInexistenteException();
			}
			throw new ErrorDBException();
		}finally{
			try{
				con.close();
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		}
    }

    public ArrayList getTodos() throws BusquedaSinResultadoException, ErrorDBException {
        con = conPool.getConnection();
        String sql = "SELECT * FROM `ibee`.`tipoalza` ORDER BY idTipoAlza ASC";
        PreparedStatement ps;
		ArrayList marcos=new ArrayList();
		ResultSet rs;
        TipoAlza tipoAlza = null;
		try{
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
            while (rs.next())
			{
				/*crear tipoAlza*/
                tipoAlza = new TipoAlza();
                tipoAlza.setIdTipoAlza(rs.getInt("idTipoAlza"));
                tipoAlza.setDenominacion(rs.getString("denominacion"));
                tipoAlza.setDescripcion(rs.getString("descripcion"));
			}
            if(marcos.isEmpty())
            {
                throw new BusquedaSinResultadoException();
            }
		}catch (SQLException e){
			e.printStackTrace();
			throw new ErrorDBException();
		}finally{
			try{
				con.close();
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		}
		return marcos;
    }

    public Object getUltimo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getAsignado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getSinAsignar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getUno(int idObjeto) {
        throw new UnsupportedOperationException("Not supported yet.");
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

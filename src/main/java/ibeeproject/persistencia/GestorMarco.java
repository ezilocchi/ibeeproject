/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import ibeeproject.exception.BusquedaSinResultadoException;
import ibeeproject.exception.ErrorDBException;
import ibeeproject.exception.MarcoDuplicadoException;
import ibeeproject.model.cajon.Marco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Fede 
 */
public class GestorMarco  implements Manejable {
    
    private ConexionPoolBD conPool;
    private Connection con;
    
    public GestorMarco () {
        conPool = ConexionPoolBD.getInstance();
    }

    public void insertar (Marco marco) throws MarcoDuplicadoException, ErrorDBException {
        con = conPool.getConnection();
        String sql="INSERT INTO marco VALUES ((?),(?),(?),(?),(?),(?))";
		PreparedStatement ps;
        try{
			ps=con.prepareStatement(sql);
			ps.setInt(1,marco.getNroMarco());
			ps.setInt(2,marco.getNroAlza());
			ps.setInt(3,marco.getNroCajon());
			ps.setString(4,marco.getDescripcion());
            ps.setString(5,marco.getTamaño());
            ps.setString(6,marco.getObservaciones());
			ps.executeUpdate();

		}
		catch (SQLException e) {
			e.printStackTrace();
			//System.out.println(e.getErrorCode());
			if (e.getErrorCode()==-803)
			{
				throw new MarcoDuplicadoException();
			}
			if (e.getErrorCode()==-530)
			{
				//throw new LibroInexistenteException();
			}
			throw new ErrorDBException();
		}finally{

                conPool.closeConnection(con);

		}
    }

    public ArrayList getTodos() throws BusquedaSinResultadoException, ErrorDBException {
        con = conPool.getConnection();
        String sql = "SELECT * FROM `ibee`.`marco` ORDER BY numeroMarco ASC";
        PreparedStatement ps;
		ArrayList marcos=new ArrayList();
		ResultSet rs;
        Marco marco = null;
		try{
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
            while (rs.next())
			{
				/*crear marco*/
                marco = new Marco();
                marco.setNroMarco(rs.getInt("numeroMarco"));
                marco.setNroAlza(rs.getInt("numeroAlza"));
                marco.setNroCajon(rs.getInt("numeroCajon"));
                marco.setDescripcion(rs.getString("descripcion"));
                marco.setTamaño(rs.getString("tamanio"));
                marco.setObservaciones(rs.getString("observaciones"));
			}
            if(marcos.isEmpty())
            {
                throw new BusquedaSinResultadoException();
            }
		}catch (SQLException e){
			e.printStackTrace();
			throw new ErrorDBException();
		}finally{
conPool.closeConnection(con);
		}
		return marcos;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deleteUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int updateUno(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

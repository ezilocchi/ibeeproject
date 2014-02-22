/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;


import ibeeproject.exception.AlzaDuplicadaException;
import ibeeproject.exception.BusquedaSinResultadoException;
import ibeeproject.exception.ErrorDBException;
import ibeeproject.model.cajon.Alza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Fede 
 */
public class GestorAlza implements Manejable{

    private ConexionPoolBD conPool;
    private Connection con;

    public GestorAlza () {
        conPool = ConexionPoolBD.getInstance();
    }

    public void insertar (Alza alza) throws AlzaDuplicadaException, ErrorDBException {
        con = conPool.getConnection();
        String sql="INSERT INTO alza VALUES ((?),(?),(?),(?),(?),(?),(?))";
		PreparedStatement ps;
        try{
			ps=con.prepareStatement(sql);
			ps.setInt(1,alza.getNroAlza());
			ps.setInt(2,alza.getNroCajon());
			ps.setString(3,alza.getDescripcion());
			ps.setString(4,alza.getEstado());
            ps.setString(5,alza.getTamaño());
            ps.setString(6,alza.getObservaciones());
            ps.setInt(7,alza.getIdTipoAlza());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			//System.out.println(e.getErrorCode());
			if (e.getErrorCode()==-803)
			{
				throw new AlzaDuplicadaException();
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
        String sql="SELECT * FROM `ibee`.`alza` ORDER BY numeroAlza ASC";
		return getAlzas(sql);
    }

    public ArrayList getAsignado()throws BusquedaSinResultadoException, ErrorDBException {
        String sql="SELECT * FROM `ibee`.`alza` WHERE descriocion = 'ASIGNADA' ORDER BY numeroAlza ASC";
		return getAlzas(sql);
    }

    public ArrayList getSinAsignar() throws BusquedaSinResultadoException, ErrorDBException {
        String sql="SELECT * FROM `ibee`.`alza` WHERE descriocion = 'NO ASIGNADA' ORDER BY numeroAlza ASC";
		return getAlzas(sql);
    }

    private ArrayList getAlzas (String sql) throws BusquedaSinResultadoException, ErrorDBException {
        con = conPool.getConnection();
        PreparedStatement ps;
		ArrayList alzas=new ArrayList();
		ResultSet rs;
        Alza alza = null;
		try{
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
            while (rs.next())
			{
				/*crear alza*/
                alza = new Alza();
                alza.setNroAlza(rs.getInt("numeroAlza"));
                alza.setNroCajon(rs.getInt("numeroCajon"));
                alza.setDescripcion(rs.getString("descripcion"));
                alza.setEstado(rs.getString("estado"));
                alza.setTamaño(rs.getString("tamanio"));
                alza.setObservaciones(rs.getString("observaciones"));
                alza.setIdTipoAlza(rs.getInt("idTipoAlza"));
			}
            if(alzas.isEmpty())
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
		return alzas;
    }

    public Object getUltimo() throws Exception {
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

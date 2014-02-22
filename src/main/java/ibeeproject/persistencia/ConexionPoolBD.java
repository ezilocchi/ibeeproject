/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Administrador
 */
public class ConexionPoolBD {

private static ConexionPoolBD instance = new ConexionPoolBD();
private DataSource source;

//private ConexionPoolBD () {};

public static ConexionPoolBD getInstance(){
//		if (instance==null)
//		{
//            instance = new ConexionPoolBD();
//        }
		return instance;
	}

 private ConexionPoolBD(){
  try {
            // Cargamos el Driver, para los accesos sin pool de conexiones.
            //Class.forName("com.mysql.jdbc.Driver");

         // Obtenemos el pool.
      //System.out.println("LLegué a antes del lookup111");
             Context initCtx = new InitialContext();
             //Context envCtx  = (Context) initCtx.lookup("jdbc/myDatasource");
             //System.out.println("LLegué a antes del lookup")
             this.source     = (DataSource) initCtx.lookup("jdbc/ibee_mysql");
             //System.out.println("Hola tengo conexión: "+ this.source.toString());
         } catch (Exception e) {
                e.printStackTrace();
         }
    }

public Connection getConnection()
        {
        try{
            return this.source.getConnection();
            }catch(Exception a)
            {
                a.printStackTrace();
            }
        return null;
        }

    public void closeConnection(Connection con)
        {
            try{
                con.close();
            }catch(Exception a)
                {
                a.printStackTrace();
                }
        }
}

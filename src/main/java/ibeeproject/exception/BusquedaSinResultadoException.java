/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.exception;

/**
 *
 * @author Fede 
 */
public class BusquedaSinResultadoException extends Exception {

    private String message;

	public BusquedaSinResultadoException ()
	{
		message="No se encontro objetos registrados";
	}

	public BusquedaSinResultadoException(String msg)
	{
		message=msg;
	}
	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param string
	 */
	public void setMessage(String string) {
		message = string;
	}

}

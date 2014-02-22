/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.exception;

/**
 *
 * @author Fede 
 */
public class ErrorDBException extends Exception {

    private String message;

	public ErrorDBException ()
	{
		message="Error de conexión";
	}

	public ErrorDBException (String msg)
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
	public void setMessage(String message) {
		this.message = message;
	}

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.exception;

/**
 *
 * @author Fede 
 */
public class CajonDuplicadoException extends Exception {

    private String message;

    public CajonDuplicadoException () {
        message = "El cajon ya esta registrado";
    }

    public CajonDuplicadoException (String msg){
		message=msg;
	}

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}

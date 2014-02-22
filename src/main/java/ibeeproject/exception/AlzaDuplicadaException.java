/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.exception;

/**
 *
 * @author Fede 
 */
public class AlzaDuplicadaException extends Exception {

    private String message;

    public AlzaDuplicadaException () {
        message = "El alza ya esta registrada";
    }

    public AlzaDuplicadaException (String msg){
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

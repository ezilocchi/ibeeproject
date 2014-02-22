/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.model.soporte;

import java.util.regex.*;

/**
 *
 * @author burni.matias
 */
public class UtilEmailValidate {

    /** isEmailValid: Validate email address using Java reg ex.
     * This method checks if the input string is a valid email address.
     * @param email String. Email address to validate
     * @return boolean: true if email address is valid, false otherwise.
     */
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        //Initialize reg ex for email.
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        //Make the comparison case-insensitive.
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}

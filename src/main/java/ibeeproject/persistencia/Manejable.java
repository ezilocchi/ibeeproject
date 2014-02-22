/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.persistencia;

import java.util.ArrayList;

/**
 *
 * @author Gonzalo
 */
public interface Manejable {

    public ArrayList getTodos() throws Exception;
    public Object getUltimo() throws Exception;
    public ArrayList getAsignado() throws Exception;
    public ArrayList getSinAsignar() throws Exception;
    public Object getUno(int idObjeto) throws Exception;
    public int insertUno(Object object) throws Exception;
    public int deleteUno(Object object) throws Exception;
    public int updateUno(Object object) throws Exception;
}

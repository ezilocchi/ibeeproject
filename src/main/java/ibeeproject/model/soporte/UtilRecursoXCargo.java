/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.model.soporte;

import ibeeproject.model.persona.Recurso;
import ibeeproject.persistencia.GestorRecurso;
import ibeeproject.persistencia.GestorRecursoXCargo;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase la utilizo para manejar las listas de Recursos (Menues)
 * @author carranza.matias
 */
public class UtilRecursoXCargo {

    private boolean habilitado;
    private Recurso recurso;

    /**
     * @return the habilitado
     */
    public boolean isHabilitado() {
        return habilitado;
    }

    /**
     * @param habilitado the habilitado to set
     */
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * @return the recurso
     */
    public Recurso getRecurso() {
        return recurso;
    }

    /**
     * @param recurso the recurso to set
     */
    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public static Hashtable tienePermiso(int idCargo) {

        GestorRecursoXCargo gestorRecursoXCargo = new GestorRecursoXCargo();
        return (Hashtable) gestorRecursoXCargo.getTodosHabilitadosHash(idCargo);
    }

    public static String Permiso(String denominacion, int idCargo) {

        Hashtable permisos = tienePermiso(idCargo);
        Boolean res = (Boolean) permisos.get(denominacion);
        if (res == null) {
            return "false";
        }
        return "true";
    }

    public static ArrayList dameTodos() throws Exception {
        GestorRecurso gestorRecurso = new GestorRecurso();
        ArrayList array = gestorRecurso.getTodos();
        ArrayList<UtilRecursoXCargo> utilRecursoXCargos = new ArrayList<UtilRecursoXCargo>();
        for (int i = 0; i < array.size(); i++) {
            UtilRecursoXCargo utilRecursoXCargo = new UtilRecursoXCargo();
            utilRecursoXCargo.setRecurso((Recurso) array.get(i));
            utilRecursoXCargo.setHabilitado(false);
            utilRecursoXCargos.add(i, utilRecursoXCargo);
        }
        return utilRecursoXCargos;
    }

    // Me devuelve un Array solo con los recursos que estan habilitados o sea que tienen "true"
    public static ArrayList dameHabilitados(ArrayList recursoXCargos) throws Exception {
        ArrayList recursos = new ArrayList();
        for (int i = 0; i < recursoXCargos.size(); i++) {
            UtilRecursoXCargo utilRecursoXCargo = (UtilRecursoXCargo) recursoXCargos.get(i);
            if (utilRecursoXCargo.isHabilitado()) {
                recursos.add(utilRecursoXCargo.getRecurso());
            }
        }
        return recursos;
    }

    /**
     * Me devuelve un Array con los recursos habilitados y los NO habilitados (es para mostrar en un Consultar por ejemplo)
     */
    public static ArrayList dameHabilitadosyNo(int idCargo) {
        ArrayList recursoXCargos = new ArrayList();
        try {
            ArrayList recursosTodos = new ArrayList();
            GestorRecurso gestorRecurso = new GestorRecurso();
            recursosTodos = gestorRecurso.getTodos();

            Hashtable permisos = UtilRecursoXCargo.tienePermiso(idCargo);

            for (int i = 0; i < recursosTodos.size(); i++) {
                Recurso aux = (Recurso) recursosTodos.get(i);
                if (permisos.get(aux.getNombre()) != null) {
                    UtilRecursoXCargo utilRecursoXCargo = new UtilRecursoXCargo();
                    utilRecursoXCargo.setRecurso((Recurso) recursosTodos.get(i));
                    utilRecursoXCargo.setHabilitado(true);
                    recursoXCargos.add(utilRecursoXCargo);
                } else {
                    UtilRecursoXCargo utilRecursoXCargo = new UtilRecursoXCargo();
                    utilRecursoXCargo.setRecurso((Recurso) recursosTodos.get(i));
                    utilRecursoXCargo.setHabilitado(false);
                    recursoXCargos.add(utilRecursoXCargo);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UtilRecursoXCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recursoXCargos;

    }
}

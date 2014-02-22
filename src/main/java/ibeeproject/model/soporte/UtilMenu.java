/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.model.soporte;

import ibeeproject.model.persona.Recurso;
import ibeeproject.persistencia.GestorMenu;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author carranza.matias
 */
public class UtilMenu {

    public static String APIARES = "Apiares";
    public static String COLMENAS = "Colmenas";
    public static String FAMILIAS = "Familias";
    public static String CAJONES = "Cajones";
    public static String LAYOUTS = "Layouts";
    public static String ZONAS = "Zonas";
    public static String CLIMAS = "Climas";
    public static String FLORAS = "Floras";
    public static String AGROQUIMICOS = "Agroquimicos";
    public static String ACTIVIDADES = "Actividades";
    public static String TIPODEACTIVIDAD = "TiposDeActividad";
    public static String TAREAS = "Tareas";
    public static String HOJADERUTA = "HojaDeRuta";
    public static String ENFERMEDADES = "Enfermedades";
    public static String SINTOMAS = "Sintomas";
    public static String TRATAMIENTOS = "Tratamientos";
    public static String MAPAS = "Mapas";
    public static String ALARMAS = "Alarmas";
    public static String MONITOREO = "Monitoreo";
    public static String EMPLEADOS = "Empleados";
    public static String CARGOS = "Cargos";
    public static String RECURSOS = "Recursos";
    public static String REPORTES = "Reportes";
    public static String BACKUPBD = "BackupBD";

    public static Hashtable tienePermiso(int idCargo) {
        GestorMenu gestorMenu = new GestorMenu();

        return (Hashtable) gestorMenu.getUno(idCargo);

    }

    public static String Permiso(String itemMenu, int idCargo) {
        Hashtable permisos = UtilMenu.tienePermiso(idCargo);
        Boolean res = (Boolean) permisos.get(itemMenu);
        if (res) {
            return "true";
        }
        return "false";
    }

    public static Object dameTodos() {
        ArrayList recursos = new ArrayList();

        Recurso APIARE = new Recurso();
        APIARE.setNombre(APIARES);
        recursos.add(APIARE);
        Recurso COLMENA = new Recurso();
        COLMENA.setNombre(COLMENAS);
        recursos.add(COLMENA);
        Recurso FAMILIA = new Recurso();
        FAMILIA.setNombre(FAMILIAS);
        recursos.add(FAMILIA);
        Recurso CAJONE = new Recurso();
        CAJONE.setNombre(CAJONES);
        recursos.add(CAJONE);
        Recurso LAYOUT = new Recurso();
        LAYOUT.setNombre(LAYOUTS);
        recursos.add(LAYOUT);
        Recurso ZONA = new Recurso();
        ZONA.setNombre(ZONAS);
        recursos.add(ZONA);
        Recurso CLIMA = new Recurso();
        CLIMA.setNombre(CLIMAS);
        recursos.add(CLIMA);
        Recurso FLORA = new Recurso();
        FLORA.setNombre(FLORAS);
        recursos.add(FLORA);
        Recurso AGROQUIMICO = new Recurso();
        AGROQUIMICO.setNombre(AGROQUIMICOS);
        recursos.add(AGROQUIMICO);
        Recurso ACTIVIDAD = new Recurso();
        ACTIVIDAD.setNombre(ACTIVIDADES);
        recursos.add(ACTIVIDAD);
        Recurso TIPODEACTIVIDA = new Recurso();
        TIPODEACTIVIDA.setNombre(TIPODEACTIVIDAD);
        recursos.add(TIPODEACTIVIDA);
        Recurso TAREA = new Recurso();
        TAREA.setNombre(TAREAS);
        recursos.add(TAREA);
        Recurso HOJADERUT = new Recurso();
        HOJADERUT.setNombre(HOJADERUTA);
        recursos.add(HOJADERUT);
        Recurso ENFERMEDADE = new Recurso();
        ENFERMEDADE.setNombre(ENFERMEDADES);
        recursos.add(ENFERMEDADE);
        Recurso SINTOMA = new Recurso();
        SINTOMA.setNombre(SINTOMAS);
        recursos.add(SINTOMA);
        Recurso TRATAMIENTO = new Recurso();
        TRATAMIENTO.setNombre(TRATAMIENTOS);
        recursos.add(TRATAMIENTO);
        Recurso MAPA = new Recurso();
        MAPA.setNombre(MAPAS);
        recursos.add(MAPA);
        Recurso ALARMA = new Recurso();
        ALARMA.setNombre(ALARMAS);
        recursos.add(ALARMA);
        Recurso MONITORE = new Recurso();
        MONITORE.setNombre(MONITOREO);
        recursos.add(MONITORE);
        Recurso EMPLEADO = new Recurso();
        EMPLEADO.setNombre(EMPLEADOS);
        recursos.add(EMPLEADO);
        Recurso CARGO = new Recurso();
        CARGO.setNombre(CARGOS);
        recursos.add(CARGO);
        Recurso RECURSO = new Recurso();
        RECURSO.setNombre(RECURSOS);
        recursos.add(RECURSO);
        Recurso REPORTE = new Recurso();
        REPORTE.setNombre(REPORTES);
        recursos.add(REPORTE);
        Recurso BACKUP = new Recurso();
        BACKUP.setNombre(BACKUPBD);
        recursos.add(BACKUP);

        return recursos;

//        return this.getClass().getFields();

    }
}

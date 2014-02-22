/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject.model.soporte;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author Fede 
 */
public class UtilFecha {

    /**
     * convierte un atributo tipo java.sql.Date a un atributo tipo java.util.Date
     * @param sqlFecha
     * @return
     */
    public static Date convertiFecha(java.sql.Date sqlFecha) {
        if (sqlFecha != null) {
            long fecha = sqlFecha.getTime();
            Date utilFecha = new Date(fecha);
            return utilFecha;
        } else {
            return null;
        }
    }

    /**
     * convierte un atributo tipo java.util.Date a un atributo tipo java.sql.Date
     * @param utilFecha
     * @return
     */
    public static java.sql.Date convertiFecha(Date utilFecha) {
        if (utilFecha != null) {
            long fecha = utilFecha.getTime();
            java.sql.Date sqlFecha = new java.sql.Date(fecha);
            return sqlFecha;
        } else {
            return null;
        }

    }

    public static String getHoy() {
        long fecha = System.currentTimeMillis();
        Date hoy = new Date(fecha);
        return hoy.toString();
    }

    /**
     *
     * @param desde la fecha de comienzo del intervalo
     * @param hasta la fecha de final de intervalo
     * @return devuelve la cantidad de días entre los parámetros dados, en el caso de que fecha comienzo sea mayor a fecha hasta, devuelve la cantidad de días en negativo
     */
    public static int fechasDiferenciaEnDias(Date desde, Date hasta) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(desde);
        try {
            desde = df.parse(fechaInicioString);
        } catch (ParseException ex) {
        }
        String fechaFinalString = df.format(hasta);
        try {
            hasta = df.parse(fechaFinalString);
        } catch (ParseException ex) {
        }

        long fechaInicialMs = desde.getTime();
        long fechaFinalMs = hasta.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.icesoft.faces.context.effects.JavascriptContext;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version Header2.java
 * @version Created on 27-jun-2009, 17:18:17
 * @author farias.facundo 
 */
public class Header extends AbstractFragmentBean {

    private boolean permisoApiares;
    private boolean permisoColmenas;
    private boolean permisoFamilias;
    private boolean permisoCajones;
    private boolean permisoLayouts;
    private boolean permisoZonas;
    private boolean permisoClimas;
    private boolean permisoFloras;
    private boolean permisoAgroquimicos;
    private boolean permisoActividades;
    private boolean permisoTiposDeActividad;
    private boolean permisoTareas;
    private boolean permisoHojaDeRuta;
    private boolean permisoEnfermedades;
    private boolean permisoSintomas;
    private boolean permisoTratamientos;
    private boolean permisoMapas;
    private boolean permisoAlarmas;
    private boolean permisoMonitoreo;
    private boolean permisoEmpleados;
    private boolean permisoCargos;
    private boolean permisoRecursos;
    private boolean permisoReportes;
    private boolean permisoBackupBD;
    // menues de primer nivel
    private boolean permisoProduccion;
    private boolean permisoZona;
    private boolean permisoActividad;
    private boolean permisoEnfermedad;
    private boolean permisoParametrizacion;
    private boolean permisoMonitoreos;

    public void setPermisoActividad(boolean permisoActividad) {
        this.permisoActividad = permisoActividad;
    }

    public void setPermisoActividades(boolean permisoActividades) {
        this.permisoActividades = permisoActividades;
    }

    public void setPermisoAgroquimicos(boolean permisoAgroquimicos) {
        this.permisoAgroquimicos = permisoAgroquimicos;
    }

    public void setPermisoAlarmas(boolean permisoAlarmas) {
        this.permisoAlarmas = permisoAlarmas;
    }

    public void setPermisoApiares(boolean permisoApiares) {
        this.permisoApiares = permisoApiares;
    }

    public void setPermisoBackupBD(boolean permisoBackupBD) {
        this.permisoBackupBD = permisoBackupBD;
    }

    public void setPermisoCajones(boolean permisoCajones) {
        this.permisoCajones = permisoCajones;
    }

    public void setPermisoCargos(boolean permisoCargos) {
        this.permisoCargos = permisoCargos;
    }

    public void setPermisoClimas(boolean permisoClimas) {
        this.permisoClimas = permisoClimas;
    }

    public void setPermisoColmenas(boolean permisoColmenas) {
        this.permisoColmenas = permisoColmenas;
    }

    public void setPermisoEmpleados(boolean permisoEmpleados) {
        this.permisoEmpleados = permisoEmpleados;
    }

    public void setPermisoEnfermedad(boolean permisoEnfermedad) {
        this.permisoEnfermedad = permisoEnfermedad;
    }

    public void setPermisoEnfermedades(boolean permisoEnfermedades) {
        this.permisoEnfermedades = permisoEnfermedades;
    }

    public void setPermisoFamilias(boolean permisoFamilias) {
        this.permisoFamilias = permisoFamilias;
    }

    public void setPermisoFloras(boolean permisoFloras) {
        this.permisoFloras = permisoFloras;
    }

    public void setPermisoHojaDeRuta(boolean permisoHojaDeRuta) {
        this.permisoHojaDeRuta = permisoHojaDeRuta;
    }

    public void setPermisoLayouts(boolean permisoLayouts) {
        this.permisoLayouts = permisoLayouts;
    }

    public void setPermisoMapas(boolean permisoMapas) {
        this.permisoMapas = permisoMapas;
    }

    public void setPermisoMonitoreo(boolean permisoMonitoreo) {
        this.permisoMonitoreo = permisoMonitoreo;
    }

    public void setPermisoMonitoreos(boolean permisoMonitoreos) {
        this.permisoMonitoreos = permisoMonitoreos;
    }

    public void setPermisoParametrizacion(boolean permisoParametrizacion) {
        this.permisoParametrizacion = permisoParametrizacion;
    }

    public void setPermisoProduccion(boolean permisoProduccion) {
        this.permisoProduccion = permisoProduccion;
    }

    public void setPermisoRecursos(boolean permisoRecursos) {
        this.permisoRecursos = permisoRecursos;
    }

    public void setPermisoReportes(boolean permisoReportes) {
        this.permisoReportes = permisoReportes;
    }

    public void setPermisoSintomas(boolean permisoSintomas) {
        this.permisoSintomas = permisoSintomas;
    }

    public void setPermisoTareas(boolean permisoTareas) {
        this.permisoTareas = permisoTareas;
    }

    public void setPermisoTiposDeActividad(boolean permisoTiposDeActividad) {
        this.permisoTiposDeActividad = permisoTiposDeActividad;
    }

    public void setPermisoTratamientos(boolean permisoTratamientos) {
        this.permisoTratamientos = permisoTratamientos;
    }

    public void setPermisoZona(boolean permisoZona) {
        this.permisoZona = permisoZona;
    }

    public void setPermisoZonas(boolean permisoZonas) {
        this.permisoZonas = permisoZonas;
    }

//    private boolean permisoParametrizacion;

    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public Header() {
    }

    /**
     * <p>Callback method that is called whenever a page containing
     * this page fragment is navigated to, either directly via a URL,
     * or indirectly via page navigation.  Override this method to acquire
     * resources that will be needed for event handlers and lifecycle methods.</p>
     * 
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here


        // <editor-fold defaultstate="collapsed" desc="Visual-Web-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("Page1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

    // </editor-fold>
    // Perform application initialization that must complete
    // *after* managed components are initialized
    // TODO - add your own initialization code here

    }

    /**
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called.  Override this
     * method to release resources acquired in the <code>init()</code>
     * resources that will be needed for event handlers and lifecycle methods.</p>
     * 
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void destroy() {
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
    }

    public String globalNav_action() {
        //return null means stay on the same page
        return null;
    }

    public String Home_action() {
        //return null means stay on the same page
        return "home";
    }

    public String Produccion_action() {
        //return null means stay on the same page
        return null;
    }

    public String Apiares_action() {
        //return null means stay on the same page
        return "apiares";
    }

    public String Colmenas_action() {
        //return null means stay on the same page
        return "colmenas";
    }

    public String Familias_action() {
        //return null means stay on the same page
        return "familias";
    }

    public String Cajones_action() {
        //return null means stay on the same page
        return "cajones";
    }

    public String Layouts_action() {
        return "layouts";
    }

    public String Zona_action() {
        return "zonas";
    }

    public String Climas_action() {
        return "climas";
    }

    public String ActualizacionClima_action() {
        return "ActualizarClimas";
    }

    public String Floras_action() {
        //return null means stay on the same page
        return null;
    }

    public String Agroquimicos_action() {
        //return null means stay on the same page
        return null;
    }

    public String Actividad_action() {
        //return null means stay on the same page
        return null;
    }

    public String TiposDeActividad_action() {
        //return null means stay on the same page
        return "tiposDeActividad";
    }

    public String Actividades_action() {
        //return null means stay on the same page
        return "actividades";
    }

    public String Tareas_action() {
        //return null means stay on the same page
        return "tareas";
    }

    public String HojaDeRuta_action() {
        //return null means stay on the same page
        return null;
    }

    public String Enfermedad_action() {
        //return null means stay on the same page
        return null;
    }

    public String Enfermedades_action() {
        //return null means stay on the same page
        return "enfermedades";
    }

    public String Sintomas_action() {
        //return null means stay on the same page
        return "sintomas";
    }

    public String Tratamientos_action() {
        //return null means stay on the same page
        return "tratamientos";
    }

    public String Monitoreo_action() {
        //return null means stay on the same page
        return "monitoreo";
    }

    public String Alarmas_action() {
        //return null means stay on the same page
        return "alarmas";
    }

    public String Mapas_action() {
        //return null means stay on the same page
        return "mapas";
    }

    public String Parametrizacion_action() {
        //return null means stay on the same page
        return null;
    }

    public String Usuarios_action() {
        //return null means stay on the same page
        return "empleados";
    }

    public String Cargos_action() {
        //return null means stay on the same page
        return "cargos";
    }

    public String Recursos_action() {
        //return null means stay on the same page
        return "recursos";
    }

    public String Perfil_action() {
        //return null means stay on the same page
        return null;
    }

    public String ActualizarDatos_action() {
        //return null means stay on the same page
        return "actualizarDatos";
    }

    public String CambiarContrasenia_action() {
        //return null means stay on the same page
        return "cambiarContrasenia";
    }

    public String Reportes_action() {
        //return null means stay on the same page
        return null;
    }

    public String BackupBD_action() {
        return "backup";
    }

    public String EstadoSanitario_action() {
        JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),
                "window.open('Reportes.jsp',''" +
                ",'scrollbars=yes,menubar=no,height=600," +
                "width=800,resizable=yes,toolbar=no,location=no,status=no');");
        return "home";
    }

    /**
     * @return the permisoApiares
     */
    public boolean isPermisoApiares() {
        return tienePermiso("Apiares");
    }

    /**
     * @return the permisoColmenas
     */
    public boolean isPermisoColmenas() {
        return tienePermiso("Colmenas");
    }

    /**
     * @return the permisoFamilias
     */
    public boolean isPermisoFamilias() {
        return tienePermiso("Familias");
    }

    /**
     * @return the permisoCajones
     */
    public boolean isPermisoCajones() {
        return tienePermiso("Cajones");
    }

    /**
     * @return the permisoLayouts
     */
    public boolean isPermisoLayouts() {
        return tienePermiso("Layouts");
    }

    /**
     * @return the permisoZonas
     */
    public boolean isPermisoZonas() {
        return tienePermiso("Zonas");
    }

    /**
     * @return the permisoClimas
     */
    public boolean isPermisoClimas() {
        return tienePermiso("Climas");
    }

    /**
     * @return the permisoFloras
     */
    public boolean isPermisoFloras() {
        return tienePermiso("Floras");
    }

    /**
     * @return the permisoAgroquimicos
     */
    public boolean isPermisoAgroquimicos() {
        return tienePermiso("Agroquimicos");
    }

    /**
     * @return the permisoActividades
     */
    public boolean isPermisoActividades() {
        return tienePermiso("Actividades");
    }

    /**
     * @return the permisoTiposDeActividad
     */
    public boolean isPermisoTiposDeActividad() {
        return tienePermiso("TiposDeActividad");
    }

    /**
     * @return the permisoTareas
     */
    public boolean isPermisoTareas() {
        return tienePermiso("Tareas");
    }

    /**
     * @return the permisoHojaDeRuta
     */
    public boolean isPermisoHojaDeRuta() {
        return tienePermiso("HojaDeRuta");
    }

    /**
     * @return the permisoEnfermedades
     */
    public boolean isPermisoEnfermedades() {
        return tienePermiso("Enfermedades");
    }

    /**
     * @return the permisoSintomas
     */
    public boolean isPermisoSintomas() {
        return tienePermiso("Sintomas");
    }

    /**
     * @return the permisoTratamientos
     */
    public boolean isPermisoTratamientos() {
        return tienePermiso("Tratamientos");
    }

    /**
     * @return the permisoMapas
     */
    public boolean isPermisoMapas() {
        return tienePermiso("Mapas");
    }

    /**
     * @return the permisoAlarmas
     */
    public boolean isPermisoAlarmas() {
        return tienePermiso("Alarmas");
    }

    /**
     * @return the permisoMonitoreo
     */
    public boolean isPermisoMonitoreo() {
        return tienePermiso("Monitoreo");
    }

    /**
     * @return the permisoEmpleados
     */
    public boolean isPermisoEmpleados() {
        return tienePermiso("Empleados");
    }

    /**
     * @return the permisoCargos
     */
    public boolean isPermisoCargos() {
        return tienePermiso("Cargos");
    }

    /**
     * @return the permisoRecursos
     */
    public boolean isPermisoRecursos() {
        return tienePermiso("Recursos");
    }

    /**
     * @return the permisoActualizarDatos
     */
    public boolean isPermisoActualizarDatos() {
        return tienePermiso("ActualizarDatos");
    }

    /**
     * @return the permisoCambiarContrasenia
     */
    public boolean isPermisoCambiarContrasenia() {
        return tienePermiso("CambiarContrasenia");
    }

    /**
     * @return the permisoReportes
     */
    public boolean isPermisoReportes() {
        return tienePermiso("Reportes");
    }

    /**
     * @return the permisoBackupBD
     */
    public boolean isPermisoBackupBD() {
        return tienePermiso("BackupBD");

    }

    /**
     * @return the permisoProduccion
     */
    public boolean isPermisoProduccion() {
        if (this.isPermisoApiares() || this.isPermisoColmenas() || this.isPermisoFamilias() || this.isPermisoCajones() || this.isPermisoLayouts()) {
            return true;
        }
        return false;
    }

    /**
     * @return the permisoZona
     */
    public boolean isPermisoZona() {
        if (this.isPermisoZonas() || this.isPermisoClimas()) {
            return true;
        }
        return false;
    }

    /**
     * @return the permisoActividad
     */
    public boolean isPermisoActividad() {
        if (this.isPermisoActividades() || this.isPermisoTiposDeActividad() || this.isPermisoTareas()) {
            return true;
        }
        return false;
    }

    /**
     * @return the permisoEnfermedad
     */
    public boolean isPermisoEnfermedad() {
        if (this.isPermisoEnfermedades() || this.isPermisoSintomas() || this.isPermisoTratamientos()) {
            return true;
        }
        return false;
    }

    /**
     * @return the permisoMonitoreos
     */
    public boolean isPermisoMonitoreos() {
        if (this.isPermisoMapas() || this.isPermisoAlarmas() || this.isPermisoMonitoreo()) {
            return true;
        }
        return false;
    }

    /**
     * @return the permisoParametrizacion
     */
    public boolean isPermisoParametrizacion() {
        if (this.isPermisoEmpleados() || this.isPermisoCargos() || this.isPermisoRecursos() || this.isPermisoBackupBD()) {
            return true;
        }
        return false;
    }

    public boolean tienePermiso(String perm) {
        if (getSessionBean1().getPermisos().containsKey(perm)) {
            return true;
        }
        return false;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.actividad.EstadoTarea;
import ibeeproject.model.actividad.TipoActividad;
import ibeeproject.model.actividad.TipoTarea;
import ibeeproject.model.apiar.Apiar;
import ibeeproject.model.apiar.Colmena;
import ibeeproject.model.apiar.Layout;
import ibeeproject.model.cajon.Cajon;
import ibeeproject.model.enfermedad.Enfermedad;
import ibeeproject.model.enfermedad.Sintoma;
import ibeeproject.model.enfermedad.Tratamiento;
import ibeeproject.model.familia.Familia;
import ibeeproject.model.persona.Empleado;
import ibeeproject.model.zona.Alarma;
import ibeeproject.model.zona.Zona;
import ibeeproject.persistencia.GestorAlarma;
import ibeeproject.persistencia.GestorApiar;
import ibeeproject.persistencia.GestorCajon;
import ibeeproject.persistencia.GestorColmena;
import ibeeproject.persistencia.GestorEmpleado;
import ibeeproject.persistencia.GestorEnfermedad;
import ibeeproject.persistencia.GestorEstadoTarea;
import ibeeproject.persistencia.GestorFamilia;
import ibeeproject.persistencia.GestorLayout;
import ibeeproject.persistencia.GestorSintoma;
import ibeeproject.persistencia.GestorTipoActividad;
import ibeeproject.persistencia.GestorTipoTarea;
import ibeeproject.persistencia.GestorTratamiento;
import ibeeproject.persistencia.GestorZona;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.model.SelectItem;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version Tareas.java
 * @version Created on 06-abr-2010, 20:21:08
 * @author farias.facundo
 */

public class Tareas extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private boolean consultarAll = true;
    private boolean consultar = false;
    private boolean modificar = false;
    private boolean agregar = false;
    private boolean eliminar = false;
    private boolean loadData = false;
    private ArrayList<SelectItem> estados = new ArrayList();
    private ArrayList<SelectItem> tiposDeActividad = new ArrayList();
    private ArrayList<SelectItem> tiposDeTarea = new ArrayList();
    private ArrayList<SelectItem> apiares = new ArrayList();
    private ArrayList<SelectItem> empleados = new ArrayList();
    private ArrayList<SelectItem> colmenas = new ArrayList();
    private ArrayList<SelectItem> alarmas = new ArrayList();
    private ArrayList<SelectItem> layouts = new ArrayList();
    private ArrayList<SelectItem> zonas = new ArrayList();
    private ArrayList<SelectItem> familias = new ArrayList();
    private ArrayList<SelectItem> cajones = new ArrayList();
    private ArrayList<SelectItem> enfermedades = new ArrayList();
    private ArrayList<SelectItem> tratamientos = new ArrayList();
    private ArrayList<SelectItem> sintomas = new ArrayList();

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }

    // </editor-fold>

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public Tareas() {
    }

    /**
     * <p>Callback method that is called whenever a page is navigated to,
     * either directly via a URL, or indirectly via page navigation.
     * Customize this method to acquire resources that will be needed
     * for event handlers and lifecycle methods, whether or not this
     * page is performing post back processing.</p>
     * 
     * <p>Note that, if the current request is a postback, the property
     * values of the components do <strong>not</strong> represent any
     * values submitted with this request.  Instead, they represent the
     * property values that were saved for this view when it was rendered.</p>
     */
    @Override
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here
        
        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("Tareas Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        this.doQuerys();
    }

    /**
     * <p>Callback method that is called after the component tree has been
     * restored, but before any event processing takes place.  This method
     * will <strong>only</strong> be called on a postback request that
     * is processing a form submit.  Customize this method to allocate
     * resources that will be required in your event handlers.</p>
     */
    @Override
    public void preprocess() {
    }

    /**
     * <p>Callback method that is called just before rendering takes place.
     * This method will <strong>only</strong> be called for the page that
     * will actually be rendered (and not, for example, on a page that
     * handled a postback and then navigated to a different page).  Customize
     * this method to allocate resources that will be required for rendering
     * this page.</p>
     */
    @Override
    public void prerender() {
    }

    /**
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called (regardless of whether
     * or not this was the page that was actually rendered).  Customize this
     * method to release resources acquired in the <code>init()</code>,
     * <code>preprocess()</code>, or <code>prerender()</code> methods (or
     * acquired during execution of an event handler).</p>
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
     * @return the consultar
     */
    public boolean isConsultar() {
        return consultar;
    }

    /**
     * @param consultar the consultar to set
     */
    public void setConsultar(boolean consultar) {
        this.consultar = consultar;
        this.setAgregar(false);
        this.setEliminar(false);
        this.setModificar(false);
        this.setConsultarAll(false);
    }

    /**
     * @return the modificar
     */
    public boolean isModificar() {
        return modificar;
    }

    /**
     * @param modificar the modificar to set
     */
    public void setModificar(boolean modificar) {
        this.modificar = modificar;
        this.setAgregar(false);
        this.setConsultar(false);
        this.setEliminar(false);
        this.setConsultarAll(false);
    }

    /**
     * @return the agregar
     */
    public boolean isAgregar() {
        return agregar;
    }

    /**
     * @param agregar the agregar to set
     */
    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
        this.setConsultar(false);
        this.setEliminar(false);
        this.setModificar(false);
        this.setConsultarAll(false);
    }

    /**
     * @return the eliminar
     */
    public boolean isEliminar() {
        return eliminar;
    }

    /**
     * @param eliminar the eliminar to set
     */
    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
        this.setAgregar(false);
        this.setConsultar(false);
        this.setModificar(false);
        this.setConsultarAll(false);
    }

    /**
     * @return the consultarAll
     */
    public boolean isConsultarAll() {
        return consultarAll;
    }

    /**
     * @param consultarAll the consultarAll to set
     */
    public void setConsultarAll(boolean consultarAll) {
        this.consultarAll = consultarAll;
        this.setConsultar(false);
        this.setAgregar(false);
        this.setEliminar(false);
        this.setModificar(false);
    }

        public void doQuerys() {
        if (this.isLoadData() == false) {
            this.getDBTiposDeActividad();
            this.getDBEstados();
            this.getDBEmpleados();
            this.setLoadData(true);
        }
    }

    /**
     * @return the loadData
     */
    public boolean isLoadData() {
        return loadData;
    }

    /**
     * @param loadData the loadData to set
     */
    public void setLoadData(boolean loadData) {
        this.loadData = loadData;
    }

    /**
     * @return the estados
     */
    public ArrayList<SelectItem> getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(ArrayList<SelectItem> estados) {
        this.estados = estados;
    }

    /**
     * @return the tiposDeActividad
     */
    public ArrayList<SelectItem> getTiposDeActividad() {
        return tiposDeActividad;
    }

    /**
     * @param tiposDeActividad the tiposDeActividad to set
     */
    public void setTiposDeActividad(ArrayList<SelectItem> tiposDeActividad) {
        this.tiposDeActividad = tiposDeActividad;
    }

    /**
     * @return the tiposDeTarea
     */
    public ArrayList<SelectItem> getTiposDeTarea() {
        return tiposDeTarea;
    }

    /**
     * @param tiposDeTarea the tiposDeTarea to set
     */
    public void setTiposDeTarea(ArrayList<SelectItem> tiposDeTarea) {
        this.tiposDeTarea = tiposDeTarea;
    }

    /**
     * @return the apiares
     */
    public ArrayList<SelectItem> getApiares() {
        return apiares;
    }

    /**
     * @param apiares the apiares to set
     */
    public void setApiares(ArrayList<SelectItem> apiares) {
        this.apiares = apiares;
    }

    /**
     * @return the empleados
     */
    public ArrayList<SelectItem> getEmpleados() {
        return empleados;
    }

    /**
     * @param empleados the empleados to set
     */
    public void setEmpleados(ArrayList<SelectItem> empleados) {
        this.empleados = empleados;
    }

    /**
     * @return the colmenas
     */
    public ArrayList<SelectItem> getColmenas() {
        return colmenas;
    }

    /**
     * @param colmenas the colmenas to set
     */
    public void setColmenas(ArrayList<SelectItem> colmenas) {
        this.colmenas = colmenas;
    }

    /**
     * @return the alarmas
     */
    public ArrayList<SelectItem> getAlarmas() {
        return alarmas;
    }

    /**
     * @param alarmas the alarmas to set
     */
    public void setAlarmas(ArrayList<SelectItem> alarmas) {
        this.alarmas = alarmas;
    }

    /**
     * @return the layouts
     */
    public ArrayList<SelectItem> getLayouts() {
        return layouts;
    }

    /**
     * @param layouts the layouts to set
     */
    public void setLayouts(ArrayList<SelectItem> layouts) {
        this.layouts = layouts;
    }

    /**
     * @return the zonas
     */
    public ArrayList<SelectItem> getZonas() {
        return zonas;
    }

    /**
     * @param zonas the zonas to set
     */
    public void setZonas(ArrayList<SelectItem> zonas) {
        this.zonas = zonas;
    }

    /**
     * @return the familias
     */
    public ArrayList<SelectItem> getFamilias() {
        return familias;
    }

    /**
     * @param familias the familias to set
     */
    public void setFamilias(ArrayList<SelectItem> familias) {
        this.familias = familias;
    }

    /**
     * @return the cajones
     */
    public ArrayList<SelectItem> getCajones() {
        return cajones;
    }

    /**
     * @param cajones the cajones to set
     */
    public void setCajones(ArrayList<SelectItem> cajones) {
        this.cajones = cajones;
    }

    /**
     * @return the enfermedades
     */
    public ArrayList<SelectItem> getEnfermedades() {
        return enfermedades;
    }

    /**
     * @param enfermedades the enfermedades to set
     */
    public void setEnfermedades(ArrayList<SelectItem> enfermedades) {
        this.enfermedades = enfermedades;
    }

    /**
     * @return the tratamientos
     */
    public ArrayList<SelectItem> getTratamientos() {
        return tratamientos;
    }

    /**
     * @param tratamientos the tratamientos to set
     */
    public void setTratamientos(ArrayList<SelectItem> tratamientos) {
        this.tratamientos = tratamientos;
    }

    /**
     * @return the sintomas
     */
    public ArrayList<SelectItem> getSintomas() {
        return sintomas;
    }

    /**
     * @param sintomas the sintomas to set
     */
    public void setSintomas(ArrayList<SelectItem> sintomas) {
        this.sintomas = sintomas;
    }


    /**
     * Setea inicialmente los Estados de las Actividades
     */
    private void getDBEstados() {
        GestorEstadoTarea gestor = new GestorEstadoTarea();
        EstadoTarea estado = new EstadoTarea();
        ArrayList datos = null;
        try {
            datos = gestor.getTodosParaResolucion();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBEstados");
        }
        for (int i = 0; i < datos.size(); i++) {
            estado = (EstadoTarea) datos.get(i);
            this.getEstados().add(new SelectItem(estado.getNumero(), estado.getDescripcion()));
        }
    }

    /**
     * Setea inicialmente los Estados de las Actividades
     */
    private void getDBTiposDeActividad() {
        GestorTipoActividad gestor = new GestorTipoActividad();
        TipoActividad tipoActividad = new TipoActividad();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBTiposDeActividad");
        }
        for (int i = 0; i < datos.size(); i++) {
            tipoActividad = (TipoActividad) datos.get(i);
            this.getTiposDeActividad().add(new SelectItem(tipoActividad.getCodigo(), tipoActividad.getDenominacion()));
        }
    }

    /**
     * Setea inicialmente los Apiares asociados las Actividades
     */
    private void getDBApiares() {
        GestorApiar gestor = new GestorApiar();
        Apiar apiar = new Apiar();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBApiares");
        }
        for (int i = 0; i < datos.size(); i++) {
            apiar = (Apiar) datos.get(i);
            this.getApiares().add(new SelectItem(apiar.getIdApiar(), apiar.getDenominacion()));
        }
    }

    /**
     * Setea inicialmente los Empleados de las Actividades
     */
    private void getDBEmpleados() {
        GestorEmpleado gestor = new GestorEmpleado();
        Empleado empleado = new Empleado();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBEmpleados");
        }
        for (int i = 0; i < datos.size(); i++) {
            empleado = (Empleado) datos.get(i);
            this.getEmpleados().add(new SelectItem(empleado.getLegajo(), empleado.getNombre() + " " + empleado.getApellido()));
        }
    }

    /**
     * Setea inicialmente el Array de Colmenas
     */
    private void getDBColmenas() {
        GestorColmena gestor = new GestorColmena();
        Colmena colmena = new Colmena();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBColmenas");
        }
        for (int i = 0; i < datos.size(); i++) {
            colmena = (Colmena) datos.get(i);
            this.getColmenas().add(new SelectItem(colmena.getIdColmena(), colmena.getDenominacion()));
        }
    }

    /**
     * Setea inicialmente los Empleados de las Actividades
     * Es public porque lo llamo al inicar el Popup
     */
    public void getDBTiposDeTarea(String codTipoActividad) {
        GestorTipoTarea gestor = new GestorTipoTarea();
        TipoTarea tipoTarea = new TipoTarea();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos(codTipoActividad);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBTiposDeTarea");
        }
        for (int i = 0; i < datos.size(); i++) {
            tipoTarea = (TipoTarea) datos.get(i);
            this.getTiposDeTarea().add(new SelectItem(tipoTarea.getCodigo(), tipoTarea.getDenominacion()));
        }
    }

    /**
     * Setea inicialmente el Array de Alarmas
     */
    private void getDBAlarmas() {
        GestorAlarma gestor = new GestorAlarma();
        Alarma alarma = new Alarma();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBAlarmas");
        }
        for (int i = 0; i < datos.size(); i++) {
            alarma = (Alarma) datos.get(i);
            this.getAlarmas().add(new SelectItem(alarma.getNumero(), alarma.getDenominacion()));
        }
    }

    /**
     * Setea inicialmente el Array de Layouts
     */
    private void getDBLayouts() {
        GestorLayout gestor = new GestorLayout();
        Layout layout = new Layout();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBLayouts");
        }
        for (int i = 0; i < datos.size(); i++) {
            layout = (Layout) datos.get(i);
            this.getLayouts().add(new SelectItem(layout.getIdLayout(), layout.getDenominacion()));
        }
    }

    /**
     * Setea inicialmente el Array de Zonas
     */
    private void getDBZonas() {
        GestorZona gestor = new GestorZona();
        Zona zona = new Zona();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBZonas");
        }
        for (int i = 0; i < datos.size(); i++) {
            zona = (Zona) datos.get(i);
            this.getZonas().add(new SelectItem(zona.getIdZona(), zona.getZona()));
        }
    }

    /**
     * Setea inicialmente el Array de Familias
     */
    private void getDBFamilias() {
        GestorFamilia gestor = new GestorFamilia();
        Familia familia = new Familia();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBFamilias");
        }
        for (int i = 0; i < datos.size(); i++) {
            familia = (Familia) datos.get(i);
            this.getFamilias().add(new SelectItem(familia.getNroFamilia(), familia.getDenominacion()));
        }
    }

    /**
     * Setea inicialmente el Array de Cajones
     */
    private void getDBCajones() {
        GestorCajon gestor = new GestorCajon();
        Cajon cajon = new Cajon();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBCajones");
        }
        for (int i = 0; i < datos.size(); i++) {
            cajon = (Cajon) datos.get(i);
            this.getCajones().add(new SelectItem(cajon.getNroCajon(), cajon.getDescripcion()));
        }
    }

    /**
     * Setea inicialmente el Array de Enfermedades
     */
    private void getDBEnfermedades() {
        GestorEnfermedad gestor = new GestorEnfermedad();
        Enfermedad enfermedad = new Enfermedad();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBEnfermedades");
        }
        for (int i = 0; i < datos.size(); i++) {
            enfermedad = (Enfermedad) datos.get(i);
            this.getEnfermedades().add(new SelectItem(enfermedad.getNumero(), enfermedad.getDenominacion()));
        }
    }

    /**
     * Setea inicialmente el Array de Tratamientos
     */
    private void getDBTratamientos() {
        GestorTratamiento gestor = new GestorTratamiento();
        Tratamiento tratamiento = new Tratamiento();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBTratamientos");
        }
        for (int i = 0; i < datos.size(); i++) {
            tratamiento = (Tratamiento) datos.get(i);
            this.getTratamientos().add(new SelectItem(tratamiento.getNumero(), tratamiento.getDenominacion()));
        }
    }

    /**
     * Setea inicialmente el Array de Sintomas
     */
    private void getDBSintomas() {
        GestorSintoma gestor = new GestorSintoma();
        Sintoma sintoma = new Sintoma();
        ArrayList datos = null;
        try {
            datos = gestor.getTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en la consulta BD: Tareas.getDBSintomas");
        }
        for (int i = 0; i < datos.size(); i++) {
            sintoma = (Sintoma) datos.get(i);
            this.getSintomas().add(new SelectItem(sintoma.getNumero(), sintoma.getDenominacion()));
        }
    }


    public void doQuerysOnDemand(TipoTarea tipoTarea) {
        // Ontengo los apiares necesarios desde la DB
        if (tipoTarea.isUsaApiar()) {
            this.getApiares().clear();
            this.getApiares().add(new SelectItem("00000","Seleccione.."));
            this.getDBApiares();
        }
        // Ontengo las colmenas necesarios desde la DB
        if (tipoTarea.isUsaColmena()) {
            this.getColmenas().clear();
            this.getColmenas().add(new SelectItem("00000","Seleccione.."));
            this.getDBColmenas();
        }
        // Ontengo las alarmas necesarios desde la DB
        if (tipoTarea.isUsaAlarma()) {
            this.getAlarmas().clear();
            this.getColmenas().add(new SelectItem("00000","Seleccione.."));
            this.getDBAlarmas();
        }
        // Ontengo los layouts necesarios desde la DB
        if (tipoTarea.isUsaLayout()) {
            this.getLayouts().clear();
            this.getColmenas().add(new SelectItem("00000","Seleccione.."));
            this.getDBLayouts();
        }
        // Ontengo las zonas necesarios desde la DB
        if (tipoTarea.isUsaZona()) {
            this.getZonas().clear();
            this.getColmenas().add(new SelectItem("00000","Seleccione.."));
            this.getDBZonas();
        }
        // Ontengo las familias necesarios desde la DB
        if (tipoTarea.isUsaFamilia()) {
            this.getFamilias().clear();
            this.getColmenas().add(new SelectItem("00000","Seleccione.."));
            this.getDBFamilias();
        }
        // Ontengo los cajones necesarios desde la DB
        if (tipoTarea.isUsaCajon()) {
            this.getCajones().clear();
            this.getColmenas().add(new SelectItem("00000","Seleccione.."));
            this.getDBCajones();
        }
        // Ontengo las enfermedades necesarios desde la DB
        if (tipoTarea.isUsaEnfermedad()) {
            this.getEnfermedades().clear();
            this.getColmenas().add(new SelectItem("00000","Seleccione.."));
            this.getDBEnfermedades();
        }
        // Ontengo los tratamientos necesarios desde la DB
        if (tipoTarea.isUsaTratamiento()) {
            this.getTratamientos().clear();
            this.getColmenas().add(new SelectItem("00000","Seleccione.."));
            this.getDBTratamientos();
        }
        // Ontengo los sintomas necesarios desde la DB
        if (tipoTarea.isUsaSintoma()) {
            this.getSintomas().clear();
            this.getColmenas().add(new SelectItem("00000","Seleccione.."));
            this.getDBSintomas();
        }
    }



}


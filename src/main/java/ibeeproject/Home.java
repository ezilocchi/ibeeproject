/*
 * Home.java
 *
 * Created on 27-jun-2009, 15:52:05
 * Copyright farias.facundo 
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.actividad.Actividad;
import ibeeproject.model.actividad.Tarea;
import ibeeproject.model.persona.Empleado;
import ibeeproject.model.soporte.UtilHome;
import ibeeproject.model.soporte.UtilHomeDetail;
import ibeeproject.model.zona.Alarma;
import ibeeproject.model.zona.Clima;
import ibeeproject.model.zona.Mensaje;
import ibeeproject.persistencia.GestorActividad;
import ibeeproject.persistencia.GestorAlarma;
import ibeeproject.persistencia.GestorClima;
import ibeeproject.persistencia.GestorMensaje;
import ibeeproject.persistencia.GestorTarea;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.component.UIParameter;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class Home extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private int __placeholder;
    private ArrayList lista = new ArrayList();
    private ArrayList lista_izq = new ArrayList();
    private UIParameter parametro;

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
    public Home() {
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
            log("Home Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        this.updatePanel();
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

    public void updatePanel() {
        this.getLista().clear();
        this.getLista_izq().clear();
        this.getDBActividad();
        this.getDBTarea();
        this.getDBClima();
        this.getDBAlarma();
        this.getDBMensajes();
        //this.getLista().add(new UtilHome("Mensajes"));
    }

    /**
     *
     */
    public void getDBActividad() {
        GestorActividad gestor = new GestorActividad();
        ArrayList actividades = null;
        try {
            actividades = gestor.getTopFive();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: Home! (getDBActividad)");
        }
        UtilHome util = new UtilHome("Actividades Pendientes");
        for (int i = 0; i < actividades.size(); i++) {
            Actividad act = (Actividad) actividades.get(i);
            UtilHomeDetail detalle = new UtilHomeDetail();
            detalle.setNumero(i);
            detalle.setTitle(act.getDenominacion());
            detalle.setDetail(act.getDescripcion());
            detalle.setIcon("/resources/icons/tag_orange.png");
            detalle.setLink("Resolver Actividad");
            detalle.setAction("actividades");
            detalle.setRender(true);
            if (act.getFechaEsperadaDeCierre() != null) {
                Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
                String s = formatter.format(act.getFechaEsperadaDeCierre());
                detalle.setFecha(s);
//                detalle.setFecha(DateFormat.getDateInstance(DateFormat.SHORT).format(act.getFechaEsperadaDeCierre()));
            }
            util.getDetalle().add(detalle);
        }
        this.getLista().add(util);
    }

    /**
     *
     */
    public void getDBTarea() {
        GestorTarea gestor = new GestorTarea();
        ArrayList tareas = null;
        Empleado e = (Empleado) getSessionBean1().getEmpleado();
        try {
            tareas = gestor.getTopFive(e.getLegajo());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: Home! (getDBTarea)");
        }
        UtilHome util = new UtilHome("Tareas Pendientes");
        for (int i = 0; i < tareas.size(); i++) {
            Tarea tar = (Tarea) tareas.get(i);
            UtilHomeDetail detalle = new UtilHomeDetail();
            detalle.setNumero(i);
            detalle.setTitle("Tarea" + i);
            detalle.setDetail(tar.getDescripcion());
            detalle.setIcon("/resources/icons/tag_yellow.png");
            detalle.setAction("tareas");
            detalle.setRender(true);
            detalle.setLink("Resolver Tarea");
            if (tar.getFechaPrevista() != null) {
//                detalle.setFecha(DateFormat.getDateInstance(DateFormat.SHORT).format(tar.getFechaPrevista()));
                Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
                String s = formatter.format(tar.getFechaPrevista());
                detalle.setFecha(s);
            }
            util.getDetalle().add(detalle);
        }
        this.getLista().add(util);
    }

    /**
     *
     */
    public void getDBAlarma() {
        GestorAlarma gestor = new GestorAlarma();
        ArrayList alarmas = null;
        try {
            alarmas = gestor.getTopTenPorEmpleado(getSessionBean1().getEmpleado());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: Home! (getDBAlarma)");
        }
        UtilHome util = new UtilHome("Alarmas Registrada para su cargo");
        for (int i = 0; i < alarmas.size(); i++) {
            Alarma alar = (Alarma) alarmas.get(i);
            UtilHomeDetail detalle = new UtilHomeDetail();
            detalle.setNumero(i);
            detalle.setTitle(alar.getDenominacion());
            detalle.setDetail(alar.getDescripcion());
            detalle.setIcon("/resources/icons/tag_green.png");
            detalle.setAction("alarmas");
            detalle.setRender(true);
            detalle.setLink("Ver Alarmas");
//            detalle.setFecha(DateFormat.getDateInstance(DateFormat.SHORT).format(alar.getFechaInicio()));
            Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
            String s = formatter.format(alar.getFechaInicio());
            detalle.setFecha(s);
            util.getDetalle().add(detalle);
        }
        this.getLista_izq().add(util);
    }

    /**
     *
     */
    public void getDBClima() {
        GestorClima gestor = new GestorClima();
        ArrayList climas = null;
        try {
            climas = gestor.getTopFive();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: Home! (getDBClima)");
        }
        UtilHome util = new UtilHome("Últimos Climas Registrados");
        for (int i = 0; i < climas.size(); i++) {
            Clima cli = (Clima) climas.get(i);
            UtilHomeDetail detalle = new UtilHomeDetail();
            detalle.setNumero(i);
            detalle.setTitle(cli.getLocalidad());
            detalle.setDetail("Temperatura: " + cli.getTemperatura() + "º / Humedad: " + cli.getHumedad());
            detalle.setIcon("/resources/icons/tag_purple.png");
            detalle.setAction("climas");
            detalle.setRender(true);
            detalle.setLink("Ver Climas");
//            detalle.setFecha(DateFormat.getDateInstance(DateFormat.SHORT).format(cli.getFecha()));
            Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
            String s = formatter.format(cli.getFecha());
            detalle.setFecha(s);
            util.getDetalle().add(detalle);
        }
        this.getLista_izq().add(util);
    }

    /**
     *
     */
    public void getDBMensajes() {
        GestorMensaje gestor = new GestorMensaje();
        ArrayList mensajes = null;
        try {
            mensajes = gestor.getTopTenParaEmpleado(getSessionBean1().getEmpleado());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("Error en conexion BD: Home! (getDBMensajes)");
        }
        UtilHome util = new UtilHome("Últimos Mensajes para su Cargo");
        for (int i = 0; i < mensajes.size(); i++) {
            Mensaje mensaje = (Mensaje) mensajes.get(i);
            UtilHomeDetail detalle = new UtilHomeDetail();
            detalle.setNumero(i);
            detalle.setTitle(mensaje.getTexto());
//            detalle.setDetail("Temperatura: " + cli.getTemperatura() + "º / Humedad: " + cli.getHumedad());
            detalle.setIcon("/resources/icons/tag_purple.png");
            detalle.setAction("mensajes");
            detalle.setRender(false);
            detalle.setLink("Ver Mensajes");
//            detalle.setFecha(DateFormat.getDateInstance(DateFormat.SHORT).format(mensaje.getFecha()));
            Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
            String s = formatter.format(mensaje.getFecha());
            detalle.setFecha(s);
            util.getDetalle().add(detalle);
        }
        this.getLista_izq().add(util);
    }

    /**
     * @return the lista
     */
    public ArrayList getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ArrayList lista) {
        this.lista = lista;
    }

    public String ir() {
//        System.out.println("value " + (String) this.parametro.getValue());
        return (String) this.parametro.getValue();
    }

    /**
     * @return the parametro
     */
    public UIParameter getParametro() {
        return parametro;
    }

    /**
     * @param parametro the parametro to set
     */
    public void setParametro(UIParameter parametro) {
        this.parametro = parametro;
    }

    /**
     * @return the lista_izq
     */
    public ArrayList getLista_izq() {
        return lista_izq;
    }

    /**
     * @param lista_izq the lista_izq to set
     */
    public void setLista_izq(ArrayList lista_izq) {
        this.lista_izq = lista_izq;
    }
}


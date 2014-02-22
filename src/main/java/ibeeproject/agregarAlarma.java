/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.zona.Alarma;
import ibeeproject.persistencia.GestorAlarma;
import java.util.Date;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version agregarAlarma.java
 * @version Created on 12-ene-2010, 22:03:52
 * @author carranza.matias
 */
public class agregarAlarma extends AbstractFragmentBean {

    private Alarma alarma;

    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public agregarAlarma() {
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
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
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
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
    }

    /**
     * @return the alarma
     */
    public Alarma getAlarma() {
        return alarma;
    }

    /**
     * @param alarma the alarma to set
     */
    public void setAlarma(Alarma alarma) {
        this.alarma = alarma;
    }

    public String actionAceptar() {
        // Acepta
        /* validaciones */
//        System.out.println("fecha: "+this.alarma.getFechaInicio() + " -- " + new Date());
        if (this.alarma.getFechaInicio().compareTo(new Date()) < 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("La fecha no puede ser menor o igual a hoy");
            message.setDetail("La fecha no puede ser menor o igual a hoy...");
            context.addMessage("agregar:agregarAlarma:fechaInicio", message);
            return null;
        }
        Double min = new Double("-20.0");
        Double max = new Double("50.0");
        Double rangoDesde = new Double(this.alarma.getRangoDesde());
        Double rangoHasta = new Double(this.alarma.getRangoHasta());


        if (rangoDesde.compareTo(min) < 0 || rangoDesde.compareTo(max) > 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Rango inválido, valores aceptados entre -20 y 50 grados");
            message.setDetail("Rango inválido, valores aceptados entre -20 y 50 grados...");
            context.addMessage("agregar:agregarAlarma:inputRangoDesde", message);
            return null;
        }


        if (rangoHasta.compareTo(min) < 0 || rangoHasta.compareTo(max) > 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Rango inválido, valores aceptados entre -20 y 50 grados");
            message.setDetail("Rango inválido, valores aceptados entre -20 y 50 grados...");
            context.addMessage("agregar:agregarAlarma:inputRangoHasta", message);
            return null;
        }

        if (rangoHasta.compareTo(rangoDesde) <= 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Rango Hasta debe ser mayor a Rango Desde");
            message.setDetail("Rango Hasta debe ser mayor a Rango Desde...");
            context.addMessage("agregar:agregarAlarma:inputRangoHasta", message);
            return null;
        }
        /* fin validaciones */

        // Busco el último número
        GestorAlarma gestorAlarma = new GestorAlarma();
        Alarma aux = (Alarma) gestorAlarma.getUltimo();

        // Registro la alarma

        alarma.setNumero(aux.getNumero() + 1);
        alarma.setOrigen("Usuario");
        alarma.setFechaCreacion(new Date());


        try {
            gestorAlarma.insertUno(alarma);
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean agregarAlarma");
        }
        consultarAlarmas consulta = (consultarAlarmas) getBean("consultarAlarmas");
        consulta.updateTable();
        consulta.queryAll_action();
        return "alarmas";
    }

    public String actionCancelar() {
        // Cancela
        consultarAlarmas cons = (consultarAlarmas) getBean("consultarAlarmas");
        cons.queryAll_action();
        return "alarmas";
    }
}

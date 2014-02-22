/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.enfermedad.Enfermedad;
import ibeeproject.model.enfermedad.Sintoma;
import ibeeproject.model.enfermedad.Tratamiento;
import ibeeproject.persistencia.GestorSintoma;
import ibeeproject.persistencia.GestorTratamiento;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.model.SelectItem;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version modificarEnfermedad.java
 * @version Created on 01-jul-2009, 22:23:42
 * @author farias.facundo
 */
public class consultarEnfermedad extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private Enfermedad enfermedad;
    private ArrayList<SelectItem> sintomas = new ArrayList();
    private ArrayList<SelectItem> tratamientos = new ArrayList();

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public consultarEnfermedad() {
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

    public String actionAceptar() {
        // Acepta
        consultarEnfermedades consultar = (consultarEnfermedades) getBean("consultarEnfermedades");
        consultar.queryAll_action();
        return null;
    }

    public String actionCancelar() {
        // Cancela
        consultarEnfermedades cons = (consultarEnfermedades) getBean("consultarEnfermedades");
        cons.queryAll_action();
        return null;
    }

    public void updateSintomas(int idEnfermedad)
    {
        this.getSintomas().clear();
        GestorSintoma gestor = new GestorSintoma();
        ArrayList sint = gestor.getTodosPorEnfermedad(idEnfermedad);
        for (int i = 0; i < sint.size(); i++) {
            Sintoma s = (Sintoma) sint.get(i);
            this.getSintomas().add(new SelectItem(s.getNumero(), s.getDenominacion()));
        }
    }

    public void updateTratamientos(int idEnfermedad)
    {
        this.getTratamientos().clear();
        GestorTratamiento gestor = new GestorTratamiento();
        ArrayList trat = gestor.getTodosPorEnfermedad(idEnfermedad);
        for (int i = 0; i < trat.size(); i++) {
            Tratamiento t = (Tratamiento) trat.get(i);
            this.getTratamientos().add(new SelectItem(t.getNumero(), t.getDenominacion()));
        }
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
        this.setSintomas(sintomas);
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
        this.setTratamientos(tratamientos);
    }

    /**
     * @return the enfermedad
     */
    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    /**
     * @param enfermedad the enfermedad to set
     */
    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    public boolean isTableSintomasEmpty()
    {
        if(this.getEnfermedad().getSintomas().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isTableTratamientosEmpty()
    {
        if(this.getEnfermedad().getTratamientos().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.apiar.Colmena;
import ibeeproject.persistencia.GestorColmena;
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
 * @version agregarColmena.java
 * @version Created on 30-ago-2009, 18:09:35
 * @author carranza.matias
 */
public class agregarColmena extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private Colmena colmena;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public agregarColmena() {
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

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
    }

    public String actionAceptar() {
        // Acepta

        FacesContext context = FacesContext.getCurrentInstance();
        if(colmena.getCajon().getNroCajon() == 0){
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Tiene que elegir un Cajón");
            message.setDetail("Tiene que elegir un Cajón...");
            context.addMessage("agregar:agregarColmena:cajonDefault",message);
            return null;
        }

        if(colmena.getFamilia().getNroFamilia() == 0){
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Tiene que elegir una Familia");
            message.setDetail("Tiene que elegir una Familia...");
            context.addMessage("agregar:agregarColmena:familiaDefault",message);
            return null;
        }

        // Busco el último número
        GestorColmena gestorColmena = new GestorColmena();
        try {
            gestorColmena.insertUno(colmena);
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean agregarColmena");
        }

        Colmenas colmenas = (Colmenas) getBean("Colmenas");
        colmenas.vaciarArrays();
        
        consultarColmenas consulta = (consultarColmenas) getBean("consultarColmenas");
        consulta.updateTable();
        consulta.queryAll_action();
        return "colmenas";
    }

    public String actionCancelar() {
        // Cancela
        consultarColmenas consulta = (consultarColmenas) getBean("consultarColmenas");
        consulta.queryAll_action();
        return "colmenas";
    }


    public String agregarCajon() {
        consultarCajones consultar = (consultarCajones) getBean("consultarCajones");
        consultar.add_action();

        agregarCajon agregar = (agregarCajon) getBean("agregarCajon");
        agregar.setVengoDeColmenas(true);
        
        consultar.add_action();
        
        return "nuevoCajon";
    }

    public String agregarFamilia() {
        consultarFamilia consultar = (consultarFamilia) getBean("consultarFamilia");
        consultar.add_action();

        agregarFamilia agregar = (agregarFamilia) getBean("agregarFamilia");
        agregar.setVengoDeColmenas(true);

        consultar.add_action();

        return "nuevoFamilia";
    }

    public boolean getCajonesSinAsignar() {
        boolean resultado = false;
        Colmenas colmenas = (Colmenas) getBean("Colmenas");
        if (!(colmenas.getCajonesSinAsignar().isEmpty())) {
            return true;
        }
        return resultado;
    }

    public boolean getFamiliasSinAsignar() {
        boolean resultado = false;
        Colmenas colmenas = (Colmenas) getBean("Colmenas");
        if (!(colmenas.getFamiliasSinAsignar().isEmpty())) {
            return true;
        }
        return resultado;
    }

    /**
     * @return the colmena
     */
    public Colmena getColmena() {
        return colmena;
    }

    /**
     * @param colmena the colmena to set
     */
    public void setColmena(Colmena colmena) {
        this.colmena = colmena;
        this.colmena.setFechaAlta(new Date());
    }
}

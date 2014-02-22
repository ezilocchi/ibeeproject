/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.apiar.Colmena;
import ibeeproject.model.cajon.Cajon;
import ibeeproject.model.cajon.EstadoCajon;
import ibeeproject.model.familia.Familia;
import ibeeproject.persistencia.GestorCajon;
import ibeeproject.persistencia.GestorColmena;
import ibeeproject.persistencia.GestorHistorialEstadoColmena;
import java.util.ArrayList;
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
 * @version modificarColmena.java
 * @version Created on 30-ago-2009, 18:10:18
 * @author carranza.matias
 */
public class modificarColmena extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private Colmena colmena;
    private boolean cambiarCajon;
    private Cajon cajonOriginal = new Cajon();
    private boolean cambiarFamilia;
    private Familia familiaOriginal = new Familia();
    private String nombreCambiarCajon = "Cambiar Cajón";
    private String nombreCambiarFamilia = "Cambiar Familia";

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public modificarColmena() {
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
        this.colmena = colmena.cloneColmena();
        if (colmena.getCajon() != null) {
            setCajonOriginal(colmena.getCajon());
        } else {
            setCambiarCajon(true);
        }
        if (colmena.getFamilia() != null) {
            setFamiliaOriginal(colmena.getFamilia());
        } else {
            setCambiarFamilia(true);
        }

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

    public String actionAceptar() {
        // Acepta

        FacesContext context = FacesContext.getCurrentInstance();
        if (colmena.getCajon() == null) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Tiene que elegir un Cajón");
            message.setDetail("Tiene que elegir un Cajón...");
            context.addMessage("modificar:modificarColmena:cajonDefault", message);
            return null;
        }

        if (colmena.getFamilia() == null) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Tiene que elegir una Familia");
            message.setDetail("Tiene que elegir una Familia...");
            context.addMessage("modificar:modificarColmena:familiaDefault", message);
            return null;
        }


        GestorColmena gestorColmena = new GestorColmena();
        try {
            gestorColmena.updateUnoMatias(getColmena());
            if (isCambiarCajon()) {
                EstadoCajon estadoCajon = new EstadoCajon();
                GestorCajon gestorCajon = new GestorCajon();

                //desasigno el cajon, cambio su estado
                estadoCajon.setNumero(1);
                cajonOriginal.setEstado(estadoCajon);

                gestorCajon.cambiarEstado(cajonOriginal);

                // asigno el nuevo cajon, con estado asignado
                Cajon cajon = colmena.getCajon();

                estadoCajon.setNumero(2);
                cajon.setEstado(estadoCajon);

                gestorCajon.cambiarEstado(cajon);
            }
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean modificarColmena");
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
        consultarColmenas cons = (consultarColmenas) getBean("consultarColmenas");
        cons.queryAll_action();
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

    public String cambiarCajon() {
        if (isCambiarCajon()) {
            setNombreCambiarCajon("Cambiar Cajón");
            setCambiarCajon(false);
        } else {
            setNombreCambiarCajon("Cancelar");
            setCambiarCajon(true);
        }
        return null;
    }

    public String agregarFamilia() {
        consultarFamilia consultar = (consultarFamilia) getBean("consultarFamilia");
        consultar.add_action();

        agregarFamilia agregar = (agregarFamilia) getBean("agregarFamilia");
        agregar.setVengoDeColmenas(true);

        consultar.add_action();

        return "nuevoFamilia";
    }

    public String cambiarFamilia() {
        if (isCambiarFamilia()) {
            setNombreCambiarFamilia("Cambiar Familia");
            setCambiarFamilia(false);
        } else {
            setNombreCambiarFamilia("Cancelar");
            setCambiarFamilia(true);
        }
        return null;
    }

    /**
     * @return the cambiarCajon
     */
    public boolean isCambiarCajon() {
        return cambiarCajon;
    }

    /**
     * @param cambiarCajon the cambiarCajon to set
     */
    public void setCambiarCajon(boolean cambiarCajon) {
        this.cambiarCajon = cambiarCajon;
    }

    /**
     * @return the cambiarFamilia
     */
    public boolean isCambiarFamilia() {
        return cambiarFamilia;
    }

    /**
     * @param cambiarFamilia the cambiarFamilia to set
     */
    public void setCambiarFamilia(boolean cambiarFamilia) {
        this.cambiarFamilia = cambiarFamilia;
    }

    /**
     * @param nombreCambiarCajon the nombreCambiarCajon to set
     */
    public void setNombreCambiarCajon(String nombreCambiarCajon) {

        this.nombreCambiarCajon = nombreCambiarCajon;

    }

    /**
     * @return the nombreCambiarCajon
     */
    public String getNombreCambiarCajon() {
        return nombreCambiarCajon;
    }

    /**
     * @return the nombreCambiarFamilia
     */
    public String getNombreCambiarFamilia() {
        return nombreCambiarFamilia;
    }

    /**
     * @param nombreCambiarFamilia the nombreCambiarFamilia to set
     */
    public void setNombreCambiarFamilia(String nombreCambiarFamilia) {
        this.nombreCambiarFamilia = nombreCambiarFamilia;
    }

    /**
     * @return the cajonOriginal
     */
    public Cajon getCajonOriginal() {
        return cajonOriginal;
    }

    /**
     * @param cajonOriginal the cajonOriginal to set
     */
    public void setCajonOriginal(Cajon cajonOriginal) {
        this.cajonOriginal = cajonOriginal;
    }

    /**
     * @return the familiaOriginal
     */
    public Familia getFamiliaOriginal() {
        return familiaOriginal;
    }

    /**
     * @param familiaOriginal the familiaOriginal to set
     */
    public void setFamiliaOriginal(Familia familiaOriginal) {
        this.familiaOriginal = familiaOriginal;
    }

    public String verHistoriales() {
        //Obtengo los datos
        GestorHistorialEstadoColmena gestorHistorialEstadoColmena = new GestorHistorialEstadoColmena();
        ArrayList estados = gestorHistorialEstadoColmena.getTodos(this.colmena.getIdColmena());

        // Hago visible la ventana
        ModalPopupHistorialColmena pop = (ModalPopupHistorialColmena) getBean("ModalPopupHistorialColmena");
        pop.setHistorialEstadoColmena(estados);
        pop.getPanelPopup1Bean().setShowModalPanel(true);
        pop.getPanelPopup1().setVisible(true);
        pop.getPanelPopup1().setRendered(true);

        // Configuro el Popup
        pop.setTitle("Historial de Estados");
        pop.setearTamaño(300, 200);
        return null;
    }
}

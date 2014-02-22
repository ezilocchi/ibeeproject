/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;

import ibeeproject.model.familia.Familia;
import ibeeproject.model.familia.MiembroFamilia;
import ibeeproject.model.soporte.UtilMiembroFamilia;
import ibeeproject.persistencia.GestorFamilia;
import ibeeproject.persistencia.GestorMiembroFamilia;
import java.util.ArrayList;
import javax.faces.FacesException;

/**
 * @version agregarFamilia.java
 * @version Created on 22-ago-2009, 15:40:32
 * @author burni.matias
 */
public class agregarFamilia extends AbstractFragmentBean {

    private Familia familia;
    private boolean vengoDeColmenas = false;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public agregarFamilia() {
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
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here


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
     * @return the familia
     */
    public Familia getFamilia() {
        return familia;
    }

    /**
     * @param familia the familia to set
     */
    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public boolean isVengoDeColmenas() {
        return vengoDeColmenas;
    }

    /**
     * @param vengoDeColmena the vengoDeColmenas to set
     */
    public void setVengoDeColmenas(boolean vengoDeColmenas) {
        this.vengoDeColmenas = vengoDeColmenas;
    }

    public String agregarMiembroFamilia() {
        modificarFamilia modificar = (modificarFamilia) getBean("modificarFamilia");
        modificar.setModifico(false);
        //recargo la cabecera, por si viene de MiembroFamilia.jsp (del eliminar llamo a este metodo)
        //esto lo hago para no tener que reescribir toda la logica
        consultarFamilia bean = (consultarFamilia) getBean("consultarFamilia");
        bean.setCabecera("Home &raquo; Familias &raquo; Consultar");

        // Hago visible la ventana
        ModalPopupMiembrosFamilia pop = (ModalPopupMiembrosFamilia) getBean("ModalPopupMiembrosFamilia");
        pop.getPanelPopup1Bean().setShowModalPanel(true);
        pop.getPanelPopup1().setVisible(true);
        pop.getPanelPopup1().setRendered(true);
        // Configuro el Popup
        pop.setTitle("Agregar Miembro de Familia");
        pop.setearTamaño(530, 470);

        GestorMiembroFamilia gestor1 = new GestorMiembroFamilia();
        ArrayList<MiembroFamilia> todosMiembrosFamilia = gestor1.getTodos();

        ArrayList um = new ArrayList<UtilMiembroFamilia>();

        for (int i = 0; i < todosMiembrosFamilia.size(); i++) {
            if (todosMiembrosFamilia.get(i) != null) {

                UtilMiembroFamilia utilMiembroFamilia = new UtilMiembroFamilia();
                //cargo el miembroFamilia
                utilMiembroFamilia.setMiembroFamilia((MiembroFamilia) todosMiembrosFamilia.get(i));

                //cargo el numero de familia al que esta asignado ese miembroFamilia
                GestorMiembroFamilia gestor2 = new GestorMiembroFamilia();
                ArrayList<UtilMiembroFamilia> aux = gestor2.getFamiliaAsignada();
                utilMiembroFamilia.setNumeroFamilia(aux.get(i).getNumeroFamilia());

                um.add(utilMiembroFamilia);
            }
        } //Primer FOR (i)
        pop.setUtilMiembrosFamilia(um);
        pop.setFamilia(familia);

        return null;
    }

    public String actionAceptar() throws Exception {
        GestorFamilia gestorFamilia = new GestorFamilia();
        Familia ultimaFamilia = (Familia) gestorFamilia.getUltimo();
        familia.setNroFamilia(ultimaFamilia.getNroFamilia() + 1);

        try {
            gestorFamilia.insertUno(familia);
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean agregarFamilia");
        }

        if (isVengoDeColmenas()) {
            Colmenas colmenas = (Colmenas) getBean("Colmenas");
            colmenas.getFamiliasSinAsignar().clear();
            colmenas.getDBFamiliasSinAsignar();
            consultarColmenas consultar = (consultarColmenas) getBean("consultarColmenas");
            consultar.setCabecera("Home &raquo; Colmenas &raquo; Agregar");
            return "vuelvoAgregar";
        }

        consultarFamilia consulta = (consultarFamilia) getBean("consultarFamilia");
        consulta.updateTable();
        consulta.queryAll_action();
        return null;
    }

    public String actionCancelar() {
        consultarFamilia cons = (consultarFamilia) getBean("consultarFamilia");
        cons.updateTable();
        cons.queryAll_action();
        return "familias";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.familia.Familia;
import ibeeproject.persistencia.GestorFamilia;
import ibeeproject.persistencia.GestorHistorialEstadoFamilia;
import java.util.ArrayList;
import javax.faces.FacesException;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version eliminarFamilia.java
 * @version Created on 22-ago-2009, 15:41:11
 * @author burni.matias
 */
public class eliminarFamilia extends AbstractFragmentBean {

    private Familia familia;

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    public eliminarFamilia() {
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



//    public void updateCampos() throws Exception {
//        GestorFamilia gestor = new GestorFamilia();
//        consultarFamilia bean = (consultarFamilia) getBean("consultarFamilia");
//        Familia fam = (Familia) gestor.getUno(bean.getIdFilaSeleccionada());
//        this.setearCampos(familia);
//    }
//
//    public void setearCampos(Familia f) {
//        this.caracteristicas = f.getCaracteristicas();
//        this.denominacion = f.getDemonimacion();
//        this.numeroFamilia = f.getNroFamilia();
//
//        Familias bean = (Familias) getBean("Familias");
//        this.estado = bean.getEstados().get(f.getEstado().getIdEstadoFamilia() - 1).getLabel();
//        this.especie = bean.getEspecies().get(f.getEspecie().getIdEspecie() - 1).getLabel();
//        this.fechaAlta = f.getFechaAlta();
//        this.updateMiembrosFamilia(f.getNroFamilia());
//    }
//
//    public void updateMiembrosFamilia(int nroFamilia) {
//        this.getMiembrosFamilia().clear();
//        GestorMiembroFamilia gestor = new GestorMiembroFamilia();
//        ArrayList mf = gestor.getTodosPorFamilia(nroFamilia);
//        for (int i = 0; i < mf.size(); i++) {
//            MiembroFamilia miembroFamilia = (MiembroFamilia) mf.get(i);
//            this.getMiembrosFamilia().add(new SelectItem(miembroFamilia.getIdMiembroFamilia(), miembroFamilia.getDenominacion()));
//        }
//    }
//
    public String actionAceptar() {
        // Acepta
        GestorFamilia gestor = new GestorFamilia();
        try {
            gestor.deleteUno(gestor.getUno(familia.getNroFamilia()));
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean eliminarFamili.actionAceptar");
        }
        consultarFamilia cons = (consultarFamilia) getBean("consultarFamilia");
        cons.updateTable();
        cons.queryAll_action();
        return "familias";
    }

    public String actionCancelar() {
        consultarFamilia cons = (consultarFamilia) getBean("consultarFamilia");
        cons.queryAll_action();
        return null;
    }

        public String verHistoriales() {
        //Obtengo los datos
        GestorHistorialEstadoFamilia gestorHistorialEstadoFamilia = new GestorHistorialEstadoFamilia();
        ArrayList estados = gestorHistorialEstadoFamilia.getTodos(this.familia.getNroFamilia());

        // Hago visible la ventana
        ModalPopupHistorialFamilia pop = (ModalPopupHistorialFamilia) getBean("ModalPopupHistorialFamilia");
        pop.setHistorialEstadoFamilia(estados);
        pop.getPanelPopup1Bean().setShowModalPanel(true);
        pop.getPanelPopup1().setVisible(true);
        pop.getPanelPopup1().setRendered(true);

        // Configuro el Popup
        pop.setTitle("Historial de Estados");
        pop.setearTamaño(300, 200);
        return null;
    }

}

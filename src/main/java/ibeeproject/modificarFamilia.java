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
import ibeeproject.persistencia.GestorHistorialEstadoFamilia;
import ibeeproject.persistencia.GestorMiembroFamilia;
import java.util.ArrayList;
import javax.faces.FacesException;

/**
 * @version modificarFamilia.java
 * @version Created on 22-ago-2009, 15:41:28
 * @author burni.matias
 */
public class modificarFamilia extends AbstractFragmentBean {

    private Familia familia;
    private boolean modifico = true;

    private void _init() throws Exception {
    }
    // </editor-fold>

    public modificarFamilia() {
    }

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

    }

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

    public String agregarMiembroFamilia() {
        this.modifico = true;
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
        ArrayList<MiembroFamilia> todosMiembrosPorFamilia = this.familia.getMiembrosFamilia();

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

                //cargo seleccionado. Verifico si ya estaba, y si estaba hago seleccionado=true
                for (int j = 0; j < todosMiembrosPorFamilia.size(); j++) {
                    if (todosMiembrosFamilia.get(i).getIdMiembroFamilia() == todosMiembrosPorFamilia.get(j).getIdMiembroFamilia()) {
                        utilMiembroFamilia.setSeleccionado(true);
                    }
                // El else no lo hago porque por xonstructor el Objeto tiene false
                } //Segundo FOR (j)
                um.add(utilMiembroFamilia);
            }
        } //Primer FOR (i)
        pop.setUtilMiembrosFamilia(um);
        pop.setFamilia(familia);

        return null;
    }

    public String actionAceptar() throws Exception {
        GestorFamilia gestor = new GestorFamilia();
        try {
            //hay que recorrer el array de miembros y agregarlo si hace falta
            gestor.updateUno(familia);
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean modificarFamilia.actionAceptar");
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

    /**
     * @return the modifico
     */
    public boolean isModifico() {
        return modifico;
    }

    /**
     * @param modifico the modifico to set
     */
    public void setModifico(boolean modifico) {
        this.modifico = modifico;
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

package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import ibeeproject.model.persona.Cargo;
import ibeeproject.model.persona.Empleado;
import ibeeproject.model.soporte.UtilRecursoXCargo;
import ibeeproject.persistencia.GestorCargo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;

/**
 * @version agregarCargo.java
 * @version Created on 06/03/2010, 15:43:17
 * @author burni.matias
 */
public class agregarCargo extends AbstractFragmentBean {

    private Cargo cargo;
    private UIParameter parametro;
    private ArrayList recursoXCargos;

    private void _init() throws Exception {
    }
    // </editor-fold>

    public agregarCargo() {
    }

    @Override
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();

        try {
            _init();
        } catch (Exception e) {
            log("Page1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }
        try {
            setRecursoXCargos(UtilRecursoXCargo.dameTodos());
        } catch (Exception ex) {
            Logger.getLogger(agregarCargo.class.getName()).log(Level.SEVERE, null, ex);
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
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
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
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String actionAceptar() throws Exception {
        GestorCargo gestorCargo = new GestorCargo();

        if (gestorCargo.getUno(cargo.getDenominacion()) != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Esa denominacion ya existe");
            message.setDetail("Ya existe un Cargo con esa Denominaci&oacute;n");
            context.addMessage("agregar:agregarCargo:inputDescripci&oacute;n", message);
            return null;
        }


        Cargo ultimoCargo = (Cargo) gestorCargo.getUltimo();
        cargo.setIdCargo(ultimoCargo.getIdCargo() + 1);

        ArrayList recursos = new ArrayList();
        recursos = UtilRecursoXCargo.dameHabilitados(recursoXCargos);
        cargo.setRecursos(recursos);

        try {
            gestorCargo.insertUno(cargo);
        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
            System.out.print("Error en la inserción BD: ManagedBean agregarCargo");
        }

        consultarCargos consulta = (consultarCargos) getBean("consultarCargos");
        consulta.updateTable();
        consulta.queryAll_action();
        return "cargos";
    }

    public String actionCancelar() throws Exception {
        consultarCargos cons = (consultarCargos) getBean("consultarCargos");
        cons.updateTable();
        cons.queryAll_action();
        return null;
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
     * @return the recursoXCargos
     */
    public ArrayList getRecursoXCargos() {
        return recursoXCargos;
    }

    /**
     * @param recursoXCargos the recursoXCargos to set
     */
    public void setRecursoXCargos(ArrayList recursoXCargos) {
        this.recursoXCargos = recursoXCargos;
    }
}

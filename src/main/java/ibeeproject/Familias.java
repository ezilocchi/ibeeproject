/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import ibeeproject.model.familia.Especie;
import ibeeproject.model.familia.EstadoFamilia;
import ibeeproject.model.familia.MiembroFamilia;
import ibeeproject.persistencia.GestorEspecie;
import ibeeproject.persistencia.GestorEstadoFamilia;
import ibeeproject.persistencia.GestorMiembroFamilia;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.model.SelectItem;

/**
 *
 * @version Familias.java
 * @version Created on 03-ago-2009, 19:42:05
 * @author burni.matias
 */
public class Familias extends AbstractPageBean {

    private void _init() throws Exception {
    }

    // </editor-fold>
    private int __placeholder;
    private boolean consultarAll = true;
    private boolean consultar = false;
    private boolean modificar = false;
    private boolean agregar = false;
    private boolean eliminar = false;
    private ArrayList<SelectItem> especies = new ArrayList();
    private ArrayList<SelectItem> estados = new ArrayList();
    private ArrayList<SelectItem> miembrosFamilia = new ArrayList();
    //esta bandera es para que no recargue los combos dos veces cuando se ejecuta el init()
    //porque pasa por el init una vez que entra al JSP y una vez que entra al Fragment

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public Familias() {
    }

    @Override
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();

        try {
            _init();
        } catch (Exception e) {
            log("Familias Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        // aca deberia vaciar y cargar los arrayList desde los getores para luego cargar los combos
        this.vaciarArrays();

            try {
                this.getDBEspecies();
                this.getDBEstados();
                this.getDBMiembrosFamilia();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("error en el Familias.init, cargando los combos");
            }
    }

    @Override
    public void preprocess() {
    }

    @Override
    public void prerender() {
    }

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
     * @return the __placeholder
     */
    public int get_placeholder() {
        return __placeholder;
    }

    /**
     * @param _placeholder the __placeholder to set
     */
    public void set_placeholder(int _placeholder) {
        this.__placeholder = _placeholder;
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
    }

    /**
     * @return the especies
     */
    public ArrayList<SelectItem> getEspecies() {
        return especies;
    }

    /**
     * @param especies the especies to set
     */
    public void setEspecies(ArrayList<SelectItem> especies) {
        this.especies = especies;
    }

    /**
     * @return the miembrosFamilia
     */
    public ArrayList<SelectItem> getMiembrosFamilia() {
        return miembrosFamilia;
    }

    /**
     * @param miembrosFamilia the miembrosFamilia to set
     */
    public void setMiembrosFamilia(ArrayList<SelectItem> miembrosFamilia) {
        this.miembrosFamilia = miembrosFamilia;
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
    }

    public void vaciarArrays() {
        this.getEspecies().clear();
        this.getEstados().clear();
        this.getMiembrosFamilia().clear();
    }

    public void getDBEspecies() throws Exception {
        GestorEspecie gestor = new GestorEspecie();
        Especie especie = new Especie();
        ArrayList arrEspecies = gestor.getTodos();
        for (int i = 0; i < arrEspecies.size(); i++) {
            especie = (Especie) arrEspecies.get(i);
            this.getEspecies().add(new SelectItem(especie.getIdEspecie(), especie.getDenominacion()));
        }
    }

    public void getDBEstados() throws Exception {
        GestorEstadoFamilia gestor = new GestorEstadoFamilia();
        EstadoFamilia estado = new EstadoFamilia();
        ArrayList arrEstados = gestor.getTodos();
        for (int i = 0; i < arrEstados.size(); i++) {
            estado = (EstadoFamilia) arrEstados.get(i);
            this.getEstados().add(new SelectItem(estado.getIdEstadoFamilia(), estado.getDenominacion()));
        }
    }

    public void getDBMiembrosFamilia() throws Exception {
        GestorMiembroFamilia gestor = new GestorMiembroFamilia();
        MiembroFamilia miembroFamilia = new MiembroFamilia();
        ArrayList arrMiembroFamilia = gestor.getTodos();
        for (int i = 0; i < arrMiembroFamilia.size(); i++) {
            miembroFamilia = (MiembroFamilia) arrMiembroFamilia.get(i);
            this.getMiembrosFamilia().add(new SelectItem(miembroFamilia.getIdMiembroFamilia(), miembroFamilia.getDenominacion()));
        }
    }

}


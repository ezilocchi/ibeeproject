/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibeeproject;

import com.icesoft.faces.component.jsfcl.data.PopupBean;
import com.icesoft.faces.component.panelpopup.PanelPopup;
import com.icesoft.faces.webapp.xmlhttp.PersistentFacesState;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;
import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import com.icesoft.faces.async.render.RenderManager;
import com.icesoft.faces.async.render.Renderable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.faces.FacesException;
import java.net.URL;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version ModalPopupActualizacionCliama.java
 * @version Created on 24/03/2010, 13:43:18
 * @author CyberShark
 */
public class ModalPopupActualizacionClima extends AbstractFragmentBean implements Renderable {

    private String window;
    private ActualizarClima actualizar;
    private String log;
    private BufferedReader br;
    private String aux;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    private void _init() throws Exception {
    }
    // </editor-fold>
    private PopupBean panelPopup1Bean = new PopupBean();

    public PopupBean getPanelPopup1Bean() {
        return panelPopup1Bean;
    }

    public void setPanelPopup1Bean(PopupBean pb) {
        this.panelPopup1Bean = pb;
    }
    private PanelPopup panelPopup1 = new PanelPopup();

    public PanelPopup getPanelPopup1() {
        return panelPopup1;
    }

    public void setPanelPopup1(PanelPopup pp) {
        this.panelPopup1 = pp;
    }

    public ModalPopupActualizacionClima() throws RenderingException {
        state = PersistentFacesState.getInstance();
        render = new RenderManager();

    }
    private String title;

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
        this.panelPopup1Bean.setShowModalPanel(false);
        this.panelPopup1.setRendered(false);
        this.panelPopup1.setVisible(false);

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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String cerrarPopup() {
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
        return null;
    }

    public void setearTamaño(int ancho, int alto) {
        String style = " width: " + ancho + "px; height: " + alto + "px;";
        this.panelPopup1.setStyle(style);
    }

    public String aceptaPopup() {
        return null;
    }

    public RenderManager getRender() {
        return render;
    }

    public void setRender(RenderManager render) {
        this.render = render;
    }

    public PersistentFacesState getState() {
        return state;
    }

    public void setState(PersistentFacesState state) {
        this.state = state;
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

    public String actualizar() {
        Runtime runtime = Runtime.getRuntime();
        try {


            URL url = getClass().getResource("WeatherService.jar");
            String path1 = url.getPath().replace('/', '\\');
            path1 = path1.substring(1);
//            System.out.println("Path : " + path1);
            String pathCompleto = "java -jar " + path1;
            String[] eje = new String[3];
            eje[0] = "java";
            eje[1] = "-jar";
            eje[2] = path1;

            runtime.traceInstructions(true);
            runtime.traceMethodCalls(true);

            Process p = runtime.exec("java -jar c://WeatherService.jar");

            try {

                // Se obtiene el stream de salida del programa
                java.io.InputStream is = p.getInputStream();

                /* Se prepara un bufferedReader para poder leer la salida más comodamente. */
                br = new BufferedReader(new InputStreamReader(is));
                aux = " ";
                this.start();

            } catch (Exception e) {
                // Excepciones si hay algún problema al arrancar el ejecutable o al leer su salida.*/
                e.printStackTrace();
            }

        } catch (Exception a) {
            System.out.println("Erro en Actualizar Clima");
            a.printStackTrace();
        }
        return null;
    }
    private static final int PAUSE_AMOUNT_S = 300; // milliseconds to pause between progress increases
    protected int percent = 0;
    protected boolean isRunning = false;
    protected Thread progressThread;
    protected PersistentFacesState state;
    protected RenderManager render;

    public int getPercent() {
        return percent;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Method to start the thread process
     * It is assumed the front end pages will handle the validity of when this method
     *  is called
     * For example, a start button will not be displayed if the progress bar is
     *  already running
     */
    public void start() {
        percent = 0;
        isRunning = true;

        // Create the progress thread
        progressThread = new Thread(new Runnable() {

            public void run() {
                String aux2 = "";
                for (int i = 0; i < 100; i++) {
                    // Break the progress loop if we are asked to
                    if (!isRunning) {
                        break;
                    }

                    // Sleep for the specified amount of time
                    try {
                        Thread.sleep(PAUSE_AMOUNT_S);
                    } catch (InterruptedException failedSleep) {
                    }

                    // Increase the finished percent, and render the page
                    // The standard approach would be to use the RenderManager
                    // But since this is a simple tutorial, we'll instead go directly
                    //  to the PersistentFacesState
                    try {
                        percent += 5;

                        // Se lee la primera linea
                        aux2 = br.readLine();
                        if (aux2 == " ") {
                            aux = aux + '\n' + aux2;
                            ModalPopupActualizacionClima.this.setLog(aux);
                            percent = 100;
                        }

                        // Stop running if we reach the end
                        state.executeAndRender();
                        if (percent >= 100) {
                            isRunning = false;
                            ModalPopupActualizacionClima.this.getPanelPopup1Bean().setShowModalPanel(false);
                            ModalPopupActualizacionClima.this.getPanelPopup1().setRendered(false);
                        }


//                        state.renderLater();
                    } catch (Exception failedProgress) {
                        failedProgress.printStackTrace();
                    }
                }
            }
        });

        progressThread.start();
    }

    /**
     * Method to stop the progress thread
     * This will toggle the isRunning state to false, and interrupt the thread
     * Since the isRunning boolean is checked inside the progress loop in the
     *  thread, switching it to false will stop the process from running
     */
    public void stop() {
        // Set the thread to stop running
        isRunning = false;

        // Interrupt the thread so the user gets an immediate response
        if (progressThread != null) {
            progressThread.interrupt();
        }
    }

    public void renderingException(RenderingException arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String acepta() {
        this.getPanelPopup1Bean().setShowModalPanel(false);
        this.getPanelPopup1().setVisible(false);
        this.panelPopup1.setRendered(false);
        return null;
    }
}

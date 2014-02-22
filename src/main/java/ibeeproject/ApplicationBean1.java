/*
 * ApplicationBean1.java
 *
 * Created on 27-jun-2009, 15:48:40
 * Copyright farias.facundo 
 */
package ibeeproject;

import com.sun.rave.web.ui.appbase.AbstractApplicationBean;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

/**
 * <p>Application scope data bean for your application.  Create properties
 *  here to represent cached data that should be made available to all users
 *  and pages in the application.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 */
public class ApplicationBean1 extends AbstractApplicationBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    /**
     * <p>Construct a new application data bean instance.</p>
     */
    public ApplicationBean1() {
    }

    /**
     * <p>This method is called when this bean is initially added to
     * application scope.  Typically, this occurs as a result of evaluating
     * a value binding or method binding expression, which utilizes the
     * managed bean facility to instantiate this bean and store it into
     * application scope.</p>
     * 
     * <p>You may customize this method to initialize and cache application wide
     * data values (such as the lists of valid options for dropdown list
     * components), or to allocate resources that are required for the
     * lifetime of the application.</p>
     */
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here

        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("ApplicationBean1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

    // </editor-fold>
    // Perform application initialization that must complete
    // *after* managed components are initialized
    // TODO - add your own initialization code here
    }

    /**
     * <p>This method is called when this bean is removed from
     * application scope.  Typically, this occurs as a result of
     * the application being shut down by its owning container.</p>
     * 
     * <p>You may customize this method to clean up resources allocated
     * during the execution of the <code>init()</code> method, or
     * at any later time during the lifetime of the application.</p>
     */
    public void destroy() {
    }

    /**
     * <p>Return an appropriate character encoding based on the
     * <code>Locale</code> defined for the current JavaServer Faces
     * view.  If no more suitable encoding can be found, return
     * "UTF-8" as a general purpose default.</p>
     *
     * <p>The default implementation uses the implementation from
     * our superclass, <code>AbstractApplicationBean</code>.</p>
     */
    public String getLocaleCharacterEncoding() {
        return super.getLocaleCharacterEncoding();
    }

    /**
     * Output Jasper Report
     *
     * @param filename Precompiled report filename
     * @param type Content type of report ("application/pdf" or "text/html")
     * @param data Collection of value objects
     */
    public void jasperReport(String reporte, String tipo, String dataSourceName, Map<String, String> params) throws ClassNotFoundException {
        ExternalContext econtext = getExternalContext();
        InputStream inputStream = ApplicationBean1.class.getResourceAsStream(reporte);
        if (inputStream == null) {
            throw new ClassNotFoundException("Archivo " + reporte + " no se encontrÃ³");
        }
        FacesContext fcontext = FacesContext.getCurrentInstance();
        try {
            JRExporter exporter = null;
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dataSourceName);
            Connection conn = ds.getConnection();

            final JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, params, conn);
            HttpServletResponse response = (HttpServletResponse) econtext.getResponse();
            HttpServletRequest request = (HttpServletRequest) econtext.getRequest();
            response.setContentType(tipo);
            if ("application/pdf".equals(tipo)) {
                exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());

                String reportDest = "C:\\reporttest.pdf";
                JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);
                //JasperViewer.viewReport(jasperPrint);

                Thread t = new Thread(new Runnable() {

                    public void run() {
                        JasperViewer.viewReport(jasperPrint,false);
                    }
                });
                t.start();
            }
            if ("text/html".equals(tipo)) {
                response.setHeader("Content-Disposition", "inline; filename=\"report.html\"");
                exporter = new JRHtmlExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/image?image=");
                if (exporter != null) {
                    exporter.exportReport();
                }
                fcontext.responseComplete();
            }
        } catch (Exception ex) {
            System.out.println("Fallo en la Generación de Reporte: ApplicationBean1.jasperReport()");
            ex.printStackTrace();
        }
    }
}

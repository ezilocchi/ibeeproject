/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibeeproject.model.soporte;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author farias.facundo
 */
public class UtilGraph {
        //list of the Labels for the x axis of the chart
    private List labels = new ArrayList();

    //The list of the legend label for the chart
    private List legendLabels = new ArrayList();

    //The list of the data used by the chart
    private List data = new ArrayList();

    //The list of the colors used by the chart
    private List paints = new ArrayList();

    /**
     * @return the labels
     */
    public List getLabels() {
        return labels;
    }

    /**
     * @param labels the labels to set
     */
    public void setLabels(List labels) {
        this.labels = labels;
    }

    /**
     * @return the legendLabels
     */
    public List getLegendLabels() {
        return legendLabels;
    }

    /**
     * @param legendLabels the legendLabels to set
     */
    public void setLegendLabels(List legendLabels) {
        this.legendLabels = legendLabels;
    }

    /**
     * @return the data
     */
    public List getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List data) {
        this.data = data;
    }

    /**
     * @return the paints
     */
    public List getPaints() {
        return paints;
    }

    /**
     * @param paints the paints to set
     */
    public void setPaints(List paints) {
        this.paints = paints;
    }




}

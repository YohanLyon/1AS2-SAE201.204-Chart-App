/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae201204.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author p2103678
 */
public class Pie {
    
    private ChartPanel panel;
    
    public Pie() {
        
        DefaultPieDataset dataset=new DefaultPieDataset();  
        int randint = (int) (Math.random() * 100);
            
        dataset.setValue("Températeure Positive", 100-randint);  
        dataset.setValue("Températeure Négatif", randint); 
            
        JFreeChart chart = ChartFactory.createPieChart3D(  
            "Random Pie Chart",
            dataset,
            true,
            true,
            false);  
            
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setStartAngle(290);
        plot.setForegroundAlpha(0.5f);
            
        this.panel = new ChartPanel(chart);
    }

    public ChartPanel getPanel() {
        return panel;
    }
}

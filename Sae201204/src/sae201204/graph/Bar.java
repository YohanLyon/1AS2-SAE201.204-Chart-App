/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae201204.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author p2103678
 */
public class Bar {
    
    private ChartPanel panel;
    
    public Bar() {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
            
        int randint = (int) (Math.random() * 100);
            
        dataset.addValue(randint, "Température Positive", "Degrés");
        dataset.addValue(100-randint, "Température Négative", "Degrés");
            
        JFreeChart chart = ChartFactory.createBarChart3D("Random Bar Chart", "Température", "Valeur Température", dataset);
                     
        this.panel = new ChartPanel(chart);
    }

    public ChartPanel getPanel() {
        return panel;
    }
}

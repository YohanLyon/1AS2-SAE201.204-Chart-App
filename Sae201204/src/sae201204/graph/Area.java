/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae201204.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author p2103678
 */
public class Area {
    private ChartPanel panel;
    
    public Area() {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
            
        for (int i = 1; i < 32; i++) {
            int randint = (int) (Math.random() * 30) + 10;
            dataset.addValue(randint, "Journalier", i+"");
            randint = (int) (Math.random() * 30) + 10;
            dataset.addValue(randint, "Mensuel", i+"");
        }
            
        JFreeChart chart = ChartFactory.createAreaChart("Random Temp Area Chart", "Numéro de jour ou de mois", "Degrés", dataset);
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setForegroundAlpha(0.5f);
        
        this.panel = new ChartPanel(chart);
    }

    public ChartPanel getPanel() {
        return panel;
    }
}

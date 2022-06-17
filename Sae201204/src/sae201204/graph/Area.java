/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae201204.graph;

import java.sql.ResultSet;
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
            dataset.addValue(randint, "Température", i+"");
            randint = (int) (Math.random() * 30) + 10;
            dataset.addValue(randint, "Humidité", i+"");
        }
            
        JFreeChart chart = ChartFactory.createAreaChart("Random Temp Area Chart", "Numéro de jour", "Degrés / Pourcentage", dataset);
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setForegroundAlpha(0.5f);
        
        this.panel = new ChartPanel(chart);
    }
    public Area(ResultSet rs) {
        try {
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 

            while(rs.next()) {

                dataset.addValue(rs.getInt("valeur_temp"), "Température",rs.getDate(2)+" "+ rs.getTime(2));
                dataset.addValue(rs.getInt("valeur_humidite"), "Humidité", rs.getDate(2)+" "+ rs.getTime(2));
                
                System.out.println(rs.getDate(2)+" "+ rs.getTime(2));
            }

            JFreeChart chart = ChartFactory.createAreaChart("Mesure Temp Area Chart", "Date", "Degrés / Pourcentage", dataset);
            CategoryPlot plot = chart.getCategoryPlot();
            plot.setForegroundAlpha(0.5f);
        
            this.panel = new ChartPanel(chart);
            
        } catch (Exception ex) {}
    }
    

    public ChartPanel getPanel() {
        return panel;
    }
}

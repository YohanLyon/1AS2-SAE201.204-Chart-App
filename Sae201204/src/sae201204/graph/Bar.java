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
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author p2103678
 */
public class Bar {
    
    private ChartPanel panel;
    
    public Bar() {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
            
        int randint = (int) (Math.random() * 100);
            
        dataset.addValue(randint, "Températeure > 30 degrés", "Degrés");
        dataset.addValue(100-randint, "Températeure <= 30 degrés", "Degrés");
        
        randint = (int) (Math.random() * 100);
        
        dataset.addValue(randint, "Humidité > 50 %", "Pourcentage");
        dataset.addValue(100-randint, "Humidité <= 50 %", "Pourcentage");
            
        JFreeChart chart = ChartFactory.createBarChart3D("Random Bar Chart", "Température", "Nombre de valeur de Température / Humidité", dataset);
                     
        this.panel = new ChartPanel(chart);
    }

    public Bar(ResultSet rs) {
        try {
            int i = 0;
            int s = 0;
            int hi = 0;
            int hs = 0;

            while(rs.next()) {

                if (rs.getInt("valeur_temp") <= 30) {
                    i++;
                } else {
                    s++;
                }
                
                if (rs.getInt("valeur_humidite") <= 50) {
                    hi++;
                } else {
                    hs++;
                }
            }

            DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 

            dataset.addValue(s, "Températeure > 30 degrés", "Degrés");
            dataset.addValue(i, "Températeure <= 30 degrés", "Degrés");

            dataset.addValue(hs, "Humidité > 50 %", "Pourcentage");
            dataset.addValue(hi, "Humidité <= 50 %", "Pourcentage");

            JFreeChart chart = ChartFactory.createBarChart3D("Mesure Bar Chart", "Température", "Nombre de valeur de Température / Humidité", dataset);

            this.panel = new ChartPanel(chart);
            
        } catch (Exception ex) {}
    }
    
    public ChartPanel getPanel() {
        return panel;
    }
}

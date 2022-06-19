package sae201204.graph;

import java.sql.ResultSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Cette classe permet de créer un diagramme de type Pie
 */
public class Pie {
    
    private ChartPanel panel;
    
    /**
     * Ce constructeur crée un  diagramme avec des valeurs aléatoires
     */
    public Pie() {
        
        DefaultPieDataset dataset=new DefaultPieDataset();  
        int randint = (int) (Math.random() * 100);
            
        dataset.setValue("Températeure > 30 degrés", 100-randint);  
        dataset.setValue("Températeure <= 30 degrés", randint); 
            
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
    
    /**
     * Ce constructeur crée un diagramme en fonction des résultats d'une base de données
     * @param rs Resultat d'une requete SQL
     */
    public Pie(ResultSet rs) {
        try {
            int i = 0;
            int s = 0;

            while(rs.next()) {

                if (rs.getInt("valeur_temp") <= 30) {
                    i++;
                } else {
                    s++;
                }
            }

            DefaultPieDataset dataset = new DefaultPieDataset( ); 

            dataset.setValue("Températeure > 30 degrés", s);  
            dataset.setValue("Températeure <= 30 degrés", i); 

            JFreeChart chart = ChartFactory.createPieChart3D(  
                "Mesure Pie Chart",
                dataset,
                true,
                true,
                false); 

            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setStartAngle(290);
            plot.setForegroundAlpha(0.5f);

            this.panel = new ChartPanel(chart);
        } catch (Exception ex) {}
        
    }

    /**
     * Cette méthode retourne le pannel du diagramme
     * @return ChartPanel
     */
    public ChartPanel getPanel() {
        return panel;
    }
}

package sae201204.graph;

import java.sql.ResultSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Cette classe permet de créer un diagramme de type Line
 */
public class Line {
    private ChartPanel panel;
    
    /**
     * Ce constructeur crée un  diagramme avec des valeurs aléatoires
     */
    public Line() {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
            
        for (int i = 1; i < 32; i++) {
            int randint = (int) (Math.random() * 30) + 10;
            dataset.addValue(randint, "Température", i+"");
            randint = (int) (Math.random() * 30) + 10;
            dataset.addValue(randint, "Humidité", i+"");
        }
            
        JFreeChart chart = ChartFactory.createLineChart("Random Temp Line Chart", "Numéro de jour", "Degrés / Pourcentage", dataset);
        
        this.panel = new ChartPanel(chart);
    }
    
    /**
     * Ce constructeur crée un diagramme en fonction des résultats d'une base de données
     * @param rs Resultat d'une requete SQL
     */
    public Line(ResultSet rs) {
        try {
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 

            while(rs.next()) {

                dataset.addValue(rs.getInt("valeur_temp"), "Température",rs.getDate(2)+" "+ rs.getTime(2));
                dataset.addValue(rs.getInt("valeur_humidite"), "Humidité", rs.getDate(2)+" "+ rs.getTime(2));
                
                System.out.println(rs.getDate(2)+" "+ rs.getTime(2));
            }

            JFreeChart chart = ChartFactory.createLineChart("Mesure Temp Line Chart", "Date", "Degrés / Pourcentage", dataset);
        
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

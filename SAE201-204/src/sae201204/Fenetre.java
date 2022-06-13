package sae201204;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import java.text.DecimalFormat;  
  
import javax.swing.JFrame;  
import javax.swing.SwingUtilities;  
import javax.swing.WindowConstants;  
  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.labels.PieSectionLabelGenerator;  
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;  
import org.jfree.chart.plot.PiePlot;  
import org.jfree.data.general.DefaultPieDataset;  
import org.jfree.data.general.PieDataset;  

public class Fenetre extends JFrame  {
    private static final long serialVersionUID = 6294689542092367723L;  
    public Fenetre() {
     
        super("test");  
        // Create dataset  
        PieDataset dataset = createDataset();  

        // Create chart  
        JFreeChart chart = ChartFactory.createPieChart(  
            "Pie Chart Example",  
            dataset,  
            false,   
            false,  
            false);  
        
        // Create Panel  
        ChartPanel panel = new ChartPanel(chart);  
        setContentPane(panel);  
        this.pack();
    }
  
  private PieDataset createDataset() {  
  
    DefaultPieDataset dataset=new DefaultPieDataset();  
    dataset.setValue("80-100", 50);  
    dataset.setValue("70-50", 150);  
    return dataset;  
  }  
}
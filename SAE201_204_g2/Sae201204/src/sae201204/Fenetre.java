package sae201204;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import java.text.DecimalFormat;  
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
        
import javax.swing.JFrame;  
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;  
import javax.swing.WindowConstants;  
  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.labels.PieSectionLabelGenerator;  
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;  
import org.jfree.chart.plot.PiePlot;  
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;  
import org.jfree.data.general.PieDataset;  


public class Fenetre extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 6294689542092367723L;  
    
    private JMenuBar menu;
    
    private JMenu donnees;
    private JMenu onglet;
    private JMenu aleaOnglet;
    
    private JMenuItem barGraphBd;
    private JMenuItem pieGraphBd;
    
    private JMenuItem barGraph;
    private JMenuItem pieGraph;
    
    private JMenuItem create;
    
    public Fenetre() {
        super("SAE 201-204");  
        
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(screenSize.width+10, screenSize.height-33));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initMenu();
        
        initialisation();
        this.pack();
        
        barGraph.addActionListener(this);
        pieGraph.addActionListener(this);
        
        barGraphBd.addActionListener(this);
        pieGraphBd.addActionListener(this);
        
    }
    
    private void initMenu(){
        
        barGraphBd = new JMenuItem("BarGraphe");
        pieGraphBd = new JMenuItem("PieGraphe");
        
        barGraph = new JMenuItem("BarGraphe");
        pieGraph = new JMenuItem("PieGraphe");
        
        create = new JMenuItem("Créer");
        
        donnees = new JMenu("Table");
        onglet = new JMenu("Base de données");
        aleaOnglet = new JMenu("Aléatoire");
        
        onglet.add(barGraphBd);
        onglet.add(pieGraphBd);
        
        aleaOnglet.add(barGraph);
        aleaOnglet.add(pieGraph);
        
        donnees.add(create);
        
        menu = new JMenuBar();
        
        menu.add(donnees);
        menu.add(onglet);
        menu.add(aleaOnglet);
        
        this.setJMenuBar(menu);
    }
    
    private void initialisation() {
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == barGraph) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
            
            int randint = (int) (Math.random() * 100);
            
            dataset.addValue(randint, "Femme", "Genre");
            dataset.addValue(100-randint, "Homme", "Genre");
            
            JFreeChart chart = ChartFactory.createBarChart3D("Random Bar Chart", "Population", "Nombre", dataset);
                  
            
            ChartPanel panel = new ChartPanel(chart);  
            setContentPane(panel);  
            this.revalidate();
        }
        if (e.getSource() == pieGraph) {
            
            DefaultPieDataset dataset=new DefaultPieDataset();  
            int randint = (int) (Math.random() * 100);
            
            dataset.setValue("Femmes", 100-randint);  
            dataset.setValue("Hommes", randint); 
            
            JFreeChart chart = ChartFactory.createPieChart3D(  
                "Random Pie Chart",
                dataset,
                true,
                true,
                false);  
            
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setStartAngle(290);
            plot.setForegroundAlpha(0.5f);
            
            ChartPanel panel = new ChartPanel(chart);
            setContentPane(panel);  
            this.revalidate();
        }
        if (e.getSource() == pieGraphBd) {
             
            try {
                
                ResultSet rs= DBConnection.getConnectionBD().get("SELECT * FROM Professeurs");
                int m = 0;
                int f = 0;
                while(rs.next()) {
                    if (rs.getString(4).equals("F")) {
                        f++;
                    } else {
                        m++;
                    }
                }
                
                DefaultPieDataset dataset=new DefaultPieDataset();  

                dataset.setValue("Femmes", f);  
                dataset.setValue("Hommes", m); 

                JFreeChart chart = ChartFactory.createPieChart3D(  
                    "EDT Professeurs Pie Chart",
                    dataset,
                    true,
                    true,
                    false);  

                PiePlot plot = (PiePlot) chart.getPlot();
                plot.setStartAngle(290);
                plot.setForegroundAlpha(0.5f);

                ChartPanel panel = new ChartPanel(chart);  

                setContentPane(panel);  
                this.revalidate();
                        
            } catch (Exception ex) { } 
            
            
        }
        if (e.getSource() == barGraphBd) {
            try {
                
                ResultSet rs= DBConnection.getConnectionBD().get("SELECT * FROM Professeurs");
                int m = 0;
                int f = 0;
                while(rs.next()) {
                    if (rs.getString(4).equals("F")) {
                        f++;
                    } else {
                        m++;
                    }
                }

                DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
            
                int randint = (int) (Math.random() * 100);

                dataset.addValue(f, "Femme", "Genre");
                dataset.addValue(m, "Homme", "Genre");
                
                JFreeChart chart = ChartFactory.createBarChart3D("EDT Professeurs Bar Chart", "Population", "Nombre", dataset);
                
                ChartPanel panel = new ChartPanel(chart);  
                setContentPane(panel);  
                this.revalidate();

                } catch (Exception ex) { } 
        }
    }
  
  
}
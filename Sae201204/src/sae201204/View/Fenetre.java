package sae201204.View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Point;
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
import javax.swing.JPanel;
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
import sae201204.Capteur.AnalogI2CInput;
import sae201204.Capteur.DHT22;
import sae201204.DataBase.DBConnection;
import sae201204.DataBase.Instance;
import sae201204.View.Admin.CreateUser;
import sae201204.View.Admin.EditRole;
import sae201204.graph.Area;
import sae201204.graph.Bar;
import sae201204.graph.Line;
import sae201204.graph.Pie;


public class Fenetre extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 6294689542092367723L;  
    
    private JMenuBar menu;
    
    private JMenu donnees;
    private JMenu onglet;
    private JMenu aleaOnglet;
    private JMenu capteur;
    private JMenu profil;
    
    private JMenuItem barGraphBd;
    private JMenuItem pieGraphBd;
    
    private JMenuItem barGraph;
    private JMenuItem pieGraph;
    private JMenuItem lineGraph;
    private JMenuItem areaGraph;
    
    private JMenuItem editRole;
    private JMenuItem createUser;
    
    private JMenuItem run;
    private JMenuItem stop;
    
    private JMenuItem roleUser;
    private JMenuItem deconnect;
    
    public Fenetre() {
        
        super("SAE 201-204");  
        
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(screenSize.width+10, screenSize.height-33));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialisationBD();
        initMenu();
  
        this.pack();
        
        barGraph.addActionListener(this);
        pieGraph.addActionListener(this);
        lineGraph.addActionListener(this);
        areaGraph.addActionListener(this);
        
        barGraphBd.addActionListener(this);
        pieGraphBd.addActionListener(this);
        
        deconnect.addActionListener(this);
        
        editRole.addActionListener(this);
        createUser.addActionListener(this);
        
        run.addActionListener(this);
        stop.addActionListener(this);
        
    }
    
    private void initialisationBD() {
        if (ViewConnection.selectedPlateforme == 1) {
             
                try {
                    DBConnection.getConnectionBD().update("CREATE TABLE IF NOT EXISTS Mesure (ID  INT AUTO_INCREMENT, valeur_temp int, valeur_humidite int, localisation varchar(25), CONSTRAINT PK_Mesure PRIMARY KEY (ID))");
                    
                    
                    
                } catch (Exception ex) {
                        return;
                }
            
        }
    }
    
    private void initMenu(){
        
        barGraphBd = new JMenuItem("BarGraphe");
        pieGraphBd = new JMenuItem("PieGraphe");

        barGraph = new JMenuItem("BarGraphe");
        pieGraph = new JMenuItem("PieGraphe");
        lineGraph = new JMenuItem("LineGraphe");
        areaGraph = new JMenuItem("AreaGraphe");
        
        createUser = new JMenuItem("Create User");
        editRole = new JMenuItem("Edit User");
        
        run = new JMenuItem("Start");
        stop = new JMenuItem("Stop");
        
        roleUser = new JMenuItem(Instance.role);
        deconnect = new JMenuItem("Se Déconnecter");
        
        donnees = new JMenu("Admin");
        onglet = new JMenu("Base de données");
        aleaOnglet = new JMenu("Aléatoire");
        capteur = new JMenu("Capteur");
        profil = new JMenu("Profil : " + Instance.pseudo);
        
        onglet.add(barGraphBd);
        onglet.add(pieGraphBd);
        
        aleaOnglet.add(barGraph);
        aleaOnglet.add(pieGraph);
        aleaOnglet.add(lineGraph);
        aleaOnglet.add(areaGraph);
        
        donnees.add(createUser);
        donnees.add(editRole);
        
        capteur.add(run);
        capteur.add(stop);
        
        profil.add(roleUser);
        profil.add(deconnect);
        
        menu = new JMenuBar();
        menu.setLayout(new GridBagLayout());
        
        if (Instance.role.equals("Admin")) {
            menu.add(donnees);
        }
        menu.add(onglet);
        menu.add(aleaOnglet);
        if (ViewConnection.selectedPlateforme == 0) {
            menu.add(capteur);
        }
        menu.add(profil);
        
        this.setJMenuBar(menu);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == barGraph) {
            
            Bar graph = new Bar();       
            setContentPane(graph.getPanel());  
            this.revalidate();
        }
        if (e.getSource() == pieGraph) {
            
            Pie graph = new Pie();
            setContentPane(graph.getPanel());  
            this.revalidate();
        }
        if (e.getSource() == lineGraph) {
            
            Line graph = new Line();
            setContentPane(graph.getPanel());  
            this.revalidate();
        }
        
        if (e.getSource() == areaGraph) {
            
            Area graph = new Area();
            setContentPane(graph.getPanel());  
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
        
        if (e.getSource() == deconnect) {
            Instance.pseudo = "Visiteur";
            Instance.role = "Visiteur";
            
            ViewConnection win = new ViewConnection();
            win.setVisible(true);
            this.dispose(); 
        }
        
        if (e.getSource() == editRole) {
            
            EditRole win = new EditRole();
            win.setVisible(true);
            
            this.revalidate();
        }
        
        if (e.getSource() == createUser) {
            
            sae201204.View.Admin.CreateUser win = new CreateUser();
            win.setVisible(true);
            
            this.revalidate();
        }
        
        if (e.getSource() == stop) {
            DHT22.started = false;
        }
        
        if (e.getSource() == run) {
            DHT22.started = true;
            
            try {
                AnalogI2CInput an = new AnalogI2CInput(0);
            } catch (Exception ex) {
                System.out.println(ex);
            }
            DHT22 dht22 = new DHT22(5);
        
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(1000);
                    dht22.getTemperatureAndHumidity();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
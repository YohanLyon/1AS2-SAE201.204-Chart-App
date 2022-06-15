/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae201204.View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sae201204.DataBase.DBConnection;
import sae201204.View.Panel.InscriptionPanel;

/**
 *
 * @author p2103678
 */
public class ViewConnection extends JFrame implements ActionListener {
    
    JLabel title;
    JButton inscription, connexion;
    JComboBox plateforme;
    
    InscriptionPanel pano = new InscriptionPanel();
    
    public static int selectedPlateforme;
    
    public ViewConnection() {
        super("Connexion");  
        this.setSize(new Dimension(300,150));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initialisation(); 
        
        inscription.addActionListener(this);
        connexion.addActionListener(this);
        this.pano.getConfirm().addActionListener(this);
    }
    
    private void initialisation() {
        title = new JLabel("Rejoignez-nous");
        inscription = new JButton("Inscription");
        connexion = new JButton("Connexion");
        plateforme = new JComboBox();
        plateforme.addItem("RaspBerry");
        plateforme.addItem("Ordinateur IUT");
        plateforme.addItem("Autre");
        
        graphPlacement();
    }
    
    private void graphPlacement() {
        
        JPanel pano=new JPanel();
        pano.setLayout(new GridBagLayout());
        GridBagConstraints gc=new GridBagConstraints();
        gc.fill=GridBagConstraints.BOTH;
        
        gc.gridx=0;
        gc.gridy=0;
        gc.gridwidth = 2;
        gc.insets = new Insets(0, 50, 15, 50);
        pano.add(title, gc);
        
        gc.gridx=0;
        gc.gridy=1;
        gc.gridwidth = 2;
        gc.insets = new Insets(0, 0, 15, 0);
        pano.add(plateforme, gc);
        
        gc.gridx=0;
        gc.gridy=2;
        gc.gridwidth = 1;
        pano.add(inscription, gc);
        
        gc.gridx=1;
        gc.gridy=2;
        gc.gridwidth = 1;
        pano.add(connexion, gc);
        
        this.setContentPane(pano);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inscription) {
            this.selectedPlateforme = plateforme.getSelectedIndex();
            
            this.setSize(new Dimension(500,300));
            this.setContentPane(this.pano);           
            this.revalidate(); 
        }
        
        if (e.getSource() == connexion) {
            this.selectedPlateforme = plateforme.getSelectedIndex();
        }
        
        if (e.getSource() == this.pano.getConfirm()) {
            
            
                if (pano.getPseudoUser().equals(new String()) ) {
                    JOptionPane.showMessageDialog(new JFrame(),"Veuillez indiquer un pseudo !","Erreur",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (pano.getPseudoUser().length() > 19 ) {
                    JOptionPane.showMessageDialog(new JFrame(),"Votre pseudo doit contenir moins de 20 caractère","Erreur",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (pano.getMdpUser().equals(new String()) || pano.getMdpConfirmUser().equals(new String()) ) {
                    JOptionPane.showMessageDialog(new JFrame(),"Veuillez indiquer un mot de passe !","Erreur",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (pano.getMdpUser().length() > 19 ) {
                    JOptionPane.showMessageDialog(new JFrame(),"Votre mot de passe doit contenir moins de 20 caractère","Erreur",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!pano.getMdpUser().equals(pano.getMdpConfirmUser())) {
                    JOptionPane.showMessageDialog(new JFrame(),"Les mots de passes ne sont pas les mêmes !","Erreur",JOptionPane.ERROR_MESSAGE);
                    return;     
                }
                
                try {
                    DBConnection.getConnectionBD().update("CREATE TABLE IF NOT EXISTS UserData (id  INT AUTO_INCREMENT, pseudo varchar(20), mdp varchar(20), roles varchar(20), CONSTRAINT pk_User PRIMARY KEY (id))");
                    DBConnection.getConnectionBD().update("INSERT INTO UserData VALUES (0,\'"+pano.getPseudoUser()+"\',\'"+pano.getMdpUser()+"\', \'Visiteur\')");

                } catch (Exception ex) {
                    return;
                }
                Fenetre win = new Fenetre();
                win.setVisible(true);
                this.dispose();
                
        }
    }
    
}

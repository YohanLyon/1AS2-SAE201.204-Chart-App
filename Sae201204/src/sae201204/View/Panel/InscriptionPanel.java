/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae201204.View.Panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author p2103678
 */
public class InscriptionPanel extends JPanel{
    
    JLabel title = new JLabel("Inscription");
    
    JLabel pseudo = new JLabel("Pseudo : ");
    JLabel mdp= new JLabel("Mot de Passe : ");
    JLabel mdpConfirm= new JLabel("Confirmer le Mot de Passe : ");
    
    JTextField pseudoUser;
    JPasswordField mdpUser;
    JPasswordField mdpConfirmUser;
    
    JButton confirm = new JButton("Valider");
    
    public InscriptionPanel() {
        
        pseudoUser = new JTextField(15);
        mdpUser = new JPasswordField (15);
        mdpConfirmUser = new JPasswordField (15);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc=new GridBagConstraints();
        gc.fill=GridBagConstraints.BOTH;
        
        gc.gridx=0;
        gc.gridy=0;
        gc.gridwidth = 2;
        gc.insets = new Insets(0, 150, 35, 50);
        this.add(title, gc);
        
        gc.gridx=0;
        gc.gridy=1;
        gc.gridwidth = 1;
        gc.insets = new Insets(0, 0, 15, 10);
        this.add(pseudo, gc);
        
        gc.gridx=1;
        gc.gridy=1;
        gc.gridwidth = 1;
        this.add(pseudoUser, gc);
        
        gc.gridx=0;
        gc.gridy=2;
        gc.gridwidth = 1;
        this.add(mdp, gc);
        
        gc.gridx=1;
        gc.gridy=2;
        gc.gridwidth = 1;
        this.add(mdpUser, gc);
        
        gc.gridx=0;
        gc.gridy=3;
        gc.gridwidth = 1;
        this.add(mdpConfirm, gc);
        
        gc.gridx=1;
        gc.gridy=3;
        gc.gridwidth = 1;
        this.add(mdpConfirmUser, gc);
        
        gc.gridx=0;
        gc.gridy=4;
        gc.gridwidth = 2;
        this.add(confirm, gc); 
    }

    public JButton getConfirm() {
        return confirm;
    }

    public String getPseudoUser() {
        return pseudoUser.getText();
    }

    public String getMdpUser() {
        return new String(mdpUser.getPassword());
    }

    public String getMdpConfirmUser() {
        return new String(mdpConfirmUser.getPassword());
    }
    
    
    
    
}

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
 * Cette classe permet d'initialiser un panel d'inscription utilisateur
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
    JButton back = new JButton("Retour");
    
    /**
     * Ce constructeur initialise les elements du pannel et les place dans la fenetre selon les containtes exposé
     */
    
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
        
        gc.gridx=1;
        gc.gridy=4;
        gc.gridwidth = 1;
        this.add(confirm, gc); 
        
        gc.gridx=0;
        gc.gridy=4;
        gc.gridwidth = 1;
        this.add(back, gc); 
    }
    
    /**
     * Cette méthode permet de récuperer le bouton de validation
     * @return JButton
     */
    public JButton getConfirm() {
        return confirm;
    }

    /**
     * Cette méthode permet de récuperer le pseudo
     * @return String
     */
    public String getPseudoUser() {
        return pseudoUser.getText().trim();
    }

    /**
     * Cette méthode permet de récuperer le mot de passe
     * @return String
     */
    public String getMdpUser() {
        return new String(mdpUser.getPassword());
    }

    /**
     * Cette méthode permet de récuperer le mot de passe de confirmation
     * @return String
     */
    public String getMdpConfirmUser() {
        return new String(mdpConfirmUser.getPassword());
    }

    /**
     * Cette méthode permet de récuperer le bouton retour
     * @return JButton
     */
    public JButton getBack() {
        return back;
    }
    
}

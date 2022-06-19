package sae201204.View.Admin;

import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sae201204.DataBase.DBConnection;
import sae201204.DataBase.Instance;

/**
 * Cette classe permet de supprimer un utilisateur de la ase de données
 */
public class DeleteUser extends javax.swing.JFrame {

    /**
     * Ce constructeur permet d'initialiser la fenetre de suppression d'utilisateur
     */
    public DeleteUser() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Gestion et Administration");
        String strInsert = "SELECT * FROM UserData";
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        try {
            ResultSet rsLogin = DBConnection.getConnectionBD().get(strInsert);
           
            while (rsLogin.next()) {
                String ligne[]={rsLogin.getString("id"),rsLogin.getString("pseudo"),rsLogin.getString("roles")};
                model.addRow(ligne);
 
            }     
            rsLogin.close();
            
            
        } catch (Exception ex) {
            String ligne[]={"None","None","None"};
            model.addRow(ligne);
        } 
        
        jTable1.setModel(model);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Cette méthode permet l'initialisation de tous les composants de la fenetre
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "User", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Supprimer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });


        jTextField1.setText("Inserez le pseudo du membre");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addGap(18, 18, 18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Cette méthode gère les evennement du bouton validation. Elle vérifie que l'utilisateur 
     * supprimé n'est pas l'auteur de l'instance ouverte. Si ce n'est pas l'auteurn, il supprime l'utilisateur
     * @param evt Event
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String strInsert2 = "SELECT * FROM UserData WHERE pseudo=\'"+jTextField1.getText().trim()+"\'";
        try {
            ResultSet rsLogin2 = DBConnection.getConnectionBD().get(strInsert2);
            if (rsLogin2.next()) {
                if (Instance.pseudo.equals(rsLogin2.getString("pseudo"))) {
                    JOptionPane.showMessageDialog(new JFrame(),"Vous ne pouvez pas vous supprimer vous-même","Erreur",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } 
            DBConnection.getConnectionBD().update("DELETE FROM UserData WHERE pseudo=\'"+jTextField1.getText().trim()+"\'"); 
        } catch (Exception ex) {
            return;
        }  
        
        String strInsert = "SELECT * FROM UserData";
        DefaultTableModel model = new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "User", "Role"
            }
        );
        
        try {
            ResultSet rsLogin = DBConnection.getConnectionBD().get(strInsert);
           
            while (rsLogin.next()) {
                String ligne[]={rsLogin.getString("id"),rsLogin.getString("pseudo"),rsLogin.getString("roles")};
                model.addRow(ligne);
 
            }     
            rsLogin.close();
            
            
        } catch (Exception ex) {
            String ligne[]={"None","None","None"};
            model.addRow(ligne);
        } 
        
        jTable1.setModel(model);
        
        JOptionPane.showMessageDialog(new JFrame(),"Utilisateur supprimé","Success",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * Cette méthode permet de définir le text de ce composant à vide en cas d'interaction entrante
     * Elle permet la simplicité coté utilisateur.
     * @param evt Event
     */
    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        jTextField1.setText(new String(""));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DeleteUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

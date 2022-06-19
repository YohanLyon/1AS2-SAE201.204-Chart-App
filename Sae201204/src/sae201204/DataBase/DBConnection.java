package sae201204.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sae201204.View.ViewConnection;

/**
 * Cette classe permet la connexion à la Base de données selon la plateforme utilisée
 */

public class DBConnection {
    
    private Connection connection = null;
    public static DBConnection con = null;
    private int plateforme = ViewConnection.selectedPlateforme;
    
    /**
     * Ce constructeur par défaut va initialisé la connexion en fonction de la plateforme
     * voulu. Elle dépend donc de la Classe ViewConnexion. En cas de problème de connexion
     * à la abse de données, elle affiche un message sur l'interface utilisateur.
     */

    private DBConnection() {
        switch (this.plateforme) {
            case 0:
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Weather", "AdminG", "AdminG");
                    this.con = this;
                    
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(new JFrame(),"Base de données introuvable !","Erreur",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            break;
            
            case 1:
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");  

                    connection = DriverManager.getConnection("jdbc:mysql://iutbg-lamp.univ-lyon1.fr:3306/p2103678", "p2103678", "12103678");
                    this.con = this;
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(),"Base de données introuvable !","Erreur",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            break;
            
            default:
                
                JOptionPane.showMessageDialog(new JFrame(),"Pas de base de données définit pour cette plateforme !","Erreur",JOptionPane.ERROR_MESSAGE);
            break;
        }
    }
    
    /**
     * Cette méthode permet de vérifier si il y a déjà une Instance de la connexion qui a été créée.
     * Dans le cas contraire, elle l'a créée sinon elle la retourne.
     * @return DBConnection
     * @throws ClassNotFoundException
     * @throws SQLException 
     */

    public static DBConnection getConnectionBD() throws ClassNotFoundException, SQLException{
        if(con == null){
            return new DBConnection();
        }
        return con;
    }
    
    /**
     * Cette méthode permet d'obtenir un retour d'une fonction SQL de lecture de table.
     * Elle est donc utilisé simplement pour visualiser les données de la base.
     * @param requetteSQL Requete SQL de lecture
     * @return ResultSet
     */
    
    public ResultSet get(String requetteSQL){
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(requetteSQL);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    /**
     * Cette méthode permet le traitement de toutes les requetes SQL de modification de table, de base ou de ligne.
     * Elle conserne les INSERT, UPDATE, CREATE, DELETE et ALTER
     * @param requetteSQL Requete SQL de modification
     */
    public void update(String requetteSQL){
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(requetteSQL);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    /**
     * Cette méthode permet d'obtenir la connection à la base de données
     * @return Connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Cette méthode permet de fermer la connexion à la base.
     * @throws SQLException 
     */
    public void destroy() throws SQLException {
        connection.close();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author p2103678
 */

public class DBConnection {
    
    private Connection connection = null;
    public static DBConnection con = null;
    private int plateforme = ViewConnection.selectedPlateforme;
    
    private String base = new String("Weather");

    private DBConnection() {
        switch (this.plateforme) {
            case 0:
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Weather", "root", "qwerty");
                    this.con = this;
                    
                } catch (Exception ex) {
                    System.out.println("RaspBerry System");
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(new JFrame(),"Base de données introuvable !","Erreur",JOptionPane.ERROR_MESSAGE);
                }
            break;
            
            case 1:
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");  

                    connection = DriverManager.getConnection("jdbc:mysql://iutbg-lamp.univ-lyon1.fr:3306/p2103678", "p2103678", "12103678");
                    this.con = this;
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(),"Base de données introuvable !","Erreur",JOptionPane.ERROR_MESSAGE);
                }
            break;
            
            default:
                
                JOptionPane.showMessageDialog(new JFrame(),"Pas de base de données définit pour cette plateforme !","Erreur",JOptionPane.ERROR_MESSAGE);
            break;
        }

    }

    public static DBConnection getConnectionBD() throws ClassNotFoundException, SQLException{
        if(con == null){
            return new DBConnection();
        }
        return con;
    }
    
    public ResultSet get(String requetteSQL){
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(requetteSQL);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public void update(String requetteSQL){
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(requetteSQL);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void destroy() throws SQLException {
        connection.close();
    }
    
}

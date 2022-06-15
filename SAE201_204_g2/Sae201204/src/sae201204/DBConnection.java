/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae201204;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author p2103678
 */
public class DBConnection {
    
    private Connection connection = null;
    public static DBConnection con = null;

    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  

            connection = DriverManager.getConnection("jdbc:mysql://iutbg-lamp.univ-lyon1.fr:3306/EDT", "p2103678", PassWord.password);
            this.con = this;
        } catch (Exception ex) {
            System.out.println("erreur");
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

    public Connection getConnection() {
        return connection;
    }

    public void destroy() throws SQLException {
        connection.close();
    }

    public void setConnection(String DataBase) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://iutbg-lamp.univ-lyon1.fr:3306/"+DataBase, "p2103678", PassWord.password);
    }
    
}

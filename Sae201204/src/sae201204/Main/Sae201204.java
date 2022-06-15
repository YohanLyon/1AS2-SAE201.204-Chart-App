/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae201204.Main;

import java.sql.SQLException;
import sae201204.View.Fenetre;
import sae201204.View.ViewConnection;

/**
 *
 * @author p2103678
 */
public class Sae201204 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        ViewConnection win = new ViewConnection();
        win.setVisible(true);
    }
    
}

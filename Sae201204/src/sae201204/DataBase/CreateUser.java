/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae201204.DataBase;

/**
 *
 * @author p2103678
 */
public class CreateUser {
    public CreateUser(String pseudo, String mdp, String role) {
        try {

            DBConnection.getConnectionBD().update("CREATE USER \'"+pseudo+"\'@’localhost’ IDENTIFIED BY \'"+mdp+"\' ;");
            if(role.equals("Admin"))
                DBConnection.getConnectionBD().update("GRANT ALL PRIVILEGES ON *.* TO \'"+pseudo+"\'@’localhost’ ;");
            else if(role.equals("Moderateur"))
                DBConnection.getConnectionBD().update("GRANT ALL PRIVILEGES ON Weather.* TO \'"+pseudo+"\'@’localhost’ ;");
            else if(role.equals("Visiteur"))
                DBConnection.getConnectionBD().update("GRANT ALL PRIVILEGES ON Weather.Mesure TO \'"+pseudo+"\'@’localhost’ ;");
        } catch (Exception ex) {
            return;
        }
    }
}

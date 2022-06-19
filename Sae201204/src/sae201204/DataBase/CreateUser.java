package sae201204.DataBase;

/**
 * Cette classe permet la création d'utilisateur dans la base de donnée du RaspBerry
 * Elle agit selon les objectifs donnés pour la Base de donnée et donne les permissions 
 * utilisateur demandé.
 */
public class CreateUser {
    
    /**
     * Ce constructeur permet l'initialisation d'utilisateur dans la base de données du
     * Raspberry permettant ainsi l'acces direct à la base.
     * @param pseudo Pseudo de l'utilisateur
     * @param mdp Mot de passe de l'utilisateur
     * @param role Role de l'utilisateur
     */
    
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

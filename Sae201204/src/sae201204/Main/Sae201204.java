package sae201204.Main;

import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;
import java.sql.SQLException;
import sae201204.Capteur.AnalogI2CInput;
import sae201204.Capteur.DHT22;
import static sae201204.Capteur.DHT22.started;
import sae201204.View.Fenetre;
import sae201204.View.ViewConnection;

/**
 * Classe Principale permettant l'execution du programme. Lance la fenètre de connexion.
 * 
 */
public class Sae201204 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            
            ViewConnection win = new ViewConnection();
            win.setVisible(true);
            
        } catch (Exception ex) {}
        
    }
    
}

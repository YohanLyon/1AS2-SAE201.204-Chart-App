/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author p2103678
 */
public class Sae201204 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {
        
        ViewConnection win = new ViewConnection();
        win.setVisible(true);
        
    }
    
}

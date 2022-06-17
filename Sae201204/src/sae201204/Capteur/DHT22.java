/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae201204.Capteur;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sae201204.DataBase.CreateUser;
import sae201204.DataBase.DBConnection;
import sae201204.View.ViewConnection;

public class DHT22 {
    
    ArrayList<Double> list = new ArrayList();
    private static final int MAXTIMINGS = 85;
    private final int[] dht11_dat = {0, 0, 0, 0, 0};
    
    private int pinNumber;
    
    static public boolean started = false;
    
    public DHT22(){
        
    }

    public DHT22(int pinNumber) {
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.DEFAULT_PIN_NUMBERING));
        this.pinNumber = pinNumber;
        // setup wiringPi
        if (Gpio.wiringPiSetup() == -1) {
            System.out.println(" ==>> GPIO SETUP FAILED");
            return;
        }
        GpioUtil.export(pinNumber, GpioUtil.DIRECTION_OUT);
        
    }

    // pinNumber number = Wriring PI 
    public String getTemperatureAndHumidity() {
        String temperature="no data";
        boolean done = false;
        int attempts=0;
        try {
            DBConnection.getConnectionBD().update("CREATE TABLE IF NOT EXISTS Mesure (ID  INT AUTO_INCREMENT, date_mesure datetime, valeur_temp int, valeur_humidite int, localisation varchar(25), CONSTRAINT PK_Mesure PRIMARY KEY (ID))");
        } catch (Exception ex) {
            
        } 
        while (!done && attempts<100) {
            int laststate = Gpio.HIGH;
            int j = 0;
            attempts++;
            dht11_dat[0] = dht11_dat[1] = dht11_dat[2] = dht11_dat[3] = dht11_dat[4] = 0;

            Gpio.pinMode(pinNumber, Gpio.OUTPUT);
            Gpio.digitalWrite(pinNumber, Gpio.LOW);
            Gpio.delay(18);

            Gpio.digitalWrite(pinNumber, Gpio.HIGH);
            Gpio.pinMode(pinNumber, Gpio.INPUT);

            for (int i = 0; i < MAXTIMINGS; i++) {
                int counter = 0;
                while (Gpio.digitalRead(pinNumber) == laststate) {
                    counter++;
                    Gpio.delayMicroseconds(1);
                    if (counter == 255) {
                        break;
                    }
                }

                laststate = Gpio.digitalRead(pinNumber);

                if (counter == 255) {
                    break;
                }

                /* ignore first 3 transitions */
                if (i >= 4 && i % 2 == 0) {
                    /* shove each bit into the storage bytes */
                    dht11_dat[j / 8] <<= 1;
                    if (counter > 16) {
                        dht11_dat[j / 8] |= 1;
                    }
                    j++;
                }
            }
            // check we read 40 bits (8bit x 5 ) + verify checksum in the last
            // byte
            if (j >= 40 && checkParity()) {
                
                // while (j < 40 && ! checkParity()) {
                float h = (float) ((dht11_dat[0] << 8) + dht11_dat[1]) / 10;
                if (h > 100) {
                    h = dht11_dat[0]; // for DHT22
                }
                float c = (float) (((dht11_dat[2] & 0x7F) << 8) + dht11_dat[3]) / 10;
                if (c > 125) {
                    c = dht11_dat[2]; // for DHT22
                }
                if ((dht11_dat[2] & 0x80) != 0) {
                    c = -c;
                }
                //final float f = c * 1.8f + 32;
                
                temperature = "Humidity = " + h + " % - Temperature = " + c + " °C";
                
                list.add((double)h);
                list.add((double)c);
                done = true;
                
                try {
                   
                    DBConnection.getConnectionBD().update("INSERT INTO Mesure VALUES (0,NOW(),\'"+(int) c+"\',\'"+(int) h+"\', \"Salle ln107\")");

                } catch (Exception ex) {
                       
                }
            }
        }
        if (!done ){
            temperature = "Data not avalaible...";

        }
        System.out.println(temperature);
        return temperature;
    }
    
    // pinNumber number = Wriring PI 
    public ArrayList<Double> consolePrinttTemperature() {
        boolean done = false;
        int attempts=0;
        while (!done && attempts<100) {
            int laststate = Gpio.HIGH;
            int j = 0;
            attempts++;
            dht11_dat[0] = dht11_dat[1] = dht11_dat[2] = dht11_dat[3] = dht11_dat[4] = 0;

            Gpio.pinMode(pinNumber, Gpio.OUTPUT);
            Gpio.digitalWrite(pinNumber, Gpio.LOW);
            Gpio.delay(18);

            Gpio.digitalWrite(pinNumber, Gpio.HIGH);
            Gpio.pinMode(pinNumber, Gpio.INPUT);

            for (int i = 0; i < MAXTIMINGS; i++) {
                int counter = 0;
                while (Gpio.digitalRead(pinNumber) == laststate) {
                    counter++;
                    Gpio.delayMicroseconds(1);
                    if (counter == 255) {
                        break;
                    }
                }

                laststate = Gpio.digitalRead(pinNumber);

                if (counter == 255) {
                    break;
                }

                /* ignore first 3 transitions */
                if (i >= 4 && i % 2 == 0) {
                    /* shove each bit into the storage bytes */
                    dht11_dat[j / 8] <<= 1;
                    if (counter > 16) {
                        dht11_dat[j / 8] |= 1;
                    }
                    j++;
                }
            }
            // check we read 40 bits (8bit x 5 ) + verify checksum in the last
            // byte
            if (j >= 40 && checkParity()) {
                
                // while (j < 40 && ! checkParity()) {
                float h = (float) ((dht11_dat[0] << 8) + dht11_dat[1]) / 10;
                if (h > 100) {
                    h = dht11_dat[0]; // for DHT22
                }
                float c = (float) (((dht11_dat[2] & 0x7F) << 8) + dht11_dat[3]) / 10;
                if (c > 125) {
                    c = dht11_dat[2]; // for DHT22
                }
                if ((dht11_dat[2] & 0x80) != 0) {
                    c = -c;
                }
                //final float f = c * 1.8f + 32;
                System.out.println("Humidity = " + h + " % - Temperature = " + c + " °C");
                list.add((double)h);
                list.add((double)c);
                done = true;
            }
        }
        if (!done ){
            System.out.println("Data not available...");
        }
        return list;
    }

    private boolean checkParity() {
        return dht11_dat[4] == (dht11_dat[0] + dht11_dat[1] + dht11_dat[2] + dht11_dat[3] & 0xFF);
    }

    public static void main(final String ars[]) throws Exception {

        int BCM_26 = 25; // wiring Pi number
        
        

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae201204.Capteur;

/**
 *
 * @author Yohangoku
 */
public class CapteurCollect extends Thread {
    public void run() {
        
        try {
            AnalogI2CInput an = new AnalogI2CInput(0);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        DHT22 dht22 = new DHT22(5);
        
        while (DHT22.started) {
            try {
                Thread.sleep(1000);
                dht22.getTemperatureAndHumidity();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}

package sae201204.Capteur;

/**
 *  Cette classe permet l'excecution du capteur de manière asynchrone
 * 
 */
public class CapteurCollect extends Thread {
    /**
     * La méthode run est excecuté au lancement du thread. Le code présent dans cette fonction sera excecuté 
     * de manière asynchrone au programme principal
     * La méthode va instancier un port de capteur pour le lire de manière Analogue.
     * Tant que la recolte de données ne passe pas à false, le capteur continura 
     * de recolter les données de température et d'humidité
     */
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

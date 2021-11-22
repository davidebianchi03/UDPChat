package udpchat;

import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davide
 */
public class Listen extends Thread {

    /*
        Classe che continua ad ascoltare interrottamente in attese che arrivino nuovi pacchetti da elaborare
     */
    private Server server = null;
    private boolean run;

    public Listen() throws SocketException {
        server = Server.getInstance();
        run = true;
    }

    @Override
    public void run() {
        /*
            Ascolto in attesa di nuovi messaggi, quindi li elaboro
         */
        while (run) {
            //Ricevo i messaggi
            Message m = null;
            try {
                m = server.receiveMessage();
            } catch (IOException ex) {
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Elaboro i messaggi
            Elabora.elabora(m);            
        }

    }

}

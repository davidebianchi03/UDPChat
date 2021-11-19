package udpchat;

import java.net.InetAddress;

/**
 *
 * @author Davide
 */
public class Connessione {
    
    /*
        Classe che si occupa di instaurare una connessione e di mantenere i dati dell'altro peer
    */
    
    private InetAddress indirizzo_ip;
    private int porta;
    
    public Connessione(){
        indirizzo_ip = null;
        porta = 0;
    }
    
    public Connessione(InetAddress indirizzo_ip, int porta){
        this.indirizzo_ip = indirizzo_ip;
        this.porta = porta;
    }
    
}

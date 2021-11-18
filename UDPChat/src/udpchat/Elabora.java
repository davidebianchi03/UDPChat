package udpchat;

import java.net.DatagramPacket;

/**
 *
 * @author Davide
 */
public class Elabora {
    
    public static String elabora(Message messaggio){
        
        String comando = messaggio.getComando();
        if(comando.equals("c")){//Comando apertura connessione
            System.out.println("Hai ricevuto una richiesta di connessione da parte di " + messaggio.getPacchetto().getAddress().toString());
        }
        
        return null;
    }
    
}

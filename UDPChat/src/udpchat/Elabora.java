package udpchat;

/**
 *
 * @author Davide
 */
public class Elabora {
    
    /*
        Classe che si occupa dell'elaborazione della richiesta che viene inviata dal client al server
    */
    
    public static String elabora(Message messaggio){
        
        String comando = messaggio.getComando();
        if(comando.equals("c")){//Comando apertura connessione
            System.out.println("Hai ricevuto una richiesta di connessione da parte di " + messaggio.getPacchetto().getAddress().toString());
        }
        
        return null;
    }
    
}

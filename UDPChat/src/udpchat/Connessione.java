package udpchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Davide
 */
public class Connessione {

    /*
        Classe che si occupa di instaurare una connessione e di mantenere i dati dell'altro peer
     */
    private InetAddress indirizzo_ip_destinatario;
    private int porta_destinatario;
    private String nickname_destinatario;
    private boolean connessione_aperta;

    public Connessione(int porta) {
        indirizzo_ip_destinatario = null;
        porta_destinatario = porta;
        nickname_destinatario = "";
        connessione_aperta = false;
    }

    public Connessione(InetAddress indirizzo_ip, int porta) {
        this.indirizzo_ip_destinatario = indirizzo_ip;
        this.porta_destinatario = porta;
        nickname_destinatario = "";
        connessione_aperta = false;
    }

    /*
        Metodo per connettersi con l'altro peer inviando il messaggio di richiesta apertura connessione
     */
    public boolean richiediConnessione(String my_nickname) throws SocketException, IOException {
        if (!connessione_aperta) {
            Message request_message = new Message("c", my_nickname);
            Server s = Server.getInstance();
            s.sendMessage(request_message);

            Message response_message = s.receiveMessage();
            if (response_message.getComando().equals("y")) {
                //connessione accettata
                int pos = response_message.getComando().length();
                nickname_destinatario = response_message.getComando().substring(0, pos);
                connessione_aperta = true;
                return true;
            } else {
                //rifiuto la connessione
                nickname_destinatario = "";
                indirizzo_ip_destinatario = null;
                connessione_aperta = false;
                return false;
            }
        }
        return false;
    }

    /*
        Metodo che serve per dire se è stata stabilita una connessione
     */
    public boolean getConnessioneAperta() {
        return connessione_aperta;
    }

    /*
        Metodo per chiudere una connessione se è già stata stabilita
     */
    public void chiudiConnessione() {
        nickname_destinatario = "";
        indirizzo_ip_destinatario = null;
        connessione_aperta = false;
    }
}

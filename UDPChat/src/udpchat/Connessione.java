package udpchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public Message last_message = null;

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
    public boolean richiediConnessione(String my_nickname, InetAddress indirizzo_ip) throws SocketException, IOException {
        if (!connessione_aperta) {
            Message request_message = new Message("c", my_nickname, indirizzo_ip);
            request_message.setIndirizzo_ip(indirizzo_ip);
            Server s = Server.getInstance();
            DatiCondivisi d = DatiCondivisi.getInstance();
            last_message= null;
            s.sendMessage(request_message);
            while(true){
                try {
                    //System.out.println(last_message);
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Connessione.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(last_message != null)
                    break;
            }
            Message response_message = last_message;
            System.out.println(response_message.getComando());
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

    public InetAddress getIndirizzo_ip_destinatario() {
        return indirizzo_ip_destinatario;
    }

    public void setIndirizzo_ip_destinatario(InetAddress indirizzo_ip_destinatario) {
        this.indirizzo_ip_destinatario = indirizzo_ip_destinatario;
    }

    public String getNickname_destinatario() {
        return nickname_destinatario;
    }

    public void setNickname_destinatario(String nickname_destinatario) {
        this.nickname_destinatario = nickname_destinatario;
    }
    
    
}

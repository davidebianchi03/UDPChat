package udpchat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Davide
 */
public class Elabora {

    /*
        Classe che si occupa dell'elaborazione della richiesta che viene inviata dal client al server
     */
    public static void elabora(Message messaggio) throws SocketException {
        DatiCondivisi d = DatiCondivisi.getInstance();
        Server s = Server.getInstance();

        if (d.getConnessione() == null) {
            d.setConnessione(new Connessione(2003));
        }

        String comando = messaggio.getComando();
        
        if (comando.equals("c") && !d.getConnessione().getConnessioneAperta()) {
            AcceptConnection.acceptConnection(messaggio);
        } else if (comando.equals("c") && d.getConnessione().getConnessioneAperta()) {
            //rifiuto la connessione di default perchè ne ho già aperta un'altra
            Message risposta = new Message("n", "", messaggio.getIndirizzo_ip());
            try {
                s.sendMessage(messaggio);
            } catch (IOException ex) {
                Logger.getLogger(Elabora.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Tentativo di connessione mentre è già stata stabilita un'altra connessione, il tentativo è stato annullato");
        } else {
            InetAddress source_address = messaggio.getPacchetto().getAddress();
            if (source_address.equals(d.getRemote_ip())) {
                switch (comando) {
                    case "m":
                        ShowReceivedMessage.showMessage(messaggio);
                        break;
                    case "e":
                        JOptionPane.showMessageDialog(d.getFrame(), "La connessione è stata interrotta dall'altro host");
                        d.getConnessione().chiudiConnessione();
                        d.getDrawMessagePage().connessioneChiusa();
                        break;
                }
            } else {
                //rispondo di default con il messaggio di chiusura della connessione
                if (!messaggio.getComando().equals("e")) {
                    Message risposta = new Message("e", "", messaggio.getIndirizzo_ip());
                    try {
                        s.sendMessage(risposta);
                    } catch (IOException ex) {
                        Logger.getLogger(Elabora.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println("Tentativo di invio messaggio da un host non connesso");
                }

            }
        }

    }

}

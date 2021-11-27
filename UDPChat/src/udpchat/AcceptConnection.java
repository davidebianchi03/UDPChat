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
public class AcceptConnection {

    public static void acceptConnection(Message messaggio) {
        DatiCondivisi d = DatiCondivisi.getInstance();
        //System.out.println("Hai ricevuto una richiesta di connessione da parte di " + messaggio.getPacchetto().getAddress().toString());
        if (!d.getConnessione().getConnessioneAperta()) {

            String ip_string = messaggio.getPacchetto().getAddress().toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialog_result = JOptionPane.showConfirmDialog(d.getFrame(), "Accettare connessione da parte di " + ip_string, "Richiesta connessione", dialogButton);

            Server s = null;
            try {
                s = Server.getInstance();
            } catch (SocketException ex) {
                Logger.getLogger(AcceptConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            Message messaggio_risposta = null;
            if (dialog_result == 0) {
                //Accetto la connessione
                //rispondo con y;myNickname;
                messaggio_risposta = new Message("y", d.getMyNickname(), messaggio.getIndirizzo_ip());
                d.getDrawMessagePage().changeNomeDestinatario(messaggio.getCorpo_messaggio());
                //creo la connessione
                Connessione connessione = new Connessione(messaggio.getIndirizzo_ip(), 2003);
                connessione.setNickname_destinatario(messaggio.getCorpo_messaggio());
                connessione.apriConnessione();
                d.setRemote_ip(messaggio.getIndirizzo_ip());
                d.setConnessione(connessione);
            } else {
                //Rifiuto la connessione
                //rispondo con n
                messaggio_risposta = new Message("n", "", messaggio.getIndirizzo_ip());
            }
            try {
                s.sendMessage(messaggio_risposta);
                //System.out.println(messaggio_risposta.getIndirizzo_ip());
            } catch (IOException ex) {
                Logger.getLogger(Elabora.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            try {
                Server s = Server.getInstance();
                s.sendMessage(new Message("n", "", messaggio.getIndirizzo_ip()));
            } catch (IOException ex) {
                Logger.getLogger(Elabora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}

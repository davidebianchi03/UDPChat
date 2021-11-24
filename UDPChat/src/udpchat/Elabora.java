package udpchat;

import java.io.IOException;
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
        if (messaggio.getPacchetto().getAddress().equals(d.getRemote_ip()) || d.getRemote_ip() == null) {
            //System.out.println(messaggio.toCSV());
            String comando = messaggio.getComando();
            if (comando.equals("c")) {//Comando apertura connessione
                //System.out.println("Hai ricevuto una richiesta di connessione da parte di " + messaggio.getPacchetto().getAddress().toString());
                String ip_string = messaggio.getPacchetto().getAddress().toString();
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialog_result = JOptionPane.showConfirmDialog(d.getFrame(), "Accettare connessione da parte di " + ip_string, "Richiesta connessione", dialogButton);
                
                Server s = Server.getInstance();
                Message messaggio_risposta  = null;
                if (dialog_result == 0) {
                    //Accetto la connessione
                    //rispondo con y;myNickname;
                    messaggio_risposta = new Message("y",d.getMyNickname(), messaggio.getIndirizzo_ip());                    
                } else {
                    //Rifiuto la connessione
                    //rispondo con n
                    messaggio_risposta = new Message("n","", messaggio.getIndirizzo_ip());
                }
                try {
                    s.sendMessage(messaggio_risposta);
                    //System.out.println(messaggio_risposta.getIndirizzo_ip());
                } catch (IOException ex) {
                    Logger.getLogger(Elabora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            System.out.println("Messaggio ricevuto da un'altro host mentre è già aperta una comunicazione");
        }

    }

}

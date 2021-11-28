package udpchat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Davide
 */
/*
    Classe per inviare una richiesta di connessione
 */
public class SendConnectionRequest extends Thread {

    private Connessione connessione;
    private InetAddress indirizzo_ip;
    private DrawMessagePage draw;

    public SendConnectionRequest(InetAddress indirizzo_ip) {
        this.connessione = DatiCondivisi.getInstance().getConnessione();
        this.indirizzo_ip = indirizzo_ip;
        this.draw = DatiCondivisi.getInstance().getDrawMessagePage();
    }

    @Override
    public void run() {
        try {
            //Invia richiesta
            DatiCondivisi d = DatiCondivisi.getInstance();
            d.setRemote_ip(indirizzo_ip);
            Connessione c = d.getConnessione();
            if (!c.getConnessioneAperta()) {
                c = new Connessione(indirizzo_ip, 2003);
                d.setConnessione(c);

                boolean risultato_connessione = c.richiediConnessione(d.getMyNickname(), indirizzo_ip);
                //d.getDrawMessagePage().hideStopConnectionPane();
                if (!risultato_connessione) {
                    JOptionPane.showMessageDialog(d.getFrame(), "L'altro host ha rifiutato la connessione o c'è stato un problema di comunicazione");
                } else {
                    draw.changeNomeDestinatario(c.getNickname_destinatario());
                }
            } else {
                JOptionPane.showMessageDialog(d.getFrame(), "Non è possibile aprire una nuova connessione perchè ne è già stata stabilita una");
            }

        } catch (UnknownHostException ex) {
            Logger.getLogger(DrawMessagePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DrawMessagePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

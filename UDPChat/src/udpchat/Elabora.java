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
        if(d.getConnessione() == null){
            d.setConnessione(new Connessione(2003));
        }
        
        String comando = messaggio.getComando();
        
        if(comando.equals("c")&& !d.getConnessione().getConnessioneAperta()){
            AcceptConnection.acceptConnection(messaggio);
        }
        else if("c".equals(comando) && d.getConnessione().getConnessioneAperta()){
            System.out.println("Tentativo di connessione mentre è già stata stabilita un'altra connessione");
        }
        else{
            InetAddress source_address = messaggio.getPacchetto().getAddress();
            if(source_address.equals(d.getRemote_ip())){
                switch(comando){
                    case "m":
                        ShowReceivedMessage.showMessage(messaggio);
                        break;
                }
            }
            else{
                System.out.println("Tentativo di invio messaggio da un host non connesso");
            }
        }

    }

}

package udpchat;

import javax.swing.JOptionPane;

/**
 *
 * @author Davide
 */
public class Elabora {

    /*
        Classe che si occupa dell'elaborazione della richiesta che viene inviata dal client al server
     */
    public static String elabora(Message messaggio) {

        String comando = messaggio.getComando();
        if (comando.equals("c")) {//Comando apertura connessione
            System.out.println("Hai ricevuto una richiesta di connessione da parte di " + messaggio.getPacchetto().getAddress().toString());
            String ip_string = messaggio.getPacchetto().getAddress().toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialog_result = JOptionPane.showConfirmDialog(null, "Accettare connessione da parte di " + ip_string, "Richiesta connessione", dialogButton);
            if (dialog_result == 0) {
                //Accetto la connessione
            } else {
                //Rifiuto la connessione
            }
        }

        return null;
    }

}

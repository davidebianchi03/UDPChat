package udpchat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Davide
 */
public class ShowReceivedMessage {

    public static void showMessage(Message messaggio) {
        String corpo_messaggio = messaggio.getCorpo_messaggio();
        DatiCondivisi d = DatiCondivisi.getInstance();
        String my_nickname = d.getMyNickname();
        Connessione connessione = d.getConnessione();
        String nickname = connessione.getNickname_destinatario();//nickname dell'altro peer

        System.out.println("Messaggio da: " + nickname + ", corpo messaggio: " + corpo_messaggio);

        DrawMessagePage draw = d.getDrawMessagePage();
        draw.addMessage(corpo_messaggio, true);

    }

}

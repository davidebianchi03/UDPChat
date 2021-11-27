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

        //mostro il messaggio sulla schermata
        /*int width = 100;
        int height = 100;
        BufferedImage buffered_image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        Graphics img_graphics = buffered_image.createGraphics();
        //colore dello sfondo
        Color background_color = Color.GRAY;
        img_graphics.setColor(background_color);
        img_graphics.fillRect(0, 0, width, height);*/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DrawMessagePage draw = d.getDrawMessagePage();
        draw.addMessage(dtf.format(now) + " ,From: " + nickname + " ,Corpo: " +corpo_messaggio);

    }

}

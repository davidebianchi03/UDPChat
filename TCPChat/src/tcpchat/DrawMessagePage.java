package tcpchat;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Davide
 */
public class DrawMessagePage {

    private UI ui;
    private JLabel lbl_nickname;
    private JTextArea txt_message;
    private JScrollPane txt_message_scroll;
    private JButton btn_send_message;
    private JButton btn_stop;

    public DrawMessagePage(UI ui) {
        this.ui = ui;
    }

    public void drawPage() {
        SharedData shared_data = SharedData.getInstance();
        //rimuovo tutto dallo schermo
        ui.getContentPane().removeAll();
        ui.revalidate();
        ui.repaint();
        //disegno la barra superiore
        lbl_nickname = new JLabel("<html><body style = 'color:white'>Hello world</body></html>", JLabel.CENTER);
        lbl_nickname.setBounds(0, 0, ui.getWidth(), 50);
        Color lbl_bg_color = new Color(28, 31, 51);
        lbl_nickname.setBackground(lbl_bg_color);
        lbl_nickname.setOpaque(true);
        ui.add(lbl_nickname);
        //disegno la barra inferiore
        //disegno l'input del testo del messaggio  
        txt_message = new JTextArea("Scrivi un messaggio");
        txt_message.setBounds(5, ui.getHeight() - 100, ui.getWidth() - 350, 50);

        txt_message_scroll = new JScrollPane(txt_message);
        txt_message_scroll.setBackground(Color.white);
        txt_message_scroll.setOpaque(true);
        Insets txt_message_padding = new Insets(10, 10, 0, 10);
        SimpleBorder txt_message_border = new SimpleBorder(Color.BLACK, 1, 25, txt_message_padding);//Color border_color, int border_thickness, int border_radius, Insets padding
        txt_message_scroll.setBorder(txt_message_border);
        txt_message_scroll.setBounds(5, ui.getHeight() - 100, ui.getWidth() - 350, 50);
        ui.add(txt_message_scroll);
        //disegno il pulsante invia
        URL url = getClass().getResource("/images/send.png");
        ImageIcon img = new ImageIcon(url);
        Image resized_img = img.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        btn_send_message = new JButton(new ImageIcon(resized_img));
        btn_send_message.setBounds(ui.getWidth() - 340, ui.getHeight() - 100, 50, 50);
        Color btn_send_color = new Color(28, 31, 51);
        btn_send_message.setBackground(btn_send_color);
        Insets btn_send_message_padding = new Insets(12, 8, 7, 12);
        SimpleBorder btn_send_border = new SimpleBorder(btn_send_color, 0, 50, btn_send_message_padding);//Color border_color, int border_thickness, int border_radius, Insets padding
        btn_send_message.setBorder(btn_send_border);
        ui.add(btn_send_message);
        //disegno il pulsante per interrompere la comunicazione
        btn_stop = new JButton("<html><body style = 'color:white' >Disconnetti</body></html>");
        btn_stop.setBounds(ui.getWidth() - 285, ui.getHeight() - 100, (ui.getWidth() - 30) - (ui.getWidth() - 285), 50);
        Color btn_stop_color = new Color(171, 3, 3);
        btn_stop.setBackground(btn_stop_color);
        Insets btn_stop_padding = new Insets(12, 8, 7, 12);
        SimpleBorder btn_stop_border = new SimpleBorder(btn_stop_color, 0, 25, btn_stop_padding);//Color border_color, int border_thickness, int border_radius, Insets padding
        btn_stop.setBorder(btn_stop_border);
        ui.add(btn_stop);
    }

    void screenResized() {
        if (lbl_nickname != null) {
            lbl_nickname.setBounds(0, 0, ui.getWidth(), 50);
        }
        if (txt_message != null) {
            txt_message.setBounds(5, ui.getHeight() - 100, ui.getWidth() - 350, 50);
        }
        if (txt_message_scroll != null) {
            txt_message_scroll.setBounds(5, ui.getHeight() - 100, ui.getWidth() - 350, 50);
        }
        if (btn_send_message != null) {
            btn_send_message.setBounds(ui.getWidth() - 340, ui.getHeight() - 100, 50, 50);
        }
        if (btn_stop != null) {
            btn_stop.setBounds(ui.getWidth() - 285, ui.getHeight() - 100, (ui.getWidth() - 30) - (ui.getWidth() - 285), 50);
        }
        ui.revalidate();
        ui.repaint();
    }

}

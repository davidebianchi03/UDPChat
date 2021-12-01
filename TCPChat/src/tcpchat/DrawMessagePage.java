package tcpchat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    private JPanel chat_active_panel;
    private JTextField txt_indirizzo_ip;
    private JButton btn_connect;
    private JLabel lbl_set_ip;

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
        lbl_nickname.setFont(new Font("Verdana", Font.PLAIN, 13));
        ui.add(lbl_nickname);
        //disegno la barra inferiore
        //disegno l'input del testo del messaggio  
        txt_message = new JTextArea("Scrivi un messaggio");
        txt_message.setFont(new Font("Verdana", Font.PLAIN, 13));
        txt_message.setBounds(5, ui.getHeight() - 100, ui.getWidth() - 350, 50);

        txt_message_scroll = new JScrollPane(txt_message);
        txt_message_scroll.setBackground(Color.white);
        txt_message_scroll.setOpaque(true);
        Insets txt_message_padding = new Insets(10, 10, 0, 10);
        SimpleBorder txt_message_border = new SimpleBorder(Color.BLACK, 1, 25, txt_message_padding);//Color border_color, int border_thickness, int border_radius, Insets padding
        txt_message_scroll.setBorder(txt_message_border);
        txt_message_scroll.setBounds(5, ui.getHeight() - 100, ui.getWidth() - 350, 50);
        txt_message_scroll.setFont(new Font("Verdana", Font.PLAIN, 13));
        ui.add(txt_message_scroll);
        //disegno il pulsante invia
        URL url = getClass().getResource("/images/send.png");
        ImageIcon img = new ImageIcon(url);
        Image resized_img = img.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        btn_send_message = new JButton(new ImageIcon(resized_img));
        btn_send_message.setBounds(ui.getWidth() - 340, ui.getHeight() - 100, 50, 50);
        btn_send_message.setFont(new Font("Verdana", Font.PLAIN, 13));
        Color btn_send_color = new Color(28, 31, 51);
        btn_send_message.setBackground(btn_send_color);
        Insets btn_send_message_padding = new Insets(12, 8, 7, 12);
        SimpleBorder btn_send_border = new SimpleBorder(btn_send_color, 0, 50, btn_send_message_padding);//Color border_color, int border_thickness, int border_radius, Insets padding
        btn_send_message.setBorder(btn_send_border);
        ui.add(btn_send_message);
        //disegno il pulsante per interrompere la comunicazione
        btn_stop = new JButton("<html><body style = 'color:white' >Disconnetti</body></html>");
        btn_stop.setFont(new Font("Verdana", Font.PLAIN, 13));
        btn_stop.setBounds(ui.getWidth() - 285, ui.getHeight() - 100, (ui.getWidth() - 30) - (ui.getWidth() - 285), 50);
        Color btn_stop_color = new Color(171, 3, 3);
        btn_stop.setBackground(btn_stop_color);
        Insets btn_stop_padding = new Insets(12, 8, 7, 12);
        SimpleBorder btn_stop_border = new SimpleBorder(btn_stop_color, 0, 25, btn_stop_padding);//Color border_color, int border_thickness, int border_radius, Insets padding
        btn_stop.setBorder(btn_stop_border);
        ui.add(btn_stop);
        //pannello che contiene i componenti necessari per l'instaurazione di una connessione
        chat_active_panel = new JPanel(null);
        chat_active_panel.setBounds(0, 0, ui.getWidth(), ui.getHeight());
        Color chat_active_panel_color = new Color(255, 255, 255);
        chat_active_panel.setBackground(chat_active_panel_color);
        ui.add(chat_active_panel);

        //disegno l'interfaccia per la connessione
        //label descrizione inserimento indirizzo ip
        lbl_set_ip = new JLabel("Inserisci l'indirizzo ip dell'altro peer");
        lbl_set_ip.setBounds(chat_active_panel.getWidth() / 2 - 270, chat_active_panel.getHeight() / 2 - 20, 275, 40);
        lbl_set_ip.setFont(new Font("Verdana", Font.PLAIN, 13));
        chat_active_panel.add(lbl_set_ip);

        //inserimento testo indirizzo ip
        txt_indirizzo_ip = new JTextField("192.168.1.23");
        txt_indirizzo_ip.setBounds(chat_active_panel.getWidth() / 2 - 25, chat_active_panel.getHeight() / 2 - 20, 150, 40);
        Insets txt_indirizzo_ip_padding = new Insets(12, 8, 7, 12);
        SimpleBorder txt_indirizzo_ip_border = new SimpleBorder(Color.BLACK, 0, 25, btn_stop_padding);//Color border_color, int border_thickness, int border_radius, Insets padding
        txt_indirizzo_ip.setBorder(txt_indirizzo_ip_border);
        txt_indirizzo_ip.setFont(new Font("Verdana", Font.PLAIN, 13));
        chat_active_panel.add(txt_indirizzo_ip);

        //pulsante per richiedere la connessione
        btn_connect = new JButton("<html><body style = 'color:white'>Connetti</body></html>");
        btn_connect.setBounds(chat_active_panel.getWidth() / 2 + 140, chat_active_panel.getHeight() / 2 - 20, 100, 40);
        Color btn_connect_color = new Color(28, 31, 51);
        btn_connect.setBackground(btn_connect_color);
        Insets btn_connect_padding = new Insets(8, 8, 8, 8);
        SimpleBorder btn_connect_border = new SimpleBorder(btn_connect_color, 0, 25, btn_connect_padding);//Color border_color, int border_thickness, int border_radius, Insets padding
        btn_connect.setBorder(btn_connect_border);
        btn_connect.setFont(new Font("Verdana", Font.PLAIN, 13));
        btn_connect.addActionListener(new ButtonConnectClicked(this));
        chat_active_panel.add(btn_connect);
        
        disableMessagesPage();
        //activateMessagePage();
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
        if (lbl_set_ip != null) {
            lbl_set_ip.setBounds(chat_active_panel.getWidth() / 2 - 280, chat_active_panel.getHeight() / 2 - 20, 275, 40);
        }
        if (txt_indirizzo_ip != null) {
            txt_indirizzo_ip.setBounds(chat_active_panel.getWidth() / 2 - 25, chat_active_panel.getHeight() / 2 - 20, 150, 40);
        }
        if(btn_connect != null){
            btn_connect.setBounds(chat_active_panel.getWidth() / 2 + 140, chat_active_panel.getHeight() / 2 - 20, 100, 40);
        }
        ui.revalidate();
        ui.repaint();
    }

    void disableMessagesPage() {
        if (chat_active_panel != null) {
            ui.setComponentZOrder(chat_active_panel, 0);
            chat_active_panel.setVisible(true);
        }

        if (txt_message != null) {
            txt_message.setVisible(false);
        }

        if (txt_message_scroll != null) {
            txt_message_scroll.setVisible(false);
        }
        if (btn_send_message != null) {
            btn_send_message.setVisible(false);
        }
        if (btn_stop != null) {
            btn_stop.setVisible(false);
        }
        if (lbl_nickname != null) {
            lbl_nickname.setVisible(false);
        }
    }

    void activateMessagePage() {
        if (chat_active_panel != null) {
            ui.setComponentZOrder(chat_active_panel, 0);
            chat_active_panel.setVisible(false);
        }

        if (txt_message != null) {
            txt_message.setVisible(true);
        }

        if (txt_message_scroll != null) {
            txt_message_scroll.setVisible(true);
        }
        if (btn_send_message != null) {
            btn_send_message.setVisible(true);
        }
        if (btn_stop != null) {
            btn_stop.setVisible(true);
        }
        if (lbl_nickname != null) {
            lbl_nickname.setVisible(true);
        }
    }

    public JLabel getLbl_nickname() {
        return lbl_nickname;
    }

    public JTextArea getTxt_message() {
        return txt_message;
    }

    public JButton getBtn_send_message() {
        return btn_send_message;
    }

    public JButton getBtn_stop() {
        return btn_stop;
    }

    public JTextField getTxt_indirizzo_ip() {
        return txt_indirizzo_ip;
    }

    public JButton getBtn_connect() {
        return btn_connect;
    }
    
    

}

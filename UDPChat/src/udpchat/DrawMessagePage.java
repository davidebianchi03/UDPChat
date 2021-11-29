package udpchat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Davide
 */
public class DrawMessagePage {

    private JTextField txt_indirizzo_ip;
    private JButton btn_conferma_ip;
    private Frame frame;
    private JLabel lbl_nickname_peer;
    private JScrollPane scroll_messages;
    private JPanel panel_lista_messaggi;
    private JTextField txt_messaggio;
    private JButton btn_invia_messaggio;
    private JOptionPane stop_connection_pane;
    private JButton stop_connection_button;
    private SendConnectionRequest cr;
    private int last_message_y;

    public DrawMessagePage() {
        txt_indirizzo_ip = null;
        btn_conferma_ip = null;
        DatiCondivisi d = DatiCondivisi.getInstance();
        d.setDrawMessagePage(this);
        last_message_y = 100;
    }

    public void draw(Frame frame) {
        last_message_y = 0;
        this.frame = frame;
        //Rimuovo tutti i componenti sullo schermo
        frame.getContentPane().removeAll();
        frame.repaint();
        //disegno la nuova pagina
        DatiCondivisi d = DatiCondivisi.getInstance();
        //Visualizzazione del Nickname in alto a destra
        JLabel lbl_nickname = new JLabel(d.getMyNickname());
        lbl_nickname.setBounds(10, 0, 150, 30);
        frame.add(lbl_nickname);
        //Visualizzazione dei controlli per l'inserimento dell'ip dell'altro peer
        txt_indirizzo_ip = new JTextField("192.168.1.23");/////////---->DA CAMBIARE
        txt_indirizzo_ip.setBounds(frame.getWidth() - 465, 5, 100, 30);
        frame.add(txt_indirizzo_ip);
        lbl_nickname_peer = new JLabel("");
        lbl_nickname_peer.setBounds(frame.getWidth() / 2 - 150, 5, 100, 30);
        frame.add(lbl_nickname_peer);
        btn_conferma_ip = new JButton("Connetti");
        btn_conferma_ip.setBounds(frame.getWidth() - 340, 5, 100, 30);
        btn_conferma_ip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (d.getConnessione() == null) {
                        d.setConnessione(new Connessione(2003));
                    }
                    String stringa_indirizzo_ip = txt_indirizzo_ip.getText();
                    InetAddress indirizzo_ip = InetAddress.getByName(stringa_indirizzo_ip);
                    cr = new SendConnectionRequest(indirizzo_ip);
                    cr.start();

                } catch (UnknownHostException ex) {
                    Logger.getLogger(DrawMessagePage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        frame.add(btn_conferma_ip);
        try {
            //Inizio ad ascoltare in attesa di richieste
            Listen listen = new Listen();
            listen.start();
            d.setListen(listen);
        } catch (SocketException ex) {
            Logger.getLogger(DrawMessagePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        //visualizzazione dei messaggi
        panel_lista_messaggi = new JPanel(null);
        panel_lista_messaggi.setBounds(0, 0, frame.getWidth() - 125, frame.getHeight() - 200);
        scroll_messages = new JScrollPane(panel_lista_messaggi);
        scroll_messages.setBounds(20, 50, frame.getWidth() - 50, frame.getHeight() - 200);
        //panel_lista_messaggi.setPreferredSize(new Dimension(frame.getWidth() - 50, (frame.getHeight() - 200)));
        scroll_messages.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_messages.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(scroll_messages);
        //invio dei messaggi
        txt_messaggio = new JTextField("Messaggio");
        txt_messaggio.setBounds(0, frame.getHeight() - 100, frame.getWidth() - 250, 50);
        frame.add(txt_messaggio);
        btn_invia_messaggio = new JButton("Invia");
        btn_invia_messaggio.setBounds(frame.getWidth() - 225, frame.getHeight() - 100, 150, 50);
        frame.add(btn_invia_messaggio);

        btn_invia_messaggio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatiCondivisi d = DatiCondivisi.getInstance();
                if (d.getConnessione() == null) {
                    d.setConnessione(new Connessione(2003));
                }
                Connessione c = d.getConnessione();

                String testo_messaggio = txt_messaggio.getText();
                //visualizzo il messaggio sull'interfaccia grafica

                //invio il messaggio
                if (c.getConnessioneAperta()) {
                    addMessage(testo_messaggio, false);
                    //invio del messaggio
                    try {
                        Server s = Server.getInstance();
                        Message m = new Message("m", testo_messaggio, d.getRemote_ip());
                        s.sendMessage(m);
                    } catch (SocketException ex) {
                        Logger.getLogger(DrawMessagePage.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(DrawMessagePage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Prima di inviare un messaggio instaurare una connessione");
                }
            }
        }
        );

        stop_connection_button = new JButton("<html><body>Interrompi connessione/<br>tentativo connessione</body></html>");
        stop_connection_button.setBounds(frame.getWidth() - 220, 0, 200, 50);
        frame.add(stop_connection_button);
        stop_connection_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //interrompi connessione/tentativo di connessione
                Connessione connessione = d.getConnessione();
                if (connessione.isConnessione_in_corso()) {
                    //se sta si sta aprendo una connessione, l'annullo
                    cr.annullaConnessione();
                    JOptionPane.showMessageDialog(frame, "La connessione è stata annullata con successo");
                } else if (connessione.getConnessioneAperta()) {
                    int result = JOptionPane.showConfirmDialog(frame, "Sei sicuro di voler chiudere la connessione?");
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            //se la connessione è già stata aperta la chiudo
                            connessione.richiediChiusuraConnessione();
                        } catch (SocketException ex) {
                            Logger.getLogger(DrawMessagePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("Connessione chiusa");
                    }

                }
                connessioneChiusa();
            }
        });
    }

    void changeNomeDestinatario(String nome) {
        lbl_nickname_peer.setText(nome);
    }

    int msg_counter = 0;

    void addMessage(String message, boolean messaggio_ricevuto) {

        JLabel message_lbl = new JLabel(message);

        int width = message_lbl.getText().length() * 11;
        int height = message_lbl.getText().length() * 20;
        
        String time_stamp = new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(new Date());
        JLabel time_label = new JLabel(time_stamp);
        
        if(width < 50){
            width = 50;
        }

        TextBubbleBorder border;
        if (messaggio_ricevuto) {//se il messaggio è stato ricevuto lo allineo a sinistra e gli cambio il colore in grigio
            message_lbl.setBounds(10, last_message_y, width, 50);
            message_lbl.setBackground(new Color(204, 204, 204));
            border = new TextBubbleBorder(Color.BLACK, 1, 16, 16);
            time_label.setBounds(10, last_message_y - 35, 100, 50);
        } else {//se il messaggio è stato ricevuto lo allineo a sinistra e gli cambio il colore in azzurro
            message_lbl.setBounds(panel_lista_messaggi.getWidth() - 50 - width, last_message_y, width, 50);
            message_lbl.setText("<html><body><font color='white'>" + message_lbl.getText() + "</font></body></html>");
            message_lbl.setBackground(new Color(71, 160, 255));
            border = new TextBubbleBorder(Color.BLACK, 1, 16, 16, false);
            time_label.setBounds(panel_lista_messaggi.getWidth() - 50 - width, last_message_y - 35, 100, 50);
        }
        message_lbl.setOpaque(true);
        message_lbl.setBorder(border);

        int new_line_count = countMatches(message_lbl.getText(), "<br>");
        last_message_y += 60 + 10 * new_line_count + 20;
        
        panel_lista_messaggi.add(message_lbl);
        panel_lista_messaggi.add(time_label);
        

        panel_lista_messaggi.setPreferredSize(new Dimension(frame.getWidth() - 125, last_message_y + 50));
        scroll_messages.setViewportView(panel_lista_messaggi);
        scroll_messages.getVerticalScrollBar().setValue(last_message_y);
        panel_lista_messaggi.repaint();
    }

    void hideStopConnectionPane() {
        stop_connection_pane.setVisible(false);
    }

    void connessioneChiusa() {
        //cancello il nickname
        lbl_nickname_peer.setText("");
        //cancello tutti i messaggi
        //panel_lista_messaggi.setText("");
        last_message_y = 0;
    }

    int countMatches(String str, String findStr) {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = str.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }
        return count;
    }

}

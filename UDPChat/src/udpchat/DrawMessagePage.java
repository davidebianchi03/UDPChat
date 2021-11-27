package udpchat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private JLabel lbl_lista_messaggi;
    private JTextField txt_messaggio;
    private JButton btn_invia_messaggio;

    public DrawMessagePage() {
        txt_indirizzo_ip = null;
        btn_conferma_ip = null;
        DatiCondivisi d = DatiCondivisi.getInstance();
        d.setDrawMessagePage(this);
    }

    public void draw(Frame frame) {
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
        txt_indirizzo_ip.setBounds(frame.getWidth() - 225, 0, 100, 30);
        frame.add(txt_indirizzo_ip);
        lbl_nickname_peer = new JLabel("");
        lbl_nickname_peer.setBounds(frame.getWidth() / 2 - 50, 0, 100, 30);
        frame.add(lbl_nickname_peer);
        btn_conferma_ip = new JButton("Connetti");
        btn_conferma_ip.setBounds(frame.getWidth() - 120, 0, 100, 30);
        btn_conferma_ip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Invia richiesta
                    DatiCondivisi d = DatiCondivisi.getInstance();
                    String stringa_indirizzo_ip = txt_indirizzo_ip.getText();
                    InetAddress indirizzo_ip = InetAddress.getByName(stringa_indirizzo_ip);
                    d.setRemote_ip(indirizzo_ip);
                    if (d.getConnessione() == null) {
                        d.setConnessione(new Connessione(2003));
                    }
                    Connessione c = d.getConnessione();
                    if (!c.getConnessioneAperta()) {
                        c = new Connessione(indirizzo_ip, 2003);
                        d.setConnessione(c);
                        boolean risultato_connessione = c.richiediConnessione(d.getMyNickname(), indirizzo_ip);
                        if (!risultato_connessione) {
                            JOptionPane.showMessageDialog(frame, "L'altro host ha rifiutato la connessione");
                        } else {
                            changeNomeDestinatario(c.getNickname_destinatario());
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Non è possibile aprire una nuova connessione perchè ne è già stata stabilita una");
                    }

                } catch (UnknownHostException ex) {
                    Logger.getLogger(DrawMessagePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
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
        lbl_lista_messaggi = new JLabel();
        lbl_lista_messaggi.setBounds(0, 0, frame.getWidth(), frame.getHeight() - 50);
        scroll_messages = new JScrollPane(lbl_lista_messaggi);
        scroll_messages.setBounds(0, 50, frame.getWidth(), frame.getHeight() - 200);
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
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                DrawMessagePage draw = d.getDrawMessagePage();
                addMessage(dtf.format(now) + " ,From: " + "TU" + " ,Corpo: " + testo_messaggio);
                //invio il messaggio
                if (c.getConnessioneAperta()) {
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

    }

    void changeNomeDestinatario(String nome) {
        lbl_nickname_peer.setText(nome);
    }

    void addMessage(String message_body) {
        String string_to_write = lbl_lista_messaggi.getText().replace("<html><body>", "").replace("</body></html>", "") + "<br/>" + message_body;
        lbl_lista_messaggi.setText("<html><body>" + string_to_write + "</body></html>");
    }

}

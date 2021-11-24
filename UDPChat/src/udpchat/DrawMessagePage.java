package udpchat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Davide
 */
public class DrawMessagePage {

    private JTextField txt_indirizzo_ip;
    private JButton btn_conferma_ip;
    private Frame frame;
    
    public DrawMessagePage(){
        txt_indirizzo_ip = null;
        btn_conferma_ip = null;
    }
    
    public void draw(Frame frame){
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
        btn_conferma_ip = new JButton("Connetti");
        btn_conferma_ip.setBounds(frame.getWidth() - 120, 0, 100, 30);
        btn_conferma_ip.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Invia richiesta
                    DatiCondivisi d = DatiCondivisi.getInstance();
                    String stringa_indirizzo_ip = txt_indirizzo_ip.getText();
                    InetAddress indirizzo_ip = InetAddress.getByName(stringa_indirizzo_ip);
                    d.setRemote_ip(indirizzo_ip);
                    Connessione c = d.getConnessione();
                    if(c == null || !c.getConnessioneAperta()){
                        c = new Connessione( indirizzo_ip, 2003);
                        d.setConnessione(c);
                        c.richiediConnessione(d.getMyNickname(), indirizzo_ip);
                    }
                    else{
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
        
        
    }
    
}

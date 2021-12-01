package tcpchat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Davide
 */
public class ButtonConnectClicked implements ActionListener {

    /*
        Questa classe di occupa di eseguire la richiesta di connessione quando viene premuto il pulsanta connetti
     */
    private DrawMessagePage draw_page = null;
    private JLabel lbl_nickname;
    private JTextArea txt_message;
    private JButton btn_send_message;
    private JButton btn_stop;
    private JTextField txt_indirizzo_ip;
    private JButton btn_connect;

    public ButtonConnectClicked(DrawMessagePage draw_page) {
        this.draw_page = draw_page;
        //get all the components
        lbl_nickname = draw_page.getLbl_nickname();
        txt_message = draw_page.getTxt_message();
        btn_send_message = draw_page.getBtn_send_message();
        btn_stop = draw_page.getBtn_stop();
        txt_indirizzo_ip = draw_page.getTxt_indirizzo_ip();
        btn_connect = draw_page.getBtn_connect();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ip_address_string = txt_indirizzo_ip.getText();
        InetAddress ip_address = null;
        SharedData shared_data = SharedData.getInstance();
        
        boolean valid_ip_address = true;
        try {
            ip_address = InetAddress.getByName(ip_address_string);
            valid_ip_address = true;
        } catch (UnknownHostException ex) {
            //Logger.getLogger(ButtonConnectClicked.class.getName()).log(Level.SEVERE, null, ex);
            valid_ip_address = false;
        }
        
        if(valid_ip_address){//se l'indirizzo ip è valido
            
        }
        else{
            //se l'indirizzo ip non è valido visualizzo un messaggio di errore
            JOptionPane.showMessageDialog(shared_data.getUi(), "L'indirizzo ip inserito non è valido");
        }
    }

}

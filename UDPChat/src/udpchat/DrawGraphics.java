package udpchat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Davide
 */
public class DrawGraphics {

    private Frame frame;
    public static JTextField txt_nickname;

    public DrawGraphics(Frame frame) {
        this.frame = frame;
        txt_nickname = null;
    }

    public void drawNicknameInput() {
        //Rimuovo tutti i componenti sullo schermo
        frame.getContentPane().removeAll();
        frame.repaint();
        //disegno la nuova pagina
        JLabel lbl_request = new JLabel("Inserisci il tuo nickname");
        lbl_request.setBounds(frame.getWidth() / 2 - 75, frame.getHeight() / 2, 150, 30);
        txt_nickname = new JTextField("myip");
        txt_nickname.setBounds(frame.getWidth() / 2 - 75, frame.getHeight() / 2 + 25, 150, 30);
        JButton btn_confirm_nickname = new JButton("Conferma");
        btn_confirm_nickname.setBounds(frame.getWidth() / 2 - 75, frame.getHeight() / 2 + 55, 150, 30);
        btn_confirm_nickname.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //get nickname
                JTextField txt_nickname = DrawGraphics.txt_nickname;
                DatiCondivisi.my_nickname = txt_nickname.getText();
                //inizia ad ascoltare
                drawChatPage();
            }
        });

        frame.add(lbl_request);
        frame.add(txt_nickname);
        frame.add(btn_confirm_nickname);
        frame.revalidate();
        frame.repaint();
    }

    public void drawChatPage() {
        //Rimuovo tutti i componenti sullo schermo
        frame.getContentPane().removeAll();
        frame.repaint();
        //disegno la nuova pagina
        
    }

    public String getNickname() {
        return txt_nickname.getText();
    }

}

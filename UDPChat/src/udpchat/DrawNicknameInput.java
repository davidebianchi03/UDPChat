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
public class DrawNicknameInput {

    public static JTextField txt_nickname;

    public DrawNicknameInput() {
        txt_nickname = null;
    }

    public void draw(Frame frame) {
        //Rimuovo tutti i componenti sullo schermo
        frame.getContentPane().removeAll();
        frame.repaint();
        //disegno la nuova pagina
        JLabel lbl_request = new JLabel("Inserisci il tuo nickname");
        lbl_request.setBounds(frame.getWidth() / 2 - 75, frame.getHeight() / 2, 150, 30);
        txt_nickname = new JTextField("User1234");
        txt_nickname.setBounds(frame.getWidth() / 2 - 75, frame.getHeight() / 2 + 25, 150, 30);
        JButton btn_confirm_nickname = new JButton("Conferma");
        btn_confirm_nickname.setBounds(frame.getWidth() / 2 - 75, frame.getHeight() / 2 + 55, 150, 30);
        btn_confirm_nickname.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //get nickname
                JTextField txt_nickname = DrawNicknameInput.txt_nickname;
                DatiCondivisi d = DatiCondivisi.getInstance();
                d.setMyNickname(txt_nickname.getText());
                //inizia ad ascoltare
                DrawMessagePage drawMessagePage = new DrawMessagePage();
                drawMessagePage.draw(frame);
            }
        });

        frame.add(lbl_request);
        frame.add(txt_nickname);
        frame.add(btn_confirm_nickname);
        frame.revalidate();
        frame.repaint();
    }

}

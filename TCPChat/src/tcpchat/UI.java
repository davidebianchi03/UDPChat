package tcpchat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/*
    Classe che serve per gestire il frame grafico
 */
public class UI extends JFrame {

    private JLabel lbl_logo_img = null;
    private JLabel lbl_instructions = null;
    private JButton btn_confirm = null;
    private JTextField txt_nickname = null;

    public UI() {
        getContentPane().setLayout(null);//-->Importante 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fermo tutta l'applicazione quando chiudo il jframe
        setSize(1000, 700);
        drawNicknamePage();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                screenResized();
            }
        });
        //Imposto l'icona del programma
        URL url = getClass().getResource("/images/logo.png");
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
        setVisible(true);
    }

    void drawNicknamePage() {
        //Imagine logo
        URL url = getClass().getResource("/images/logo.png");
        ImageIcon img = new ImageIcon(url);
        Image resized_img = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lbl_logo_img = new JLabel(new ImageIcon(resized_img));
        lbl_logo_img.setBounds(getWidth() / 2 - 50, getHeight() / 2 - 140, 100, 100);
        add(lbl_logo_img);

        //JLabel che dice all'utente cosa fare
        lbl_instructions = new JLabel("Inserisci il tuo nickname", JLabel.CENTER);
        lbl_instructions.setFont(new Font("Verdana", Font.PLAIN, 13));
        lbl_instructions.setBounds(getWidth() / 2 - 100, getHeight() / 2 - 35, 200, 35);
        add(lbl_instructions);
        System.out.println(getWidth());

        //TextField per l'inserimento del nickname
        txt_nickname = new JTextField("Your username");
        txt_nickname.setFont(new Font("Verdana", Font.PLAIN, 13));
        txt_nickname.setBounds(getWidth() / 2 - 100, getHeight() / 2, 200, 35);
        Insets i = new Insets(10, 10, 10, 10);
        SimpleBorder txt_nickname_border = new SimpleBorder(Color.BLACK, 1, 25, i);//Color border_color, int border_thickness, int border_radius, Insets padding
        txt_nickname.setBorder(txt_nickname_border);
        add(txt_nickname);

        //Pulsante per la conferma
        btn_confirm = new JButton("<html><body style = 'color:white'>Conferma</body></html>");
        btn_confirm.setFont(new Font("Verdana", Font.PLAIN, 13));
        btn_confirm.setBounds(getWidth() / 2 - 100, getHeight() / 2 + 40, 200, 35);
        Color btn_bg_color = new Color(28, 31, 51);
        SimpleBorder button_confirm_border = new SimpleBorder(btn_bg_color, 1, 25, i);//Color border_color, int border_thickness, int border_radius, Insets padding
        txt_nickname.setBorder(txt_nickname_border);
        btn_confirm.setBorder(button_confirm_border);
        btn_confirm.setBackground(btn_bg_color);
        add(btn_confirm);
    }

    void screenResized() {
        lbl_logo_img.setBounds(getWidth() / 2 - 50, getHeight() / 2 - 140, 100, 100);
        lbl_instructions.setBounds(getWidth() / 2 - 100, getHeight() / 2 - 35, 200, 35);
        txt_nickname.setBounds(getWidth() / 2 - 100, getHeight() / 2, 200, 35);
        btn_confirm.setBounds(getWidth() / 2 - 100, getHeight() / 2 + 40, 200, 35);
        revalidate();
        repaint();
    }

}

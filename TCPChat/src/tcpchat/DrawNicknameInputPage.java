package tcpchat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Davide
 */
public class DrawNicknameInputPage {

    /*
        Classe che si occupa di disegnare la scherma di input del nickname
     */
    private UI ui;
    //Componenti grafici disegnati sullo schermo
    private JLabel lbl_logo_img = null;
    private JLabel lbl_instructions = null;
    private JButton btn_confirm = null;
    private JTextField txt_nickname = null;

    public DrawNicknameInputPage(UI ui) {
        this.ui = ui;
    }

    public void drawPage() {
        //rimuovo tutto dallo schermo
        ui.getContentPane().removeAll();
        ui.revalidate();
        ui.repaint();
        //Imagine logo
        URL url = getClass().getResource("/images/logo.png");
        ImageIcon img = new ImageIcon(url);
        Image resized_img = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lbl_logo_img = new JLabel(new ImageIcon(resized_img));
        lbl_logo_img.setBounds(ui.getWidth() / 2 - 50, ui.getHeight() / 2 - 140, 100, 100);
        ui.add(lbl_logo_img);

        //JLabel che dice all'utente cosa fare
        lbl_instructions = new JLabel("Inserisci il tuo nickname", JLabel.CENTER);
        lbl_instructions.setFont(new Font("Verdana", Font.PLAIN, 13));
        lbl_instructions.setBounds(ui.getWidth() / 2 - 100, ui.getHeight() / 2 - 35, 200, 35);
        ui.add(lbl_instructions);

        //TextField per l'inserimento del nickname
        txt_nickname = new JTextField("Your username");
        txt_nickname.setFont(new Font("Verdana", Font.PLAIN, 13));
        txt_nickname.setBounds(ui.getWidth() / 2 - 100, ui.getHeight() / 2, 200, 35);
        Insets i = new Insets(10, 10, 10, 10);
        SimpleBorder txt_nickname_border = new SimpleBorder(Color.BLACK, 1, 25, i);//Color border_color, int border_thickness, int border_radius, Insets padding
        txt_nickname.setBorder(txt_nickname_border);
        ui.add(txt_nickname);

        //Pulsante per la conferma
        btn_confirm = new JButton("<html><body style = 'color:white'>Conferma</body></html>");
        btn_confirm.setFont(new Font("Verdana", Font.PLAIN, 13));
        btn_confirm.setBounds(ui.getWidth() / 2 - 100, ui.getHeight() / 2 + 40, 200, 35);
        Color btn_bg_color = new Color(28, 31, 51);
        SimpleBorder button_confirm_border = new SimpleBorder(btn_bg_color, 1, 25, i);//Color border_color, int border_thickness, int border_radius, Insets padding
        txt_nickname.setBorder(txt_nickname_border);
        btn_confirm.setBorder(button_confirm_border);
        btn_confirm.setBackground(btn_bg_color);
        btn_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //quando il pulsante viene premuto cambio pagina
                SharedData shared_data = SharedData.getInstance();
                shared_data.setNickname(txt_nickname.getText());
                DrawMessagePage d = new DrawMessagePage(ui);
                d.drawPage();

                ui.addComponentListener(new ComponentAdapter() {

                    public void componentResized(ComponentEvent componentEvent) {
                        d.screenResized();
                    }
                }
                );

            }
        });
        ui.add(btn_confirm);
    }

    void screenResized() {
        lbl_logo_img.setBounds(ui.getWidth() / 2 - 50, ui.getHeight() / 2 - 140, 100, 100);
        lbl_instructions.setBounds(ui.getWidth() / 2 - 100, ui.getHeight() / 2 - 35, 200, 35);
        txt_nickname.setBounds(ui.getWidth() / 2 - 100, ui.getHeight() / 2, 200, 35);
        btn_confirm.setBounds(ui.getWidth() / 2 - 100, ui.getHeight() / 2 + 40, 200, 35);
        ui.revalidate();
        ui.repaint();
    }
}

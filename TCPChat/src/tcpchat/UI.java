package tcpchat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
    Classe che serve per gestire il frame grafico
 */
public class UI extends JFrame {

    private FlowLayout frame_layout;

    public UI() {
        frame_layout = new FlowLayout();
        getContentPane().setLayout(frame_layout);//-->Importante drawInsertNicknamePage();
        setSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fermo tutta l'applicazione quando chiudo il jframe
        drawInsertNicknamePage();
        setVisible(true);

    }

    /*
        Metodo per disegnare la pagine che serve per permettere all'utente di inserire il proprio nickname
     */
    void drawInsertNicknamePage() {
        
        JLabel lbl_istruzioni = new JLabel("Inserisci qui il tuo nickname");
        lbl_istruzioni.setHorizontalAlignment(JLabel.CENTER);
        lbl_istruzioni.setBounds(50, 50, 250, 150);
        getContentPane().add(lbl_istruzioni);

        JTextField txt_nickname = new JTextField("Your username");
        Insets padding = new Insets(0, 0, 0, 0);
        SimpleBorder border = new SimpleBorder(Color.BLACK, 2, 0, padding);//Color border_color, int border_thickness, int border_radius, int padding
        txt_nickname.setBorder(border);
        txt_nickname.setBounds(50, 150, 150, 25);
        getContentPane().add(txt_nickname);
    }
}

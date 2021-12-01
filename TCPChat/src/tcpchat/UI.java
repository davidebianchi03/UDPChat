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
        SharedData shared_data = SharedData.getInstance();
        shared_data.setUi(this);
        getContentPane().setLayout(null);//-->Importante 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fermo tutta l'applicazione quando chiudo il jframe
        setSize(1000, 700);
        DrawNicknameInputPage d = new DrawNicknameInputPage(this);
        d.drawPage();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                d.screenResized();
            }
        });
        //Imposto l'icona del programma
        setFrameIcon("/images/logo.png");
        setVisible(true);
    }
    
    public void setFrameIcon(String path){
        URL url = getClass().getResource(path);
        ImageIcon img = new ImageIcon(url);
        setIconImage(img.getImage());
    }
}

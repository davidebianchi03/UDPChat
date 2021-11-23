
package udpchat;

/**
 *
 * @author Davide
 */
public class MainClass {

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frame f = new Frame();
                f.setVisible(true);
                DatiCondivisi d = DatiCondivisi.getInstance();
                d.setFrame(f);
            }
        });
    }

}

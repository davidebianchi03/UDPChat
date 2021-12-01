package tcpchat;

/**
 *
 * @author Davide
 */
public class DrawMessagePage {
    
    private UI ui;
    
    public DrawMessagePage(UI ui){
        this.ui = ui;
    }
    
    public void drawPage(){
        //rimuovo tutto dallo schermo
        ui.getContentPane().removeAll();
        ui.revalidate();
        ui.repaint();
    }
    
}

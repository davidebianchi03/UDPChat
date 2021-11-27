package udpchat;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Davide
 */
public class MessageImageList {
    
    private List<Image> lista_messaggi;
    
    public MessageImageList(){
        lista_messaggi = new ArrayList<>();
    }
    
    public void addMessage(Image msg){
        lista_messaggi.add(msg);
        //create image
        //draw the image
    }    
}

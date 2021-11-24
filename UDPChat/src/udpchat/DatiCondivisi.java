package udpchat;

import java.net.InetAddress;

/**
 *
 * @author Davide
 */
public class DatiCondivisi {
    
    private String my_nickname = "";
    private Frame frame = null;
    private static DatiCondivisi instance = null;
    private Connessione connessione = null;
    private InetAddress remote_ip; 
    private Listen listen;
    private DatiCondivisi(){
        
    }
    
    public static DatiCondivisi getInstance(){
        if(instance == null){
            synchronized(DatiCondivisi.class){
                if(instance == null)
                    instance = new DatiCondivisi();
            }
        }
        return instance;
    }

    public String getMyNickname() {
        return my_nickname;
    }

    public void setMyNickname(String my_nickname) {
        this.my_nickname = my_nickname;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public Connessione getConnessione() {
        return connessione;
    }

    public void setConnessione(Connessione connessione) {
        this.connessione = connessione;
    }

    public InetAddress getRemote_ip() {
        return remote_ip;
    }

    public void setRemote_ip(InetAddress remote_ip) {
        this.remote_ip = remote_ip;
    }

    public Listen getListen() {
        return listen;
    }

    public void setListen(Listen listen) {
        this.listen = listen;
    }
    
    
    
    
    
    
    
    
    
}

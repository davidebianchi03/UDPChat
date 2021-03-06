package tcpchat;

/**
 *
 * @author Davide
 */
public class SharedData {
    
    private static SharedData instance;
    private String nickname;
    private UI ui;
    
    private SharedData(){
        
    }
    
    public static SharedData getInstance(){
        if(instance == null){
            synchronized(SharedData.class){
                if(instance == null){
                    instance = new SharedData();
                }
            }
        }
        return instance;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }
    
    

   
    
    
    
    
    
    
}

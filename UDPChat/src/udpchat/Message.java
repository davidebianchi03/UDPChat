package udpchat;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 *
 * @author Davide
 */
public class Message {

    private String comando = null;
    private String corpo_messaggio = null;
    private DatagramPacket pacchetto = null;
    private InetAddress indirizzo_ip;

    public Message() {
        comando = "";
        corpo_messaggio = "";
    }

    public Message(String comando, String corpo_messaggio, InetAddress indirizzo_ip) {
        this.comando = comando;
        this.corpo_messaggio = corpo_messaggio;
        this.indirizzo_ip = indirizzo_ip;
    }
    
    /*
        Metodo per caricare il messaggio da una stringa in formato csv
    */
    public static Message loadFromCSV(String csv_string){
        String comando = "";
        String corpo_messaggio = "";
        
        int pos = csv_string.indexOf(';');
        comando = csv_string.substring(0, pos);
        corpo_messaggio = csv_string.substring(pos + 1, csv_string.length());
        
        //Creo oggetto messaggio e lo restituisco
        Message m = new Message(comando, corpo_messaggio, null);
        return m;
    }
    
    /*
        Metodo per caricare il messaggio dal tipo di oggetto 'DatagramPacket'
    */
    public static Message loadFromDatagramPacket(DatagramPacket pacchetto){
        byte[] buffer = pacchetto.getData();
        String stringa_ricevuta = new String(buffer,0,pacchetto.getLength());
        Message m = loadFromCSV(stringa_ricevuta);
        m.setPacchetto(pacchetto);
        return m;
    }
    
    /*
        Metodo per salvare il contenuto dell'oggetto in una stringa in formato CSV
    */
    public String toCSV(){
        String csv_string = comando + ";" + corpo_messaggio+";";
        return csv_string;
    }
    
    /*
        Metodi get e set
    */
    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getCorpo_messaggio() {
        return corpo_messaggio;
    }

    public void setCorpo_messaggio(String corpo_messaggio) {
        this.corpo_messaggio = corpo_messaggio;
    }

    public DatagramPacket getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(DatagramPacket pacchetto) {
        this.pacchetto = pacchetto;
    }

    public InetAddress getIndirizzo_ip() {
        return indirizzo_ip;
    }

    public void setIndirizzo_ip(InetAddress indirizzo_ip) {
        this.indirizzo_ip = indirizzo_ip;
    }
    
    
    

}

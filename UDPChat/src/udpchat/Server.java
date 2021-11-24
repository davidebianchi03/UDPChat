package udpchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Davide
 */
public class Server {

    /*
        Classe server utilizzata per la gestione della comunicazione con l'altro peer, si tratta di un singleton
     */
    private DatagramSocket server_socket;
    private static Server instance = null;
    private final int server_port = 2003;//porta su cui il server dovrà lavorare

    /*
        Costruttore parametrico, l'unico parametro da passare è il numero della porta al quale il server dovrà ascoltare
     */
    private Server() throws SocketException {
        server_socket = new DatagramSocket(server_port);
    }

    /*
        Metodo che restituisce un'istanza di Server, se è già stato creata, altrimenti ne crea una nuova
    */
    public static Server getInstance() throws SocketException {
        if (instance == null) {
            synchronized (Server.class) {
                if (instance == null) {
                    instance = new Server();
                }
            }
        }
        return instance;
    }
    
    /*
        Metodo per inviare un datagramma all'altro peer
    */
    public void sendMessage(Message messaggio) throws IOException{
        String stringa_messaggio = messaggio.toCSV();
        byte[] buffer_invio = stringa_messaggio.getBytes();
        DatagramPacket pacchetto_invio = new DatagramPacket(buffer_invio, buffer_invio.length);
        DatiCondivisi d = DatiCondivisi.getInstance();
        pacchetto_invio.setAddress(messaggio.getIndirizzo_ip());
        pacchetto_invio.setPort(2003);
        server_socket.send(pacchetto_invio);
    }
    
    /*
        Metodo per ricevere un datagramma dall'altro peer
    */
    public Message receiveMessage() throws IOException{
        byte[] buffer_ricezione = new byte[1500];
        DatagramPacket pacchetto_ricezione = new DatagramPacket(buffer_ricezione, buffer_ricezione.length);
        server_socket.receive(pacchetto_ricezione);
        Message messaggio = Message.loadFromDatagramPacket(pacchetto_ricezione);
        messaggio.setIndirizzo_ip(pacchetto_ricezione.getAddress());
        return messaggio;
    }
    
    public DatagramSocket getServerSocket(){
        return server_socket;
    }
    
    /*
        Metodo per cancellare l'istanza della classe server
    */
    public static void deleteInstance(){
        //Close server socket
        instance.getServerSocket().close();
        instance = null;
    }

}

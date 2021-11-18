
package udpchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Davide
 */
public class UDPChat {

    
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket s = new DatagramSocket(2003);
        byte[] buffer = new byte[1500];
        DatagramPacket p = new DatagramPacket(buffer, buffer.length);
        s.receive(p);
        Message m = Message.loadFromDatagramPacket(p);
        Elabora.elabora(m);
    }
    
}

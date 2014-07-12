package finalproject.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinalProjectClient {
    private Players ppl;
    private ServerSocket ssock;
    private Socket sock;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String[] playerData;
    
    FinalProjectClient(int port){        
        try {
            ppl = new Players();
            ssock = new ServerSocket(port);
            System.out.println("Listening");
            while (true) {
                sock = ssock.accept();
                input = new ObjectInputStream(sock.getInputStream());
                output = new ObjectOutputStream(sock.getOutputStream());
                System.out.println("Connected");
                playerData = (String[]) input.readObject();
                ppl.addPlayer(sock, playerData[0], Integer.valueOf(playerData[1]));
                new Thread( new MultiThreadedServer(sock) ).start();
            }  
        } catch (IOException ex) {
            Logger.getLogger(FinalProjectClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FinalProjectClient.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}

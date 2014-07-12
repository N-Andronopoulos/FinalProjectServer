package finalproject.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MultiThreadedServer implements Runnable {
    Socket csocket;
    MultiThreadedServer(Socket csocket) {
        this.csocket = csocket;
    }

    @Override
    public void run() {
        try (
                ObjectInputStream input = 
                        new ObjectInputStream(csocket.getInputStream()); 
                ObjectOutputStream output = 
                        new ObjectOutputStream(csocket.getOutputStream())           
        ){
            //System.out.println("Client passed");
            //output.writeObject("Hello");
            System.exit(1);
        }catch (IOException e) {
            System.out.println(e);
        }
        
    }
}
package finalproject.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

private Players ppl;
private GameLogic gm;
private ServerSocket ssock;
private Socket sock;
private ObjectInputStream input;
private ObjectOutputStream output;
private String[] playerData;
private ArrayList<PlayerRunTime> connections;
private PlayerRunTime runTime;

Server(int port) {
    try {
	ppl = new Players();
	ssock = new ServerSocket(port);
	System.out.println("Listening");
	connections = new ArrayList();
	while (true) {
	    sock = ssock.accept();
	    input = new ObjectInputStream(sock.getInputStream());
	    output = new ObjectOutputStream(sock.getOutputStream());
	    System.out.println("Player Connected");
	    playerData = (String[]) input.readObject();
	    ppl.addPlayer(sock, playerData[0], Integer.valueOf(playerData[1]));
	    runTime = new PlayerRunTime(sock, ppl);
	    connections.add(runTime);
	    new Thread(runTime).start();
	}
    } catch (IOException | ClassNotFoundException ex) {
	Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
    }

}

private boolean addThread(PlayerRunTime x) {
    if (connections.size() > 0) {
	for (PlayerRunTime tmp : connections) {
	    //Updates all the threads with the new Players list
	    tmp.update(ppl);
	}
	return true;
    } else {
	return connections.add(x);
    }
}

private void removeThread(int index) {
    connections.remove(index);
}

}

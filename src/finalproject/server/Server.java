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

private PlayerList ppl;
private ServerSocket ssock;
private Socket sock;
private ObjectInputStream input;
private ObjectOutputStream output;
private String[] playerData;
private ArrayList<PlayerRunTime> connections;
private GameSettings gms;
private Player currentPlayer;

Server(int port) {
    try {
	ppl = new PlayerList();
	ssock = new ServerSocket(port);
	System.out.println("Listening");
	connections = new ArrayList();
	//First Player sets the game settings
	sock = ssock.accept();
	input = new ObjectInputStream(sock.getInputStream());
	output = new ObjectOutputStream(sock.getOutputStream());
	//Exchange info
	currentPlayer = (Player) input.readObject();
	output.writeObject("OK");
	gms = (GameSettings) input.readObject();
	output.writeObject("OK");
	while (gms.getPlayers() != ppl.size()) {
	    sock = ssock.accept();
	    input = new ObjectInputStream(sock.getInputStream());
	    output = new ObjectOutputStream(sock.getOutputStream());
	    currentPlayer = (Player) input.readObject();
	    addPlayer(currentPlayer);
	    output.writeObject("OK");	    
	}
    } catch (IOException | ClassNotFoundException ex) {
	Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
    }

}

private boolean addPlayer(Player p) {
    if (ppl.size() > 3) {
	return false;
    } else {
	return ppl.addPlayer(p);
    }
}

}

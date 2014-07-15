package server;

import commonEntities.Dice;
import commonEntities.GameSettings;
import commonEntities.Pawn;
import commonEntities.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

private ServerSocket ssock;
private Socket sock;
private ObjectInputStream input;
private ObjectOutputStream output;
private String[] playerData;
private GameSettings gms;
private HashMap<Socket, Player> playerList;
private HashMap<Socket, ObjectStreams> socketStreams;
private Player currentPlayer;
private Pawn pwn;
private Dice dc;

Server(int port) {
    try {
	playerList = new HashMap<>();
	socketStreams = new HashMap<>();
	ssock = new ServerSocket(port);
	System.out.println("Listening");
	//First Player sets the game settings
	sock = ssock.accept();
	addStreams(sock, new ObjectInputStream(sock.getInputStream()), new ObjectOutputStream(sock.getOutputStream()));
	System.out.println("Connection is made");
	//Tell if this is a new game
	sendData(sock, "NEW");
	//Exchange info
	currentPlayer = (Player) readData(sock);
	addPlayer(sock, currentPlayer);
	System.out.println("Game Master Connected: " + currentPlayer.getName());
	sendData(sock,"OK");
	gms = (GameSettings) readData(sock);
	sendData(sock,"OK");
	//Adding new players
	while (gms.getPlayers() != playerList.size()) {
	    System.out.println("Waiting for other players...");
	    sock = ssock.accept();	    
	    addStreams(sock, new ObjectInputStream(sock.getInputStream()), new ObjectOutputStream(sock.getOutputStream()));
	    
	    System.out.println("New connection...");
	    //Tell if this is a new game
	    sendData(sock,"EXISTS");
	    //Read players info
	    currentPlayer = (Player) readData(sock);
	    System.out.println("Player Connected: " + currentPlayer.getName());
	    addPlayer(sock, currentPlayer);
	    sendData(sock,"OK");
	}
	playerData = new String[gms.getPlayers()];
	System.out.println("Starting Game...");
	syncGame();
	announce("Game Starts!");
	StartGame();
	endGame();

    } catch (IOException | ClassNotFoundException ex) {
	Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
    }

}

private boolean addPlayer(Socket s, Player p) {
    if (playerList.size() > 3) {
	return false;
    } else {
	playerList.put(s, p);
	return true;
    }
}

private void addStreams(Socket s, ObjectInputStream i, ObjectOutputStream o) {
    socketStreams.put(s, new ObjectStreams(i, o));
}

private ObjectOutputStream getOutStream(Socket s){
    return socketStreams.get(s).out;
}

private ObjectInputStream getInStream(Socket s){
    return socketStreams.get(s).in;
}

private void StartGame() {

}

private void announce(String data) throws IOException {
    for (Socket s : playerList.keySet()) {
	sendData(s, data);
    }
}

private void updatePawn(Socket origin, Pawn p) throws IOException {
    for (Socket s : playerList.keySet()) {
	if (s != origin) {
	    sendData(s, p);
	}
    }
}

private void sendData(Socket s, Object data) throws IOException {
    getOutStream(s).writeObject(data);
}

private Object readData(Socket s) throws IOException, ClassNotFoundException {
    return getInStream(s).readObject();
}

private void endGame() throws IOException {
    announce("Game Over");
    for (Socket s : playerList.keySet()) {
	s.close();
	//CAUTION HERE... may cause something to go wrong here....
	playerList.remove(s);
    }
    System.out.println("Game Ended, player disconnected. Thanks for playing");
    System.exit(1);
}

private void syncGame() throws IOException {
    int counter = 0;
    for (Socket s : playerList.keySet()) {
	playerData[counter] = playerList.get(s).toString();
	counter++;
    }
    for (Socket s : playerList.keySet()) {
	sendData(s, gms);
	sendData(s, playerData);
    }
    System.out.println("Setup Complete! Starting Game...");
}

class ObjectStreams {

ObjectInputStream in;
ObjectOutputStream out;

private ObjectStreams(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
}

}

}

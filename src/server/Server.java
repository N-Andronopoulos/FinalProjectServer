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
private HashMap<Socket,Player> playerList;
private Player currentPlayer;
private Pawn pwn;
private Dice dc;

Server(int port) {
    try {
	playerList = new HashMap<>();
	ssock = new ServerSocket(port);
	System.out.println("Listening");
	//First Player sets the game settings
	sock = ssock.accept();
	input = new ObjectInputStream(sock.getInputStream());
	output = new ObjectOutputStream(sock.getOutputStream());
	System.out.println("Connection is made");
	//Exchange info
	currentPlayer = (Player) input.readObject();
	addPlayer(sock,currentPlayer);
	System.out.println("Game Master Connected: "+currentPlayer.getName());
	output.writeObject("OK");
	gms = (GameSettings) input.readObject();
	output.writeObject("OK");
	while (gms.getPlayers() != playerList.size()) {
	    System.out.println("Waiting for other players...");
	    sock = ssock.accept();
	    input = new ObjectInputStream(sock.getInputStream());
	    output = new ObjectOutputStream(sock.getOutputStream());
	    System.out.println("New connection...");
	    currentPlayer = (Player) input.readObject();
	    System.out.println("Player Connected: "+currentPlayer.getName());
	    addPlayer(sock,currentPlayer);
	    output.writeObject("OK");
	}
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

private void StartGame() {
    while(true){
	for(Socket s : playerList.keySet()){
	    //ampla
	}
    }

}

private void announce(String data) throws IOException{
    for(Socket s : playerList.keySet()){
	sendData(s, data);
    }
}

private void updatePawn(Pawn p) throws IOException{
    for(Socket s : playerList.keySet()){
	sendData(s, p);
    }
}
private void sendData(Socket x, Object data) throws IOException{
    output = new ObjectOutputStream(x.getOutputStream());
    input = new ObjectInputStream(x.getInputStream());
    output.writeObject(data);
}

private Object readData(Socket x) throws IOException, ClassNotFoundException{
    output = new ObjectOutputStream(x.getOutputStream());
    input = new ObjectInputStream(x.getInputStream());
    return input.readObject();
}

private void endGame() throws IOException{
    announce("Game Over");
    for(Socket s : playerList.keySet()){
	s.close();
	//CAUTION HERE... may cause something to go wrong here....
	playerList.remove(s);
    }
    System.out.println("Game Ended, player disconnected. Thanks for playing");
    System.exit(1);
}

private void syncGame() throws IOException {
    for(Socket s : playerList.keySet()){
	sendData(s, gms);
	sendData(s, playerList);
    }
    System.out.println("Setup Complete! Starting Game...");
}

}

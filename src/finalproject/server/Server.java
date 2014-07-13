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

private ServerSocket ssock;
private Socket sock;
private ObjectInputStream input;
private ObjectOutputStream output;
private String[] playerData;
private GameSettings gms;
private ArrayList<Player> playerList;
private Player currentPlayer;
private Pawn pwn;
private Dice dc;

Server(int port) {
    try {
	playerList = new ArrayList<>();
	ssock = new ServerSocket(port);
	System.out.println("Listening");
	//First Player sets the game settings
	sock = ssock.accept();
	input = new ObjectInputStream(sock.getInputStream());
	output = new ObjectOutputStream(sock.getOutputStream());
	//Exchange info
	currentPlayer = (Player) input.readObject();
	addPlayer(currentPlayer);
	System.out.println("Game Master Connected: "+currentPlayer.name);
	output.writeObject("OK");
	gms = (GameSettings) input.readObject();
	output.writeObject("OK");
	while (gms.getPlayers() != playerList.size()) {
	    sock = ssock.accept();
	    input = new ObjectInputStream(sock.getInputStream());
	    output = new ObjectOutputStream(sock.getOutputStream());
	    currentPlayer = (Player) input.readObject();
	    addPlayer(currentPlayer);
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

private boolean addPlayer(Player p) {
    if (playerList.size() > 3) {
	return false;
    } else {
	return playerList.add(p);
    }
}

private void StartGame() {
    while(true){
	for(Player p : playerList){
	    //ampla
	    
	}
    }

}

private void announce(String data) throws IOException{
    for(Player x: playerList){
	sendData(x.getSocket(), data);
    }
}

private void updatePawn(Pawn p) throws IOException{
    for(Player x: playerList){
	sendData(x.getSocket(), p);
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
    for(Player x: playerList){
	x.getSocket().close();
	//CAUTION HERE... may cause something to go wrong here....
	playerList.remove(x);
    }
    System.out.println("Game Ended, player disconnected. Thanks for playing");
}

private void syncGame() throws IOException {
    for(Player x: playerList){
	sendData(x.getSocket(), gms);
	sendData(x.getSocket(), playerList);
    }
    System.out.println("Setup Complete! Starting Game...");
}

}

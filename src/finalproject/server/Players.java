package finalproject.server;

import java.net.Socket;
import java.util.ArrayList;

public class Players {

private final ArrayList<Player> playerList;
private final String[] colours = {"White", "Black", "Red", "Blue"};

Players() {
    playerList = new ArrayList<>();
}

public boolean addPlayer(Socket sok, String name, int colour) {
    return playerList.add(new Player(sok, name, colour));
}

public boolean rmPlayer(Socket sok, String name, int color) {
    return playerList.remove(new Player(sok, name, color));
}

public ArrayList lsPlayer() {
    return playerList;
}

public int sizePlayer() {
    return playerList.size();
}

public boolean isEmpty() {
    return playerList.size() <= 0;
}

void clear() {
    playerList.clear();
}

class Player {

public String name;
public Socket socket;
public int colour;

Player(Socket s, String name, int colour) {
    this.name = name;
    this.socket = s;
    this.colour = colour;
}

@Override
public String toString() {
    return socket + name + colour;
}
}

}

package finalproject.server;

import commonEntities.Player;
import java.util.ArrayList;

public class PlayerList {

private final ArrayList<Player> playerList;
private final String[] colours = {"White", "Black", "Red", "Blue"};

PlayerList() {
    playerList = new ArrayList<>();
}

public boolean addPlayer(Player p) {
    return playerList.add(p);
}

public boolean rmPlayer(Player p) {
    return playerList.remove(p);
}

public ArrayList lsPlayer() {
    return playerList;
}

public int size() {
    return playerList.size();
}

public boolean isEmpty() {
    return playerList.size() <= 0;
}

void clear() {
    playerList.clear();
}

}

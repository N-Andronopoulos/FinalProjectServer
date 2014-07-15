package commonEntities;

import java.io.Serializable;

public class GameSettings implements Serializable{

private int players;
private boolean rejoinable;
private int numberPawns;

public GameSettings(int players, boolean rejoinable, int numberPawns) {
    this.numberPawns=numberPawns;
    this.players=players;
    this.rejoinable=rejoinable;
}

public int getPlayers() {
    return players;
}

public void setPlayers(int players) {
    this.players = players;
}

public boolean isRejoinable() {
    return rejoinable;
}

public void setRejoinable(boolean rejoinable) {
    this.rejoinable = rejoinable;
}

public int getNumberPawns() {
    return numberPawns;
}

public void setNumberPawns(int numberPawns) {
    this.numberPawns = numberPawns;
}

}

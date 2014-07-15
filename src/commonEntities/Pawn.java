package commonEntities;

import java.io.Serializable;

public class Pawn implements Serializable{

private int color;
private String playerName;
private int position;

public int getColor() {
    return color;
}

public void setColor(int color) {
    this.color = color;
}

public String getPlayerName() {
    return playerName;
}

public void setPlayerName(String playerName) {
    this.playerName = playerName;
}

public int getPosition() {
    return position;
}

public void setPosition(int position) {
    this.position = position;
}

}

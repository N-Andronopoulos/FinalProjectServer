package commonEntities;

import java.io.Serializable;

/**
 * This class describes the game settings.
 *
 * @author Nikolas
 */
public class GameSettings implements Serializable {

    private int players;
    private boolean rejoinable;
    private int numberPawns;

    /**
     *
     * @param players Number of expected players.
     * @param rejoinable Marks the server as rejoinable if a player looses
     * connection.
     * @param numberPawns Number of Pawn in the game per player.
     */
    public GameSettings(int players, boolean rejoinable, int numberPawns) {
	this.numberPawns = numberPawns;
	this.players = players;
	this.rejoinable = rejoinable;
    }

    /**
     *
     * @return The number of expected players.
     */
    public int getPlayers() {
	return players;
    }

    /**
     *
     * @param players Sets the amount of player in the game.
     */
    public void setPlayers(int players) {
	this.players = players;
    }

    /**
     *
     * @return
     */
    public boolean isRejoinable() {
	return rejoinable;
    }

    /**
     *
     * @param rejoinable
     */
    public void setRejoinable(boolean rejoinable) {
	this.rejoinable = rejoinable;
    }

    /**
     *
     * @return
     */
    public int getNumberPawns() {
	return numberPawns;
    }

    /**
     *
     * @param numberPawns
     */
    public void setNumberPawns(int numberPawns) {
	this.numberPawns = numberPawns;
    }

    /**
     *
     * @return The object described as a string.
     */
    @Override
    public String toString() {
	return players + "," + rejoinable + "," + numberPawns;
    }

}

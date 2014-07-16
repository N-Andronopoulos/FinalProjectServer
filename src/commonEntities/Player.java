package commonEntities;

import java.io.Serializable;

/**
 * This is a class to describe a player.
 *
 * @author Nikolas
 */
public class Player implements Serializable {

    /**
     * Player's name.
     */
    public String name;

    /**
     * Player's selected color.
     */
    public int color;

    /**
     * Creates a Player with the given name and color.
     *
     * @param name Player's name.
     * @param color Player's selected color.
     */
    public Player(String name, int color) {
	this.name = name;
	this.color = color;
    }

    /**
     * Return the player's name.
     *
     * @return A String with the player's name.
     */
    public String getName() {
	return name;
    }

    /**
     * Sets the players name.
     *
     * @param name A String with the name.
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Returns the player's selected color.
     *
     * @return An integer designating the color.
     */
    public int getColor() {
	return color;
    }

    /**
     * Set the player's selected color.
     *
     * @param color An integer designating the color.
     */
    public void setColor(int color) {
	this.color = color;
    }

    /**
     * Converts the player's fields to string.
     *
     * @return Returns the player as a String.
     */
    @Override
    public String toString() {
	return name + ':' + color;
    }

}

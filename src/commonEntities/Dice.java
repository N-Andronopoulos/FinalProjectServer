package commonEntities;

import java.io.Serializable;

/**
 * This class describes the two dices in the game.
 *
 * @author Nikolas
 */
public class Dice implements Serializable {

    private int die;

    /**
     *
     * @param die1 The first die.
     */
    public Dice(int die) {
	this.die = die;
    }

    /**
     *
     * @return
     */
    public int getDie() {
	return die;
    }

    /**
     *
     * @param die
     */
    public void setDie(int die) {
	this.die = die;
    }
    /**
     * 
     * @return Returns the die as a String.
     */
    @Override
    public String toString(){
	return this.die+"";
    }
}

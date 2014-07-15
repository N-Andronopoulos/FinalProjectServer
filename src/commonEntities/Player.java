package commonEntities;

import java.io.Serializable;

/**
 *
 * @author Nikolas
 */
public class Player implements Serializable {

public String name;
public int colour;

public Player(String name, int colour) {
    this.name = name;
    this.colour = colour;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public int getColour() {
    return colour;
}

public void setColour(int colour) {
    this.colour = colour;
}

@Override
public String toString() {
    return name + ',' + colour;
}

}

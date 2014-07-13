package finalproject.server;

import java.net.Socket;

public class Player {

public String name;
public Socket socket;
public int colour;

Player(Socket s, String name, int colour) {
    this.name = name;
    this.socket = s;
    this.colour = colour;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public Socket getSocket() {
    return socket;
}

public void setSocket(Socket socket) {
    this.socket = socket;
}

public int getColour() {
    return colour;
}

public void setColour(int colour) {
    this.colour = colour;
}

@Override
public String toString() {
    return socket + name + colour;
}

}

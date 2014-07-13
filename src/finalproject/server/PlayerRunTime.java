package finalproject.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PlayerRunTime implements Runnable {

private Socket csocket;
private Players ppl;
private ObjectInputStream input;
private ObjectOutputStream ouput;

PlayerRunTime(Socket csocket, Players ppl) {
    this.csocket = csocket;
    this.ppl = ppl;
}

@Override
public void run() {
    System.out.println(ppl.lsPlayer());

}

public void update(Players ppl) {
    this.ppl = ppl;
}

}

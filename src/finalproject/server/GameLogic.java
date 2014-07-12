package finalproject.server;

import java.net.Socket;
import java.util.Random;

public class GameLogic {
    
    private int state;
    private int playerTurn;
    private Players ppl;
    private Random rand;
    GameLogic(Players ppl){
        this.ppl = ppl;
        this.state = 0;
    }
    
    public boolean addPlayer(Socket sok,String name,int color){
        return ppl.addPlayer(sok, name, color);
    }
    
    public int findFirstPlayer(){
        return rand.nextInt(ppl.sizePlayer()+1);
    }
}

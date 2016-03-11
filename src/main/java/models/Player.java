package models;

import java.util.List;

/**
 * Created by Braxton on 3/8/2016.
 */
abstract public class Player {
    public Game hostGame;
    String name;
    int stakeInPot;

    abstract public void initialDeal();

    public boolean autoLoses(){
        int colPos=0;
        while(colPos<hostGame.cols.size()){
            if(hostGame.ownership.get(colPos).contentEquals(name) && hostGame.colScore(colPos)<=21) {
                return false;
            }
            colPos++;
        }
        return true;
    }

}

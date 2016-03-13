package models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Braxton on 3/8/2016.
 */
abstract public class Player implements Serializable{
    public Game hostGame;
    int stakeInPot;

    abstract public void initialDeal();

    abstract public boolean loses();

}

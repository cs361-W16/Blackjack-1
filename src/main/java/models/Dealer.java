package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Braxton on 3/8/2016.
 */
public class Dealer extends Player implements Serializable {

    public void initialDeal(){
        Card card1=hostGame.drawCard();
        Card card2=hostGame.drawCard();
        card1.faceUp=false;
        hostGame.dealCardToCol(2,card1);
        hostGame.dealCardToCol(2,card2);
    }

    public boolean loses (){
        return hostGame.colScore(2)>21;
    }

    public void play(){
        Card theCard;
        while(hostGame.colScore(2)<17){
            theCard=hostGame.drawCard();
            hostGame.dealCardToCol(2,theCard);
        }
    }

    public Dealer(Game g){
        hostGame=g;
    }

}

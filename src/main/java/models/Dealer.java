package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Braxton on 3/8/2016.
 */
public class Dealer extends Player {

    public void initialDeal(){
        Card card1=drawCard();
        Card card2=drawCard();
        card1.faceUp=false;
        dealCardToCol(0,card1);
        dealCardToCol(0,card2);
    }

    public boolean loses (){
        return colScore(0)>21;
    }

    public void play(){
        Card theCard;
        while(colScore(0)<17){
            theCard=drawCard();
            dealCardToCol(0,theCard);
        }
    }

    public Dealer(java.util.List<java.util.List<Card>> Cols, java.util.List<Card> Deck ){
        cols=Cols;
        deck=Deck;
    }
    public Dealer (){}

}

package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Braxton on 3/8/2016.
 */
public class Dealer extends Player {

    boolean done;

    public void initialDeal(){
        Card card1=drawCard();
        Card card2=drawCard();
        card1.faceUp=false;
        dealCardToCol(0,card1);
        dealCardToCol(0,card2);
        done=false;
    }

    public boolean loses (){
        return colScore(0)>21;
    }

    public void autoplay(User theUser){
        if((theUser.zeroStayed || theUser.isBusted(0)) &&
           (theUser.cols.get(1).size()==0 || theUser.oneStayed || theUser.isBusted(1) )) {
            Card theCard;
            while (colScore(0) < 17) {
                theCard = drawCard();
                dealCardToCol(0, theCard);
            }
            done=true;
        }
    }

    public Dealer(java.util.List<java.util.List<Card>> Cols, java.util.List<Card> Deck ){
        cols=Cols;
        deck=Deck;
    }
    public Dealer (){
        //NEVER DELETE THIS
    }

}

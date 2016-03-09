package models;

import java.util.ArrayList;

/**
 * Created by Braxton on 3/8/2016.
 */
public class Dealer extends Player {

    public void initialDeal(){
        Card card1=hostGame.drawCard();
        Card card2=hostGame.drawCard();
        card1.faceUp=false;
        java.util.List<Card> dealtCol= new ArrayList<Card>();
        dealtCol.add(card1);
        dealtCol.add(card2);
        hostGame.cols.add(dealtCol);
        hostGame.ownership.add(name);
    }

    public void play(){
        int pos=0;
        while(hostGame.ownership.get(pos).contentEquals(name) || pos<hostGame.ownership.size()){
            pos++;
        }
        Card theCard;
        while(hostGame.colScore(pos)<17){
            theCard=hostGame.drawCard();
            hostGame.dealCardToCol(pos,theCard);
        }
    }

    public Dealer(){
        name="Dealer";
    }

}

package models;

import java.util.ArrayList;

/**
 * Created by Braxton on 3/8/2016.
 */
public class User extends Player {


    public void initialDeal(){
        Card card1=hostGame.drawCard();
        Card card2=hostGame.drawCard();
        java.util.List<Card> dealtCol= new ArrayList<Card>();
        dealtCol.add(card1);
        dealtCol.add(card2);
        hostGame.cols.add(dealtCol);
        hostGame.ownership.add(name);
    }

    public User(){
        name="User";
    }

    public void hit(){

    }

    public void stay(){

    }

    public void doubleDown(){

    }

    public void split(){

    }

}

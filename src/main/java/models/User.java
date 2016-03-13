package models;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Braxton on 3/8/2016.
 */
public class User extends Player implements Serializable {

    public String errorCode;
    public Boolean zeroIsStayed = false;
    public Boolean oneIsStayed = false;
    public Boolean isSplit = false;
    public int cardValue;
    public int colPos;


    public void initialDeal(){
        Card card1=hostGame.drawCard();
        Card card2=hostGame.drawCard();
        hostGame.isStay = false;
    }

    public boolean isBusted(int col){
        return hostGame.colScore(col)>21;
    }

    public boolean loses (){
        return isBusted(0) && isBusted (1);
    }

    public void hit(java.util.List<Card> dealtCol, Boolean isStay, int col){

        int cardValue = hostGame.colScore(col);

        if (cardValue > 21) { //if card value is > 21
            errorCode="You hand has already busted!";
            //isBusted = true;

        }
        else if (isStay == true) { //if user chose to stay
            errorCode="Unable to hit: You've already chose to stay.";
            //isBusted = false;

        }
        else { //else deal a card
            Card newCard = hostGame.drawCard();
            dealtCol.add(newCard);
            hostGame.cols.add(dealtCol);

        }
        errorCode=" ";

    }

    public boolean stay(int col){
        int cardValue = hostGame.colScore(col);

        if (cardValue < 21) {
            hostGame.isStay = true;
        }
        else {
            errorCode = "Your hand has already busted.";
            hostGame.isStay = false;
        }

        return hostGame.isStay;
    }

    public int doubleDown(int moneyOnBet){
        int newMoney = 0;
        newMoney = moneyOnBet *2;   //double the money on bet
        return newMoney;

    }

    public void split(Card card1, Card card2, java.util.List<Card> dealtCol){
            //if both cards has the same value and user has not split yet (can only split once)
            if (card2.getValue() == card1.getValue() ) {

                if (hostGame.isStay == false & isSplit.equals(false)) {

                    dealtCol.remove(card2);                                     //remove card2 from initial col
                    java.util.List<Card> dealtCol2 = new ArrayList<Card>();     //create another col
                    dealtCol2.add(card2);                                       //add to col that just created

                    Card newCard1 = hostGame.drawCard();                          //draw another card for both hands
                    Card newCard2 = hostGame.drawCard();

                    dealtCol.add(newCard1);                                     //add card to cols
                    dealtCol2.add(newCard2);

                    isSplit = true;
                }

            }

            else {

                errorCode = "Unable to split cards.";
                return;
            }

    }

    public int userBet(){
        hostGame.playerWin = hostGame.isPlayerWin();
        if(hostGame.playerWin = true){
            hostGame.totalCash += hostGame.pot;
            return hostGame.totalCash;
        }
        else if(hostGame.playerWin = false){
            hostGame.totalCash -= hostGame.pot;
            return hostGame.totalCash;
        }
        hostGame.pot += (hostGame.bet * 2);
        hostGame.didBet = true;
        return hostGame.totalCash;
    }


    public User (Game g){
        hostGame=g;
    }

}

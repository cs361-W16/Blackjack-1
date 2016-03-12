package models;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Braxton on 3/8/2016.
 */
public class User extends Player {

    public User(){
        name="User";
    }

    public String errorCode;
    public Boolean isBusted = false;
    public Boolean zeroStayed=false;
    public Boolean oneStayed=false;
    public Boolean isSplit = false;
    public int cardValue;
    public int colPos;


    public void initialDeal(){
        Card card1=hostGame.drawCard();
        Card card2=hostGame.drawCard();
        java.util.List<Card> dealtCol= new ArrayList<Card>();
        dealtCol.add(card1);
        dealtCol.add(card2);
        hostGame.cols.add(dealtCol);
        hostGame.isStay = false;
    }




    public void hit(int col){
        if(col<0 || col>1) {
            return;
        }


        if (hostGame.colScore(col)>21) { //if card value is > 21
            errorCode="You hand has already busted!";
            isBusted = true;
        }
        else if ( (col==0 && zeroStayed) || (col==1 && oneStayed) ) { //if user chose to stay
            errorCode="Unable to hit: You've already chose to stay.";
            isBusted = false;

        }
        else if (hostGame.didBet == true) { //else deal a card
            Card newCard = hostGame.drawCard();
            hostGame.dealCardToCol(col,newCard);
        }
        errorCode=" ";

    }



    public void stay(int col){
        if(col<0 || col>1) {
            errorCode = "Unable to identify col";
            return;
        }
        if (hostGame.colScore(col)>21) {
            errorCode = "Your hand has already busted.";
            isBusted = true;
        }
        else if(col == 0 && !isBusted){
            zeroStayed = true;
        }
        else if(col == 1 && !isBusted) {
            oneStayed = true;
        }
    }

    public int doubleDown(int moneyOnBet){
        int newMoney = 0;
        newMoney = moneyOnBet *2;   //double the money on bet

        return newMoney;
    }

    public void split(){

            //check if the col to be split has only 2 cards
            if(hostGame.cols.get(0).size()!=2){
                return;
            }

            //check if there is already a card in the 2nd col
            if(hostGame.cols.get(1).size()!=0){
                return;
            }

            //get value of card1 and card2 of col
            int card1 = hostGame.cols.get(0).get(0).getValue();
            int card2 = hostGame.cols.get(0).get(1).getValue();
            Card theCard2 = hostGame.cols.get(0).get(1);                //get the 2nd card from the col 0


            //if both cards has the same value and user has not split yet (can only split once)
            if (card1 == card2 ) {

                if (!zeroStayed  || !oneStayed || !isSplit) {

                    hostGame.cols.get(0).remove(1);              //remove 2nd card from col
                    hostGame.cols.get(1).add(theCard2);          //add 2nd card to col 1

                    Card newCard1 = hostGame.drawCard();         //draw another card for both col 0 and col 1
                    Card newCard2 = hostGame.drawCard();

                    hostGame.cols.get(0).add(newCard1);          //add card to cols
                    hostGame.cols.get(1).add(newCard2);

                    isSplit = true;
                }

            }

            else {
                errorCode = "Unable to split cards.";
                return;
            }

    }

    public int userBet(){
        User x = new User();
        x.hostGame.playerWin = x.hostGame.isPlayerWin();
        if(x.hostGame.playerWin = true){
            x.hostGame.totalCash += x.hostGame.pot;
            return x.hostGame.totalCash;
        }
        else if(x.hostGame.playerWin = false){
            x.hostGame.totalCash -= x.hostGame.pot;
            return x.hostGame.totalCash;
        }
        x.hostGame.pot += (x.hostGame.bet * 2);
        x.hostGame.didBet = true;
        return x.hostGame.totalCash;
    }


}

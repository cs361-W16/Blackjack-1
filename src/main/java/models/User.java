package models;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Braxton on 3/8/2016.
 */
public class User extends Player implements Serializable{

    public Boolean zeroStayed=false;
    public Boolean oneStayed=false;
    public Boolean doublezero=false;
    public Boolean doubleone=false;
    public Boolean isSplit = false;
    public boolean didBet = false;
    public int cardValue;
    public int colPos;

    public void initialDeal(){
        Card card1= drawCard();
        Card card2= drawCard();
        zeroStayed = false;
        dealCardToCol(0,card1);
        dealCardToCol(0,card2);
    }

    public boolean isBusted(int col){
        return  colScore(col)>21;
    }

    public boolean loses (){
        return isBusted(0) && isBusted (1);
    }

    public String hit(int col){

        if(col<0 || col>1) {
            return "Invalid column";
        }

        if(col==1 && !isSplit){
            return "Invalid column";
        }


        if ( colScore(col)>21) { //if card value is > 21
             return "You hand has already busted!";
        }

        else if ( (col==0 && zeroStayed) || (col==1 && oneStayed) ) { //if user chose to stay
             return "Unable to hit: You've already chose to stay.";
            //isBusted = false;

        }
         //else deal a card
        Card newCard =  drawCard();
        dealCardToCol(col,newCard);
        didBet = false;

         return "hit function";

    }



    public String stay(int col){
        if(col<0 || col>1) {
            return "Unable to identify col";
        }
        if ( colScore(col)>21) {
            return "Your hand has already busted.";
        }
        if(!isBusted(col)) {
            if(col==0){
                zeroStayed = true;
            }
            else {
                oneStayed = true;
            }
        }
        return " ";
    }

    public String doubleDown(int theCol){
        if(theCol==0){
            if(doublezero || zeroStayed || isBusted(0)){
                return "Cannot Double Down";
            }
            else{
                doublezero=true;
                hit(0);
                stay(0);
            }
        }
        else if(theCol==1 && isSplit){
            if(doubleone || oneStayed || isBusted(1) || !isSplit){
                return "Cannot Double Down";
            }
            else{
                doubleone=true;
                hit(1);
                stay(1);
            }
        }
        else{
            return "Invalid Column";
        }
        return " ";
    }

    public String split(){

            //check if the col to be split has only 2 cards
            if( cols.get(0).size()!=2){
                return " ";
            }

            //check if there is already a card in the 2nd col
            if( cols.get(1).size()!=0){
                return " ";
            }

            //get value of card1 and card2 of col
            int val1 =  cols.get(0).get(0).getValue();
            int val2 =  cols.get(0).get(1).getValue();               //get the 2nd card from the col 0

            //if both cards has the same value and user has not split yet (can only split once)

            if ( val1==val2 && !zeroStayed  && !oneStayed && !isSplit) {

                 dealCardToCol(1, cols.get(0).get(1));     //add 2nd card to col 1
                 cols.get(0).remove(1);              //remove 2nd card from col

                Card newCard1 =  drawCard();         //draw another card for both col 0 and col 1
                Card newCard2 =  drawCard();

                 cols.get(0).add(newCard1);          //add card to cols
                 cols.get(1).add(newCard2);

                isSplit = true;
                didBet = false;
            }


            else {
                return "Unable to split cards.";
            }

        return " ";
    }

    public User(java.util.List<java.util.List<Card>> Cols, java.util.List<Card> Deck ){
        cols=Cols;
        deck=Deck;
        oneStayed=false;
        zeroStayed=false;
    }

    public User(){
        //NEVER DELETE THIS
    }


}

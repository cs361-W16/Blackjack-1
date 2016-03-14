package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by michaelhilton on 1/25/16.
 */
public class Game implements Serializable {

    public java.util.List<Card> deck = new ArrayList<>();
    public java.util.List<java.util.List<Card>> cols = new ArrayList<>();

    public User theUser;
    public String user1State;
    public String user2State;
    public Dealer theDealer;

    public int ante;
    public int totalCash=100;
    public boolean playerWin = false;
    public boolean isStay;
    public int pot;

    public String errorCode;

    public Game() {

        user1State=" ";
        user2State=" ";

        buildDeck();
        shuffle();
        cols.add(new ArrayList<Card>());
        cols.add(new ArrayList<Card>());
        cols.add(new ArrayList<Card>());

        java.util.List<java.util.List<Card>> userCols = new ArrayList<>();
        java.util.List<Card> userDeck = new ArrayList<>();
        userCols.add(cols.get(0));
        userCols.add(cols.get(1));
        userDeck=deck;

        theUser=new User(userCols,userDeck);

        java.util.List<java.util.List<Card>> dealerCols = new ArrayList<>();
        java.util.List<Card> dealerDeck = new ArrayList<>();
        dealerCols.add(cols.get(2));
        dealerDeck=deck;

        theDealer=new Dealer(dealerCols,dealerDeck);

        ante = 2;
        theUser.initialDeal();
        theDealer.initialDeal();
    }

    public void reload(){
        cols.set(0,theUser.cols.get(0));
        cols.set(1,theUser.cols.get(1));
        cols.set(2,theDealer.cols.get(0));
    }

    public void judge(){
        if(theDealer.done){
            int zeroBet;
            if (theUser.doublezero){
                zeroBet=4;
            }
            else{
                zeroBet=2;
            }
            int oneBet;
            if (theUser.doubleone){
                oneBet=4;
            }
            else{
                oneBet=2;
            }
            if(theUser.isBusted(0) ){
                user1State="LOSE";
                totalCash-=zeroBet;
            }
            else if(theUser.colScore(0)<=theDealer.colScore(0)){
                user1State="LOSE";
                totalCash-=zeroBet;
            }
            else{
                user1State="WIN";
                totalCash+=zeroBet;
            }

            if (theUser.cols.get(1).size()==0){
                user2State=" ";
            }
            else if(theUser.isBusted(1) ){
                user1State="LOSE";
                totalCash-=oneBet;
            }
            else if(theUser.colScore(1)<=theDealer.colScore(0)){
                user2State="LOSE";
                totalCash-=oneBet;
            }
            else{
                user2State="WIN";
                totalCash+=oneBet;
            }
        }
    }

    public void buildDeck() {
        for (int i = 1; i < 14; i++) {
            deck.add(new Card(i, Suit.Clubs, true));
            deck.add(new Card(i, Suit.Hearts, true));
            deck.add(new Card(i, Suit.Diamonds, true));
            deck.add(new Card(i, Suit.Spades, true));
        }
    }

    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed));
    }


    //customDeal to setup game for testing purposes



    /*public String getCardFromURL(Card card) {
        String URL = "https://raw.githubusercontent.com/cs361-W16/Blackjack-1/master/src/main/java/assets/images/";
        int tempV = card.getValue();
        String tempS = determineSuit(card);
        URL = URL + tempV + "of" + tempS + ".png";
        return URL;
    }*/

    /*String determineSuit(Card card) {
        if (card.getSuit() == Suit.Clubs) {
            return "Clubs";
        } else if (card.getSuit() == Suit.Hearts) {
            return "Hearts";
        } else if (card.getSuit() == Suit.Diamonds) {
            return "Diamonds";
        } else if (card.getSuit() == Suit.Spades) {
            return "Spades";
        }
        else
            System.out.println("Unable to determine suit");
        return "";
    }*/
    public boolean isPlayerWin(int theCol) {

        if (isStay && (theUser.colScore(theCol) <= 21 && theUser.colScore(theCol)  > theDealer.colScore(0))) {
            return true;
        } else
            return false;
    }

    public void newGame(){

        user1State=" ";
        user2State=" ";

        deck.clear();
        buildDeck();
        shuffle();
        cols.get(0).clear();
        cols.get(1).clear();
        cols.get(2).clear();


        java.util.List<java.util.List<Card>> userCols = new ArrayList<>();
        java.util.List<Card> userDeck = new ArrayList<>();
        userCols.add(cols.get(0));
        userCols.add(cols.get(1));
        userDeck=deck;

        theUser=new User(userCols,userDeck);

        java.util.List<java.util.List<Card>> dealerCols = new ArrayList<>();
        java.util.List<Card> dealerDeck = new ArrayList<>();
        dealerCols.add(cols.get(2));
        dealerDeck=deck;

        theDealer=new Dealer(dealerCols,dealerDeck);

        ante = 2;
        theUser.initialDeal();
        theDealer.initialDeal();
    }


}
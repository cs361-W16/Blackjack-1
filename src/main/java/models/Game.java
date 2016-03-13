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
    public Dealer theDealer;

    public int pot;
    public int ante;
    public int totalCash=100;
    public boolean playerWin;
    public int bet;
    public boolean isStay;
    public boolean didBet;


    public String errorCode;

    public Game() {
        cols.add(new ArrayList<Card>());
        cols.add(new ArrayList<Card>());
        cols.add(new ArrayList<Card>());
        theUser=new User(this);
        theDealer=new Dealer(this);
        ante = 2;
        errorCode = " ";
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

    private boolean colHasCards(int colNumber) {
        return this.cols.get(colNumber).size() > 0;
    }

    public void dealCardToCol(int colTo, Card cardToDeal) {
        cols.get(colTo).add(cardToDeal);
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            buildDeck();
            shuffle();
        }
        Card result = deck.get(deck.size() - 1);
        deck.remove(deck.size() - 1);
        return result;
    }

    public int colScore(int col) {
        int pos = 0;
        int theScore = 0;
        int aces = 0;
        while (pos < cols.get(col).size()) {
            if (cols.get(col).get(pos).getValue() > 10) {
                theScore += 10;
            } else {
                theScore += cols.get(col).get(pos).getValue();
            }
            if (cols.get(col).get(pos).getValue() == 1) {
                aces++;
            }
            pos++;
        }
        while (theScore <= 11 && aces > 0) {
            aces--;
            theScore += 10;
        }
        return 0;
    }

    public String getCardFromURL(Card card) {
        String URL = "https://raw.githubusercontent.com/cs361-W16/Blackjack-1/master/src/main/java/assets/images/";
        int tempV = card.getValue();
        String tempS = determineSuit(card);
        URL = URL + tempV + "of" + tempS + ".png";
        return URL;
    }

    String determineSuit(Card card) {
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
    }


    public void newGame() {

    }

    public boolean isPlayerWin() {
        //go through the columns and check if the player has busted
        int colPos = 0;

        while (colPos < cols.size()) {
            if (colScore(colPos) <= 21) {
                if (isStay = true) { //if both dealer and player stayed
                    if (colScore(1) > colScore(2) || colScore(0) > colScore(2)) //players cards value is higher than dealers
                        playerWin = true;
                    return playerWin;
                }
            } else
                playerWin = false;
            colPos++;
            return playerWin;
        }
        return playerWin;

    }
}
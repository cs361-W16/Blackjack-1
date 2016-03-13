package models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Braxton on 3/8/2016.
 */
abstract public class Player implements Serializable{
    public java.util.List<java.util.List<Card>> cols;
    public java.util.List<Card> deck;

    int stakeInPot;

    abstract public void initialDeal();

    abstract public boolean loses();

    private boolean colHasCards(int colNumber) {
        return this.cols.get(colNumber).size() > 0;
    }

    public void dealCardToCol(int colTo, Card cardToDeal) {
        cols.get(colTo).add(cardToDeal);
    }

    public Card drawCard() {
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
        return theScore;
    }

    public Player(){}

}

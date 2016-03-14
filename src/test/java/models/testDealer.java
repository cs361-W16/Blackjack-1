package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Braxton on 3/13/2016.
 */
public class testDealer {

    @Test
    public void testDealerScoring(){
        Game g = new Game();
        g.theDealer.cols.get(0).clear();
        Card card1= new Card(1,Suit.Spades,true);
        Card card2= new Card(10,Suit.Spades,true);
        Card card3= new Card(1,Suit.Hearts,true);
        g.theDealer.dealCardToCol(0,card1);
        g.theDealer.dealCardToCol(0,card2);
        assertEquals(21,g.theDealer.colScore(0));
        g.theDealer.dealCardToCol(0,card3);
        assertEquals(12,g.theDealer.colScore(0));
    }

    @Test
    public void testDealerPlaying(){
        Game g = new Game();
        g.theDealer.autoplay(g.theUser);
        assertEquals(false,g.theDealer.done);
        g.theUser.stay(0);
        g.theDealer.autoplay(g.theUser);
        assertEquals(true,g.theDealer.done);

    }

    @Test
    public void testDealerLoses(){
        Game g = new Game();
        Dealer d = new Dealer();
        g.theDealer.cols.get(0).clear();
        Card card1= new Card(10,Suit.Clubs,true);
        Card card2= new Card(10,Suit.Spades,true);
        Card card3= new Card(10,Suit.Hearts,true);
        g.theDealer.dealCardToCol(0,card1);
        g.theDealer.dealCardToCol(0,card2);
        assertEquals(false,g.theDealer.loses());
        g.theDealer.dealCardToCol(0,card3);
        assertEquals(true,g.theDealer.loses());
    }

}

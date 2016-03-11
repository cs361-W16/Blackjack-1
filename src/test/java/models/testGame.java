package models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by michaelhilton on 1/25/16.
 */
public class testGame {

    @Test
    public void testGameCreation(){
        Game g = new Game();
        assertNotNull(g);
    }

    @Test
    public void testGameBuildDeck(){
        Game g = new Game();
        g.buildDeck();
        assertEquals(52,g.deck.size());

    }

    @Test //test if the col size increase after hitting
    public void testColSizeAfterHit(){
        Game g = new Game();
        User u = new User();
        u.hostGame = g;
        java.util.List<Card> dealtCol= new ArrayList<Card>();
        Boolean isStay = false;
        u.hit(dealtCol, isStay, 1);

        assertEquals(1, dealtCol.size());
    }

    @Test //test if user can hit when isStay is true
    public void testisStayForHit() {
        Game g = new Game();
        User u = new User();
        u.hostGame = g;
        java.util.List<Card> dealtCol= new ArrayList<Card>();
        Boolean isStay = false;
        u.hit(dealtCol, isStay, 1);

        assertEquals(false, u.isBusted);
    }

    @Test
    public void testStay(){
        Game g = new Game();
        User u = new User();
        u.hostGame = g;
        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        g.cols.add(new ArrayList<Card>());
        g.dealCardToCol(0,card1);
        g.dealCardToCol(0,card2);


        Boolean result = u.stay(0);

        assertEquals(true, result);
    }

    @Test
    public void testDoubleDown(){
        Game g = new Game();
        User u = new User();
        u.hostGame = g;
        int result = u.doubleDown(20);

        assertEquals(40, result);
    }

    @Test
    public void testSplit(){
        Game g = new Game();
        User u = new User();
        u.hostGame = g;
        java.util.List<Card> dealtCol = new ArrayList<Card>();
        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(8, Suit.Diamonds, true);
        dealtCol.add(card1);
        dealtCol.add(card2);
        u.split(card1, card2, dealtCol);

        assertEquals(2, dealtCol.size());

    }


}

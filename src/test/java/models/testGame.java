package models;

import org.junit.Test;

import java.util.ArrayList;

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

    @Test
    public void testHit(){
        Game g = new Game();
        User u = new User();
        u.hostGame = g;
        java.util.List<Card> dealtCol= new ArrayList<Card>();
        u.hit(20, dealtCol);

        assertEquals(1, dealtCol.size());
    }

    @Test
    public void testStay(){
        Game g = new Game();
        User u = new User();
        u.hostGame = g;
        String result = u.stay(21);

        assertEquals("false", result);
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

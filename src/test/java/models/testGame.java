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

    @Test
    public void testInitialDeal() {
        Game g = new Game();
        User u = new User();
        u.initialDeal();

        assertEquals(2, g.cols.get(0).size());
    }

    @Test //test if the col size increase after hitting
    public void testColSizeAfterHit(){
        Game g = new Game();
        User u = new User();

        g.cols.add(new ArrayList<Card>());
        u.hit(0);

        assertEquals(1, g.cols.get(0).size());
    }

    @Test //test if user can hit when isStay is true
    public void testisStayForHit() {
        Game g = new Game();
        User u = new User();

        g.cols.add(new ArrayList<Card>());
        u.zeroStayed = true;
        u.hit(0);

        //assertEquals(false, u.isBusted);
    }

    @Test //test if user can hit after busted
    public void testisStayAfterBusted() {
        Game g = new Game();
        User u = new User();

        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        Card card3 = new Card(10, Suit.Hearts, true);
        g.cols.add(new ArrayList<Card>());
        u.dealCardToCol(0, card1);
        u.dealCardToCol(0, card2);
        u.dealCardToCol(0, card3);
        //g.colScore(0);
        u.hit(0);

        //assertEquals(true, u.isBusted);
    }

    @Test
    public void testHitFor3rdCol() {
        Game g = new Game();
        User u = new User();

        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        g.cols.add(new ArrayList<Card>());
        u.dealCardToCol(0, card1);
        u.dealCardToCol(0, card2);

        u.hit(3);

        assertEquals(0, g.cols.get(0).size());
    }

    @Test
    public void testStay(){
        Game g = new Game();
        User u = new User();

        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        g.cols.add(new ArrayList<Card>());
        u.dealCardToCol(0,card1);
        u.dealCardToCol(0,card2);
        u.stay(0);

        assertEquals(true, u.zeroStayed);
    }

    @Test
    public void testNotStay(){
        Game g = new Game();
        User u = new User();

        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        g.cols.add(new ArrayList<Card>());
        u.dealCardToCol(0,card1);
        u.dealCardToCol(0,card2);
        u.stay(3);

        assertEquals(false, u.zeroStayed);
    }

    @Test
    public void testStayForBusted(){
        Game g = new Game();
        User u = new User();

        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        Card card3 = new Card(10, Suit.Hearts, true);
        g.cols.add(new ArrayList<Card>());
        u.dealCardToCol(0,card1);
        u.dealCardToCol(0,card2);
        u.dealCardToCol(0,card3);
        //g.colScore(0);
        u.stay(0);

        //assertEquals(true, u.isBusted);
    }

    @Test
    public void testStayFor0ColBusted(){
        Game g = new Game();
        User u = new User();

        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        g.cols.add(new ArrayList<Card>());
        u.dealCardToCol(0,card1);
        u.dealCardToCol(0,card2);
        //g.colScore(0);
        u.stay(0);

        assertEquals(true, u.zeroStayed);
    }

    @Test
    public void testStayFor1ColBusted(){
        Game g = new Game();
        User u = new User();

        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        g.cols.add(new ArrayList<Card>());
        u.dealCardToCol(1,card1);
        u.dealCardToCol(1,card2);
        //g.colScore(0);
        u.stay(1);

        assertEquals(true, u.oneStayed);
    }

    @Test
    public void testDoubleDown(){
        Game g = new Game();
        User u = new User();

        //int result = u.doubleDown(20);

        //assertEquals(40, result);
    }

    @Test
    public void testSplit(){
        Game g = new Game();
        User u = new User();

        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(8, Suit.Diamonds, true);
        u.dealCardToCol(0, card1);
        u.dealCardToCol(0, card2);
        u.split();

        assertEquals(true, u.isSplit);

    }

    @Test
    public void testNotSplit() {
        Game g = new Game();
        User u = new User();

        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        u.dealCardToCol(0, card1);
        u.dealCardToCol(0, card2);
        u.split();

        assertEquals(false, u.isSplit);
    }

    @Test
    public void testSplitForOneCard(){
        Game g = new Game();
        User u = new User();

        Card card1 = new Card(8, Suit.Clubs, true);
        u.dealCardToCol(0, card1);
        u.split();

        assertEquals(false, u.isSplit);
    }

    @Test
    public void testSplitWithOneCardIn2ndCol(){
        Game g = new Game();
        User u = new User();

        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(9, Suit.Clubs, true);
        Card card3 = new Card(7, Suit.Clubs, true);
        u.dealCardToCol(0, card1);
        u.dealCardToCol(0, card2);
        u.dealCardToCol(1, card3);
         u.split();

        assertEquals(false, u.isSplit);
    }




}

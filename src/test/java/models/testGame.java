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
        assertEquals(48,g.deck.size());

    }

    @Test
    public void testInitialDeal() {
        Game g = new Game();
        assertEquals(2, g.theUser.cols.get(0).size());
    }

    @Test //test if the col size increase after hitting
    public void testColSizeAfterHit(){
        Game g = new Game();
        g.theUser.hit(0);
        assertEquals(3, g.theUser.cols.get(0).size());
    }

    @Test //test if user can hit when isStay is true
    public void testisStayForHit() {
        Game g = new Game();
        g.cols.add(new ArrayList<Card>());
        g.theUser.zeroStayed = true;
        g.theUser.hit(0);

        assertEquals(2, g.theUser.cols.get(0).size());
    }

    @Test //test if user can hit after busted
    public void testisStayAfterBusted() {
        Game g = new Game();

        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        Card card3 = new Card(10, Suit.Hearts, true);
        g.cols.add(new ArrayList<Card>());
        g.theUser.dealCardToCol(0, card1);
        g.theUser.dealCardToCol(0, card2);
        g.theUser.dealCardToCol(0, card3);
        assertEquals(true, g.theUser.isBusted(0));
    }

    @Test
    public void testHitFor2ndCol() {
        Game g = new Game();
        g.theUser.hit(1);

        assertEquals(0, g.theUser.cols.get(1).size());
    }

    @Test
    public void testStay(){
        Game g = new Game();

        g.theUser.stay(0);

        assertEquals(true, g.theUser.zeroStayed);
    }

    @Test
    public void testNotStay(){
        Game g = new Game();

        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        g.cols.add(new ArrayList<Card>());
        g.theUser.dealCardToCol(0,card1);
        g.theUser.dealCardToCol(0,card2);
        g.theUser.stay(3);

        assertEquals(false, g.theUser.zeroStayed);
    }

    @Test
    public void testStayForBusted(){
        Game g = new Game();

        g.theUser.cols.get(0).clear();
        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        Card card3 = new Card(10, Suit.Hearts, true);
        g.cols.add(new ArrayList<Card>());
        g.theUser.dealCardToCol(0,card1);
        g.theUser.dealCardToCol(0,card2);
        g.theUser.dealCardToCol(0,card3);
        assertEquals(30,g.theUser.colScore(0));
        g.theUser.stay(0);

        assertEquals(true, g.theUser.isBusted(0));
    }

    @Test
    public void testStayFor0ColBusted(){
        Game g = new Game();
        g.theUser.stay(0);

        assertEquals(true, g.theUser.zeroStayed);
    }

    @Test
    public void testStayFor1ColBusted(){
        Game g = new Game();

        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        g.cols.add(new ArrayList<Card>());
        g.theUser.dealCardToCol(1,card1);
        g.theUser.dealCardToCol(1,card2);
        //g.colScore(0);
        g.theUser.stay(1);

        assertEquals(true, g.theUser.oneStayed);
    }

    @Test
    public void testDoubleDown(){
        Game g = new Game();
        g.theUser.doubleDown(0);
        assertEquals(3,g.theUser.cols.get(0).size());
        assertEquals(true,g.theUser.doublezero);
    }

    @Test
    public void testSplit(){
        Game g = new Game();

        g.theUser.cols.get(0).clear();
        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(8, Suit.Diamonds, true);
        g.theUser.dealCardToCol(0, card1);
        g.theUser.dealCardToCol(0, card2);
        g.theUser.split();

        assertEquals(true, g.theUser.isSplit);

    }

    @Test
    public void testNotSplit() {
        Game g = new Game();
        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        g.theUser.dealCardToCol(0, card1);
        g.theUser.dealCardToCol(0, card2);
        g.theUser.split();

        assertEquals(false, g.theUser.isSplit);
    }

    @Test
    public void testSplitForOneCard(){
        Game g = new Game();

        Card card1 = new Card(8, Suit.Clubs, true);
        g.theUser.dealCardToCol(0, card1);
        g.theUser.split();

        assertEquals(false, g.theUser.isSplit);
    }

    @Test
    public void testSplitWithOneCardIn2ndCol(){
        Game g = new Game();

        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(9, Suit.Clubs, true);
        Card card3 = new Card(7, Suit.Clubs, true);
        g.theUser.dealCardToCol(0, card1);
        g.theUser.dealCardToCol(0, card2);
        g.theUser.dealCardToCol(1, card3);
        g.theUser.split();

        assertEquals(false, g.theUser.isSplit);
    }


    @Test
    public void testNewGame(){
        Game g = new Game();

        g.theUser.cols.get(0).clear();
        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        Card card3 = new Card(10, Suit.Hearts, true);
        g.cols.add(new ArrayList<Card>());
        g.theUser.dealCardToCol(0,card1);
        g.theUser.dealCardToCol(0,card2);
        g.theUser.dealCardToCol(0,card3);
        assertEquals(30,g.theUser.colScore(0));
        assertEquals(true, g.theUser.isBusted(0));
        g.newGame();

        assertEquals(2,g.theUser.cols.get(0).size());
        assertEquals(false,g.theUser.isBusted(0));

    }

    @Test
    public void testReload(){
        Game g = new Game();

        g.theUser.cols.get(0).clear();
        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(1, Suit.Diamonds, true);
        Card card3 = new Card(12, Suit.Hearts, true);
        g.cols.add(new ArrayList<Card>());
        g.theUser.dealCardToCol(0,card1);
        g.theUser.dealCardToCol(0,card2);
        g.theUser.dealCardToCol(0,card3);
        g.reload();
        assertEquals(card1,g.cols.get(0).get(0));
        assertEquals(card2,g.cols.get(0).get(1));
        assertEquals(card3,g.cols.get(0).get(2));

    }

    @Test
    public void testJudge(){
        Game g = new Game();

        g.theUser.cols.get(0).clear();
        g.theDealer.cols.get(0).clear();
        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        Card card3 = new Card(2, Suit.Hearts, true);
        Card card4 = new Card(1, Suit.Spades, true);
        Card card5 = new Card(1, Suit.Diamonds, true);
        g.cols.add(new ArrayList<Card>());
        g.theUser.dealCardToCol(0,card1);
        g.theUser.dealCardToCol(1,card2);
        g.theDealer.dealCardToCol(0,card1);
        g.theDealer.dealCardToCol(0,card3);
        g.theUser.isSplit=true;
        g.theUser.doubleone=true;
        g.judge();
        assertEquals(100,g.totalCash);
        g.theUser.oneStayed=true;
        g.theUser.zeroStayed=true;
        g.theDealer.done=true;
        g.judge();
        assertEquals(12,g.theDealer.colScore(0));
        assertEquals(10,g.theUser.colScore(0));
        assertEquals(10,g.theUser.colScore(1));
        assertEquals(94,g.totalCash);
        assertEquals("LOSE",g.user1State);
        assertEquals("LOSE",g.user2State);

        g.theUser.dealCardToCol(0,card4);
        g.theUser.dealCardToCol(1,card5);
        g.theUser.doubleone=false;
        g.theUser.doublezero=true;

        g.judge();

        assertEquals(21,g.theUser.colScore(0));
        assertEquals(21,g.theUser.colScore(1));
        assertEquals(100,g.totalCash);
        assertEquals("WIN",g.user1State);
        assertEquals("WIN",g.user2State);

        g.theUser.dealCardToCol(0,card1);
        g.theUser.dealCardToCol(1,card2);
        g.theUser.dealCardToCol(0,card1);
        g.theUser.dealCardToCol(1,card2);
        g.theUser.doubleone=false;
        g.theUser.doublezero=true;

        g.judge();

        assertEquals(94,g.totalCash);
        assertEquals("LOSE",g.user1State);
        assertEquals("WIN",g.user2State);

        g.theUser.cols.get(1).clear();
        g.judge();
        assertEquals(" ",g.user2State);


    }



}

package models;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rynn on 3/13/2016.
 */
public class testUser {

    @Test
    public void testUser(){
        Game g = new Game();
        User u = new User();
    }

     @Test
     public void testHit(){
         Game g = new Game();
         g.theUser.hit(0);

         assertEquals(3, g.cols.get(0).size());
     }

    @Test
    public void testHitFor3rdCol(){
        Game g = new Game();
        String result = "";
        result = g.theUser.hit(2);

        assertEquals("Invalid column", result);
    }

    @Test
    public void testHitForEmptySplitCol(){
        Game g = new Game();
        String result = "";
        g.theUser.isSplit = false;
        result = g.theUser.hit(1);

        assertEquals("Invalid column", result);
    }

    @Test
    public void testHitForBusted() {
        Game g = new Game();

        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        Card card3 = new Card(10, Suit.Hearts, true);

        g.theUser.dealCardToCol(0, card1);
        g.theUser.dealCardToCol(0, card2);
        g.theUser.dealCardToCol(0, card3);

        g.theUser.colScore(0);
        String result = " ";
        result = g.theUser.hit(0);

        assertEquals("You hand has already busted!", result);
    }

    @Test
    public void testHitForStayed() {
        Game g = new Game();
        g.theUser.zeroStayed = true;
        String result = " ";
        result = g.theUser.hit(0);

        assertEquals("Unable to hit: You've already chose to stay.", result);
    }

    @Test
    public void testStayForCol0() {
        Game g  = new Game();
        g.theUser.cols.get(0).clear();
        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        g.theUser.dealCardToCol(0, card1);
        g.theUser.dealCardToCol(0, card2);


        g.theUser.stay(0);

        assertEquals(true, g.theUser.zeroStayed);
    }

    @Test
    public void testStayForCol1() {
        Game g  = new Game();
        g.theUser.cols.get(0).clear();
        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        g.theUser.dealCardToCol(1, card1);
        g.theUser.dealCardToCol(1, card2);


        g.theUser.stay(1);

        assertEquals(true, g.theUser.oneStayed);
    }

    @Test
    public void testStayFor3rdCol() {
        Game g = new Game();
        String result = "";
        result = g.theUser.stay(2);

        assertEquals("Unable to identify col", result);
    }

    @Test
    public void testStayForBusted(){
        Game g = new Game();

        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);
        Card card3 = new Card(10, Suit.Hearts, true);

        g.theUser.dealCardToCol(0, card1);
        g.theUser.dealCardToCol(0, card2);
        g.theUser.dealCardToCol(0, card3);

        g.theUser.colScore(0);
        String result = " ";
        result = g.theUser.stay(0);

        assertEquals("Your hand has already busted.", result);
    }

    @Test
    public void testDoubleDownCol0() {
        Game g = new Game();
        g.theUser.zeroStayed = false;
        g.theUser.doubleDown(0);

        assertEquals(true, g.theUser.doublezero);
    }

    @Test
    public void testDoubleDownCol1() {
        Game g = new Game();

        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);

        g.theUser.dealCardToCol(1, card1);
        g.theUser.dealCardToCol(1, card2);
        g.theUser.isSplit = true;
        g.theUser.doubleDown(1);

        assertEquals(true, g.theUser.doubleone);
    }

    @Test
    public void testDDafterStayedCol0() {
        Game g = new Game();

        g.theUser.zeroStayed = true;
        String result = "";
        result = g.theUser.doubleDown(0);

        assertEquals("Cannot Double Down", result);
    }

    @Test
    public void testDDafterStayedCol1() {
        Game g = new Game();

        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);

        g.theUser.dealCardToCol(1, card1);
        g.theUser.dealCardToCol(1, card2);

        g.theUser.oneStayed = true;
        g.theUser.isSplit = true;
        String result = "";
        result = g.theUser.doubleDown(1);

        assertEquals("Cannot Double Down", result);
    }

    @Test
    public void testDDfor3rdCol() {
        Game g = new Game();
        String result = "";
        result = g.theUser.doubleDown(3);

        assertEquals("Invalid Column", result);
    }

    @Test
    public void testSplitMoreThanTwoCardsCol0(){
        Game g = new Game();
        Card card1 = new Card(10, Suit.Clubs, true);
        g.theUser.dealCardToCol(0, card1);
        g.theUser.split();

        assertEquals(0, g.cols.get(1).size());
    }

    @Test //if there is already card in Col 1
    public void testSplitExistedCardCol1(){
        Game g = new Game();
        Card card1 = new Card(10, Suit.Clubs, true);
        g.theUser.dealCardToCol(1, card1);

        g.theUser.split();

        assertEquals(1, g.cols.get(1).size());
    }

    @Test
    public void testSplit(){
        Game g = new Game();
        g.theUser.cols.get(0).clear();
        Card card1 = new Card(10, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);

        g.theUser.dealCardToCol(0, card1);
        g.theUser.dealCardToCol(0, card2);

        g.theUser.split();

        assertEquals(true, g.theUser.isSplit);
    }

    @Test
    public void testNotSplit(){
        Game g = new Game();
        g.theUser.cols.get(0).clear();
        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);

        g.theUser.dealCardToCol(0, card1);
        g.theUser.dealCardToCol(0, card2);

        g.theUser.split();

        assertEquals(false, g.theUser.isSplit);
    }

    @Test
    public void testLosesCol0() {
        Game g = new Game();
        g.theUser.cols.get(0).clear();
        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);

        g.theUser.dealCardToCol(0, card1);
        g.theUser.dealCardToCol(0, card2);

        assertEquals(false, g.theUser.loses());
    }

    @Test
    public void testLosesCol1() {
        Game g = new Game();
        g.theUser.cols.get(1).clear();
        Card card1 = new Card(8, Suit.Clubs, true);
        Card card2 = new Card(10, Suit.Diamonds, true);

        g.theUser.dealCardToCol(1, card1);
        g.theUser.dealCardToCol(1, card2);

        assertEquals(false, g.theUser.loses());
    }

}

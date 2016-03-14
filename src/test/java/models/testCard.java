package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michaelhilton on 1/26/16.
 */
public class testCard {
   @Test
    public void testGetSuit(){
        Card c = new Card(5,Suit.Clubs, true);
        assertEquals(Suit.Clubs,c.getSuit());
    }

    @Test
    public void testToString(){
        Card c = new Card(5,Suit.Clubs, true);
        assertEquals("5Clubs",c.toString());
    }
    @Test
    public void testGetValue(){
        Card c = new Card(5,Suit.Clubs, true);
        assertEquals(5,c.getValue());
    }



}

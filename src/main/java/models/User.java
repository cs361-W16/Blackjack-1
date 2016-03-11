package models;

import java.util.ArrayList;

/**
 * Created by Braxton on 3/8/2016.
 */
public class User extends Player {


    public void initialDeal(){
        Card card1=hostGame.drawCard();
        Card card2=hostGame.drawCard();
        java.util.List<Card> dealtCol= new ArrayList<Card>();
        dealtCol.add(card1);
        dealtCol.add(card2);
        hostGame.cols.add(dealtCol);
        hostGame.ownership.add(name);
    }

    public User(){
        name="User";
    }

    public void hit(){

    }

    public void stay(){

    }

    public void doubleDown(){

    }

    public void split(){

    }
    public int userBet(){
        User x = new User();
        x.hostGame.playerWin = x.hostGame.isPlayerWin();
            if(x.hostGame.playerWin = true){
                x.hostGame.totalCash += x.hostGame.pot;
                return x.hostGame.totalCash;
            }
            else if(x.hostGame.playerWin = false){
                x.hostGame.totalCash -= x.hostGame.pot;
                return x.hostGame.totalCash;
            }
        return x.hostGame.totalCash;
    }
    public int updatePot() {
        User u = new User();
        u.hostGame.pot += (u.hostGame.bet * 2);
        return u.hostGame.pot;
    }
}

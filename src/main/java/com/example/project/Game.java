package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){ //returns which player wins or if there is a tie
        int p1HandValue = Utility.getHandRanking(p1Hand); //declares and initializes int to the hand ranking of each player's hand
        int p2HandValue = Utility.getHandRanking(p2Hand);
        if (p1HandValue > p2HandValue) { //if player 1's hand value is greater than player 2's hand value, return player 1 wins
            return "Player 1 wins!";
        } else if (p2HandValue > p1HandValue) { //if player 2's hand value is greater than player 1's hand value, return player 2 wins
            return "Player 2 wins!";
        } else { //if both players have the same hand, there is a tiebreaker for whoever has the higher card
            int p1Max = 0;
            int p2Max = 0;
            if (Utility.getRankValue(p1.getHand().get(0).getRank()) > Utility.getRankValue(p1.getHand().get(1).getRank())) {
                p1Max = Utility.getRankValue(p1.getHand().get(0).getRank());
            } else {
                p1Max = Utility.getRankValue(p1.getHand().get(1).getRank());
            }
            if (Utility.getRankValue(p2.getHand().get(0).getRank()) > Utility.getRankValue(p2.getHand().get(1).getRank())) {
                p2Max = Utility.getRankValue(p2.getHand().get(0).getRank());
            } else {
                p2Max = Utility.getRankValue(p2.getHand().get(1).getRank());
            }
            if (p1Max > p2Max) {
                return "Player 1 wins!";
            } else if (p2Max > p1Max) {
                return "Player 2 wins!";
            } else {
                return "Tie!";
            }
        }
    }


    public static void play(){ //simulate card playing
    
    }
        
        

}
package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        int winner = 0;
        int p1HandValue = Utility.getHandRanking(p1Hand);
        int p2HandValue = Utility.getHandRanking(p2Hand);
        if (p1HandValue > p2HandValue) {
            return "Player 1 wins!";
        } else if (p2HandValue > p1HandValue) {
            return "Player 2 wins!";
        } else {
            if (p1Hand.equals("A Pair")) {
                winner = highestPair(p1, p2);
                if ()
            }
            if (p1Hand.equals("Two Pair")) {

            }





            // if (Utility.getRankValue(p1.getHand().get(0).getRank()) > Utility.getRankValue(p1.getHand().get(1).getRank())) {
            //     p1Max = Utility.getRankValue(p1.getHand().get(0).getRank());
            // } else {
            //     p1Max = Utility.getRankValue(p1.getHand().get(1).getRank());
            // }
            // if (Utility.getRankValue(p2.getHand().get(0).getRank()) > Utility.getRankValue(p2.getHand().get(1).getRank())) {
            //     p2Max = Utility.getRankValue(p2.getHand().get(0).getRank());
            // } else {
            //     p2Max = Utility.getRankValue(p2.getHand().get(1).getRank());
            // }
            // if (p1Max > p2Max) {
            //     return "Player 1 wins!";
            // } else if (p2Max > p1Max) {
            //     return "Player 2 wins!";
            // } else {
            //     return "Tie";
            // }
        }
    }

    public int highestPair(Player p1, Player p2) {
        int p1Max = 0;
        int p2Max = 0;
        ArrayList<Integer> p1Frequency = p1.findRankingFrequency();
        ArrayList<Integer> p2Frequency = p2.findRankingFrequency();
        for (int i = 0; i < p1Frequency.size(); i++) {
            if (p1Frequency.get(i) == 2) {
                p1Max = i;
                break;
            }
        }
        for (int j = 0; j < p2Frequency.size(); j++) {
            if (p2Frequency.get(j) == 2) {
                p2Max = j;
                break;
            }
        }
        if (p1Max > p2Max) {
            return 1;
        }else if (p2Max > p1Max) {
            return 2;
        } else {
            return 0;
        }

    }

    public static void play(){ //simulate card playing
    
    }
        
        

}
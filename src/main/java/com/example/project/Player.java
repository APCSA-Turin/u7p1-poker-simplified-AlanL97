package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){
        allCards = new ArrayList<Card>(communityCards);
        allCards.add(hand.get(0));
        allCards.add(hand.get(1));
        sortAllCards();
        if (isRoyalFlush()) {
            return "Royal Flush";
        } else if (isStraightFlush()) {
            return "Straight Flush";
        } else if (isFourOfAKind()) {
            return "Four of a Kind";
        } else if (isFullHouse()) {
            return "Full House";
        } else if (isFlush()) {
            return "Flush";
        } else if (isStraight()) {
            return "Straight";
        } else if (isThreeOfAKind()) {
            return "Three of a Kind";
        } else if (isTwoPair()) {
            return "Two Pair";
        } else if (isPair()) {
            return "A Pair";
        } else if (isHighCard(communityCards)) {
            return "High Card";
        } else {
            return "Nothing";
        }
    }

    public boolean isHighCard(ArrayList<Card> communityCards) {
        int max = Utility.getRankValue(communityCards.get(0).getRank());
        for (int i = 1; i < communityCards.size(); i++) {
            if (Utility.getRankValue(communityCards.get(i).getRank()) > max) {
                max = Utility.getRankValue(communityCards.get(i).getRank());
            }
        }
        if (Utility.getRankValue(hand.get(0).getRank()) > max || Utility.getRankValue(hand.get(1).getRank()) > max) {
            return true;
        }
        return false;
    }

    public boolean isPair() {
        ArrayList<Integer> frequency = findRankingFrequency();
        for (int i = 0; i < frequency.size(); i++) {
            if (frequency.get(i) == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isTwoPair() {
        int count = 0;
        ArrayList<Integer> frequency = findRankingFrequency();
        for (int i = 0; i < frequency.size(); i++) {
            if (frequency.get(i) == 2) {
                count++;
            }
        }
        if (count == 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isThreeOfAKind() {
        ArrayList<Integer> frequency = findRankingFrequency();
        for (int i = 0; i < frequency.size(); i++) {
            if (frequency.get(i) == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean isStraight() {
        int count = 1;
        ArrayList<Integer> frequency = findRankingFrequency();
        for (int i = 0; i < frequency.size(); i++) {
            if (i != frequency.size()-1 && frequency.get(i) == 1 && frequency.get(i+1) == 1) {
                count++;
            }
            if (count == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean isFlush() {
        ArrayList<Integer> frequency = findSuitFrequency();
        for (int i = 0; i < frequency.size(); i++) {
            if (frequency.get(i) == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean isFullHouse() {
        if (isPair() && isThreeOfAKind()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFourOfAKind() {
        ArrayList<Integer> frequency = findRankingFrequency();
        for (int i = 0; i < frequency.size(); i++) {
            if (frequency.get(i) == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean isStraightFlush() {
        if (isStraight() && isFlush()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRoyalFlush() {
        if (isStraightFlush() && allCards.get(0).getRank().equals("10")) {
            return true;
        } else {
            return false;
        }
    }

    public void sortAllCards() {
        Card temp;
        for (int i = 1; i < allCards.size(); i++) {
            int j = i-1;
            while (i != 0 && Utility.getRankValue(allCards.get(i).getRank()) < Utility.getRankValue(allCards.get(j).getRank())) {
                temp = allCards.set(j, allCards.get(i));
                allCards.set(i, temp);
                i = j;
                j = i-1;
            }
        }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> frequency = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        for (int i = 0; i < allCards.size(); i++) {
            int idx = Utility.getRankValue(allCards.get(i).getRank())-2;
            frequency.set(idx, frequency.get(idx)+1);
        }
        return frequency;
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> frequency = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0));
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i).getSuit().equals("♦")) {
                frequency.set(0, frequency.get(0)+1);
            }
            if (allCards.get(i).getSuit().equals("♣")) {
                frequency.set(1, frequency.get(1)+1);
            }
            if (allCards.get(i).getSuit().equals("♥")) {
                frequency.set(2, frequency.get(2)+1);
            }
            if (allCards.get(i).getSuit().equals("♠")) {
                frequency.set(3, frequency.get(3)+1);
            }
        }
        return frequency;
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}

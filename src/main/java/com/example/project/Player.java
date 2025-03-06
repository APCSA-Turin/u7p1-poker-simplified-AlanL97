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

    public void addCard(Card c){ //adds card to hand
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){ //returns player's best hand
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

    public boolean isHighCard(ArrayList<Card> communityCards) { //returns true if player has a high card
        int max = Utility.getRankValue(communityCards.get(0).getRank());
        for (int i = 1; i < communityCards.size(); i++) { //iterates through communityCards to find the highest card in communityCards
            if (Utility.getRankValue(communityCards.get(i).getRank()) > max) {
                max = Utility.getRankValue(communityCards.get(i).getRank());
            }
        }
        if (Utility.getRankValue(hand.get(0).getRank()) > max || Utility.getRankValue(hand.get(1).getRank()) > max) { //if either card in player's hand is greater than the highest card in the communityCards, return true
            return true;
        }
        return false;
    }

    public boolean isPair() { //returns true if player has a pair
        ArrayList<Integer> frequency = findRankingFrequency();
        for (int i = 0; i < frequency.size(); i++) { //iterates through frequency and returns true if there is a 2 in frequency because that means there's a pair in it
            if (frequency.get(i) == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isTwoPair() { //returns true is player has a two pair
        int count = 0; //declares and initializes count which is the number of pairs in allCards
        ArrayList<Integer> frequency = findRankingFrequency();
        for (int i = 0; i < frequency.size(); i++) { //iterates through frequency and increments count if there is a 2 in frequency
            if (frequency.get(i) == 2) {
                count++;
            }
        }
        if (count == 2) { //if count is equal to 2, returns true because that means there are two pairs
            return true;
        } else {
            return false;
        }
    }

    public boolean isThreeOfAKind() { //returns true if player has a three of a kind
        ArrayList<Integer> frequency = findRankingFrequency();
        for (int i = 0; i < frequency.size(); i++) { //iterates through frequency and returns true if there is a 3 in frequency
            if (frequency.get(i) == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean isStraight() { //returns true if player has a straight
        int count = 1; //declares and initializes count which is the number of consecutive ones in frequency
        ArrayList<Integer> frequency = findRankingFrequency();
        for (int i = 0; i < frequency.size(); i++) {
            if (i != frequency.size()-1 && frequency.get(i) == 1 && frequency.get(i+1) == 1) { //if frequency at index i and at i+1 equals 1, increment count
                count++;
            }
            if (count == 5) { //if count is equal to 5, return true because this means that there is a straight
                return true;
            }
        }
        return false;
    }

    public boolean isFlush() { //returns true if player has a flush
        ArrayList<Integer> frequency = findSuitFrequency();
        for (int i = 0; i < frequency.size(); i++) { //iterates through frequency and returns true if frequency has a 5 because that means that all cards have the same suit
            if (frequency.get(i) == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean isFullHouse() { //returns true if player has a full house
        if (isPair() && isThreeOfAKind()) { //returns true if player has a pair and a three of a kind becaus ethat means they have a full house. calls isPair() and isThreeOfAKind()
            return true;
        } else {
            return false;
        }
    }

    public boolean isFourOfAKind() { //returns true if player had a four of a kind
        ArrayList<Integer> frequency = findRankingFrequency();
        for (int i = 0; i < frequency.size(); i++) { //iterates through frequency and returns true if frequency has a 4
            if (frequency.get(i) == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean isStraightFlush() { //returns true if pkayer has a straight flush
        if (isStraight() && isFlush()) { //returns true if player has both a straight and a flush. calls isStraight() and isFlush()
            return true;
        } else {
            return false;
        }
    }

    public boolean isRoyalFlush() { //returns true if player has a royal flush
        if (isStraightFlush() && allCards.get(0).getRank().equals("10")) { //returns true if player has a straight flush and if the first card in allCards has rank 10 because all cards following it will be the cards in a royal flush
            return true;
        } else {
            return false;
        }
    }

    public void sortAllCards() { //sorts allCards using insertion sort
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

    public ArrayList<Integer> findRankingFrequency() { //returns an ArrayList with the rank frequency of allCards
        ArrayList<Integer> frequency = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)); //declares and initializes a new ArrayList with 13 elements set to 0
        for (int i = 0; i < allCards.size(); i++) { //iterates through allCards and increments the element at the corresponding index of that rank
            int idx = Utility.getRankValue(allCards.get(i).getRank())-2;
            frequency.set(idx, frequency.get(idx)+1);
        }
        return frequency;
    }

    public ArrayList<Integer> findSuitFrequency() { //returns an ArrayList with the suit frequency of allCards
        ArrayList<Integer> frequency = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0)); //decalres and initializes a new ArrayList with 4 elements set to 0
        for (int i = 0; i < allCards.size(); i++) { //iterates through allCards
            if (allCards.get(i).getSuit().equals("♦")) { //if suit is diamond, increments element at index 0
                frequency.set(0, frequency.get(0)+1);
            }
            if (allCards.get(i).getSuit().equals("♣")) { //if suit is club, increments element at index 1
                frequency.set(1, frequency.get(1)+1);
            }
            if (allCards.get(i).getSuit().equals("♥")) { //if suit is hearts, increments element at index 2
                frequency.set(2, frequency.get(2)+1);
            }
            if (allCards.get(i).getSuit().equals("♠")) { //if suit is spades, increments element at index 3
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

import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Hand{
    SortedSet<Card> cards = new TreeSet<>();

    public Card playerHasCard(Trick trick, int round) {
        Scanner scanner = new Scanner(System.in);
        boolean bool = false;
        boolean handHasSuit = false;
        Suits cardSuit = null;
        Ranks cardRank = null;
        Card cardToPlay;

        if(trick.lead == null) {
            for (Card card1 : cards) {
                if (round == 1 && card1.getSuit() == Suits.Clubs && card1.getRank() == Ranks.Deuce) {
                    System.out.println("You played 2 of clubs");
                    cardToPlay = new Card(card1.getSuit(), card1.getRank());
                    return cardToPlay;
                }
            }
        }

        do {
            do {
                if(trick.lead != null){
                    System.out.println("Type card rank that has " + trick.lead + " suit to play it (Deuce/Three/Four/Five/Six/Seven/Eight/Nine/Ten/Jack/Queen/King/Ace)");
                }
                else {
                    System.out.println("Type card rank to play it (Deuce/Three/Four/Five/Six/Seven/Eight/Nine/Ten/Jack/Queen/King/Ace)");
                }

                String rankString = scanner.nextLine();

                switch (rankString) {
                    case "Deuce":
                        bool = true;
                        cardRank = Ranks.Deuce;
                        break;
                    case "Three":
                        bool = true;
                        cardRank = Ranks.Three;
                        break;
                    case "Four":
                        bool = true;
                        cardRank = Ranks.Four;
                        break;
                    case "Five":
                        bool = true;
                        cardRank = Ranks.Five;
                        break;
                    case "Six":
                        bool = true;
                        cardRank = Ranks.Six;
                        break;
                    case "Seven":
                        bool = true;
                        cardRank = Ranks.Seven;
                        break;
                    case "Eight":
                        bool = true;
                        cardRank = Ranks.Eight;
                        break;
                    case "Nine":
                        bool = true;
                        cardRank = Ranks.Nine;
                        break;
                    case "Ten":
                        bool = true;
                        cardRank = Ranks.Ten;
                        break;
                    case "Jack":
                        bool = true;
                        cardRank = Ranks.Jack;
                        break;
                    case "Queen":
                        bool = true;
                        cardRank = Ranks.Queen;
                        break;
                    case "King":
                        bool = true;
                        cardRank = Ranks.King;
                        break;
                    case "Ace":
                        bool = true;
                        cardRank = Ranks.Ace;
                        break;
                    default:
                        bool = false;
                }

                if (bool == false) {
                    System.out.println("Card rank was incorrect try again");
                    continue;
                }

                for (Card card:cards) {
                    if(card.getSuit() == trick.lead && card.getRank() == cardRank){
                        bool = true;
                        break;
                    }
                    else{
                        bool = false;
                    }
                }

                if (bool == false) {
                    System.out.println("There is no such card with lead suit (" + trick.lead +") and given rank in your hand try again");
                }
            }
            while (bool != true);

            bool = false;

            do {
                if(trick.lead != null){
                    System.out.println("Type card " + trick.lead + " suit to play it (Clubs/Diamonds/Hearts/Spades)");
                }
                else {
                    System.out.println("Type card suit to play it (Clubs/Diamonds/Hearts/Spades)");
                }

                String suitString = scanner.nextLine();

                switch (suitString) {
                    case "Clubs":
                        bool = true;
                        cardSuit = Suits.Clubs;
                        break;
                    case "Hearts":
                        bool = true;
                        cardSuit = Suits.Hearts;
                        break;
                    case "Diamonds":
                        bool = true;
                        cardSuit = Suits.Diamonds;
                        break;
                    case "Spades":
                        bool = true;
                        cardSuit = Suits.Spades;
                        break;
                    default:
                        bool = false;
                }

                if (bool == false) {
                    System.out.println("Card suit was incorrect try again");
                    continue;
                }

                if(round == 1 && suitString.equals("Hearts")){
                    System.out.println("You can't play hearts card on the first round choose different suit");
                    bool = false;
                    continue;
                }
                for (Card card:cards) {
                    if (suitString.equals("Hearts") && trick.brokenHearts == false && trick.lead == card.getSuit()) {
                        System.out.println("You can't play hearts card if hearts were not broken try again");
                        bool = false;
                        break;
                    }
                    if (suitString.equals("Hearts") && trick.brokenHearts == false && trick.lead == null) {
                        System.out.println("You can't play hearts card if hearts were not broken try again");
                        bool = false;
                        break;
                    }
                }
                if(bool == false){
                    continue;
                }
                for (Card card:cards) {
                    if(trick.lead == card.getSuit()){
                        handHasSuit = true;
                        break;
                    }
                }
                if(handHasSuit && trick.lead != null && trick.lead != cardSuit){
                    System.out.println("Card suit wasn't lead suit try again");
                    bool = false;
                }




            }
            while (bool != true);

            cardToPlay = new Card(cardSuit, cardRank);
            for (Card card : cards) {
                if (card.getSuit() == cardToPlay.getSuit() && card.getRank() == cardToPlay.getRank()) {
                    if(cardToPlay.getSuit() == Suits.Hearts){
                        trick.brokenHearts = true;
                    }
                    bool = true;
                    break;
                } else {
                    bool = false;
                }
            }
            if (bool == false) {
                System.out.println("There is no such card in your hand try again");
            }
        }
        while (bool != true);
        return cardToPlay;
    }

    public Card botPlayCard(Trick trick, int round) {
        if(trick.lead == null && round == 1) {
            for (Card card1 : cards) {
                if (card1.getSuit() == Suits.Clubs && card1.getRank() == Ranks.Deuce) {
                    Card card = new Card(card1.getSuit(), card1.getRank());
                    trick.cards.add(card);
                    cards.remove(card);
                    return card;
                }
            }
        }

        if(trick.lead != null) {
            for (Card card : cards) {
                if(card.getSuit() == trick.lead){
                    trick.cards.add(card);
                    cards.remove(card);
                    return card;
                }
            }
        }
        if(trick.lead == null){
            for (Card card : cards) {
                if(card.getSuit() == Suits.Hearts && trick.brokenHearts == false){

                }
                else if(card.getSuit() == Suits.Hearts && trick.brokenHearts != false){
                    trick.cards.add(card);
                    cards.remove(card);
                    return card;
                }
                else{
                    trick.cards.add(card);
                    cards.remove(card);
                    return card;
                }
            }
        }
        Card tempCard = cards.first();
        if(tempCard.getSuit() == Suits.Hearts){
            trick.brokenHearts = true;
        }
        trick.cards.add(cards.first());
        cards.remove(cards.first());
        return tempCard;
    }

    public Card playCard(Trick trick, int round){
        Card card = playerHasCard(trick, round);
        trick.cards.add(card);
        cards.remove(card);
        return card;
    }
}
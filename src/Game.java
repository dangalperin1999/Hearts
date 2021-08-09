import java.util.Scanner;

public class Game {

    Player[] players = new Player[4];

    Deck deck = new Deck();
    Trick trick = new Trick();
    Hand bot1Hand = new Hand();
    Hand bot2Hand = new Hand();
    Hand bot3Hand = new Hand();
    Hand playerHand = new Hand();

    Player bot1 = new Player("Bob", Seats.North, bot1Hand);
    Player bot2 = new Player("Josh", Seats.East, bot2Hand);
    Player bot3 = new Player("Drake", Seats.South, bot3Hand);
    Player player;

    public void play(){

        players[0] = bot1;
        players[1] = bot2;
        players[2] = bot3;

        System.out.println("Game rules:\n" +
                "There are 4 players each player gets 13 cards\n" +
                "Game starts with 2 of Clubs\n" +
                "Each player puts one card when it's his turn\n" +
                "Played card's suit must be lead suit or if there is no lead suit the player can play any card\n" +
                "Card with Hearts suit can't be played on the first turn and if hearts were not broken\n" +
                "Hearts are broken when a player plays a card with hearts suit\n" +
                "Every round all played cards go into a trick\n" +
                "Player that played the biggest card with lead suit wins the trick\n" +
                "Winner of the trick gets points if the trick had cards with Hearts or Queen of Spades and starts next trick\n" +
                "The game ends when a player gets 100 points and the winner is the player with smallest amount of points\n");

        Scanner sc = new Scanner(System.in);
        System.out.println("Type your name");
        String playerName = sc.nextLine();
        player = new Player(playerName, Seats.West, playerHand);

        players[3] = player;

        while(bot1.score != 100 && bot2.score != 100 && bot3.score != 100 && player.score != 100 && bot1.score < 100 && bot2.score < 100 && bot3.score < 100 && player.score < 100) {
            deck.shuffle();

            for (int i = 0; i < 13; i++) {
                bot1.hand.cards.add(deck.dealCard());
                bot2.hand.cards.add(deck.dealCard());
                bot3.hand.cards.add(deck.dealCard());
                player.hand.cards.add(deck.dealCard());
            }

            int round = 1;

            for (Card card:bot1.hand.cards) {
                if(card.getRank() == Ranks.Deuce && card.getSuit() == Suits.Clubs){
                    trick.leader = bot1;
                    break;
                }
            }

            for (Card card:bot2.hand.cards) {
                if(card.getRank() == Ranks.Deuce && card.getSuit() == Suits.Clubs){
                    trick.leader = bot2;
                    break;
                }
            }

            for (Card card:bot3.hand.cards) {
                if(card.getRank() == Ranks.Deuce && card.getSuit() == Suits.Clubs){
                    trick.leader = bot3;
                    break;
                }
            }

            for (Card card:player.hand.cards) {
                if(card.getRank() == Ranks.Deuce && card.getSuit() == Suits.Clubs){
                    trick.leader = player;
                    break;
                }
            }

            int index = 0;
            for (int i = 0; i < 4; i++) {
                if(players[i] == trick.getLeader()){
                    index = i;
                }
            }
            Card cardPlayed = null;
            Card newCard;
            for (int i = 0; i < 13; i++) {
                for (int j = index; true; j++) {
                    if(players[j] == bot1 || players[j] == bot2 || players[j] == bot3){
                        if(j == index) {
                            cardPlayed = players[j].hand.botPlayCard(trick, round);
                            round++;
                            trick.lead = cardPlayed.getSuit();
                            trick.winner = players[j];
                        }
                        else{
                            newCard = players[j].hand.botPlayCard(trick, round);
                            int cardPlayedRank = 0;
                            int newCardRank = 0;

                            switch (cardPlayed.getRank().getSymbol()){
                                case "2":
                                    cardPlayedRank = 2;
                                    break;
                                case "3":
                                    cardPlayedRank = 3;
                                    break;
                                case "4":
                                    cardPlayedRank = 4;
                                    break;
                                case "5":
                                    cardPlayedRank = 5;
                                    break;
                                case "6":
                                    cardPlayedRank = 6;
                                    break;
                                case "7":
                                    cardPlayedRank = 7;
                                    break;
                                case "8":
                                    cardPlayedRank = 8;
                                    break;
                                case "9":
                                    cardPlayedRank = 9;
                                    break;
                                case "T":
                                    cardPlayedRank = 10;
                                    break;
                                case "J":
                                    cardPlayedRank = 11;
                                    break;
                                case "Q":
                                    cardPlayedRank = 12;
                                    break;
                                case "K":
                                    cardPlayedRank = 13;
                                    break;
                                case "A":
                                    cardPlayedRank = 14;
                                    break;
                            }
                            switch (newCard.getRank().getSymbol()){
                                case "2":
                                    newCardRank = 2;
                                    break;
                                case "3":
                                    newCardRank = 3;
                                    break;
                                case "4":
                                    newCardRank = 4;
                                    break;
                                case "5":
                                    newCardRank = 5;
                                    break;
                                case "6":
                                    newCardRank = 6;
                                    break;
                                case "7":
                                    newCardRank = 7;
                                    break;
                                case "8":
                                    newCardRank = 8;
                                    break;
                                case "9":
                                    newCardRank = 9;
                                    break;
                                case "T":
                                    newCardRank = 10;
                                    break;
                                case "J":
                                    newCardRank = 11;
                                    break;
                                case "Q":
                                    newCardRank = 12;
                                    break;
                                case "K":
                                    newCardRank = 13;
                                    break;
                                case "A":
                                    newCardRank = 14;
                                    break;
                            }
                            if(newCardRank > cardPlayedRank && newCard.getSuit() == cardPlayed.getSuit()){
                                trick.winner = players[j];
                                cardPlayed = newCard;
                            }


                        }
                    }
                    else{
                        if(j == index) {
                            System.out.println("Trick: " + trick.cards);
                            System.out.println("Your hand: " + players[j].hand.cards);
                            cardPlayed = players[j].hand.playCard(trick, round);
                            round++;
                            trick.lead = cardPlayed.getSuit();
                            trick.winner = players[j];
                        }
                        else{
                            System.out.println("Trick: " + trick.cards);
                            System.out.println("Your hand: " + players[j].hand.cards);
                            newCard = players[j].hand.playCard(trick, round);
                            int cardPlayedRank = 0;
                            int newCardRank = 0;
                            switch (cardPlayed.getRank().getSymbol()){
                                case "2":
                                    cardPlayedRank = 2;
                                    break;
                                case "3":
                                    cardPlayedRank = 3;
                                    break;
                                case "4":
                                    cardPlayedRank = 4;
                                    break;
                                case "5":
                                    cardPlayedRank = 5;
                                    break;
                                case "6":
                                    cardPlayedRank = 6;
                                    break;
                                case "7":
                                    cardPlayedRank = 7;
                                    break;
                                case "8":
                                    cardPlayedRank = 8;
                                    break;
                                case "9":
                                    cardPlayedRank = 9;
                                    break;
                                case "T":
                                    cardPlayedRank = 10;
                                    break;
                                case "J":
                                    cardPlayedRank = 11;
                                    break;
                                case "Q":
                                    cardPlayedRank = 12;
                                    break;
                                case "K":
                                    cardPlayedRank = 13;
                                    break;
                                case "A":
                                    cardPlayedRank = 14;
                                    break;
                            }
                            switch (newCard.getRank().getSymbol()){
                                case "2":
                                    newCardRank = 2;
                                    break;
                                case "3":
                                    newCardRank = 3;
                                    break;
                                case "4":
                                    newCardRank = 4;
                                    break;
                                case "5":
                                    newCardRank = 5;
                                    break;
                                case "6":
                                    newCardRank = 6;
                                    break;
                                case "7":
                                    newCardRank = 7;
                                    break;
                                case "8":
                                    newCardRank = 8;
                                    break;
                                case "9":
                                    newCardRank = 9;
                                    break;
                                case "T":
                                    newCardRank = 10;
                                    break;
                                case "J":
                                    newCardRank = 11;
                                    break;
                                case "Q":
                                    newCardRank = 12;
                                    break;
                                case "K":
                                    newCardRank = 13;
                                    break;
                                case "A":
                                    newCardRank = 14;
                                    break;
                            }
                            if(newCardRank > cardPlayedRank && newCard.getSuit() == cardPlayed.getSuit()){
                                trick.winner = players[j];
                                cardPlayed = newCard;
                            }
                        }
                    }
                    if(index == 0 && j == 3){
                        break;
                    }
                    if(j == 3){
                        j = -1;
                    }
                    if(j == index-1){
                        break;
                    }
                }
                System.out.println("Trick: " + trick.cards);
                System.out.println(trick.winner.name + " won the trick");
                trick.leader = trick.winner;
                for (int k = 0; k < 4; k++) {
                    if(players[k] == trick.getLeader()){
                        for (Card card: trick.cards) {
                            if(card.getSuit() == Suits.Hearts){
                                players[k].score += 1;
                            }
                            if(card.getSuit() == Suits.Spades && card.getRank() == Ranks.Queen){
                                players[k].score += 13;
                            }
                        }
                        index = k;
                    }
                }
                System.out.println("Your score: " + player.score);
                trick.lead = null;
                trick.discard(deck);
            }
        }
        int lowest = players[0].score;
        for (int i = 1; i < 4; i++) {
            if(players[i].score < lowest){
                lowest = players[i].score;
            }
        }
        System.out.print("Scores: ");
        for (int i = 0; i < 4; i++) {
            System.out.print(players[i].score + ",");
            System.out.println();
            if(players[i].score == lowest){
                System.out.println("The winner is " + players[i].name);
            }
        }
    }
}


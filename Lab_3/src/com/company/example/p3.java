// Author: Jake Edwards
// Class: CSC 241-001
// Professor: Dr. Ivancic
// Date Started: October 3, 2017
// Date Finished: October 4, 2017

package com.company.example;

import java.util.Scanner;

public class p3 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        CardDeck cardDeck = new CardDeck(); // create card deck
        cardDeck.shuffle(); // shuflle cards
        System.out.println("Deck shuffled:");

        // prompt user
        System.out.println("Begin play?(y/n)");
        String choice = input.next(); // store user choice

        // if the user picks a non-existent choice this loop will run until they pick a valid choice of "y" or "n"
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
            System.out.println("Please choose one of the two choices:");
            System.out.println("Begin play? (y/n)");
            choice = input.next();
        }

        // user picks "y"
        if (choice.equalsIgnoreCase("y")) {

            // create 2 players
            Playerhand player1 = new Playerhand();
            Playerhand player2 = new Playerhand();

            Card card1;
            Card card2;

            System.out.println("Dealing out cards...");

            int counter = 1;
            for (int i = 0; counter < 52; i += 2) { // LOADS HANDS FOR PLAYER 1 AND 2
                card1 = cardDeck.getCard(i);
                card2 = cardDeck.getCard(counter);

                player1.addCardToBack(card1);
                player2.addCardToBack(card2);

                counter += 2;
            }
            // PLAYER1 AND PLAYER2 HANDS LOADED

            System.out.println("Begin playing 20 rounds...");
            System.out.println("Show top card...");

            // game will run for 20 rounds unless someone fails war first
            int round = 0;
            while (round < 20) {

                // pulls a card off the top of each players hand and is now referenced by these vars
                Card p1Card = player1.removeTopCard();
                Card p2Card = player2.removeTopCard();

                System.out.println("Show top card...");
                System.out.println("Player A: " + p1Card.getRank() + " " + p1Card.getSuit());
                System.out.println("Player B: " + p2Card.getRank() + " " + p2Card.getSuit());

                // For ease of use.  getRank() returns string and they need to be compared so much easier with int form
                int rank1 = p1Card.getRankAsInt();
                int rank2 = p2Card.getRankAsInt();
                // if both cards have same rank then war takes place
                if (rank1 == rank2) { // WARRRRR!!!!!!!

                    System.out.println("WARRRRR!!!!!!!!");


                    Playerhand warHand = new Playerhand(); // utilizing the Playerhand class to hold the cards for the war pile
                    Card warCardp1; // 3rd card that is drawn
                    Card warCardp2; // 3rd card that is drawn

                    if (player1.getSize() > 2 && player2.getSize() > 2) {
                        do {
                            System.out.println("Discard top 2 cards into the pool and reveal the 3rd");

                            // add 2 cards from each hand to the warHand
                            warHand.addCardToBack(player1.removeTopCard());
                            warHand.addCardToBack(player2.removeTopCard());
                            warHand.addCardToBack(player1.removeTopCard());
                            warHand.addCardToBack(player2.removeTopCard());

                            // pull 3rd card for each player to compare and see who wins or if it's a tie and war continues
                            warCardp1 = player1.removeTopCard();
                            warCardp2 = player1.removeTopCard();

                            // print out players 3rd card
                            System.out.println("Player A: " + warCardp1.getRank() + " " + warCardp1.getSuit());
                            System.out.println("Player B: " + warCardp2.getRank() + " " + warCardp2.getSuit());


                            // will continue looping only as long as both players 3rd cards are of equal rank AND
                            // both players have at least 3 cards still in there hand.
                        } while ( (warCardp1.getRankAsInt() == warCardp2.getRankAsInt()) &&
                                  (player1.getSize() > 2 && player2.getSize() > 2));

                        // if the previous while loop exited b/c a player had less than 3 cards, this if will break out of the game loop
                        if (player1.getSize() < 3 || player2.getSize() < 2) {
                            break;
                        }

                        if (warCardp1.getRankAsInt() > warCardp2.getRankAsInt()) { // player1 pulled a higher rank, wins war, and gets all the cards
                            System.out.println("Winner of hand: Player A");

                            player1.addCardToBack(warCardp1); // adds both players 3rd cards to the winning players hand
                            player1.addCardToBack(warCardp2);

                            while (warHand.getSize() > 0) {
                                player1.addCardToBack(warHand.removeTopCard());
                            }
                        }
                        else { // player2 pulled a higher rank, wins war, and gets all the cards
                            System.out.println("Winner of hand: Player B");

                            player2.addCardToBack(warCardp1); // adds both players 3rd cards to the winning players hand
                            player2.addCardToBack(warCardp2);

                            while (warHand.getSize() > 0) {
                                player1.addCardToBack(warHand.removeTopCard());
                            }
                        }

                    }

                    // at least one or more players has less than 3 cards meaning they loose whole game immediately
                    else {
                        break; // END GAME
                    }
                } // END WAR

                // cards do not have the same rank, so game proceeds as normal
                else {
                    if (rank1 > rank2) { // player1 wins

                        // place both cards at the bottom of player1's hand
                        player1.addCardToBack(p1Card);
                        player1.addCardToBack(p2Card);
                        System.out.println("Winner of hand: Player A");
                    }
                    else { // player 2 wins
                        player2.addCardToBack(p1Card);
                        player2.addCardToBack(p2Card);
                        System.out.println("Winner of hand: Player B");
                    }
                }

                round++;
            }
            // GAME LOOP HAS BEEN EXITED

            if (player1.getSize() > player2.getSize()) { // player 1 has more cards and wins
                System.out.println("The winner is...\nPlayer A");
            }
            else if (player2.getSize() > player1.getSize()) { // player 2 has more cards and wins
                System.out.println("The winner is...\nPlayer B");
            }
            else { // a tie
                System.out.println("The game is a tie!");
            }
        }

        // user picks "n"
        else {
            System.exit(0);
        }

    }
}

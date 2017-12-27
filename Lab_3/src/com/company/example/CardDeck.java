// Author: Jake Edwards
// Class: CSC 241-001
// Professor: Dr. Ivancic
// Date Started: October 3, 2017
// Date Finished: October 4, 2017

package com.company.example;

import java.util.Random;

public class CardDeck {

    private Card[] cardDeck;

    // constructor
    public CardDeck() {
        // actually create individual cards
        cardDeck = new Card[52];

        int counter = 0;
        // loops through ranks
        for (int rank = 2; rank < 15 ; rank++) {

            // loops through suits
            for (int suit = 0; suit < 4; suit++) {
                cardDeck[counter] = new Card(suit, rank, counter);

                counter++;
            }
        }
    }

    // print deck to console
    public void print() {

        int x = 0;
        while (x < 52) {
            for (int i = 0; i < 4; i++) {
                Card card = cardDeck[x];
                String suit = card.getSuit();

                if (i < 3) {
                    System.out.print(card.getRank() + " " + suit + ", ");
                }
                else {
                    System.out.println(card.getRank() + " " + suit);
                }


                x++;
            }
        }
    }

    // precondition: method is called on a CardDeck object
    // postcondition: the deck is now shuffled randomly
    public void shuffle() {

        Random rand = new Random();

        for (int i = 0; i < cardDeck.length; i++) {

            // creates random number between 0(inclusive) and 52(exclusive)
            int x = rand.nextInt(52);

            // swap cards
            Card first = cardDeck[i];
            Card second = cardDeck[x];
            cardDeck[i] = second;
            cardDeck[x] = first;
        }
    }

    // essentially a Bubble Sort
    public void sort() {

        boolean sortedByOrder;

        // first sort cards by order variable
        do {
            sortedByOrder = true;
            for (int i = 0; i < 51; i++) {
                if (cardDeck[i].getOrder() < cardDeck[i+1].getOrder()) {
                    // deck is not sorted by order yet
                    sortedByOrder = false;

                    // swap cards
                    Card first = cardDeck[i];
                    Card second = cardDeck[i+1];
                    cardDeck[i] = second;
                    cardDeck[i+1] = first;
                }
            }
        }while (!sortedByOrder);

        // Cards are sorted by order/rank, but now need to be sorted by suit
        boolean sortedBySuit;

        // x var for working with the 4 cards per rank.  Will be incremented by 4 ever loop
        int x = 0;

        for (int i = 0; i < 13; i++) { // to go through all 13 rows(ranks) of cards

            Card first = cardDeck[x];
            Card second = cardDeck[x+1];
            Card third = cardDeck[x+2];
            Card fourth = cardDeck[x+3];

            cardDeck[x] = fourth;
            cardDeck[x+1] = third;
            cardDeck[x+2] = second;
            cardDeck[x+3] = first;

            x += 4;
        }
    }

    // precondition: getCard is called on the deck and passed an integer i b/w 0 and cardDeck.length
    // postcondition: method returns the card deck and index i, or null and an error is printed if i is out of bounds
    public Card getCard(int i) {
        if (i >= 0 && i < cardDeck.length) {
            return cardDeck[i];
        }
        else {
            System.out.println("Out of bounds: Card of index " + i + " does not exist.");
            return null;
        }
    }
}

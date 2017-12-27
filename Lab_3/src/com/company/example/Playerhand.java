// Author: Jake Edwards
// Class: CSC 241-001
// Professor: Dr. Ivancic
// Date Started: October 3, 2017
// Date Finished: October 4, 2017

package com.company.example;

public class Playerhand {

    private int size; // keeps track of the size of the player's hand
    private Card head; // points to first card in linked list modelling a hand of cards

    // default constructor
    public Playerhand() {
        this.size = 0;
    }

    // precondition: method is called on Playerhand object
    // postcondition: if hand is empty(size == 0) return null, otherwise return card on top and move head pointer to head.next
    public Card removeTopCard() {
        if (head != null) {
            Card temp = head;
            head = head.next;
            size--;

            return temp;
        }
        else {
            return null;
        }
    }

    // precondition: method is called on PlayerHand object and is passed a card from the deck
    // postcondition: if hand is empty, set head to card.  If it is not empty, method traverses to last element and adds card to end
    public void addCardToBack(Card card) {
        if (head == null) {
            head = card;
            card.next = null;
            size++;
        }
        else {
            // Traverse to last card in the hand
            Card curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = card;
            card.next = null;
            size++;
        }
    }

    public int getSize() {
        return this.size;
    }
}

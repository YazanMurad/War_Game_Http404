package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {
    private int size;
    private final ArrayList<Card> cards;

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>();

        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        for (String suit : suits) {
            for (int value = 1; value <= 13; value++) {
                cards.add(new PlayingCard(suit, value));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int getSize() {
        return cards.size();
    }
    
    public void setSize(int size) {
        this.size = size;
    }
}

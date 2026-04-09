package ca.sheridancollege.project;

public class PlayingCard extends Card {
    private final String suit;
    private final int value;

    public PlayingCard(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        String rank = String.valueOf(value);
        if (value == 11) rank = "Jack";
        else if (value == 12) rank = "Queen";
        else if (value == 13) rank = "King";
        else if (value == 1) rank = "Ace";
        
        return rank + " of " + suit;
    }
}

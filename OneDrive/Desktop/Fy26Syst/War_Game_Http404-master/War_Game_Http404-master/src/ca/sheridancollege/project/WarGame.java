package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WarGame extends Game {

    private WarPlayer p1;
    private WarPlayer p2;

    public WarGame(String name, WarPlayer p1, WarPlayer p2) {
        super(name);
        this.p1 = p1;
        this.p2 = p2;

        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        setPlayers(players);
        
        setupGame();
    }
    
    private void setupGame() {
        GroupOfCards deck = new GroupOfCards(52);
        deck.shuffle();
        ArrayList<Card> cards = deck.getCards();
        
        for (int i = 0; i < cards.size(); i++) {
            if (i % 2 == 0) {
                p1.addCard(cards.get(i));
            } else {
                p2.addCard(cards.get(i));
            }
        }
    }

    @Override
    public void play() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to WAR");

        while (p1.hasCards() && p2.hasCards()) {
            System.out.println(p1.getName() + " Cards: " + p1.getCardCount());
            System.out.println(p2.getName() + " Cards: " + p2.getCardCount());
            System.out.println("Press Enter to play the next round...");
            input.nextLine();

            ArrayList<Card> pile = new ArrayList<>();
            resolveRound(pile);
        }
        
        declareWinner();
        input.close();
    }
    
    private void resolveRound(ArrayList<Card> pile) {
        Card c1 = p1.drawCard();
        Card c2 = p2.drawCard();
        
        if (c1 == null || c2 == null) return;
        
        pile.add(c1);
        pile.add(c2);
        
        System.out.println(p1.getName() + " plays: " + c1);
        System.out.println(p2.getName() + " plays: " + c2);
        
        int v1 = c1.getValue();
        int v2 = c2.getValue();
        
        if (v1 > v2) {
            System.out.println("--> " + p1.getName() + " wins the round!");
            for (Card c : pile) p1.addCard(c);
        } else if (v2 > v1) {
            System.out.println("--> " + p2.getName() + " wins the round!");
            for (Card c : pile) p2.addCard(c);
        } else {
            System.out.println("War Declared!");
            handleWar(pile);
        }
    }
    
    private void handleWar(ArrayList<Card> pile) {
        if (p1.getCardCount() < 4 || p2.getCardCount() < 4) {
            System.out.println("A player has insufficient cards to continue the war!");
            if (p1.getCardCount() < 4) {
                while (p1.hasCards()) p2.addCard(p1.drawCard());
            } else {
                while (p2.hasCards()) p1.addCard(p2.drawCard());
            }
            return;
        }

        System.out.println("Each player places 3 cards face down...");
        for (int i = 0; i < 3; i++) {
            pile.add(p1.drawCard());
            pile.add(p2.drawCard());
        }
        
        System.out.println("Flipping the 4th card!");
        resolveRound(pile);
    }
    
    public void declareWinner() {
        System.out.println("GAME OVER");
        if (p1.hasCards())
            System.out.println("Winner: " + p1.getName());
        else
            System.out.println("Winner: " + p2.getName());
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Deck {
    ArrayList<Card> deck;
    public Deck() {
        deck = new ArrayList<>();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public ArrayList<Card> dealCards(int numberOfCards) {
        ArrayList<Card> dealtCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            if (!deck.isEmpty()) {
                dealtCards.add(deck.remove(0));
            }
        }
        return dealtCards;
    }

}

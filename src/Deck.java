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

}

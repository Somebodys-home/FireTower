import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class Deck {
    ArrayList<Card> deck = new ArrayList<>(52);
    Random rand = new Random();
    public Deck(GameBoard board, Scanner scanner) {
        addCard(new Ember(board, scanner), 2);
        addCard(new Explosion(board, scanner), 4);
        addCard(new BurningSnag(board, scanner), 4);
        addCard(new FlareUp(board, scanner), 4);
        addCard(new Airdrop(board, scanner), 4);
        addCard(new FireEngine(board, scanner), 4);
        addCard(new SmokeJumper(board, scanner), 4);
        addCard(new DozerLine(board, scanner), 3);
        addCard(new SmokeJumper(board, scanner), 3);
        addCard(new DeReforest(board, scanner), 4);
        // how do you add wind cards?
    }

    public void addCard(Card card, int count) {
        for (int i = count; i > 0; i--) {
            deck.add(card);
        }
    }

    public Card dealRandomCard() { //picks a random card from the deck
        if (deck.size() > 0) {
            int index = rand.nextInt(deck.size());
            return deck.remove(index);
        }
        return null; // Return null if the deck is empty
    }
}

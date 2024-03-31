import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Deck {
    ArrayList<Card> deck;
    public Deck() {
        deck = new ArrayList<>();
    }

    public void initalizeGameDeck(GameBoard board, Scanner scanner){
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
        addCard(new WindCard("North", board.getWindDirection(), board, scanner), 2);
        addCard(new WindCard("South", board.getWindDirection(), board, scanner), 2);
        addCard(new WindCard("East", board.getWindDirection(), board, scanner), 2);
        addCard(new WindCard("West", board.getWindDirection(), board, scanner), 2);
    }

    // Specifically so I don't have to use a lotta loops, don't use this method outside of this class
    public void addCard(Card card, int count) {
        for (int i = count; i > 0; i--) {
            deck.add(card);
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
}

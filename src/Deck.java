import java.util.ArrayList;
import java.util.Scanner;

public class Deck {
    ArrayList<Card> deck = new ArrayList<>(52);
    public Deck(GameBoard board, Scanner scanner) {
        addCard(new Ember(board, scanner), 2);
        addCard(new Explosion(board, scanner), 4);
        addCard(new BurningSnag(board, scanner), 4);
        addCard(new FlareUp(board, scanner), 4);
        addCard(new Airdrop(board), 4);
        addCard(new FireEngine(board), 4);
        addCard(new SmokeJumper(board), 4);
        addCard(new DozerLine(board), 3);
        addCard(new SmokeJumper(board), 3);
        addCard(new DeReforest(board), 4);
//        addCard(); for the wind cards
//        addCard();
//        addCard();
//        addCard();
    }

    public void addCard(Card card, int count) {
        for (int i = count; i > 0; i--) {
            deck.add(card);
        }
    }
}

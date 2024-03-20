import java.util.Scanner;

public abstract class FireCard extends Card {
    public FireCard(String name, GameBoard board, Scanner scan) {
        super(name, board, scan);
    }

    @Override
    public abstract void cardEffect();
}

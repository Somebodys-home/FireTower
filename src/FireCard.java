import java.util.Scanner;

public abstract class FireCard extends Card {
    public FireCard(String name, Board board, Scanner scan) {
        super(name, board, scan);
    }

    @Override
    public abstract void cardEffect();
}

import java.util.Scanner;

public abstract class FirebreakCard extends Card {
    public FirebreakCard(String name, GameBoard board, Scanner scan) {
        super(name, board, scan);
    }

    @Override
    public abstract void cardEffect();
}

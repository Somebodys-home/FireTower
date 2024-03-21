import java.util.Scanner;

public abstract class WaterCard extends Card {
    public WaterCard(String name, GameBoard board, Scanner scan) {
        super(name, board, scan);
    }

    @Override
    public abstract void cardEffect();
}

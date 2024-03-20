import java.util.Scanner;

public class BucketCard extends Card {
    private boolean hasWater;
    public BucketCard(GameBoard board, Scanner scanner) {
        super("Bucket", board, scanner);
        hasWater = true;
    }

    @Override
    public void cardEffect() {

    }
}

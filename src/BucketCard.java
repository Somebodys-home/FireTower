import java.util.Scanner;

public class BucketCard extends Card {
    private boolean hasWater;
    public BucketCard(GameBoard board, Scanner scanner) {
        super("Bucket", board, scanner);
        hasWater = true;
    }

    @Override
    public void cardEffect() {
        if (hasWater) {
            System.out.println("Select a fire space to extinguish.");
            Space targetSpace = getBoard().getSpace(getScan());
            if (targetSpace instanceof Fire) {
                targetSpace = new Space("🌲", targetSpace.getX(), targetSpace.getY()); // replace fire with tree
                hasWater = false; // bucket is now empty
                System.out.println("Fire extinguished!");
            } else {
                System.out.println("Invalid target. Please select a fire space.");
            }
        } else {
            System.out.println("Bucket is empty. Cannot extinguish fire.");
        }
    }
}

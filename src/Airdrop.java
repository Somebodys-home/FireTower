import java.util.Scanner;

public class Airdrop extends WaterCard{
    public Airdrop(GameBoard board, Scanner scan) {
        super("Airdrop", board, scan);
    }

    @Override
    public void cardEffect() {
        System.out.println("Select up to three fire spaces to extinguish.");
        for (int i = 0; i < 3; i++) {
            Space targetSpace = getBoard().getSpace(getScan());
            if (targetSpace instanceof Fire) {
                targetSpace = new Space("🌲", targetSpace.getX(), targetSpace.getY()); // replace fire with tree
                System.out.println("Fire extinguished at (" + targetSpace.getX() + ", " + targetSpace.getY() + ")!");
            } else {
                System.out.println("Invalid target. Please select a fire space.");
                i--; // decrement i to retry this iteration
            }
        }
    }
}

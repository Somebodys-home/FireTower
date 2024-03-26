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
                getBoard().obtainBoard()[targetSpace.getY()][targetSpace.getX()] = new Space("🌲", targetSpace.getX(), targetSpace.getY()); // replace fire with tree
                hasWater = false; // bucket is now empty
                System.out.println("Fire extinguished!");
            } else {
                System.out.println("Invalid target. Please select a fire space.");
            }
        } else {
            System.out.println("Bucket is empty. Cannot extinguish fire.");
        }
    }

    @Override
    public Space initialStep() {  //TODO: FIX STUFF HERE
        System.out.println("Select a space on your tower space with a flame to extinguish it and two other flames.");
        System.out.println("Used only once.");
        Space targettedSpace;
        do {
            targettedSpace = getSpace();
        } while (!(getBoard().isValidWater(targettedSpace)));
        getBoard().obtainBoard()[targettedSpace.getY()][targettedSpace.getX()] = new Space(targettedSpace.getX(), targettedSpace.getY());
        return targettedSpace;
    }
}

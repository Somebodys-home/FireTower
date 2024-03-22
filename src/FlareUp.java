import java.util.Scanner;

public class FlareUp extends FireCard {
    public FlareUp(GameBoard board, Scanner scan) {
        super("Flare Up", board, scan);
    }

    @Override
    public void cardEffect() {
        System.out.println("Select a space on the board that isn't part of the eternal flame or a fire tower area.");
        System.out.println("This space must build off of an existing fire space.");
        Space targettedSpace;
        do {
            targettedSpace = getBoard().getSpace(getScan());
        } while (targettedSpace instanceof Fire || targettedSpace instanceof Firebreak);

    }
}

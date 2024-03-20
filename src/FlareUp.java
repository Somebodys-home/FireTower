import java.util.Scanner;

public class FlareUp extends FireCard {
    public FlareUp(GameBoard board, Scanner scan) {
        super("Flare Up", board, scan);
    }

    @Override
    public void cardEffect() {
        System.out.println("Select a space on the board that isn't part of the eternal flame or a fire tower area.");
        System.out.println("This space must build off of an existing fire space.");
        System.out.print("Select an x-coordinate: ");
        int x = getScan().nextInt();
        getScan().nextLine();
        System.out.print("Select a y-coordinate: ");
        int y = getScan().nextInt();
        getScan().nextLine();
    }
}

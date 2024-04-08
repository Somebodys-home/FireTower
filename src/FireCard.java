import java.util.Scanner;

public abstract class FireCard extends Card {
    public FireCard(String name, GameBoard board, Scanner scan) {
        super(name, board, "\033[0;31m", scan);
    }

    @Override
    public abstract void cardEffect();

    public Space initialStep() {
        System.out.println("Select a space on the board that isn't part of the eternal flame or a fire tower area.");
        Space targettedSpace;
        do {
            System.out.println("This space must build off of an existing fire space.");
            targettedSpace = getSpace();
        } while (!(getBoard().buildsOffFire(targettedSpace)));
        getBoard().obtainBoard()[targettedSpace.getY()][targettedSpace.getX()] = new Fire(targettedSpace.getX(), targettedSpace.getY());
        return targettedSpace;
    }
}

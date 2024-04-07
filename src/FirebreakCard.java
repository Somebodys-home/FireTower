import java.util.Scanner;

public abstract class FirebreakCard extends Card {
    public FirebreakCard(String name, GameBoard board, Scanner scan) {
        super(name, board, "\033[0;35m", scan);
    }

    @Override
    public abstract void cardEffect();

    public Space initialStep() {
        System.out.println("Select a space on the board that isn't already a firebreak or next to one.");
        System.out.println("Firebreaks block fire but not water so be mindful of that.");
        Space targettedSpace;
        do {
            targettedSpace = getSpace();
        } while (!(getBoard().isValidFireBreak(targettedSpace)));
        getBoard().obtainBoard()[targettedSpace.getY()][targettedSpace.getX()] = new Firebreak(targettedSpace.getX(), targettedSpace.getY());
        return targettedSpace;
    }
}

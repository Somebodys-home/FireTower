import java.util.Scanner;

public abstract class WaterCard extends Card {
    public WaterCard(String name, GameBoard board, Scanner scan) {
        super(name, board, "\033[0;34m", scan);
    }

    @Override
    public abstract void cardEffect();

    public Space initialStep() {
        System.out.println("Select a space on the board that is occupied by fire not in the fire tower area.");
        System.out.println("Extinguish the fires in the desired direction which can pass through firebreaks.");
        Space targettedSpace;
        do {
            targettedSpace = getSpace();
        } while (!(getBoard().isValidWaterPlacement(targettedSpace)));
        getBoard().obtainBoard()[targettedSpace.getY()][targettedSpace.getX()] = new Space(targettedSpace.getX(), targettedSpace.getY());
        return targettedSpace;
    }
}

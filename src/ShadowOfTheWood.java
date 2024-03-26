import java.util.Scanner;

public class ShadowOfTheWood extends EventCard {
    public ShadowOfTheWood(GameBoard board, Card[] deck, Card[] discard, Player[] turnOrder, Scanner scan) {
        super("Shadow of The Wood", board, deck, discard, turnOrder, scan);
    }

    @Override
    public void cardEffect() {

    }

    public Space initialStep() {   //TODO: THE SHADOWS DESIRE CHANGE
        System.out.println("Slenderman lurks in the forest within.");
        System.out.println("Proceed with caution.");
        Space targettedSpace;
        do {
            targettedSpace = getSpace();
        } while (!(getBoard().isValidWater(targettedSpace)));
        getBoard().obtainBoard()[targettedSpace.getY()][targettedSpace.getX()] = new Space(targettedSpace.getX(), targettedSpace.getY());
        return targettedSpace;
    }
}

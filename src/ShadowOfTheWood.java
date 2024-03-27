import java.util.Scanner;

public class ShadowOfTheWood extends EventCard {
    public ShadowOfTheWood(GameBoard board, Card[] deck, Card[] discard, Player[] turnOrder, Scanner scan) {
        super("Shadow of The Wood", board, deck, discard, turnOrder, scan);
    }

    /*

    STEP 0: Get one player to die to shuffle the card into the deck and then draw it

    STEP 1: Discard the Shadow of the Wood card and draw back up to your hand size

    STEP 2: Each eliminated player in turn order from whoever drew the card chooses one option below:

    STEP 2a: Steal a card ar random from each active player and then play one of those cards or Reckless abandon if possible

    STEP 2b: Roll the wind die thrice and place a fire gem in each direction indicated without changing the general wind direction

    STEP 3: Check for eliminations. If all active towers die, then the shadows win.

     */

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

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
        // Discard this card immediately and draw back up to hand size
        discard(this);
        drawUpToHandSize();

        // Each eliminated player must choose one of the following actions in turn order
        for (Player player : turnOrder) {
            if (player.isEliminated()) {
                System.out.println("Player " + player.getName() + ", choose an action:");
                System.out.println("1. Pull a card at random from each active player’s hand and take its action.");
                System.out.println("2. Roll the wind die three times and place one fire gem orthogonally adjacent to a fire gem on the board in the direction indicated on the die.");

                int action = scan.nextInt();
                if (action == 1) {
                    pullCardAndTakeAction(player);
                } else if (action == 2) {
                    rollWindDieAndPlaceFireGem(player);
                }
            }
        }

        // If any player eliminations occur, resolve after Shadow of the Wood concludes
        resolvePlayerEliminations();
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



    private void pullCardAndTakeAction(Player player) {
        // TODO: Implement this method to pull the card
    }

    private void rollWindDieAndPlaceFireGem(Player player) {
        // TODO: Implement this method based to place the firegem
    }

    private void resolvePlayerEliminations() {
        // TODO: Implement this method to handle eliminated players (e.g., update scores, check for tower burning)
    }
}

import java.util.Scanner;

public class ChampionOfTheWood extends EventCard {
    public ChampionOfTheWood(GameBoard board, Card[] deck, Card[] discard, Player[] turnOrder, Scanner scan) {
        super("Champion Of The Wood", board, deck, discard, turnOrder, scan);
    }

    @Override
    public void cardEffect() {

    }

    public Space initialStep() {  //TODO: FIX STUFF HERE
        System.out.println("You are the champion.");
        System.out.println("Don't brag now, we don't like braggers.");
        Space targettedSpace;
        do {
            targettedSpace = getSpace();
        } while (!(getBoard().isValidWater(targettedSpace)));
        getBoard().obtainBoard()[targettedSpace.getY()][targettedSpace.getX()] = new Space(targettedSpace.getX(), targettedSpace.getY());
        return targettedSpace;
    }
}

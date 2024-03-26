import java.util.Scanner;

public class MutualAid extends EventCard {
    public MutualAid(
            String name,
            GameBoard board,
            Card[] deck,
            Card[] discard,
            Player[] turnOrder,
            Scanner scan
    ) {
        super(name, board, deck, discard, turnOrder, scan);
    }

    @Override
    public void cardEffect() {

    }

    public Space initialStep() {   //TODO: CHANGES ARE INDEED NEEDED
        System.out.println("Everyone is willing to help each other.");
        System.out.println("Use this opportunity wisely.");
        Space targettedSpace;
        do {
            targettedSpace = getSpace();
        } while (!(getBoard().isValidWater(targettedSpace)));
        getBoard().obtainBoard()[targettedSpace.getY()][targettedSpace.getX()] = new Space(targettedSpace.getX(), targettedSpace.getY());
        return targettedSpace;
    }
}

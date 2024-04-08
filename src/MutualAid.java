import java.util.Scanner;

public class MutualAid extends EventCard {
    public MutualAid(
            GameBoard board,
            Card[] deck,
            Card[] discard,
            Player[] turnOrder,
            Scanner scan
    ) {
        super("Mutual Aid", board, deck, discard, turnOrder, scan);
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
        } while (!(getBoard().isValidWaterPlacement(targettedSpace)));
        getBoard().obtainBoard()[targettedSpace.getY()][targettedSpace.getX()] = new Space(targettedSpace.getX(), targettedSpace.getY());
        return targettedSpace;
    }

    @Override
    public String[] cardDisplay() {
        String[] cardStrings =
                       {"╭───────╮",
                        "│ Mutual│",
                        "│  Aid  │",
                        "│       │",
                        "│ Event │",
                        "╰───────╯"};
        return cardStrings;
    }
}

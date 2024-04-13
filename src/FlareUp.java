import java.util.Scanner;

public class FlareUp extends FireCard {
    public FlareUp(GameBoard board, Scanner scan) {
        super("Flare Up", board, scan);
    }

    @Override
    public void cardEffect() {
        System.out.println("Choose a space following the rule below. Then, indicate a direction. Afterward, your flare will build itself.");
        Space targettedSpace = initialStep();
        WindDirection targetWind = getBoard().chooseDirection(getScan());
        Space adjacentSpace = getBoard().checkOrthogonallyAdjacent(targettedSpace, targetWind);
        if (getBoard().isValidFirePlacement(adjacentSpace)) {
            getBoard().placeFire(adjacentSpace);
            Space nextAdjacentSpace = getBoard().checkOrthogonallyAdjacent(adjacentSpace, targetWind);
            if (getBoard().isValidFirePlacement(nextAdjacentSpace)) {
                getBoard().placeSpace(nextAdjacentSpace);
            }
        }
    }

    @Override
    public String[] cardDisplay() {
        String[] cardStrings =
                       {"╭───────╮",
                        "│ Flare │",
                        "│  Up   │",
                        "│       │",
                        "│ Fire  │",
                        "╰───────╯"};
        return cardStrings;
    }
}

import java.util.Scanner;

public class ScratchLine extends FirebreakCard {
    public ScratchLine(GameBoard board, Scanner scan) {
        super("Scratch Line", board, scan);
    }

    @Override
    public void cardEffect() {    //TODO: Need to check for edge case where all four sides for scratch placement is occupied
        Space targettedSpace = initialStep();
        WindDirection targetWind = getBoard().chooseDirection(getScan());
        Space adjacentSpace = getBoard().checkOrthogonallyAdjacent(targettedSpace, targetWind);
        Space nextAdjacentSpace = getBoard().checkOrthogonallyAdjacent(adjacentSpace, targetWind);
        if (getBoard().isValidFireBreak(nextAdjacentSpace)) {
            getBoard().obtainBoard()[nextAdjacentSpace.getY()][nextAdjacentSpace.getX()] = new Firebreak(nextAdjacentSpace.getX(), nextAdjacentSpace.getY());
        }
    }

    @Override
    public String[] cardDisplay() {
        String[] cardStrings =
                       {"╭───────╮",
                        "│Scratch│",
                        "│ Line  │",
                        "│ Fire- │",
                        "│ Break │",
                        "╰───────╯"};
        return cardStrings;
    }
}


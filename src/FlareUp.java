import java.util.Scanner;

public class FlareUp extends FireCard {
    public FlareUp(GameBoard board, Scanner scan) {
        super("Flare Up", board, scan);
    }

    @Override
    public void cardEffect() {
        Space targettedSpace = initialStep();
        WindDirection targetWind = getBoard().chooseDirection(getScan());
        Space adjacentSpace = getBoard().checkOrthogonallyAdjacent(targettedSpace, targetWind);
        if (getBoard().isValidFire(adjacentSpace)) {
            getBoard().obtainBoard()[adjacentSpace.getY()][adjacentSpace.getX()] = new Fire(adjacentSpace.getX(), adjacentSpace.getY());
            Space nextAdjacentSpace = getBoard().checkOrthogonallyAdjacent(adjacentSpace, targetWind);
            if (getBoard().isValidFire(nextAdjacentSpace)) {
                getBoard().obtainBoard()[nextAdjacentSpace.getY()][nextAdjacentSpace.getX()] = new Fire(nextAdjacentSpace.getX(), nextAdjacentSpace.getY());
            }
        }
    }
}

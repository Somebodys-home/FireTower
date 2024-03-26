import java.util.Scanner;

public class Airdrop extends WaterCard {
    public Airdrop(GameBoard board, Scanner scan) {
        super("Airdrop", board, scan);
    }

    @Override
    public void cardEffect() {
        Space targettedSpace = initialStep();
        WindDirection targetWind = getBoard().chooseDirection(getScan());
        Space adjacentSpace = getBoard().checkOrthogonallyAdjacent(targettedSpace, targetWind);
        if (getBoard().isValidWater(adjacentSpace)) {
            getBoard().obtainBoard()[adjacentSpace.getY()][adjacentSpace.getX()] = new Space(adjacentSpace.getX(), adjacentSpace.getY());
            Space nextAdjacentSpace = getBoard().checkOrthogonallyAdjacent(adjacentSpace, targetWind);
            if (getBoard().isValidWater(nextAdjacentSpace)) {
                getBoard().obtainBoard()[nextAdjacentSpace.getY()][nextAdjacentSpace.getX()] = new Space(nextAdjacentSpace.getX(), nextAdjacentSpace.getY());
            }
        }
    }
}

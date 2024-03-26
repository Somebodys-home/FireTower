import java.util.Scanner;

public class FlareUp extends FireCard {
    public FlareUp(GameBoard board, Scanner scan) {
        super("Flare Up", board, scan);
    }

    @Override
    public void cardEffect() {
        Space targettedSpace = initialStep();
        String choice = "";
        while (!choice.equals("NORTH") && !choice.equals("EAST") && !choice.equals("SOUTH") && !choice.equals("WEST")) {
            System.out.print("In which direction would you like to build your flare in(NORTH, EAST, SOUTH, WEST)? ");
            choice = getScan().nextLine();
        }
        WindDirection targetWind;
        if (choice.equalsIgnoreCase("NORTH")) {
            targetWind = WindDirection.NORTH;
        } else if (choice.equalsIgnoreCase("SOUTH")) {
            targetWind = WindDirection.SOUTH;
        } else if (choice.equalsIgnoreCase("EAST")) {
            targetWind = WindDirection.EAST;
        } else {
            targetWind = WindDirection.WEST;
        }
        Space adjacentSpace = getBoard().checkOrthogonallyAdjacent(targettedSpace, targetWind);
        if (!(getBoard().isValidFire(adjacentSpace))) {
            getBoard().obtainBoard()[adjacentSpace.getY()][adjacentSpace.getX()] = new Fire(adjacentSpace.getX(), adjacentSpace.getY());
            Space nextAdjacentSpace = getBoard().checkOrthogonallyAdjacent(targettedSpace, targetWind);
            if (!(getBoard().isValidFire(nextAdjacentSpace))) {
                getBoard().obtainBoard()[nextAdjacentSpace.getY()][nextAdjacentSpace.getX()] = new Fire(nextAdjacentSpace.getX(), nextAdjacentSpace.getY());
            }
        }
    }
}

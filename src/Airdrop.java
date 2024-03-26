import java.util.Scanner;

public class Airdrop extends WaterCard {
    public Airdrop(GameBoard board, Scanner scan) {
        super("Airdrop", board, scan);
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
        if (!(getBoard().isValidWater(adjacentSpace))) {
            getBoard().obtainBoard()[adjacentSpace.getY()][adjacentSpace.getX()] = new Space(adjacentSpace.getX(), adjacentSpace.getY());
            Space nextAdjacentSpace = getBoard().checkOrthogonallyAdjacent(targettedSpace, targetWind);
            if (!(getBoard().isValidWater(nextAdjacentSpace))) {
                getBoard().obtainBoard()[nextAdjacentSpace.getY()][nextAdjacentSpace.getX()] = new Space(nextAdjacentSpace.getX(), nextAdjacentSpace.getY());
            }
        }
    }
}

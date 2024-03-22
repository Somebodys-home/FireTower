import java.util.Scanner;

public class FlareUp extends FireCard {
    public FlareUp(GameBoard board, Scanner scan) {
        super("Flare Up", board, scan);
    }

    @Override
    public void cardEffect() {
        System.out.println("Select a space on the board that isn't part of the eternal flame or a fire tower area.");
        System.out.println("This space must build off of an existing fire space.");
        Space targettedSpace;
        do {
            targettedSpace = getBoard().getSpace(getScan());
        } while (targettedSpace instanceof Fire || targettedSpace instanceof Firebreak);
        getBoard().obtainBoard()[targettedSpace.getY()][targettedSpace.getX()] = new Fire(targettedSpace.getX(), targettedSpace.getY());
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
        if (!(adjacentSpace instanceof Fire) && !(adjacentSpace instanceof Firebreak)) {
            getBoard().obtainBoard()[adjacentSpace.getY()][adjacentSpace.getX()] = new Fire(adjacentSpace.getX(), adjacentSpace.getY());
            Space nextAdjacentSpace = getBoard().checkOrthogonallyAdjacent(targettedSpace, targetWind);
            if (!(nextAdjacentSpace instanceof Fire) && !(nextAdjacentSpace instanceof Firebreak)) {
                getBoard().obtainBoard()[nextAdjacentSpace.getY()][nextAdjacentSpace.getX()] = new Fire(nextAdjacentSpace.getX(), nextAdjacentSpace.getY());
            }
        }
    }
}

import java.util.Scanner;

public class BurningSnag extends FireCard {
    public BurningSnag(GameBoard board, Scanner scan) {
        super("Burning Snag", board, scan);
    }

    @Override
    public void cardEffect() {
        Space targettedSpace = initialStep();
        String choice = "";
        while (!choice.equals("NORTHWEST") && !choice.equals("NORTHEAST") && !choice.equals("SOUTHEAST") && !choice.equals("SOUTHWEST")) {
            System.out.print("In which direction would you like to spread the snag to(NORTHWEST, NORTHEAST, SOUTHEAST, SOUTHWEST)? ");
            choice = getScan().nextLine();
        }
        int[] offsetX = {-1, -1, 0, 1, 1, 1, 0, -1};   //offsets are set to whatever is desired
        int[] offsetY = {0, -1, -1, -1, 0, -1, -1, 1};
        int[] offsetValues = {0, 1, 2};                  //offsets for the offsets
        if (choice.equalsIgnoreCase("NORTHEAST")) {
            for (int i = 0; i < offsetValues.length; i++) {
                offsetValues[i] += 2;
            }
        } else if (choice.equalsIgnoreCase("SOUTHEAST")) {
            for (int i = 0; i < offsetValues.length; i++) {
                offsetValues[i] += 4;
            }
        } else if (choice.equalsIgnoreCase("SOUTHWEST")) {
            for (int i = 0; i < offsetValues.length; i++) {
                offsetValues[i] += 6;
                offsetValues[2] -= 8;  //otherwise index 8 is out of bounds when 2 + 6 is done
            }
        }
        for (int offsetValue : offsetValues) {
            int xCoord = targettedSpace.getX() + offsetX[offsetValue];
            int yCoord = targettedSpace.getY() + offsetY[offsetValue];
            if (getBoard().isValidFire(getBoard().obtainBoard()[yCoord][xCoord])) {
                getBoard().obtainBoard()[yCoord][xCoord] = new Fire(xCoord, yCoord);
            }
        }
    }
}

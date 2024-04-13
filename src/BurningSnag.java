import java.util.Scanner;

public class BurningSnag extends FireCard {
    public BurningSnag(GameBoard board, Scanner scan) {
        super("Burning Snag", board, scan);
    }

    @Override
    public void cardEffect() {
        System.out.println("Choose a valid space indicated below and choose an inter-cardinal direction to build three more fire tokens.");
        Space targettedSpace = initialStep();
        String choice = "";
        while (!choice.equals("NORTHWEST") && !choice.equals("NORTHEAST") && !choice.equals("SOUTHEAST") && !choice.equals("SOUTHWEST")) {
            System.out.print("In which direction would you like to spread the snag to(NORTHWEST, NORTHEAST, SOUTHEAST, SOUTHWEST)? ");
            choice = getScan().nextLine();
        }
        int[] offsetX = {-1, -1, 0, 1, 1, 1, 0, -1};   //offsets are set to whatever is desired
        int[] offsetY = {0, -1, -1, -1, 0, 1, 1, 1};
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
                if (offsetValues[i] >= offsetX.length) {
                    offsetValues[i] -= 8;  //otherwise index 8 is out of bounds when 2 + 6 is done
                }
            }
        }
        for (int offsetValue : offsetValues) {
            int xCoord = targettedSpace.getX() + offsetX[offsetValue];
            int yCoord = targettedSpace.getY() + offsetY[offsetValue];
            if (getBoard().isSpaceInBounds(xCoord, yCoord)) {
                getBoard().placeFire(getBoard().obtainBoard()[yCoord][xCoord]); //places fire
            }
        }
    }

    @Override
    public String[] cardDisplay() {
        String[] cardStrings =
                       {"╭───────╮",
                        "│Burning│",
                        "│ Snag  │",
                        "│       │",
                        "│ Fire  │",
                        "╰───────╯"};
        return cardStrings;
    }
}

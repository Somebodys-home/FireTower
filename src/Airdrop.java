import java.util.Scanner;

public class Airdrop extends WaterCard {
    public Airdrop(GameBoard board, Scanner scan) {
        super("Airdrop", board, scan);
    }

    @Override
    public void cardEffect() {
        System.out.println("Choose a space following the rule below. Then, indicate a direction. Afterward, the fire tokens will begone.");
        Space targettedSpace = initialStep();
        WindDirection targetWind = getBoard().chooseDirection(getScan());
        Space adjacentSpace = getBoard().checkOrthogonallyAdjacent(targettedSpace, targetWind);
        if (getBoard().isValidWaterPlacement(adjacentSpace)) {
            if (!(adjacentSpace instanceof Firebreak)) {
                getBoard().removeFire(adjacentSpace);
            }
            Space nextAdjacentSpace = getBoard().checkOrthogonallyAdjacent(adjacentSpace, targetWind);
            if (getBoard().isValidWaterPlacement(nextAdjacentSpace)) {
                if (!(nextAdjacentSpace instanceof Firebreak)) {
                    getBoard().removeFire(nextAdjacentSpace);
                }
            }
        }
    }

    @Override
    public String[] cardDisplay() {
        String[] cardStrings =
                       {"╭───────╮",
                        "│Airdrop│",
                        "│       │",
                        "│       │",
                        "│ Water │",
                        "╰───────╯"};
        return cardStrings;
    }
}

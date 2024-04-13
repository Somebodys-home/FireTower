import java.util.Scanner;

public class DozerLine extends FirebreakCard {
    public DozerLine(GameBoard board, Scanner scan) {
        super("Dozer Line", board, scan);
    }

    @Override
    public void cardEffect() {
        Space target = getSpace();
        while (!(getBoard().isValidFireBreakPlacement(target))) {
            System.out.println("Select a valid square!");
            target = getSpace();
        }
        getBoard().placeFireBreak(target);
        WindDirection windDir = getBoard().chooseDirection(getScan());
        Space adjacentSpace = getBoard().checkOrthogonallyAdjacent(target, windDir);
        getBoard().placeFireBreak(adjacentSpace);
    }

    @Override
    public String[] cardDisplay() {
        String[] cardStrings =
                       {"╭───────╮",
                        "│ Dozer │",
                        "│ Line  │",
                        "│ Fire- │",
                        "│ Break │",
                        "╰───────╯"};
        return cardStrings;
    }

}

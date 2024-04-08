import java.util.Scanner;

public class DeReforest extends FirebreakCard {
    public DeReforest(GameBoard board, Scanner scan) {
        super("De / Reforest", board, scan);
    }

    @Override
    public void cardEffect() {
        String answer;
        do {
            System.out.println("(de)forest or (re)forest?");
            answer = getScan().nextLine().toLowerCase();
        } while (!(answer.equals("de") || answer.equals("re")));

        if (answer.equals("de")) {
            Space targetToDeforest;
            do {
                targetToDeforest = getSpace();
            } while (!(targetToDeforest.getSpaceEmoji().equals("\uD83C\uDF32")));   //checks for a tree to deforest
            getBoard().obtainBoard()[targetToDeforest.getY()][targetToDeforest.getX()] = new Firebreak(targetToDeforest.getX(), targetToDeforest.getY());
        } else {
            Space targetToReforest;
            do {
                targetToReforest = getSpace();
            } while (!(targetToReforest instanceof Firebreak));   //checks for a tree to deforest
            getBoard().obtainBoard()[targetToReforest.getY()][targetToReforest.getX()] = new Space(targetToReforest.getX(), targetToReforest.getY());
        }
    }

    @Override
    public String[] cardDisplay() {
        String[] cardStrings =
                       {"╭───────╮",
                        "│ De/Re │",
                        "│ Forest│",
                        "│ Fire- │",
                        "│ Break │",
                        "╰───────╯"};
        return cardStrings;
    }
}

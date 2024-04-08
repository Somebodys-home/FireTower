import java.util.Scanner;

public class Explosion extends FireCard {
    public Explosion(GameBoard board, Scanner scan) {
        super("Explosion", board, scan);
    }

    @Override
    public void cardEffect() {
        System.out.println("Select a Fire Space(not the eternal flame) to change all surrounding squares into fire and your selected fire token into a firebreak token.");
        System.out.println("Other firebreak tokens and the eternal flame cannot be affected by this.");
        Space targettedSpace;
        do {
            targettedSpace = getBoard().getSpace(getScan());
        } while (getBoard().isValidFirePlacement(targettedSpace));
        getBoard().obtainBoard()[targettedSpace.getY()][targettedSpace.getX()] = new Firebreak(targettedSpace.getX(), targettedSpace.getY()); // firebreak
        int[] offsetX = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] offsetY = {0, -1, -1, -1, 0, 1, 1, 1};
        for (int i = 0; i < offsetY.length; i++) {
            if (targettedSpace.getX() + offsetX[i] >= 0 || targettedSpace.getX() + offsetX[i] < getBoard().obtainBoard().length || targettedSpace.getY() + offsetY[i] >= 0 || targettedSpace.getY() + offsetY[i] < getBoard().obtainBoard().length) {
                Space adjacentSpace = getBoard().obtainBoard()[targettedSpace.getY() + offsetX[i]][targettedSpace.getX() + offsetY[i]];
                if (getBoard().isValidFirePlacement(adjacentSpace)) {
                    getBoard().obtainBoard()[adjacentSpace.getY()][adjacentSpace.getX()] = new Fire(adjacentSpace.getX(), adjacentSpace.getY());
                }
            }
        }
    }

    @Override
    public String[] cardDisplay() {
        String[] cardStrings =
                       {"╭───────╮",
                        "│ Explo │",
                        "│ -sion │",
                        "│       │",
                        "│ Fire  │",
                        "╰───────╯"};
        return cardStrings;
    }
}

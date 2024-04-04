import java.util.Scanner;

public class Explosion extends FireCard {
    public Explosion(GameBoard board, Scanner scan) {
        super("Explosion", board, scan);
    }

    @Override
    public void cardEffect() {
        Space targettedSpace;
        do {
            targettedSpace = getBoard().getSpace(getScan());
        } while (!(getBoard().isValidFire(targettedSpace)));

        targettedSpace = new Firebreak(targettedSpace.getX(), targettedSpace.getY()); // firebreak
        int[] offsetX = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] offsetY = {0, -1, -1, -1, 0, -1, -1, 1};
        for (int i = 0; i < offsetY.length; i++) {
            if (targettedSpace.getX() + offsetX[i] >= 0 || targettedSpace.getX() + offsetX[i] < getBoard().obtainBoard().length || targettedSpace.getY() + offsetY[i] >= 0 || targettedSpace.getY() + offsetY[i] < getBoard().obtainBoard().length) {
                Space adjacentSpace = getBoard().obtainBoard()[targettedSpace.getY() + offsetX[i]][targettedSpace.getX() + offsetY[i]];
                if (getBoard().isValidFire(adjacentSpace)) {
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

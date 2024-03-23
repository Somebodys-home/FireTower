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
        } while (!(targettedSpace instanceof Fire));

        targettedSpace = new Firebreak(targettedSpace.getX(), targettedSpace.getY()); // firebreak
        int[] offsetX = {-1, 0, 1, 1, 1, 0, -1, -1};
        int[] offsetY = {-1, -1, -1, 0, 1, 1, 1, 0};
        for (int i = 0; i < offsetY.length; i++) {
            if (targettedSpace.getX() + offsetX[i] >= 0 || targettedSpace.getX() + offsetX[i] < getBoard().obtainBoard().length || targettedSpace.getY() + offsetY[i] >= 0 || targettedSpace.getY() + offsetY[i] < getBoard().obtainBoard().length) {
                Space adjacentSpace = getBoard().obtainBoard()[targettedSpace.getY() + offsetX[i]][targettedSpace.getX() + offsetY[i]];
                if (getBoard().isValidFire(adjacentSpace)) {
                    adjacentSpace = new Fire(adjacentSpace.getX(), adjacentSpace.getY());        //TODO: MARKED FOR REVIEW
                }
            }
        }
    }
}

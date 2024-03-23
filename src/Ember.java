import java.util.Scanner;

public class Ember extends FireCard {
    public Ember(GameBoard board, Scanner scan) {
        super("Ember", board, scan);
    }

    @Override
    public void cardEffect() {
        Space targettedSpace;
        do {
            targettedSpace = getBoard().getSpace(getScan());
        } while (!(targettedSpace instanceof Fire));

        Space ember;
        /*do {

        } while (ember instanceof )*/ //TODO: GABRIEL THIS FOR U
        System.out.println("What x coordinate do you want this fire space at?");
        int scanX = getScan().nextInt();
        System.out.println("What y coordinate do you want this fire space at?");
        int scanY = getScan().nextInt();
        targettedSpace = new Space("\uD83C\uDF32", targettedSpace.getX(), targettedSpace.getY());
        getBoard().obtainBoard()[scanX][scanY] = new Fire(scanX, scanY);
    }
}

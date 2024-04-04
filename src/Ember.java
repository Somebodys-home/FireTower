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
        do {
            ember = getBoard().getSpace(getScan());
        } while (!(getBoard().isValidFire(ember)));
        System.out.println("What x coordinate do you want this fire space at?");
        int scanX = getScan().nextInt();
        System.out.println("What y coordinate do you want this fire space at?");
        int scanY = getScan().nextInt();
        getBoard().obtainBoard()[targettedSpace.getY()][targettedSpace.getX()] = new Space("\uD83C\uDF32", targettedSpace.getX(), targettedSpace.getY());
        getBoard().obtainBoard()[scanY][scanX] = new Fire(scanX, scanY);
    }

    @Override
    public String[] cardDisplay() {
        String[] cardStrings =
                       {"╭───────╮",
                        "│ Ember │",
                        "│       │",
                        "│       │",
                        "│ Fire  │",
                        "╰───────╯"};
        return cardStrings;
    }
}

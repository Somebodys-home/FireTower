import java.util.Scanner;

public class Ember extends FireCard {
    public Ember(GameBoard board, Scanner scan) {
        super("Ember", board, scan);
    }

    @Override
    public void cardEffect() {
        Space ember;
        do {
            System.out.println("Choose a fire token to move.");
            ember = getBoard().getSpace(getScan());
        } while (!(ember instanceof Fire));
        getBoard().placeSpace(ember);
        Space targetSpace;
        do {
            System.out.println("Select a valid space to move the fire token to.");
            targetSpace = getSpace();
        } while (!getBoard().buildsOffFire(targetSpace));
        getBoard().placeFire(targetSpace);   //places fire
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

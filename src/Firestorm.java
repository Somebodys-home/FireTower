import java.util.Random;
import java.util.Scanner;

public class Firestorm extends EventCard {
    public Firestorm(GameBoard board, Card[] deck, Card[] discard, Player[] turnOrder, Scanner scan) {
        super("Firestorm", board, deck, discard, turnOrder, scan);
    }

    @Override
    public void cardEffect() {
        System.out.println("A firestorm has started! The fire is spreading in all directions.");

        // Get the current state of the board
        Space[][] boardState = getBoard().obtainBoard();

        // Create a copy of the board state to track changes
        Space[][] newBoardState = new Space[boardState.length][boardState[0].length];
        for (int i = 0; i < boardState.length; i++) {
            System.arraycopy(boardState[i], 0, newBoardState[i], 0, boardState[i].length);
        }

        // Iterate over the entire board
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                // If the current space is a Fire, spread it in all directions
                if (boardState[i][j] instanceof Fire) {
                    spreadFire(newBoardState, i, j);
                }
            }
        }

        System.out.println("The firestorm has ended.");
    }

    private void spreadFire(Space[][] boardState, int x, int y) {
        // Define the four directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Spread the fire in all four directions
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            // Check if the new coordinates are within the board
            if (newX >= 0 && newX < boardState.length && newY >= 0 && newY < boardState[0].length) {
                // If the new space is not a Fire or a Firebreak, turn it into a Fire
                if (!(boardState[newX][newY] instanceof Fire) && !(boardState[newX][newY] instanceof Firebreak)) {
                    boardState[newX][newY] = new Fire(newX, newY);
                }
            }
        }
    }

    public Space initialStep() {   //TODO: FIXING REQUIRED
        System.out.println("The storm itself closes in.");
        System.out.println("It has formed its own wind system in the form of a fire tornado.");
        Space targettedSpace;
        do {
            targettedSpace = getSpace();
        } while (!(getBoard().isValidWater(targettedSpace)));
        getBoard().obtainBoard()[targettedSpace.getY()][targettedSpace.getX()] = new Space(targettedSpace.getX(), targettedSpace.getY());
        return targettedSpace;
    }

    @Override
    public String[] cardDisplay() {
        String[] cardStrings =
                       {"╭───────╮",
                        "│ Fire- │",
                        "│ Storm │",
                        "│       │",
                        "│ Event │",
                        "╰───────╯"};
        return cardStrings;
    }
}

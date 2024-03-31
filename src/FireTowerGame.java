import java.util.ArrayList;
import java.util.Scanner;

public class FireTowerGame {
    private GameBoard board;
    private Player[] turnOrder;
    private Player currentPlayer;
    Scanner scan;

    public FireTowerGame() {
        board = new GameBoard();
        scan = new Scanner(System.in);
    }

    public void start() {
        board.initializeBoard();
        board.setWeatherVane();
        board.printBoard();
        System.out.println("How many players would like to play? ");
        int playerNum = 0;
        while (playerNum <= 1 || playerNum > 4) {
            System.out.println("Please select an amount between 2 and 4 inclusive.");
            playerNum = scan.nextInt();
            scan.nextLine();
        }
        turnOrder = new Player[playerNum];
        ArrayList<String> hearts = new ArrayList<String>();
        hearts.add("❤️");
        hearts.add("💜");
        hearts.add("💙");
        hearts.add("💚");
        String choice;
        do {
            System.out.println("The first player may choose from the available hearts to defend(copy paste the heart to choose it): " + hearts);
            choice = scan.nextLine();
        } while (!hearts.contains(choice));
        turnOrder[0] = new Player("...", board, new BucketCard(board, scan), 0, 0); //TODO: HIGHLIGHTED
        if (turnOrder.length == 2) {
            int otherChoice = hearts.indexOf(choice) + 2;
            if (otherChoice >= hearts.size()) {
                otherChoice -= hearts.size();
            }
            System.out.println("The other player must now defend " + hearts.get(otherChoice));
            turnOrder[1] = new Player("...", board, new BucketCard(board, scan), 0, 0); //TODO: HIGHLIGHTED
        } else {
            hearts.remove(choice);
            do {
                System.out.println("The second player may choose from the available hearts to defend(copy paste the heart to choose it): " + hearts);
                choice = scan.nextLine();
            } while (!hearts.contains(choice));
            turnOrder[1] = new Player("...", board, new BucketCard(board, scan), 0, 0); //TODO: HIGHLIGHTED
            hearts.remove(choice);
            do {
                System.out.println("The third player may choose from the available hearts to defend(copy paste the heart to choose it): " + hearts);
                choice = scan.nextLine();
            } while (!hearts.contains(choice));
            turnOrder[2] = new Player("...", board, new BucketCard(board, scan), 0, 0); //TODO: HIGHLIGHTED
            if (hearts.size() == 4) {
                hearts.remove(choice);
                System.out.println("The fourth player must now defend " + hearts.get(0));
                turnOrder[3] = new Player("...", board, new BucketCard(board, scan), 0, 0); //TODO: HIGHLIGHTED
            }
        }
    }

    //PRECONDITION: positionNum will always be between 1 and 3 inclusive
    private Player turnOrderPosition(int positionNum) {
        for (int i = 0; i < turnOrder.length; i++) {
            if (turnOrder[i] == currentPlayer) {
                if (i + positionNum >= turnOrder.length) {
                    i -= turnOrder.length;
                }
                return turnOrder[i + positionNum];
            }
        }
        return null;
    }

    //PRECONDITION: heart can only be one of the four emojis representing each tower's vulnerable square
    private void determinePlayerTowerArea(String heart) {
        if (heart.equals("\uD83D\uDC9C")) {
            //TODO: TO BE DONE BY ISFAR LATER
        }
    }

    public void prolouge() {
        String answer;
        do {
            System.out.println("Before we start, do all players know how to play Fire Tower? (y/n)");
            answer = scan.next();
        } while (!(answer.equals("y")) || !(answer.equals("n")));

        if (answer.equals("y")) {
            System.out.println("Good. Have fun burning alive!");
        } else {
            System.out.println("So you've already chosen where you want to start. That represents you in each of your respective towers.");
            System.out.println("At the start, the wind direction will be randomized, and each of you will get a hand of 5 cards and a bucket.");
            System.out.println("On your turn you must do 2 things: spread the fire and use your action, in that order.");
            System.out.println();
            System.out.println("SPREADING FIRE:");
            System.out.println("You must place another fire marker on any forest (tree) space that is orthogonally adjacent to another fire, following the wind direction.");
            System.out.println("Orthogonally adjacent means anything thats directly up, down, left or right to something.");
            System.out.println("For example: if the wind is blowing north, than you can spread the fire to any spot that's north of an already existing fire space.");
            System.out.println("The fire at the middle of the board is the eternal flame; that's where you start and it also can't be put out or blocked by any means.");
            System.out.println("More on that later.");
            System.out.println();
            System.out.println("USING YOUR ACTION:");
            System.out.println("Every turn, you can use your action to play one card from your hand.");
            System.out.println();
            System.out.println("There are 4 different card types:");
            System.out.println("Fire: Puts fire on the board.");
            System.out.println("Water: Extinguishes fire.");
            System.out.println("Firebreak: Puts a firebreak on the board, which fire can't spread on.");
            System.out.println("Wind: Changes the direction of the wind.");
            System.out.println();
        }
    }
}

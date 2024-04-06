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
        /*Card card = new Ember(board, scan);    FOR TESTING PURPOSES ONLY
        for (int i = 0; i < 6; i++) {
            System.out.println(card.cardDisplay()[i]);
        }*/
        board.setWeatherVane();
        board.printBoard();
        board.initalizeGameDeck(board, scan);
        selectPlayerTower();
        setupPlayerHands();
        System.out.println(board);
    }

    private void setupPlayerHands() {
        for (Player player : turnOrder) {
            player.addCardsToHand(board.getDeck(), 5);
        }

        playerTurn(turnOrder[0]); // testing only
    }

    private void selectPlayerTower() {
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

    // is the method for a singular player turn
    public void playerTurn(Player player) {
        int answer = -1;
        board.placeFireInWindDirection(scan);
        while (answer < 0 || answer > 4) {
            System.out.println("Type index of card you want to play (0 - 4)");
            answer = scan.nextInt();
            player.playCard(answer);
        }
    }
}

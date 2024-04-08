import java.util.ArrayList;
import java.util.Scanner;

public class FireTowerGame {
    private GameBoard board;
    private Player[] turnOrder;
    private Player currentPlayer;
    private int playerNum;
    Scanner scan;

    public FireTowerGame() {
        board = new GameBoard();
        scan = new Scanner(System.in);
    }

    public void start() {
        board.initializeBoard();
        board.setWeatherVane();
        board.printBoard();
        board.initalizeGameDeck(board, scan);

        System.out.println();
        selectPlayerTower();
        setupPlayerHands();
        gameplayLoop();
        System.out.println(board);
    }

    private void setupPlayerHands() {
        board.getDeck().shuffleDeck();
        for (Player player : turnOrder) {
            ArrayList<Card> cardsToDeal = board.getDeck().dealCards(5);
            player.addCardsToHand(cardsToDeal);
        }
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
        turnOrder[0] = new Player("Player 1", board, new BucketCard(board, scan), getHeartCoordinates(choice)[0], getHeartCoordinates(choice)[1]); //TODO: HIGHLIGHTED
        if (turnOrder.length == 2) {
            int otherChoice = hearts.indexOf(choice) + 2;
            if (otherChoice >= hearts.size()) {
                otherChoice -= hearts.size();
            }
            System.out.println("The other player must now defend " + hearts.get(otherChoice));
            turnOrder[1] = new Player("Player 2", board, new BucketCard(board, scan), getHeartCoordinates(hearts.get(otherChoice))[0], getHeartCoordinates(hearts.get(otherChoice))[1]); //TODO: HIGHLIGHTED
        } else {
            hearts.remove(choice);
            do {
                System.out.println("The second player may choose from the available hearts to defend(copy paste the heart to choose it): " + hearts);
                choice = scan.nextLine();
            } while (!hearts.contains(choice));
            turnOrder[1] = new Player("Player 2", board, new BucketCard(board, scan), getHeartCoordinates(choice)[0], getHeartCoordinates(choice)[1]); //TODO: HIGHLIGHTED
            hearts.remove(choice);
            do {
                System.out.println("The third player may choose from the available hearts to defend(copy paste the heart to choose it): " + hearts);
                choice = scan.nextLine();
            } while (!hearts.contains(choice));
            turnOrder[2] = new Player("Player 3", board, new BucketCard(board, scan), getHeartCoordinates(choice)[0], getHeartCoordinates(choice)[1]); //TODO: HIGHLIGHTED
            if (playerNum == 4) {
                hearts.remove(choice);
                System.out.println("The fourth player must now defend " + hearts.get(0));
                turnOrder[3] = new Player("Player 4", board, new BucketCard(board, scan), getHeartCoordinates(hearts.get(0))[0], getHeartCoordinates(hearts.get(0))[1]); //TODO: HIGHLIGHTED
            }
        }
        board.setWindDirection();
        System.out.println("Wind Direction: " + board.getWindDirection());
    }

    private int[] getHeartCoordinates(String heart) {
        int[] coordinates = new int[2];
        if ("❤️".equals(heart)) {
            coordinates[0] = 0; // X coordinate
            coordinates[1] = 0; // Y coordinate
        } else if ("💜".equals(heart)) {
            coordinates[0] = 15;
            coordinates[1] = 0;
        } else if ("💚".equals(heart)) {
            coordinates[0] = 0;
            coordinates[1] = 15;
        } else if ("💙".equals(heart)) {
            coordinates[0] = 15;
            coordinates[1] = 15;
        } else {
            coordinates[0] = -1;
            coordinates[1] = -1;
        }
        return coordinates;
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
        int answer;
        System.out.println(player.getName() + "'s turn");
        board.placeFireInWindDirection(scan);
        int numberOfCards = player.getCards().size();
        if (numberOfCards == 0) {
            setupPlayerHands();
        }
        player.printPlayerCards();
        System.out.println("Type index of card you want to play (0 - " + (numberOfCards - 1) + ")");
        answer = scan.nextInt();
        scan.nextLine();
        if (answer >= 0 && answer < numberOfCards) {
            player.playCard(answer);
        } else {
            System.out.println("Invalid card index. Please try again.");
        }
    }


    public void rotatePlayerTurns() {
        // Store the last element of the array
        Player last = turnOrder[turnOrder.length - 1];

        // Shift each element to the right by one position starting from the end
        for (int i = turnOrder.length - 1; i > 0; i--) {
            turnOrder[i] = turnOrder[i - 1];
        }

        // Place the last element at the beginning of the array
        turnOrder[0] = last;
    }

    public void gameplayLoop() {
        while (turnOrder.length > 1) {
            for (int i = 0; i < turnOrder.length; i++) {
                if (turnOrder[i].isBurned()) {
                    System.out.println(turnOrder[i].getName() + " has been eliminated!");
                    eliminatePlayer(i);
                    i--;
                }
            }

            // Proceed with the game if there are still players left
            if (turnOrder.length > 1) {
                Player currentPlayer = turnOrder[0];
                currentPlayer.getPlayerHand().getDeck().set(0, new DozerLine(board, scan));  //DELETE PLEASE ISFAR LATER
                currentPlayer.getPlayerHand().getDeck().set(1, new ScratchLine(board, scan));  //DELETE PLEASE ISFAR LATER
                playerTurn(currentPlayer);
                currentPlayer.addCardsToHand(board.getDeck().dealCards(currentPlayer.getPlayerHand().getDeck().size() - 5));
                rotatePlayerTurns();
            } else {
                System.out.println("Congratulations You have won!");
                break;
            }
        }
    }

    private void eliminatePlayer(int playerIndex) { //removes a player from the turn order
        Player[] newTurnOrder = new Player[turnOrder.length - 1];
        for (int i = 0, j = 0; i < turnOrder.length; i++) {
            if (i != playerIndex) {
                newTurnOrder[j++] = turnOrder[i];
            }
        }
        turnOrder = newTurnOrder;
    }
}

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class FireTowerGame {
    private GameBoard board;
    private Player[] turnOrder;
    private Player currentPlayer;
    Scanner scan;

    OutputWindow outputWindow = new OutputWindow();

    String[] cardTypes = {
            "Ember", "Explosion", "BurningSnag", "FlareUp", "Airdrop",
            "FireEngine", "SmokeJumper", "DozerLine", "SmokeJumper", "DeReforest"
    };




//    public void setDeck() {
//        Card card = new FireCard("test", board);
//        for (int i = 60; i > 0; i--) {
//            deck.add(Card);
//        }
//    }

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

        // After initializing players, deal 5 random cards to each player
        for (Player player : turnOrder) {
            for (int i = 0; i < 5; i++) {
                int cardIndex = new Random().nextInt(cardTypes.length);
                Card card = createCard(cardTypes[cardIndex]);
                player.addCardToHand(card);
            }
        }
        /player
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

    private Card createCard(String cardType) { //looks at cardType and picks out a random card
        Card card = null;
        if (cardType.equals("Ember")) {
            card = new Ember(board, scan);
        } else if (cardType.equals("Explosion")) {
            card = new Explosion(board, scan);
        } else if (cardType.equals("BurningSnag")) {
            card = new BurningSnag(board, scan);
        } else if (cardType.equals("FlareUp")) {
            card = new FlareUp(board, scan);
        } else if (cardType.equals("Airdrop")) {
            card = new Airdrop(board, scan);
        } else if (cardType.equals("FireEngine")) {
            card = new FireEngine(board, scan);
        } else if (cardType.equals("SmokeJumper")) {
            card = new SmokeJumper(board, scan);
        } else if (cardType.equals("DozerLine")) {
            card = new DozerLine(board, scan);
        } else if (cardType.equals("DeReforest")) {
            card = new DeReforest(board, scan);
        }
        return card;
    }

    //PRECONDITION: heart can only be one of the four emojis representing each tower's vulnerable square
    private void determinePlayerTowerArea(String heart) {
        if (heart.equals("\uD83D\uDC9C")) {
            //TODO: TO BE DONE BY ISFAR LATER
        }
    }
}

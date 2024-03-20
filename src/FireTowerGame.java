import java.util.Scanner;

public class FireTowerGame {
    GameBoard board = new GameBoard();
    Player[] players;
    Scanner scan = new Scanner(System.in);
//    public void setDeck() {
//        Card card = new FireCard("test", board);
//        for (int i = 60; i > 0; i--) {
//            deck.add(Card);
//        }
//    }
    public void start() {
        board.initializeBoard();
        board.setWeatherVane();
        board.printBoard();
        System.out.println("How many players would like to play? ");
        int playerNum = 0;
        while (playerNum <= 0 || playerNum > 4) {
            System.out.println("Please select an amount between 1 and 4 inclusive.");
            playerNum = scan.nextInt();
            scan.nextLine();
        }
        players = new Player[playerNum];
    }
}

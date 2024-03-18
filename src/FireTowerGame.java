import java.util.Scanner;

public class FireTowerGame {
    Board board = new Board();
    Player[] players;
    Scanner scan = new Scanner(System.in);
    // insert players
    // insert deck / discard here
    public void start() {
        board.setBoard();
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

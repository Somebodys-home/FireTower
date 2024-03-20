import java.util.Scanner;

public abstract class Card {
    private String name;
    private GameBoard board;
    private Scanner scan;
    public Card(String name, GameBoard board, Scanner scan) {
        this.name = name;
        this.board = board;
        this.scan = scan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scanner getScan() {
        return scan;
    }

    public GameBoard getBoard() {
        return board;
    }

    public abstract void cardEffect();
}

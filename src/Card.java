import java.util.Scanner;

public abstract class Card {
    private String name;
    private Board board;
    private Scanner scan;
    public Card(String name, Board board, Scanner scan) {
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

    public Board getBoard() {
        return board;
    }

    public abstract void cardEffect();
}

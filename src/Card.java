import java.util.Scanner;

public abstract class Card {
    private String name;
    private GameBoard board;
    private String cardColor;
    private Scanner scan;
    private int x;
    private int y;
    public Card(String name, GameBoard board, String cardColor, Scanner scan) {
        this.name = name;
        this.board = board;
        this.cardColor = cardColor;
        this.scan = scan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardColor() {
        return cardColor;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }

    public Scanner getScan() {
        return scan;
    }

    public GameBoard getBoard() {
        return board;
    }

    public abstract void cardEffect();

    public abstract Space initialStep();

    public Space getSpace() {
        System.out.println("Enter x coordinate:");
        x = scan.nextInt();
        System.out.println("Enter y coordinate:");
        y = scan.nextInt();

        return getBoard().obtainBoard()[y][x];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String[] cardDisplay() {
        String[] cardStrings =
               {"╭───────╮",
                "│ Empty │",
                "│       │",
                "│       │",
                "│ Card  │",
                "╰───────╯"};
        return cardStrings;
    }


}

import java.util.Scanner;

public class DeReforest extends FirebreakCard {
    public DeReforest(GameBoard board, Scanner scan) {
        super("De / Reforest", board, scan);
    }

    @Override
    public void cardEffect() {
        String answer;
        do {
            System.out.println("(de)forest or (re)forest?");
            answer = getScan().nextLine().toLowerCase();
        } while (!(answer.equals("de") || !(answer.equals("re"))));

        if (answer.equals("de")) {
            getBoard().board[getBoard().getSpace(getScan()).getX()][getBoard().getSpace(getScan()).getY()] = new Firebreak(getBoard().getSpace(getScan()).getX(), getBoard().getSpace(getScan()).getY());
        } else if (answer.equals("re")) {
            Space answer2;
            int scanX;
            int scanY;
            do {
                System.out.println("Enter x coordinate of firebreak you want removed:");
                scanX = getScan().nextInt();
                System.out.println("Enter y coordinate of firebreak you want removed:");
                scanY = getScan().nextInt();
                answer2 = getBoard().board[scanX][scanY];
            } while (!(answer2 instanceof Firebreak));

            getBoard().board[scanX][scanY] = new Space("\uD83D\uDC9B", scanX, scanY);
        }
    }
}

import java.util.Scanner;

public class DozerLine extends FirebreakCard {
    public DozerLine(GameBoard board, Scanner scan) {
        super("Dozer Line", board, scan);
    }

    @Override
    public void cardEffect() {
        Space space = getBoard().getSpace(getScan());
        int scanX = space.getX();
        Firebreak firebreak = new Firebreak(getBoard().getSpace(getScan()).getX(), getBoard().getSpace(getScan()).getY());

        String answer;
        do {
            System.out.println("Input a direction (N/NE/E/SE/S/SW/W/NW):");
            answer = getScan().nextLine();
        } while (!(answer.equals("N") || answer.equals("NE") || answer.equals("E") ||
                answer.equals("SE") || answer.equals("S") || answer.equals("SW") ||
                answer.equals("W") || answer.equals("NW")));

        if (answer.equals("N")) {
            getBoard().board
        }
    }

}

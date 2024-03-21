import java.util.Scanner;

public class MutualAid extends EventCard {
    public MutualAid(
            String name,
            GameBoard board,
            Card[] deck,
            Card[] discard,
            Player[] turnOrder,
            Scanner scan
    ) {
        super(name, board, deck, discard, turnOrder, scan);
    }

    @Override
    public void cardEffect() {

    }
}

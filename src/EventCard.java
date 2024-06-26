import java.util.Scanner;

public abstract class EventCard extends Card {
    private Card[] deck;
    private Card[] discard;
    private Player[] turnOrder;
    public EventCard(String name, GameBoard board, Card[] deck, Card[] discard, Player[] turnOrder, Scanner scan) {
        super(name, board, "\033[0;32m", scan);
        this.deck = deck;
        this.discard = discard;
        this.turnOrder = turnOrder;
    }

    public Card[] getDeck() {
        return deck;
    }

    public Card[] getDiscard() {
        return discard;
    }

    public Player[] getTurnOrder() {
        return turnOrder;
    }

    public void setDiscard(Card[] discard) {
        this.discard = discard;
    }

    @Override
    public abstract void cardEffect();
}

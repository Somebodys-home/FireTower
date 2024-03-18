public abstract class EventCard extends Card {
    private Card[] deck;
    private Card[] discard;
    private Player[] turnOrder;
    public EventCard(String name, Board board, Card[] deck, Card[] discard, Player[] turnOrder) {
        super(name, board);
        this.deck = deck;
        this.discard = discard;
        this.turnOrder = turnOrder;
    }

    @Override
    public abstract void cardEffect();
}

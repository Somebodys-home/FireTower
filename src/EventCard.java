public class EventCard extends Card {
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
    public void cardEffect() {
        return;                   //use turn order to obtain hand
    }
}

public class EventCard extends Card {
    private Card[] deck;
    private Card[] discard;
    public EventCard(String name, Space[][] board, Card[] deck, Card[] discard) {
        super(name, board);
        this.deck = deck;
        this.discard = discard;
    }

    @Override
    public void cardEffect() {
        return;                   //TODO: REVISIT
    }

    public void cardEffect(Card[] hand, Card[] deck, Card[] discard) {

    }
}

public abstract class Card {
    private String name;
    public Card(String name) {
        this.name = name;
    }

    public abstract void cardEffect(Card[] hand, Card[] deck, Card[] discard, Space[] board);
}

public abstract class Card {
    private String name;
    private String[][] board;
    public Card(String name, String[][] board) {
        this.name = name;
        this.board = board;
    }

    public abstract void cardEffect(Card[] hand, Card[] deck, Card[] discard, Space[][] board);
}

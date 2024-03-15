public abstract class Card {
    private String name;
    private Space[][] board;
    public Card(String name, Space[][] board) {
        this.name = name;
        this.board = board;
    }

    public abstract void cardEffect();
}

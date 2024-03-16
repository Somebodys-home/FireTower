public abstract class Card {
    private String name;
    private Board board;
    public Card(String name, Board board) {
        this.name = name;
        this.board = board;
    }
    public Board getBoard() {
        return board;
    }

    public abstract void cardEffect();
}

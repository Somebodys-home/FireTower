public abstract class FirebreakCard extends Card {
    public FirebreakCard(String name, GameBoard board) {
        super(name, board);
    }

    @Override
    public abstract void cardEffect();
}

public abstract class WaterCard extends Card {
    public WaterCard(String name, GameBoard board) {
        super(name, board);
    }

    @Override
    public abstract void cardEffect();
}

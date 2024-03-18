public abstract class WaterCard extends Card {
    public WaterCard(String name, Board board) {
        super(name, board);
    }

    @Override
    public abstract void cardEffect();
}

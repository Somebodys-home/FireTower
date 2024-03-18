public class BucketCard extends Card {
    private boolean hasWater;
    public BucketCard(String name, Board board) {
        super(name, board);
        hasWater = true;
    }

    @Override
    public void cardEffect() {

    }
}

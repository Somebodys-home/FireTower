public class WindCard extends Card {
    private WindDirection windDirection;

    public WindCard(String name, WindDirection direction, Board board) {
        super(name, board);
        windDirection = direction;
    }


    @Override
    public void cardEffect() {

    }
}

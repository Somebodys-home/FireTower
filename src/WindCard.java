public class WindCard extends Card {
    private WindDirection windDirection;

    public WindCard(String name, WindDirection direction) {
        super(name);
        windDirection = direction;
    }

    @Override
    public void cardEffect(Card[] hand, Card[] deck, Card[] discard, Space[] board) {

    }


}

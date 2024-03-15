public class Player {
    Board board;
    BucketCard bucketCard;
    boolean burned;
    Card[] playerHand;
    public Player() {
        playerHand = new Card[5];
        // thats on u buddy >:)
        burned = false;
    }
}

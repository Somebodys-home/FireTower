public class Player {
    String name;
    GameBoard board;
    BucketCard bucketCard;
    boolean burned;
    Card[] playerHand;
    public Player(String name, GameBoard board, BucketCard bucket) {
        this.name = name;
        this.board = board;
        playerHand = new Card[5];
        bucketCard = bucket;
        burned = false;
    }

    public String getName() {
        return name;
    }
}

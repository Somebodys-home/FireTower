public class Player {
    private String name;
    private GameBoard board;
    private BucketCard bucketCard;
    private boolean burned;
    private Card[] playerHand;
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

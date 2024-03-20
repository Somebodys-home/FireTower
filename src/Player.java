import java.util.ArrayList;

public class Player {
    String name;
    Board board;
    BucketCard bucketCard;
    boolean burned;
    Card[] playerHand;
    public Player(String name, Board board, BucketCard bucketCard) {
        this.name = name;
        this.board = board;
        playerHand = new Card[5];
        this.bucketCard = bucketCard;
        burned = false;
    }

    public String getName() {
        return name;
    }
}

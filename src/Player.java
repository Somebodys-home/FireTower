public class Player {
    private String name;
    private GameBoard board;
    private BucketCard bucketCard;
    private boolean burned;
    private Card[] playerHand;
    private Space[][] towerArea;
    public Player(String name, GameBoard board, BucketCard bucket, int towerAreaX, int towerAreaY) {
        this.name = name;
        this.board = board;
        playerHand = new Card[5];
        bucketCard = bucket;
        burned = false;
        towerArea = new Space[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                towerArea[j][i] = board.obtainBoard()[towerAreaY + j][towerAreaX + i];
            }
        }
    }

    public String getName() {
        return name;
    }
}

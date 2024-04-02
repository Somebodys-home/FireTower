import java.util.Random;
public class Player {
    private String name;
    private GameBoard board;
    private BucketCard bucketCard;
    private boolean burned;
    private Deck playerHand;
    private Space[][] towerArea;
    public Player(String name, GameBoard board, BucketCard bucket, int towerAreaX, int towerAreaY) {
        this.name = name;
        this.board = board;
        playerHand = new Deck();
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

    // add a card to the player's hand
    public void addCardToHand(Card card) {
        for (int i = 0; i < this.playerHand.length; i++) {
            if (this.playerHand[i] == null) {
                this.playerHand[i] = card;
                break;
            }
        }
    }

    //randomly assign five cards to the player at the start of the game
    public void assignStartingHand(Card[] deck) {
        Random rand = new Random();
        for (int i = 0; i < this.playerHand.length; i++) {
            int cardIndex = rand.nextInt(deck.length);
            this.playerHand[i] = deck[cardIndex];
        }
    }
}

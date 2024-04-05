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

    public void playCard(int idxOfCard) {
        playerHand.getDeck().get(idxOfCard).cardEffect();
    }

    public void crispy() {

    }

    //add a card to the player's hand
    public void addCardToHand(Card card) {
        for (int i = 0; i < playerHand.getDeck().size(); i++) {
            if (playerHand.getDeck().get(i) == null) {
                playerHand.getDeck().set(i, card);
                break;
            }
        }
    }

    // PRE-CONDITION: count <= 5
    public void addCardsToHand(Deck deck, int count) {
        while (count != 0) {
            for (int i = 0; i < playerHand.getDeck().size(); i++) {
                if (playerHand.getDeck().get(i) == null) {
                    playerHand.getDeck().set(i, deck.getDeck().get(0));
                    count--;
                }
            }
        }

    }
}

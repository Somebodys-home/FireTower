import java.util.Random;
import java.util.ArrayList;
public class Player {
    private String name;
    private GameBoard board;
    private BucketCard bucketCard;
    private boolean canUseBucket;
    private boolean burned;
    private Deck playerHand;
    private Space[][] towerArea;
    private int towerAreaX;
    private int towerAreaY;
    private Space heart;

    public Player(String name, GameBoard board, BucketCard bucket, int heartCoordinatesX, int heartCoordinatesY, int towerAreaX, int towerAreaY) {
        this.name = name;
        this.board = board;
        this.towerAreaX = towerAreaX;
        this.towerAreaY = towerAreaY;
        heart = board.obtainBoard()[heartCoordinatesY][heartCoordinatesX];
        playerHand = new Deck();
        bucketCard = bucket;
        burned = false;
        towerArea = new Space[3][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                towerArea[i][j] = board.obtainBoard()[towerAreaY][towerAreaX];
            }
        }
        canUseBucket = false;
    }

    public int getTowerAreaX() {
        return towerAreaX;
    }
    public int getTowerAreaY() {
        return towerAreaY;
    }

    public boolean isBurned() {
        Space towerSpace = board.obtainBoard()[towerAreaY][towerAreaX];
        return towerSpace instanceof Fire || towerSpace instanceof EternalFlame;
    }


    public void printPlayerCards() {
        ArrayList<Card> hand = playerHand.getDeck();
        for (int i = 0; i < 6; i++) {
            for (Card card : hand) {
                System.out.print(card.getCardColor());
                System.out.print(card.cardDisplay()[i]);
                System.out.print(Colors.RESET);
            }
            System.out.print(Colors.BLACK);
            System.out.print("|");
            System.out.print(bucketCard.getCardColor());
            System.out.print(bucketCard.cardDisplay()[i]);
            System.out.println(Colors.RESET);
        }
    }

    public String getName() {
        return name;
    }

    public void playCard(int idxOfCard) {
        if (idxOfCard >= 0 && idxOfCard < this.playerHand.getDeck().size()) {
            Card cardToPlay = this.playerHand.getDeck().get(idxOfCard);
            cardToPlay.cardEffect(); // Execute the card's effect
            this.playerHand.getDeck().remove(idxOfCard); // Remove the card from the hand
        } else {
            System.out.println("Invalid card index.");
        }
    }


    public void crispified() {
        System.out.printf("As " + name + " would've said: ashes to ashes, dust to dust.");
        burned = true;
    }

    //add a card to the player's hand
    public void addCardsToHand(ArrayList<Card> newCards) {
        for (Card card : newCards) {
            playerHand.getDeck().add(card);
        }
    }

    public ArrayList<Card> getCards() {
        return playerHand.getDeck();
    }

    public Deck getPlayerHand() {
        return playerHand;
    }

    public void checkFireInvasion() {
        boolean hasFire = false;
        for (int i = 0; i < towerArea.length; i++) {
            for (int j = 0; j < towerArea[0].length; j++) {
                if (towerArea[i][j].getSpaceEmoji().equals("\uD83D\uDD25")) {
                    canUseBucket = true;
                    hasFire = true;
                    if (towerArea[i][j] instanceof WinCondition) {
                        burned = true;
                    }
                }
            }
        }
        if (!hasFire) {
            canUseBucket = false;
        }
    }
    public void emptyBucket() {
        bucketCard.cardEffect();
        bucketCard.emptyWater();
    }
}

import java.util.Random;
import java.util.ArrayList;
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


    public void crispy() {

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



    // PRE-CONDITION: count <= 5
    public void addCardsToHand(Deck deck, int count) {
        if (count > 0) {
            for (int i = 0; i < playerHand.getDeck().size(); i++) {
                if (playerHand.getDeck().get(i) == null) {
                    playerHand.getDeck().set(i, deck.getDeck().remove(0));
                    count--;
                }
            }
        }
    }

    public boolean isBurned() {
        return burned;
    }
}

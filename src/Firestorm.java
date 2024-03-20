import java.util.Random;
public class Firestorm extends EventCard {
    public Firestorm(GameBoard board, Card[] deck, Card[] discard, Player[] turnOrder) {
        super("Fire Storm", board, deck, discard, turnOrder);
    }

    @Override
    public void cardEffect() {
        System.out.println("A Firestorm event has occurred!");

        // Step 1: Discard the Firestorm card and draw back up to hand size
        discardCard();

        // Step 2: Roll the die for the firestorm wind direction
        WindDirection firestormWindDirection = rollForNewDirection();

        // Step 3: Place a fire gem on every empty space orthogonally adjacent to every fire gem on the board
        // and the eternal flame in the firestorm wind direction
        placeFireGemsInWindDirection(firestormWindDirection);

        // Step 4: Roll again for a new wind direction
        WindDirection newWindDirection = rollForNewDirection();

        // Step 5: Each player may discard and draw cards
        for (Player player : getTurnOrder()) {
            playerDiscardAndDraw(player);
        }

        // Step 6: Resume normal play with the new wind direction
        System.out.println("Resuming normal play with the new wind direction: " + newWindDirection);
        getBoard().setWindDirection(newWindDirection);
    }

    private void discardCard() {
        getDiscard().add(this);    //TODO: ATIF WHERE IS THE ADD METHOD
    }

    private WindDirection rollForNewDirection() {
        Random random = new Random();
        return WindDirection.values()[random.nextInt(WindDirection.values().length)];
    }

    private void placeFireGemsInWindDirection(WindDirection windDirection) {
        for (int i = 0; i < getBoard().obtainBoard().length; i++) {
            for (int j = 0; j < getBoard().obtainBoard()[0].length; j++) {
                if (getBoard().obtainBoard()[i][j] instanceof Fire) {
                    // Place a fire gem on every empty space orthogonally adjacent to the current fire gem
                    getBoard().placeFireGemInWindDirection();
                }
            }
        }
    }

    private void playerDiscardAndDraw(Player player) {
        System.out.println(player.getName() + ", it's your turn to discard and draw cards.");
        // Implement player's ability to discard and draw cards here so need player class
    }
}

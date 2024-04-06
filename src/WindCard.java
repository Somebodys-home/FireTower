import java.util.Scanner;

public class WindCard extends Card {
    private WindDirection windDirection;

    public WindCard(String name, WindDirection direction, GameBoard board, Scanner scan) {
        super(name, board, scan);
        windDirection = direction;
    }


    @Override
    public void cardEffect() {
        GameBoard board = getBoard();
        WindDirection currentDirection = board.getWindDirection();
        System.out.println();
        int option = (int) (Math.random() * 3) + 1;

        // Perform action based on the chosen option using if statements
        if (option == 1) {
            // Change the wind direction to the direction indicated on the card
            board.setWindDirection();
            System.out.println("The wind direction has been changed to: " + windDirection);
        } else if (option == 2) {
            // Roll the die for a new wind direction
            WindDirection newDirection = rollForNewDirection(currentDirection);
            board.setWindDirection(newDirection);
            System.out.println("The wind direction has been changed to: " + newDirection);
        } else if (option == 3) {
            // Place a fire gem on an empty space orthogonally adjacent to a fire gem or the eternal flame
            // in the wind direction indicated on the card
        } else {
            System.out.println("Invalid option");
        }
    }

    private WindDirection rollForNewDirection(WindDirection currentDirection) {
        WindDirection newDirection = currentDirection;
        int direction = (int) (Math.random() * 4);       //TODO: POSSIBLE RE-ROLL?

        // Ensure the new direction is different from the current direction
        if (direction == 0 && currentDirection != WindDirection.NORTH) {
            newDirection = WindDirection.NORTH;
        } else if (direction == 1 && currentDirection != WindDirection.SOUTH) {
            newDirection = WindDirection.SOUTH;
        } else if (direction == 2 && currentDirection != WindDirection.EAST) {
            newDirection = WindDirection.EAST;
        } else if (direction == 3 && currentDirection != WindDirection.WEST) {
            newDirection = WindDirection.WEST;
        }

        return newDirection;
    }

    public Space initialStep() {
        System.out.println("The wind blows in the direction it desires.");
        System.out.println("THE " + windDirection + "ERN WINDS RANGE ON!!!!");
        return null;
    }
}
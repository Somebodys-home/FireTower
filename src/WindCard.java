import java.util.Scanner;

public class WindCard extends Card {
    private WindDirection windDirection;

    public WindCard(String name, WindDirection direction, GameBoard board, Scanner scan) {
        super(name, board, "\033[0;37m", scan);
        windDirection = direction;
    }


    @Override
    public void cardEffect() {
        GameBoard board = getBoard();
        WindDirection currentDirection = board.getWindDirection();
        System.out.println();
        System.out.println("Pick one of the options:");
        System.out.println("1-Change the direction to " + windDirection + ".");
        System.out.println("2-Change the direction to a random direction.");
        System.out.println("3-Spread the fire toward the " + windDirection + ".");
        int option = 0;
        while (option < 1 || option > 3) {
            System.out.print("Make your selection: ");
            option = getScan().nextInt();
            getScan().nextLine();
            if (option < 1 || option > 3) {
                System.out.println("Invalid option.");
            }
        }

        // Perform action based on the chosen option using if statements
        if (option == 1) {
            // Change the wind direction to the direction indicated on the card
            board.setWindDirection(windDirection);
        } else if (option == 2) {
            // Roll the die for a new wind direction
            board.setWindDirection();
        } else {
            // Place a fire gem on an empty space orthogonally adjacent to a fire gem or the eternal flame
            // in the wind direction indicated on the card
            board.placeFireInAnyDirection(getScan(), windDirection);
        }
    }

    private WindDirection rollForNewDirection(WindDirection currentDirection) {
        WindDirection newDirection = currentDirection;
        while (newDirection == currentDirection) {
            int direction = (int) (Math.random() * 4);       //TODO: POSSIBLE RE-ROLL?

            // Ensure the new direction is different from the current direction
            if (direction == 0) {
                newDirection = WindDirection.NORTH;
            } else if (direction == 1) {
                newDirection = WindDirection.SOUTH;
            } else if (direction == 2) {
                newDirection = WindDirection.EAST;
            } else if (direction == 3) {
                newDirection = WindDirection.WEST;
            }
        }

        return newDirection;
    }

    public Space initialStep() {
        System.out.println("The wind blows in the direction it desires.");
        System.out.println("THE " + windDirection + "ERN WINDS RANGE ON!!!!");
        return null;
    }

    @Override
    public String[] cardDisplay() {
        String[] cardStrings;
        if (windDirection == WindDirection.NORTH) {
            cardStrings = new String[] {"╭───────╮",
                                        "│ North │",
                                        "│       │",
                                        "│       │",
                                        "│ Wind  │",
                                        "╰───────╯"};
        } else if (windDirection == WindDirection.SOUTH) {
            cardStrings = new String[] {"╭───────╮",
                                        "│ South │",
                                        "│       │",
                                        "│       │",
                                        "│ Wind  │",
                                        "╰───────╯"};
        } else if (windDirection == WindDirection.EAST) {
            cardStrings = new String[] {"╭───────╮",
                                        "│ East  │",
                                        "│       │",
                                        "│       │",
                                        "│  Wind │",
                                        "╰───────╯"};
        } else {
            cardStrings = new String[] {"╭───────╮",
                                        "│ West  │",
                                        "│       │",
                                        "│       │",
                                        "│  Wind │",
                                        "╰───────╯"};
        }
        return cardStrings;
    }
}
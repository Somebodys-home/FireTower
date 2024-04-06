import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {
    private Space[][] board;
    private String[][] weatherVane;
    private WindDirection windDirection;
    private Deck deck;
    private ArrayList<Card> discard = new ArrayList<>();

    public GameBoard() {
        board = new Space[16][16];
        weatherVane = new String[4][4];
        deck = new Deck();
    }

    public Deck getDeck() {
        return deck;
    }

    public Space[][] obtainBoard() {
        return board;
    }

    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Space("\uD83C\uDF32", j, i);
            }
        }
        board[7][7] = new EternalFlame(7, 7);
        board[8][7] = new EternalFlame(8,  7);
        board[7][8] = new EternalFlame(7, 8);
        board[8][8] = new EternalFlame(8, 8);

        int[] x = {0, 0, 13, 13};
        int[] y = {0, 13, 0, 13};
        int[] xWin = {0, 15, 0, 15};
        int[] yWin = {0, 0, 15, 15};
        String[] hearts = {"❤\uFE0F", "\uD83D\uDC9A", "\uD83D\uDC9C", "\uD83D\uDC99"};
        // top left
        for (int h = 0; h < 4; h++) {
            for (int i = y[h]; i < y[h] + 3; i++) {
                for (int j = x[h]; j < x[h] + 3; j++) {
                    if (i == xWin[h] && j == yWin[h]) {
                        board[i][j] = new WinCondition(hearts[h], j, i); //NOTE: i refers to y since it dictates rows which is y-axis
                    }  else {
                        board[i][j] = new FireTower(j, i);        //NOTE: j refers to x since it dictates columns which is x-axis
                    }
                }
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 10; i++) {
            System.out.print("0\uFE0F⃣");
        }
        for (int i = 0; i < 6; i++) {
            System.out.print("1\uFE0F⃣");
        }                                        //TODO: MAKE IT LOOK BETTER
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "\uFE0F⃣");
        }
        for (int i = 0; i < 6; i++) {
            System.out.print(i + "\uFE0F⃣");
        }
        System.out.println();
        for (int i = 0; i < 16; i++) {
            for (Space space1 : board[i]) {
                System.out.print(space1.getSpaceEmoji());
            }
            if (i < 10) {
                System.out.print("0\uFE0F⃣");
            } else {
                System.out.print("1\uFE0F⃣");
            }
            System.out.print((i % 10) + "\uFE0F⃣");
            if (i >= 6 && i  <= 9) {
                int j = i - 6;
                System.out.print("       ");
                for (int k = 0; k < weatherVane[j].length; k++) {
                    System.out.print(weatherVane[j][k]);
                }
            }
            System.out.println();
        }

    }

    public void setWeatherVane() {
        weatherVane[0][0] = "\uD83C\uDF43";
        weatherVane[0][3] = "\uD83C\uDF41";
        weatherVane[3][0] = "\uD83C\uDF3F";
        weatherVane[3][3] = "\uD83C\uDF42";

        weatherVane[0][1] = "\uD83D\uDFE6";
        weatherVane[0][2] = "\uD83D\uDFE6";
        weatherVane[1][0] = "\uD83D\uDFE6";
        weatherVane[2][0] = "\uD83D\uDFE6";
        weatherVane[1][3] = "\uD83D\uDFE6";
        weatherVane[2][3] = "\uD83D\uDFE6";
        weatherVane[3][1] = "\uD83D\uDFE6";
        weatherVane[3][2] = "\uD83D\uDFE6";

        weatherVane[1][1] = "\uD83D\uDCA5";
        weatherVane[1][2] = "\uD83D\uDCA5";
        weatherVane[2][1] = "\uD83D\uDCA5";
        weatherVane[2][2] = "\uD83D\uDCA5";
    }

    public WindDirection getWindDirection() {
        return windDirection;
    }

    public void setWindDirection() {
        int direction = (int) (Math.random() * 4);
        if (direction == 0) {
            windDirection = WindDirection.NORTH;
        } else if (direction == 1) {
            windDirection = WindDirection.SOUTH;
        } else if (direction == 2) {
            windDirection = WindDirection.EAST;
        } else if (direction == 3) {
            windDirection = WindDirection.WEST;
        }
    }

    public void setWindDirection(WindDirection direction) {
        if (direction == WindDirection.NORTH) {
            windDirection = WindDirection.NORTH;
        } else if (direction == WindDirection.SOUTH) {
            windDirection = WindDirection.SOUTH;
        } else if (direction == WindDirection.EAST) {
            windDirection = WindDirection.EAST;
        } else if (direction == WindDirection.WEST) {
            windDirection = WindDirection.WEST;
        }
    }

    public Space checkOrthogonallyAdjacent(Space space) {
        return checkOrthogonallyAdjacent(space, windDirection);
    }

    public Space checkOrthogonallyAdjacent(Space space, WindDirection direction) {
        int rowOffset = 0;
        int colOffset = 0;
        System.out.println(windDirection == WindDirection.NORTH);
        System.out.println(windDirection);
        if (windDirection == WindDirection.NORTH) {
            rowOffset = -1;
        } else if (windDirection == WindDirection.SOUTH) {
            rowOffset = 1;
        } else if (windDirection == WindDirection.EAST) {
            colOffset = 1;
        } else if (windDirection == WindDirection.WEST) {
            colOffset = -1;
        }
        int newRow = space.getY() + rowOffset;
        int newCol = space.getX() + colOffset;
        if (isValidPosition(board[newRow][newCol])) {
            if (space == board[newRow][newCol]) {
                System.out.println("cooooooooooooooooooooooooooooooool");
            }
            return board[newRow][newCol];
        }
        return null;
    }

    public void placeFireInWindDirection(Scanner scan) {   //does step 1 of a turn
        ArrayList<Space> validFire = new ArrayList<Space>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (isValidFire(board[j][i])) {
                    validFire.add(board[j][i]);
                }
            }
        }
        System.out.println( " TTTTTTTTTTTTTTTT: " + validFire.get(0));
        for (Space eachValidFire : validFire) {
            Space targetSpace = checkOrthogonallyAdjacent(board[eachValidFire.getY()][eachValidFire.getX()], windDirection);
            System.out.println(eachValidFire);
            System.out.println(targetSpace);
            board[targetSpace.getY()][targetSpace.getX()].setSpaceEmoji("⭕");  //still a tree
        }
        printBoard();
        selectFirePlacement(scan);
        printBoard(); //MAYBE DELETE LATER
    }

    public void placeFireAnyWhere(Scanner scan) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                WindDirection[] winds = new WindDirection[]{WindDirection.NORTH, WindDirection.EAST, WindDirection.SOUTH, WindDirection.WEST};
                for (int k = 0; k < winds.length; k++) {
                    Space targetSpace = checkOrthogonallyAdjacent(board[j][i], windDirection);
                    if (targetSpace != null && isValidFire(targetSpace)) {
                        board[targetSpace.getY()][targetSpace.getX()].setSpaceEmoji("⭕");  //still a tree
                    }
                }
            }
        }
        selectFirePlacement(scan);
    }

    private void selectFirePlacement(Scanner scan) {
        printBoard();
        Space selectedSpace = new Space("", -1, -1);
        while (!(selectedSpace.getSpaceEmoji().equals("⭕"))) {
            System.out.println("Please select a space with a \"⭕\" to spread the fire to.");
            selectedSpace = getSpace(scan);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[j][i] != selectedSpace) {
                    //System.out.println(board[j][i].getSpaceEmoji().equals("⭕"));
                }
                if (board[j][i].getSpaceEmoji().equals("⭕") && board[j][i] != selectedSpace) {
                    selectedSpace.setSpaceEmoji("\uD83C\uDF32"); //goes back to displaying a tree
                } else if (board[j][i] == selectedSpace) {
                    selectedSpace = new Fire(selectedSpace.getX(), selectedSpace.getY());  //evolves into fire
                }
            }
        }
    }
    
    public void placeFireGemInWindDirection() {
        // Get the current wind direction from the board
        WindDirection windDirection = getWindDirection();

        // Define the row and column offsets based on the wind direction
        int rowOffset = 0;
        int colOffset = 0;
        if (windDirection == WindDirection.NORTH) {
            rowOffset = -1;
        } else if (windDirection == WindDirection.SOUTH) {
            rowOffset = 1;
        } else if (windDirection == WindDirection.EAST) {
            colOffset = 1;
        } else if (windDirection == WindDirection.WEST) {
            colOffset = -1;
        }

        // Iterate through the board to find fire spaces
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] instanceof Fire) {
                    // Calculate the adjacent position
                    int newRow = i + rowOffset;
                    int newCol = j + colOffset;
                    // Check if the adjacent position is valid and empty
                    if (isValidPosition(board[newRow][newCol]) && board[newRow][newCol].getSpaceEmoji().equals("\uD83C\uDF32")) {
                        // Place the fire symbol and return
                        board[newRow][newCol] = new Space("\uD83D\uDD25", newCol, newRow); // should be fire symbol
                        System.out.println("Placed a fire gem in the wind direction.");
                        return;
                    }
                }
            }
        }

        // If no empty adjacent space was found, print a message
        System.out.println("No empty adjacent space found to place the fire gem.");
    }

    private boolean isValidPosition(Space focus) {
        return focus.getY() >= 0 && focus.getY() < board.length && focus.getX() >= 0 && focus.getX() < board[0].length;
    }

    public boolean isValidFire(Space focus) {
        return focus instanceof Fire || focus instanceof EternalFlame;
        //return isValidFireBreak(focus) && !(focus instanceof EternalFlame);
    }

    public boolean isValidFireBreak(Space focus) {
        WindDirection[] directions = new WindDirection[]{WindDirection.NORTH, WindDirection.WEST, WindDirection.SOUTH, WindDirection.EAST};
        for (WindDirection windDir: directions) {
            if (checkOrthogonallyAdjacent(focus, windDir) instanceof Firebreak) {
                return false;
            }
        }
        return !(focus instanceof Firebreak);
    }
    public boolean isValidWater(Space focus) {
        return !(focus instanceof FireTower) && !(focus instanceof EternalFlame);
    }

    public Space getSpace(Scanner scan) {
        System.out.println("Enter x coordinate:");
        int scanX = scan.nextInt();
        System.out.println("Enter y coordinate:");
        int scanY = scan.nextInt();

        return obtainBoard()[scanX][scanY];
    }

    public WindDirection chooseDirection(Scanner scan) {
        String choice = "";
        while (!choice.equals("NORTH") && !choice.equals("EAST") && !choice.equals("SOUTH") && !choice.equals("WEST")) {
            System.out.print("In which direction would you like to build your flare in(NORTH, EAST, SOUTH, WEST)? ");
            choice = scan.nextLine();
        }
        WindDirection targetWind;
        if (choice.equalsIgnoreCase("NORTH")) {
            targetWind = WindDirection.NORTH;
        } else if (choice.equalsIgnoreCase("SOUTH")) {
            targetWind = WindDirection.SOUTH;
        } else if (choice.equalsIgnoreCase("EAST")) {
            targetWind = WindDirection.EAST;
        } else {
            targetWind = WindDirection.WEST;
        }
        return targetWind;
    }

    public void initalizeGameDeck(GameBoard board, Scanner scanner){
        addCard(new Ember(board, scanner), 2);
        addCard(new Explosion(board, scanner), 4);
        addCard(new BurningSnag(board, scanner), 4);
        addCard(new FlareUp(board, scanner), 4);
        addCard(new Airdrop(board, scanner), 4);
        addCard(new FireEngine(board, scanner), 4);
        addCard(new SmokeJumper(board, scanner), 4);
        addCard(new DozerLine(board, scanner), 3);
        addCard(new SmokeJumper(board, scanner), 3);
        addCard(new DeReforest(board, scanner), 4);
        addCard(new WindCard("North", board.getWindDirection(), board, scanner), 2);
        addCard(new WindCard("South", board.getWindDirection(), board, scanner), 2);
        addCard(new WindCard("East", board.getWindDirection(), board, scanner), 2);
        addCard(new WindCard("West", board.getWindDirection(), board, scanner), 2);
    }

    // Specifically so I don't have to use a lotta loops, don't use this method outside of this class
    public void addCard(Card card, int count) {
        for (int i = count; i > 0; i--) {
            deck.getDeck().add(card);
        }
    }
}
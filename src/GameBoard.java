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
        System.out.println("The wind direction has been changed to: " + windDirection);
        updateWeatherVane();
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
        System.out.println("The wind direction has been changed to: " + windDirection);
        updateWeatherVane();
    }

    private void updateWeatherVane() {
        setWeatherVane();
        if (windDirection == WindDirection.NORTH) {
            weatherVane[0][1] = "⬆️";
            weatherVane[0][2] = "⬆️";
        } else if (windDirection == WindDirection.SOUTH) {
            weatherVane[3][1] = "⬇️";
            weatherVane[3][2] = "⬇️";
        } else if (windDirection == WindDirection.EAST) {
            weatherVane[1][3] = "➡️";
            weatherVane[2][3] = "➡️";
        } else if (windDirection == WindDirection.WEST) {
            weatherVane[1][0] = "⬅️";
            weatherVane[2][0] = "⬅️";
        }
    }

    public Space checkOrthogonallyAdjacent(Space space) {
        return checkOrthogonallyAdjacent(space, windDirection);
    }

    public Space checkOrthogonallyAdjacent(Space space, WindDirection direction) {
        int rowOffset = 0;
        int colOffset = 0;
        if (direction == WindDirection.NORTH) {
            rowOffset = -1;
        } else if (direction == WindDirection.SOUTH) {
            rowOffset = 1;
        } else if (direction == WindDirection.EAST) {
            colOffset = 1;
        } else if (direction == WindDirection.WEST) {
            colOffset = -1;
        }
        int newRow = space.getY() + rowOffset;
        int newCol = space.getX() + colOffset;
        if (isValidPosition(board[newRow][newCol])) {
            return board[newRow][newCol];
        }
        return null;
    }

    public void placeFireInWindDirection(Scanner scan) {
        placeFireInAnyDirection(scan, windDirection);
    }

    public void placeFireInAnyDirection(Scanner scan, WindDirection direction) {   //does step 1 of a turn
        ArrayList<Space> validFire = new ArrayList<Space>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (isValidFire(board[j][i])) {
                    validFire.add(board[j][i]);
                }
            }
        }
        for (Space eachValidFire : validFire) {
            Space targetSpace = checkOrthogonallyAdjacent(board[eachValidFire.getY()][eachValidFire.getX()], direction);
            //System.out.println(eachValidFire);
            //System.out.println(targetSpace.getX() + ", " + targetSpace.getY());
            if (!(isValidFire(targetSpace)) && !(targetSpace instanceof Firebreak)) {
                board[targetSpace.getY()][targetSpace.getX()].setSpaceEmoji("⭕");  //still a tree
            }
        }
        selectFirePlacement(scan);
        printBoard();
    }

    public void placeFireAnyWhere(Scanner scan) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                WindDirection[] winds = new WindDirection[]{WindDirection.NORTH, WindDirection.EAST, WindDirection.SOUTH, WindDirection.WEST};
                for (int k = 0; k < winds.length; k++) {
                    Space targetSpace = checkOrthogonallyAdjacent(board[j][i]);
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
                board[selectedSpace.getY()][selectedSpace.getX()] = new Fire(selectedSpace.getX(), selectedSpace.getY());  //evolves into fire
                if (board[j][i].getSpaceEmoji().equals("⭕")) {
                    if (board[j][i] instanceof FireTower) {
                        board[j][i].setSpaceEmoji("\uD83C\uDFEF"); //goes back to displaying a fire tower
                    } else {
                        board[j][i].setSpaceEmoji("\uD83C\uDF32"); //goes back to displaying a tree
                    }
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
        return (focus.getSpaceEmoji().equals("\uD83D\uDD25") || focus instanceof EternalFlame) && isSpaceInBounds(focus.getX(), focus.getY());
        //return isValidFireBreak(focus) && !(focus instanceof EternalFlame);
    }

    public boolean isValidFirePlacement(Space focus) {
        return !(isValidFire(focus)) && !(focus instanceof Firebreak);
    }

    public boolean isValidFireBreakPlacement(Space focus) {
        WindDirection[] directions = new WindDirection[]{WindDirection.NORTH, WindDirection.WEST, WindDirection.SOUTH, WindDirection.EAST};
        for (WindDirection windDir: directions) {
            if (checkOrthogonallyAdjacent(focus, windDir) instanceof Firebreak) {
                return false;
            }
        }
        return !(focus instanceof FireTower) && !(focus instanceof Firebreak) && !(focus instanceof EternalFlame) && isSpaceInBounds(focus.getX(), focus.getY());
    }
    public boolean isValidWaterPlacement(Space focus) {
        return !(focus instanceof FireTower) && !(focus instanceof EternalFlame) && isSpaceInBounds(focus.getX(), focus.getY());
    }

    public Space getSpace(Scanner scan) {
        int x = -1;
        int y = -1;
        while(!isSpaceInBounds(x, y)) {
            System.out.println("Enter x coordinate:");
            x = scan.nextInt();
            System.out.println("Enter y coordinate:");
            y = scan.nextInt();
        }

        scan.nextLine();

        return obtainBoard()[y][x];
    }

    public boolean isSpaceInBounds(int x, int y) { //could've had single parameter, but I want both coordinates checked in a space for convenience
        return x >= 0 && x <= 15 && y >= 0 && y <= 15;
    }

    public WindDirection chooseDirection(Scanner scan) {
        String choice = "";
        while (!choice.equals("NORTH") && !choice.equals("EAST") && !choice.equals("SOUTH") && !choice.equals("WEST")) {
            System.out.print("In which direction would you like to build toward(NORTH, EAST, SOUTH, WEST)? ");
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

    public void initializeGameDeck(GameBoard board, Scanner scanner){
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
        addCard(new WindCard("North", WindDirection.NORTH, board, scanner), 2);
        addCard(new WindCard("South", WindDirection.SOUTH, board, scanner), 2);
        addCard(new WindCard("East", WindDirection.EAST, board, scanner), 2);
        addCard(new WindCard("West", WindDirection.WEST, board, scanner), 2);
    }

    // Specifically so I don't have to use a lotta loops, don't use this method outside of this class
    public void addCard(Card card, int count) {
        for (int i = count; i > 0; i--) {
            deck.getDeck().add(card);
        }
    }

    public boolean buildsOffFire(Space focus) {
        if (isValidFire(focus)) {
            return false;
        }
        int[] offsetX = {-1, 0, 1, 0};
        int[] offsetY = {0, -1, 0, 1};
        for (int i = 0; i < offsetX.length; i++) {
            if (isValidFire(board[focus.getY() + offsetY[i]][focus.getX() + offsetX[i]])) {
                return true;
            }
        }
        return false;
    }

    public void placeFire(Space target) {
        if (isValidFirePlacement(target)) {
            if (target instanceof FireTower) {
                target.setSpaceEmoji("\uD83D\uDD25");
            } else {
                board[target.getY()][target.getX()] = new Fire(target.getX(), target.getY());
            }
        }
    }

    public void placeSpace(Space target) {
        if (!(target instanceof FireTower)) {
            board[target.getY()][target.getX()] = new Space(target.getX(), target.getY());
        }
    }

    public void removeFire(Space target) {
        if (isValidWaterPlacement(target)) {
            board[target.getY()][target.getX()] = new Space(target.getX(), target.getY());
        }
    }

    public void placeFireBreak(Space target) {
        if (isValidFireBreakPlacement(target)) {
            board[target.getY()][target.getX()] = new Firebreak(target.getX(), target.getY());
        }
    }
}
import java.util.ArrayList;
public class GameBoard {
    Space[][] board;
    String[][] weatherVane;
    WindDirection windDirection;
    ArrayList<Card> deck = new ArrayList<>(60);
    ArrayList<Card> discard = new ArrayList<>();

    public GameBoard() {
        board = new Space[16][16];
        weatherVane = new String[4][4];
    }

    public void setBoard(Space[][] space)  {

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
        // top left
        for (int h = 0; h < 4; h++) {
            for (int i = y[h]; i < y[h] + 3; i++) {
                for (int j = x[h]; j < x[h] + 3; j++) {
                    if (i == 0 && j == 0) {
                        board[i][j] = new WinCondition("\uD83D\uDC9B", j, i); //NOTE: i refers to y since it dictates rows which is y-axis
                    }  else {
                        board[i][j] = new FireTower(j, i);        //NOTE: j refers to x since it dictates columns which is x-axis
                    }
                }
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 16; i++) {
            System.out.print((i / 10) + "\uFE0F⃣");
        }
        System.out.println();
        for (int i = 0; i < 16; i++) {
            System.out.print((i % 10) + "\uFE0F⃣");
        }
        System.out.println();
        for (int i = 0; i < 16; i++) {
            for (Space space1 : board[i]) {
                System.out.print(space1.getSpaceEmoji());
            }
            System.out.print((i / 10) + "\uFE0F⃣");
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
        if (windDirection == WindDirection.NORTH) {
            rowOffset = -1;
        } else if (windDirection == WindDirection.SOUTH) {
            rowOffset = 1;
        } else if (windDirection == WindDirection.EAST) {
            colOffset = 1;
        } else if (windDirection == WindDirection.WEST) {
            colOffset = -1;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] instanceof Fire) {
                    // Calculate the adjacent position
                    int newRow = i + rowOffset;
                    int newCol = j + colOffset;
                    // Check if the adjacent position is valid
                    if (isValidPosition(newRow, newCol)) {
                        return board[newRow][newCol];
                    }
                }
            }
        }
        return null;
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
                    if (isValidPosition(newRow, newCol) && board[newRow][newCol].getSpaceEmoji().equals("\uD83C\uDF32")) {
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

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    private boolean isValidFire(int row, int col) {
        return board[row][col] instanceof Firebreak || board[row][col] instanceof EternalFlame;
    }
}
public class Board {
    Space[][] board;
    String[][] weatherVane;
    WindDirection windDirection;
    public Board() {
        board = new Space[16][16];
        weatherVane = new String[4][4];
    }

    public void setBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Space("\uD83C\uDF32");
            }
        }
        board[7][7] = new EternalFlame();
        board[8][7] = new EternalFlame();
        board[7][8] = new EternalFlame();
        board[8][8] = new EternalFlame();
        // top left
        board[0][0] = new WinCondition("\uD83D\uDC9B");
        board[1][0] = new FireTower();
        board[2][0] = new FireTower();
        board[0][1] = new FireTower();
        board[1][1] = new FireTower();
        board[2][1] = new FireTower();
        board[0][2] = new FireTower();
        board[1][2] = new FireTower();
        board[2][2] = new FireTower();

        //top right
        board[0][15] = new WinCondition("❤\uFE0F");
        board[1][15] = new FireTower();
        board[2][15] = new FireTower();
        board[0][14] = new FireTower();
        board[1][14] = new FireTower();
        board[2][14] = new FireTower();
        board[0][13] = new FireTower();
        board[1][13] = new FireTower();
        board[2][13] = new FireTower();

        //bottom left
        board[15][0] = new WinCondition("\uD83D\uDC99");
        board[14][0] = new FireTower();
        board[13][0] = new FireTower();
        board[15][1] = new FireTower();
        board[14][1] = new FireTower();
        board[13][1] = new FireTower();
        board[15][2] = new FireTower();
        board[14][2] = new FireTower();
        board[13][2] = new FireTower();

        //bottom right
        board[15][15] = new WinCondition("\uD83D\uDC9A");
        board[14][15] = new FireTower();
        board[13][15] = new FireTower();
        board[15][14] = new FireTower();
        board[14][14] = new FireTower();
        board[13][14] = new FireTower();
        board[15][13] = new FireTower();
        board[14][13] = new FireTower();
        board[13][13] = new FireTower();

    }

    public void printBoard() {
        for (int i = 0; i < 10; i++) {
            System.out.print("0\uFE0F⃣");
        }
        for (int i = 0; i < 6; i++) {
            System.out.print("1\uFE0F⃣");
        }
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

    public void printWeatherVane() {
        for (String[] row : weatherVane) {
            System.out.print("             ");
            for (String block : row) {
                System.out.print(block);
            }
            System.out.println();
        }
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

    public Space checkFireSpreadable(Space space) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == space) {
                    if (windDirection == WindDirection.NORTH) {
                        if (i - 1 >= 0 && board[i - 1][j].getSpaceEmoji().equals("\uD83C\uDF32")) {
                            return board[i - 1][j];
                        }
                    } else if (windDirection == WindDirection.SOUTH) {
                        if (i + 1 < board.length && board[i + 1][j].getSpaceEmoji().equals("\uD83C\uDF32")) {
                            return board[i + 1][j];
                        }
                    } else if (windDirection == WindDirection.EAST) {
                        if (j + 1 < board[0].length && board[i][j + 1].getSpaceEmoji().equals("\uD83C\uDF32")) {
                            return board[i][j + 1];
                        }
                    } else {
                        if (j - 1 >= 0 && board[i][j - 1].getSpaceEmoji().equals("\uD83C\uDF32")) {
                            return board[i][j - 1];
                        }
                    }
                }
            }
        }
        return null;
        // given a space, check if its an instance of fire
        // if it is, then set the space adjancent to it to a fire space depending on what windDirection is
        // also check for otb
    }
}

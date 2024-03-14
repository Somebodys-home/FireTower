public class Board {
    Space[][] board;
    String windDirection;
    public Board() {
        board = new Space[16][16];
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
        board[0][0] = new WinCondition();
        board[1][0] = new FireTower();
        board[2][0] = new FireTower();
        board[0][1] = new FireTower();
        board[1][1] = new FireTower();
        board[2][1] = new FireTower();
        board[0][2] = new FireTower();
        board[1][2] = new FireTower();
        board[2][2] = new FireTower();

        //top right
        board[0][14] = new FireTower();
        board[1][14] = new FireTower();
        board[2][14] = new FireTower();
        board[0][13] = new FireTower();
        board[1][13] = new FireTower();
        board[2][13] = new FireTower();
        board[0][12] = new FireTower();
        board[1][12] = new FireTower();
        board[2][12] = new FireTower();

        //bottom left
        board[15][0] = new FireTower();
        board[14][0] = new FireTower();
        board[13][0] = new FireTower();
        board[15][1] = new FireTower();
        board[14][1] = new FireTower();
        board[13][1] = new FireTower();
        board[15][2] = new FireTower();
        board[14][2] = new FireTower();
        board[13][2] = new FireTower();

        //bottom right
        board[15][14] = new FireTower();
        board[14][14] = new FireTower();
        board[13][14] = new FireTower();
        board[15][13] = new FireTower();
        board[14][13] = new FireTower();
        board[13][13] = new FireTower();
        board[15][12] = new FireTower();
        board[14][12] = new FireTower();
        board[13][12] = new FireTower();

    }

    public void printBoard() {
        for (Space[] spaces : board) {
            for (Space space1 : spaces) {
                System.out.print(space1.getSpaceEmoji());
            }
            System.out.println();
        }

    }

    public void setWindDirection() {
        int direction = (int) (Math.random() * 3);
        if (direction == 0) {
            windDirection = "N";
        } else if (direction == 1) {
            windDirection = "S";
        } else if (direction == 2) {
            windDirection = "E";
        } else if (direction == 3) {
            windDirection = "W";
        }
    }

    public void spreadFire() {

    }
}

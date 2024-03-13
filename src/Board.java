public class Board {
    Space[][] board;

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
    }

    public void printBoard() {
        for (Space[] spaces : board) {
            for (Space space1 : spaces) {
                System.out.print(space1.getSpaceEmoji());
            }
            System.out.println();
        }

    }
}

public class Board {
    Space[][] board = new Space[16][16];
    Space space = new Space();

    public Board() {}

    public void setBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = space;
            }
        }
    }

    public Space[][] getBoard() {
        return board;
    }
}

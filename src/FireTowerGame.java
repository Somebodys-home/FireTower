public class FireTowerGame {
    Board board = new Board();
    // insert players
    // insert deck / discard here
    public void start() {
        board.setBoard();
        board.setWeatherVane();
        board.printBoard();
        board.printWeatherVane();
    }
}

public class Main {
    public static void main(String[] args) {
        while (true) {
            GameController game = new GameController(
                    new Player(new Position(0, 0)),
                    new Board(15)
            );

            game.run();
        }
    }
}
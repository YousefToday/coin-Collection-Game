public class Main {
    public static void main(String[] args) {
        GameController game = new GameController(
                new Player(new Position(1, 13)),
                new Agent(new Position(13, 13)),
                new Board(15)
        );

        game.run();
    }
}
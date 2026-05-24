import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private Player player;
    private Agent agent;
    private Board board;
    private ArrayList<Coin> coins;

    static Scanner scanner = new Scanner(System.in);

    public GameController(Player player, Agent agent, Board board) {
        this.player = player;
        this.agent = agent;
        this.board = board;
        coins = new ArrayList<>();
    }

    public void updateBoard() {
        System.out.println("Your move:");
        char playerMove = takaMoveInput();
        takeTurn(player, playerMove);

        char agentMove = agent.chooseMove(board);
        System.out.println("AI move: " + agentMove);
        takeTurn(agent, agentMove);

        board.printBoard();
        printScore();
    }

    private void takeTurn(Entity entity, char direction) {
        if (direction == 'X') {
            return;
        }

        board.removeEntFromCell(entity.getPos());
        entity.move(board, direction);
        board.addEntInCell(entity);

        checkCoins(entity);
    }

    public static boolean isValidMoveInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        char c = input.trim().toUpperCase().charAt(0);
        return c == 'W' || c == 'A' || c == 'S' || c == 'D';
    }

    public static char takaMoveInput() {
        String input;

        do {
            input = scanner.nextLine();

            if (!isValidMoveInput(input)) {
                System.out.println("Use W, A, S, or D to move!");
            }

        } while (!isValidMoveInput(input));

        return input.trim().toUpperCase().charAt(0);
    }

    public void checkCoins(Entity entity) {
        if (board.getCells(entity.getPos()).hasCoin()) {
            board.removeObjFromCell(entity.getPos());
            entity.collect();
        }
    }

    private void printScore() {
        System.out.println("Player coins: " + player.getCoins());
        System.out.println("AI coins: " + agent.getCoins());
        System.out.println();
    }

    public void run() {
        board.addEntInCell(player);
        board.addEntInCell(agent);

        setupLevel();

        System.out.println("Level: Greedy Gauntlet");
        System.out.println("Player = p");
        System.out.println("AI Agent = A");
        System.out.println("AI behavior: simple greedy Manhattan-distance movement.");
        System.out.println("Goal: collect more coins than the AI.");
        System.out.println();

        board.printBoard();
        printScore();

        while (!board.getCoinPositions().isEmpty()) {
            updateBoard();
        }

        System.out.println("Game Over!");
        printScore();

        if (player.getCoins() > agent.getCoins()) {
            System.out.println("Player wins.");
        } else if (agent.getCoins() > player.getCoins()) {
            System.out.println("AI wins.");
        } else {
            System.out.println("Draw.");
        }
    }
    private void addCoin(int x, int y) {
        Coin coin = new Coin(new Position(x, y));
        coins.add(coin);
        board.addObjInCell(coin);
    }

    private void setupLevel() {
        board.addWalls(true, 5, 2, 6);
        board.addWalls(true, 5, 8, 12);
        board.addWalls(true, 9, 2, 6);
        board.addWalls(true, 9, 8, 12);
        board.addWalls(false, 4, 6, 8);
        board.addWalls(false, 10, 6, 8);
        board.addWalls(false, 2, 1, 3);
        board.addWalls(true, 3, 3, 4);
        board.addWalls(false, 2, 11, 13);
        board.addWalls(true, 11, 3, 4);
        addCoin(1, 12);
        addCoin(2, 11);
        addCoin(3, 10);
        addCoin(3, 8);
        addCoin(13, 12);
        addCoin(12, 11);
        addCoin(11, 10);
        addCoin(5, 7);
        addCoin(9, 7);
        addCoin(6, 7);
        addCoin(7, 7);
        addCoin(8, 7);
        addCoin(7, 6);
        addCoin(7, 8);
        addCoin(2, 1);
        addCoin(12, 1);
    }
}
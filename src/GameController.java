import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private Player player;
    private Agent agent;
    private Board board;
    private ArrayList<Coin> coins;

    private int turns;
    private int failedMoves;
    private int maxTurns;
    private boolean stopWhenNoCoins;

    public GameController(Player player, Board board) {
        this.player = player;
        this.board = board;
        this.agent = null;
        coins = new ArrayList<>();

        turns = 0;
        failedMoves = 0;
        maxTurns = -1;
        stopWhenNoCoins = false;
    }

    public GameController(Player player, Agent agent, Board board) {
        this.player = player;
        this.agent = agent;
        this.board = board;
        coins = new ArrayList<>();

        turns = 0;
        failedMoves = 0;
        maxTurns = -1;
        stopWhenNoCoins = false;
    }

    public void updateBoard() {
        char playerMove = takaMoveInput();
        takeTurn(player, playerMove);

        if (agent != null) {
            char agentMove = agent.chooseMove(board);
            takeTurn(agent, agentMove);
        }

        board.printBoard();
        printStats();
    }

    private void takeTurn(Entity entity, char direction) {
        Position beforeMove = entity.getPos().copy();

        board.removeEntFromCell(entity.getPos());
        entity.move(board, direction);
        board.addEntInCell(entity);

        if (beforeMove.equals(entity.getPos())) {
            failedMoves++;
        }

        checkCoins(entity);
        turns++;
    }

    static Scanner scanner = new Scanner(System.in);

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
                System.out.println("Use W,A,S,or D to move!");
            }

        } while (!isValidMoveInput(input));

        return input.trim().toUpperCase().charAt(0);
    }

    public void checkGameLogicRules() {
        checkCoins(player);

        if (agent != null) {
            checkCoins(agent);
        }
    }

    public void checkCoins(Entity entity) {
        if (board.getCells(entity.getPos()).hasCoin()) {
            board.removeObjFromCell(entity.getPos());
            entity.collect();
        }
    }

    public boolean isGameOver() {
        if (maxTurns != -1 && turns >= maxTurns) {
            return true;
        }

        if (stopWhenNoCoins && board.getCoinPositions().isEmpty()) {
            return true;
        }

        return false;
    }

    public void setMaxTurns(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    public void setStopWhenNoCoins(boolean stopWhenNoCoins) {
        this.stopWhenNoCoins = stopWhenNoCoins;
    }

    public void printStats() {
        System.out.println("Player coins: " + player.getCoins());

        if (agent != null) {
            System.out.println("Agent coins: " + agent.getCoins());
        }

        System.out.println("Turns: " + turns);
        System.out.println("Failed moves: " + failedMoves);
    }

    public void run() {
        board.addEntInCell(player);

        if (agent != null) {
            board.addEntInCell(agent);
        }

        board.addWalls(false, 3, 2, 4);
        board.addWalls(false, 5, 2, 4);
        board.addWalls(false, 4, 2, 2);
        board.addWalls(false, 1, 5, 6);

        for (int i = 0; i < 10; i++) {
            Coin coin = new Coin(new Position((i * i + 14) % Board.getSize(), (i * i + 9) % Board.getSize()));
            coins.add(coin);
            board.addObjInCell(coin);
        }

        board.printBoard();

        while (!isGameOver()) {
            updateBoard();
        }
    }
}
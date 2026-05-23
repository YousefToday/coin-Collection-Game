import java.util.ArrayList;
import java.util.Random;

public class Agent extends Entity {
    private Random random;

    public Agent(Position p) {
        super(p, "A");
        random = new Random();
    }

    public char chooseMove(Board board) {
        ArrayList<Character> legalMoves = board.getLegalMoves(this);

        if (legalMoves.isEmpty()) {
            return ' ';
        }

        ArrayList<Position> coins = board.getCoinPositions();

        if (coins.isEmpty()) {
            return chooseRandomMove(legalMoves);
        }

        char bestMove = legalMoves.get(0);
        int bestDistance = Integer.MAX_VALUE;

        for (char move : legalMoves) {
            Position next = board.getNextPosition(pos, move);
            int distance = distanceToClosestCoin(next, coins);

            if (distance < bestDistance) {
                bestDistance = distance;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private char chooseRandomMove(ArrayList<Character> legalMoves) {
        int index = random.nextInt(legalMoves.size());
        return legalMoves.get(index);
    }

    private int distanceToClosestCoin(Position position, ArrayList<Position> coins) {
        int bestDistance = Integer.MAX_VALUE;

        for (Position coin : coins) {
            int distance = position.distanceTo(coin);

            if (distance < bestDistance) {
                bestDistance = distance;
            }
        }

        return bestDistance;
    }
}
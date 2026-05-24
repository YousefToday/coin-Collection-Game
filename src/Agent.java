import java.util.ArrayList;

public class Agent extends Entity {

    public Agent(Position p) {
        super(p, "A");
    }

    public char chooseMove(Board board) {
        ArrayList<Character> legalMoves = board.getLegalMoves(this);

        if (legalMoves.isEmpty()) {
            return 'X';
        }

        ArrayList<Position> coins = board.getCoinPositions();

        if (coins.isEmpty()) {
            return 'X';
        }

        Position target = getClosestCoin(coins);

        char preferredMove = choosePreferredMoveTowardTarget(board, legalMoves, target);

        if (preferredMove != 'X') {
            return preferredMove;
        }

        return chooseBestLegalMove(board, legalMoves, target);
    }

    private Position getClosestCoin(ArrayList<Position> coins) {
        Position closestCoin = coins.get(0);
        int bestDistance = pos.distanceTo(closestCoin);

        for (Position coin : coins) {
            int distance = pos.distanceTo(coin);

            if (distance < bestDistance) {
                bestDistance = distance;
                closestCoin = coin;
            }
        }

        return closestCoin;
    }

    private char choosePreferredMoveTowardTarget(Board board, ArrayList<Character> legalMoves, Position target) {
        int dx = target.x - pos.x;
        int dy = target.y - pos.y;

        if (dx > 0 && legalMoves.contains('D')) {
            return 'D';
        }

        if (dx < 0 && legalMoves.contains('A')) {
            return 'A';
        }

        if (dy > 0 && legalMoves.contains('S')) {
            return 'S';
        }

        if (dy < 0 && legalMoves.contains('W')) {
            return 'W';
        }

        return 'X';
    }

    private char chooseBestLegalMove(Board board, ArrayList<Character> legalMoves, Position target) {
        char bestMove = legalMoves.get(0);
        int bestDistance = Integer.MAX_VALUE;

        for (char move : legalMoves) {
            Position next = board.getNextPosition(pos, move);
            int distance = next.distanceTo(target);

            if (distance < bestDistance) {
                bestDistance = distance;
                bestMove = move;
            }
        }

        return bestMove;
    }
}
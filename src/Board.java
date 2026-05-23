import java.util.ArrayList;

public class Board {
    private static int size;
    private Cell[][] cells;

    public Board(int size) {
        Board.size = size;
        cells = new Cell[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                cells[x][y] = new Cell(new Position(x, y));
            }
        }
    }

    public void printBoard() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.print(cells[x][y]);
            }
            System.out.println();
        }
    }

    public void addObjInCell(GameObject object) {
        if (isInside(object.pos.x, object.pos.y)) {
            cells[object.pos.x][object.pos.y].setGameObject(object);
        }
    }

    public void removeObjFromCell(Position p) {
        if (isInside(p.x, p.y)) {
            cells[p.x][p.y].setGameObject(null);
        }
    }

    public void addEntInCell(Entity ent) {
        if (isInside(ent.pos.x, ent.pos.y)) {
            cells[ent.pos.x][ent.pos.y].setEntity(ent);
        }
    }

    public void removeEntFromCell(Position p) {
        if (isInside(p.x, p.y)) {
            cells[p.x][p.y].setEntity(null);
        }
    }

    public void addWalls(boolean column, int vector, int start, int end) {
        if (column) {
            for (int i = start; i <= end; i++) {
                if (isInside(vector, i)) {
                    cells[vector][i].setGameObject(new Obstacle(new Position(vector, i)));
                }
            }
        } else {
            for (int i = start; i <= end; i++) {
                if (isInside(i, vector)) {
                    cells[i][vector].setGameObject(new Obstacle(new Position(i, vector)));
                }
            }
        }
    }

    public Cell getCells(Position p) {
        return cells[p.x][p.y];
    }

    public static int getSize() {
        return size;
    }

    public boolean isInside(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public boolean isEmptyCell(int x, int y) {
        return isInside(x, y) && cells[x][y].isEmpty();
    }

    public boolean isWalkableCell(int x, int y) {
        return isInside(x, y) && cells[x][y].isWalkable();
    }

    public Position getNextPosition(Position p, char direction) {
        int newX = p.x;
        int newY = p.y;

        switch (Character.toUpperCase(direction)) {
            case 'W':
                newY--;
                break;
            case 'A':
                newX--;
                break;
            case 'S':
                newY++;
                break;
            case 'D':
                newX++;
                break;
            default:
                break;
        }

        return new Position(newX, newY);
    }

    public boolean canMove(Position p, char direction) {
        Position next = getNextPosition(p, direction);
        return isWalkableCell(next.x, next.y);
    }

    public ArrayList<Character> getLegalMoves(Entity ent) {
        ArrayList<Character> moves = new ArrayList<>();

        if (canMove(ent.getPos(), 'W')) {
            moves.add('W');
        }

        if (canMove(ent.getPos(), 'A')) {
            moves.add('A');
        }

        if (canMove(ent.getPos(), 'S')) {
            moves.add('S');
        }

        if (canMove(ent.getPos(), 'D')) {
            moves.add('D');
        }

        return moves;
    }

    public ArrayList<Position> getCoinPositions() {
        ArrayList<Position> coinPositions = new ArrayList<>();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (cells[x][y].hasCoin()) {
                    coinPositions.add(new Position(x, y));
                }
            }
        }

        return coinPositions;
    }
}
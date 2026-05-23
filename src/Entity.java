public abstract class Entity {
    protected Position pos;
    protected String icon;
    public int coins;

    public Entity(Position p, String icon) {
        pos = p;
        this.icon = icon;
        coins = 0;
    }

    public Position getPos() {
        return pos;
    }

    public String getIcon() {
        return icon;
    }

    public int getCoins() {
        return coins;
    }

    public void collect() {
        coins++;
    }

    public void move(Board board, char direction) {
        Position next = board.getNextPosition(pos, direction);

        if (board.isWalkableCell(next.x, next.y)) {
            pos = next;
        }
    }
}
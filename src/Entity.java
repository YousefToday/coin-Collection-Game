public abstract class Entity {
    protected Position pos;
    String icon;
    public int coins;
    public Entity(Position p , String icon) {
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
    public void collect(){
        coins++;
    }

    public abstract void move(Board board);
}

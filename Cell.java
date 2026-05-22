public class Cell {
    private Position pos;
    private GameObject object;
    private Entity occupant;

    public Cell(Position p) {
        pos = p;
        object = null;
    }
    public void setGameObject(GameObject object){
        this.object = object;
    }
    public GameObject getGameObject(){
        return object;
    }
    public void setEntity(Entity ent){
        this.occupant = ent;
    }
    public Entity getEntity(){
        return occupant;
    }
    @Override
    public String toString(){
        if(isOccupied())
            return " " + occupant.getIcon() + " ";
        else if(hasObject())
            return " "+ object.getIcon() + " ";
        else return " . ";
    }
    public boolean isOccupied(){
        return occupant != null;
    }
    public boolean hasObject(){
        return object != null;
    }
    public boolean isEmpty(){
        return !isOccupied() && !hasObject();
    }
    public boolean isWalkable(){
        return !isOccupied() && !(object instanceof Obstacle);
    }
    public boolean hasCoin(){
        return object instanceof Coin;
    }
}

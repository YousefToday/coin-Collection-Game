public class GameObject {
    protected Position pos;
    protected String icon;

    public GameObject(Position p , String icon){
        pos = p;
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }
}

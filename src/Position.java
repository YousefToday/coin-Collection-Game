import java.util.Objects;

public class Position {
    int x;
    int y;
    public Position(int x , int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position position) {
        return x == position.x && y == position.y;
    }
}

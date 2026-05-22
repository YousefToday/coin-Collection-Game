public class Board {
    private static int size;
    private Cell[][] cells;

    public Board(int size){
        Board.size = size;
        cells = new Cell[size][size];
        for(int y = 0; y < size; y++)
            for(int x = 0; x < size ; x++) {
                cells[x][y] = new Cell(new Position(x,y));
            }
    }
    public void printBoard(){
        for(int y = 0 ; y < size ; y++) {
            for(int x = 0 ; x < size  ; x++)
                System.out.print(cells[x][y]);
            System.out.println();
        }
    }

    public void addObjInCell(GameObject object) {
        cells[object.pos.x][object.pos.y].setGameObject(object);
    }
    public void removeObjFromCell(Position p){
        cells[p.x][p.y].setGameObject(null);
    }
    public void addEntInCell(Entity ent) {
        cells[ent.pos.x][ent.pos.y].setEntity(ent);
    }
    public void removeEntFromCell(Position p){
        cells[p.x][p.y].setEntity(null);
    }

    public void addWalls(boolean column ,int vector, int start ,int end ){
        if(column) {
            for (int i = start; i <= end; i++) {
                cells[vector][i].setGameObject(new Obstacle(new Position(vector , i)));
            }
        }else{
            for(int i = start ; i<=end ; i++) {
                    cells[i][vector].setGameObject(new Obstacle(new Position(i , vector)));
            }
        }
    }

    public Cell getCells(Position p) {
        return cells[p.x][p.y];
    }

    public static int getSize() { //for the player movement
        return size;
    }
    public boolean isEmptyCell(int x , int y){
        return cells[x][y].isEmpty();
    }
    public boolean isWalkableCell(int x, int y){
        Position p = new Position(x , y);
        return getCells(p).isWalkable();
    }
}

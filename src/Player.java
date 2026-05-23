public class Player extends Entity{
    public Player(Position p){
        super(p , "p");
    }
    @Override
    public void move(Board board){
        switch(GameController.takaMoveInput()){
                case 'W':
                    if(pos.y > 0 && board.isWalkableCell(pos.x , pos.y - 1))
                        pos.y--;
                    break;
                case 'A':
                    if(pos.x > 0 && board.isWalkableCell(pos.x - 1 , pos.y))
                        pos.x--;
                    break;
                case 'S':
                    if(pos.y < Board.getSize() - 1 && board.isWalkableCell(pos.x , pos.y + 1))
                        pos.y++;
                    break;
                case 'D':
                    if(pos.x < Board.getSize() - 1 && board.isWalkableCell(pos.x + 1 , pos.y))
                        pos.x++;
                    break;
        }
    }
}

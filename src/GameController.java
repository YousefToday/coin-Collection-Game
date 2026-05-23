import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class GameController {
    private Player player;
    private Board board;
    private ArrayList<Coin> coins;
    public GameController(Player player , Board board) {
        this.player = player;
        this.board = board;
        coins =  new ArrayList<>();
    }

    public void updateBoard() {
        board.removeEntFromCell(player.pos);
        player.move(board);
        board.addEntInCell(player);
        checkGameLogicRules();
        board.printBoard();
        System.out.println(player.coins);
    }
    static Scanner scanner  = new Scanner(System.in);

    public static boolean isValidMoveInput(String input){
        char c = input.trim().toUpperCase().charAt(0);
        return c == 'W' || c == 'A' || c == 'S' || c == 'D';
    }
    public static char takaMoveInput(){
        String input;
        do{
            input = scanner.nextLine();
            if(!isValidMoveInput(input)) System.out.println("Use W,A,S,or D to move!");
        }while(!isValidMoveInput(input));
        return input.trim().toUpperCase().charAt(0);
    }
    public void checkGameLogicRules(){
        checkCoins();
    }
    public void checkCoins(){
        if(board.getCells(player.pos).hasCoin()) {
            board.removeObjFromCell(player.pos);
            player.coins++;
        }

    }
    public void run(){
        board.addEntInCell(player);
        board.addWalls(false, 3, 2, 4);
        board.addWalls(false, 5, 2, 4);
        board.addWalls(false, 4, 2, 2);
        board.addWalls(false, 1, 5, 6);
        for(int i = 0; i < 10 ;i++){
            Coin coin = new Coin(new Position((i*i +14)%Board.getSize() ,(i*i + 9)%Board.getSize()));
            board.addObjInCell(coin);

        }
        board.printBoard();
        while(true) {
            updateBoard();
        }
    }
}

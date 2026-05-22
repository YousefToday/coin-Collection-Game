//import java.util.Scanner;
//
//public class Level {
//    private Board board;
//    private GameController logic;
//    public void runTheGame(GameController logic , Player player , int level){
//    }
//    public Level(int n){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("this is Room : " + n + "\n");
//        Player player;
//        switch (n) {
//            case 1:
//                board = new Board(7);
//                player = new Player(0, 6);
//                board.addInCell(player);
//                board.addWalls(false, 3, 2, 4);
//                board.addWalls(false, 5, 2, 4);
//                board.addWalls(false, 4, 2, 2);
//                board.addWalls(false, 1, 5, 6);
//                board.printBoard();
//                runTheGame(logic , player , n);
//                break;
//            case 2:
//                board = new Board(9);
//                player = new Player(0, 8);
//                board.addInCell(player);
//                board.addWalls(false, 8, 1, 2);
//                board.addWalls(false, 2, 0, 1);
//                board.addWalls(false, 5, 1, 3);
//                board.addWalls(false, 3, 4, 6);
//                board.addWalls(false, 1, 7, 8);
//                board.addWalls(false, 6, 8, 8);
//                board.addWalls(true, 4, 1, 7);
//                board.addWalls(true, 6, 6, 7);
//                board.printBoard();
//                runTheGame(logic , player , n);
//                break;
//            case 3:
//                board = new Board(13);
//                player = new Player(0, 12);
//                board.addInCell(player);
//                board.addWalls(false, 8, 1, 4);
//                board.addWalls(true, 4, 9, 11);
//
//                board.addWalls(true, 2, 2, 6);
//                board.addWalls(false, 3, 3, 4);
//
//                board.addWalls(true, 6, 2, 6);
//                board.addWalls(false, 6, 7, 9);
//
//                board.addWalls(true, 6, 8, 10);
//                board.addWalls(false, 8, 7, 8);
//
//                board.addWalls(false, 0, 9, 9);
//
//                board.addWalls(true, 11, 8, 9);
//
//                board.addWalls(false, 12, 10, 10);
//
//                board.addWalls(true, 10, 2, 3);
//                board.addWalls(false, 2, 9, 9);
//
//                board.printBoard();
//                runTheGame(logic , player , n);
//                break;
//        }
//    }
//    public GameController getLogic() {
//        return logic;
//    }
//}

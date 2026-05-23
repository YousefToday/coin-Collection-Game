//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true) {
            GameController game = new GameController(new Player(new Position(0 , 0)),new Board(15));
            game.run();
        }
    }
}
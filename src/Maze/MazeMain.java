package Maze;
import Maze.Maze;

import java.util.Scanner;

public class MazeMain {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.printMap();
        String move;
        for(move = scanner.nextLine();
            !move.equals("stop");
            move = scanner.nextLine()) {
            switch(move) {
                case "u": {
                    if (maze.canIMoveUp()) {
                        maze.moveUp();
                    }
                    break;
                }
                case "d": {
                    if (maze.canIMoveDown()) {
                        maze.moveDown();
                    }
                    break;
                }
                case "l": {
                    if (maze.canIMoveLeft()) {
                        maze.moveLeft();
                    }
                    break;
                }
                case "r": {
                    if (maze.canIMoveRight()) {
                        maze.moveRight();
                    }
                    break;
                }
            }
            maze.printMap();
            System.err.println("This is an error...");
            System.out.println("This is an not-error...");
        }
    }
}

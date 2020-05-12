package Critters;

import java.awt.*;
import java.util.Random;

public class ScanCat extends Critter {
    private Color color = Color.RED;
    private Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA};
    private int moveCount = 0;
    private int dir = 0;
    private Action lastAction = null;
    private int turnRight = 0;
    private int turnLeft = 0;
    private boolean goUp = false;
    private static Random random = new Random();

    public ScanCat() {
        super();
    }
    // This method should be overriden (default action is turning left)
    public Action getMove(CritterInfo info) {
        dir = info.getDirection().ordinal();
        Action action = Action.HOP;
        if (turnRight != 0) {
            if (turnRight == 3) action = Action.HOP;
            if (turnRight == 2) action = Action.RIGHT;
            if (turnRight == 1) action = Action.HOP;
            turnRight--;
        } else if (turnLeft != 0) {
            if (turnLeft == 3) action = Action.HOP;
            if (turnLeft == 2) action = Action.LEFT;
            if (turnLeft == 1) action = Action.HOP;
            turnLeft--;
        }
        if (info.frontThreat()) {
            action = Action.INFECT;
        } else if (info.getFront() == Neighbor.SAME) {
            action = Action.RIGHT;
            turnRight = 3;
        } else if (info.getFront() == Neighbor.WALL) {
            if (info.getDirection() == Direction.NORTH) {
                action = Action.RIGHT;
                goUp = false;
            } else if (info.getDirection() == Direction.SOUTH) {
                action = Action.RIGHT;
                goUp = true;
            } else if (info.getDirection() == Direction.EAST) {
                if (goUp) {
                    action = Action.LEFT;
                    turnLeft = 3;
                } else {
                    action = Action.RIGHT;
                    turnRight = 3;
                }
            } else {
                if (goUp) {
                    action = Action.RIGHT;
                    turnRight = 3;
                } else {
                    action = Action.LEFT;
                    turnLeft = 3;
                }
            }
        }
        moveCount++;
        lastAction = action;
        return action;
    }

    // This method should be overriden (default color is black)
    public Color getColor() {
        return this.color;
    }

    // This method should be overriden (default display is "?")
    public String toString() {
        return "SCAN";
    }
}
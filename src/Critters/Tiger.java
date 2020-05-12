package Critters;

import java.awt.*;
import java.util.Random;

public class Tiger extends Critter {
    private Color color = Color.RED;
    private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
    private int moveCount = 0;
    private static Random random = new Random();

    public Tiger() {
        super();
        color = colors[random.nextInt(3)];
    }
    // This method should be overriden (default action is turning left)
    public Action getMove(CritterInfo info) {
        if (moveCount % 3 == 0) {
            color = colors[random.nextInt(3)];
        }
        Action action;
        if (info.frontThreat()) {
            action = Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
            action = Action.LEFT;
        } else if (info.getFront() == Neighbor.SAME) {
            action = Action.RIGHT;
        } else {
            action = Action.HOP;
        }
        moveCount++;
        return action;
    }

    // This method should be overriden (default color is black)
    public Color getColor() {
        return this.color;
    }

    // This method should be overriden (default display is "?")
    public String toString() {
        return "TGR";
    }
}
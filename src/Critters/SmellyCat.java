package Critters;

import java.awt.*;
import java.util.Random;

public class SmellyCat extends Critter {
    private Color color = Color.RED;
    private String[] images = {"Sm","Sm","Sm","Sm"};
    private Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA};
    private int moveCount = 0;
    private int dir = 0;
    private Action lastAction = null;
    private static Random random = new Random();

    public SmellyCat() {
        super();
    }
    // This method should be overriden (default action is turning left)
    public Action getMove(CritterInfo info) {
        color = colors[moveCount % colors.length];
        dir = info.getDirection().ordinal();
        Action action = Action.HOP;
        if (info.frontThreat()) {
            action = Action.INFECT;
        }else if (info.getFront() == Neighbor.SAME) {
            action = Action.RIGHT;
        }else if (info.getFront() == Neighbor.WALL) {
            action = Action.RIGHT;
        }else if (info.getLeft() == Neighbor.SAME) {
            action = Action.RIGHT;
        }else if (info.getRight() == Neighbor.SAME) {
            action = Action.LEFT;
        }else if (info.getBack() == Neighbor.SAME) {
            action = Action.HOP;
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
        String s = images[dir];
        int i = moveCount % s.length();
        return s.substring(i, i+1);
    }
}
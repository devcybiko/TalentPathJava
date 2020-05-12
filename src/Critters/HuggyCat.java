package Critters;

import java.awt.*;
import java.util.Random;

public class HuggyCat extends Critter {
    private Color color = Color.RED;
    private String[] images = {"Hc", "Hc", "Hc", "Hc"};
    private Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA};
    private int moveCount = 0;
    private int dir = 0;
    private Action lastAction = null;
    private static Random random = new Random();

    public HuggyCat() {
        super();
    }
    // This method should be overriden (default action is turning left)
    public Action getMove(CritterInfo info) {
        color = colors[moveCount % colors.length];
        dir = info.getDirection().ordinal();
        Action action = Action.HOP;
        if (info.getFront() == Neighbor.WALL) {
            action = Action.RIGHT;
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
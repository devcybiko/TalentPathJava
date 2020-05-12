package Critters;

import java.awt.*;
import java.util.Random;

public class RandomCat extends Critter {
    private Color color = Color.RED;
    private String[] images = {"Rm","Rm","Rm","Rm"};
    private Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA};
    private int moveCount = 0;
    private int dir = 0;
    private Action lastAction = null;
    private static Random random = new Random();

    public RandomCat() {
        super();
    }
    // This method should be overriden (default action is turning left)
    public Action getMove(CritterInfo info) {
        color = colors[moveCount % colors.length];
        dir = info.getDirection().ordinal();
        int mv = random.nextInt(100);
        Action action = Action.HOP;
        if (info.frontThreat()) action = Action.INFECT;
        else if (mv <= 75) action = Action.HOP;
        else if (mv <= 87) action = Action.RIGHT;
        else if (mv <= 99) action = Action.LEFT;
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
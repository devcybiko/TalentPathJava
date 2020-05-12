package Critters;

import java.awt.*;
import java.util.Random;

public class AggressiveCat extends Critter {
    private Color color = Color.RED;
    private String[] images = {"A^|^","Vv|V", "(<-<" ,">->)"};
    private Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA};
    private int moveCount = 0;
    private int dir = 0;
    private Action lastAction = null;
    private static Random random = new Random();

    public AggressiveCat() {
        super();
    }
    // This method should be overriden (default action is turning left)
    public Action getMove(CritterInfo info) {
        color = colors[moveCount % colors.length];
        dir = info.getDirection().ordinal();
        Action action = Action.HOP;
        if (info.frontThreat()) {
                action = Action.INFECT;
        } else if (info.leftThreat()) {
                action = Action.LEFT;
        } else if (info.rightThreat()) {
                action = Action.RIGHT;
        } else if (info.backThreat()) {
            action = Action.RIGHT;
        }else if (info.getFront() != Neighbor.EMPTY) {
            action = random.nextInt(2) == 0 ? Action.RIGHT : Action.LEFT;
        } else if (lastAction == Action.INFECT) {
            action = random.nextInt(2) == 0 ? Action.RIGHT : Action.LEFT;
        } else {
            if (moveCount % (1+random.nextInt(10)) == 0) {
                action = random.nextInt(2) == 0 ? Action.LEFT : Action.RIGHT;
            } else {
                action = Action.HOP;
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
        String s = images[dir];
        int i = moveCount % s.length();
        return s.substring(i, i+1);
    }
}
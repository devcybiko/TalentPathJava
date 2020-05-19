package Critters;

import java.awt.*;
import java.util.Random;

public class GLS extends Critter {
    private Color color = Color.RED;
    private String[] images = {"XoXo"};
    private Color[] colors = {Color.RED};
    private int moveCount = 0;
    private int dir = 0;
    private Critter.Action lastAction = null;
    private static Random random = new Random();
    private static int retreat = 0;

    public GLS() {
        super();
    }

    // This method should be overriden (default action is turning left)
    public Critter.Action getMove(CritterInfo info) {
        color = colors[moveCount % colors.length];
        Critter.Action action = Action.HOP;
        if (retreat != 0) {
            action = Critter.Action.LEFT;
            retreat--;
        } else if (info.getFront() != Neighbor.EMPTY) {
            if (info.frontThreat()) {
                action = Action.INFECT;
                retreat = 2;
            } else if (info.leftThreat() && info.rightThreat()) {
                action = Action.LEFT;
                retreat = 1;
            } else if (info.leftThreat()) {
                action = Action.RIGHT;
            } else if (info.rightThreat()) {
                action = Action.LEFT;
            } else action = random.nextInt(2) == 0 ? Action.LEFT : Action.RIGHT;
        } else {
            if (random.nextInt(10) == 0) {
                action = random.nextInt(2) == 0 ? Action.LEFT : Action.RIGHT;
            }
        }

        lastAction = action;
        moveCount++;
        return action;
    }

    // This method should be overriden (default color is black)
    public Color getColor() {
        return this.color;
    }

    // This method should be overriden (default display is "?")
    public String toString() {
        return "GLS";
    }
}
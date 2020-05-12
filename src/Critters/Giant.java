package Critters;

import java.awt.*;
import java.util.Random;

public class Giant extends Critter {
    private Color color = Color.GRAY;
    private String[] images = {"fee", "fie", "foe", "fum"};
    private int moveCount = 0;

    public Giant() {
        super();
    }

    public Action getMove(CritterInfo info) {
        Action action;
        if (info.frontThreat()) {
            action = Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            action = Action.HOP;
        } else {
            action = Action.RIGHT;
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
        int img = (moveCount / 6) % images.length;
        return images[img];
    }
}
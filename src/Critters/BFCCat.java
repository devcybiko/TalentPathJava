package Critters;

import java.awt.*;

public class BFCCat extends Critter {
    private Color color = Color.BLACK;
    private final String image = "BFC";
    private int moveCount = 0;

    public BFCCat() {
        super();
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return image;
    }

    @Override
    public Action getMove(CritterInfo info) {
        Action action = null;
        if (info.frontThreat()) {
            action = Action.INFECT;
        }
        else if (info.leftThreat() || info.rightThreat()) {
            action = Action.HOP;
        }
        else if (info.getLeft() == Neighbor.OTHER || moveCount%3 == 0) {
            action = Action.LEFT;
        }
        else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.OTHER) {
            action = Action.RIGHT;
        }
        else if (info.getFront() == Neighbor.OTHER) {
            action = Action.INFECT;
        }
        else {
            action = Action.HOP;
        }
        moveCount++;
        return action;
    }
}
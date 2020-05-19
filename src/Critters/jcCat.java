package Critters;

import java.awt.*;

public class jcCat extends Critter{

    private Color color0 = Color.ORANGE;
    private String[] images = {"jca"};
    private int moveCount = 0;

    public Critter.Action getMove(CritterInfo info) {
        Critter.Action action = null;
        if (info.frontThreat()) {
            action = Critter.Action.INFECT;
            this.images[0] = "JCA";
        }
        else if (info.getFront() == Critter.Neighbor.EMPTY) {
            action = Critter.Action.HOP;
        } else {
            action = Critter.Action.LEFT ;
            return action;
        }
        moveCount++;
        return action;
    }

    public Color getColor() {
        return this.color0;
    }

    public String toString() {
        return images[0];
    }

}

package Critters;

import java.awt.*;

public class FACCat extends Critter {

    public FACCat() {
        super();
    }

    public Color getColor() {
        return Color.BLACK;
    }

    public String toString() {
        return "FAC";
    }

    public Critter.Action getMove(CritterInfo info) {
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            int rand = (int) (Math.random() * 2);
            if (rand == 0) {
                return Action.RIGHT;
            } else {
                return Action.LEFT;
            }
        }
    }
}
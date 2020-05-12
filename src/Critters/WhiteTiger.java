package Critters;

import java.awt.*;
import java.util.Random;

public class WhiteTiger extends Tiger {
    private boolean hasInfectedOther = false;

    public WhiteTiger() {
        super();
    }

    public Action getMove(CritterInfo info) {
        Action action = super.getMove(info);
        if (action == Action.INFECT) hasInfectedOther = true;
        return action;
    }
    public Color getColor() {
        return Color.WHITE;
    }

    public String toString() {
        return hasInfectedOther ? "TGR" : "tgr";
    }
}
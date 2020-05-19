package Critters;

import java.awt.Color;
import java.util.Random;

public class SKGCat extends Critter{
    private Critter.Action[] turns = {Critter.Action.LEFT, Critter.Action.RIGHT};
    private Random rand = new Random();
    public Critter.Action getMove(CritterInfo info) {
        Critter.Action action = null;
        if (info.frontThreat()) {
            action = Critter.Action.INFECT;
        } else if (info.leftThreat() || info.rightThreat() || info.backThreat()) {
            action = Critter.Action.HOP;
        }else{
           int index = rand.nextInt(2);
            action = turns[index];
        }
        return action;
    }

    public Color getColor() {
        return Color.BLUE;
    }

    public String toString() {
        return "SKG";
    }
}

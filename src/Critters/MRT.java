package Critters;

import java.awt.*;

public class MRT extends Critter {
    private int moveCount = 0;
    private Color[] images = {Color.WHITE, Color.LIGHT_GRAY, Color.GRAY, Color.DARK_GRAY, Color.BLACK};
    private Color[] images1 = {Color.YELLOW, Color.ORANGE, Color.RED};
    private boolean infected = false;

    public MRT(){
        getColor();
    }
    public Action getMove(CritterInfo info) {
        Action action;
            if(info.getFront() == Neighbor.OTHER){
                action = Action.INFECT;
                infected = true;
            }else if (info.getRight() == Neighbor.SAME){
                action = Action.LEFT;
            } else if (info.getLeft() == Neighbor.SAME){
                action = Action.RIGHT;
            } else if (info.getFront() == Neighbor.EMPTY){
                action = Action.RIGHT;
            } else {
                action = Action.LEFT;
            }
      moveCount++;
      return action;
    }

    public Color getColor() {
        Color newColor = null;
            int findNum = ((moveCount/1) % images.length);
            newColor = images[findNum];
        return newColor;
    }

    public String toString() {
        String str = "";
        if(infected) {
            str = "MRT";
        } else {
            str = "mrt";
        }
        return str;
    }
}

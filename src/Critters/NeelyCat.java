package Critters;

import java.awt.*;

public class NeelyCat extends Critter{

    private int moveCount;
    private boolean inCorner = false;

    public boolean isInCorner() {
        return inCorner;
    }

    public void setInCorner(boolean inCorner) {
        this.inCorner = inCorner;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    //getColor
    //Determines which color the critter will be displayed as
    //color.(enter color here)
    public Color getColor() {
        Color color = Color.BLACK;
        if (getMoveCount() % 2 == 0) {
            color = Color.RED;
        }
        return color;
    }

    //toString
    //determines which character the critter will be displayed as
    public String toString() {
        return "NAM";
    }

    //getMove
    //determines the movement behavior of your critter
    //can only return: action.up, action.left, action.right, action.infect
    //moveCounter may be helpful
    public Action getMove(CritterInfo info) {
        Action answer = Action.HOP;
        if (info.frontThreat()) {
            setMoveCount(getMoveCount()+1);
            answer = Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL){
            setMoveCount(getMoveCount()+1);
            answer = Action.RIGHT;
        } else if (info.getFront() == Neighbor.SAME) {
            setMoveCount(getMoveCount()+1);
            answer = Action.RIGHT;
        } else {
            setMoveCount(getMoveCount()+1);
        }
        return answer;
    }

}

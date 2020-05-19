package Critters;

import java.awt.*;
import java.util.Random;

public class BrianCat extends Critter {
    private Color color = Color.YELLOW;
    private final Color[] colors = {Color.YELLOW, Color.MAGENTA};
    private int moveCount = 0;
//    private int hops = 0;
//    private int lefts = 0;
//    private int rights = 0;
    // private Action lastMove = null;
    // private static Random rand = new Random();

    public BrianCat() {
        super();
    }

    public Action getMove(CritterInfo info){
        color = colors[moveCount % 2];
        Action action;
        if (info.frontThreat()) {
            action = Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            action = Action.HOP;
        }else if (info.getLeft() == Neighbor.WALL){
            action= Action.RIGHT;
        } else {
            action = Action.LEFT;
        }
        moveCount++;
        return action;

    }

    ///   more complicated does not equate to better survival
//    public Action getMove(CritterInfo info) {
//        color = colors[moveCount % 2];
//        Action action;
//        if(lefts >4 || rights > 4){
//            action = Action.HOP;
//        } else if(hops >=5){
//            action = Action.LEFT;
//            hops -=4;
//        } else if (info.frontThreat()) {
//            action = Action.INFECT;
//            rights--;
//            lefts--;
//        } else if ( info.getLeft() == Neighbor.WALL) {
//            action = Action.RIGHT;
//            rights++;
//            lefts--;
//        } else if ( info.getRight() == Neighbor.WALL) {
//            action = Action.LEFT;
//            lefts++;
//            rights--;
//        }else if(info.getFront() == Neighbor.EMPTY){
//            action = Action.HOP;
//            hops++;
//        } else {
//            action = Action.INFECT;
//
//        }
//        moveCount++;
//        return action;
//
//
//    }
//

    public Color getColor() {
        return this.color;
    }

    public String toString() {
        return "BWL";
    }
}

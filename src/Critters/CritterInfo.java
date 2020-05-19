// The CritterInfo interface defines a set of methods for querying the
// state of a critter simulation.  You should not alter this file.  See
// the documentation in the Critter class for a more detailed explanation.
package Critters;

public interface CritterInfo {
    public Critter.Neighbor getFront();
    public Critter.Neighbor getBack();
    public Critter.Neighbor getLeft();
    public Critter.Neighbor getRight();
    public Critter.Direction getDirection();
    public boolean frontThreat();
    public boolean backThreat();
    public boolean leftThreat();
    public boolean rightThreat();
}

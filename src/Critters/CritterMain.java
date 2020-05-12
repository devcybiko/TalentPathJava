// CSE 142 Homework 9 (Critters)
// Authors: Stuart Reges and Marty Stepp
//          modified by Kyle Thayer
//
// CritterMain provides the main method for a simple simulation program.  Alter
// the number of each critter added to the simulation if you want to experiment
// with different scenarios.  You can also alter the width and height passed to
// the CritterFrame constructor.

package Critters;

public class CritterMain {
    public static void main(String[] args) {
        CritterFrame frame = new CritterFrame(60, 40);

        // uncomment each of these lines as you complete these classes
        //frame.add(30, ClusterCat.class);
        //frame.add(30, HuggyCat.class);
        //frame.add(30, EastCat.class);
//        frame.add(30, Bear.class);
//        frame.add(30, Tiger.class);
//        frame.add(30, WhiteTiger.class);
//        frame.add(30, Giant.class);
        frame.add(30, NinjaCat.class);
        frame.add(30, HoppyCat.class);
        frame.add(30, AggressiveCat.class);
        frame.add(30, ScaredyCat.class);
        frame.add(30, SmellyCat.class);
        frame.add(30, RandomCat.class);
        frame.add(30, ScanCat.class);
        // frame.add(30, FlyCat.class);

        // frame.add(5, FlyTrap.class);
        //frame.add(30, Food.class);

        frame.start();
    }
}
